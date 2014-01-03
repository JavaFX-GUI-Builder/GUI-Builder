package bdl.build.javafx.scene.shape;

import bdl.build.GObject;
import javafx.scene.shape.Circle;

public class GCircle extends Circle implements GObject {
    private String fieldName;

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
