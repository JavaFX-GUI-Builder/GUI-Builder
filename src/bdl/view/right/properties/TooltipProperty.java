package bdl.view.right.properties;

import bdl.build.GObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

public class TooltipProperty extends GridPane implements PanelProperty {

    private GObject gObj;
    private TextField textField;
    private Control control;

    public TooltipProperty (final GObject gObj, String name, String getter, String setter) {
        this.gObj = gObj;

        add(new Label(name + ":"), 0, 0);
        textField = new TextField();

        try {
            control = (Control) gObj;
        } catch (Exception e) {
            //May not extend Control
            e.printStackTrace();
            return;//TODO: Probably need some better behavior here.
        }

        Tooltip tooltip = control.getTooltip();
        String tooltipText = "";
        if (tooltip != null) tooltipText = tooltip.getText();
        textField.setText(tooltipText);

        add(textField, 1, 0);

        //Upon losing focus, save to the GObject
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                if (!aBoolean2) {
                    control.setTooltip(new Tooltip(textField.getText()));
                }
            }
        });
    }

    @Override
    public String getJavaCode() {
        Tooltip tooltip = control.getTooltip();
        String tooltipText = "";
        if (tooltip != null) {
            tooltipText = tooltip.getText();
            return gObj.getFieldName() + ".setTooltip(new Tooltip(" + tooltipText + "));\n";
        } else {
            return "";
        }
    }
}
