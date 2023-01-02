package fraud.rings.nebula.graph.dao.interfaces;


import fraud.rings.nebula.graph.dto.PaymentDTO;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;

import java.sql.Timestamp;

/**
 * @author Brume
 **/
public interface FraudEventPaymentDao {
    void createTag() throws GraphDatabaseException;

    void createVertex(PaymentDTO payment) throws GraphDatabaseException;

    void createEdge() throws GraphDatabaseException;

    void insertEdge(String userId, String paymentId, Timestamp createdAt) throws GraphDatabaseException;

    void createIndex() throws GraphDatabaseException;

    void reBuildIndex() throws GraphDatabaseException;

}
