package bdl.view.top;

import bdl.lang.LabelGrabber;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

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

    public Menu menuHelp;
    public MenuItem mItmAbout;
    public MenuItem mItmClearAll;

    public TopPanel(boolean isBlueJAttached) {
        menuFile = new Menu(LabelGrabber.getLabel("menu.file"));
        mItmLoadFile = new MenuItem(LabelGrabber.getLabel("menu.file.open"));
        mItmSaveFile = new MenuItem(LabelGrabber.getLabel("menu.file.save"));
        mItmClose = new MenuItem(LabelGrabber.getLabel("menu.file.close"));
        mItmFullScreen = new MenuItem(LabelGrabber.getLabel("fullscreen.enable.text"));

        menuEdit = new Menu(LabelGrabber.getLabel("menu.edit"));
        mItmUndo = new MenuItem(LabelGrabber.getLabel("menu.edit.undo"));
        mItmUndo.setDisable(true);
        mItmUndo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        mItmRedo = new MenuItem(LabelGrabber.getLabel("menu.edit.redo"));
        mItmRedo.setDisable(true);
        mItmRedo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
        mItmDelete = new MenuItem(LabelGrabber.getLabel("menu.edit.delete"));
        mItmDelete.setDisable(true);
        mItmDelete.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        mItmClearAll = new MenuItem(LabelGrabber.getLabel("menu.edit.clearall"));
        mItmClearAll.setDisable(true);

        menuView = new Menu(LabelGrabber.getLabel("menu.view"));
        mItmHistory = new CheckMenuItem(LabelGrabber.getLabel("menu.view.history"));
        mItmHistory.setSelected(true);
        mItmHierarchy = new CheckMenuItem(LabelGrabber.getLabel("menu.view.hierarchy"));
        mItmHierarchy.setSelected(true);

        menuHelp = new Menu(LabelGrabber.getLabel("menu.help"));
        mItmAbout = new MenuItem(LabelGrabber.getLabel("menu.help.about"));

        if (isBlueJAttached) {
            menuFile.getItems().addAll(mItmSaveFile, mItmFullScreen, mItmClose);
        } else {
            menuFile.getItems().addAll(mItmLoadFile, mItmSaveFile, mItmFullScreen, mItmClose);
        }
        menuEdit.getItems().addAll(mItmUndo, mItmRedo, mItmDelete, mItmClearAll);
        menuView.getItems().addAll(mItmHierarchy, mItmHistory);
        menuHelp.getItems().addAll(mItmAbout);

        getMenus().addAll(menuFile, menuEdit, menuView);
        getMenus().addAll(menuHelp);
    }
}