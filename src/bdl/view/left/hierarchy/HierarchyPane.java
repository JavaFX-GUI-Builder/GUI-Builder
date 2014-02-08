package bdl.view.left.hierarchy;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Ben Goodwin
 */
public class HierarchyPane extends AnchorPane {

    public TreeView<HierarchyTreeItem> treeView;
    public TreeItem<HierarchyTreeItem> treeRoot;

    public HierarchyPane() {
        this.setMinWidth(200);
        this.setMaxWidth(200);

        treeView = new TreeView<>();
        AnchorPane.setBottomAnchor(treeView, 0.0);
        AnchorPane.setTopAnchor(treeView, 0.0);
        AnchorPane.setLeftAnchor(treeView, 0.0);
        AnchorPane.setRightAnchor(treeView, 0.0);
        this.getChildren().add(treeView);
    }
}
