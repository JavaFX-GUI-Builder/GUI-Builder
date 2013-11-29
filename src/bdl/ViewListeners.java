package bdl;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ViewListeners {

    AnchorPane viewWindow;

    public ViewListeners(AnchorPane viewWindow) {
        this.viewWindow = viewWindow;
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
        outline.setFill(Color.TRANSPARENT);


        viewWindow.getChildren().add(outline);
    }
}
