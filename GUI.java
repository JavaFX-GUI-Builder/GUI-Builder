import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.Tooltip;

import javafx.scene.control.TextArea;

public class GUI extends Application {

    public static TextArea textArea1 = new TextArea();

    private Parent getRoot() {
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(textArea1);

        textArea1.setLayoutX(44.0);
        textArea1.setLayoutY(76.0);
        textArea1.setText("Text Area Text!");

        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(getRoot(), 600.0, 400.0);
        
        primaryStage.setTitle("GUI Builder");
        primaryStage.setScene(scene);
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