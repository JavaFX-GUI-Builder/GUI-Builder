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
