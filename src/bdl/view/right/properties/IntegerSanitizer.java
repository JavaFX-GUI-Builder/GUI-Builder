package bdl.view.right.properties;

/**
 *
 * @author Ben Goodwin
 */
public class IntegerSanitizer {
    public static int sanitize(String defaultValue) {
        return Integer.parseInt(defaultValue.split(".")[0]);
    }
}