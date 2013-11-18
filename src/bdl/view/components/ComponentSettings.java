package bdl.view.components;

import java.util.ArrayList;
import java.util.Collection;

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
    private Collection<Properties> properties = new ArrayList<>();
    private Collection<Layout> layout = new ArrayList<>();
    private Collection<Listeners> listeners = new ArrayList<>();

    /**
     * Returns a collection of Properties properties associated with the this
     * component.
     * 
     * @return A collection of Properties objects associated with this component
     */
    public Collection<Properties> getProperties() {
        return properties;
    }

    /**
     * Returns a collection of Layout properties associated with the this
     * component.
     * 
     * @return A collection of Layout objects associated with this component
     */
    public Collection<Layout> getLayout() {
        return layout;
    }

    /**
     * Returns a collection of Listeners properties associated with the this
     * component.
     * 
     * @return A collection of Listener objects associated with this component
     */
    public Collection<Listeners> getListeners() {
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
     */
    public class Properties {

        private final String name;
        private final String type;
        private final boolean value;

        public Properties(String name, String type, String value) {
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
     * Simple Layout object to store name, type, value.
     */
    public class Layout {

        private final String name;
        private final String type;
        private final boolean value;

        public Layout(String name, String type, String value) {
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
     * Simple Listeners object to store name, type, value.
     */
    public class Listeners {

        private final String name;
        private final String type;
        private final boolean value;

        public Listeners(String name, String type, String value) {
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
}