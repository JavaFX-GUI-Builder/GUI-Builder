package bdl.build.scene.control;

public abstract class GButtonBase extends GLabeled {

    String onAction;

    public GButtonBase(String fieldName) {
        super(fieldName);
    }

    public String getOnAction() {
        return onAction;
    }

    public void setOnAction(String onAction) {
        this.onAction = onAction;
    }
}
