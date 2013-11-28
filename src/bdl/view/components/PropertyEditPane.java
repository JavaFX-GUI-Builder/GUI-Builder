package bdl.view.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
        for (final ComponentSettings.Properties p : cs.getProperties()) {
            HBox temp = createRow(p);
            if (temp != null) {
                vb.add(temp);
            }
        }

        Label ll = new Label("Layout");
        ll.setFont(new Font(ll.getFont().getFamily(), ll.getFont().getSize() + 3));
        vb.add(ll);
        for (final ComponentSettings.Layout p : cs.getLayout()) {
            HBox temp = createRow(p);
            if (temp != null) {
                vb.add(temp);
            }
        }

        Label jl = new Label("Listeners");
        jl.setFont(new Font(jl.getFont().getFamily(), jl.getFont().getSize() + 3));
        vb.add(jl);
        for (final ComponentSettings.Listeners p : cs.getListeners()) {
            HBox temp = createRow(p);
            if (temp != null) {
                vb.add(temp);
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

    private HBox createRow(final ComponentSettings.PropertyType p) {
        if (p.getValue()) {
            HBox a = new HBox();
            Label l = new Label(p.getName());
            l.setFont(new Font(l.getFont().getFamily(), l.getFont().getSize() + 1));
            Pane right;
            if (p.getType().equals("boolean")) {
                right = createCheckBox(p);
            } else {
                right = createTextField(p, p.getType());
            }

            Pane s = new Pane();
            a.setFillHeight(true);
            a.setSpacing(10);
            HBox.setHgrow(right, Priority.NEVER);
            HBox.setHgrow(l, Priority.NEVER);
            HBox.setHgrow(s, Priority.SOMETIMES);
            
            a.getChildren().addAll(l, s, right);
            return a;
        } else {
            return null;
        }
    }

    private Pane createCheckBox(final ComponentSettings.PropertyType p) {
        Pane a = new Pane();
        final CheckBox b = new CheckBox();
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Trigger: " + p.getName() + " " + p.getType() + " - " + b.isSelected());
            }
        });
        b.setAlignment(Pos.CENTER_LEFT);
        
        Pane s = new Pane();
        a.getChildren().addAll(b, s);
        return a;
    }

    private Pane createTextField(final ComponentSettings.PropertyType p, String type) {
        Pane a = new Pane();
        final TextField tf = new TextField();
        tf.promptTextProperty().setValue(type);

        tf.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (t1 == false) {
                    System.out.println("Trigger: " + p.getName() + " " + p.getType() + " - " + tf.getText());
                }
            }
        });
        tf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Trigger: " + p.getName() + " " + p.getType() + " - " + tf.getText());
            }
        });
        a.getChildren().add(tf);
        return a;
    }
}
