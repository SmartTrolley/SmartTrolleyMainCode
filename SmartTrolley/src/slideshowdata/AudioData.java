package slideshowdata;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
* SmartTrolley Data Class for Audio
* includes getters and setters for this data and any attributes or elements for parsing
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 27 May 2014]
**/
@Root (strict = false)
public class AudioData{
	
	@Attribute (name = "urlname", required=true)
	private String urlname;
	
	@Attribute (name = "starttime", required=false)
	private int starttime;
	
	@Attribute (name = "loop", required=false)
	private Boolean loop;

	public String getUrlname() {
		return urlname;
	}

	public int getStarttime() {
		return starttime;
	}

	public Boolean getLoop() {
		return loop;
	}

	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}

	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}

	public void setLoop(Boolean loop) {
		this.loop = loop;
	}
	
}
