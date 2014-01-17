package bdl.view.middle;

import bdl.build.GUIObject;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class MiddlePanel extends TabPane {

    public Tab viewTab;
    public Tab codeTab;
    public Tab previewTab;

    public GUIObject viewPane;
    public AnchorPane viewPaneDecorator;
    public TextArea codePane;
    private final AnchorPane blankPane;

    public MiddlePanel() {
        setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        viewTab = new Tab("    Editor View    ");
        codeTab = new Tab("    Code View    ");
        previewTab = new Tab("    Preview GUI    ");

        getTabs().addAll(viewTab, codeTab, previewTab);

        viewPaneDecorator = new AnchorPane();
        blankPane = new AnchorPane();
        blankPane.setStyle("-fx-background-color:#94B2E0;");
        blankPane.setDisable(true);
        AnchorPane.setBottomAnchor(blankPane, 0.0);
        AnchorPane.setTopAnchor(blankPane, 0.0);
        AnchorPane.setRightAnchor(blankPane, 0.0);
        AnchorPane.setLeftAnchor(blankPane, 0.0);
        viewPane = new GUIObject();
        viewPane.setStyle("-fx-background-color:#FFFFFF;");
        viewPane.setMinWidth(800);
        viewPane.setMinHeight(600);
        viewPane.setMaxWidth(800);
        viewPane.setMaxHeight(600);
        ScrollPane scroll = new ScrollPane();
        AnchorPane.setBottomAnchor(scroll, 50.0);
        AnchorPane.setTopAnchor(scroll, 50.0);
        AnchorPane.setRightAnchor(scroll, 50.0);
        AnchorPane.setLeftAnchor(scroll, 50.0);
        scroll.setContent(viewPane);
        scroll.setMaxWidth(800);
        scroll.setMaxHeight(600);
        viewPaneDecorator.getChildren().addAll(blankPane, scroll);

        //viewPane.setStyle("-fx-opacity: 1;");//TODO - We could use this to prevent Node interactions

        codePane = new TextArea();
        codePane.setEditable(false);

        viewTab.setContent(viewPaneDecorator);
        codeTab.setContent(codePane);
    }

}
