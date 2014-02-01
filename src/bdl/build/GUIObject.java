package bdl.build;

import bdl.lang.LabelGrabber;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.AnchorPane;

public class GUIObject extends AnchorPane {

    private String className = "GUI";
    private String title = LabelGrabber.getLabel("default.gui.title");
    private double width;
    private double height;

    public String getGUITitle() {
        return title;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getGUIWidth() {
        return width;
    }

    public void setGUIWidth(double width) {
        this.width = width;
    }

    public double getGUIHeight() {
        return height;
    }

    public void setGUIHeight(double height) {
        this.height = height;
    }
}
