package bdl.build.scene.control;

import bdl.build.GObject;
import bdl.build.GType;
import javafx.scene.control.ToolBar;

import java.util.ArrayList;
import java.util.List;

public class GToolBar extends ToolBar implements GObject {
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
        return GType.ToolBar;
    }
}
