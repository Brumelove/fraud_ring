package fraud.rings.nebula.graph.queries;

/**
 * @author Brume
 **/
public class FraudEventUserQueries {


    public static String USER_VERTEX = "INSERT VERTEX IF NOT EXISTS user(user_id, email, uuid, first_name, last_name, mobile,registration_time,created_at, id , deleted_at) VALUES  ?:(?, ?, ?, ?, ?, ?, ?, ?, ?); ";


    public static String USER_TAG = "CREATE TAG user(user_id string, email string, uuid string, first_name string, last_name string, mobile string,registration_time string,created_at timestamp, id int, deleted_at timestamp)";

    public static String USER_INDEX = "CREATE TAG INDEX IF NOT EXISTS user_index ON user();";

    public static String REBUILD_USER_INDEX = "REBUILD TAG INDEX user_index";

    public static String HAS_EMAIL_EDGE = "CREATE EDGE has_email(email string)";

    public static String INSERT_HAS_EMAIL_EDGE = "INSERT EDGE has_email(email) VALUES ? -> ? :(?)";

    public static String HAS_PHONE_NUMBER_EDGE = "CREATE EDGE has_phone_number(phone_number string)";

    public static String INSERT_HAS_PHONE_NUMBER_EDGE = "INSERT EDGE has_phone_number(phone_number) VALUES ? -> ? :(?)";
}
