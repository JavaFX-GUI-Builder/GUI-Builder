package bdl.build.javafx.scene.image;

import bdl.build.GObject;
import bdl.model.ComponentSettings;
import javafx.scene.image.ImageView;

public class GImageView extends ImageView implements GObject {
    private String fieldName;
    private ComponentSettings componentSettings;

    public GImageView(ComponentSettings componentSettings) {
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
