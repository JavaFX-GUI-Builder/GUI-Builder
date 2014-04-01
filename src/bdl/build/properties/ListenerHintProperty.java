package bdl.build.properties;

import bdl.build.GObject;
import bdl.build.GUIObject;
import bdl.lang.LabelGrabber;
import bdl.view.right.hints.ListenerHintWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ListenerHintProperty implements PanelProperty {

    private Button button = new Button(LabelGrabber.getLabel("hint.button.text"));

    public ListenerHintProperty (final GObject gObj, final GUIObject guiObject, String name, final String text, GridPane gp, int row) {
        gp.add(new Label(name + ":"), 0, row);
        gp.add(button, 1, row);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new ListenerHintWindow(guiObject.getClassName() + '.' + gObj.getFieldName() + text);
            }
        });
    }

    @Override
    public String getJavaCode() {
        return "";
    }

    @Override
    public String getFXMLCode() {
        return "";
    }
}
