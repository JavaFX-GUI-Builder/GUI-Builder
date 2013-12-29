package bdl.view.right;

import bdl.build.GObject;
import bdl.model.ComponentSettings;
import bdl.model.ComponentSettingsStore;
import bdl.model.Property;
import bdl.view.right.properties.FieldName;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.lang.reflect.Constructor;

public class PropertyEditPane extends GridPane {

    private ComponentSettingsStore componentSettingsStore;

    public PropertyEditPane(ComponentSettingsStore componentSettingsStore) {
        //For reference, old properties panel: http://i.imgur.com/UBb7P4k.png
        this.componentSettingsStore = componentSettingsStore;
        add(new Label("No component selected."), 0, 0);
    }

    public void showProperties(GObject gObj) {
        int currentRow = 0;
        this.getChildren().clear();

        ComponentSettings componentSettings = gObj.getComponentSettings();

        Label propertiesHeading = new Label("Properties:");
        propertiesHeading.setFont(Font.font(propertiesHeading.getFont().getFamily(), FontWeight.BOLD, propertiesHeading.getFont().getSize() + 0.5));
        add(propertiesHeading, 0, currentRow++);
        add(new FieldName(gObj), 0, currentRow++);

        for (Property property : componentSettings.getProperties()) {
            String type = property.getType();
            try {
                Class panelPropertyClass = Class.forName("bdl.view.right.properties." + type + "Property");
                Constructor constructor = panelPropertyClass.getConstructor(GObject.class, String.class, String.class, String.class);
                Node node = (Node)constructor.newInstance(gObj, property.getName(), property.getGetter(), property.getSetter());
                add(node, 0, currentRow++);
            } catch (Exception e) {
                System.out.println(type + "Property failed.");
                e.printStackTrace();
            }
        }


//        add(new StringProperty(gObj, "Text", "getText", "setText"), 0, currentRow++);
//        add(new BackwardsBooleanProperty(gObj, "Enabled", "isDisabled", "setDisable"), 0, currentRow++);

    }

//    private void setContents(Component cs, GObject gObj) {
//        int row = 0;
//        int i = 0;
//        Label hl = new Label("Properties");
//        hl.setFont(Font.font(hl.getFont().getFamily(), FontWeight.BOLD, hl.getFont().getSize() + 0.5));
//        grid.add(hl, 0, row, 2, 1);
//        row++;
//        ArrayList<Component.Properties> alp = (ArrayList<Component.Properties>) cs.getProperties();
//        for (int j = 0; j < alp.size(); j++) {
//
//        }
//
//        Label jl = new Label("Listeners");
//        jl.setFont(Font.font(jl.getFont().getFamily(), FontWeight.BOLD, jl.getFont().getSize() + 0.5));
//        grid.add(jl, 0, row);
//        row++;
//        ArrayList<Component.Listeners> alli = (ArrayList<Component.Listeners>) cs.getListeners();
//        for (int j = 0; j < alli.size(); j++) {
//            if (list[i] instanceof Button && alli.get(j).getValue()) {
//                if (alli.get(j).getType().equals("listeners")) {
//                    list[i] = createListenerHint(alli.get(j));
//                }
//                createRow(alli.get(j).getName(), list[i], row);
//            }
//            row++;
//        }
//    }
//
//    private void createRow(String p, Node node, int row) {
//        Label l = new Label(p);
//        GridPane.setHalignment(l, HPos.RIGHT);
//        GridPane.setMargin(l, new Insets(0, 5, 0, 0));
//        grid.add(l, 0, row);
//        grid.add(node, 1, row);
//    }
//
//    private CheckBox createCheckBox(final Component.PropertyType p) {
//        final CheckBox b = new CheckBox();
//        b.setMaxWidth(120);
//        b.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent t) {
//                System.out.println("Trigger: " + p.getName() + " " + p.getType() + " - " + b.isSelected());
//            }
//        });
//
//        return b;
//    }
//
//    private TextField createTextField(final Component.PropertyType p, String value) {
//        final TextField tf = new TextField();
//        tf.promptTextProperty().setValue(p.getType());
//        if (p.getType().equals("int")) {
//            tf.setMaxWidth(50);
//        } else {
//            tf.setMaxWidth(120);
//        }
//        if (p.getName().equals("name")) {
//            addValidation(tf, "name");
//        } else {
//            addValidation(tf, p.getType());
//        }
//
//        tf.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
//                if (t1 == false) {
//                    System.out.println("Trigger: " + p.getName() + " " + p.getType() + " - " + tf.getText());
//                }
//            }
//        });
//        tf.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent t) {
//                System.out.println("Trigger: " + p.getName() + " " + p.getType() + " - " + tf.getText());
//            }
//        });
//        tf.setText(value);
//        System.out.println("3 " + value + " " + p.getName());
//        return tf;
//    }
//
//    private ColorPicker createColorPicker(final Component.PropertyType p) {
//        final ColorPicker cp = new ColorPicker();
//        cp.setMaxWidth(120);
//        cp.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent t) {
//                System.out.println("Trigger: " + p.getName() + " " + p.getType() + " - " + cp.getValue().toString());
//            }
//        });
//        return cp;
//    }
//
//    private Button createImageHint(final Component.PropertyType p) {
//        final Button b = new Button("Image Hint");
//        b.setMaxWidth(120);
//        b.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent t) {
//                new ImageHintWindow(p);
//            }
//        });
//        return b;
//    }
//
//    private Button createListenerHint(final Component.PropertyType p) {
//        final Button b = new Button("Listener Hint");
//        b.setMaxWidth(120);
//        b.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent t) {
//                new ListenerHintWindow(p);
//            }
//        });
//        return b;
//    }
//
//    private void addValidation(final TextField tf, String type) {
//        if (type.equals("int")) {
//            tf.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
//                @Override
//                public void handle(KeyEvent event) {
//                    String character = event.getCharacter();
//                    try {
//                        Integer.parseInt(character);
//                    } catch (Exception e) {
//                        event.consume();
//                    }
//                }
//            });
//        } else if (type.equals("name")) {
//            tf.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
//                @Override
//                public void handle(KeyEvent event) {
//                    if (tf.getText().length() == 0) {
//                        if (!event.getCharacter().matches("[a-z]")) {
//                            event.consume();
//                        }
//                    } else {
//                        if (!event.getCharacter().matches("\\w")) {
//                            event.consume();
//                        }
//                    }
//                }
//            });
//        }
//        else {}
//    }
}
