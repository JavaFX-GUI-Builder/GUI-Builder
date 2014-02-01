package bdl.view.left;

import bdl.lang.LabelGrabber;
import bdl.model.ComponentSettings;
import bdl.model.ComponentSettingsStore;
import bdl.view.View;
import bdl.view.hierarchy.HierarchyPane;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;

public class LeftPanel extends SplitPane {

    public TitledPane hierarchyPane;
    public ListView<ComponentMenuItem> leftList;
    public TreeItem<String> treeRoot;
    public TreeView<String> leftTreeView;

    public LeftPanel(ComponentSettingsStore componentSettingsStore, final View view) {
        //Begin left component list
        leftList = new ListView<>();
        leftList.setCellFactory(new Callback<ListView<ComponentMenuItem>, ListCell<ComponentMenuItem>>() {
            @Override
            public ListCell<ComponentMenuItem> call(ListView<ComponentMenuItem> list) {
                return new LeftListCellFactory(view);
            }
        });


        for (ComponentSettings componentSettings : componentSettingsStore.getComponents()) {
            String type = componentSettings.getType();
            ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/bdl/icons/" + componentSettings.getIcon())));
            leftList.getItems().add(new ComponentMenuItem(type, icon, componentSettings));
        }
        //End left component list

        //Begin left hierarchy panel
        HierarchyPane hierPane = new HierarchyPane(view);
        hierarchyPane = new TitledPane(LabelGrabber.getLabel("hierarchy.tab.title"), hierPane);
        hierarchyPane.setCollapsible(false);
        hierarchyPane.setMinWidth(205);
        hierarchyPane.setMaxWidth(205);
        //End left hierarchy panel

        getItems().addAll(leftList);
    }

    private static class LeftListCellFactory extends ListCell<ComponentMenuItem> {

        public LeftListCellFactory(final View view) {
            super();
            this.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    Dragboard db = view.leftPanel.leftList.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent cc = new ClipboardContent();
                    cc.putString("");
                    db.setContent(cc);
                    t.consume();
                }
            });
        }
        
        @Override
        public void updateItem(ComponentMenuItem cmi, boolean empty) {
            super.updateItem(cmi, empty);
            if(!empty && cmi != null) {
                setText(cmi.getText());
                setGraphic(cmi.getGraphic());
            }
            else {
                setText(null);
                setGraphic(null);
                return;
            }
        }
    }
}
