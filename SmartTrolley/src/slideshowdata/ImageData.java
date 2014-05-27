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
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root (strict = false)
public class ImageData {
	
	@Attribute (name = "urlname", required=true)
	public String urlname;
	
	@Attribute (name = "xstart", required=true)
	public int xstart;
	
	@Attribute (name = "ystart", required=true)
	public int ystart;
	
	@Attribute (name = "width", required=false)
	public int width;
	
	@Attribute (name = "height", required=false)
	public int height;
	
	@Attribute (name = "layer", required=true)
	public int layer;
	
	@Attribute (name = "duration", required=true)
	public int duration;
	
	@Attribute (name = "starttime", required=true)
	public int starttime;
	
	@Attribute (name = "branch", required=true)
	public int branch;
	

}
