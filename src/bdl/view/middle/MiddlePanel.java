package bdl.view.middle;

import bdl.build.GUIObject;
import bdl.lang.LabelGrabber;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class MiddlePanel extends TabPane {

    public Tab viewTab;
    public Tab codeTab;
    public Tab previewTab;

    public GUIObject viewPane;
    public AnchorPane viewPaneDecorator;
    public TextArea codePane;
    public ScrollPane scroll;
    private StackPane blankPane;

    public MiddlePanel() {
        setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        viewTab = new Tab("    " + LabelGrabber.getLabel("editor.view.tab") + "    ");
        codeTab = new Tab("    " + LabelGrabber.getLabel("code.view.tab") + "    ");
        previewTab = new Tab("    " + LabelGrabber.getLabel("preview.gui.tab") + "    ");

        getTabs().addAll(viewTab, codeTab, previewTab);

        blankPane = new StackPane();
        blankPane.setStyle("-fx-background-color:#94B2E0;");
        
        viewPane = new GUIObject();
        viewPane.setStyle("-fx-background-color:#FFFFFF;");
        viewPane.setMinWidth(600);
        viewPane.setMinHeight(400);
        viewPane.setMaxWidth(600);
        viewPane.setMaxHeight(400);
        
        scroll = new ScrollPane();
        
        viewPaneDecorator = new AnchorPane();
        viewPaneDecorator.getChildren().add(viewPane);

        viewPaneDecorator.setMinWidth(600);
        viewPaneDecorator.setMinHeight(400);
        viewPaneDecorator.setMaxWidth(600);
        viewPaneDecorator.setMaxHeight(400);

        Rectangle rect = new Rectangle();
        rect.widthProperty().bind(viewPaneDecorator.widthProperty());
        rect.heightProperty().bind(viewPaneDecorator.heightProperty());
        viewPaneDecorator.setClip(rect);

        scroll.setContent(blankPane);
        blankPane.getChildren().addAll(viewPaneDecorator);
        StackPane.setAlignment(viewPaneDecorator, Pos.CENTER);
        StackPane.setMargin(viewPaneDecorator, new Insets(30, 30, 30, 30));

        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        //viewPane.setStyle("-fx-opacity: 1;");//TODO - We could use this to prevent Node interactions

        codePane = new TextArea();
        codePane.setEditable(false);

        viewTab.setContent(scroll);
        codeTab.setContent(codePane);
    }

}
