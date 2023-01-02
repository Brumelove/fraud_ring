package fraud.rings.nebula.graph.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author Brume
 **/
@Data
public class TagDto {
    private Object vertexId;
    private String tagName;
    private Map<String, Object> props;
}
