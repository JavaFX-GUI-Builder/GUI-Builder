package bdl.build.javafx.scene.shape;

import bdl.build.GObject;
import bdl.model.ComponentSettings;
import javafx.scene.shape.Rectangle;

public class GRectangle extends Rectangle implements GObject {
    private String fieldName;
    private ComponentSettings componentSettings;

    public GRectangle(ComponentSettings componentSettings) {
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
