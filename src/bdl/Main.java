package bdl;

import bdl.controller.ViewListeners;
import bdl.view.left.LeftPanel;
import bdl.view.middle.MiddlePanel;
import bdl.view.right.RightPanel;
import bdl.view.top.TopPanel;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main class.
 */
public class Main extends Application {

    AnchorPane root;
    public static ViewListeners viewListeners;

    public static Stage stage;
    public static LeftPanel leftPanel;
    public static RightPanel rightPanel;
    public static AnchorPane middleTop;

    @Override
    public void start(final Stage stage) throws Exception {
        this.stage = stage;
        stage.setMinWidth(800);
        stage.setMinHeight(500);
        
        //Begin Main panel
        BorderPane borderPane = new BorderPane();
        root = new AnchorPane();
        root.prefWidth(854);
        root.prefHeight(500);
        AnchorPane.setBottomAnchor(borderPane, 0.0);
        AnchorPane.setTopAnchor(borderPane, 0.0);
        AnchorPane.setLeftAnchor(borderPane, 0.0);
        AnchorPane.setRightAnchor(borderPane, 0.0);
        root.getChildren().add(borderPane);
        //End Main panel

        //Begin TopPanel (MenuBar)
        borderPane.setTop(new TopPanel());
        //End TopPanel (MenuBar)

        //Begin MiddlePanel
        SplitPane mainContent = new SplitPane();
        middleTop = new AnchorPane();
        rightPanel = new RightPanel();
        viewListeners = new ViewListeners(middleTop);
        leftPanel = new LeftPanel(viewListeners);

        AnchorPane middleAnchorPane = new AnchorPane();
        MiddlePanel middleTabPane = new MiddlePanel();
        AnchorPane.setTopAnchor(middleTabPane, 0.0);
        AnchorPane.setBottomAnchor(middleTabPane, 0.0);
        AnchorPane.setLeftAnchor(middleTabPane, 0.0);
        AnchorPane.setRightAnchor(middleTabPane, 0.0);
        middleAnchorPane.getChildren().add(middleTabPane);
        //End MiddlePanel

        //Begin LeftPanel
        AnchorPane leftAnchorPane = new AnchorPane();
        leftPanel.setOrientation(Orientation.VERTICAL);
        AnchorPane.setLeftAnchor(leftPanel, 0.0);
        AnchorPane.setRightAnchor(leftPanel, 0.0);
        AnchorPane.setTopAnchor(leftPanel, 0.0);
        AnchorPane.setBottomAnchor(leftPanel, 0.0);
        leftPanel.setDividerPosition(0, 0.6);
        leftPanel.setMinWidth(225);
        leftAnchorPane.getChildren().add(leftPanel);
        //End LeftPanel

        //Begin RightPanel
        AnchorPane rightAnchorPane = new AnchorPane();
        AnchorPane.setTopAnchor(rightPanel, 0.0);
        AnchorPane.setBottomAnchor(rightPanel, 0.0);
        AnchorPane.setLeftAnchor(rightPanel, 0.0);
        AnchorPane.setRightAnchor(rightPanel, 0.0);
        rightPanel.setOrientation(Orientation.VERTICAL);
        rightPanel.setDividerPositions(0.6);
        rightPanel.setMinWidth(225);
        rightAnchorPane.getChildren().add(rightPanel);
        //End RightPanel

        mainContent.getItems().addAll(leftAnchorPane, middleAnchorPane, rightAnchorPane);
        mainContent.setDividerPositions(0.25, 0.75);

        borderPane.setCenter(mainContent);


        Scene scene = new Scene(root, 1024, 600);

        stage.setTitle("GUI Builder");
        stage.setScene(scene);
        stage.show();
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
