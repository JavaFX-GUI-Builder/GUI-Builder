package bdl.view.left;

import bdl.lang.LabelGrabber;
import bdl.model.ComponentSettings;
import bdl.model.ComponentSettingsStore;
import bdl.view.View;
import bdl.view.left.hierarchy.HierarchyPane;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;

public class LeftPanel extends SplitPane {

    private TitledPane hierarchyTitledPane;
    public ListView<ComponentMenuItem> leftList;
    public HierarchyPane hierarchyPane;

    public LeftPanel() {
        //Begin left component list
        leftList = new ListView<>();
        //End left component list

        //Begin left hierarchy panel
        hierarchyPane = new HierarchyPane();
        hierarchyTitledPane = new TitledPane(LabelGrabber.getLabel("hierarchy.tab.title"), hierarchyPane);
        hierarchyTitledPane.setCollapsible(false);
        hierarchyTitledPane.setMinWidth(205);
        hierarchyTitledPane.setMaxWidth(205);
        //End left hierarchy panel

        getItems().addAll(leftList, hierarchyTitledPane);
    }
}
