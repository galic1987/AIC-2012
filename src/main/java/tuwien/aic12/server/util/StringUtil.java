package tuwien.aic12.server.util;

/**
 *
 * @author vanjalee
 */
public class StringUtil {

    public boolean isNotEmpty(String str) {
        if (str != null && !str.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
