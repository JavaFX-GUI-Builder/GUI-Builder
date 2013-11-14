package bdl.build;

public abstract class GObject {

    private String fieldName;
    private String initCode;

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

    public String getInitCode() {
        return initCode;
    }

    public void setInitCode(String initCode) {
        this.initCode = initCode;
    }
}
