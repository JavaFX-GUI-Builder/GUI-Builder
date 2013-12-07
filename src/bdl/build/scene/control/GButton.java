package bdl.build.scene.control;

import bdl.ViewListeners;
import bdl.build.GObject;
import bdl.build.GType;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GButton extends Button implements GObject {

    private String fieldName;

    public GButton(final ViewListeners viewListeners) {
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
        return GType.Button;
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
