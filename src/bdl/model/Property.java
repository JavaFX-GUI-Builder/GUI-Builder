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

package bdl.model;

/**
 * Simple Properties object to store name, type, value.
 */
public class Property {
    private String name;
    private boolean enabled;
    private String type;
    private String defaultValue;
    private String getter;
    private String setter;
    private String fxml;

    public Property(String name, String enabled, String type, String defaultValue, String getter, String setter, String fxml) {
        this.name = name;
        this.enabled = Boolean.parseBoolean(enabled);
        this.type = type;
        this.defaultValue = defaultValue;
        this.getter = getter;
        this.setter = setter;
        this.fxml = fxml;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSetter() {
        return setter;
    }

    public String getFxml() {
        return fxml;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getGetter() {
        return getter;
    }
}
