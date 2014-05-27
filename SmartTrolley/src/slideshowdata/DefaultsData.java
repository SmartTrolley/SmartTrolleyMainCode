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
	public String backgroundcolor;
	@Element (name = "font")
	public String font;
	@Element (name = "fontsize")
	public int fontsize;
	@Element (name = "fontcolor")
	public String fontcolor;
	@Element (name = "linecolor")
	public String linecolor;
	@Element (name = "fillcolor")
	public String fillcolor;

}