package bdl.view.right.properties;

import bdl.build.GObject;
import bdl.build.GUIObject;
import bdl.view.right.hints.ListenerHintWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ListenerHintProperty implements PanelProperty {

    private Button button = new Button("Hint");

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
}
