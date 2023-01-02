package fraud.rings.nebula.graph.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author Brume
 **/
@Data
public class EdgeDto {
    private Object srcId;
    private Object dstId;
    private Long rank;
    private String edgeType;
    private Map<String, Object> props;
}
