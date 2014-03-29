package bdl.view.left;

import bdl.lang.LabelGrabber;
import bdl.view.left.hierarchy.HierarchyPane;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;

public class LeftPanel extends SplitPane {

    public TitledPane hierarchyTitledPane;
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
