package bdl.build;

import bdl.Interface;
import bdl.build.properties.PanelProperty;
import java.util.ArrayList;
import javafx.scene.Node;

import java.util.HashMap;
import java.util.HashSet;
import javafx.scene.layout.Pane;

public class CodeGenerator {

    public static String generateJavaCode(GUIObject guiObject, HashMap<String, String> allImports, Interface blueJInterface) {

        StringBuilder code = new StringBuilder();

        code.append(getJavaImports(guiObject, allImports)).append('\n');//Add imports

        String clName = (blueJInterface != null ? blueJInterface.getOpenGUIName() : guiObject.getClassName());
        String clExtends = (blueJInterface != null ? "guibuilder.GUI" : "Application");
        code.append("public class ").append(clName).append(" extends ").append(clExtends).append(" {\n\n");//Open class tag

        //Add declarations
        for (Node node : guiObject.getChildren()) {
            declaration(node, code);
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
        code.append(");\n");

        //Build a list of panes so that we can add each node into that pane's pane.
        ArrayList<Pane> paneList = buildPaneList(guiObject);
        for (Pane p : paneList) {
            code.append("        ").append(((GObject) p).getFieldName()).append(".getChildren().addAll(");
            prefix = "";
            for (Node node : p.getChildren()) {
                GObject gObj = (GObject) node;
                code.append(prefix);
                prefix = ", ";
                code.append(gObj.getFieldName());
            }
            code.append(");\n");
        }
        code.append("\n");

        for (Node node : guiObject.getChildren()) {
            panelProperties(node, code);
        }
        code.append("        return root;\n");
        code.append("    }\n\n");


        // Add show method
        code.append("    public void show() {\n"
                + "        new JFXPanel();\n"
                + "        Platform.runLater(new Runnable() {\n"
                + "            @Override\n"
                + "            public void run() {\n"
                + "                start(new Stage());\n"
                + "            }\n"
                + "        });\n"
                + "    }\n\n");
        
        //Add start method
        code.append("    @Override\n"
                + "    public void start(Stage primaryStage) {\n"
                + "        Scene scene = new Scene(getRoot(), " + guiObject.getWidth() + ", " + guiObject.getHeight() + ");\n"
                + "        \n"
                + "        primaryStage.setTitle(\"" + guiObject.getGUITitle() + "\");\n"
                + "        primaryStage.setScene(scene);\n"
                + "        primaryStage.show();\n"
                + "    }\n\n");

        //Add main method
        code.append("    /**\n"
                + "     * The main() method is ignored in correctly deployed JavaFX application.\n"
                + "     * main() serves only as fallback in case the application can not be\n"
                + "     * launched through deployment artifacts, e.g., in IDEs with limited FX\n"
                + "     * support. NetBeans ignores main().\n"
                + "     *\n"
                + "     * @param args the command line arguments\n"
                + "     */\n"
                + "    public static void main(String[] args) {\n"
                + "        launch(args);\n"
                + "    }\n");;

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
            fxmlOutput(node, code);
        }

        code.append("    </children>\n");
        code.append("</AnchorPane>");
        return code.toString();
    }

    private static String getJavaImports(GUIObject guiObject, HashMap<String, String> allImports) {
        HashSet<String> imports = new HashSet<>();

        for (Node node : guiObject.getChildren()) {
            javaImports(node, imports, allImports);
        }

        StringBuilder importsString = new StringBuilder();
        importsString.append("import javafx.application.Application;\n")
                .append("import javafx.application.Platform;\n")
                .append("import javafx.embed.swing.JFXPanel;\n")
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

    private static void declaration(Node node, StringBuilder code) {
        GObject gObj = (GObject) node;
        String nodeType = node.getClass().getSimpleName().substring(1);
        code.append("    public static ")
                .append(nodeType)
                .append(" ")
                .append(gObj.getFieldName())
                .append(" = new ")
                .append(nodeType)
                .append("();\n");
        if (node instanceof Pane) {
            for (Node node2 : ((Pane) node).getChildren()) {
                declaration(node2, code);
            }
        }
    }

    private static void properties(String prefix, Node node, StringBuilder code) {
        GObject gObj = (GObject) node;
        code.append(prefix);
        prefix = ", ";
        code.append(gObj.getFieldName());
        if (node instanceof Pane) {
            code.append("        ").append(((GObject) node).getFieldName()).append(".getChildren().addAll(");
            prefix = "";
            for (Node node2 : ((Pane) node).getChildren()) {
                properties(prefix, node2, code);
            }
            code.append(");\n");
        }
    }

    private static void panelProperties(Node node, StringBuilder code) {
        GObject gObj = (GObject) node;
        for (PanelProperty property : gObj.getPanelProperties()) {
            String javaCode = property.getJavaCode();
            if (!javaCode.isEmpty()) {
                code.append("        ").append(javaCode.replace("\n", "\n        ")).append("\n");
            }
        }
        code.append('\n');
        if (node instanceof Pane) {
            for (Node node2 : ((Pane) node).getChildren()) {
                panelProperties(node2, code);
            }
        }
    }

    private static void fxmlOutput(Node node, StringBuilder code) {
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
        if (node instanceof Pane) {
            for (Node node2 : ((Pane) node).getChildren()) {
                fxmlOutput(node2, code);
            }
        }
    }

    private static void javaImports(Node node, HashSet<String> imports, HashMap<String, String> allImports) {
        String gObjectName = node.getClass().getSimpleName().substring(1);
        imports.add("import " + allImports.get(gObjectName) + "." + gObjectName + ";\n");
        if (node instanceof Pane) {
            for (Node node2 : ((Pane) node).getChildren()) {
                javaImports(node2, imports, allImports);
            }
        }
    }

    // Recursively build an arraylist of panes in the current gui.
    private static ArrayList<Pane> buildPaneList(GUIObject guiObject) {
        ArrayList<Pane> list = new ArrayList<>();
        for (Node n : guiObject.getChildren()) {
            if (n instanceof Pane) {
                list.add((Pane) n);
                buildList((Pane) n, list);
            }
        }
        return list;
    }

    private static void buildList(Pane pane, ArrayList<Pane> list) {
        for (Node n : pane.getChildren()) {
            if (n instanceof Pane) {
                list.add((Pane) n);
                buildList((Pane) n, list);
            }
        }
    }
}
