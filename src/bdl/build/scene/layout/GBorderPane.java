package bdl.build.scene.layout;

import bdl.build.scene.GNode;

public class GBorderPane extends GPane {

    GNode top, right, bottom, left, center;

    public GBorderPane(String fieldName) {
        super(fieldName);
    }

    public GNode getTop() {
        return top;
    }

    public void setTop(GNode top) {
        this.top = top;
    }

    public GNode getRight() {
        return right;
    }

    public void setRight(GNode right) {
        this.right = right;
    }

    public GNode getBottom() {
        return bottom;
    }

    public void setBottom(GNode bottom) {
        this.bottom = bottom;
    }

    public GNode getLeft() {
        return left;
    }

    public void setLeft(GNode left) {
        this.left = left;
    }

    public GNode getCenter() {
        return center;
    }

    public void setCenter(GNode center) {
        this.center = center;
    }
}
