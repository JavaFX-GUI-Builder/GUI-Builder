package bdl.view.hierarchy;

import bdl.build.GObject;
import bdl.view.View;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 *
 * @author Ben Goodwin
 */
public class HierarchyPane extends AnchorPane {

    public TreeView<GObject> treeView;
    public TreeItem<GObject> treeRoot;

    public HierarchyPane(final View view) {
        this.setMinWidth(200);
        this.setMaxWidth(200);


        treeView = new TreeView<>();
        AnchorPane.setBottomAnchor(treeView, 0.0);
        AnchorPane.setTopAnchor(treeView, 0.0);
        AnchorPane.setLeftAnchor(treeView, 0.0);
        AnchorPane.setRightAnchor(treeView, 0.0);
        this.getChildren().add(treeView);

        treeView.setCellFactory(new Callback<TreeView<GObject>, TreeCell<GObject>>() {
            @Override
            public TreeCell<GObject> call(TreeView<GObject> p) {
                return new HierarchyTreeCellFactory(view, view.viewListeners);
            }
        });

        treeRoot = new TreeItem(view.middleTabPane.viewPane);
        treeView.setRoot(treeRoot);
        treeRoot.setExpanded(true);
        treeView.setShowRoot(true);
    }

    public void reorder() {
    }

    public final TreeItem add(TreeItem ti, GObject add) {
        TreeItem a = new TreeItem(add);
        if (ti == null) {
            treeRoot.getChildren().add(a);
        } else {
            ti.getChildren().add(a);
        }
        return a;
    }
}
