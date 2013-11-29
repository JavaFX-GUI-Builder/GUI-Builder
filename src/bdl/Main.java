package bdl;

import bdl.build.scene.control.GButton;
import bdl.view.components.ComponentViewReader;
import bdl.view.components.PropertyEditPane;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *  Main class.
 */
public class Main extends Application {

    AnchorPane root;
    
    @Override
    public void start(Stage stage) throws Exception {

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

        //Begin MenuBar
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
        //End MenuBar

        SplitPane mainContent = new SplitPane();

        //Begin Left panel
        AnchorPane left = new AnchorPane();
        SplitPane leftSplitPane = new SplitPane();
        leftSplitPane.setOrientation(Orientation.VERTICAL);
        AnchorPane.setLeftAnchor(leftSplitPane, 0.0);
        AnchorPane.setRightAnchor(leftSplitPane, 0.0);
        AnchorPane.setTopAnchor(leftSplitPane, 0.0);
        AnchorPane.setBottomAnchor(leftSplitPane, 0.0);

        //Begin left component list
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
        //End left component list

        //Begin left hierarchy panel
        TreeItem<String> treeRoot = new TreeItem<>("AnchorPane");
        treeRoot.setExpanded(true);
        treeRoot.getChildren().add(new TreeItem<>("Button"));
        treeRoot.getChildren().add(new TreeItem<>("Button"));
        treeRoot.getChildren().add(new TreeItem<>("Button"));
        treeRoot.getChildren().add(new TreeItem<>("Button"));

        TreeView<String> leftTreeView = new TreeView<>(treeRoot);

        TitledPane leftTitledPane = new TitledPane("Hierarchy", leftTreeView);
        leftTitledPane.setCollapsible(false);
        //End left hierarchy panel

        leftSplitPane.getItems().addAll(leftList, leftTitledPane);
        left.getChildren().add(leftSplitPane);

        //Begin middle panel
        AnchorPane middle = new AnchorPane();
        SplitPane middleSplitPane = new SplitPane();
        middleSplitPane.setOrientation(Orientation.VERTICAL);
        AnchorPane.setTopAnchor(middleSplitPane, 0.0);
        AnchorPane.setBottomAnchor(middleSplitPane, 0.0);
        AnchorPane.setLeftAnchor(middleSplitPane, 0.0);
        AnchorPane.setRightAnchor(middleSplitPane, 0.0);

        AnchorPane middleTop = new AnchorPane();
        ViewListeners viewListeners = new ViewListeners(middleTop);
        GButton b = new GButton(viewListeners);
        b.setText("Button Text");
        b.setLayoutX(10);
        b.setLayoutY(10);
        middleTop.getChildren().add(b);

        middleSplitPane.getItems().addAll(middleTop, new AnchorPane());
        middleSplitPane.setDividerPositions(0.75);
        middle.getChildren().add(middleSplitPane);
        //End middle panel

        //Begin right panel
        AnchorPane right = new AnchorPane();
        SplitPane rightSplitPane = new SplitPane();
        rightSplitPane.setDividerPositions(0.7);
        rightSplitPane.setOrientation(Orientation.VERTICAL);
        AnchorPane.setTopAnchor(rightSplitPane, 0.0);
        AnchorPane.setBottomAnchor(rightSplitPane, 0.0);
        AnchorPane.setLeftAnchor(rightSplitPane, 0.0);
        AnchorPane.setRightAnchor(rightSplitPane, 0.0);
        //End right panel

        AnchorPane rightSplitPaneTop = new AnchorPane();

        //Begin right properties panel
        ComponentViewReader.parseSettings();
        PropertyEditPane pep = new PropertyEditPane();
        ScrollPane propertyScroll = new ScrollPane();
        propertyScroll.setContent(pep);
        AnchorPane.setTopAnchor(propertyScroll, 0.0);
        AnchorPane.setBottomAnchor(propertyScroll, 0.0);
        AnchorPane.setLeftAnchor(propertyScroll, 0.0);
        AnchorPane.setRightAnchor(propertyScroll, 0.0);
        rightSplitPaneTop.getChildren().add(propertyScroll);
        //End right properties panel

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
