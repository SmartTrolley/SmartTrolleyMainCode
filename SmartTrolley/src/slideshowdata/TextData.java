/**
* SmartTrolley
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 27 May 2014]
**/
package slideshowdata;


import java.util.ArrayList;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root (strict = false)
public class TextData {
	
	@ElementList (entry = "textbody", inline = true)
	private ArrayList<TextBodyData> textbodies;
		
	@Attribute (name = "xstart", required=true)
	private int xstart;
	
	@Attribute (name = "ystart", required=true)
	private int ystart;
	
	@Attribute (name = "xend", required=true)
	private int xend;
	
	@Attribute (name = "yend", required=true)
	private int yend;
	
	@Attribute (name = "layer", required=false)
	private int layer;
	
	@Attribute (name = "duration", required=false)
	private int duration;
	
	@Attribute (name = "starttime", required=false)
	private int starttime;
	
	@Attribute (name = "font", required=false)
	private String font;
	
	@Attribute (name = "fontcolor", required=false)
	private String fontcolor;
	
	@Attribute (name = "fontsize", required=false)
	private int fontsize;

	public ArrayList<TextBodyData> getTextbodies() {
		return textbodies;
	}

	public int getXstart() {
		return xstart;
	}

	public int getYstart() {
		return ystart;
	}

	public int getXend() {
		return xend;
	}

	public int getYend() {
		return yend;
	}

	public int getLayer() {
		return layer;
	}

	public int getDuration() {
		return duration;
	}

	public int getStarttime() {
		return starttime;
	}

	public String getFont() {
		return font;
	}

	public String getFontcolor() {
		return fontcolor;
	}

	public int getFontsize() {
		return fontsize;
	}


}
