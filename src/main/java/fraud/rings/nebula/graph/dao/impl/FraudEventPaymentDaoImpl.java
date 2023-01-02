package fraud.rings.nebula.graph.dao.impl;


import fraud.rings.nebula.graph.dao.interfaces.FraudEventPaymentDao;
import fraud.rings.nebula.graph.dto.PaymentDTO;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;
import fraud.rings.nebula.graph.queries.FraudEventPaymentQueries;
import fraud.rings.nebula.graph.statements.FraudEventStatements;
import fraud.rings.nebula.graph.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import static fraud.rings.nebula.graph.util.Constants.FRAUD_SPACE;


/**
 * @author Brume
 **/
public class FraudEventPaymentDaoImpl extends FraudEventPaymentQueries implements FraudEventPaymentDao {

    @Override
    public void createTag() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(PAYMENT_TAG)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event payment tag", e);
        }
    }

    @Override
    public void createVertex(PaymentDTO payment) throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(PAYMENT_VERTEX)

        ) {
            FraudEventStatements.prepareInsertFraudEventPayment(payment, statement);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event payment vertex", e);
        }
    }

    @Override
    public void createEdge() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(USER_TO_PAYMENT_EDGE)

        ) {
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred  creating fraud event payment edge ", e);
        }
    }

    @Override
    public void insertEdge(String userId, String paymentId, Timestamp createdAt) throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_TO_PAYMENT_EDGE)

        ) {
            FraudEventStatements.prepareInsertFraudEventPaymentEdge(userId, paymentId, createdAt, statement);

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while inserting into fraud event payment edge", e);
        }
    }

    @Override
    public void createIndex() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(PAYMENT_INDEX)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event payment index", e);
        }
    }

    @Override
    public void reBuildIndex() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(REBUILD_PAYMENT_INDEX)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event payment rebuild index", e);
        }
    }
}
