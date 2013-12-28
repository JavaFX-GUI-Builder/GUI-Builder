package bdl.model;

import org.w3c.dom.*;

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
public class ComponentSettings {

    private Collection<Component> allComponents;

    public ComponentSettings(String path) throws Exception {
        allComponents = new ArrayList<>();
        parseComponentSettings(path);
    }

    /**
     * Takes a name of a component and returns the associated Component
     * object for it.
     *
     * @param componentName The name of the component to look up
     * @return The Component file associated with that component or null if no component exists with the provided name
     */
    public Component getComponent(String componentName) {
        for (Component component : allComponents) {
            if (component.getType().equals(componentName)) {
                return component;
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
    public Collection<Component> getComponents() {
        return allComponents;
    }

    /**
     * Returns all the names of components currently supported
     *
     * @return A collection of Strings of names of components supported
     */
    public Collection<String> getComponentNames() {
        ArrayList<String> al = new ArrayList<>();
        for (Component cs : allComponents) {
            al.add(cs.getType());
        }
        return al;
    }

    /**
     * Reads in the xml properties file located at
     * bdl.view.components.component-options.xml and parses the file creating 
     * a list of Component with all properties initialised.
     */
    private void parseComponentSettings(String path) throws Exception {
        File settings = new File(path);
        if (!settings.exists()) {
            throw new RuntimeException("File " + path + " does not exist, could not load ComponentSettings.");
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document d = db.parse(settings);

        Element root = d.getDocumentElement();
        root.normalize();

        NodeList components = root.getElementsByTagName("component");

        for (int i = 0; i < components.getLength(); i++) {
            Component component = new Component();
            boolean enabled = parseComponent(component, (Element)components.item(i));
            if (enabled) allComponents.add(component);
        }
    }

    private boolean parseComponent(Component component, Element element) {
        if (!element.getElementsByTagName("enabled").item(0).getTextContent().equals("true")) {
            return false;
        }

        component.setType(element.getElementsByTagName("type").item(0).getTextContent());
        component.setPackageName(element.getElementsByTagName("package").item(0).getTextContent());
        component.setLayoutType(element.getElementsByTagName("layouttype").item(0).getTextContent());
        component.setIcon(element.getElementsByTagName("icon").item(0).getTextContent());

        Element propertiesElement = (Element)element.getElementsByTagName("properties").item(0);
        parseProperties(component, propertiesElement);

        return true;
    }

    private void parseProperties(Component component, Element propertiesElement) {
        NodeList properties = propertiesElement.getElementsByTagName("property");

        for (int i = 0; i < properties.getLength(); i++) {
            Element property = (Element)properties.item(i);

            String name = property.getElementsByTagName("name").item(0).getTextContent();
            String type = property.getElementsByTagName("enabled").item(0).getTextContent();
            String enabled = property.getElementsByTagName("pseudotype").item(0).getTextContent();
            String defaultValue = property.getElementsByTagName("default").item(0).getTextContent();
            String getter = property.getElementsByTagName("getter").item(0).getTextContent();
            String setter = property.getElementsByTagName("setter").item(0).getTextContent();

            component.addProperty(name, type, enabled, defaultValue, getter, setter);
        }
    }
}
