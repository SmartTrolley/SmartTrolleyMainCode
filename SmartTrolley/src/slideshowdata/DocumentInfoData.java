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
public class DocumentInfoData {
	@Element (name = "author")
	public String author;
	@Element (name = "version")
	public String version;
	@Element (name = "title")
	public String title;
	@Element (name = "comment")
	public String comment;
	@Element (name = "width")
	public int width;
	@Element (name = "height")
	public int height;

}