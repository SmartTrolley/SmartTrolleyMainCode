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
public class AudioData {
	
	@Attribute (name = "urlname", required=true)
	public String urlname;
	
	@Attribute (name = "starttime", required=false)
	public int starttime;
	
	@Attribute (name = "loop", required=false)
	public Boolean loop;
	

}
