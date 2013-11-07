package bdl.view.components;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class will read in the xml file that provides the configuration for
 * every component that the GUI will support, and provides static methods to
 * return all or individual settings per component
 *
 * @author Ben Goodwin
 */
public class ComponentViewReader {

    private static Collection<ComponentSettings> allComponents = new ArrayList<>();

    /**
     * Takes a name of a component and returns the associated ComponentSettings
     * object for it.
     *
     * @param name The name of the component to look up
     * @return The ComponentSettings file associated with that component
     */
    public static ComponentSettings getSettingsByName(String name) {
        for (ComponentSettings cs : allComponents) {
            if (cs.getName().equals(name)) {
                return cs;
            }
        }
        return null;
    }

    /**
     * Returns all the components currently supported by the GUI as a collection
     * of ComponentSettings objects
     *
     * @return A collection of every component in it's ComponentSettings state
     */
    public static Collection<ComponentSettings> allComponents() {
        return allComponents;
    }

    /**
     * Returns all the names of components currently supported
     *
     * @return A collection of Strings of names of components supported
     */
    public static Collection<String> allComponentNames() {
        ArrayList al = new ArrayList<>();
        for (ComponentSettings cs : allComponents) {
            al.add(cs.getName());
        }
        return al;
    }

    /**
     * Reads in the xml properties file located at
     * bdl.view.components.component-options.xml and parses the file creating 
     * a list of ComponentSettings with all properties initialised. 
     */
    public static void ParseSettings() {
        File settings;
        try {
            settings = new File("src/bdl/view/components/component-options.xml");
        } catch (Exception e) {
            System.out.println("Cannot find file specified.");
            settings = null;
        }
        if (settings != null) {
            try {
                DocumentBuilderFactory dbf =
                        DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document d = db.parse(settings);


                d.getDocumentElement().normalize();
                Node root = d.getDocumentElement();
                NodeList components = d.getDocumentElement().getElementsByTagName("component");
                allComponents = new ArrayList<>();

                for (int i = 0; i < components.getLength(); i++) {
                    ComponentSettings cs = new ComponentSettings();
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
            } catch (ParserConfigurationException | SAXException | IOException e) {
                if (e instanceof IOException) {
                    System.out.println("Error reading file: " + e);
                } else if (e instanceof SAXException) {
                    System.out.println("Error SAX thing: " + e);
                } else {
                    System.out.println("Parse Error: " + e);
                }
            }
        }
    }
}
