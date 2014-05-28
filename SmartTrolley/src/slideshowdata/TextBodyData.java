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
	private int branch;

	@Attribute (name = "italic", required=false)
	private Boolean italic;
	
	@Attribute (name = "bold", required=false)
	private Boolean bold;
	
	@Attribute (name = "underlined", required=false)
	private Boolean underlined;
	
	@Element (name = "textstring", required = false)
	private String textstring;
	
	
	public int getBranch() {
		return branch;
	}
	
	public Boolean getItalic() {
		return italic;
	}


	public Boolean getBold() {
		return bold;
	}


	public Boolean getUnderlined() {
		return underlined;
	}


	public String getTextstring() {
		return textstring;
	}


	
	
	
	
}
