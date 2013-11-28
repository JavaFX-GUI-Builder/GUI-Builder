package bdl.view.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Ben Goodwin
 */
public class PropertyEditPane extends Pane {

    private GridPane grid = new GridPane();

    public PropertyEditPane() {
        this.setPadding(new Insets(3, 3, 3, 3));
        updateContents("Button");
        this.getChildren().add(grid);
    }

    private void setContents(ComponentSettings cs) {
        int x = 0;

        Label hl = new Label("Properties");
        hl.setFont(Font.font(hl.getFont().getFamily(), FontWeight.BOLD, hl.getFont().getSize() + 0.5));
        grid.add(hl, 0, x, 2, 1);
        x++;
        for (final ComponentSettings.Properties p : cs.getProperties()) {
            if (p.getValue()) {
                createRow(p, x);
                x++;
            }
        }

        Label ll = new Label("Layout");
        ll.setFont(Font.font(ll.getFont().getFamily(), FontWeight.BOLD, ll.getFont().getSize() + 0.5));
        grid.add(ll, 0, x);
        x++;
        for (final ComponentSettings.Layout p : cs.getLayout()) {
            if (p.getValue()) {
                createRow(p, x);
                x++;
            }
        }

        Label jl = new Label("Listeners");
        jl.setFont(Font.font(jl.getFont().getFamily(), FontWeight.BOLD, jl.getFont().getSize() + 0.5));
        grid.add(jl, 0, x);
        x++;
        for (final ComponentSettings.Listeners p : cs.getListeners()) {
            if (p.getValue()) {
                createRow(p, x);
                x++;
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
        grid = new GridPane();
        setContents(ComponentViewReader.getSettingsByName(componentName));
    }

    private void createRow(final ComponentSettings.PropertyType p, int row) {
        Label l = new Label(p.getName());
        GridPane.setHalignment(l, HPos.RIGHT);
        GridPane.setMargin(l, new Insets(0, 5, 0, 0));
        grid.add(l, 0, row);
        if (p.getType().equals("boolean")) {
            grid.add(createCheckBox(p), 1, row);
        } else if (p.getType().equals("File"))   {
            grid.add(createTextField(p), 1, row);
        } else if (p.getType().equals("Color"))   {
            grid.add(createColorPicker(p), 1, row);
        } else {
            grid.add(createTextField(p), 1, row);
        }
    }

    private CheckBox createCheckBox(final ComponentSettings.PropertyType p) {
        final CheckBox b = new CheckBox();
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Trigger: " + p.getName() + " " + p.getType() + " - " + b.isSelected());
            }
        });

        return b;
    }

    private TextField createTextField(final ComponentSettings.PropertyType p) {
        final TextField tf = new TextField();
        tf.promptTextProperty().setValue(p.getType());
        if (p.getType().equals("int")) {
            tf.setMaxWidth(50);
        } else {
            tf.setMaxWidth(100);
        }
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
        return tf;
    }

    private ColorPicker createColorPicker(final ComponentSettings.PropertyType p) {
        final ColorPicker cp = new ColorPicker();
        cp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Trigger: " + p.getName() + " " + p.getType() + " - " + cp.getValue().toString());
            }
            
        });
        return cp;
    }
}
