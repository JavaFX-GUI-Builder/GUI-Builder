package bdl.view.right.properties;

import bdl.build.GObject;
import bdl.model.history.HistoryManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.lang.reflect.Method;

public class ColorProperty implements PanelProperty {

    private GObject gObj;
    private String setter;
    private String fxml;
    private ColorPicker colorPicker;

    public ColorProperty(final GObject gObj, String name, String getter, final String setter, String fxml, String defaultValue, GridPane gp, int row, Node settingsNode, HistoryManager hm) {
        this.gObj = gObj;
        this.setter = setter;
        this.fxml = fxml;

        gp.add(new Label(name + ":"), 0, row);
        colorPicker = new ColorPicker();

        //Grab value from settingsNode if given
        if (settingsNode != null) {
            try {
                Method method = settingsNode.getClass().getMethod(getter);
                String value = method.invoke(settingsNode).toString();
                if (value != null) {
                    defaultValue = value;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        colorPicker.setValue(Color.web(defaultValue));//TODO - Handle bad defaultValue values

        try {
            setValue();
        } catch (Exception e) {
            e.printStackTrace();
            return;//TODO: Probably need some better behavior here.
        }

        gp.add(colorPicker, 1, row);

        //On action, save to the GObject
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    setValue();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;//TODO: Probably need some better behavior here.
                }
            }
        });
    }

    private void setValue() throws Exception {
        Method method = gObj.getClass().getMethod(setter, Paint.class);
        method.invoke(gObj, colorPicker.getValue());
    }

    @Override
    public String getJavaCode() {
        return gObj.getFieldName() + "." + setter + "(Color.web(\"" + colorPicker.getValue().toString() + "\"));";
    }

    @Override
    public String getFXMLCode() {
        return fxml + "=\"" + colorPicker.getValue().toString() + "\"";
    }

}
