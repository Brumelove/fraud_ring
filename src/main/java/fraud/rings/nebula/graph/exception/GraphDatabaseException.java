package fraud.rings.nebula.graph.exception;


//import com.vesoft.nebula.meta.ErrorCode;

import com.vesoft.nebula.ErrorCode;

/**
 * @author brume
 */
public class GraphDatabaseException extends Exception {

    int code = ErrorCode.E_UNKNOWN.getValue();

    public GraphDatabaseException(String s) {
        super(s);
    }

    public GraphDatabaseException(Throwable t) {
        super(t);
    }

    public GraphDatabaseException(int code, String msg) {
        super("code:" + code + ",msg:" + msg);
        code = code;
    }

    public GraphDatabaseException(String s, Throwable t) {
        super(s, t);
    }

    public int getCode() {
        return code;
    }

}

