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

package bdl.view.left;

import bdl.lang.LabelGrabber;
import bdl.view.left.hierarchy.HierarchyPane;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;

public class LeftPanel extends SplitPane {

    public TitledPane hierarchyTitledPane;
    public ListView<ComponentMenuItem> leftList;
    public HierarchyPane hierarchyPane;

    public LeftPanel() {
        //Begin left component list
        leftList = new ListView<>();
        //End left component list

        //Begin left hierarchy panel
        hierarchyPane = new HierarchyPane();
        hierarchyTitledPane = new TitledPane(LabelGrabber.getLabel("hierarchy.tab.title"), hierarchyPane);
        hierarchyTitledPane.setCollapsible(false);
        hierarchyTitledPane.setMinWidth(205);
        hierarchyTitledPane.setMaxWidth(205);
        //End left hierarchy panel

        getItems().addAll(leftList, hierarchyTitledPane);
    }
}
