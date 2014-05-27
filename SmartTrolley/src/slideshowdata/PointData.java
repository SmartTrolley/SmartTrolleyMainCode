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
public class PointData {
	
	@Attribute (name = "num", required=true)
	public int num;
	
	@Attribute (name = "x", required=true)
	public int x;
	
	@Attribute (name = "y", required=true)
	public int y;
	

}
