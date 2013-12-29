package bdl.build.javafx.scene.control;

import bdl.build.GObject;
import bdl.model.ComponentSettings;
import javafx.scene.control.Label;

public class GLabel extends Label implements GObject {
    private String fieldName;
    private ComponentSettings componentSettings;

    public GLabel(ComponentSettings componentSettings) {
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
