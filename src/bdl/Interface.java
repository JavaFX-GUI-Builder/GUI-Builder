package bdl;

import javafx.application.Platform;
import javax.swing.SwingUtilities;

/**
 * Class to be used as a .jar interface between BlueJ and the GUI Builder
 * Interface to be used by BlueJ and the GUI builder to communicate between each other.
 *
 * @author David Hodgson
 */
public class Interface {
    
    // If you are not working with the BlueJ project, comment out the following section of BlueJ code.
//    public static bluej.pkgmgr.PkgMgrFrame pmf;
//    public static void SetPkgMgrFrame(bluej.pkgmgr.PkgMgrFrame pmf) { Interface.pmf = pmf; }
//    // Interface from GUI Builder -> BlueJ
//    public static void TestWriteMessage(final String msg) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                // TODO: Attempt to communicate back to BlueJ.
//                pmf.setStatus(msg);
//            }
//        });
//    }
    
    // Private fields
    private static javafx.stage.Stage stage;
    private static bdl.controller.Controller controller;
    private static boolean started = false;
    private static ClassLoader cl;
    
    // Interface from BlueJ -> GUI Builder
    public static void Show() {
        if (started == false) { Start(); }
        else { stage.show(); }
    }
    public static void Hide() { stage.hide(); }
    public static void SetStage(javafx.stage.Stage stage) { Interface.stage = stage; }
    public static void SetController(bdl.controller.Controller controller) { Interface.controller = controller; }
    public static void SetClassLoader(ClassLoader cl) { Interface.cl = cl; }
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
    
    // Private methods
    private static void Start() {
        System.out.println("bdl.Interface.Start() called");
        started = true;
        Thread guiThread = new Thread(new bdl.Main(), "guibuilder");
        guiThread.setContextClassLoader(cl);
        guiThread.start();
    }
}
