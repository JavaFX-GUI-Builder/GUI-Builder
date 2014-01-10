package bdl.build.javafx.scene.control;

import bdl.build.GObject;
import bdl.view.right.properties.PanelProperty;
import javafx.scene.control.Hyperlink;

import java.util.List;

public class GHyperlink extends Hyperlink implements GObject {
    private String fieldName;
    private List<PanelProperty> properties;

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public void setPanelProperties(List<PanelProperty> properties) {
        this.properties = properties;
    }

    @Override
    public List<PanelProperty> getPanelProperties() {
        return properties;
    }
    
    @Override
    public void fire() {
        //Prevents the hyperlink from being fired if you click on it
    }
}
