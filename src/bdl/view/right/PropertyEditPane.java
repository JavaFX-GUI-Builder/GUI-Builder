package bdl.view.right;

import bdl.build.GObject;
import bdl.model.ComponentSettings;
import bdl.model.Property;
import bdl.view.right.properties.FieldName;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.lang.reflect.Constructor;

public class PropertyEditPane extends GridPane {

    public PropertyEditPane(GObject gObj) {
        //For reference, old properties panel: http://i.imgur.com/UBb7P4k.png
        if (gObj == null) {
            add(new Label("No component selected."), 0, 0);
        } else {
            int currentRow = 0;
            this.getChildren().clear();

            ComponentSettings componentSettings = gObj.getComponentSettings();

            Label propertiesHeading = new Label("Properties:");
            propertiesHeading.setFont(Font.font(propertiesHeading.getFont().getFamily(), FontWeight.BOLD, propertiesHeading.getFont().getSize() + 0.5));
            add(propertiesHeading, 0, currentRow++);
            add(new FieldName(gObj), 0, currentRow++);

            for (Property property : componentSettings.getProperties()) {
                String type = property.getType();
                try {
                    Class panelPropertyClass = Class.forName("bdl.view.right.properties." + type + "Property");
                    Constructor constructor = panelPropertyClass.getConstructor(GObject.class, String.class, String.class, String.class, String.class);
                    Node node = (Node)constructor.newInstance(gObj, property.getName(), property.getGetter(), property.getSetter(), property.getDefaultValue());
                    add(node, 0, currentRow++);
                } catch (Exception e) {
                    System.out.println(type + "Property failed.");
                    e.printStackTrace();
                }
            }
        }
    }
}
