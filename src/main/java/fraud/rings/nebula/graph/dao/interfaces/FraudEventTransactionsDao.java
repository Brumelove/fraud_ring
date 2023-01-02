package fraud.rings.nebula.graph.dao.interfaces;

import com.vesoft.nebula.client.graph.exception.IOErrorException;
import com.vesoft.nebula.jdbc.impl.NebulaResultSet;
import fraud.rings.nebula.graph.dto.TransactionDTO;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author Brume
 **/
public interface FraudEventTransactionsDao {
    void createTag() throws GraphDatabaseException;

    void createVertex(TransactionDTO transaction) throws GraphDatabaseException;

    void createEdge() throws GraphDatabaseException;

    void insertEdge(String userId, String transactionId, Timestamp createdAt) throws GraphDatabaseException;

    void createIndex() throws GraphDatabaseException;

    void reBuildIndex() throws GraphDatabaseException;

    NebulaResultSet testNebulaResult() throws GraphDatabaseException, IOErrorException, SQLException, GraphDatabaseException;
}
