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
