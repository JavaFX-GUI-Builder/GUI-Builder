package bdl.view.right.properties;

import bdl.build.GObject;
import bdl.model.history.HistoryManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Method;

public class StringProperty implements PanelProperty {
    
    private GObject gObj;
    private String setter;
    private String fxml;
    private TextField textField;
    
    public StringProperty(final GObject gObj, String name, String getter, final String setter, String fxml, String defaultValue, GridPane gp, int row, Node settingsNode, HistoryManager hm) {
        this.gObj = gObj;
        this.setter = setter;
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
        
        textField.setText(defaultValue);
        
        try {
            setValue();
        } catch (Exception e) {
            e.printStackTrace();
            return;//TODO: Probably need some better behavior here.
        }
        
        gp.add(textField, 1, row);

        //Upon losing focus, save to the GObject
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                if (!aBoolean2) {
                    try {
                        setValue();
                    } catch (Exception e) {
                        return;//TODO: Probably need some better behavior here.
                    }
                }
            }
        });
    }
    
    private void setValue() throws Exception {
        Method method = gObj.getClass().getMethod(setter, String.class);
        method.invoke(gObj, textField.getText());
    }
    
    @Override
    public String getJavaCode() {
        return gObj.getFieldName() + "." + setter + "(\"" + textField.getText().replace("\\", "\\\\").replace("\"", "\\\"") + "\");";
    }

    @Override
    public String getFXMLCode() {
        return fxml + "=\"" + textField.getText().replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }
}
