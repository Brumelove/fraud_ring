package fraud.rings.nebula.graph.util;

import com.vesoft.nebula.*;
import com.vesoft.nebula.client.graph.data.ResultSet;
import fraud.rings.nebula.graph.dto.*;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brume
 **/
@Slf4j
public class NebulaUtils {
    public static VertexDto fromVertex(Vertex vertex) {
        VertexDto result = new VertexDto();
        Object vertexId = vertex.vid.getFieldValue();
        ArrayList tags = new ArrayList<>();
        for (Tag tag : vertex.tags) {
            tags.add(fromTag(vertexId, tag));
        }
        result.setVertexId(vertexId);
        result.setTags(tags);
        return result;
    }


    public static TagDto fromTag(Object vertexId, Tag tag) {
        TagDto tagDTO = new TagDto();
        String tagName = new String(tag.name);
        tagDTO.setTagName(tagName);
        HashMap props = new HashMap<>();
        if (tag.props != null) {
            for (byte[] key : tag.props.keySet()) {
                props.put(new String(key), fromValue(tag.props.get(key)));
            }
        }
        tagDTO.setVertexId(vertexId);
        tagDTO.setProps(props);
        return tagDTO;
    }


    public static EdgeDto fromEdge(Edge edge) {
        EdgeDto edgeDTO = new EdgeDto();
        Object src = fromValue(edge.src);
        if (src instanceof Vertex) {
            edgeDTO.setSrcId(((VertexDto) src).getVertexId());
        } else {
            edgeDTO.setSrcId(src);
        }
        Object dst = fromValue(edge.dst);
        if (dst instanceof Vertex) {
            edgeDTO.setDstId(((VertexDto) dst).getVertexId());
        } else {
            edgeDTO.setDstId(dst);
        }
        HashMap props = new HashMap<>();
        if (edge.props != null) {
            for (byte[] key : edge.props.keySet()) {
                props.put(new String(key), fromValue(edge.props.get(key)));
            }
        }
        edgeDTO.setRank(edge.ranking);
        edgeDTO.setEdgeType(new String(edge.name));
        edgeDTO.setProps(props);
        return edgeDTO;
    }


    public static PathDto fromPath(Path path) {
        PathDto pathDTO = new PathDto();
        ArrayList<VertexDto> vertexDtos = new ArrayList<>();
        ArrayList<EdgeDto> edgeDtos = new ArrayList<>();
        List<Step> steps = path.getSteps();
        VertexDto srcVertexDto = fromVertex(path.src);
        vertexDtos.add(srcVertexDto);
        Object lastSrcVertexId = srcVertexDto.getVertexId();
        for (Step step : steps) {
            VertexDto newVertexDto = fromVertex(step.dst);
            vertexDtos.add(newVertexDto);
            EdgeDto edgeDTO = fromStep(step);
            edgeDTO.setSrcId(lastSrcVertexId);
            edgeDTO.setDstId(newVertexDto.getVertexId());
            lastSrcVertexId = newVertexDto.getVertexId();
            edgeDtos.add(edgeDTO);
        }
        pathDTO.setVertexDtos(vertexDtos);
        pathDTO.setEdgeDtos(edgeDtos);
        return pathDTO;
    }


    private static EdgeDto fromStep(Step step) {
        EdgeDto edgeDTO = new EdgeDto();
        HashMap<String, Object> props = new HashMap<>();
        if (step.props != null) {
            for (byte[] key : step.props.keySet()) {
                props.put(new String(key), fromValue(step.props.get(key)));
            }
        }
        edgeDTO.setEdgeType(new String(step.name));
        edgeDTO.setRank(step.ranking);
        edgeDTO.setProps(props);
        return edgeDTO;
    }


    public static Object fromValue(Value value) {
        Object fieldValue = value.getFieldValue();
        int setField = value.getSetField();
        switch (setField) {
            case Value.SVAL:
                fieldValue = new String((byte[]) fieldValue);
                break;
            case Value.VVAL:
                fieldValue = fromVertex(value.getVVal());
                break;
            case Value.EVAL:
                fieldValue = fromEdge(value.getEVal());
                break;
            case Value.PVAL:
                fieldValue = fromPath(value.getPVal());
                break;
            case Value.LVAL:
            case Value.UVAL:
                NList list = value.getLVal();
                ArrayList childList = new ArrayList();
                for (Value childValue : list.values) {
                    childList.add(fromValue(childValue));
                }
                fieldValue = childList;
                break;
            case Value.MVAL:
                NMap mVal = value.getMVal();
                HashMap map = new HashMap();
                for (byte[] skey : mVal.kvs.keySet()) {
                    map.put(new String(skey), fromValue(mVal.kvs.get(skey)));
                }
                fieldValue = map;
                break;
        }
        return fieldValue;
    }

    public static NebulaResultSet executeQuery(ResultSet resultSet) throws GraphDatabaseException {
        NebulaResultSet nebulaResultSet = new NebulaResultSet();
        try {
            if (!resultSet.isSucceeded()) {
                log.error("ERROR ::: ::: " + resultSet.getErrorMessage());
                return nebulaResultSet;
            }
            if (resultSet.isEmpty()) {
                return nebulaResultSet;
            }

            nebulaResultSet.setColumns(resultSet.keys());
            List<String> cols = resultSet.keys();
            List<Row> rows = resultSet.getRows();
            if (!rows.isEmpty()) {
                for (Row row : rows) {
                    Map<String, Object> props = new HashMap<>();
                    List<com.vesoft.nebula.Value> rowValues = row.getValues();
                    for (int i = 0; i < cols.size(); i++) {
                        String key = cols.get(i);
                        props.put(key, NebulaUtils.fromValue(rowValues.get(i)));
                    }
                    nebulaResultSet.getRows().add(props);
                }
            }

        } catch (Throwable e) {
            log.error("执行异常!", e);
            throw new GraphDatabaseException(e);
        }

        return nebulaResultSet;
    }
}
