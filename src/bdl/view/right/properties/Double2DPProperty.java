package bdl.view.right.properties;

import bdl.build.GObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Double2DPProperty implements PanelProperty {

    private GObject gObj;
    private String setter;
    private String getter;
    private String fxml;
    private TextField textField;
    private DecimalFormat format = new DecimalFormat("#.##");

    public Double2DPProperty(final GObject gObj, String name, final String getter, final String setter, String fxml, String defaultValue, GridPane gp, int row, Node settingsNode) {
        this.gObj = gObj;
        this.setter = setter;
        this.getter = getter;
        this.fxml = fxml;

        gp.add(new Label(name + ":"), 0, row);
        textField = new TextField();

        //Grab value from settingsNode if given
        if (settingsNode != null) {
            try {
                Method method = settingsNode.getClass().getMethod(getter);
                String value = (String) method.invoke(settingsNode);
                if (value != null) {
                    defaultValue = value;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        textField.setText(format.format(Double.parseDouble(defaultValue)));//TODO - Handle bad defaultValue values

        setValue();

        gp.add(textField, 1, row);

        //Upon losing focus, save to the GObject
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                if (!aBoolean2) {
                    setValue();
                }
            }
        });
        
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    setValue();
                }
            }
        });
    }

    private void setValue() {
        try {
            double dValue = (double)((int)(0.5+(Double.parseDouble(textField.getText()) * 100)))/100;
            textField.setText(format.format(dValue));

            Method method = gObj.getClass().getMethod(setter, double.class);
            method.invoke(gObj, dValue);
        } catch (Exception e) {
            //Reset
            Method method;
            try {
                method = gObj.getClass().getMethod(getter);
                textField.setText(format.format((Double)method.invoke(gObj)));
            } catch (Exception ee) {
                //Can never happen - the fact we get here verifies this method works because we used it earlier
                e.printStackTrace();
            }
            //TODO show error message
        }
    }

    @Override
    public String getJavaCode() {
        return gObj.getFieldName() + "." + setter + "(" + textField.getText() + ");";
    }

    @Override
    public String getFXMLCode() {
        return fxml + "=\"" + textField.getText() + "\"";
    }

}
