/*
 * This file is part of JavaFX-GUI-Builder.
 *
 * Copyright (C) 2014  Leon Atherton, Ben Goodwin, David Hodgson
 *
 * JavaFX-GUI-Builder is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * JavaFX-GUI-Builder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JavaFX-GUI-Builder.  If not, see <http://www.gnu.org/licenses/>.
 */

package bdl.view.middle;

import bdl.build.GUIObject;
import bdl.controller.GUIHelper;
import bdl.lang.LabelGrabber;
import bdl.view.right.PropertyEditPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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

    public Rectangle outline;
    public Rectangle highlight;

    public MiddlePanel() {
        setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        viewTab = new Tab("    " + LabelGrabber.getLabel("editor.view.tab") + "    ");
        codeTab = new Tab("    " + LabelGrabber.getLabel("code.view.tab") + "    ");
        previewTab = new Tab("    " + LabelGrabber.getLabel("preview.gui.tab") + "    ");

        getTabs().addAll(viewTab, codeTab, previewTab);

        blankPane = new StackPane();
        blankPane.setStyle("-fx-background-color:#94B2E0;");
        
        viewPane = new GUIObject();
        viewPane.setPEP(new PropertyEditPane(viewPane));
        viewPane.setStyle("-fx-background-color:#FFFFFF;");

        scroll = new ScrollPane();
        
        viewPaneDecorator = new AnchorPane();
        viewPaneDecorator.getChildren().add(viewPane);

        GUIHelper.setBounds(viewPane, viewPaneDecorator, 600, 400);

        Rectangle rect = new Rectangle();
        rect.widthProperty().bind(viewPaneDecorator.widthProperty());
        rect.heightProperty().bind(viewPaneDecorator.heightProperty());
        viewPaneDecorator.setClip(rect);

        scroll.setContent(blankPane);
        blankPane.getChildren().addAll(viewPaneDecorator);
        StackPane.setAlignment(viewPaneDecorator, Pos.CENTER);
        StackPane.setMargin(viewPaneDecorator, new Insets(30, 30, 30, 30));

        outline = new Rectangle();
        outline.setStrokeWidth(2);
        outline.setStroke(Color.BLUE);
        outline.setFill(Color.TRANSPARENT);
        outline.setMouseTransparent(true);
        outline.setStyle("-fx-opacity: 1;");//Could use this to make a light grey foreground
        viewPaneDecorator.getChildren().add(outline);
        
        highlight = new Rectangle();
        highlight.setStrokeWidth(2);
        highlight.setStroke(Color.RED);
        highlight.setFill(Color.TRANSPARENT);
        highlight.setMouseTransparent(true);
        highlight.setStyle("-fx-opacity: 1;");//Could use this to make a light grey foreground
        viewPaneDecorator.getChildren().add(highlight);

        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        //viewPane.setStyle("-fx-opacity: 1;");//TODO - We could use this to prevent Node interactions

        codePane = new TextArea();
        codePane.setEditable(false);

        viewTab.setContent(scroll);
        codeTab.setContent(codePane);
    }

}
