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
    public MenuItem mItmUndo;
    public MenuItem mItmRedo;
    public MenuItem mItmDelete;

    public Menu menuView;
    public CheckMenuItem mItmHistory;
    public CheckMenuItem mItmHierarchy;
    
    // David
    public Menu menuDebug;
    public MenuItem mItmDebugMsg;

    public Menu menuHelp;
    public MenuItem mItmAbout;

    public TopPanel() {
        menuFile = new Menu(LabelGrabber.getLabel("menu.file"));
        mItmClose = new MenuItem(LabelGrabber.getLabel("menu.file.close"));
        mItmFullScreen = new MenuItem(LabelGrabber.getLabel("fullscreen.enable.text"));
        mItmLoadFile = new MenuItem(LabelGrabber.getLabel("menu.file.open"));
        mItmSaveFile = new MenuItem(LabelGrabber.getLabel("menu.file.save"));

        menuEdit = new Menu(LabelGrabber.getLabel("menu.edit"));
        mItmUndo = new MenuItem(LabelGrabber.getLabel("menu.edit.undo"));
        mItmUndo.setDisable(true);
        mItmRedo = new MenuItem(LabelGrabber.getLabel("menu.edit.redo"));
        mItmRedo.setDisable(true);
        mItmDelete = new MenuItem(LabelGrabber.getLabel("menu.edit.delete"));

        menuView = new Menu(LabelGrabber.getLabel("menu.view"));
        mItmHistory = new CheckMenuItem(LabelGrabber.getLabel("menu.view.history"));
        mItmHistory.setSelected(true);
        mItmHierarchy = new CheckMenuItem(LabelGrabber.getLabel("menu.view.hierarchy"));
        mItmHierarchy.setSelected(true);
        
        // David
        menuDebug = new Menu("Debug");
        mItmDebugMsg = new MenuItem("Test String");

        menuHelp = new Menu(LabelGrabber.getLabel("menu.help"));
        mItmAbout = new MenuItem(LabelGrabber.getLabel("menu.help.about"));

        menuFile.getItems().addAll(mItmLoadFile, mItmSaveFile, mItmFullScreen, mItmClose);
        menuEdit.getItems().addAll(mItmUndo, mItmRedo, mItmDelete);
        menuView.getItems().addAll(mItmHierarchy, mItmHistory);
        menuDebug.getItems().addAll(mItmDebugMsg);
        menuHelp.getItems().addAll(mItmAbout);

        getMenus().addAll(menuFile, menuEdit, menuView, menuDebug, menuHelp);
    }
}