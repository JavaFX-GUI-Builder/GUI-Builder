package bdl.view.middle;

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
