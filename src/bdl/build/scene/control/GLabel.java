package bdl.build.scene.control;

import bdl.build.GObject;
import bdl.build.GType;
import javafx.scene.control.Label;

/**
 * Created with IntelliJ IDEA.
 * User: Leon
 * Date: 24/11/13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
public class GLabel extends Label implements GObject {
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
        return GType.Label;
    }
}
