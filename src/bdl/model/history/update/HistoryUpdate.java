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

package bdl.model.history.update;

public class HistoryUpdate {

    private int currentIndex;
    private HistoryItemDescription[] history;

    public HistoryUpdate(int currentIndex, HistoryItemDescription[] history) {
        this.currentIndex = currentIndex;
        this.history = history;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public HistoryItemDescription[] getHistory() {
        return history;
    }

    public boolean canUndo() {
        return currentIndex > 0;
    }

    public boolean canRedo() {
        return currentIndex < history.length - 1;
    }

    public String getUndoDescription() {
        if (currentIndex - 1 > 0) {
            return history[currentIndex - 1].getDescription();
        } else {
            return null;
        }
    }

    public String getRedoDescription() {
        if (currentIndex + 1 < history.length) {
            return history[currentIndex + 1].getDescription();
        } else {
            return null;
        }
    }
}
