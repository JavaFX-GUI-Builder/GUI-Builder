package bdl.view.components;

import bdl.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainMenuBar extends MenuBar {

    public MainMenuBar() {
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
                    Main.leftSplitPane.getItems().add(Main.leftTitledPane);
                    Main.leftSplitPane.setDividerPosition(0, 0.6);
                } else {
                    Main.leftSplitPane.getItems().remove(Main.leftTitledPane);
                }
            }
        });

        mItmHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (mItmHistory.isSelected()) {
                    Main.rightSplitPane.getItems().add(Main.rightSplitPaneBottom);
                    Main.rightSplitPane.setDividerPosition(0, 0.6);
                } else {
                    Main.rightSplitPane.getItems().remove(Main.rightSplitPaneBottom);
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