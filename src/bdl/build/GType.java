package bdl.build;

public enum GType {
    Button,
    Menu,
    MenuBar,
    MenuItem,
    ToolBar,
    Image,
    ImageView,
    AnchorPane;

    @Override
    public String toString() {
        return this.name();
    }
}
