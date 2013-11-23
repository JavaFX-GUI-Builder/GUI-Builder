package bdl.build.scene.image;

import bdl.build.GObject;
import bdl.build.GType;
import javafx.scene.image.Image;

public class GImage extends Image implements GObject {

    private String fieldName;

    public GImage(String s) {
        super(s);
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
    public GType getType() {
        return GType.Image;
    }
}
