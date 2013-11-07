package bdl.view.components;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Ben Goodwin
 */
class ComponentViewReader {

    private static Collection<ComponentSettings> allComponents = ParseSettings();

    public static ComponentSettings getSettingsByName(String name) {
        for (ComponentSettings cs : allComponents) {
            if (cs.getName().equals(name)) {
                return cs;
            }
        }
        return null;
    }

    public static Collection<ComponentSettings> allComponents() {
        return allComponents;
    }

    public static Collection<ComponentSettings> allComponentNames() {
        ArrayList al = new ArrayList<>();
        for (ComponentSettings cs : allComponents) {
            al.add(cs.getName());
        }
        return allComponents;
    }

    private static Collection<ComponentSettings> ParseSettings() {
        try {
            File settings = new File("component-options.xml");
        } catch (Exception e) {
            System.out.println("Cannot find file.");
        }

        return null;
    }
}
