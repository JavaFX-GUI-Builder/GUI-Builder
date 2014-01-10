package bdl.view.right.properties;

import bdl.build.GObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class FieldName {

    private ArrayList<String> fieldNames;

    public FieldName(final GObject gObj, ArrayList<String> fieldNames, String type, GridPane gp, int row) {
        gp.add(new Label("Field Name:"), 0, row);
        final TextField textField = new FieldNameTextField();
        this.fieldNames = fieldNames;

        //Set default field name
        type = type.substring(0, 1).toLowerCase() + type.substring(1);
        int count = 1;
        while (fieldNames.contains(type + count)) {
            count++;
        }
        textField.setText(type + count);
        gObj.setFieldName(type + count);
        fieldNames.add(type + count);

        gp.add(textField, 1, row);

        //Upon losing focus, save to the GObject
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                if (!aBoolean2) gObj.setFieldName(textField.getText());
            }
        });
    }

    private class FieldNameTextField extends TextField {
        //JavaFX source: http://hg.openjdk.java.net/openjfx/8/master/rt/file/b473c7e4e115/modules/controls/src/main/java/javafx/scene/control/TextInputControl.java
        /* We override the TextField implementation to add our own validation. */

        private boolean backspacePressed = false;

        public FieldNameTextField() {
            super();
            addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                        backspacePressed = true;
                    }
                }
            });
        }

        final ChangeListener<Number> ignoreNextBackspace = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                //Use the anchor property because the anchor changes after the caret, so otherwise it ends up selecting a character.
                anchorProperty().removeListener(ignoreNextBackspace);
                selectRange(number.intValue(), number.intValue());
                backspacePressed = false;
            }
        };

        @Override public void replaceText(int start, int end, String text) {
            try {
                if (isAllowed(start, end, text)) {
                    super.replaceText(start, end, text);
                } else if (backspacePressed) {
                    anchorProperty().addListener(ignoreNextBackspace);
                }
            } catch (Exception e) {
                //TODO - Using undo after adding an invalid character throws an exception...
            }
        }

        @Override public void replaceSelection(String text) {
            int a = getCaretPosition(), b=getAnchor();
            int start = Math.min(a, b);
            int end = Math.max(a, b);
            if (isAllowed(start, end, text)) {
                super.replaceSelection(text);
            }
            backspacePressed = false;
        }

        private String[] reservedWords = {
                "assert",
                "abstract", "boolean", "break", "byte",
                "case", "catch", "char", "class",
                "const", "continue", "default", "do",
                "double", "else", "extends", "final",
                "finally", "float", "for", "goto",
                "if", "implements", "import",
                "instanceof", "int", "interface",
                "long", "native", "new", "package",
                "private", "protected", "public",
                "return", "short", "static", "super",
                "switch", "synchronized", "this",
                "throw", "throws", "transient",
                "try", "void", "volatile", "while"
        };

        private boolean isAllowed(int start, int end, String text) {
            if (text != null) {
                String curText = getText();
                if (curText == null) curText = "";
                if (end > curText.length()) end = curText.length();
                String potentialNewText = curText.substring(0, start) + text + curText.substring(end);

                //Must not cause new text to be empty.
                //I actually think this should have slightly different behavior - allow it, but reset to previous on blur? - Leon
                if (potentialNewText.isEmpty()) {
                    return false;
                }
                //Must not start with a number or symbol. (£,$,_ are allowed).
                if (start == 0 && !text.isEmpty() && !text.substring(0, 1).matches("[A-Za-z£$_]")) {
                    return false;
                }
                //Must not cause modifications which lead to starting with a number or symbol. (£,$,_ are allowed).
                if (!potentialNewText.isEmpty() && !potentialNewText.substring(0, 1).matches("[A-Za-z£$_]")) {
                    return false;
                }
                //Must not contain any reserved symbols
                if (!text.matches("[A-Za-z0-9£$_]*")) {
                    return false;
                }
                //Must not cause a duplicate of another field name
                if (fieldNames.contains(potentialNewText)) {
                    return false;
                }
                //Finally, must not cause a reserved word
                for (int i = 0; i < reservedWords.length; i++){
                    if (potentialNewText.equals(reservedWords[i])) {
                        return false;
                    }
                }
                fieldNames.remove(getText());
                fieldNames.add(potentialNewText);
            }
            return true;//Text passed the above checks
        }
    }

}
