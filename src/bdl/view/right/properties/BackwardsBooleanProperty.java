package bdl.view.right.properties;

import bdl.build.GObject;
import bdl.model.history.HistoryManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Method;

public class BackwardsBooleanProperty implements PanelProperty {

    private GObject gObj;
    private String setter;
    private String fxml;
    private CheckBox checkBox;

    public BackwardsBooleanProperty(final GObject gObj, String name, String getter, final String setter, String fxml, String defaultValue, GridPane gp, int row, Node settingsNode, HistoryManager hm) {
        this.gObj = gObj;
        this.setter = setter;
        this.fxml = fxml;

        gp.add(new Label(name + ":"), 0, row);
        checkBox = new CheckBox();

        //Grab value from settingsNode if given
        if (settingsNode != null) {
            try {
                Method method = settingsNode.getClass().getMethod(getter);
                String value = Boolean.toString(!((Boolean)method.invoke(settingsNode)));
                if (value != null) {
                    defaultValue = value;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
        return gObj.getFieldName() + "." + setter + "(" + !checkBox.isSelected() + ");";
    }

    @Override
    public String getFXMLCode() {
        return fxml + "=\"" + !checkBox.isSelected() + "\"";
    }
}
