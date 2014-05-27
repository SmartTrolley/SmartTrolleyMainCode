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
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root (strict = false)
public class TextBodyData {

	@Attribute (name = "branch", required=false)
	public int branch;
	
	@Attribute (name = "italic", required=false)
	public Boolean italic;
	
	@Attribute (name = "bold", required=false)
	public Boolean bold;
	
	@Attribute (name = "underlined", required=false)
	public Boolean underlined;
	
	@Element (name = "textstring", required = false)
	public String textstring;
	
}
