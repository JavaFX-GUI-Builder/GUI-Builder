package bdl.controller;

import bdl.Interface;
import bdl.build.CodeGenerator;
import bdl.build.GObject;
import bdl.lang.LabelGrabber;
import bdl.model.ComponentSettings;
import bdl.model.ComponentSettingsStore;
import bdl.model.history.HistoryItem;
import bdl.model.history.HistoryListener;
import bdl.model.history.HistoryManager;
import bdl.model.history.update.HistoryItemDescription;
import bdl.model.history.update.HistoryUpdate;
import bdl.model.selection.SelectionListener;
import bdl.model.selection.SelectionManager;
import bdl.view.View;
import bdl.view.left.ComponentMenuItem;
import bdl.view.left.hierarchy.HierarchyTreeItem;
import bdl.view.right.PropertyEditPane;
import bdl.view.right.history.HistoryPanelItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

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
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Cursor;

public class Controller {

    public View view;
    private ComponentSettingsStore componentSettingsStore;
    private ViewListeners viewListeners;
    private ArrayList<String> fieldNames;
    public HistoryManager historyManager;
    public SelectionManager selectionManager;
    public static boolean historyPause = false;

    public Controller(View view, ComponentSettingsStore componentSettingsStore) {
        this.view = view;
        this.componentSettingsStore = componentSettingsStore;
        fieldNames = new ArrayList<>();
        historyManager = new HistoryManager();
        viewListeners = new ViewListeners(historyManager);
        selectionManager = new SelectionManager();

        setupLeftPanel();
        setupMiddlePanel();
        setupRightPanel();
        setupTopPanel();
    }

    private void setupTopPanel() {
        view.topPanel.mItmLoadFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("FXML files (*.fxml)", "*.fxml");
                fileChooser.getExtensionFilters().add(filter);

                File file = fileChooser.showOpenDialog(view.getStage());

                if (file != null) {

                    view.middleTabPane.viewPane.getChildren().clear();
                    selectionManager.clearSelection();
                    historyManager.clearHistory();

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
                                        historyPause = true;
                                        Class componentClass = Class.forName("bdl.build." + componentSettings.getPackageName() + ".G" + componentSettings.getType());
                                        Constructor constructor = componentClass.getConstructor();
                                        GObject newThing = (GObject) constructor.newInstance();
                                        newThing.setFieldName(node.getId());

                                        addGObject(newThing, componentSettings, view, viewListeners, node, -1, -1);
                                        historyPause = false;
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

        //Add HistoryListener for the Undo/Redo menu items in the Edit menu
        historyManager.addHistoryListener(new HistoryListener() {
            @Override
            public void historyUpdated(final HistoryUpdate historyUpdate) {
                //Undo MenuItem
                if (historyUpdate.canUndo()) {
                    view.topPanel.mItmUndo.setDisable(false);
                    view.topPanel.mItmUndo.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            historyManager.updateTo(historyUpdate.getCurrentIndex() - 1);
                        }
                    });
                } else {
                    view.topPanel.mItmUndo.setDisable(true);
                }

                //Redo MenuItem
                if (historyUpdate.canRedo()) {
                    view.topPanel.mItmRedo.setDisable(false);
                    view.topPanel.mItmRedo.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            historyManager.updateTo(historyUpdate.getCurrentIndex() + 1);
                        }
                    });
                } else {
                    view.topPanel.mItmRedo.setDisable(true);
                }
            }
        });

        //Edit Menu > Delete button functionality
        selectionManager.addSelectionListener(new SelectionListener() {
            @Override
            public void updateSelected(final GObject gObject) {
                view.topPanel.mItmDelete.setDisable(false);
                view.topPanel.mItmDelete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        view.middleTabPane.viewPane.getChildren().remove(gObject);
                        selectionManager.clearSelection();
                        historyManager.addHistory(new HistoryItem() {
                            @Override
                            public void restore() {
                                view.middleTabPane.viewPane.getChildren().remove(gObject);
                                selectionManager.clearSelection();
                            }

                            @Override
                            public void revert() {
                                view.middleTabPane.viewPane.getChildren().add((Node) gObject);
                                selectionManager.updateSelected(gObject);
                            }

                            @Override
                            public String getAppearance() {
                                return gObject.getFieldName() + " deleted";
                            }
                        });
                    }
                });
                view.topPanel.mItmClearAll.setDisable(false);
                view.topPanel.mItmClearAll.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        final List<Node> list = new ArrayList<>();
                        list.addAll(view.middleTabPane.viewPane.getChildren());
                        view.middleTabPane.viewPane.getChildren().clear();
                        selectionManager.clearSelection();

                        historyManager.addHistory(new HistoryItem() {
                            @Override
                            public void restore() {
                                for (Node n : list) {
                                    view.middleTabPane.viewPane.getChildren().remove(n);
                                }
                                selectionManager.clearSelection();
                            }

                            @Override
                            public void revert() {
                                for (Node n : list) {
                                    view.middleTabPane.viewPane.getChildren().add(n);
                                    selectionManager.updateSelected((GObject) n);
                                }
                            }

                            @Override
                            public String getAppearance() {
                                return ("Clear All");
                            }
                        });
                    }
                });
            }

            @Override
            public void clearSelection() {
                view.topPanel.mItmDelete.setDisable(true);
                view.topPanel.mItmClearAll.setDisable(true);
            }
        });

        view.topPanel.mItmHierarchy.setOnAction(
                new EventHandler<ActionEvent>() {
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

        view.topPanel.mItmHistory.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (view.topPanel.mItmHistory.isSelected()) {
                    view.rightPanel.getItems().add(view.rightPanel.historyPanel);
                    view.rightPanel.setDividerPosition(0, 0.6);
                } else {
                    view.rightPanel.getItems().remove(view.rightPanel.historyPanel);
                }
            }
        });

