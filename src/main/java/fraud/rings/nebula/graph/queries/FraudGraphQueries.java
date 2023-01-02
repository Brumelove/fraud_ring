package fraud.rings.nebula.graph.queries;

/**
 * @author Brume
 **/
public class FraudGraphQueries {


    public static String TIMESTAMP_VERTEX = "INSERT VERTEX IF NOT EXISTS lending_timestamp(timestamps ) VALUES  ?:(?); ";
    public static String TIMESTAMP_TAG = "CREATE TAG lending_timestamp(timestamps string)";

    public static String CREATE_SPACE(String spaceName) {
        return "CREATE SPACE " + spaceName + "(partition_num=15, replica_factor=1, vid_type=fixed_string(30));";
    }
}
