package bdl.build.javafx.scene.control;

import bdl.build.GObject;
import javafx.scene.control.TextArea;

public class GTextArea extends TextArea implements GObject {
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