//        view.topPanel.mItmDebugMsg.setOnAction(
//                new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent t) {
//                Interface.TestWriteMessage("Debug test from GUI Builder!");
//            }
//        });
    }

    private void setupLeftPanel() {
        view.leftPanel.leftList.setCellFactory(new Callback<ListView<ComponentMenuItem>, ListCell<ComponentMenuItem>>() {
            @Override
            public ListCell<ComponentMenuItem> call(ListView<ComponentMenuItem> list) {
                return new LeftListCellFactory(view);
            }
        });


        for (ComponentSettings componentSettings : componentSettingsStore.getComponents()) {
            String type = componentSettings.getType();
            ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/bdl/icons/" + componentSettings.getIcon())));
            view.leftPanel.leftList.getItems().add(new ComponentMenuItem(type, icon, componentSettings));
        }

        view.leftPanel.leftList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    ComponentSettings componentSettings = view.leftPanel.leftList.getSelectionModel().getSelectedItem().getComponentSettings();
                    if (componentSettings != null) {
                        GObject newThing = null;

                        historyPause = true;
                        try {
                            Class panelPropertyClass = Class.forName("bdl.build." + componentSettings.getPackageName() + ".G" + componentSettings.getType());
                            Constructor constructor = panelPropertyClass.getConstructor();
                            newThing = (GObject) constructor.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        addGObject(newThing, componentSettings, view, viewListeners, null, -1, -1);
                        historyPause = false;
                    }
                    view.leftPanel.leftList.getSelectionModel().select(-1);
                }
            }
        });

        view.leftPanel.hierarchyPane.treeRoot = new TreeItem<>(new HierarchyTreeItem(view.middleTabPane.viewPane, view, selectionManager, historyManager));
        view.leftPanel.hierarchyPane.treeView.setRoot(view.leftPanel.hierarchyPane.treeRoot);
        view.leftPanel.hierarchyPane.treeRoot.setExpanded(true);
        view.leftPanel.hierarchyPane.treeView.setShowRoot(true);

        view.leftPanel.hierarchyPane.treeView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TreeItem<HierarchyTreeItem> item = view.leftPanel.hierarchyPane.treeView.getSelectionModel().getSelectedItem();
                if (item != null) {
                    selectionManager.updateSelected(item.getValue().getGObject());
                }
            }
        });

        //Add listener to node list to update hierarchy pane
        view.middleTabPane.viewPane.getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> change) {
                TreeItem<HierarchyTreeItem> root = view.leftPanel.hierarchyPane.treeRoot;
                root.getChildren().clear();

                ObservableList nodes = change.getList();
                // Add backwards so that they appear in the correct order
                for (int i = nodes.size() - 1; i >= 0; i--) {
                    root.getChildren().add(new TreeItem<>(new HierarchyTreeItem((GObject) nodes.get(i), view, selectionManager, historyManager)));
                }
            }
        });

        //Add selection handlers for Hierarchy Pane
        selectionManager.addSelectionListener(new SelectionListener() {
            @Override
            public void updateSelected(GObject gObject) {
                for (TreeItem<HierarchyTreeItem> ti : view.leftPanel.hierarchyPane.treeRoot.getChildren()) {
                    if (ti.getValue().getGObject().getFieldName().equals(gObject.getFieldName())) {
                        view.leftPanel.hierarchyPane.treeView.getSelectionModel().select(ti);
                    }
                }
            }

            @Override
            public void clearSelection() {
                view.leftPanel.hierarchyPane.treeView.getSelectionModel().select(-1);
            }
        });
    }

    private void setupMiddlePanel() {

        // Add selection handlers for the outline
        selectionManager.addSelectionListener(new SelectionListener() {
            @Override
            public void updateSelected(GObject gObject) {
                Node node = (Node) gObject;
                Rectangle outline = view.middleTabPane.outline;
                outline.setVisible(true);

                double nodeX = node.getLayoutX();
                double nodeY = node.getLayoutY();
                Bounds bounds = node.getLayoutBounds();
                double nodeW = bounds.getWidth();
                double nodeH = bounds.getHeight();
                if (node instanceof Circle) {
                    outline.setLayoutX(nodeX - 4 - (nodeW / 2));
                    outline.setLayoutY(nodeY - 4 - (nodeH / 2));
                } else if (node instanceof Rectangle) {
                    Rectangle r = (Rectangle) node;
                    outline.setLayoutX(nodeX - 4 - (r.getStrokeWidth() / 2));
                    outline.setLayoutY(nodeY - 4 - (r.getStrokeWidth() / 2));
                } else {
                    outline.setLayoutX(nodeX - 4);
                    outline.setLayoutY(nodeY - 4);
                }
                outline.setWidth(nodeW + 8);
                outline.setHeight(nodeH + 8);
            }

            @Override
            public void clearSelection() {
                view.middleTabPane.outline.setVisible(false);
            }
        });

        view.middleTabPane.viewPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.middleTabPane.viewPane.requestFocus();
                selectionManager.clearSelection();
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

                    historyPause = true;
                    try {
                        Class panelPropertyClass = Class.forName("bdl.build." + componentSettings.getPackageName() + ".G" + componentSettings.getType());
                        Constructor constructor = panelPropertyClass.getConstructor();
                        newThing = (GObject) constructor.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    addGObject(newThing, componentSettings, view, viewListeners, null, (int) t.getX(), (int) t.getY());
                    historyPause = false;
                }
                view.leftPanel.leftList.getSelectionModel().select(-1);
            }
        });
    }

    private void setupRightPanel() {

        // Add selection handlers for the Property Edit Pane
        selectionManager.addSelectionListener(new SelectionListener() {
            @Override
            public void updateSelected(GObject gObject) {
                view.rightPanel.propertyScroll.setContent(gObject.getPEP());
            }

            @Override
            public void clearSelection() {
                view.rightPanel.propertyScroll.setContent(new PropertyEditPane());
            }
        });

        // Add history handlers for the History Pane
        historyManager.addHistoryListener(new HistoryListener() {
            @Override
            public void historyUpdated(HistoryUpdate historyUpdate) {
                ObservableList<HistoryPanelItem> panelItems = view.rightPanel.historyPanel.getItems();
                panelItems.clear();

                for (HistoryItemDescription item : historyUpdate.getHistory()) {
                    panelItems.add(new HistoryPanelItem(item));
                }

                view.rightPanel.historyPanel.getSelectionModel().select(historyUpdate.getCurrentIndex());
                view.rightPanel.historyPanel.scrollTo(historyUpdate.getCurrentIndex());
            }
        });

        view.rightPanel.historyPanel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    HistoryPanelItem historyItemDescription = view.rightPanel.historyPanel.getSelectionModel().getSelectedItem();
                    if (historyItemDescription != null) {
                        historyManager.updateTo(historyItemDescription.getIndex());
                    }
                }
            }
        });

    }

    //x and y are initial layout positions. To be used only with drag and drop.
    private void addGObject(final GObject newThing, ComponentSettings componentSettings, final View view, final ViewListeners viewListeners, Node settingsNode, int x, int y) {

        //Sets the default settings on the gObject and creates the property edit pane
        final PropertyEditPane propertyEditPane = new PropertyEditPane(newThing, componentSettings, fieldNames, view.middleTabPane.viewPane, settingsNode, historyManager);

        newThing.setPEP(propertyEditPane);

        if (componentSettings.getLayoutType().equals("anchorpane")) {
            final AnchorPane newAP = (AnchorPane) newThing;
            newAP.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent t) {
                    t.acceptTransferModes(TransferMode.ANY);
                    Rectangle highlight = view.middleTabPane.highlight;
                    highlight.setVisible(true);
                    double nodeX = newAP.getLayoutX();
                    double nodeY = newAP.getLayoutY();
                    Bounds bounds = newAP.getLayoutBounds();
                    double nodeW = bounds.getWidth();
                    double nodeH = bounds.getHeight();
                    highlight.setLayoutX(nodeX - 4);
                    highlight.setLayoutY(nodeY - 4);
                    highlight.setWidth(nodeW + 8);
                    highlight.setHeight(nodeH + 8);
                }
            });
            
            newAP.setOnDragExited(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent t) {
                    view.middleTabPane.highlight.setVisible(false);
                }
            });

            newAP.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent t) {
                    t.consume();
                    ComponentMenuItem cmj = view.leftPanel.leftList.getSelectionModel().getSelectedItem();
                    ComponentSettings componentSettings = cmj.getComponentSettings();
                    if (componentSettings != null) {
                        GObject newnewThing = null;

                        historyPause = true;
                        try {
                            Class panelPropertyClass = Class.forName("bdl.build." + componentSettings.getPackageName() + ".G" + componentSettings.getType());
                            Constructor constructor = panelPropertyClass.getConstructor();
                            newnewThing = (GObject) constructor.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

//                        addGObject(newThing, componentSettings, view, viewListeners, null, (int) t.getX(), (int) t.getY());
                        ((AnchorPane) newThing).getChildren().add((Node) newnewThing);
                        historyPause = false;
                    }
                    view.leftPanel.leftList.getSelectionModel().select(-1);
                }
            });
        }

        final Node newNode = (Node) newThing;

        newNode.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds t, Bounds t1) {
                selectionManager.updateSelected((GObject) newNode);
            }
        });

        newNode.layoutXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                selectionManager.updateSelected((GObject) newNode);
            }
        });

        newNode.layoutYProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                selectionManager.updateSelected((GObject) newNode);
            }
        });

        newNode.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.middleTabPane.viewPane.requestFocus();
                selectionManager.updateSelected((GObject) newNode);
                viewListeners.onMousePressed(newNode, mouseEvent);
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
                selectionManager.updateSelected((GObject) newNode);
                mouseEvent.consume();
            }
        });


        final ContextMenu nodePopUp = new ContextMenu();
        final MenuItem deletebutton = new MenuItem(LabelGrabber.getLabel("delete.node.text"));
        nodePopUp.getItems().add(deletebutton);
        newNode.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getButton().equals(MouseButton.SECONDARY)) {
                    nodePopUp.show(newNode, Side.RIGHT, 0, 0);
                    deletebutton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            view.middleTabPane.viewPane.getChildren().remove(newNode);
                            selectionManager.clearSelection();
                            historyManager.addHistory(new HistoryItem() {
                                @Override
                                public void revert() {
                                    view.middleTabPane.viewPane.getChildren().add(newNode);
                                    selectionManager.updateSelected((GObject) newNode);
                                }

                                @Override
                                public void restore() {
                                    view.middleTabPane.viewPane.getChildren().remove(newNode);
                                    selectionManager.clearSelection();
                                }

                                @Override
                                public String getAppearance() {
                                    return ((GObject) newNode).getFieldName() + " deleted!";
                                }
                            });
                        }
                    });
                }
            }
        });

        view.middleTabPane.viewPane.getChildren().add(newNode);

        historyManager.addHistory(new HistoryItem() {
            @Override
            public void restore() {
                view.middleTabPane.viewPane.getChildren().add(newNode);
                selectionManager.updateSelected(newThing);
            }

            @Override
            public void revert() {
                view.middleTabPane.viewPane.getChildren().remove(newThing);
                selectionManager.clearSelection();
            }

            @Override
            public String getAppearance() {
                return newThing.getClass().getSuperclass().getSimpleName() + " added!";
            }
        });


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

    private static class LeftListCellFactory extends ListCell<ComponentMenuItem> {

        public LeftListCellFactory(final View view) {
            super();
            final ObjectProperty<Cursor> cp = this.cursorProperty();
            this.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    cp.setValue(Cursor.CROSSHAIR);
                }
            });
            this.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    cp.setValue(Cursor.CROSSHAIR);
                }
            });
            this.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    cp.setValue(Cursor.CROSSHAIR);
                }
            });
            this.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    cp.setValue(Cursor.CROSSHAIR);
                }
            });


            this.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    Dragboard db = view.leftPanel.leftList.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent cc = new ClipboardContent();
                    cc.putString("");
                    db.setContent(cc);
                    cp.setValue(Cursor.CROSSHAIR);
                    t.consume();
                }
            });
        }

        @Override
        public void updateItem(ComponentMenuItem cmi, boolean empty) {
            super.updateItem(cmi, empty);
            if (!empty && cmi != null) {
                setText(cmi.getText());
                setGraphic(cmi.getGraphic());
            } else {
                setText(null);
                setGraphic(null);
                return;
            }
        }
    }
}
