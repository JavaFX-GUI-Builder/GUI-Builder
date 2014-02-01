package bdl.view.hierarchy;

import bdl.build.GObject;
import bdl.build.javafx.scene.control.GButton;
import bdl.build.javafx.scene.layout.GAnchorPane;
import bdl.controller.ViewListeners;
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
    private TreeItem<GObject> treeRoot;
    private ViewListeners viewListeners;

    public HierarchyPane(View view) {
        this.setMinWidth(200);
        this.setMaxWidth(200);

        viewListeners = new ViewListeners(view);

        final HierarchyTreeCellFactory htcf = new HierarchyTreeCellFactory();
        htcf.viewlisteners = viewListeners;

        treeView = new TreeView<>();
        AnchorPane.setBottomAnchor(treeView, 0.0);
        AnchorPane.setTopAnchor(treeView, 0.0);
        AnchorPane.setLeftAnchor(treeView, 0.0);
        AnchorPane.setRightAnchor(treeView, 0.0);
        this.getChildren().add(treeView);

        treeView.setCellFactory(new Callback<TreeView<GObject>, TreeCell<GObject>>() {
            @Override
            public TreeCell<GObject> call(TreeView<GObject> p) {
                return htcf;
            }
        });
        GButton but = new GButton();
        but.setFieldName("Testing root");
        TreeItem butti = new TreeItem(but);
        treeRoot = butti;
        treeView.setRoot(treeRoot);
        treeRoot.setExpanded(true);
        treeView.setShowRoot(true);

        GButton but1 = new GButton();
        but1.setFieldName("Testing Button");
        TreeItem but1ti = new TreeItem(but1);
        GButton but2 = new GButton();
        but2.setFieldName("Testing Button2");
        TreeItem but2ti = new TreeItem(but2);
        but1ti.getChildren().add(but2ti);
        treeRoot.getChildren().add(but1ti);
    }

    public void reorder() {
    }

    public final TreeItem add(TreeItem ti, GObject add) {
        TreeItem a = new TreeItem(add);
        ti.getChildren().add(a);
        return a;
    }
}
