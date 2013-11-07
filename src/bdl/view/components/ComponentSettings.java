package bdl.view.components;

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
class ComponentSettings {

    private String name;
    private Collection<Properties> properties;
    private Collection<Layout> layout;
    private Collection<Listeners> listeners;

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
