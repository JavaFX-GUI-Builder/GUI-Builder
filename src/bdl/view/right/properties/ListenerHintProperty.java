package bdl.view.right.properties;

import bdl.view.right.hints.ListenerHintWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ListenerHintProperty implements PanelProperty {

    private Button button = new Button("Hint");
    private Control control;

    public ListenerHintProperty (String name, final String text, GridPane gp, int row) {
        gp.add(new Label(name + ":"), 0, row);
        gp.add(button, 1, row);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new ListenerHintWindow(text);
            }
        });
    }

    @Override
    public String getJavaCode() {
        return "";
    }
}
