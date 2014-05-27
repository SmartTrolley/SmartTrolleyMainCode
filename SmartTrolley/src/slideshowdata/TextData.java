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
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root (strict = false)
public class TextData {
	
	@ElementList (name = "textbody")
	public ArrayList<TextBodyData> textbodydatalist;
		
	@Attribute (name = "xstart", required=true)
	public int xstart;
	
	@Attribute (name = "ystart", required=true)
	public int ystart;
	
	@Attribute (name = "xend", required=true)
	public int xend;
	
	@Attribute (name = "yend", required=true)
	public int yend;
	
	@Attribute (name = "layer", required=false)
	public int layer;
	
	@Attribute (name = "duration", required=false)
	public int duration;
	
	@Attribute (name = "starttime", required=false)
	public int starttime;
	
	@Attribute (name = "font", required=false)
	public String font;
	
	@Attribute (name = "fontcolor", required=false)
	public String fontcolor;
	
	@Attribute (name = "fontsize", required=false)
	public int fontsize;

	

	
	

}
