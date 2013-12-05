package bdl;

import bdl.build.GObject;
import bdl.build.GType;
import bdl.build.scene.control.*;
import bdl.build.scene.shape.GCircle;
import bdl.build.scene.shape.GRectangle;
import bdl.view.components.ComponentViewReader;
import bdl.view.components.PropertyEditPane;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main class.
 */
public class Main extends Application {

    AnchorPane root;

    @Override
    public void start(final Stage stage) throws Exception {

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

        //Begin MenuBar
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem mItmClose = new MenuItem("Close");
        mItmClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
        final MenuItem mItmFullScreen = new MenuItem("Make Full Screen");
        mItmFullScreen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (stage.isFullScreen()) {
                    stage.setFullScreen(false);
                    mItmFullScreen.setText("Make Full Screen");
                } else {
                    stage.setFullScreen(true);
                    mItmFullScreen.setText("Exit Full Screen");
                }
            }
        });
        Menu menuEdit = new Menu("Edit");
        MenuItem mItmDelete = new MenuItem("Delete");
        Menu menuView = new Menu("View");
        final CheckMenuItem mItmHistory = new CheckMenuItem("Show History");
        final CheckMenuItem mItmHierarchy = new CheckMenuItem("Show Hierarchy");
        Menu menuHelp = new Menu("Help");
        MenuItem mItmAbout = new MenuItem("About");
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
        menuFile.getItems().addAll(mItmFullScreen, mItmClose);
        menuEdit.getItems().addAll(mItmDelete, mItmAbout);
        menuView.getItems().addAll(mItmHierarchy, mItmHistory);

        borderPane.setTop(menuBar);
        //End MenuBar

        SplitPane mainContent = new SplitPane();

        //Begin Left panel
        AnchorPane left = new AnchorPane();
        final SplitPane leftSplitPane = new SplitPane();
        leftSplitPane.setOrientation(Orientation.VERTICAL);
        AnchorPane.setLeftAnchor(leftSplitPane, 0.0);
        AnchorPane.setRightAnchor(leftSplitPane, 0.0);
        AnchorPane.setTopAnchor(leftSplitPane, 0.0);
        AnchorPane.setBottomAnchor(leftSplitPane, 0.0);

        //Begin left component list
        final ListView<GType> leftList = new ListView<>();
        leftList.getItems().add(GType.Button);
        leftList.getItems().add(GType.CheckBox);
        leftList.getItems().add(GType.ComboBox);
        leftList.getItems().add(GType.Label);
//        leftList.getItems().add(GType.ListView);
//        leftList.getItems().add(GType.Menu);
//        leftList.getItems().add(GType.MenuBar);
//        leftList.getItems().add(GType.MenuItem);
//        leftList.getItems().add(GType.ScrollPane);
//        leftList.getItems().add(GType.SplitPane);
        leftList.getItems().add(GType.TextArea);
        leftList.getItems().add(GType.TextField);
        leftList.getItems().add(GType.ToolBar);
