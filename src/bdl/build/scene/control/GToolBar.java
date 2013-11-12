package bdl.build.scene.control;

import bdl.build.GObject;

import java.util.ArrayList;
import java.util.List;

public class GToolBar extends GControl {

    private ArrayList<GButton> buttons = new ArrayList<>();

    public GToolBar(String fieldName) {
        super(fieldName);
    }

    public void addButton(GButton button) {
        buttons.add(button);
    }

    public List<GButton> getButtons() {
        return buttons;
    }
}
