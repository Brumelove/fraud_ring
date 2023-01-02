package fraud.rings.nebula.graph.dao.impl;


import fraud.rings.nebula.graph.dao.interfaces.FraudEventAddressDao;
import fraud.rings.nebula.graph.dto.BillingAddressDTO;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;
import fraud.rings.nebula.graph.queries.FraudEventAddressQueries;
import fraud.rings.nebula.graph.statements.FraudEventStatements;
import fraud.rings.nebula.graph.util.ConnectionPool;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static fraud.rings.nebula.graph.util.Constants.FRAUD_SPACE;


/**
 * @author Brume
 **/
@Slf4j
public class FraudEventAddressDaoImpl extends FraudEventAddressQueries implements FraudEventAddressDao {

    @Override
    public void createTag() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(ADDRESS_TAG)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event address tag", e);
        }
    }

    @Override
    public void createVertex(BillingAddressDTO address) throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(ADDRESS_VERTEX)

        ) {
            FraudEventStatements.prepareInsertFraudEventAddress(address, statement);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event address vertex", e);
        }
    }

    @Override
    public void createEdge() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(USER_TO_ADDRESS_EDGE)

        ) {

            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event address edge", e);
        }
    }

    @Override
    public void insertEdge(String userId, int addressId, String longitude, String latitude) throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_TO_ADDRESS_EDGE)

        ) {
            FraudEventStatements.prepareInsertFraudEventAddressEdge(userId, addressId, longitude, latitude, statement);

            boolean result = statement.execute();
            log.info("result" + result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while inserting fraud event address edge", e);
        }
    }

    @Override
    public void createIndex() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(ADDRESS_INDEX)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event address index", e);
        }
    }

    @Override
    public void reBuildIndex() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(REBUILD_ADDRESS_INDEX)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event address rebuild index", e);
        }
    }


}
