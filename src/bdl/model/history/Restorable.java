package bdl.model.history;

public interface Restorable {

    /**
     * The Restorable class will use the passed in restore data to return to a previous state.
     *
     * @param restoreData It is assumed that the Restorable class will know what to do with this restore data.
     */
    public void restore (Object restoreData);
}
