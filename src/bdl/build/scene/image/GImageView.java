package bdl.build.scene.image;

import bdl.build.scene.GNode;

public class GImageView extends GNode {

    private GImage image;
    private String fitWidth = "";
    private String fitHeight = "";

    public GImageView(String fieldName) {
        super(fieldName);
    }

    public GImage getImage() {
        return image;
    }

    public void setImage(GImage image) {
        this.image = image;
    }

    public String getFitWidth() {
        return fitWidth;
    }

    public void setFitWidth(String fitWidth) {
        this.fitWidth = fitWidth;
    }

    public String getFitHeight() {
        return fitHeight;
    }

    public void setFitHeight(String fitHeight) {
        this.fitHeight = fitHeight;
    }
}
