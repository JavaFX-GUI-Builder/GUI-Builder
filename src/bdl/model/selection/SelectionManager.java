package bdl.model.selection;

import bdl.build.GObject;

import java.util.ArrayList;
import java.util.List;

public class SelectionManager {

    private List<SelectionListener> selectionListeners;

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
    }

    public void clearSelection() {
        for (SelectionListener selectionListener : selectionListeners) {
            selectionListener.clearSelection();
        }
    }

}
