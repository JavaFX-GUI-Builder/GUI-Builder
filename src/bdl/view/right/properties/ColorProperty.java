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

    public ColorProperty(final GObject gObj, String name, String getter, final String setter, String defaultValue) {
        this.gObj = gObj;
        this.setter = setter;

        add(new Label(name + ":"), 0, 0);
        colorPicker = new ColorPicker();

        colorPicker.setValue(Color.web(defaultValue));//TODO - Handle bad defaultValue values

        try {
            setValue();
        } catch (Exception e) {
            e.printStackTrace();
            return;//TODO: Probably need some better behavior here.
        }

        add(colorPicker, 1, 0);

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
        return gObj.getFieldName() + "." + setter + " - todo...\n";//TODO
    }

}
