package fraud.rings.nebula.graph.queries;

/**
 * @author Brume
 **/
public class FraudEventDeviceQueries {


    public static String DEVICE_VERTEX = "INSERT VERTEX IF NOT EXISTS device(device_id , uuid, manufacturer , device_type, os, device_model, user_agent, ip_address ) VALUES  ?:(?, ?, ?, ?,?,?,?,?); ";


    public static String DEVICE_TAG = "CREATE TAG IF NOT EXISTS device(device_id string, uuid string, manufacturer string, device_type string, os string, device_model string, user_agent string, ip_address string)";

    public static String USER_TO_DEVICE_EDGE = "CREATE EDGE has_device(created_at timestamp, ipAddress string)";

    public static String INSERT_USER_TO_DEVICE_EDGE = "INSERT EDGE has_device(created_at, ipAddress) VALUES ? -> ? :(?, ?)";

    public static String DEVICE_INDEX = "CREATE TAG INDEX IF NOT EXISTS device_index ON device();";

    public static String REBUILD_DEVICE_INDEX = "REBUILD TAG INDEX device_index";
}
