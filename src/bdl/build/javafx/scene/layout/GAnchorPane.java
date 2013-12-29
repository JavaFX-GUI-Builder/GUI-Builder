package bdl.build.javafx.scene.layout;

import bdl.build.GObject;
import bdl.model.ComponentSettings;
import javafx.scene.layout.AnchorPane;

public class GAnchorPane extends AnchorPane implements GObject {
    private String fieldName;
    private ComponentSettings componentSettings;

    public GAnchorPane(ComponentSettings componentSettings) {
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
