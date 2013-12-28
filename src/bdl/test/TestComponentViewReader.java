package bdl.test;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A short test class to test the output of the parsed xml file.
 * @author Ben Goodwin
 */
public class TestComponentViewReader extends Application {
    
    @Override
    public void start(Stage primaryStage) {
//        ComponentSettings.parseSettings();
//        for (Component cse : ComponentSettings.allComponents()) {
//            System.out.println("Name: " + cse.getName());
//            System.out.println("Properties: ");
//            for (Component.Properties p : cse.getProperties()) {
//                if (p.getValue()) {
//                    System.out.println("     " + p.getName());
//                }
//            }
//            System.out.println("Layout: ");
//            for (Component.Layout p : cse.getLayout()) {
//                if (p.getValue()) {
//                    System.out.println("     " + p.getName());
//                }
//            }
//            System.out.println("Listeners: ");
//            for (Component.Listeners p : cse.getListeners()) {
//                if (p.getValue()) {
//                    System.out.println("     " + p.getName());
//                }
//            }
//        }
        
//        primaryStage.setScene(new Scene(new PropertyEditPane()));
        primaryStage.show();

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
