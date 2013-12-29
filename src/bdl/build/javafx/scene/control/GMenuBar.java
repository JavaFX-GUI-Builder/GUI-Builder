package bdl.build.javafx.scene.control;

import bdl.build.GObject;
import bdl.model.ComponentSettings;
import javafx.scene.control.MenuBar;

public class GMenuBar extends MenuBar implements GObject {
    private String fieldName;
    private ComponentSettings componentSettings;

    public GMenuBar(ComponentSettings componentSettings) {
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
