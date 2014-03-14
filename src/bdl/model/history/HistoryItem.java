package bdl.model.history;

public abstract class HistoryItem {
    abstract public void restore();

    abstract public void revert();

    abstract public String getAppearance();
}
