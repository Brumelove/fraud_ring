package fraud.rings.nebula.graph.queries;

/**
 * @author Brume
 **/
public class FraudEventPaymentQueries {
    public static String PAYMENT_VERTEX = "INSERT VERTEX IF NOT EXISTS payment_method(payment_method_id, method_type, card_last_four, expiry_month, expiry_year, id, uuid ) VALUES  ?:(?, ?, ?,?, ?,?); ";


    public static String PAYMENT_TAG = "CREATE TAG payment_method(payment_method_id string, method_type string, card_last_four string, expiry_month string, expiry_year string, id int, uuid string)";

    public static String USER_TO_PAYMENT_EDGE = "CREATE EDGE pays_by(created_at timestamp)";

    public static String INSERT_USER_TO_PAYMENT_EDGE = "INSERT EDGE pays_by (created_at) VALUES ? -> ? :(?)";

    public static String PAYMENT_INDEX = "CREATE TAG INDEX IF NOT EXISTS payment_method_index ON payment_method();";

    public static String REBUILD_PAYMENT_INDEX = "REBUILD TAG INDEX payment_method_index";
}
