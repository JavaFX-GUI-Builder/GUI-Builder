package bdl.view.right.properties;

import bdl.build.GObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.lang.reflect.Method;

public class ColorProperty extends GridPane implements PanelProperty {

    private GObject gObj;
    private String setter;
    private ColorPicker colorPicker;

    public ColorProperty(final GObject gObj, String name, String getter, final String setter) {
        this.gObj = gObj;
        this.setter = setter;

        add(new Label(name + ":"), 0, 0);
        colorPicker = new ColorPicker();

        Method method;
        try {
            method = gObj.getClass().getMethod(getter);
            colorPicker.setValue((Color) method.invoke(gObj));
        } catch (Exception e) {
            e.printStackTrace();
            return;//TODO: Probably need some better behavior here.
        }

        add(colorPicker, 1, 0);

        //On action, save to the GObject
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Color color = colorPicker.getValue();
                Method method;
                try {
                    method = gObj.getClass().getMethod(setter, Paint.class);
                    method.invoke(gObj, color);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;//TODO: Probably need some better behavior here.
                }
            }
        });
    }

    @Override
    public String getJavaCode() {
        return "";//TODO
    }

}
