package bdl.view.middle;

import bdl.build.GUIObject;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class MiddlePanel extends TabPane {

    public Tab viewTab;
    public Tab codeTab;
    public Tab previewTab;

    public AnchorPane viewPane;
    public AnchorPane viewPaneDecorator;
    public TextArea codePane;

    public MiddlePanel() {
        setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        viewTab = new Tab("    Editor View    ");
        codeTab = new Tab("    Code View    ");
        previewTab = new Tab("    Preview GUI    ");

        getTabs().addAll(viewTab, codeTab, previewTab);

        viewPaneDecorator = new AnchorPane();
        viewPane = new GUIObject();
        AnchorPane.setBottomAnchor(viewPane, 0.0);
        AnchorPane.setTopAnchor(viewPane, 0.0);
        AnchorPane.setRightAnchor(viewPane, 0.0);
        AnchorPane.setLeftAnchor(viewPane, 0.0);
        viewPaneDecorator.getChildren().add(viewPane);

        viewPane.setStyle("-fx-opacity: 1;");//TODO - We could use this to prevent Node interactions

        codePane = new TextArea();
        codePane.setEditable(false);

        viewTab.setContent(viewPaneDecorator);
        codeTab.setContent(codePane);
    }

}
