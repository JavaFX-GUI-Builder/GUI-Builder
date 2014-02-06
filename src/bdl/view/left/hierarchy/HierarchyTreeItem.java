package bdl.view.left.hierarchy;

import bdl.build.GObject;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;

public class HierarchyTreeItem extends Label {

    GObject gObject;

    public HierarchyTreeItem(GObject gObject) {
        this.gObject = gObject;
        this.textProperty().bind(gObject.fieldNameProperty());
    }

    public GObject getGObject() {
        return gObject;
    }
}
