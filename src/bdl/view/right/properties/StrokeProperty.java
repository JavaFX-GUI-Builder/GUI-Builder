package bdl.view.right.properties;

import bdl.build.GObject;
import bdl.controller.Controller;
import bdl.model.history.HistoryItem;
import bdl.model.history.HistoryManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class StrokeProperty implements PanelProperty {

    private GObject gObj;
    private Shape node;
    private ColorPicker colorPicker;
    private TextField textField;
    private final HistoryManager historyManager;

    public StrokeProperty(final GObject gObj, String name, String getter, final String setter, String fxml, String defaultValue, GridPane gp, int row, Node settingsNode, HistoryManager hm) {
        this.gObj = gObj;
        this.node = (Shape)gObj;
        this.historyManager = hm;

        gp.add(new Label("Stroke:"), 0, row);
        gp.add(new Label("Stroke Width:"), 0, row + 1);

        colorPicker = new ColorPicker();
        textField = new TextField();

        Color defaultStrokeColor = Color.LIMEGREEN;
        double defaultStrokeWidth = 0;

        //Grab value from settingsNode if given
        if (settingsNode != null) {
            Color loadedStrokeColor = (Color)((Shape)settingsNode).getStroke();
            if (loadedStrokeColor != null) {
                defaultStrokeColor = loadedStrokeColor;
                defaultStrokeWidth = ((Shape)settingsNode).getStrokeWidth();// Inside IF otherwise loads as width=1 when no width is set
            }
        }

        colorPicker.setValue(defaultStrokeColor);
        textField.setText("" + defaultStrokeWidth);

        setColor();
        setWidth();

        gp.add(colorPicker, 1, row);
        gp.add(textField, 1, row + 1);

        //On action, save to the GObject
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setColor();
            }
        });
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                if (!aBoolean2) {
                    setWidth();
                }
            }
        });
    }

    private void setColor() {
        if (!Controller.historyPause && !node.getStroke().equals(colorPicker.getValue())) {

            historyManager.addHistory(new HistoryItem() {
                final Color before = (Color) node.getStroke();
                final Color after = colorPicker.getValue();

                @Override
                public void revert() {
                    node.setStroke(before);
                    colorPicker.setValue(before);
                    // work around to update colorpicker's displayed selection
                    colorPicker.fireEvent(new ActionEvent(null, colorPicker));
                }

                @Override
                public void restore() {
                    node.setStroke(after);
                    colorPicker.setValue(after);
                    // work around to update colorpicker's displayed selection
                    colorPicker.fireEvent(new ActionEvent(null, colorPicker));
                }

                @Override
                public String getAppearance() {
                    return gObj.getFieldName() + " stroke changed";
                }
            });
        }
        node.setStroke(colorPicker.getValue());
    }

    private void setWidth() {
        try {
            final double newWidth = Double.parseDouble(textField.getText());

            if (!Controller.historyPause) {
                historyManager.addHistory(new HistoryItem() {
                    double newValue = newWidth;
                    double oldValue = node.getStrokeWidth();

                    @Override
                    public void revert() {
                        node.setStrokeWidth(oldValue);
                        textField.setText("" + oldValue);
                    }

                    @Override
                    public void restore() {
                        node.setStrokeWidth(newValue);
                        textField.setText("" + newValue);
                    }

                    @Override
                    public String getAppearance() {
                        return gObj.getFieldName() + " stroke width changed";
                    }
                });
            }

            node.setStrokeWidth(newWidth);
        } catch (Exception e) {
            //double value didn't parse
            textField.setText("" + node.getStrokeWidth());
        }
    }


    @Override
    public String getJavaCode() {
        if (node.getStrokeWidth() != 0) {
            return gObj.getFieldName() + ".setStroke(Color.web(\"" + colorPicker.getValue().toString() + "\"));\n"
                    + gObj.getFieldName() + ".setStrokeWidth(" + textField.getText() + ");";
        } else {
            return "";
        }
    }

    @Override
    public String getFXMLCode() {
        if (node.getStrokeWidth() != 0) {
            return "stroke=\"" + colorPicker.getValue().toString() + "\" strokeWidth=\"" + textField.getText() + "\"";
        } else {
            return "";
        }
    }
}
