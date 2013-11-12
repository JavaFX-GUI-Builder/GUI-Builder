package bdl.build.scene.control;

public class GButton extends GButtonBase {

    private String label;

    public GButton(String fieldName) {
        super(fieldName);
    }

    public String getLabel() {
        return label == null ? "" : label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
