package fraud.rings.nebula.graph.dao.impl;


import com.vesoft.nebula.client.graph.exception.IOErrorException;
import com.vesoft.nebula.client.graph.net.Session;
import fraud.rings.nebula.graph.dao.interfaces.FraudGraphDao;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;
import fraud.rings.nebula.graph.queries.FraudGraphQueries;
import fraud.rings.nebula.graph.queries.GraphQueries;
import fraud.rings.nebula.graph.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static fraud.rings.nebula.graph.util.Constants.FRAUD_SPACE;


/**
 * @author Brume
 **/
public class FraudGraphDaoImpl extends FraudGraphQueries implements FraudGraphDao {

    public void createSpace() throws IOErrorException {
        Session session = ConnectionPool.getSession();
        String createSpace = GraphQueries.CREATE_SPACE(FRAUD_SPACE);

        session.execute(createSpace);
    }

    @Override
    public void createTag() throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(TIMESTAMP_TAG)

        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event tag", e);
        }
    }

    @Override
    public void createVertex(String timestamp) throws GraphDatabaseException {
        try (Connection connection = ConnectionPool.getConnection(FRAUD_SPACE);
             PreparedStatement statement = connection.prepareStatement(TIMESTAMP_VERTEX)

        ) {
            // prepareInsertLendingEventTimestamp(timestamp, statement);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new GraphDatabaseException("An error occurred while creating fraud event vertex", e);
        }
    }

    @Override
    public void createEdge() {

    }


}
