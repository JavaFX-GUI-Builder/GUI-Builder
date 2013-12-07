package bdl.view.right;

import bdl.build.GObject;
import bdl.build.scene.control.GButton;
import bdl.view.components.ComponentSettings;
import bdl.view.components.ComponentViewReader;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Ben Goodwin
 */
public class PropertyEditPane extends Pane {

    private GridPane grid = new GridPane();
    private TextField name = new TextField();
    private TextField text = new TextField();
    private Button image = new Button();
    private CheckBox enabled = new CheckBox();
    private TextField tooltip = new TextField();
    private ColorPicker foreground = new ColorPicker();
    private ColorPicker background = new ColorPicker();
    private TextField xPos = new TextField();
    private TextField yPos = new TextField();
    private TextField minHeight = new TextField();
    private TextField minWidth = new TextField();
    private TextField maxHeight = new TextField();
    private TextField maxWidth = new TextField();
    private Button listenerHint = new Button();
    private Node[] list = {name, text, image, enabled, tooltip, foreground, background, xPos, yPos, minHeight, minWidth, maxHeight, maxWidth, listenerHint};
    private GObject object;

    public PropertyEditPane() {
        updateContents(new GButton(null));
        this.getChildren().add(grid);
    }

    private void setContents(ComponentSettings cs, GObject gObj) {
        int row = 0;
        int i = 0;
        Label hl = new Label("Properties");
        hl.setFont(Font.font(hl.getFont().getFamily(), FontWeight.BOLD, hl.getFont().getSize() + 0.5));
        grid.add(hl, 0, row, 2, 1);
        row++;
        ArrayList<ComponentSettings.Properties> alp = (ArrayList<ComponentSettings.Properties>) cs.getProperties();
        for (int j = 0; j < alp.size(); j++) {
            if (list[i] instanceof TextField && alp.get(j).getValue()) {
                String value = "";
                if(gObj instanceof Labeled) {
                    Labeled lObj = (Labeled) gObj;
                    value = lObj.getText();
                }
                System.out.println("1 " + value + " " + alp.get(j).getName());
                list[i] = createTextField(alp.get(j), value);
                createRow(alp.get(j).getName(), list[i], row);
                System.out.println("2 " + value + " " + alp.get(j).getName());
            } else if (list[i] instanceof Button && alp.get(j).getValue()) {
                if (alp.get(j).getName().equals("image")) {
                    list[i] = createImageHint(alp.get(j));
                }
                createRow(alp.get(j).getName(), list[i], row);
            } else if (list[i] instanceof CheckBox && alp.get(j).getValue()) {
                list[i] = createCheckBox(alp.get(j));
                createRow(alp.get(j).getName(), list[i], row);
            } else if (list[i] instanceof ColorPicker && alp.get(j).getValue()) {
                list[i] = createColorPicker(alp.get(j));
                createRow(alp.get(j).getName(), list[i], row);
            }
            i++;
            row++;
        }

        Label ll = new Label("Layout");
        ll.setFont(Font.font(ll.getFont().getFamily(), FontWeight.BOLD, ll.getFont().getSize() + 0.5));
        grid.add(ll, 0, row);
        row++;
        ArrayList<ComponentSettings.Layout> all = (ArrayList<ComponentSettings.Layout>) cs.getLayout();
        for (int j = 0; j < all.size(); j++) {
            if (list[i] instanceof TextField && all.get(j).getValue()) {
                String value = "";
                list[i] = createTextField(all.get(j), value);
                createRow(all.get(j).getName(), list[i], row);
            }
            row++;
        }

        Label jl = new Label("Listeners");
        jl.setFont(Font.font(jl.getFont().getFamily(), FontWeight.BOLD, jl.getFont().getSize() + 0.5));
        grid.add(jl, 0, row);
        row++;
        ArrayList<ComponentSettings.Listeners> alli = (ArrayList<ComponentSettings.Listeners>) cs.getListeners();
        for (int j = 0; j < alli.size(); j++) {
            if (list[i] instanceof Button && alli.get(j).getValue()) {
                if (alli.get(j).getType().equals("listeners")) {
                    list[i] = createListenerHint(alli.get(j));
                }
                createRow(alli.get(j).getName(), list[i], row);
            }
            row++;
        }
    }

    /**
     * Updates the panel with the settings per component
     *
     * @param componentName The currently selected Component name to fill the
     * properties panel with the appropriate settings for.
     */
    public void updateContents(GObject gObj) {
        object = gObj;
        grid = new GridPane();
        ComponentSettings cs = ComponentViewReader.getSettingsByName(gObj.getType().toString());
        setContents(cs, gObj);
        //fillContents(gObj);
    }

