/*
 * This file is part of JavaFX-GUI-Builder.
 *
 * Copyright (C) 2014  Leon Atherton, Ben Goodwin, David Hodgson
 *
 * JavaFX-GUI-Builder is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * JavaFX-GUI-Builder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JavaFX-GUI-Builder.  If not, see <http://www.gnu.org/licenses/>.
 */

package bdl.build.properties;

import bdl.build.GObject;
import bdl.model.history.HistoryItem;
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
    private String getter;
    private String fxml;
    private ColorPicker colorPicker;
    private final HistoryManager historyManager;

    public ColorProperty(final GObject gObj, String name, String getter, final String setter, String fxml, String defaultValue, GridPane gp, int row, Node settingsNode, HistoryManager hm) {
        this.gObj = gObj;
        this.setter = setter;
        this.getter = getter;
        this.fxml = fxml;
        this.historyManager = hm;

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
                }
            }
        });
    }

    private void setValue() throws Exception {
        final Method setMethod = gObj.getClass().getMethod(setter, Paint.class);
        final Method getMethod = gObj.getClass().getMethod(getter);
        final Color old = (Color) getMethod.invoke(gObj);
        final Color nnew = colorPicker.getValue();
        if(!historyManager.isPaused())
        historyManager.addHistory(new HistoryItem() {
            @Override
            public void revert() {
                try {
                    setMethod.invoke(gObj, old);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                colorPicker.setValue(old);
                historyManager.pause();
                // work around to update colorpicker's displayed selection
                colorPicker.fireEvent(new ActionEvent(null, colorPicker));
                historyManager.unpause();
            }

            @Override
            public void restore() {
                try {
                    setMethod.invoke(gObj, nnew);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                colorPicker.setValue(nnew);
                historyManager.pause();
                // work around to update colorpicker's displayed selection
                colorPicker.fireEvent(new ActionEvent(null, colorPicker));
                historyManager.unpause();
            }

            @Override
            public String getAppearance() {
                return gObj.getFieldName() + " color changed!";
            }
        });
        setMethod.invoke(gObj, colorPicker.getValue());
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
