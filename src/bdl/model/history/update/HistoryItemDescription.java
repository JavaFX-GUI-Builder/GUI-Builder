package bdl.model.history.update;

public class HistoryItemDescription {

    private int index;
    private String description;

    public HistoryItemDescription(int index, String description) {
        this.index = index;
        this.description = description;
    }

    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }
}
