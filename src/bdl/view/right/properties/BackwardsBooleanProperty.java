package bdl.view.right.properties;

import bdl.build.GObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Method;

public class BackwardsBooleanProperty implements PanelProperty {

    private GObject gObj;
    private String setter;
    private CheckBox checkBox;

    public BackwardsBooleanProperty(final GObject gObj, String name, String getter, final String setter, String defaultValue, GridPane gp, int row) {
        this.gObj = gObj;
        this.setter = setter;

        gp.add(new Label(name + ":"), 0, row);
        checkBox = new CheckBox();

        checkBox.setSelected(Boolean.parseBoolean(defaultValue));//TODO - Handle bad defaultValue values

        try {
            setValue();
        } catch (Exception e) {
            e.printStackTrace();
            return;//TODO: Probably need some better behavior here.
        }

        gp.add(checkBox, 1, row);

        //Upon change, save to the GObject
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                try {
                    setValue();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;//TODO: Probably need some better behavior here.
                }
            }
        });
    }

    private void setValue() throws Exception {
        Method method = gObj.getClass().getMethod(setter, boolean.class);
        method.invoke(gObj, !checkBox.isSelected());
    }

    @Override
    public String getJavaCode() {
        return gObj.getFieldName() + "." + setter + "(" + !checkBox.isSelected() + ");\n";
    }


}
