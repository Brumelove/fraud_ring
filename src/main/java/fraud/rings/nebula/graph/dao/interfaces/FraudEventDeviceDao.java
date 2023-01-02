package fraud.rings.nebula.graph.dao.interfaces;

import fraud.rings.nebula.graph.dto.DeviceDTO;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;

import java.sql.Timestamp;

/**
 * @author Brume
 **/
public interface FraudEventDeviceDao {
    void createTag() throws GraphDatabaseException;

    void createVertex(DeviceDTO device) throws GraphDatabaseException;

    void createEdge() throws GraphDatabaseException;

    void insertEdge(String userId, String deviceId, Timestamp createdAt, String ipAddress) throws GraphDatabaseException;

    void createIndex() throws GraphDatabaseException;

    void reBuildIndex() throws GraphDatabaseException;

}
