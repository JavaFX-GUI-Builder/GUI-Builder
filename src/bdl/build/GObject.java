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

package bdl.build;

import bdl.view.right.PropertyEditPane;
import bdl.build.properties.PanelProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public interface GObject {

    /**
     * @return The name of this field in the java file
     */
    public String getFieldName();

    public void setFieldName(String fieldName);

    public void setPanelProperties(List<PanelProperty> properties);

    public List<PanelProperty> getPanelProperties();

    public void setPEP(PropertyEditPane propertyEditPane);
    
    public PropertyEditPane getPEP();

    public StringProperty fieldNameProperty();
}
