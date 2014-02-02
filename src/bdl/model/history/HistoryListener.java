package bdl.model.history;

import bdl.model.history.update.HistoryUpdate;

public interface HistoryListener {

    /**
     * Method called when history gets updated so that views can update to reflect new history
     * @param historyUpdate HistoryUpdate containing the new history data
     */
    public void historyUpdated(HistoryUpdate historyUpdate);
}
