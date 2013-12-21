package bdl.controller;

import bdl.Main;
import bdl.build.GObject;
import bdl.build.GType;
import bdl.build.scene.control.*;
import bdl.build.scene.shape.GCircle;
import bdl.build.scene.shape.GRectangle;
import bdl.view.View;
import bdl.view.left.LeftPanel;
import bdl.view.right.RightPanel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {

    public Controller(final View view) {

        //Start Top Panel
        view.topPanel.mItmHierarchy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (view.topPanel.mItmHierarchy.isSelected()) {
                    view.leftPanel.getItems().add(view.leftPanel.hierarchyPane);
                    view.leftPanel.setDividerPosition(0, 0.6);
                } else {
                    view.leftPanel.getItems().remove(view.leftPanel.hierarchyPane);
                }
            }
        });

        view.topPanel.mItmHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (view.topPanel.mItmHistory.isSelected()) {
                    view.rightPanel.getItems().add(view.rightPanel.rightSplitPaneBottom);
                    view.rightPanel.setDividerPosition(0, 0.6);
                } else {
                    view.rightPanel.getItems().remove(view.rightPanel.rightSplitPaneBottom);
                }
            }
        });
        //End TopPanel

        //Start MiddlePanel
        view.middleTabPane.previewTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (view.middleTabPane.previewTab.isSelected()) {
                    view.middleTabPane.getSelectionModel().select(0);
                }
            }
        });

        final ViewListeners viewListeners = new ViewListeners(view);

        view.leftPanel.leftList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    GType curType = view.leftPanel.leftList.getSelectionModel().getSelectedItem();
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

                            view.middleTabPane.viewPane.getChildren().add(newNode);
                        }
                    }
                    view.leftPanel.leftList.getSelectionModel().select(-1);
                }
            }
        });
        //End MiddlePanel



    }

}