//        leftList.getItems().add(GType.ImageView);
        leftList.getItems().add(GType.Circle);
        leftList.getItems().add(GType.Rectangle);

        final AnchorPane middleTop = new AnchorPane();
        final ViewListeners viewListeners = new ViewListeners(middleTop);
        leftList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    GType curType = leftList.getSelectionModel().getSelectedItem();
                    if (curType != null) {
                        GObject newThing = null;
                        switch (curType) {
                            case Button:
                                GButton newBtn = new GButton(viewListeners);
                                newBtn.setText("Test");
                                newBtn.setLayoutX(10);
                                newBtn.setLayoutY(10);
                                newThing = newBtn;
                                break;
                            case CheckBox:
                                GCheckBox newChkBox = new GCheckBox(viewListeners);
                                newChkBox.setLayoutX(10);
                                newChkBox.setLayoutY(10);
                                newThing = newChkBox;
                                break;
                            case ComboBox:
                                GComboBox newCBox = new GComboBox(viewListeners);
                                newCBox.setLayoutX(10);
                                newCBox.setLayoutY(10);
                                newThing = newCBox;
                                break;
                            case Label:
                                GLabel newLbl = new GLabel(viewListeners);
                                newLbl.setLayoutX(10);
                                newLbl.setLayoutY(10);
                                newLbl.setText("Label");
                                newThing = newLbl;
                                break;
                            case ListView:
                                //Do nothing, don't want to deal with this just yet
                                break;
                            case Menu:
                                //Can't add to AnchorPane
                                break;
                            case MenuBar:
                                //Can't add to AnchorPane
                                break;
                            case MenuItem:
                                //Can't add to AnchorPane
                                break;
                            case ScrollPane:
                                //Do nothing, don't want to deal with this just yet
                                break;
                            case SplitPane:
                                //Do nothing, don't want to deal with this just yet
                                break;
                            case TextArea:
                                GTextArea newTxtArea = new GTextArea(viewListeners);
                                newTxtArea.setText("Text Area Text!");
                                newTxtArea.setLayoutX(10);
                                newTxtArea.setLayoutY(10);
                                newThing = newTxtArea;
                                break;
                            case TextField:
                                GTextField newTxtField = new GTextField(viewListeners);
                                newTxtField.setText("Text Field Text!");
                                newTxtField.setLayoutX(10);
                                newTxtField.setLayoutY(10);
                                newThing = newTxtField;
                                break;
                            case ToolBar:
                                GToolBar newToolBar = new GToolBar(viewListeners);
                                newToolBar.setLayoutX(10);
                                newToolBar.setLayoutY(10);
                                newThing = newToolBar;
                                break;
                            case ImageView:
                                //Do nothing for the minute
                                break;
                            case Circle:
                                GCircle newCircle = new GCircle(viewListeners);
                                newCircle.setStrokeWidth(5);
                                newCircle.setStroke(Color.RED);
                                newCircle.setRadius(10);
                                newCircle.setFill(Color.PINK);
                                newCircle.setLayoutX(50);
                                newCircle.setLayoutY(50);
                                newThing = newCircle;
                                break;
                            case Rectangle:
                                GRectangle newRectangle = new GRectangle(viewListeners);
                                newRectangle.setFill(Color.DARKORCHID);
                                newRectangle.setWidth(100);
                                newRectangle.setHeight(100);
                                newRectangle.setLayoutX(100);
                                newRectangle.setLayoutY(100);
                                newThing = newRectangle;
                                break;
                        }

                        //Could be null, e.g. ListView or ScrollPane
                        if (newThing != null) {
                            middleTop.getChildren().add((Node) newThing);
                        }
                    }
                    leftList.getSelectionModel().select(-1);
                }
            }
        });

        //End left component list

        //Begin left hierarchy panel
        TreeItem<String> treeRoot = new TreeItem<>("AnchorPane");
        treeRoot.setExpanded(true);
        treeRoot.getChildren().add(new TreeItem<>("Button"));
        treeRoot.getChildren().add(new TreeItem<>("Button"));
        treeRoot.getChildren().add(new TreeItem<>("Button"));
        treeRoot.getChildren().add(new TreeItem<>("Button"));

        TreeView<String> leftTreeView = new TreeView<>(treeRoot);

        final TitledPane leftTitledPane = new TitledPane("Hierarchy", leftTreeView);
        leftTitledPane.setCollapsible(false);
        //End left hierarchy panel

        leftSplitPane.getItems().addAll(leftList);
        leftSplitPane.setDividerPosition(0, 0.6);
        leftSplitPane.setMinWidth(225);

        mItmHierarchy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (mItmHierarchy.isSelected()) {
                    leftSplitPane.getItems().add(leftTitledPane);
                    leftSplitPane.setDividerPosition(0, 0.6);
                } else {
                    leftSplitPane.getItems().remove(leftTitledPane);
                }
            }
        });

        left.getChildren().add(leftSplitPane);

        //Begin middle panel
        AnchorPane middle = new AnchorPane();
        final TabPane middleTabPane = new TabPane();
        middleTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab viewTab = new Tab("    Editor View    ");
        Tab codeTab = new Tab("    Code View    ");
        final Tab previewTab = new Tab("    Preview GUI    ");

        previewTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (previewTab.isSelected()) {
                    middleTabPane.getSelectionModel().select(0);
                    System.out.println("XXX");
                }
            }
        });
        middleTabPane.getTabs().addAll(viewTab, codeTab, previewTab);
        AnchorPane.setTopAnchor(middleTabPane, 0.0);
        AnchorPane.setBottomAnchor(middleTabPane, 0.0);
        AnchorPane.setLeftAnchor(middleTabPane, 0.0);
        AnchorPane.setRightAnchor(middleTabPane, 0.0);

        GButton b = new GButton(viewListeners);
        b.setText("Button Text");
        b.setLayoutX(100);
        b.setLayoutY(100);
        middleTop.getChildren().add(b);
        viewTab.setContent(middleTop);
        middle.getChildren().add(middleTabPane);
        //End middle panel

        //Begin right panel
        AnchorPane right = new AnchorPane();
        final SplitPane rightSplitPane = new SplitPane();
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

        final AnchorPane rightSplitPaneBottom = new AnchorPane();

        rightSplitPane.getItems().addAll(rightSplitPaneTop);
        rightSplitPane.setDividerPosition(0, 0.6);
        rightSplitPane.setMinWidth(225);
        

        mItmHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (mItmHistory.isSelected()) {
                    rightSplitPane.getItems().add(rightSplitPaneBottom);
                    rightSplitPane.setDividerPosition(0, 0.6);
                } else {
                    rightSplitPane.getItems().remove(rightSplitPaneBottom);
                }
            }
        });

        right.getChildren().add(rightSplitPane);

        mainContent.getItems().addAll(left, middle, right);
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
