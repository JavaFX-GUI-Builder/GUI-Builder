package bdl.build.javafx.scene.control;

import bdl.build.GObject;
import bdl.model.ComponentSettings;
import javafx.scene.control.ComboBox;

public class GComboBox extends ComboBox implements GObject{
    private String fieldName;
    private ComponentSettings componentSettings;

    public GComboBox(ComponentSettings componentSettings) {
        this.componentSettings = componentSettings;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public ComponentSettings getComponentSettings() {
        return componentSettings;
    }
}
