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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Method;

public class BackwardsBooleanProperty implements PanelProperty {

    private GObject gObj;
    private String setter;
    private String fxml;
    private CheckBox checkBox;
    private final HistoryManager historyManager;
    private String getter;

    public BackwardsBooleanProperty(final GObject gObj, String name, String getter, final String setter, String fxml, String defaultValue, GridPane gp, int row, Node settingsNode, HistoryManager hm) {
        this.gObj = gObj;
        this.setter = setter;
        this.getter = getter;
        this.fxml = fxml;
        this.historyManager = hm;

        gp.add(new Label(name + ":"), 0, row);
        checkBox = new CheckBox();

        //Grab value from settingsNode if given
        if (settingsNode != null) {
            try {
                Method method = settingsNode.getClass().getMethod(getter);
                String value = Boolean.toString(!((Boolean) method.invoke(settingsNode)));
                if (value != null) {
                    defaultValue = value;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        checkBox.setSelected(Boolean.parseBoolean(defaultValue));//TODO - Handle bad defaultValue values

        try {
            setValue();
        } catch (Exception e) {
            e.printStackTrace();
            return;//TODO: Probably need some better behavior here.
        }

        gp.add(checkBox, 1, row);

        //Upon change, save to the GObject
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
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
        final Method setMethod = gObj.getClass().getMethod(setter, boolean.class);
        final Method getMethod = gObj.getClass().getMethod(getter);
        final boolean old = (boolean) getMethod.invoke(gObj);
        final boolean nnew = !checkBox.isSelected();
        if (old != nnew && !historyManager.isPaused()) {
            historyManager.addHistory(new HistoryItem() {
                @Override
                public void revert() {
                    try {
                        setMethod.invoke(gObj, old);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void restore() {
                    try {
                        setMethod.invoke(gObj, nnew);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public String getAppearance() {
                    return gObj.getFieldName() + " checkbox changed!";
                }
            });
        }
        setMethod.invoke(gObj, !checkBox.isSelected());
    }

    @Override
    public String getJavaCode() {
        return gObj.getFieldName() + "." + setter + "(" + !checkBox.isSelected() + ");";
    }

    @Override
    public String getFXMLCode() {
        return fxml + "=\"" + !checkBox.isSelected() + "\"";
    }
}
