package bdl.view.right;

import bdl.view.right.history.HistoryPanel;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class RightPanel extends SplitPane {

    public HistoryPanel<Label> historyPanel;
    public ScrollPane propertyScroll;
    public PropertyEditPane propertyEditPane;

    public RightPanel() {
        AnchorPane rightSplitPaneTop = new AnchorPane();

        //Begin right properties panel
        propertyEditPane = new PropertyEditPane();
        propertyScroll = new ScrollPane();
        propertyScroll.setContent(propertyEditPane);
        AnchorPane.setTopAnchor(propertyScroll, 0.0);
        AnchorPane.setBottomAnchor(propertyScroll, 0.0);
        AnchorPane.setLeftAnchor(propertyScroll, 0.0);
        AnchorPane.setRightAnchor(propertyScroll, 0.0);
        rightSplitPaneTop.getChildren().add(propertyScroll);
        //End right properties panel

        historyPanel = new HistoryPanel<>();

        getItems().addAll(rightSplitPaneTop, historyPanel);
    }
}
