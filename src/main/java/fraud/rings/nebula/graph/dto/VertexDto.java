package fraud.rings.nebula.graph.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Brume
 **/
@Data
public class VertexDto {
    private Object vertexId;
    private List<TagDto> tags;
}
