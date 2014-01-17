package bdl.controller;

import bdl.build.CodeGenerator;
import bdl.build.GObject;
import bdl.model.ComponentSettings;
import bdl.view.View;
import bdl.view.left.ComponentMenuItem;
import bdl.view.right.PropertyEditPane;
import bdl.view.right.properties.PanelProperty;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javafx.scene.shape.Circle;

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
                    //Grab imports & generate code
                    HashMap<String, String> imports = new HashMap<>();
                    for (ComponentMenuItem componentMenuItem : view.leftPanel.leftList.getItems()) {
                        ComponentSettings componentSettings = componentMenuItem.getComponentSettings();
                        imports.put(componentSettings.getType(), componentSettings.getPackageName());
                    }
                    String code = CodeGenerator.generateCode(view.middleTabPane.viewPane, imports);

                    //Write .java file
                    try {
                        BufferedOutputStream cssOutput = new BufferedOutputStream(new FileOutputStream(view.middleTabPane.viewPane.getClassName() + ".java"));
                        cssOutput.write(code.getBytes());
                        cssOutput.flush();
                        cssOutput.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Compile class
                    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                    if (compiler == null) {
                        throw new RuntimeException("Jar could not be created as Java version requires javac.");
                    }
                    StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

                    Iterable<? extends JavaFileObject> compilationUnits1 =
                            fileManager.getJavaFileObjectsFromFiles(Arrays.asList(new File(view.middleTabPane.viewPane.getClassName() + ".java")));

                    compiler.getTask(null, fileManager, null, null, null, compilationUnits1).call();

                    try {
                        fileManager.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //Load & run class
                    try {
                        URL[] urls = new URL[]{new File(".").toURI().toURL()};
                        URLClassLoader ucl = new URLClassLoader(urls);
                        Class guiClass = Class.forName(view.middleTabPane.viewPane.getClassName(), false, ucl);
                        Method main = guiClass.getMethod("start", Stage.class);
                        Object obj = guiClass.newInstance();
                        main.invoke(obj, new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Delete created files
                    new File(view.middleTabPane.viewPane.getClassName() + ".java").delete();
                    new File(view.middleTabPane.viewPane.getClassName() + ".class").delete();

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
                            newThing = (GObject) constructor.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Sets the default settings on the gObject and creates the property edit pane
                        final PropertyEditPane propertyEditPane = new PropertyEditPane(newThing, componentSettings, fieldNames);

                        //Could be null, e.g. ListView or ScrollPane
                        if (newThing != null) {
                            final Node newNode = (Node) newThing;

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
                            if (newNode instanceof Circle) {
                                newNode.setLayoutX((newNode.getLayoutBounds().getWidth() / 2) + 4);
                                newNode.setLayoutY((newNode.getLayoutBounds().getWidth() / 2) + 4);
                            }
                            else {
                                newNode.setLayoutX(newNode.getLayoutX() + 4);
                                newNode.setLayoutY(newNode.getLayoutY() + 4);
                            }
                        }
                    }
                    view.leftPanel.leftList.getSelectionModel().select(-1);
                }
            }
        });
        //End LeftPanel


    }
}
