package bdl;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *  Main class.
 */
public class Main extends Application {

    AnchorPane root;
    
    @Override
    public void start(Stage stage) throws Exception {

        BorderPane borderPane = new BorderPane();
        root = new AnchorPane();
        root.prefWidth(854);
        root.prefHeight(500);
        AnchorPane.setBottomAnchor(borderPane, 0.0);
        AnchorPane.setTopAnchor(borderPane, 0.0);
        AnchorPane.setLeftAnchor(borderPane, 0.0);
        AnchorPane.setRightAnchor(borderPane, 0.0);
        root.getChildren().add(borderPane);

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem mItmClose = new MenuItem("Close");
        Menu menuEdit = new Menu("Edit");
        MenuItem mItmDelete = new MenuItem("Delete");
        Menu menuHelp = new Menu("Help");
        MenuItem mItmAbout = new MenuItem("About");
        menuBar.getMenus().addAll(menuFile, menuEdit, menuHelp);
        menuFile.getItems().add(mItmClose);
        menuEdit.getItems().add(mItmDelete);
        menuEdit.getItems().add(mItmAbout);

        borderPane.setTop(menuBar);

        SplitPane mainContent = new SplitPane();

        AnchorPane left = new AnchorPane();
        SplitPane leftSplitPane = new SplitPane();
        leftSplitPane.setOrientation(Orientation.VERTICAL);
        AnchorPane.setLeftAnchor(leftSplitPane, 0.0);
        AnchorPane.setRightAnchor(leftSplitPane, 0.0);
        AnchorPane.setTopAnchor(leftSplitPane, 0.0);
        AnchorPane.setBottomAnchor(leftSplitPane, 0.0);


        ListView<Label> leftList = new ListView<>();

        leftList.getItems().add(new Label("Button", new Button("Button")));
        leftList.getItems().add(new Label("Button", new Button("Button")));
        leftList.getItems().add(new Label("Button", new Button("Button")));
        leftList.getItems().add(new Label("Button", new Button("Button")));
        leftList.getItems().add(new Label("Button", new Button("Button")));
        leftList.getItems().add(new Label("Button", new Button("Button")));
        leftList.getItems().add(new Label("Button", new Button("Button")));
        leftList.getItems().add(new Label("Button", new Button("Button")));
        leftList.getItems().add(new Label("Button", new Button("Button")));
        leftList.getItems().add(new Label("Button", new Button("Button")));
        leftList.getItems().add(new Label("Button", new Button("Button")));
        leftList.getItems().add(new Label("Button", new Button("Button")));

        TreeItem<String> treeRoot = new TreeItem<>("AnchorPane");
        treeRoot.setExpanded(true);
        treeRoot.getChildren().add(new TreeItem<>("Button"));
        treeRoot.getChildren().add(new TreeItem<>("Button"));
        treeRoot.getChildren().add(new TreeItem<>("Button"));
        treeRoot.getChildren().add(new TreeItem<>("Button"));

        TreeView<String> leftTreeView = new TreeView<>(treeRoot);

        TitledPane leftTitledPane = new TitledPane("Hierarchy", leftTreeView);
        leftTitledPane.setCollapsible(false);

        leftSplitPane.getItems().addAll(leftList, leftTitledPane);
        left.getChildren().add(leftSplitPane);


        AnchorPane middle = new AnchorPane();
        SplitPane middleSplitPane = new SplitPane();
        middleSplitPane.setOrientation(Orientation.VERTICAL);
        AnchorPane.setTopAnchor(middleSplitPane, 0.0);
        AnchorPane.setBottomAnchor(middleSplitPane, 0.0);
        AnchorPane.setLeftAnchor(middleSplitPane, 0.0);
        AnchorPane.setRightAnchor(middleSplitPane, 0.0);
        middleSplitPane.getItems().addAll(new AnchorPane(), new AnchorPane());
        middleSplitPane.setDividerPositions(0.5);
        middle.getChildren().add(middleSplitPane);

        AnchorPane right = new AnchorPane();
        SplitPane rightSplitPane = new SplitPane();
        rightSplitPane.setDividerPositions(0.6);
        rightSplitPane.setOrientation(Orientation.VERTICAL);
        AnchorPane.setTopAnchor(rightSplitPane, 0.0);
        AnchorPane.setBottomAnchor(rightSplitPane, 0.0);
        AnchorPane.setLeftAnchor(rightSplitPane, 0.0);
        AnchorPane.setRightAnchor(rightSplitPane, 0.0);

        AnchorPane rightSplitPaneTop = new AnchorPane();

        Accordion propertiesAccordian = new Accordion();
        AnchorPane.setTopAnchor(propertiesAccordian, 0.0);
        AnchorPane.setBottomAnchor(propertiesAccordian, 0.0);
        AnchorPane.setLeftAnchor(propertiesAccordian, 0.0);
        AnchorPane.setRightAnchor(propertiesAccordian, 0.0);
        propertiesAccordian.getPanes().add(new TitledPane("Properties", new Pane()));
        propertiesAccordian.getPanes().add(new TitledPane("Layout", new Pane()));
        propertiesAccordian.getPanes().add(new TitledPane("Listeners", new Pane()));
        rightSplitPaneTop.getChildren().add(propertiesAccordian);

        AnchorPane rightSplitPaneBottom = new AnchorPane();

        rightSplitPane.getItems().addAll(rightSplitPaneTop, rightSplitPaneBottom);

        right.getChildren().add(rightSplitPane);

        mainContent.getItems().addAll(left, middle, right);
        mainContent.setDividerPositions(0.25, 0.75);

        borderPane.setCenter(mainContent);
        
        Scene scene = new Scene(root, 1024, 500);
        
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
