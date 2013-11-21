package bdl;

import bdl.view.components.PropertyEditPane;
import java.util.Set;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Simple Main class.
 * 
 * @author David Hodgson
 */
public class Main extends Application {

    Parent root;
    
    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("GUIBuilder.fxml"));
        
//        ObservableList<Node> nodes = root.getChildrenUnmodifiable();
//        for (Node n : nodes) {
//            System.out.println("Child id: " + n.getId());
//        }
        
        Scene scene = new Scene(root, 1024, 768);
        
        // Find the AnchorPane on the right and add a PropertyEditPane to it.
//        Node anchorPaneRight = searchForNode(root, "#anchorPaneRight");
//        if (anchorPaneRight != null) { System.out.println("Found anchorPaneRight!"); }
//        else { System.err.println("Failed to find anchorPaneRight."); }
//        ObservableList<Node> rootNodes = root.getChildrenUnmodifiable();
//        for (Node n : rootNodes)
//            System.out.println("Root node: "+n.getId());
//        if (anchorPaneRight == null) {
//            System.err.println("anchorPaneRight is null.");
//        } else { System.out.println("anchorPaneRight is NOT null! =D"); }
//        Set<Node> nodes = root.lookupAll("anchorPaneRight");
//        if (nodes.size() == 1) {
//            Node[] nodes2 = new Node[0];
//            AnchorPane anchorPaneRight = (AnchorPane) nodes.toArray(nodes2)[0];
//            if (anchorPaneRight != null) {
//                anchorPaneRight.getChildren().addAll(new PropertyEditPane());
//                System.out.println("Added PropertyEditPane to right AnchorPane.");
//            } else { System.err.println("anchorPaneRight is null."); }
//        } else { System.err.println("lookupAll returned empty."); }
        
//        Node anchorPaneRightNode = root.lookupAll("#anchorPaneRight");
//        if (anchorPaneRightNode != null) {
//            AnchorPane anchorPaneRight = (AnchorPane) anchorPaneRightNode;
//            //Parent anchorPaneRightParent = anchorPaneRight.getParent();
//            anchorPaneRight.getChildren().addAll(new PropertyEditPane());
//            System.out.println("Added PropertyEditPane to right AnchorPane.");
//        } else { System.err.println("Failed to find right AnchorPane."); }
        
        stage.setTitle("GUI Builder");
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
    }
    
//    private Node searchForNode(Node rootNode, String nodeId) {
//        ObservableList<Node> nodes = ((Parent)rootNode).getChildrenUnmodifiable();
//        
//        // Breadth first.
//        for (Node n : nodes) {
//            System.out.println("Node ID: "+n.getId());
//            if (n.getId().equals(nodeId)) { return n; }
//        }
//        // Then go deeper.
//        for (Node n : nodes) {
//            Node result = searchForNode(n,nodeId);
//            if (result != null) { return result; }
//        }
//        
//        return null;
//    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
