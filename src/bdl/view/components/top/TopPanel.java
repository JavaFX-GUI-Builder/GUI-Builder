package bdl.view.components.top;

import bdl.Main;
import bdl.view.components.left.LeftPanel;
import bdl.view.components.right.RightPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopPanel extends MenuBar {

    public TopPanel() {
        Menu menuFile = new Menu("File");

        MenuItem mItmClose = new MenuItem("Close");
        mItmClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.stage.close();
            }
        });

        final MenuItem mItmFullScreen = new MenuItem("Make Full Screen");
        mItmFullScreen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (Main.stage.isFullScreen()) {
                    Main.stage.setFullScreen(false);
                    mItmFullScreen.setText("Make Full Screen");
                } else {
                    Main.stage.setFullScreen(true);
                    mItmFullScreen.setText("Exit Full Screen");
                }
            }
        });

        Menu menuEdit = new Menu("Edit");
        MenuItem mItmDelete = new MenuItem("Delete");

        Menu menuView = new Menu("View");
        final CheckMenuItem mItmHistory = new CheckMenuItem("Show History");
        final CheckMenuItem mItmHierarchy = new CheckMenuItem("Show Hierarchy");

        mItmHierarchy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (mItmHierarchy.isSelected()) {
                    Main.leftPanel.getItems().add(LeftPanel.hierarchyPane);
                    Main.leftPanel.setDividerPosition(0, 0.6);
                } else {
                    Main.leftPanel.getItems().remove(LeftPanel.hierarchyPane);
                }
            }
        });

        mItmHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (mItmHistory.isSelected()) {
                    Main.rightPanel.getItems().add(RightPanel.rightSplitPaneBottom);
                    Main.rightPanel.setDividerPosition(0, 0.6);
                } else {
                    Main.rightPanel.getItems().remove(RightPanel.rightSplitPaneBottom);
                }
            }
        });


        Menu menuHelp = new Menu("Help");
        MenuItem mItmAbout = new MenuItem("About");

        menuFile.getItems().addAll(mItmFullScreen, mItmClose);
        menuEdit.getItems().addAll(mItmDelete);
        menuView.getItems().addAll(mItmHierarchy, mItmHistory);
        menuHelp.getItems().addAll(mItmAbout);

        getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
    }
}