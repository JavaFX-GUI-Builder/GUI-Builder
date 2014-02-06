package bdl.build.javafx.scene.shape;

import bdl.build.GObject;
import bdl.view.right.PropertyEditPane;
import bdl.view.right.properties.PanelProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.shape.Circle;

import java.util.List;

public class GCircle extends Circle implements GObject {
    private String fieldName;
    private List<PanelProperty> properties;
    private PropertyEditPane pep;
    private StringProperty fieldNameProperty = new SimpleStringProperty();

    @Override
    public String getFieldName() {
        return fieldNameProperty.getValue();
    }

    @Override
    public void setFieldName(String fieldName) {
        fieldNameProperty.setValue(fieldName);
    }

    @Override
    public StringProperty fieldNameProperty() {
        return fieldNameProperty;
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
    public void setPEP(PropertyEditPane propertyEditPane) {
        pep = propertyEditPane;
    }
    
    @Override
    public PropertyEditPane getPEP() {
        return pep;
    }
}
