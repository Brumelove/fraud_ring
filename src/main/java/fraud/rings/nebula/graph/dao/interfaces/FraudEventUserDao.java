package fraud.rings.nebula.graph.dao.interfaces;

import fraud.rings.nebula.graph.dto.UserDTO;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;

/**
 * @author Brume
 **/
public interface FraudEventUserDao {
    void createTag() throws GraphDatabaseException;

    void createVertex(UserDTO fraudEventUser) throws GraphDatabaseException;

    void createEmailEdge() throws GraphDatabaseException;

    void createPhoneNumberEdge() throws GraphDatabaseException;

    void insertEmailEdge(String userId, String transactionId, String email) throws GraphDatabaseException;

    void insertPhoneNumberEdge(String userId, String transactionId, String phoneNumber) throws GraphDatabaseException;

    void createIndex() throws GraphDatabaseException;

    void reBuildIndex() throws GraphDatabaseException;
}
