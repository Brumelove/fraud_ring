package fraud.rings.nebula.graph.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Brume
 **/
@Data
public class PathDto {
    private List<VertexDto> vertexDtos;
    private List<EdgeDto> edgeDtos;
}
