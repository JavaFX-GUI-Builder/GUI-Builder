package bdl.build.scene.control;

import bdl.ViewListeners;
import bdl.build.GObject;
import bdl.build.GType;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;

import javafx.scene.paint.Color;

public class GToolBar extends ToolBar implements GObject {
    private String fieldName;

    public GToolBar(final ViewListeners viewListeners) {
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
        return GType.ToolBar;
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
        return (int) Math.round(this.getMinHeight());
    }

    @Override
    public void setGMinHeight(int minHeight) {
        this.setMinHeight(minHeight);
    }

    @Override
    public int getGMinWidth() {
        return (int) Math.round(this.getMinWidth());
    }

    @Override
    public void setGMinWidth(int minWidth) {
        this.setMinWidth(minWidth);
    }

    @Override
    public int getGMaxHeight() {
        return (int) Math.round(this.getMaxHeight());
    }

    @Override
    public void setGMaxHeight(int maxHeight) {
        this.setMaxHeight(maxHeight);
    }

    @Override
    public int getGMaxWidth() {
        return (int) Math.round(this.getMaxWidth());
    }

    @Override
    public void setGMaxWidth(int maxWidth) {
        this.setMaxWidth(maxWidth);
    }
}
