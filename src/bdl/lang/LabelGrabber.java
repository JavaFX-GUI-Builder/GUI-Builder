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

package bdl.lang;

import java.io.*;
import java.util.Properties;

/**
 *
 * @author Ben Goodwin
 */
public class LabelGrabber {
    
    private static Properties lang;
    private File languageFile;
    
    public LabelGrabber() {
        lang = new Properties();
//        languageFile = new File("lang/gb.lang"); //TODO update later with more languages
//        if(languageFile == null) {
//            System.err.println("Missing language file at: lang/gb.lang");
//            return;
//        }
//        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(languageFile), "UTF-8"))) {
//            lang.load(reader);
//        }
//        catch(IOException e) {
//            e.printStackTrace();
//        }
        try {
            lang.load(getClass().getResourceAsStream("/bdl/lang/gb.lang"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    public static String getLabel(String key) {
        String string = lang.getProperty(key);
        if(string == null) {
            System.err.println("Missing value for key: " + key);
            return "";
        }
        return string;
    }
}
