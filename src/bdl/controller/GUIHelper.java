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

package bdl.controller;

import bdl.build.GUIObject;
import javafx.scene.layout.AnchorPane;

public class GUIHelper {

    public static void setBounds(GUIObject viewPane, AnchorPane decorator, double width, double height) {
        viewPane.setMinWidth(width);
        viewPane.setMinHeight(height);
        viewPane.setMaxWidth(width);
        viewPane.setMaxHeight(height);
        decorator.setMinWidth(width);
        decorator.setMinHeight(height);
        decorator.setMaxWidth(width);
        decorator.setMaxHeight(height);

        viewPane.setGUIWidth(width);
        viewPane.setGUIHeight(height);
    }
}
