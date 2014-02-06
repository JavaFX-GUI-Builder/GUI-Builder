package bdl.controller;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class ViewListeners {

    boolean isMousePressed = false;

    public void onMousePressed(Node node, MouseEvent mouseEvent) {
        isMousePressed = true;
        curX = mouseEvent.getX();
        curY = mouseEvent.getY();
    }
    double curX = 0, curY = 0;

    public void onMouseDragged(Node node, MouseEvent mouseEvent) {
        if (isMousePressed) {
            double x = node.getLayoutX() + (mouseEvent.getX() - curX);
            double y = node.getLayoutY() + (mouseEvent.getY() - curY);
            node.setLayoutX(x);
            node.setLayoutY(y);
        }
    }


    public void onMouseReleased(Node node, MouseEvent mouseEvent) {
        isMousePressed = false;
    }
}
