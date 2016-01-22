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

package bdl.model.selection;

import bdl.build.GObject;

import java.util.ArrayList;
import java.util.List;

public class SelectionManager {

    private List<SelectionListener> selectionListeners;
    private GObject currentlySelected = null;

    public SelectionManager() {
        selectionListeners = new ArrayList<>();
    }

    public void addSelectionListener(SelectionListener selectionListener) {
        selectionListeners.add(selectionListener);
    }

    public void updateSelected(GObject gObject) {
        for (SelectionListener selectionListener : selectionListeners) {
            selectionListener.updateSelected(gObject);
        }
        currentlySelected = gObject;
    }

    public void clearSelection() {
        for (SelectionListener selectionListener : selectionListeners) {
            selectionListener.clearSelection();
        }
        currentlySelected = null;
    }

    /**
     * Gets the currently selected GObject or null if no GObject is selected
     * @return The GObject that is currently selected or null if no GObject selected
     */
    public GObject getCurrentlySelected() {
        return currentlySelected;
    }

}
