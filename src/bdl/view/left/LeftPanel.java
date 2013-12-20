package bdl.view.left;

import bdl.Main;
import bdl.ViewListeners;
import bdl.build.GObject;
import bdl.build.GType;
import bdl.build.scene.control.*;
import bdl.build.scene.shape.GCircle;
import bdl.build.scene.shape.GRectangle;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class LeftPanel extends SplitPane {

    public static TitledPane hierarchyPane;

    public LeftPanel(final ViewListeners viewListeners) {
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

        leftList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    GType curType = leftList.getSelectionModel().getSelectedItem();
                    if (curType != null) {
                        GObject newThing = null;
                        switch (curType) {
                            case Button:
                                GButton newBtn = new GButton();
                                newBtn.setText("Test");
                                newBtn.setLayoutX(10);
                                newBtn.setLayoutY(10);
                                newThing = newBtn;
                                break;
                            case CheckBox:
                                GCheckBox newChkBox = new GCheckBox();
                                newChkBox.setLayoutX(10);
                                newChkBox.setLayoutY(10);
                                newThing = newChkBox;
                                break;
                            case ComboBox:
                                GComboBox newCBox = new GComboBox();
                                newCBox.setLayoutX(10);
                                newCBox.setLayoutY(10);
                                newThing = newCBox;
                                break;
                            case Label:
                                GLabel newLbl = new GLabel();
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
                                GTextArea newTxtArea = new GTextArea();
                                newTxtArea.setText("Text Area Text!");
                                newTxtArea.setLayoutX(10);
                                newTxtArea.setLayoutY(10);
                                newThing = newTxtArea;
                                break;
                            case TextField:
                                GTextField newTxtField = new GTextField();
                                newTxtField.setText("Text Field Text!");
                                newTxtField.setLayoutX(10);
                                newTxtField.setLayoutY(10);
                                newThing = newTxtField;
                                break;
                            case ToolBar:
                                GToolBar newToolBar = new GToolBar();
                                newToolBar.setLayoutX(10);
                                newToolBar.setLayoutY(10);
                                newThing = newToolBar;
                                break;
                            case ImageView:
                                //Do nothing for the minute
                                break;
                            case Circle:
                                GCircle newCircle = new GCircle();
                                newCircle.setStrokeWidth(5);
                                newCircle.setStroke(Color.RED);
                                newCircle.setRadius(10);
                                newCircle.setFill(Color.PINK);
                                newCircle.setLayoutX(50);
                                newCircle.setLayoutY(50);
                                newThing = newCircle;
                                break;
                            case Rectangle:
                                GRectangle newRectangle = new GRectangle();
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
                            final Node newNode = (Node)newThing;

                            newNode.setOnMousePressed(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    viewListeners.onMouseClicked(newNode);
                                    viewListeners.onMousePressed(newNode, mouseEvent);
                                }
                            });
                            newNode.setOnMouseReleased(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    viewListeners.onMouseReleased(newNode, mouseEvent);
                                }
                            });
                            newNode.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    viewListeners.onMouseDragged(newNode, mouseEvent);
                                }
                            });

                            Main.middleTop.getChildren().add(newNode);
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

        hierarchyPane = new TitledPane("Hierarchy", leftTreeView);
        hierarchyPane.setCollapsible(false);
        //End left hierarchy panel

        getItems().addAll(leftList);
    }

}
