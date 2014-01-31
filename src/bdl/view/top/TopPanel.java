package bdl.view.top;

import bdl.lang.LabelGrabber;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopPanel extends MenuBar {

    public Menu menuFile;
    public MenuItem mItmClose;
    public MenuItem mItmFullScreen;
    public MenuItem mItmLoadFile;
    public MenuItem mItmSaveFile;

    public Menu menuEdit;
    public MenuItem mItmDelete;

    public Menu menuView;
    public CheckMenuItem mItmHistory;
    public CheckMenuItem mItmHierarchy;

    public Menu menuHelp;
    public MenuItem mItmAbout;

    public TopPanel() {
        menuFile = new Menu(LabelGrabber.getLabel("menu.file"));
        mItmClose = new MenuItem(LabelGrabber.getLabel("menu.file.close"));
        mItmFullScreen = new MenuItem(LabelGrabber.getLabel("fullscreen.enable.text"));
        mItmLoadFile = new MenuItem(LabelGrabber.getLabel("menu.file.open"));
        mItmSaveFile = new MenuItem(LabelGrabber.getLabel("menu.file.save"));

        menuEdit = new Menu(LabelGrabber.getLabel("menu.edit"));
        mItmDelete = new MenuItem(LabelGrabber.getLabel("menu.edit.delete"));

        menuView = new Menu(LabelGrabber.getLabel("menu.view"));
        mItmHistory = new CheckMenuItem(LabelGrabber.getLabel("menu.view.history"));
        mItmHierarchy = new CheckMenuItem(LabelGrabber.getLabel("menu.view.hierarchy"));

        menuHelp = new Menu(LabelGrabber.getLabel("menu.help"));
        mItmAbout = new MenuItem(LabelGrabber.getLabel("menu.help.about"));

        menuFile.getItems().addAll(mItmLoadFile, mItmSaveFile, mItmFullScreen, mItmClose);
        menuEdit.getItems().addAll(mItmDelete);
        menuView.getItems().addAll(mItmHierarchy, mItmHistory);
        menuHelp.getItems().addAll(mItmAbout);

        getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
    }
}