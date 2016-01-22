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

package bdl.build.properties;

import bdl.build.GObject;
import bdl.build.GUIObject;
import bdl.lang.LabelGrabber;
import bdl.view.right.hints.ListenerHintWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ListenerHintProperty implements PanelProperty {

    private Button button = new Button(LabelGrabber.getLabel("hint.button.text"));

    public ListenerHintProperty (final GObject gObj, final GUIObject guiObject, String name, final String text, GridPane gp, int row) {
        gp.add(new Label(name + ":"), 0, row);
        gp.add(button, 1, row);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new ListenerHintWindow(guiObject.getClassName() + '.' + gObj.getFieldName() + text);
            }
        });
    }

    @Override
    public String getJavaCode() {
        return "";
    }

    @Override
    public String getFXMLCode() {
        return "";
    }
}
