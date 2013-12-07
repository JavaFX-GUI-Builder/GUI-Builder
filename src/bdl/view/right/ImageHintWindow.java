/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bdl.view.right;

import bdl.view.components.ComponentSettings;
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
public class ImageHintWindow {

    public ImageHintWindow(ComponentSettings.PropertyType p) {
        Stage s = new Stage();
        Pane pane = new Pane();
        VBox vb = new VBox();
        s.resizableProperty().setValue(false);
        Scene sc = new Scene(pane, 400, 200);
        s.setScene(sc);
        Label l = new Label("This code template is for suggestion only");
        TextArea ta = new TextArea();
        ta.setEditable(false);
        ta.setText(".setGraphic(new Image(**FILELOCATION**));");
        vb.getChildren().addAll(l, ta);
        pane.getChildren().add(vb);
        s.show();
    }
}