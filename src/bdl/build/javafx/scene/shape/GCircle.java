package bdl.build.javafx.scene.shape;

import bdl.build.GObject;
import bdl.model.ComponentSettings;
import javafx.scene.shape.Circle;

public class GCircle extends Circle implements GObject {
    private String fieldName;
    private ComponentSettings componentSettings;

    public GCircle(ComponentSettings componentSettings) {
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
