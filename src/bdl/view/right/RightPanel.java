package bdl.view.right;

import bdl.model.ComponentSettingsStore;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class RightPanel extends SplitPane {

    public AnchorPane rightSplitPaneBottom;
    public ScrollPane propertyScroll;
    public PropertyEditPane propertyEditPane;

    public RightPanel(ComponentSettingsStore componentSettingsStore) {
        AnchorPane rightSplitPaneTop = new AnchorPane();

        //Begin right properties panel
        propertyEditPane = new PropertyEditPane(componentSettingsStore);
        propertyScroll = new ScrollPane();
        propertyScroll.setContent(propertyEditPane);
        AnchorPane.setTopAnchor(propertyScroll, 0.0);
        AnchorPane.setBottomAnchor(propertyScroll, 0.0);
        AnchorPane.setLeftAnchor(propertyScroll, 0.0);
        AnchorPane.setRightAnchor(propertyScroll, 0.0);
        rightSplitPaneTop.getChildren().add(propertyScroll);
        //End right properties panel

        rightSplitPaneBottom = new AnchorPane();

        getItems().addAll(rightSplitPaneTop);
    }
}
