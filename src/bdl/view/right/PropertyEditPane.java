package bdl.view.right;

import bdl.build.GObject;
import bdl.build.scene.control.GButton;
import bdl.build.scene.control.GTextArea;
import java.io.File;

import bdl.view.components.ComponentSettings;
import bdl.view.components.ComponentViewReader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

/**
 *
 * @author Ben Goodwin
 */
public class PropertyEditPane extends Pane {

    private GridPane grid = new GridPane();

    public PropertyEditPane() {
        updateContents(new GTextArea(null));
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
    public void updateContents(GObject gObj) {
        grid = new GridPane();
        ComponentSettings cs = ComponentViewReader.getSettingsByName(gObj.getType().toString());
        setContents(cs);
    }

    public void fillContents(GObject gObj) {
        //Most generic to most specific
        gObj.getFieldName();

        if (gObj instanceof Node) {
            //Catches all, but uses instanceof just to fit in with the rest...
            Node nObj = (Node) gObj;
            nObj.getLayoutX();
            nObj.getLayoutY();
        }
        if (gObj instanceof Control) {
            //Catches Labeled (all of those below), TextArea, TextField, ToolBar, ComboBox, ColorPicker, etc
            Control cObj = (Control) gObj;
            cObj.getHeight();
            cObj.getWidth();
            cObj.getMaxHeight();
            cObj.getMaxWidth();
            cObj.getMinHeight();
            cObj.getMinWidth();
        }
        if (gObj instanceof Labeled) {
            //Catches TitledPane, Label, Button, CheckBox, Hyperlink, MenuButton, ToggleButton
            Labeled lObj = (Labeled) gObj;
            lObj.getText();
            lObj.isUnderline();
            lObj.getFont();
            lObj.getTextFill();
            lObj.getAlignment();
        }

        //Then go to the most specific, to catch the single-type only values
        if (gObj instanceof CheckBox) {
            CheckBox cObj = (CheckBox) gObj;
            cObj.isSelected();
        }
    }

//    public void fillContents(GObject gObj) {
//        switch(gObj.getType()) {
//            case Button: 
//                Button b = (Button) gObj;
//                break;
//            case Menu: 
//                Menu m = (Menu) gObj;
//                break;
//            case MenuBar: 
//                MenuBar mb = (MenuBar) gObj;
//                break;
//            case MenuItem: 
//                MenuItem mi = (MenuItem) gObj;
//                break;
//            case ToolBar: 
//                ToolBar tb = (ToolBar) gObj;
//                break;
//            case Image: 
//                Image i = (Image) gObj;
//                break;
//            case ImageView: 
//                ImageView iv = (ImageView) gObj;
//                break;
//            case AnchorPane: 
//                AnchorPane ap = (AnchorPane) gObj;
//                break;
//            case CheckBox: 
//                CheckBox cb = (CheckBox) gObj;
//                break;
//            case ComboBox: 
//                ComboBox cob = (ComboBox) gObj;
//                break;
//            case Label: 
//                Label l = (Label) gObj;
//                break;
//            case ListView: 
//                ListView lv = (ListView) gObj;
//                break;
//            case TextArea: 
//                TextArea ta = (TextArea) gObj;
//                break;
//            case TextField: 
//                TextField tf = (TextField) gObj;
//                break;
//            case SplitPane: 
//                SplitPane sp = (SplitPane) gObj;
//                break;
//            case ScrollPane: 
//                ScrollPane scp = (ScrollPane) gObj;
//                break;
//            default: 
//        }
//    }
    private void createRow(final ComponentSettings.PropertyType p, int row) {
        Label l = new Label(p.getName());
        GridPane.setHalignment(l, HPos.RIGHT);
        GridPane.setMargin(l, new Insets(0, 5, 0, 0));
        grid.add(l, 0, row);
        if (p.getType().equals("boolean")) {
            grid.add(createCheckBox(p), 1, row);
        } else if (p.getType().equals("File")) {
            grid.add(createImageHint(p), 1, row);
        } else if (p.getType().equals("Color")) {
            grid.add(createColorPicker(p), 1, row);
        } else if (p.getType().equals("String") || p.getType().equals("int") || p.getType().equals("float") || p.getType().equals("double")) {
            grid.add(createTextField(p), 1, row);
        } else {
            grid.add(createListenerHint(p), 1, row);
        }
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

    private TextField createTextField(final ComponentSettings.PropertyType p) {
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

    private Button createFileChooser(final ComponentSettings.PropertyType p) {
        final Button b = new Button("Browse...");
        b.setMaxWidth(120);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                FileChooser fc = new FileChooser();
                File f = fc.showOpenDialog(b.getScene().getWindow());
                if (f != null) {
                    b.setText(f.getName());
                    System.out.println("Trigger: " + f.getAbsolutePath());
                }
            }
        });
        return b;
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
                    if(tf.getText().length() == 0) {
                        if(!event.getCharacter().matches("[a-z]")) {
                            event.consume();
                        }
                    }
                    else {
                        if(!event.getCharacter().matches("\\w")) {
                            event.consume();
                    }
                }
                }
            });
        }
    }
}
