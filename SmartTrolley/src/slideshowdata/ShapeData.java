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
public class ShapeData {

	@Attribute (name = "fillcolor", required=false)
	public String fillcolor;
	
	@Attribute (name = "linecolor", required=false)
	public String linecolor;
	
	@Attribute (name = "layer", required=false)
	public int layer;
	
	@Attribute (name = "duration", required=false)
	public int duration;
	
	@Attribute (name = "starttime", required=false)
	public int starttime;
	
	@Attribute (name = "totalpoints", required=true)
	public int totalpoints;
	
	@Attribute (name = "width", required=true)
	public int width;
	
	@Attribute (name = "height", required=true)
	public int height;
	
	
	
}
