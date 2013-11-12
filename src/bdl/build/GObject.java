package bdl.build;

import javafx.scene.image.Image;

public abstract class GObject {

    private String fieldName;

    public GObject(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     *
     * @return The name of this field in the java file
     */
    public String getFieldName(){
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}
