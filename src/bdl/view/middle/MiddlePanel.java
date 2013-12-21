package bdl.view.middle;

import bdl.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class MiddlePanel extends TabPane {

    public Tab viewTab;
    public Tab codeTab;
    public Tab previewTab;

    public AnchorPane viewPane;

    public MiddlePanel() {
        setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        viewTab = new Tab("    Editor View    ");
        codeTab = new Tab("    Code View    ");
        previewTab = new Tab("    Preview GUI    ");

        getTabs().addAll(viewTab, codeTab, previewTab);

        viewPane = new AnchorPane();

        viewTab.setContent(viewPane);
    }

}
