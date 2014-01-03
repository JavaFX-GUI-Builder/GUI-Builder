package bdl.build.javafx.scene.layout;

import bdl.build.GObject;
import javafx.scene.layout.AnchorPane;

public class GAnchorPane extends AnchorPane implements GObject {
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
