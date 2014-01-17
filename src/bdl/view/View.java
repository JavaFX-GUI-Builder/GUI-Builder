package bdl.view;

import bdl.model.ComponentSettingsStore;
import bdl.view.left.LeftPanel;
import bdl.view.middle.MiddlePanel;
import bdl.view.right.RightPanel;
import bdl.view.top.TopPanel;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class View extends AnchorPane {

    public TopPanel topPanel;
    public LeftPanel leftPanel;
    public RightPanel rightPanel;
    public MiddlePanel middleTabPane;

    public View(ComponentSettingsStore componentSettingsStore) {
        //Begin Main panel
        BorderPane borderPane = new BorderPane();
        prefWidth(854);
        prefHeight(500);
        AnchorPane.setBottomAnchor(borderPane, 0.0);
        AnchorPane.setTopAnchor(borderPane, 0.0);
        AnchorPane.setLeftAnchor(borderPane, 0.0);
        AnchorPane.setRightAnchor(borderPane, 0.0);
        getChildren().add(borderPane);
        //End Main panel

        //Begin TopPanel (MenuBar)
        topPanel = new TopPanel();
        borderPane.setTop(topPanel);
        //End TopPanel (MenuBar)

        //Begin MiddlePanel
        SplitPane mainContent = new SplitPane();
        rightPanel = new RightPanel();

        leftPanel = new LeftPanel(componentSettingsStore);

        AnchorPane middleAnchorPane = new AnchorPane();
        middleTabPane = new MiddlePanel();
        AnchorPane.setTopAnchor(middleTabPane, 0.0);
        AnchorPane.setBottomAnchor(middleTabPane, 0.0);
        AnchorPane.setLeftAnchor(middleTabPane, 0.0);
        AnchorPane.setRightAnchor(middleTabPane, 0.0);
        middleAnchorPane.getChildren().add(middleTabPane);
        //End MiddlePanel

        //Begin LeftPanel
        AnchorPane leftAnchorPane = new AnchorPane();
        leftPanel.setOrientation(Orientation.VERTICAL);
        AnchorPane.setLeftAnchor(leftPanel, 0.0);
        AnchorPane.setRightAnchor(leftPanel, 0.0);
        AnchorPane.setTopAnchor(leftPanel, 0.0);
        AnchorPane.setBottomAnchor(leftPanel, 0.0);
        leftPanel.setDividerPosition(0, 0.6);
        leftPanel.setMinWidth(205);
        leftAnchorPane.setMaxWidth(205);
        leftAnchorPane.getChildren().add(leftPanel);
        //End LeftPanel

        //Begin RightPanel
        AnchorPane rightAnchorPane = new AnchorPane();
        AnchorPane.setTopAnchor(rightPanel, 0.0);
        AnchorPane.setBottomAnchor(rightPanel, 0.0);
        AnchorPane.setLeftAnchor(rightPanel, 0.0);
        AnchorPane.setRightAnchor(rightPanel, 0.0);
        rightPanel.setOrientation(Orientation.VERTICAL);
        rightPanel.setDividerPositions(0.6);
        rightPanel.setMinWidth(205);
        rightAnchorPane.setMaxWidth(205);
        rightAnchorPane.getChildren().add(rightPanel);
        //End RightPanel

        mainContent.getItems().addAll(leftAnchorPane, middleAnchorPane, rightAnchorPane);
        mainContent.setDividerPositions(0.25, 0.75);

        borderPane.setCenter(mainContent);

    }

}
