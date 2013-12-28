package bdl.model;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class will read in the xml file that provides the configuration for
 * every component that the GUI will support, and provides static methods to
 * return all or individual settings per component
 *
 * @author Ben Goodwin
 */
public class ComponentSettings {

    private Collection<Component> allComponents;

    public ComponentSettings(String path) throws Exception {
        allComponents = new ArrayList<>();
        parseSettings(path);
    }

    /**
     * Takes a name of a component and returns the associated Component
     * object for it.
     *
     * @param componentName The name of the component to look up
     * @return The Component file associated with that component
     */
    public Component getComponent(String componentName) {
        for (Component cs : allComponents) {
            if (cs.getName().equals(componentName)) {
                return cs;
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
            al.add(cs.getName());
        }
        return al;
    }

    /**
     * Reads in the xml properties file located at
     * bdl.view.components.component-options.xml and parses the file creating 
     * a list of Component with all properties initialised.
     */
    private void parseSettings(String path) throws Exception {
        File settings = new File(path);
        if (!settings.exists()) {
            throw new RuntimeException("File " + path + " does not exist, could not load ComponentSettings.");
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document d = db.parse(settings);


        d.getDocumentElement().normalize();
        Node root = d.getDocumentElement();
        NodeList components = d.getDocumentElement().getElementsByTagName("component");
        allComponents = new ArrayList<>();

        for (int i = 0; i < components.getLength(); i++) {
            Component cs = new Component();
            NamedNodeMap nnm = components.item(i).getAttributes();
            cs.setName(nnm.item(0).getNodeValue());

            Element el = (Element) components.item(i);
            NodeList properties = el.getElementsByTagName("properties").item(0).getChildNodes();
            NodeList layout = el.getElementsByTagName("layout").item(0).getChildNodes();
            NodeList listeners = el.getElementsByTagName("listeners").item(0).getChildNodes();

            for (int j = 0; j < properties.getLength(); j++) {
                if (properties.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Node n = properties.item(j);
                    String name, type, value;
                    name = n.getNodeName();
                    type = n.getAttributes().item(0).getNodeValue();
                    value = n.getTextContent();
                    if (name.equals("other")) {
                        cs.addProperties(value, type, "true");
                    } else {
                        cs.addProperties(name, type, value);
                    }
                }
            }

            for (int j = 0; j < layout.getLength(); j++) {
                if (layout.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Node n = layout.item(j);
                    String name, type, value;
                    name = n.getNodeName();
                    type = n.getAttributes().item(0).getNodeValue();
                    value = n.getTextContent();
                    if (name.equals("other")) {
                        cs.addLayout(value, type, "true");
                    } else {
                        cs.addLayout(name, type, value);
                    }
                }
            }

            for (int j = 0; j < listeners.getLength(); j++) {
                if (listeners.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Node n = listeners.item(j);
                    String name, type, value;
                    name = n.getNodeName();
                    type = n.getAttributes().item(0).getNodeValue();
                    value = n.getTextContent();
                    if (name.equals("other")) {
                        cs.addListeners(value, type, "true");
                    } else {
                        cs.addListeners(name, type, value);
                    }
                }
            }

            allComponents.add(cs);
        }
    }
}
