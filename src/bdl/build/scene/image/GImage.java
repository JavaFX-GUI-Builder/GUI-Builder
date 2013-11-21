package bdl.build.scene.image;

import bdl.build.GObject;

public class GImage extends GObject {
    private String url;

    public GImage(String fieldName, String url) {
        super(fieldName);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
