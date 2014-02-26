package bdl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javax.swing.SwingUtilities;

/**
 * Class to be used as a .jar interface between BlueJ and the GUI Builder
 * Interface to be used by BlueJ and the GUI builder to communicate between each other.
 *
 * @author David Hodgson
 */
public class Interface {
    
    // Private fields
    private static javafx.stage.Stage stage;
    private static bdl.controller.Controller controller;
    private static boolean started = false;
    private static ClassLoader cl;
    private static bluej.pkgmgr.PkgMgrFrame pmf;
    //private static Class<?> interfaceToBlueJ;
    
    // Public methods
    public static void Show() {
        if (started == false) { Start(); }
        else { stage.show(); }
    }
    public static void Hide() { stage.hide(); }
    public static void SetUsingBlueJ(boolean bool) {
        // TODO -- Determines whether BlueJ-specific features are enabled,
        // or whether the application should run standalone
        // (e.g. standalone = don't send code updates to BlueJ)
    }
    public static void SetStage(javafx.stage.Stage stage) { Interface.stage = stage; }
    public static void SetController(bdl.controller.Controller controller) { Interface.controller = controller; }
    public static void SetClassLoader(ClassLoader cl) { Interface.cl = cl; }
    public static void SetPkgMgrFrame(bluej.pkgmgr.PkgMgrFrame pmf) { Interface.pmf = pmf; }
    public static void TestToggleHistory() {
        Platform.runLater(new Runnable() { 
            @Override
            public void run() {
                bdl.view.View view = controller.view;
                // Change selected state
                view.topPanel.mItmHistory.setSelected(!view.topPanel.mItmHistory.isSelected());
                if (view.topPanel.mItmHistory.isSelected()) {
                    view.rightPanel.getItems().add(view.rightPanel.historyPanel);
                    view.rightPanel.setDividerPosition(0, 0.6);
                    
                } else {
                    view.rightPanel.getItems().remove(view.rightPanel.historyPanel);
                }
            }
        });
    }
//    public static void BlueJSetup() {
//        try {
//            interfaceToBlueJ = Class.forName("bdl.InterfaceToBlueJ", true, cl);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void TestWriteMessage(final String msg) {
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            // TODO: Attempt to communicate back to BlueJ.
            pmf.setStatus(msg);
        }
    });
    }
    
    // Private methods
    private static void Start() {
        System.out.println("bdl.Interface.Start() called");
        started = true;
        Thread guiThread = new Thread(new bdl.Main(), "guibuilder");
        guiThread.setContextClassLoader(cl);
        guiThread.start();
    }
}
