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

public class ColorProperty implements PanelProperty {

    private GObject gObj;
    private String setter;
    private ColorPicker colorPicker;

    public ColorProperty(final GObject gObj, String name, String getter, final String setter, String defaultValue, GridPane gp, int row) {
        this.gObj = gObj;
        this.setter = setter;

        gp.add(new Label(name + ":"), 0, row);
        colorPicker = new ColorPicker();

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

}
