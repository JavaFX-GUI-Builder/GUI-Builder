package bdl.test;

import bdl.build.GField;
import bdl.build.GUIObject;
import bdl.build.scene.control.GButton;
import bdl.build.scene.control.GToolBar;
import bdl.build.scene.image.GImage;
import bdl.build.scene.image.GImageView;
import bdl.build.scene.layout.GBorderPane;

public class TestBuild {

    public static void main(String[] args) {
        GUIObject guiObject = new GUIObject(null, "Image Viewer", 300, 250);

        GBorderPane root = new GBorderPane("root");

        GImageView imView = new GImageView("imView");
        GImage image = new GImage("image", "GoogleDataCenter/CBF_002.jpg");
        imView.setImage(image);

        GField aspectRatio = new GField("aspectRatio", double.class);
        aspectRatio.setInitCode("image.getWidth() / image.getHeight();");

        root.setCenter(imView);

        GButton fitBtn = new GButton("fitBtn");
        fitBtn.setLabel("Fit");
        fitBtn.setOnAction("imView.setFitHeight(root.getHeight());");

        GButton aspectRatioBtn = new GButton("aspectRatioBtn");
        aspectRatioBtn.setLabel("Correct Aspect Ratio");
        aspectRatioBtn.setOnAction("imView.setFitHeight(imView.getFitWidth() / aspectRatio);");


        GToolBar toolbar = new GToolBar("toolbar");
        toolbar.addButton(fitBtn);
        toolbar.addButton(aspectRatioBtn);
        root.setTop(toolbar);

        imView.setFitHeight("root.getHeight()");

    }

}
