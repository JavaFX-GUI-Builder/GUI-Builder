package bdl.build.scene.layout;

import bdl.build.GObject;
import bdl.build.GType;
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

    @Override
    public GType getType() {
        return GType.AnchorPane;
    }
}
