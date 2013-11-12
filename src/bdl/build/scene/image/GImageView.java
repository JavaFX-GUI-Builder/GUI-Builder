package bdl.build.scene.image;

import bdl.build.scene.GNode;

public class GImageView extends GNode {

    private GImage image;

    public GImageView(String fieldName) {
        super(fieldName);
    }

    public GImage getImage() {
        return image;
    }

    public void setImage(GImage image) {
        this.image = image;
    }
}
