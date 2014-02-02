package bdl.model.history;

import javafx.scene.control.Label;

public class HistoryItem extends Label {

    Restorable restoreObject;
    Object updateData;
    String updateAppearance;

    /**
     * Contains a reference to the updated Restorable, some data about the update,
     * and a String appearance of the update.
     * @param restoreObject A reference back to the object updated required to revert change the change
     * @param updateData Some piece of update data that the Restorable will understand what to do with (not standardised)
     * @param updateAppearance A string appearance of the update
     */
    public HistoryItem (Restorable restoreObject, Object updateData, String updateAppearance) {
        this.restoreObject = restoreObject;
        this.updateData = updateData;
        this.updateAppearance = updateAppearance;
    }

    public Restorable getRestoreObject() {
        return restoreObject;
    }

    public Object getUpdateData() {
        return updateData;
    }

    public String getUpdateAppearance() {
        return updateAppearance;
    }
}
