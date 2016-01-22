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
import bdl.model.history.HistoryManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

public class TooltipProperty implements PanelProperty {

    private GObject gObj;
    private TextField textField;
    private Control control;

    public TooltipProperty(final GObject gObj, String name, String getter, String setter, String fxml, String defaultValue, GridPane gp, int row, Node settingsNode, HistoryManager hm) {
        this.gObj = gObj;

        gp.add(new Label(name + ":"), 0, row);
        textField = new TextField();

        //Grab value from settingsNode if given
        if (settingsNode != null) {
            Tooltip tooltip = ((Control)settingsNode).getTooltip();
            if (tooltip != null) {
                defaultValue = tooltip.getText();
            }
        }

        textField.setText(defaultValue);

        control = (Control) gObj;

        setValue();

        gp.add(textField, 1, row);

        //Upon losing focus, save to the GObject
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                if (!aBoolean2) {
                    setValue();
                }
            }
        });
    }

    private void setValue() {
        if (!textField.getText().isEmpty()) {
            control.setTooltip(new Tooltip(textField.getText()));
        }
    }

    @Override
    public String getJavaCode() {
        Tooltip tooltip = control.getTooltip();
        String tooltipText = "";
        if (tooltip != null) {
            tooltipText = tooltip.getText().replace("\\", "\\\\").replace("\"", "\\\"");
            return gObj.getFieldName() + ".setTooltip(new Tooltip(\"" + tooltipText + "\"));";
        } else {
            return "";
        }
    }

    @Override
    public String getFXMLCode() {
        return "";//TODO Complete this
    }
}
