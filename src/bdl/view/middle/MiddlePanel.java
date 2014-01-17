package bdl.view.middle;

import bdl.build.GUIObject;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class MiddlePanel extends TabPane {

    public Tab viewTab;
    public Tab codeTab;
    public Tab previewTab;

    public GUIObject viewPane;
    public AnchorPane viewPaneDecorator;
    public TextArea codePane;
    public ScrollPane scroll;
    private final AnchorPane blankPane;

    public MiddlePanel() {
        setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        viewTab = new Tab("    Editor View    ");
        codeTab = new Tab("    Code View    ");
        previewTab = new Tab("    Preview GUI    ");

        getTabs().addAll(viewTab, codeTab, previewTab);

        blankPane = new AnchorPane();
        blankPane.setStyle("-fx-background-color:#94B2E0;");
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
        
        scroll = new ScrollPane();
        AnchorPane.setBottomAnchor(scroll, 50.0);
        AnchorPane.setTopAnchor(scroll, 50.0);
        AnchorPane.setRightAnchor(scroll, 50.0);
        AnchorPane.setLeftAnchor(scroll, 50.0);
        
        viewPaneDecorator = new AnchorPane();
        AnchorPane.setBottomAnchor(viewPaneDecorator, 50.0);
        AnchorPane.setTopAnchor(viewPaneDecorator, 50.0);
        AnchorPane.setRightAnchor(viewPaneDecorator, 50.0);
        AnchorPane.setLeftAnchor(viewPaneDecorator, 50.0);
        
        scroll.setContent(viewPane);
        scroll.setMaxWidth(800);
        scroll.setMaxHeight(600);
        blankPane.getChildren().addAll(viewPaneDecorator, scroll);

        //viewPane.setStyle("-fx-opacity: 1;");//TODO - We could use this to prevent Node interactions

        codePane = new TextArea();
        codePane.setEditable(false);

        viewTab.setContent(blankPane);
        codeTab.setContent(codePane);
    }

}
