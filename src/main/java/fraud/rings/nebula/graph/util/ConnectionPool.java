package fraud.rings.nebula.graph.util;

import com.vesoft.nebula.client.graph.data.HostAddress;
import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.exception.IOErrorException;
import com.vesoft.nebula.client.graph.net.NebulaPool;
import com.vesoft.nebula.client.graph.net.Session;
import com.vesoft.nebula.jdbc.impl.NebulaDriver;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


/**
 * @author Brume
 **/
@Slf4j
public class ConnectionPool {
    private static final List<HostAddress> addresses = Arrays.asList(new HostAddress("127.0.0.1", 9669));
    private static final String username = "root";
    private static final String password = "nebula";


    public static Connection getConnection(String spaceName) throws SQLException {
        nebulaPool();

        return DriverManager.getConnection("jdbc:nebula://" + spaceName, username, password);
    }

    private static NebulaDriver nebulaPool() throws SQLException {
        Properties poolProperties = new Properties();

        poolProperties.put("addressList", addresses);
        poolProperties.put("minConnsSize", 2);
        poolProperties.put("maxConnsSize", 12);
        poolProperties.put("timeout", 1015);
        poolProperties.put("idleTime", 727);
        poolProperties.put("intervalIdle", 1256);
        poolProperties.put("waitTime", 1256);

        return new NebulaDriver(poolProperties);
    }

    public static Session getSession() {
        NebulaPool pool = new NebulaPool();
        Session session = null;
        try {
            pool.init(addresses, nebulaPool().getNebulaPoolConfig());
            session = pool.getSession(username, password, false);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return session;
    }

    public static Session getOrCreateSession(String spaceName) throws IOErrorException, GraphDatabaseException {
        Session session = getSession();
        if (spaceName != null) {
            ResultSet resultSet = session.execute("use " + spaceName + ";");
            if (!resultSet.isSucceeded()) {
                throw new GraphDatabaseException(resultSet.getErrorCode(), resultSet.getErrorMessage());
            }
        }
        return session;
    }
}
