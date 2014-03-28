package bdl;

import bdl.controller.Controller;
import java.io.File;

public interface Interface {

    // GUI Builder -> BlueJ
    
    public void setGUIBuilderController(Controller controller);
    
    public File getWorkingDirectory();
    
    public boolean isEditingGUI();
    
    public String getOpenGUIName();
    
    public File getOpenGUIFile();
    
    public void recompileOpenGUI();
    
    public File getUserPrefDir();

    public void sendMessageToBlueJ(String message);

    public boolean isBlueJAttached();
    
    // BlueJ -> GUI Builder
    
    public void show();
    
    public void hide();

}
