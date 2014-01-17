package bdl.view.right.properties;

/**
 *
 * @author Ben Goodwin
 */
public class StringSanitizer {
    
    
    public static String sanitize(String oldString) {
        StringBuilder newString = new StringBuilder();
        char[] c = oldString.toCharArray();
        for(int i = 0; i < c.length; i++) {
            if((i == c.length-1) && c[i] == '\\') {
                
            }
            else if (c[i] != '\"') {
                newString.append(c[i]);
            }
        }
        return newString.toString();
    }
}

