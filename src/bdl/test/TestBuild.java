package bdl.test;

import bdl.build.GField;
import bdl.build.GUIObject;
import bdl.build.scene.control.GButton;
import bdl.build.scene.control.GToolBar;
import bdl.build.scene.layout.GBorderPane;

public class TestBuild {

    public static void main(String[] args) {
        GUIObject guiObject = new GUIObject(null, "Image Viewer", 300, 250);

        GField aspectRatio = new GField("aspectRatio", double.class);
        aspectRatio.setInitCode("image.getWidth() / image.getHeight();");

        GBorderPane root = new GBorderPane("root");
        GToolBar toolbar = new GToolBar("toolbar");
        root.setTop(toolbar);

        GButton fitBtn = new GButton("fitBtn");
        fitBtn.setLabel("Fit");
        fitBtn.setOnAction("imView.setFitHeight(root.getHeight());");

        GButton aspectRatioBtn = new GButton("aspectRatioBtn");
        aspectRatioBtn.setLabel("Correct Aspect Ratio");
        aspectRatioBtn.setOnAction("imView.setFitHeight(imView.getFitWidth() / aspectRatio);");



    }

}
