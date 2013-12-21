package bdl.view.left;

import bdl.build.GType;
import javafx.scene.control.*;

public class LeftPanel extends SplitPane {

    public TitledPane hierarchyPane;
    public ListView<GType> leftList;
    public TreeItem<String> treeRoot;
    public TreeView<String> leftTreeView;

    public LeftPanel() {
        //Begin left component list
        leftList = new ListView<>();
        leftList.getItems().add(GType.Button);
        leftList.getItems().add(GType.CheckBox);
        leftList.getItems().add(GType.ComboBox);
        leftList.getItems().add(GType.Label);
//        leftList.getItems().add(GType.ListView);
//        leftList.getItems().add(GType.Menu);
//        leftList.getItems().add(GType.MenuBar);
//        leftList.getItems().add(GType.MenuItem);
//        leftList.getItems().add(GType.ScrollPane);
//        leftList.getItems().add(GType.SplitPane);
        leftList.getItems().add(GType.TextArea);
        leftList.getItems().add(GType.TextField);
        leftList.getItems().add(GType.ToolBar);
//        leftList.getItems().add(GType.ImageView);
        leftList.getItems().add(GType.Circle);
        leftList.getItems().add(GType.Rectangle);
        //End left component list

        //Begin left hierarchy panel
        treeRoot = new TreeItem<>("AnchorPane");
        treeRoot.setExpanded(true);
        treeRoot.getChildren().add(new TreeItem<>("Button"));
        treeRoot.getChildren().add(new TreeItem<>("Button"));
        treeRoot.getChildren().add(new TreeItem<>("Button"));
        treeRoot.getChildren().add(new TreeItem<>("Button"));

        leftTreeView = new TreeView<>(treeRoot);

        hierarchyPane = new TitledPane("Hierarchy", leftTreeView);
        hierarchyPane.setCollapsible(false);
        //End left hierarchy panel

        getItems().addAll(leftList);
    }

}
