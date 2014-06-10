package slideshowdata;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
* SmartTrolley Data Class for Defaults
* includes getters and setters for this data and any attributes or elements for parsing
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 27 May 2014]
**/
@Root (strict = false)
public class DefaultsData{

	private int listid;
	
	@Element (name = "backgroundcolor")
	private String backgroundcolor;
	@Element (name = "font")
	private String font;
	@Element (name = "fontsize")
	private int fontsize;
	@Element (name = "fontcolor")
	private String fontcolor;
	@Element (name = "linecolor")
	private String linecolor;
	@Element (name = "fillcolor")
	private String fillcolor;
	
	public String getBackgroundcolor() {
		return backgroundcolor;
	}
	public String getFont() {
		return font;
	}
	public int getFontsize() {
		return fontsize;
	}
	public String getFontcolor() {
		return fontcolor;
	}
	public String getLinecolor() {
		return linecolor;
	}
	public String getFillcolor() {
		return fillcolor;
	}
	
	public int getListId() {
		return listid;
	}
	
	
	public void setBackgroundcolor(String backgroundcolor) {
		this.backgroundcolor = backgroundcolor;
	}
	public void setFont(String font) {
		this.font = font;
	}
	public void setFontsize(int fontsize) {
		this.fontsize = fontsize;
	}
	public void setFontcolor(String fontcolor) {
		this.fontcolor = fontcolor;
	}
	public void setLinecolor(String linecolor) {
		this.linecolor = linecolor;
	}
	public void setFillcolor(String fillcolor) {
		this.fillcolor = fillcolor;
	}
	public void setListId(int listid) {
		this.listid = listid;
		
	}
	
	

}