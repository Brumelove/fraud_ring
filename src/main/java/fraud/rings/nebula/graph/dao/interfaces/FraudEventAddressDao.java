package fraud.rings.nebula.graph.dao.interfaces;


import fraud.rings.nebula.graph.dto.BillingAddressDTO;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;

/**
 * @author Brume
 **/
public interface FraudEventAddressDao {
    void createTag() throws GraphDatabaseException;

    void createVertex(BillingAddressDTO address) throws GraphDatabaseException;

    void createEdge() throws GraphDatabaseException;

    void insertEdge(String userId, int addressId, String longitude, String latitude) throws GraphDatabaseException;

    void createIndex() throws GraphDatabaseException;

    void reBuildIndex() throws GraphDatabaseException;
}
