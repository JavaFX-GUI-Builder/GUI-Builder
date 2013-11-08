package bdl.build;

import bdl.build.scene.layout.GRegion;

public class GUIObject {

    private String stageTitle;
    private int stageWidth;
    private int stageHeight;

    private GRegion root;


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
}
