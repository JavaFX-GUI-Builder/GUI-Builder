package bdl.controller;

import bdl.build.CodeGenerator;
import bdl.build.GObject;
import bdl.lang.LabelGrabber;
import bdl.model.ComponentSettings;
import bdl.view.View;
import bdl.view.left.ComponentMenuItem;
import bdl.view.right.PropertyEditPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Circle;

public class Controller {

    private final ArrayList<String> fieldNames;
    private static final DataFormat cmjFormat = new DataFormat("fmjFormat");

    public Controller(final View view) {
        final ViewListeners viewListeners = new ViewListeners(view);
        view.viewListeners = viewListeners;
        fieldNames = new ArrayList<>();

        //Start Top Panel
        view.topPanel.mItmLoadFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.middleTabPane.viewPane.getChildren().clear();

                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("FXML files (*.fxml)", "*.fxml");
                fileChooser.getExtensionFilters().add(filter);

                File file = fileChooser.showOpenDialog(view.getStage());

                if (file != null) {
                    try {
                        Parent parent = FXMLLoader.load(file.toURI().toURL());

                        GUIHelper.setBounds(view.middleTabPane.viewPane, view.middleTabPane.viewPaneDecorator, parent.prefWidth(0), parent.prefHeight(0));
                        String className = parent.getId();
                        if (className != null && !className.isEmpty()) {
                            view.middleTabPane.viewPane.setClassName(className);
                        }

                        for (Node node : parent.getChildrenUnmodifiable()) {

                            for (ComponentMenuItem componentMenuItem : view.leftPanel.leftList.getItems()) {
                                ComponentSettings componentSettings = componentMenuItem.getComponentSettings();
                                try {
                                    if (componentSettings.getType().equals(node.getClass().getSimpleName())) {
                                        Class componentClass = Class.forName("bdl.build." + componentSettings.getPackageName() + ".G" + componentSettings.getType());
                                        Constructor constructor = componentClass.getConstructor();
                                        GObject newThing = (GObject) constructor.newInstance();
                                        newThing.setFieldName(node.getId());

                                        addGObject(newThing, componentSettings, view, viewListeners, node, -1, -1);

                                        break;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        view.topPanel.mItmSaveFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("FXML files (*.fxml)", "*.fxml");
                fileChooser.getExtensionFilters().add(filter);

                File file = fileChooser.showSaveDialog(view.getStage());

                if (file != null) {
                    if (!file.getName().toLowerCase().endsWith(".fxml")) {
                        file = new File(file.getAbsoluteFile() + ".fxml");
                    }

                    try {
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write(CodeGenerator.generateFXMLCode(view.middleTabPane.viewPane, null));//We don't need the imports, for the minute...
                        fileWriter.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

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
        view.middleTabPane.viewPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.rightPanel.propertyScroll.setContent(new PropertyEditPane());
                viewListeners.resetOutline();
                mouseEvent.consume();
            }
        });

        view.middleTabPane.codeTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (view.middleTabPane.codeTab.isSelected()) {
                    HashMap<String, String> imports = new HashMap<>();
                    for (ComponentMenuItem componentMenuItem : view.leftPanel.leftList.getItems()) {
                        ComponentSettings componentSettings = componentMenuItem.getComponentSettings();
                        imports.put(componentSettings.getType(), componentSettings.getPackageName());
                    }
                    String code = CodeGenerator.generateJavaCode(view.middleTabPane.viewPane, imports);
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
                    String code = CodeGenerator.generateJavaCode(view.middleTabPane.viewPane, imports);

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

                        addGObject(newThing, componentSettings, view, viewListeners, null, -1, -1);

                    }
                    view.leftPanel.leftList.getSelectionModel().select(-1);
                }
            }
        });
        //End LeftPanel

        view.middleTabPane.viewPane.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                t.acceptTransferModes(TransferMode.ANY);
            }
        });

        view.middleTabPane.viewPane.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                ComponentMenuItem cmj = view.leftPanel.leftList.getSelectionModel().getSelectedItem();
                ComponentSettings componentSettings = cmj.getComponentSettings();
                if (componentSettings != null) {
                    GObject newThing = null;

                    try {
                        Class panelPropertyClass = Class.forName("bdl.build." + componentSettings.getPackageName() + ".G" + componentSettings.getType());
                        Constructor constructor = panelPropertyClass.getConstructor();
                        newThing = (GObject) constructor.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    addGObject(newThing, componentSettings, view, viewListeners, null, (int) t.getX(), (int) t.getY());

                }
                view.leftPanel.leftList.getSelectionModel().select(-1);
            }
        });
    }

    //x and y are initial layout positions. To be used only with drag and drop.
    private void addGObject(GObject newThing, ComponentSettings componentSettings, final View view, final ViewListeners viewListeners, Node settingsNode, int x, int y) {
        //Sets the default settings on the gObject and creates the property edit pane
        final PropertyEditPane propertyEditPane = new PropertyEditPane(newThing, componentSettings, fieldNames, view.middleTabPane.viewPane, settingsNode);

        newThing.setPEP(propertyEditPane);

        final Node newNode = (Node) newThing;

        newNode.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds t, Bounds t1) {
                viewListeners.redraw(newNode);
            }
        });

        newNode.layoutXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                viewListeners.redraw(newNode);
            }
        });

        newNode.layoutYProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                viewListeners.redraw(newNode);
            }
        });

        newNode.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                viewListeners.onMousePressed(newNode, mouseEvent);
                view.rightPanel.propertyScroll.setContent(propertyEditPane);
                for (TreeItem<GObject> ti : view.leftPanel.hierPane.treeRoot.getChildren()) {
                    if (ti.getValue().getFieldName().equals(((GObject) newNode).getFieldName())) {
                        view.leftPanel.hierPane.treeView.getSelectionModel().select(ti);
                    }
                }
                mouseEvent.consume();
            }
        });
        newNode.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                viewListeners.onMouseReleased(newNode, mouseEvent);
                mouseEvent.consume();
            }
        });
        newNode.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                viewListeners.onMouseDragged(newNode, mouseEvent);
                mouseEvent.consume();
            }
        });

        newNode.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getButton().equals(MouseButton.SECONDARY)) {
                    ContextMenu popUp = new ContextMenu();
                    MenuItem button = new MenuItem(LabelGrabber.getLabel("delete.node.text"));
                    popUp.getItems().add(button);
                    popUp.show(newNode, Side.RIGHT, 0, 0);
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            view.middleTabPane.viewPane.getChildren().remove(newNode);
                            view.rightPanel.propertyScroll.setContent(new PropertyEditPane());
                            view.currentlySelected = null;
                            viewListeners.resetOutline();
                            for (TreeItem<GObject> ti : view.leftPanel.hierPane.treeRoot.getChildren()) {
                                if (ti.getValue().getFieldName().equals(((GObject) newNode).getFieldName())) {
                                    view.leftPanel.hierPane.treeRoot.getChildren().remove(ti);
                                }
                            }
                        }
                    });
                }
            }
        });

        view.rightPanel.propertyScroll.setContent(propertyEditPane);
        view.middleTabPane.viewPane.getChildren().add(newNode);
        view.currentlySelected = (GObject) newNode;
        view.leftPanel.hierPane.add(null, (GObject) newNode);

        if (settingsNode == null) {
            if (newNode instanceof Circle) {
                newNode.setLayoutX((newNode.getLayoutBounds().getWidth() / 2) + 4);
                newNode.setLayoutY((newNode.getLayoutBounds().getWidth() / 2) + 4);
            } else {
                newNode.setLayoutX(newNode.getLayoutX() + 4);
                newNode.setLayoutY(newNode.getLayoutY() + 4);
            }
        }

        if (x > 0 && y > 0) {
            newNode.setLayoutX(x);
            newNode.setLayoutY(y);
        }
    }
}
