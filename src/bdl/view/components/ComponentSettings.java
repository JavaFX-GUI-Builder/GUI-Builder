package bdl.view.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Component Settings stores editable values for components. Stores the settings
 * and properties, layout and listener options for each component that will be
 * used by the property editing panes to list the options available to edit for
 * each component. Will be initialised after the associated xml file has been
 * read by ComponenttViewReader.
 *
 * @author Ben Goodwin
 */
public class ComponentSettings {

    private String name;
    private List<Properties> properties = new ArrayList<>();
    private List<Layout> layout = new ArrayList<>();
    private List<Listeners> listeners = new ArrayList<>();

    /**
     * Returns a collection of Properties properties associated with the this
     * component.
     * 
     * @return A collection of Properties objects associated with this component
     */
    public List<Properties> getProperties() {
        return properties;
    }

    /**
     * Returns a collection of Layout properties associated with the this
     * component.
     * 
     * @return A collection of Layout objects associated with this component
     */
    public List<Layout> getLayout() {
        return layout;
    }

    /**
     * Returns a collection of Listeners properties associated with the this
     * component.
     * 
     * @return A collection of Listener objects associated with this component
     */
    public List<Listeners> getListeners() {
        return listeners;
    }

    /**
     * Returns the name of the component portrayed by this ComponentSettings
     * 
     * @return The name of the component portrayed by this ComponentSettings
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the component to be represented
     * 
     * @param nodeValue The Component name to be represented
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds the given Properties into a new Properties object, then adds it to
     * the collection
     *
     * @param name Name of the property read from XML
     * @param type Type of the property read from XML
     * @param value Value (true or false) of the property read from XML
     */
    public void addProperties(String name, String type, String value) {
        properties.add(new Properties(name, type, value));
    }

    /**
     * Adds the given Properties into a new Layout object, then adds it to the
     * collection
     *
     * @param name Name of the property read from XML
     * @param type Type of the property read from XML
     * @param value Value (true or false) of the property read from XML
     */
    public void addLayout(String name, String type, String value) {
        layout.add(new Layout(name, type, value));
    }

    /**
     * Adds the given properties into a new Listener object, then adds it to the
     * collection
     *
     * @param name Name of the property read from XML
     * @param type Type of the property read from XML
     * @param value Value (true or false) of the property read from XML
     */
    public void addListeners(String name, String type, String value) {
        listeners.add(new Listeners(name, type, value));
    }
    
    /**
     * Simple Properties object to store name, type, value.
     * To be extended, not used, for code simplicity.
     */
    public class PropertyType {
        private final String name;
        private final String type;
        private final boolean value;

        public PropertyType(String name, String type, String value) {
            this.name = name;
            this.type = type;
            this.value = Boolean.parseBoolean(value);
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public boolean getValue() {
            return value;
        }
    }

    /**
     * Simple Properties object to store name, type, value.
     */
    public class Properties extends ComponentSettings.PropertyType {
        public Properties(String name, String type, String value) {
            super(name, type, value);
        }
    }

    /**
     * Simple Layout object to store name, type, value.
     */
    public class Layout extends ComponentSettings.PropertyType {
        public Layout(String name, String type, String value) {
            super(name, type, value);
        }
    }

    /**
     * Simple Listeners object to store name, type, value.
     */
    public class Listeners extends ComponentSettings.PropertyType {
        public Listeners(String name, String type, String value) {
            super(name, type, value);
        }
    }
}