package bdl.build.scene.shape;

import bdl.ViewListeners;
import bdl.build.GObject;
import bdl.build.GType;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GRectangle extends Rectangle implements GObject {

    private String fieldName;

    public GRectangle(final ViewListeners viewListeners) {
        final Node thisNode = this;
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            }
        });
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                viewListeners.onMouseClicked(thisNode);
                viewListeners.onMousePressed(thisNode, mouseEvent);
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                viewListeners.onMouseReleased(thisNode, mouseEvent);
            }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                viewListeners.onMouseDragged(thisNode, mouseEvent);
            }
        });
    }

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
        return GType.Rectangle;
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
        return (Color) this.getStroke();
    }

    @Override
    public void setGForeground(Color foreground) {
        this.setStroke(foreground);
    }

    @Override
    public Color getGBackground() {
        return (Color) this.getFill();
    }

    @Override
    public void setGBackground(Color background) {
        this.setFill(background);
    }

    @Override
    public int getGX() {
        return (int) Math.round(this.getLayoutX());
    }

    @Override
    public void setGX(int xPos) {
        this.setLayoutX(xPos);
    }

    @Override
    public int getGY() {
        return (int) Math.round(this.getLayoutY());
    }

    @Override
    public void setGY(int yPos) {
        this.setLayoutY(yPos);
    }

    @Override
    public int getGMinHeight() {
        return (int) Math.round(this.getHeight());
    }

    @Override
    public void setGMinHeight(int minHeight) {
        this.setHeight(minHeight);
    }

    @Override
    public int getGMinWidth() {
        return (int) Math.round(this.getWidth());
    }

    @Override
    public void setGMinWidth(int minWidth) {
        this.setWidth(minWidth);
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
