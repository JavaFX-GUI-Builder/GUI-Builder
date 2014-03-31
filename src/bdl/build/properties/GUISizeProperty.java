package bdl.build.properties;

import bdl.build.GUIObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;

public class GUISizeProperty implements PanelProperty {

    private TextField width;
    private TextField height;
    private DecimalFormat format = new DecimalFormat("#.##");

    public GUISizeProperty(final GUIObject gObj, String name, GridPane gp, int row) {
        int row1 = row;
        int row2 = row + 1;

        Label lwidth = new Label("Width:");
        Label lheight = new Label("Height:");
        
        gp.add(lwidth, 0, row1);
        gp.add(lheight, 0, row2);
        width = new TextField();
        height = new TextField();

        width.setText(format.format(gObj.getGUIWidth()));
        height.setText(format.format(gObj.getGUIHeight()));

        gp.add(width, 1, row1);
        gp.add(height, 1, row2);

        //Upon losing focus, save to the GObject
        width.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                if (!aBoolean2) {
                    try {
                        double value = Double.parseDouble(width.getText());
                        gObj.setGUIWidth(value);
                    } catch (Exception e) {
                        //Reset value
                        width.setText(format.format(gObj.getGUIWidth()));
                    }
                }
            }
        });
        height.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                if (!aBoolean2) {
                    try {
                        double value = Double.parseDouble(height.getText());
                        gObj.setGUIHeight(value);
                    } catch (Exception e) {
                        //Reset value
                        height.setText(format.format(gObj.getGUIHeight()));
                    }
                }
            }
        });
        
        width.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        double value = Double.parseDouble(width.getText());
                        gObj.setGUIWidth(value);
                    } catch (Exception e) {
                        //Reset value
                        width.setText(format.format(gObj.getGUIWidth()));
                    }
                }
            }
        });
        
        height.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        double value = Double.parseDouble(height.getText());
                        gObj.setGUIHeight(value);
                    } catch (Exception e) {
                        //Reset value
                        height.setText(format.format(gObj.getGUIHeight()));
                    }
                }
            }
        });
    }

    @Override
    public String getJavaCode() {
        return ""; //TODO
    }

    @Override
    public String getFXMLCode() {
        return ""; //TODO 
    }
}
