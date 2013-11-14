package bdl.build;

import bdl.build.scene.layout.GRegion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GUIObject {

    private String stageTitle;
    private int stageWidth;
    private int stageHeight;

    private Collection<GObject> fields;

    private GRegion root;

    public GUIObject(GRegion root, String stageTitle, int stageWidth, int stageHeight) {
        this.root = root;
        this.stageTitle = stageTitle;
        this.stageWidth = stageWidth;
        this.stageHeight = stageHeight;
        fields = new ArrayList<>();
    }

    public String getStageTitle() {
        return stageTitle;
    }

    public void setStageTitle(String stageTitle) {
        this.stageTitle = stageTitle;
    }

    public int getStageWidth() {
        return stageWidth;
    }

    public void setStageWidth(int stageWidth) {
        this.stageWidth = stageWidth;
    }

    public int getStageHeight() {
        return stageHeight;
    }

    public void setStageHeight(int stageHeight) {
        this.stageHeight = stageHeight;
    }

    public GRegion getRoot() {
        return root;
    }

    public void setRoot(GRegion root) {
        this.root = root;
    }

    public void addField(GObject obj) {
        fields.add(obj);
    }

    public Collection<GObject> getFields() {
        return fields;
    }
}
