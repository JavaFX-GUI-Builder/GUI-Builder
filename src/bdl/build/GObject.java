package bdl.build;

import bdl.model.ComponentSettings;

public interface GObject {

    /**
     * @return The name of this field in the java file
     */
    public String getFieldName();

    public void setFieldName(String fieldName);

    public ComponentSettings getComponentSettings();


}
