package bdl.model.selection;

import bdl.build.GObject;

public interface SelectionListener {
    public void updateSelected(GObject gObject);

    public void clearSelection();
}
