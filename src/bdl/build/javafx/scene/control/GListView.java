package bdl.build.javafx.scene.control;

import bdl.build.GObject;
import bdl.build.GType;
import javafx.scene.control.ListView;

public class GListView extends ListView implements GObject {
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
        return GType.ListView;
    }
}
