package bdl.model.history;

import bdl.model.history.update.HistoryItemDescription;
import bdl.model.history.update.HistoryUpdate;

import java.util.ArrayList;
import java.util.List;

public class HistoryManager {

    private int currentIndex;
    private ArrayList<HistoryItem> chain;
    private List<HistoryListener> historyListeners;

    public HistoryManager() {
        currentIndex = 0;
        chain = new ArrayList<>();
        chain.add(new HistoryItem(null, null, "Start"));
        historyListeners = new ArrayList<>();
    }

    public void addHistoryListener(HistoryListener historyListener) {
        historyListeners.add(historyListener);
    }

    public void addHistory(HistoryItem newHistoryItem) {
        chain.add(newHistoryItem);
        currentIndex++;
        notifyOfUpdate();
    }

    public void clearHistory() {
        chain.clear();
        chain.add(new HistoryItem(null, null, "Start"));
        currentIndex = 0;
        notifyOfUpdate();
    }

    private void notifyOfUpdate() {
        if (historyListeners.size() > 0) {
            ArrayList<HistoryItemDescription> historyDescriptions = new ArrayList<>();
            int i = 0;
            for (HistoryItem historyItem : chain) {
                historyDescriptions.add(new HistoryItemDescription(i, historyItem.getUpdateAppearance()));
                i++;
            }
            HistoryUpdate historyUpdate = new HistoryUpdate(currentIndex, historyDescriptions.toArray(new HistoryItemDescription[historyDescriptions.size()]));
            for (HistoryListener historyListener : historyListeners) {
                historyListener.historyUpdated(historyUpdate);
            }
        }
    }


}
