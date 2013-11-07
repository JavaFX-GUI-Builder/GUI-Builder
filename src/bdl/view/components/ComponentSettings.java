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

    public Collection<Properties> getProperties() {
        return properties;
    }

    public Collection<Layout> getLayout() {
        return layout;
    }

    public Collection<Listeners> getListeners() {
        return listeners;
    }

    public String getName() {
        return name;
    }

    public void setName(String nodeValue) {
        name = nodeValue;
    }

    public void addProperties(String name, String type, String value) {
        properties.add(new Properties(name, type, value));
    }

    public void addLayout(String name, String type, String value) {
        layout.add(new Layout(name, type, value));
    }

    public void addListeners(String name, String type, String value) {
        listeners.add(new Listeners(name, type, value));
    }

    
    
    public class Properties {

        private final String name;
        private final String type;
        private final String value;

        public Properties(String name, String type, String value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getValue() {
            return value;
        }
    }

    public class Layout {

        private final String name;
        private final String type;
        private final String value;

        public Layout(String name, String type, String value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getValue() {
            return value;
        }
    }

    public class Listeners {

        private final String name;
        private final String type;
        private final String value;

        public Listeners(String name, String type, String value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getValue() {
            return value;
        }
    }
}