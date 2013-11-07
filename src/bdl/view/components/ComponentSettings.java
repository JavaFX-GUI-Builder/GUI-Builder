package bdl.view.components;

import java.util.Collection;

/**
 * Component Settings stores editable values for components.
 * Stores the settings and properties, layout and listener options for each 
 * component that will be used by the property editing panes to list the options
 * available to edit for each component. Will be initialised after the
 * associated xml file has been read by ComponenttViewReader.
 * 
 * @author Ben Goodwin
 */
class ComponentSettings {
    
    private static String name;
    private static Collection<Properties> properties;
    private static Collection<Layout> layout;
    private static Collection<Listeners> listeners;

    
    public static Collection<Properties> getProperties() {
        return properties;
    }
    
    public static Collection<Layout> getLayout() {
        return layout;
    }
    
    public static Collection<Listeners> getListeners() {
        return listeners;
    }
    
    public static String getName() {
        return name;
    }
    
    
    
    private static class Properties {
        
        public Properties() {
        }
    }

    private static class Layout {

        public Layout() {
        }
    }

    private static class Listeners {

        public Listeners() {
        }
    }
}
