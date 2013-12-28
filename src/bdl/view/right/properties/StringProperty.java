package bdl.view.right.properties;

import bdl.build.GObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Method;

public class StringProperty extends GridPane implements PanelProperty {

    private GObject gObj;
    private String setter;
    private TextField textField;

    public StringProperty(final GObject gObj, String name, String getter, final String setter) {
        this.gObj = gObj;
        this.setter = setter;

        add(new Label(name + ":"), 0, 0);
        textField = new TextField();

        Method method;
        try {
            method = gObj.getClass().getMethod(getter);
            textField.setText((String)method.invoke(gObj));
        } catch (Exception e) {
            e.printStackTrace();
            return;//TODO: Probably need some better behavior here.
        }

        add(textField, 1, 0);

        //Upon losing focus, save to the GObject
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                if (!aBoolean2) {
                    Method method;
                    try {
                        method = gObj.getClass().getMethod(setter, String.class);
                        method.invoke(gObj, textField.getText());
                    } catch (Exception e) {
                        return;//TODO: Probably need some better behavior here.
                    }
                }
            }
        });
    }

    @Override
    public String getJavaCode() {
        return gObj.getFieldName() + "." + setter + "(" + textField.getText() + ");\n";
    }
}
