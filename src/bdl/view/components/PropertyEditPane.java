package bdl.view.components;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Ben Goodwin
 */
public class PropertyEditPane extends HBox {

    private VBox vbox = new VBox();

    public PropertyEditPane() {
        this.getChildren().addAll(vbox);
        updateContents("Button");
        vbox.setFillWidth(true);
    }

    private void setContents(ComponentSettings cs) {
        ObservableList vb = vbox.getChildren();

        Label hl = new Label("Properties");
        hl.setFont(new Font(hl.getFont().getFamily(), hl.getFont().getSize() + 3));
        vb.add(hl);
        for (ComponentSettings.Properties p : cs.getProperties()) {
            if (p.getValue()) {
                HBox a = new HBox();
                Label l = new Label(p.getName());
                l.setFont(new Font(l.getFont().getFamily(), l.getFont().getSize() + 1));
                TextField tf = new TextField();
                tf.promptTextProperty().setValue(p.getType());

                Pane s = new Pane();
                a.setFillHeight(true);
                a.setSpacing(10);
                HBox.setHgrow(tf, Priority.NEVER);
                HBox.setHgrow(l, Priority.NEVER);
                HBox.setHgrow(s, Priority.ALWAYS);

                tf.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });

                a.getChildren().addAll(l, s, tf);
                vb.add(a);
            }
        }

        Label il = new Label("Layout");
        il.setFont(new Font(il.getFont().getFamily(), il.getFont().getSize() + 3));
        vb.add(il);

        for (ComponentSettings.Layout p : cs.getLayout()) {
            if (p.getValue()) {
                HBox a = new HBox();
                Label l = new Label(p.getName());
                l.setFont(new Font(l.getFont().getFamily(), l.getFont().getSize() + 1));
                TextField tf = new TextField();
                tf.promptTextProperty().setValue(p.getType());

                Pane s = new Pane();
                a.setFillHeight(true);
                a.setSpacing(10);
                HBox.setHgrow(tf, Priority.NEVER);
                HBox.setHgrow(l, Priority.NEVER);
                HBox.setHgrow(s, Priority.ALWAYS);

                tf.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });

                a.getChildren().addAll(l, s, tf);
                vb.add(a);
            }
        }

        Label jl = new Label("Listeners");
        jl.setFont(new Font(jl.getFont().getFamily(), jl.getFont().getSize() + 3));
        vb.add(jl);

        for (ComponentSettings.Listeners p : cs.getListeners()) {
            if (p.getValue()) {
                HBox a = new HBox();
                Label l = new Label(p.getName());
                l.setFont(new Font(l.getFont().getFamily(), l.getFont().getSize() + 1));
                TextField tf = new TextField();
                tf.promptTextProperty().setValue(p.getType());

                Pane s = new Pane();
                a.setFillHeight(true);
                a.setSpacing(10);
                HBox.setHgrow(tf, Priority.NEVER);
                HBox.setHgrow(l, Priority.NEVER);
                HBox.setHgrow(s, Priority.ALWAYS);

                tf.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });

                a.getChildren().addAll(l, s, tf);
                vb.add(a);
            }
        }
    }

    /**
     * Updates the panel with the settings per component
     *
     * @param componentName The currently selected Component name to fill the
     * properties panel with the appropriate settings for.
     */
    public void updateContents(String componentName) {
        vbox.getChildren().clear();
        setContents(ComponentViewReader.getSettingsByName(componentName));
    }
}
