package bdl.build;

import bdl.lang.LabelGrabber;
import javafx.scene.layout.AnchorPane;

public class GUIObject extends AnchorPane {

    public String getGUITitle() {
        return LabelGrabber.getLabel("default.gui.title");
    }

    public String getClassName() {
        return "GUI";
    }
}
