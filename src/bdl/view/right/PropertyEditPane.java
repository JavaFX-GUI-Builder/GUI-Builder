package bdl.view.right;

import bdl.build.GObject;
import bdl.build.GUIObject;
import bdl.model.ComponentSettings;
import bdl.model.ListenerHint;
import bdl.model.Property;
import bdl.view.right.properties.FieldName;
import bdl.view.right.properties.LayoutProperty;
import bdl.view.right.properties.ListenerHintProperty;
import bdl.view.right.properties.PanelProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class PropertyEditPane extends GridPane {

    public PropertyEditPane(GObject gObj, ComponentSettings componentSettings, ArrayList<String> fieldNames, GUIObject guiObject) {
        //For reference, old properties panel: http://i.imgur.com/UBb7P4k.png
        if (gObj == null) {
            add(new Label("No component selected."), 0, 0);
        } else {
            int currentRow = 0;
            this.getChildren().clear();
            this.setMaxWidth(200);

            Label propertiesHeading = new Label("Properties:");
            propertiesHeading.setMinWidth(90);
            propertiesHeading.setFont(Font.font(propertiesHeading.getFont().getFamily(), FontWeight.BOLD, propertiesHeading.getFont().getSize() + 0.5));
            add(propertiesHeading, 0, currentRow++);
            new FieldName(gObj, fieldNames, componentSettings.getType(), this, currentRow++);

            List<PanelProperty> panelPropertyList = new ArrayList<>();
            for (Property property : componentSettings.getProperties()) {
                String type = property.getType();
                try {
                    Class panelPropertyClass = Class.forName("bdl.view.right.properties." + type + "Property");
                    Constructor constructor = panelPropertyClass.getConstructor(GObject.class, String.class, String.class, String.class, String.class, GridPane.class, int.class);
                    PanelProperty panelProperty = (PanelProperty)constructor.newInstance(gObj, property.getName(), property.getGetter(), property.getSetter(), property.getDefaultValue(), this, currentRow++);
                    if(panelProperty instanceof LayoutProperty) {
                        currentRow++;
                    }
                    panelPropertyList.add(panelProperty);
                } catch (Exception e) {
                    System.out.println(type + "Property failed.");
                    e.printStackTrace();
                }
            }
            for (ListenerHint lhint : componentSettings.getListenerHints()) {
                String name = lhint.getName();
                String text = lhint.getText();
                try {
                    PanelProperty panelProperty = new ListenerHintProperty(gObj, guiObject, name, text, this, currentRow++);
                    panelPropertyList.add(panelProperty);
                } catch (Exception e) {
                    System.out.println(name + "Listener failed.");
                    e.printStackTrace();
                }
            }
            gObj.setPanelProperties(panelPropertyList);
        }
    }
}
