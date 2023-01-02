package fraud.rings.nebula.graph.dao.impl;


import fraud.rings.nebula.graph.dao.interfaces.FraudEventUserDao;
import fraud.rings.nebula.graph.dto.UserDTO;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;
import fraud.rings.nebula.graph.queries.FraudEventUserQueries;
import fraud.rings.nebula.graph.statements.FraudEventStatements;
import fraud.rings.nebula.graph.util.ConnectionPool;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static fraud.rings.nebula.graph.util.Constants.FRAUD_SPACE;


/**
 * @author Brume
 **/
@Slf4j
public class FraudEventUserDaoImpl extends FraudEventUserQueries implements FraudEventUserDao {

    @Override
    public void createTag() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(USER_TAG)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event user tag", e);
        }
    }

    @Override
    public void createVertex(UserDTO fraudEventUser) throws GraphDatabaseException {
        ResultSet result;
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(USER_VERTEX)

        ) {
            FraudEventStatements.prepareInsertFraudEventUser(fraudEventUser, statement);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event user vertex", e);
        }
    }

    public void createEmailEdge() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(HAS_EMAIL_EDGE)

        ) {
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud transaction edge", e);
        }
    }

    @Override
    public void insertEmailEdge(String userId, String transactionId, String email) throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(INSERT_HAS_EMAIL_EDGE)

        ) {
            FraudEventStatements.prepareInsertFraudEventUserEdge(userId, transactionId, email, statement);

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while inserting into fraud transaction edge", e);
        }
    }

    public void createPhoneNumberEdge() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(HAS_PHONE_NUMBER_EDGE)


        ) {
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud transaction edge", e);
        }
    }

    @Override
    public void insertPhoneNumberEdge(String userId, String transactionId, String phoneNumber) throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(INSERT_HAS_PHONE_NUMBER_EDGE)

        ) {
            FraudEventStatements.prepareInsertFraudEventUserEdge(userId, transactionId, phoneNumber, statement);

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while inserting into fraud transaction edge", e);
        }
    }

    @Override
    public void createIndex() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(USER_INDEX)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event user index", e);
        }
    }

    @Override
    public void reBuildIndex() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(REBUILD_USER_INDEX)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event user rebuid index", e);
        }
    }
}
