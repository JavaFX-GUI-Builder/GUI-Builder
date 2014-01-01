package bdl.controller;

import bdl.build.GObject;
import bdl.view.View;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ViewListeners {
    private Rectangle outline;
    private View view;

    boolean isMousePressed = false;

    public ViewListeners(View view) {
        this.view = view;
        outline = new Rectangle();
        outline.setStrokeWidth(2);
        outline.setStroke(Color.BLUE);
        outline.setFill(null);
        view.middleTabPane.viewPane.getChildren().add(outline);
    }

    public void onMousePressed(Node node, MouseEvent mouseEvent) {
        isMousePressed = true;
        curX = mouseEvent.getX();
        curY = mouseEvent.getY();

        double nodeX = node.getLayoutX();
        double nodeY = node.getLayoutY();
        Bounds bounds = node.getLayoutBounds();
        double nodeW = bounds.getWidth();
        double nodeH = bounds.getHeight();
        outline.setLayoutX(nodeX - 4);
        outline.setLayoutY(nodeY - 4);
        outline.setWidth(nodeW + 8);
        outline.setHeight(nodeH + 8);
    }

    double curX = 0, curY = 0;
    public void onMouseDragged(Node node, MouseEvent mouseEvent) {
        if (isMousePressed) {
            double x = node.getLayoutX() + (mouseEvent.getX() - curX);
            double y = node.getLayoutY() + (mouseEvent.getY() - curY);
            node.setLayoutX(x);
            node.setLayoutY(y);
            outline.setLayoutX(x - 4);
            outline.setLayoutY(y - 4);
        }
    }

    public void onMouseReleased(Node node, MouseEvent mouseEvent) {
        isMousePressed = false;
    }
}
