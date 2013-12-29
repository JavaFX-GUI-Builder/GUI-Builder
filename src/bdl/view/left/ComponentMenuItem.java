package bdl.view.left;

import bdl.model.ComponentSettings;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class ComponentMenuItem extends Label {

    private ComponentSettings componentSettings;

    public ComponentMenuItem(String s, Node graphic, ComponentSettings componentSettings) {
        super(s, graphic);
        this.componentSettings = componentSettings;
    }

    public ComponentSettings getComponentSettings() {
        return componentSettings;
    }

}
