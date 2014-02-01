package bdl.view.hierarchy;

import bdl.build.GObject;
import bdl.controller.ViewListeners;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Ben Goodwin
 */
public class HierarchyTreeCellFactory extends TreeCell<GObject> {

    public ViewListeners viewlisteners;

    public HierarchyTreeCellFactory(ViewListeners viewlisteners) {
        super();
        this.viewlisteners = viewlisteners;
    }

    @Override
    public void updateItem(GObject item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            setText(item.getFieldName());
        }
    }
}