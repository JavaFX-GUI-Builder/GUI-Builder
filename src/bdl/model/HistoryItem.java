package bdl.model;

import bdl.view.right.properties.PanelProperty;
import javafx.scene.control.Label;

public class HistoryItem extends Label {

    PanelProperty panelProperty;
    Object updateData;
    String updateAppearance;

    /**
     * Contains a reference to the updated property, some data about the update,
     * and a String appearance to display within the History panel.
     * @param panelProperty A reference back to the updated property used when reverting properties
     * @param updateData Some piece of update data that the PanelProperty will understand what to do with (not standardised)
     * @param updateAppearance A string appearance of the update to display within the History panel.
     */
    public HistoryItem (PanelProperty panelProperty, Object updateData, String updateAppearance) {
        this.panelProperty = panelProperty;
        this.updateData = updateData;
        this.updateAppearance = updateAppearance;
    }

    public PanelProperty getPanelProperty() {
        return panelProperty;
    }

    public Object getUpdateData() {
        return updateData;
    }

    public String getUpdateAppearance() {
        return updateAppearance;
    }
}
