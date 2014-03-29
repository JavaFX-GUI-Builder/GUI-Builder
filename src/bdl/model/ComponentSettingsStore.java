package bdl.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class will parse the xml file that provides the configuration for
 * every component that the GUI will support, and provides static methods to
 * return all or individual settings per component

 */
public class ComponentSettingsStore {

    private Collection<ComponentSettings> allComponentSettings;

    public ComponentSettingsStore(String path) throws Exception {
        allComponentSettings = new ArrayList<>();
        parseComponentSettings(path);
    }

    /**
     * Takes a name of a component and returns the associated Component
     * object for it.
     *
     * @param componentName The name of the component to look up
     * @return The Component file associated with that component or null if no component exists with the provided name
     */
    public ComponentSettings getComponent(String componentName) {
        for (ComponentSettings componentSettings : allComponentSettings) {
            if (componentSettings.getType().equals(componentName)) {
                return componentSettings;
            }
        }
        return null;
    }

    /**
     * Returns all the components currently supported by the GUI as a collection
     * of Component objects
     *
     * @return A collection of every component in it's Component state
     */
    public Collection<ComponentSettings> getComponents() {
        return allComponentSettings;
    }

    /**
     * Returns all the names of components currently supported
     *
     * @return A collection of Strings of names of components supported
     */
    public Collection<String> getComponentNames() {
        ArrayList<String> al = new ArrayList<>();
        for (ComponentSettings cs : allComponentSettings) {
            al.add(cs.getType());
        }
        return al;
    }

    /**
     * Reads in the xml properties file located at
     * bdl.model.component-settings.xml and parses the file creating
     * a list of Component with all properties initialised.
     */
    private void parseComponentSettings(String path) throws Exception {
//        File settings = new File(path);
//        if (!settings.exists()) {
//            throw new RuntimeException("File " + path + " does not exist, could not load ComponentSettings.");
//        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document d = db.parse(getClass().getResourceAsStream(path));

        Element root = d.getDocumentElement();
        root.normalize();

        NodeList components = root.getElementsByTagName("component");

        for (int i = 0; i < components.getLength(); i++) {
            ComponentSettings componentSettings = new ComponentSettings();
            boolean enabled = parseComponent(componentSettings, (Element)components.item(i));
            if (enabled) allComponentSettings.add(componentSettings);
        }
    }

    private boolean parseComponent(ComponentSettings componentSettings, Element element) {
        if (!element.getElementsByTagName("enabled").item(0).getTextContent().equals("true")) {
            return false;
        }

        componentSettings.setType(element.getElementsByTagName("type").item(0).getTextContent());
        componentSettings.setPackageName(element.getElementsByTagName("package").item(0).getTextContent());
        componentSettings.setLayoutType(element.getElementsByTagName("layouttype").item(0).getTextContent());
        componentSettings.setIcon(element.getElementsByTagName("icon").item(0).getTextContent());

        Element propertiesElement = (Element)element.getElementsByTagName("properties").item(0);
        parseProperties(componentSettings, propertiesElement);
        
        Element listenerElement = (Element)element.getElementsByTagName("listeners").item(0);
        parseListeners(componentSettings, listenerElement);

        return true;
    }

    private void parseProperties(ComponentSettings componentSettings, Element propertiesElement) {
        NodeList properties = propertiesElement.getElementsByTagName("property");

        for (int i = 0; i < properties.getLength(); i++) {
            Element property = (Element)properties.item(i);

            String name = property.getElementsByTagName("name").item(0).getTextContent();
            String type = property.getElementsByTagName("enabled").item(0).getTextContent();
            String enabled = property.getElementsByTagName("pseudotype").item(0).getTextContent();
            String defaultValue = property.getElementsByTagName("default").item(0).getTextContent();
            String getter = property.getElementsByTagName("getter").item(0).getTextContent();
            String setter = property.getElementsByTagName("setter").item(0).getTextContent();
            String fxml = property.getElementsByTagName("fxml").item(0).getTextContent();

            componentSettings.addProperty(name, type, enabled, defaultValue, getter, setter, fxml);
        }
    }
    
    private void parseListeners(ComponentSettings componentSettings, Element listenerElement) {
        NodeList listeners = listenerElement.getElementsByTagName("listener");

        for (int i = 0; i < listeners.getLength(); i++) {
            Element listener = (Element)listeners.item(i);

            String name = listener.getElementsByTagName("name").item(0).getTextContent();
            String method = listener.getElementsByTagName("method").item(0).getTextContent();
            String event = listener.getElementsByTagName("event").item(0).getTextContent();

            componentSettings.addListenerHint(name, method, event);
        }
    }
}
