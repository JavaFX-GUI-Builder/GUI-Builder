package bdl.build;

import javafx.scene.paint.Color;

public interface GObject {

    /**
     * @return The name of this field in the java file
     */
    public String getFieldName();

    public void setFieldName(String fieldName);

    public GType getType();
    
    public String getGText();
    
    public void setGText(String text);
    
    public boolean getGEnabled();
    
    public void setGEnabled(boolean enabled);
    
    public String getGTooltip();
    
    public void setGTooltip(String tooltip);
    
    public Color getGForeground();
    
    public void setGForeground(Color foreground);
    
    public Color getGBackground();
    
    public void setGBackground(Color foreground);
    
    public int getGX();
    
    public void setGX(int xPos);
    
    public int getGY();
    
    public void setGY(int yPos);
    
    public int getGMinHeight();
    
    public void setGMinHeight(int minHeight);
    
    public int getGMinWidth();
    
    public void setGMinWidth(int minWidth);
    
    public int getGMaxHeight();
    
    public void setGMaxHeight(int maxHeight);
    
    public int getGMaxWidth();
    
    public void setGMaxWidth(int maxWidth);

}
