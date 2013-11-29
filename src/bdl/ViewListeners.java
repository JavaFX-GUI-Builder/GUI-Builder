package bdl;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ViewListeners {

    AnchorPane viewWindow;

    boolean isMousePressed = false;

    public ViewListeners(AnchorPane viewWindow) {
        this.viewWindow = viewWindow;
    }

    public void onMousePressed(Node node, MouseEvent mouseEvent) {
        isMousePressed = true;
        curX = mouseEvent.getX();
        curY = mouseEvent.getY();
    }

    double curX = 0, curY = 0;
    public void onMouseDragged(Node node, MouseEvent mouseEvent) {
        if (isMousePressed) {
            node.setLayoutX(node.getLayoutX() + (mouseEvent.getX() - curX));
            node.setLayoutY(node.getLayoutY() + (mouseEvent.getY() - curY));
        }
    }

    public void onMouseReleased(Node node, MouseEvent mouseEvent) {
        isMousePressed = false;
    }

    public void onMouseClicked(Node node) {
        double nodeX = node.getLayoutX();
        double nodeY = node.getLayoutY();
        Bounds bounds = node.getLayoutBounds();
        double nodeW = bounds.getWidth();
        double nodeH = bounds.getHeight();
        Rectangle outline = new Rectangle(nodeX - 10, nodeY - 10, nodeW + 20, nodeH + 20);
        outline.setStrokeWidth(4);
        outline.setStroke(Color.BLUE);
        outline.setFill(null);


        viewWindow.getChildren().add(outline);
    }
}
