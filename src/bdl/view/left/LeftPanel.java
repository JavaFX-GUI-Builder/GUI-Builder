package bdl.view.left;

import bdl.model.ComponentSettings;
import bdl.model.ComponentSettingsStore;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LeftPanel extends SplitPane {

    public TitledPane hierarchyPane;
    public ListView<ComponentMenuItem> leftList;
    public TreeItem<String> treeRoot;
    public TreeView<String> leftTreeView;

    public LeftPanel(ComponentSettingsStore componentSettingsStore) {
        //Begin left component list
        leftList = new ListView<>();

        for (ComponentSettings componentSettings : componentSettingsStore.getComponents()) {
            String type = componentSettings.getType();
            ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/bdl/icons/" + componentSettings.getIcon())));
            leftList.getItems().add(new ComponentMenuItem(type, icon, componentSettings));
        }

//        leftList.getItems().add(GType.Button);
//        leftList.getItems().add(GType.CheckBox);
//        leftList.getItems().add(GType.ComboBox);
//        leftList.getItems().add(GType.Label);
////        leftList.getItems().add(GType.ListView);
////        leftList.getItems().add(GType.Menu);
////        leftList.getItems().add(GType.MenuBar);
////        leftList.getItems().add(GType.MenuItem);
////        leftList.getItems().add(GType.ScrollPane);
////        leftList.getItems().add(GType.SplitPane);
//        leftList.getItems().add(GType.TextArea);
//        leftList.getItems().add(GType.TextField);
//        leftList.getItems().add(GType.ToolBar);
////        leftList.getItems().add(GType.ImageView);
//        leftList.getItems().add(GType.Circle);
//        leftList.getItems().add(GType.Rectangle);
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
