package fraud.rings.nebula.graph.queries;

/**
 * @author Brume
 **/
public class FraudEventAddressQueries {


    public static String ADDRESS_VERTEX = "INSERT VERTEX IF NOT EXISTS address(id, city , street , country, latitude, longitude, postal_code, address ) VALUES  ?:(?,?, ?, ?, ?, ?, ?); ";


    public static String ADDRESS_TAG = "CREATE TAG address(id int, city string, street string, country string, latitude string, longitude string, postal_code string, address string)";

    public static String USER_TO_ADDRESS_EDGE = "CREATE EDGE lives_in(longitude string, latitude string)";

    public static String INSERT_USER_TO_ADDRESS_EDGE = "INSERT EDGE lives_in (longitude, latitude) VALUES ? -> ? :(?, ?)";

    public static String ADDRESS_INDEX = "CREATE TAG INDEX IF NOT EXISTS address_index ON address();";

    public static String REBUILD_ADDRESS_INDEX = "REBUILD TAG INDEX address_index";
}