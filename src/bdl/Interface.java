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

package bdl;

import bdl.controller.Controller;
import java.io.File;

public interface Interface {

    // GUI Builder -> BlueJ
    
    /** Stores the Controller for later referencing to the GUI Builder.
     * @param controller the Controller of the GUI Builder.
     */
    public void setGUIBuilderController(Controller controller);
    
    /** @return a File pointing to the path of the working directory.
     */
    public File getWorkingDirectory();
    
    /** @return true if a GUI is being edited, false otherwise.
     */
    public boolean isEditingGUI();
    
    /** @return the name of the currently open GUI.
     */
    public String getOpenGUIName();
    
    /** @return a File pointing to the currently open GUI's java file.
     */
    public File getOpenGUIFile();
    
    /** If ct is currently being edited in the GUI Builder, this method will close it and reset the GUI Builder.
     * @param ct The removed class.
     */
    public void notifyRemovedClass(Object ct);
    
    /** Marks the currently open class as dirty (needs recompiling) in BlueJ.
     */
    public void markAsDirty();
    
    /** Recompiles the currently open class in BlueJ.
     */
    public void recompileOpenGUI();
    
    /** @return a File pointing to the path representing the user's preferences directory.
     */
    public File getUserPrefDir();

    /** Writes a message onto BlueJ's status bar.
     * @param message the message to display
     */
    public void sendMessageToBlueJ(String message);

    /** @return true
     */
    public boolean isBlueJAttached();
    
    // BlueJ -> GUI Builder
    
    /** Makes the GUI Builder visible.
     */
    public void show();
    
    /** Makes the GUI Builder invisible.
     */
    public void hide();

}
