/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smarttrolleygui;

/**
 *
 * @author me
 */
public class TextObject {
    
    String text, font, size, color, xstart, xend, ystart, yend;

    public TextObject() {
    }
    
    // setters
    public void setText(String text) {
        this.text = text;
    }
    
    public void setFont(String font) {
        this.font = font;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setXStart(String xstart) {
        this.xstart = xstart;
    }

    public void setXEnd(String xend) {
        this.xend = xend;
    }

    public void setYStart(String ystart) {
        this.ystart = ystart;
    }

    public void setYEnd(String yend) {
        this.yend = yend;
    }
    
    // getters
    public String getText() {
        return text;
    }
    
    public String getFont() {
        return font;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getXStart() {
        return xstart;
    }

    public String getXEnd() {
        return xend;
    }

    public String getYStart() {
        return ystart;
    }

    public String getYEnd() {
        return yend;
    }   
}
