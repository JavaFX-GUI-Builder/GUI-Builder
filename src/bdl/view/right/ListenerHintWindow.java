package bdl.view.right;

import bdl.model.ComponentSettings;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ben Goodwin
 */
public class ListenerHintWindow {

    public ListenerHintWindow(ComponentSettings.PropertyType p) {
        Stage s = new Stage();
        Pane pane = new Pane();
        VBox vb = new VBox();
        s.resizableProperty().setValue(false);
        Scene sc = new Scene(pane, 400, 200);
        s.setScene(sc);
        Label l = new Label("This code template is for suggestion only");
        TextArea ta = new TextArea();
        ta.setEditable(false);
        if (p.getName().equals("onActionProperty")) {
            ta.setText(".setOnAction(new EventHandler<ActionEvent>() {\n"
                    + "    @Override\n" + "    public void handle(ActionEvent t) {\n"
                    + "        //TODO\n" + "    }\n" + "});");
        } else if (p.getName().equals("textProperty")) {
            ta.setText(".setOnKeyTyped(new EventHandler<KeyEvent>() {\n"
                    + "    @Override\n" + "    public void handle(KeyEvent t) {\n"
                    + "        //TODO\n" + "    }\n" + "});");
        }
        vb.getChildren().addAll(l, ta);
        pane.getChildren().add(vb);
        s.show();
    }
}
