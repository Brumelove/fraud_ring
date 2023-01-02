package fraud.rings.nebula.graph.dao.impl;


import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.jdbc.impl.NebulaResultSet;
import fraud.rings.nebula.graph.dao.interfaces.FraudEventTransactionsDao;
import fraud.rings.nebula.graph.dto.TransactionDTO;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;
import fraud.rings.nebula.graph.queries.FraudEventTransactionsQueries;
import fraud.rings.nebula.graph.statements.FraudEventStatements;
import fraud.rings.nebula.graph.util.ConnectionPool;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import static fraud.rings.nebula.graph.util.Constants.FRAUD_SPACE;


/**
 * @author Brume
 **/
@Slf4j
public class FraudEventTransactionsDaoImpl extends FraudEventTransactionsQueries implements FraudEventTransactionsDao {

    @Override
    public void createTag() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(TRANSACTION_TAG)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud transaction tag", e);
        }
    }

    @Override
    public void createVertex(TransactionDTO transaction) throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(TRANSACTION_VERTEX)

        ) {
            FraudEventStatements.prepareInsertFraudEventTransactions(transaction, statement);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud transaction vertex", e);
        }
    }

    @Override
    public void createEdge() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(USER_TO_TRANSACTION_EDGE)

        ) {
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud transaction edge", e);
        }
    }

    @Override
    public void insertEdge(String userId, String transactionId, Timestamp createdAt) throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_TO_TRANSACTION_EDGE)

        ) {
            FraudEventStatements.prepareInsertFraudEventTransactionEdge(userId, transactionId, createdAt, statement);

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while inserting into fraud transaction edge", e);
        }
    }

    @Override
    public void createIndex() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(TRANSACTION_INDEX)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud transaction index", e);
        }
    }

    @Override
    public void reBuildIndex() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(REBUILD_TRANSACTION_INDEX)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud transaction rebuild index", e);
        }
    }

    @Override
    public NebulaResultSet testNebulaResult() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(GET_TRANSACTION_PATH_LENGTH2_WITH_SAME_DEVICE)

        ) {
            var result = statement.executeQuery();

//            Session session = ConnectionPool.getOrCreateSession(FRAUD_SPACE);
//            String createSpace = GET_TRANSACTION_PATH_LENGTH2_WITH_SAME_DEVICE;

//            NebulaResultSet result = NebulaUtils.executeQuery(session.execute(createSpace));

            NebulaResultSet nebulaResultSet = new NebulaResultSet((ResultSet) result, statement);
            log.info("TEST RESULT" + nebulaResultSet);

            return nebulaResultSet;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud transaction rebuild index", e);
        }
    }
}
