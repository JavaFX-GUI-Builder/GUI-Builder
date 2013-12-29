package bdl.build.javafx.scene.control;

import bdl.build.GObject;
import bdl.model.ComponentSettings;
import javafx.scene.control.TextArea;

public class GTextArea extends TextArea implements GObject {
    private String fieldName;
    private ComponentSettings componentSettings;

    public GTextArea(ComponentSettings componentSettings) {
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
