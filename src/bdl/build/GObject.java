package bdl.build;

import bdl.view.right.properties.PanelProperty;

import java.util.List;

public interface GObject {

    /**
     * @return The name of this field in the java file
     */
    public String getFieldName();

    public void setFieldName(String fieldName);

    public void setPanelProperties(List<PanelProperty> properties);

    public List<PanelProperty> getPanelProperties();

    @Override
    public String toString();
}
