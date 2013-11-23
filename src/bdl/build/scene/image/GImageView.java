package bdl.build.scene.image;

import bdl.build.GObject;
import bdl.build.GType;
import javafx.scene.image.ImageView;

public class GImageView extends ImageView implements GObject {

    private String fieldName;

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public GType getType() {
        return GType.ImageView;
    }
}
