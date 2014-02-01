package bdl.view.hierarchy;

import bdl.build.GObject;
import bdl.controller.ViewListeners;
import bdl.view.View;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Ben Goodwin
 */
public class HierarchyTreeCellFactory extends TreeCell<GObject> {
    
    public HierarchyTreeCellFactory(final View view, final ViewListeners viewListeners) {
        super();
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                GObject node = getItem();
                viewListeners.redraw((Node) node);
                view.rightPanel.propertyScroll.setContent(node.getPEP());
//                try {
//                    ((Node)node).getOnMouseClicked().handle(t);
//                }
//                catch(Exception e) {
//                    System.err.println(e);
//                }
            }
        });
    }
    
    @Override
    public void updateItem(GObject item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            setText(item.getFieldName());
        }
    }
}