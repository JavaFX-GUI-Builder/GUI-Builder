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
    public TreeItem<GObject> treeRoot;
    private ViewListeners viewListeners;
    
    public HierarchyPane(View view) {
        this.setMinWidth(200);
        this.setMaxWidth(200);
        
        viewListeners = new ViewListeners(view);
        
        final HierarchyTreeCellFactory htcf = new HierarchyTreeCellFactory();
        htcf.viewlisteners = viewListeners;
        
        treeView = new TreeView<>();
        treeView.setCellFactory(new Callback<TreeView<GObject>,TreeCell<GObject>>() {
            @Override
            public TreeCell<GObject> call(TreeView<GObject> p) {
                return htcf;
            }
        });
        GAnchorPane gap = new GAnchorPane();
        gap.setFieldName("Testing");
        treeRoot = new TreeItem(gap);
        add(gap);
        treeRoot.setExpanded(true);
        
        GButton but = new GButton();
        but.setFieldName("Testing Button");
        add(but);
        
        treeView.setRoot(treeRoot);
        
        AnchorPane.getTopAnchor(treeView);
        AnchorPane.getBottomAnchor(treeView);
        AnchorPane.getLeftAnchor(treeView);
        AnchorPane.getRightAnchor(treeView);
        
        this.getChildren().add(treeView);
    }
    
    public void reorder() {
       
    }
    
    public void add(GObject add) {
        treeRoot.getChildren().add(new TreeItem(add));
    }
}
