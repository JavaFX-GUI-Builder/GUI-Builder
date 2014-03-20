package bdl;

import bdl.controller.Controller;

public interface Interface {

    public void setGUIBuilderController(Controller controller);

    public void sendMessageToBlueJ(String message);

    public boolean isBlueJAttached();

}
