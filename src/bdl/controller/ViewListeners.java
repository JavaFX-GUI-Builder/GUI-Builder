package bdl.controller;

import bdl.build.GObject;
import bdl.model.history.HistoryItem;
import bdl.model.history.HistoryManager;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class ViewListeners {

    HistoryManager historyManager;
    double historyX, historyY;
    boolean isMousePressed = false;
    double curX = 0, curY = 0;

    public ViewListeners(HistoryManager hm) {
        historyManager = hm;
    }

    public void onMousePressed(Node node, MouseEvent mouseEvent) {
        isMousePressed = true;
        curX = mouseEvent.getX();
        curY = mouseEvent.getY();
        historyX = node.getLayoutX();
        historyY = node.getLayoutY();
    }

    public void onMouseDragged(Node node, MouseEvent mouseEvent) {
        if (isMousePressed) {
            double x = node.getLayoutX() + (mouseEvent.getX() - curX);
            double y = node.getLayoutY() + (mouseEvent.getY() - curY);
            node.setLayoutX(x);
            node.setLayoutY(y);
        }
    }

    public void onMouseReleased(final Node node, final MouseEvent mouseEvent) {
        isMousePressed = false;
        final double finalX = node.getLayoutX();
        final double finalY = node.getLayoutY();
        historyManager.addHistory(new HistoryItem() {
            @Override
            public void restore() {
                node.setLayoutY(finalY);
                node.setLayoutX(finalX);
            }

            @Override
            public void revert() {
                node.setLayoutY((double) historyY);
                node.setLayoutX((double) historyX);
            }

            @Override
            public String getAppearance() {
                return ((GObject) node).getFieldName() + " X and Y changed!";
            }
        });
    }
}
