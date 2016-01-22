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

package bdl.view.right.hints;

import bdl.lang.LabelGrabber;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ben Goodwin
 */
public class ListenerHintWindow {

    public ListenerHintWindow(String displayText) {
        Stage s = new Stage();
        Pane pane = new Pane();
        VBox vb = new VBox();
        s.resizableProperty().setValue(false);
        Scene sc = new Scene(pane, 400, 200);
        s.setScene(sc);
        Label l = new Label(LabelGrabber.getLabel("hint.template.warning"));
        TextArea ta = new TextArea();
        ta.setEditable(false);
        ta.setText(displayText);
        vb.getChildren().addAll(l, ta);
        pane.getChildren().add(vb);
        s.show();
    }
}
