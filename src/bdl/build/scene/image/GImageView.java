package bdl.build.scene.image;

import bdl.build.GObject;
import bdl.build.GType;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class GImageView extends ImageView implements GObject {

    private String fieldName;

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public GType getType() {
        return GType.ImageView;
    }

    @Override
    public String getGText() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setGText(String text) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean getGEnabled() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setGEnabled(boolean enabled) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public String getGTooltip() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setGTooltip(String tooltip) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Color getGForeground() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setGForeground(Color foreground) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Color getGBackground() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setGBackground(Color background) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public int getGX() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setGX(int xPos) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public int getGY() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setGY(int yPos) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public int getGMinHeight() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setGMinHeight(int minHeight) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public int getGMinWidth() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setGMinWidth(int minWidth) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public int getGMaxHeight() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setGMaxHeight(int maxHeight) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public int getGMaxWidth() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setGMaxWidth(int maxWidth) {
        throw new UnsupportedOperationException("Not supported.");
    }

}
