package bdl.build;

public class GField extends GObject {

    private Class type;

    public GField(String fieldName, Class type) {
        super(fieldName);
        this.type = type;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
}