    private void fillContents(GObject gObj) {
        if (gObj != null) {
            //Most generic to most specific
            ((TextField)list[0]).setText(gObj.getFieldName());

            if (gObj instanceof Node) {
                //Catches all, but uses instanceof just to fit in with the rest...
                Node nObj = (Node) gObj;
                xPos.setText("" + nObj.getLayoutX());
                yPos.setText("" + nObj.getLayoutY());
            }
            if (gObj instanceof Control) {
                //Catches Labeled (all of those below), TextArea, TextField, ToolBar, ComboBox, ColorPicker, etc
                Control cObj = (Control) gObj;
//            cObj.getHeight();
//            cObj.getWidth();
                maxHeight.setText("" + cObj.getMaxHeight());
                maxWidth.setText("" + cObj.getMaxWidth());
                minHeight.setText("" + cObj.getMinHeight());
                minWidth.setText("" + cObj.getMinWidth());
                enabled.setSelected(cObj.disableProperty().getValue());
                tooltip.setText((cObj.getTooltip() != null) ? cObj.getTooltip().getText() : "");
            }
            if (gObj instanceof Labeled) {
                //Catches TitledPane, Label, Button, CheckBox, Hyperlink, MenuButton, ToggleButton
                Labeled lObj = (Labeled) gObj;
                text.setText(lObj.getText());
//            lObj.isUnderline();
//            lObj.getFont();
//            lObj.getTextFill();
//            lObj.getAlignment();
            }
            if (gObj instanceof Shape) {
                Shape sObj = (Shape) gObj;
                foreground.setValue((Color) sObj.getStroke());
                background.setValue((Color) sObj.getFill());
            }

            //Then go to the most specific, to catch the single-type only values
            if (gObj instanceof CheckBox) {
                CheckBox cObj = (CheckBox) gObj;
                cObj.isSelected();
            }
        }
    }

    private void createRow(String p, Node node, int row) {
        Label l = new Label(p);
        GridPane.setHalignment(l, HPos.RIGHT);
        GridPane.setMargin(l, new Insets(0, 5, 0, 0));
        grid.add(l, 0, row);
        grid.add(node, 1, row);
    }

    private CheckBox createCheckBox(final ComponentSettings.PropertyType p) {
        final CheckBox b = new CheckBox();
        b.setMaxWidth(120);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Trigger: " + p.getName() + " " + p.getType() + " - " + b.isSelected());
            }
        });

        return b;
    }

    private TextField createTextField(final ComponentSettings.PropertyType p, String value) {
        final TextField tf = new TextField();
        tf.promptTextProperty().setValue(p.getType());
        if (p.getType().equals("int")) {
            tf.setMaxWidth(50);
        } else {
            tf.setMaxWidth(120);
        }
        if (p.getName().equals("name")) {
            addValidation(tf, "name");
        } else {
            addValidation(tf, p.getType());
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
        tf.setText(value);
        System.out.println("3 " + value + " " + p.getName());
        return tf;
    }

    private ColorPicker createColorPicker(final ComponentSettings.PropertyType p) {
        final ColorPicker cp = new ColorPicker();
        cp.setMaxWidth(120);
        cp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Trigger: " + p.getName() + " " + p.getType() + " - " + cp.getValue().toString());
            }
        });
        return cp;
    }

    private Button createImageHint(final ComponentSettings.PropertyType p) {
        final Button b = new Button("Image Hint");
        b.setMaxWidth(120);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new ImageHintWindow(p);
            }
        });
        return b;
    }

    private Button createListenerHint(final ComponentSettings.PropertyType p) {
        final Button b = new Button("Listener Hint");
        b.setMaxWidth(120);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new ListenerHintWindow(p);
            }
        });
        return b;
    }

    private void addValidation(final TextField tf, String type) {
        if (type.equals("int")) {
            tf.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    String character = event.getCharacter();
                    try {
                        Integer.parseInt(character);
                    } catch (Exception e) {
                        event.consume();
                    }
                }
            });
        } else if (type.equals("name")) {
            tf.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (tf.getText().length() == 0) {
                        if (!event.getCharacter().matches("[a-z]")) {
                            event.consume();
                        }
                    } else {
                        if (!event.getCharacter().matches("\\w")) {
                            event.consume();
                        }
                    }
                }
            });
        }
        else {} 
    }
}
