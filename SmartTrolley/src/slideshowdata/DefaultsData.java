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

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root (strict = false)
public class DefaultsData {

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
	
	

}