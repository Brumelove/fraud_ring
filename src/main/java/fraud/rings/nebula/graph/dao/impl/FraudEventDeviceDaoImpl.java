package fraud.rings.nebula.graph.dao.impl;


import fraud.rings.nebula.graph.dao.interfaces.FraudEventDeviceDao;
import fraud.rings.nebula.graph.dto.DeviceDTO;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;
import fraud.rings.nebula.graph.queries.FraudEventDeviceQueries;
import fraud.rings.nebula.graph.statements.FraudEventStatements;
import fraud.rings.nebula.graph.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import static fraud.rings.nebula.graph.util.Constants.FRAUD_SPACE;


/**
 * @author Brume
 **/
public class FraudEventDeviceDaoImpl extends FraudEventDeviceQueries implements FraudEventDeviceDao {

    @Override
    public void createTag() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(DEVICE_TAG)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event device tag", e);
        }
    }

    @Override
    public void createVertex(DeviceDTO device) throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(DEVICE_VERTEX)

        ) {
            FraudEventStatements.prepareInsertFraudEventDevice(device, statement);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while while creating fraud event device vertex", e);
        }
    }

    @Override
    public void createEdge() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(USER_TO_DEVICE_EDGE)

        ) {
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event device edge ", e);
        }
    }

    @Override
    public void insertEdge(String userId, String deviceId, Timestamp createdAt, String ipAddress) throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_TO_DEVICE_EDGE)

        ) {
            FraudEventStatements.prepareInsertFraudEventDeviceEdge(userId, deviceId, createdAt, ipAddress, statement);

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while while inserting fraud event device edge", e);
        }
    }

    @Override
    public void createIndex() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(DEVICE_INDEX)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event device index", e);
        }
    }

    @Override
    public void reBuildIndex() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(REBUILD_DEVICE_INDEX)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event device rebuild index", e);
        }
    }
}
