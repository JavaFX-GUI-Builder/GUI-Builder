package bdl.build;

import bdl.view.right.properties.PanelProperty;
import javafx.scene.Node;

import java.util.HashMap;
import java.util.HashSet;

public class CodeGenerator {

    public static String generateJavaCode(GUIObject guiObject, HashMap<String, String> allImports) {

        StringBuilder code = new StringBuilder();

        code.append(getJavaImports(guiObject, allImports)).append('\n');//Add imports

        code.append("public class ").append(guiObject.getClassName()).append(" extends Application {\n\n");//Open class tag

        //Add declarations
        for (Node node : guiObject.getChildren()) {
            GObject gObj = (GObject) node;
            String nodeType = node.getClass().getSimpleName().substring(1);
            code.append("    public static ")
                    .append(nodeType)
                    .append(" ")
                    .append(gObj.getFieldName())
                    .append(" = new ")
                    .append(nodeType)
                    .append("();\n");
        }
        code.append('\n');


        //Add properties
        code.append("    private Parent getRoot() {\n");
        code.append("        AnchorPane root = new AnchorPane();\n");

        code.append("        root.getChildren().addAll(");
        String prefix = "";
        for (Node node : guiObject.getChildren()) {
            GObject gObj = (GObject) node;
            code.append(prefix);
            prefix = ", ";
            code.append(gObj.getFieldName());
        }
        code.append(");\n\n");

        for (Node node : guiObject.getChildren()) {
            GObject gObj = (GObject) node;
            for (PanelProperty property : gObj.getPanelProperties()) {
                String javaCode = property.getJavaCode();
                if (!javaCode.isEmpty()) {
                    code.append("        ").append(javaCode.replace("\n", "\n        ")).append("\n");
                }
            }
            code.append('\n');
        }
        code.append("        return root;\n");
        code.append("    }\n\n");


        //Add start method
        code.append("    @Override\n" +
                "    public void start(Stage primaryStage) {\n" +
                "        Scene scene = new Scene(getRoot(), " + guiObject.getWidth() + ", " + guiObject.getHeight() + ");\n" +
                "        \n" +
                "        primaryStage.setTitle(\"" + guiObject.getGUITitle() + "\");\n" +
                "        primaryStage.setScene(scene);\n" +
                "        primaryStage.show();\n" +
                "    }\n\n");

        //Add main method
        code.append("    /**\n" +
                "     * The main() method is ignored in correctly deployed JavaFX application.\n" +
                "     * main() serves only as fallback in case the application can not be\n" +
                "     * launched through deployment artifacts, e.g., in IDEs with limited FX\n" +
                "     * support. NetBeans ignores main().\n" +
                "     *\n" +
                "     * @param args the command line arguments\n" +
                "     */\n" +
                "    public static void main(String[] args) {\n" +
                "        launch(args);\n" +
                "    }\n");;

        code.append('}');//Close class tag
        return code.toString();
    }

    public static String generateFXMLCode(GUIObject guiObject, HashMap<String, String> allImports) {

        StringBuilder code = new StringBuilder();
        code.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n");

        code.append(getFXMLImports(guiObject, allImports)).append('\n');

        code.append("<AnchorPane id=\"");
        code.append(guiObject.getClassName());
        code.append("\" prefWidth=\"");
        code.append(guiObject.getGUIWidth());
        code.append("\" prefHeight=\"");
        code.append(guiObject.getGUIHeight());
        code.append("\" xmlns:fx=\"http://javafx.com/fxml/1\" xmlns=\"http://javafx.com/javafx/2.2\">\n");
        code.append("    <children>\n");

        for (Node node : guiObject.getChildren()) {
            String nodeClass = node.getClass().getSuperclass().getSimpleName();
            code.append("        <").append(nodeClass);
            GObject gObj = (GObject) node;
            code.append(" fx:id=\"").append(gObj.getFieldName()).append("\" ");
            for (PanelProperty property : gObj.getPanelProperties()) {
                String fxmlCode = property.getFXMLCode();
                if (!fxmlCode.isEmpty()) {
                    code.append(fxmlCode).append(' ');
                }
            }
            code.append("/>\n");
        }

        code.append("    </children>\n");
        code.append("</AnchorPane>");
        return code.toString();
    }

    private static String getJavaImports(GUIObject guiObject, HashMap<String, String> allImports) {
        HashSet<String> imports = new HashSet<>();

        for (Node node : guiObject.getChildren()) {
            String gObjectName= node.getClass().getSimpleName().substring(1);
            imports.add("import " + allImports.get(gObjectName) + "." + gObjectName + ";\n");
        }

        StringBuilder importsString = new StringBuilder();
        importsString.append("import javafx.application.Application;\n")
                .append("import javafx.scene.Scene;\n")
                .append("import javafx.scene.Parent;\n")
                .append("import javafx.scene.layout.AnchorPane;\n")
                .append("import javafx.stage.Stage;\n")
                .append("import javafx.scene.paint.Color;\n")
                .append("import javafx.scene.control.Tooltip;\n\n");
        for (String s : imports) {
            importsString.append(s);
        }

        return importsString.toString();
    }

    private static String getFXMLImports(GUIObject guiObject, HashMap<String, String> allImports) {
        StringBuilder importsString = new StringBuilder();

        importsString.append("<?import java.lang.*?>\n")
                .append("<?import java.util.*?>\n")
                .append("<?import javafx.scene.control.*?>\n")
                .append("<?import javafx.scene.layout.*?>\n")
                .append("<?import javafx.scene.paint.*?>\n")
                .append("<?import javafx.scene.shape.*?>\n");

        return importsString.toString();
    }

}
