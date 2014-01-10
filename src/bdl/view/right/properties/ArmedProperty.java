package bdl.view.right.properties;

import bdl.build.GObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ArmedProperty implements PanelProperty {

    private GObject gObj;
    private CheckBox checkBox;
    private ButtonBase buttonBase;

    public ArmedProperty (final GObject gObj, String name, String getter, String setter, String defaultValue, GridPane gp, int row) {
        this.gObj = gObj;

        gp.add(new Label(name + ":"), 0, row);
        checkBox = new CheckBox();

        try {
            buttonBase = (ButtonBase) gObj;
        } catch (Exception e) {
            //May not extend buttonbase
            e.printStackTrace();
            return;//TODO: Probably need some better behavior here.
        }

        checkBox.setSelected(Boolean.parseBoolean(defaultValue));//TODO - Handle bad defaultValue values
        if (!checkBox.isSelected()) ((ButtonBase)gObj).disarm();

        gp.add(checkBox, 1, row);

        //Upon change, save to the GObject
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                if (aBoolean2) {
                    System.out.println("XXX");
                    buttonBase.arm();
                } else {
                    System.out.println("YYY");
                    buttonBase.disarm();
                }
            }
        });
    }

    @Override
    public String getJavaCode() {
        //Code only needs to be added if the ButtonBase is disarmed.
        if (buttonBase.isArmed()) {
            return "";
        } else {
            return gObj.getFieldName() + ".disarm();\n";
        }
    }
}
