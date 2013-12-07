package bdl.view.right;

import bdl.view.components.ComponentViewReader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class RightPanel extends SplitPane {

    public static AnchorPane rightSplitPaneBottom;

    public RightPanel() {
        AnchorPane rightSplitPaneTop = new AnchorPane();

        //Begin right properties panel
        ComponentViewReader.parseSettings();
        PropertyEditPane pep = new PropertyEditPane();
        ScrollPane propertyScroll = new ScrollPane();
        propertyScroll.setContent(pep);
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
