package bdl.view.top;

import bdl.Main;
import bdl.view.left.LeftPanel;
import bdl.view.right.RightPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopPanel extends MenuBar {

    public Menu menuFile;
    public MenuItem mItmClose;
    public MenuItem mItmFullScreen;

    public Menu menuEdit;
    public MenuItem mItmDelete;

    public Menu menuView;
    public CheckMenuItem mItmHistory;
    public CheckMenuItem mItmHierarchy;

    public Menu menuHelp;
    public MenuItem mItmAbout;

    public TopPanel() {
        menuFile = new Menu("File");
        mItmClose = new MenuItem("Close");
        mItmFullScreen = new MenuItem("Make Full Screen");

        menuEdit = new Menu("Edit");
        mItmDelete = new MenuItem("Delete");

        menuView = new Menu("View");
        mItmHistory = new CheckMenuItem("Show History");
        mItmHierarchy = new CheckMenuItem("Show Hierarchy");

        menuHelp = new Menu("Help");
        mItmAbout = new MenuItem("About");

        menuFile.getItems().addAll(mItmFullScreen, mItmClose);
        menuEdit.getItems().addAll(mItmDelete);
        menuView.getItems().addAll(mItmHierarchy, mItmHistory);
        menuHelp.getItems().addAll(mItmAbout);

        getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
    }
}