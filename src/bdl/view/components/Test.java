package bdl.view.components;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A short test class to test the output of the parsed xml file.
 * @author Ben Goodwin
 */
public class Test extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        ComponentViewReader.ParseSettings();
        for (ComponentSettings cse : ComponentViewReader.allComponents()) {
            System.out.println("Name: " + cse.getName());
            System.out.println("Properties: ");
            for (ComponentSettings.Properties p : cse.getProperties()) {
                if (p.getValue().equals("true")) {
                    System.out.println("     " + p.getName());
                }
            }
            System.out.println("Layout: ");
            for (ComponentSettings.Layout p : cse.getLayout()) {
                if (p.getValue().equals("true")) {
                    System.out.println("     " + p.getName());
                }
            }
            System.out.println("Listeners: ");
            for (ComponentSettings.Listeners p : cse.getListeners()) {
                if (p.getValue().equals("true")) {
                    System.out.println("     " + p.getName());
                }
            }
        }
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
