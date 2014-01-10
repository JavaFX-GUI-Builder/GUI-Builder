package bdl.model;

/**
 *
 * @author Ben Goodwin
 */
public class ListenerHint {
    private String listenerName;
    private String listenerMethod;
    private String listenerEvent;
    private String listenerText;

    public ListenerHint(String name, String method, String event) {
        listenerName = name;
        listenerMethod = method;
        listenerEvent = event;
        listenerText = buildText();
    }
    
    public String getName() {
        return listenerName;
    }

    public String getText() {
        return listenerText;
    }

    private String buildText() {
        return "." + listenerMethod + "(new EventHandler<" + listenerEvent + ">() {\n"
                + "    @Override\n" + "    public void handle(" + listenerEvent + " e) {\n"
                + "        //TODO\n" + "    }\n" + "});";
    }
}