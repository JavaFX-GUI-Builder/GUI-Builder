package bdl.view.right.properties;

import bdl.build.GObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Method;

public class StringProperty implements PanelProperty {
    
    private GObject gObj;
    private String setter;
    private TextField textField;
    
    public StringProperty(final GObject gObj, String name, String getter, final String setter, String defaultValue, GridPane gp, int row) {
        this.gObj = gObj;
        this.setter = setter;
        
        gp.add(new Label(name + ":"), 0, row);
        textField = new TextField();
        
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
                        textField.setText(StringSanitizer.sanitize(textField.getText()));
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
        return gObj.getFieldName() + "." + setter + "(\"" + textField.getText() + "\");";
    }
}
