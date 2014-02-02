package bdl.view.right.history;

import bdl.model.history.update.HistoryItemDescription;
import javafx.scene.control.Label;

public class HistoryPanelItem extends Label {

    HistoryItemDescription historyItemDescription;

    public HistoryPanelItem (HistoryItemDescription historyItemDescription) {
        super(historyItemDescription.getDescription());
        this.historyItemDescription = historyItemDescription;
    }

    public int getIndex() {
        return historyItemDescription.getIndex();
    }
}
