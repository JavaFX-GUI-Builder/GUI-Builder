package bdl.view.right.properties;

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

        //Upon losing focus, save to the GObject
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println(text); //TODO - Add pop-up window of text
            }
        });
    }

    @Override
    public String getJavaCode() {
        return null;
    }
}
