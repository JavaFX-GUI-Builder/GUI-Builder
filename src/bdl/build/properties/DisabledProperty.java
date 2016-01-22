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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class DisabledProperty implements PanelProperty {

    private GObject gObj;
    private CheckBox checkBox;
    private final HistoryManager historyManager;

    public DisabledProperty(final GObject gObj, String name, String getter, final String setter, String fxml, String defaultValue, GridPane gp, int row, Node settingsNode, HistoryManager hm) {
        this.gObj = gObj;
        this.historyManager = hm;

        gp.add(new Label(name + ":"), 0, row);
        checkBox = new CheckBox();

        //Grab value from settingsNode if given
        if (settingsNode != null) {
            defaultValue = Boolean.toString(settingsNode.isDisabled());
        }

        checkBox.setSelected(Boolean.parseBoolean(defaultValue));//TODO - Handle bad defaultValue values

        setValue(true, checkBox.isSelected());

        gp.add(checkBox, 1, row);

        //Upon change, save to the GObject
        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setValue(false, checkBox.isSelected());
            }
        });
    }

    private void setValue(boolean init, boolean value) {
        if (value) {
            ((Node)gObj).setStyle("-fx-opacity: 0.4");
        } else {
            ((Node)gObj).setStyle("-fx-opacity: 1");
        }
        if (!init) {
            historyManager.addHistory(new HistoryItem() {
                boolean newValue = checkBox.isSelected();
                boolean oldValue = !newValue;

                @Override
                public void revert() {
                    setValue(true, oldValue);
                }

                @Override
                public void restore() {
                    setValue(true, newValue);
                }

                @Override
                public String getAppearance() {
                    return gObj.getFieldName() + " disabled set to " + newValue;
                }
            });
        } else {
            checkBox.setSelected(value);
        }
    }

    @Override
    public String getJavaCode() {
        if (checkBox.isSelected()) {
            return gObj.getFieldName() + ".setDisable(" + checkBox.isSelected() + ");";
        } else {
            return "";
        }
    }

    @Override
    public String getFXMLCode() {
        if (checkBox.isSelected()) {
            return "disable=\"" + checkBox.isSelected() + "\"";
        } else {
            return "";
        }
    }
}