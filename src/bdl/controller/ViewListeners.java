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

package bdl.controller;

import bdl.build.GObject;
import bdl.model.history.HistoryItem;
import bdl.model.history.HistoryManager;
import bdl.model.selection.SelectionManager;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class ViewListeners {

    private SelectionManager selectionManager;
    HistoryManager historyManager;
    double historyX, historyY;
    boolean isMousePressed = false;
    double curX = 0, curY = 0;

    public ViewListeners(HistoryManager hm, SelectionManager selectionManager) {
        historyManager = hm;
        this.selectionManager = selectionManager;
    }

    public void onMousePressed(Node node, MouseEvent mouseEvent) {
        isMousePressed = true;
        curX = mouseEvent.getX();
        curY = mouseEvent.getY();
        historyX = node.getLayoutX();
        historyY = node.getLayoutY();
    }

    public void onMouseDragged(Node node, MouseEvent mouseEvent) {
        if (isMousePressed && selectionManager.getCurrentlySelected() == node) {
            double x = node.getLayoutX() + (mouseEvent.getX() - curX);
            double y = node.getLayoutY() + (mouseEvent.getY() - curY);
            node.setLayoutX(x);
            node.setLayoutY(y);
        }
    }

    public void onMouseReleased(final Node node, final MouseEvent mouseEvent) {
        isMousePressed = false;
        final double finalX = node.getLayoutX();
        final double finalY = node.getLayoutY();
        if (finalX != historyX || finalY != historyY) {
            historyManager.addHistory(new HistoryItem() {
                double hFinalX = finalX;
                double hFinalY = finalY;
                double hHistoryX = historyX;
                double hHistoryY = historyY;

                @Override
                public void restore() {
                    node.setLayoutY(hFinalY);
                    node.setLayoutX(hFinalX);
                }

                @Override
                public void revert() {
                    node.setLayoutY(hHistoryY);
                    node.setLayoutX(hHistoryX);
                }

                @Override
                public String getAppearance() {
                    return ((GObject) node).getFieldName() + " layout changed!";
                }
            });
        }
    }
}
