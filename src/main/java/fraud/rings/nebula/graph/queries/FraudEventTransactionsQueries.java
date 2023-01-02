package fraud.rings.nebula.graph.queries;

/**
 * @author Brume
 **/
public class FraudEventTransactionsQueries {

    public static String TRANSACTION_VERTEX = "INSERT VERTEX IF NOT EXISTS transaction( transaction_id, uuid, amount, currency, gateway, gateway_reference, manufacturer, id ) VALUES  ?:(?, ?, ?, ?, ?, ?, ?); ";

    public static String TRANSACTION_TAG = "CREATE TAG transaction( transaction_id string, uuid string, amount string, currency string, gateway string, gateway_reference string, manufacturer string, id int)";

    public static String USER_TO_TRANSACTION_EDGE = "CREATE EDGE makes_transaction(created_at timestamp)";

    public static String INSERT_USER_TO_TRANSACTION_EDGE = "INSERT EDGE makes_transaction(created_at) VALUES ? -> ? :(?)";

    public static String TRANSACTION_INDEX = "CREATE TAG INDEX IF NOT EXISTS transaction_index ON transaction();";

    public static String REBUILD_TRANSACTION_INDEX = "REBUILD TAG INDEX transaction_index";

    public static String FLAG_TRANSACTION_ABOVE_MONTHLY_BUDGET = "MATCH (v:transaction) where v.transaction.interest_rate > 1.0 RETURN v LIMIT 10";

    public static String GET_TRANSACTION_PATH_LENGTH2_WITH_SAME_DEVICE = "match p=(v1:transaction)-[e:has_device*2]-(v2:transaction) RETURN p limit 5";

}
