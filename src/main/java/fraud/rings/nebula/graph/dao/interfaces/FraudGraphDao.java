package fraud.rings.nebula.graph.dao.interfaces;

import com.vesoft.nebula.client.graph.exception.IOErrorException;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;

/**
 * @author Brume
 **/
public interface FraudGraphDao {
    void createSpace() throws IOErrorException;

    void createTag() throws GraphDatabaseException;

    void createVertex(String timestamp) throws GraphDatabaseException;

    void createEdge();
}
