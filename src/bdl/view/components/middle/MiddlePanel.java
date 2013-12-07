package bdl.view.components.middle;

import bdl.Main;
import bdl.build.scene.control.GButton;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MiddlePanel extends TabPane {

    public MiddlePanel() {
        setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab viewTab = new Tab("    Editor View    ");
        Tab codeTab = new Tab("    Code View    ");
        final Tab previewTab = new Tab("    Preview GUI    ");


        final MiddlePanel refThis = this;
        previewTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (previewTab.isSelected()) {
                    refThis.getSelectionModel().select(0);
                    System.out.println("XXX");
                }
            }
        });
        getTabs().addAll(viewTab, codeTab, previewTab);

        GButton b = new GButton(Main.viewListeners);
        b.setText("Button Text");
        b.setLayoutX(100);
        b.setLayoutY(100);
        Main.middleTop.getChildren().add(b);
        viewTab.setContent(Main.middleTop);
    }

}
