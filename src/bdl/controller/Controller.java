package bdl.controller;

import bdl.build.CodeGenerator;
import bdl.build.GObject;
import bdl.model.ComponentSettings;
import bdl.view.View;
import bdl.view.left.ComponentMenuItem;
import bdl.view.right.PropertyEditPane;
import bdl.view.right.properties.PanelProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

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
                    HashMap<String, String> imports = new HashMap<>();
                    for (ComponentMenuItem componentMenuItem : view.leftPanel.leftList.getItems()) {
                        ComponentSettings componentSettings = componentMenuItem.getComponentSettings();
                        imports.put(componentSettings.getType(), componentSettings.getPackageName());
                    }
                    String code = CodeGenerator.generateCode(view.middleTabPane.viewPane, imports);
                    view.middleTabPane.codePane.setText(code);
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

                            newNode.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    viewListeners.onMousePressed(newNode, mouseEvent);
                                    view.rightPanel.propertyScroll.setContent(propertyEditPane);
                                }
                            });
                            newNode.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    viewListeners.onMouseReleased(newNode, mouseEvent);
                                }
                            });
                            newNode.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
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
