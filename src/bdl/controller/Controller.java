package bdl.controller;

import bdl.build.GObject;
import bdl.model.ComponentSettings;
import bdl.view.View;
import bdl.view.right.PropertyEditPane;
import bdl.view.right.properties.PanelProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class Controller {

    private final ArrayList<String> fieldNames;

    public Controller(final View view) {
        fieldNames = new ArrayList<>();

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
        view.middleTabPane.codeTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (view.middleTabPane.codeTab.isSelected()) {
                    StringBuilder code = new StringBuilder();
                    for (Node node : view.middleTabPane.viewPane.getChildren()) {
                        GObject gObj = (GObject) node;
                        for (PanelProperty property : gObj.getPanelProperties()) {
                            code.append(property.getJavaCode());
                        }
                        code.append('\n');
                    }
                    view.middleTabPane.codePane.setText(code.toString());
                }
            }
        });
        view.middleTabPane.previewTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (view.middleTabPane.previewTab.isSelected()) {
                    view.middleTabPane.getSelectionModel().select(0);
                }
            }
        });
        //End MiddlePanel

        //Start LeftPanel
        final ViewListeners viewListeners = new ViewListeners(view);

        view.leftPanel.leftList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    ComponentSettings componentSettings = view.leftPanel.leftList.getSelectionModel().getSelectedItem().getComponentSettings();
                    if (componentSettings != null) {
                        GObject newThing = null;

                        try {
                            Class panelPropertyClass = Class.forName("bdl.build." + componentSettings.getPackageName() + ".G" + componentSettings.getType());
                            Constructor constructor = panelPropertyClass.getConstructor();
                            newThing = (GObject)constructor.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Sets the default settings on the gObject and creates the property edit pane
                        final PropertyEditPane propertyEditPane = new PropertyEditPane(newThing, componentSettings, fieldNames);

                        //Could be null, e.g. ListView or ScrollPane
                        if (newThing != null) {
                            final Node newNode = (Node)newThing;

                            newNode.setOnMousePressed(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    viewListeners.onMousePressed(newNode, mouseEvent);
                                    view.rightPanel.propertyScroll.setContent(propertyEditPane);
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
        //End LeftPanel


    }

}
