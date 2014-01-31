package bdl.controller;

import bdl.build.GUIObject;
import javafx.scene.layout.AnchorPane;

public class GUIHelper {

    public static void setBounds(GUIObject viewPane, AnchorPane decorator, double width, double height) {
        viewPane.setMinWidth(width);
        viewPane.setMinHeight(height);
        viewPane.setMaxWidth(width);
        viewPane.setMaxHeight(height);
        decorator.setMinWidth(width);
        decorator.setMinHeight(height);
        decorator.setMaxWidth(width);
        decorator.setMaxHeight(height);

        viewPane.setGUIWidth(width);
        viewPane.setGUIHeight(height);
    }
}
