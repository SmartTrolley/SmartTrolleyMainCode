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
public class VideoData implements DataType{
	
	@Attribute (name = "urlname", required=true)
	private String urlname;
	
	@Attribute (name = "xstart", required=true)
	private int xstart;
	
	@Attribute (name = "ystart", required=true)
	private int ystart;
	
	@Attribute (name = "width", required=false)
	private int width;
	
	@Attribute (name = "height", required=false)
	private int height;
	
	@Attribute (name = "layer", required=true)
	private int layer;
	
	@Attribute (name = "duration", required=true)
	private int duration;
	
	@Attribute (name = "starttime", required=true)
	private int starttime;
	
	@Attribute (name = "loop", required=true)
	private Boolean loop;

	public String getUrlname() {
		return urlname;
	}

	public int getXstart() {
		return xstart;
	}

	public int getYstart() {
		return ystart;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getLayer() {
		return layer;
	}

	public int getDuration() {
		return duration;
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

	public void setXstart(int xstart) {
		this.xstart = xstart;
	}

	public void setYstart(int ystart) {
		this.ystart = ystart;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}

	public void setLoop(Boolean loop) {
		this.loop = loop;
	}
	
	

}
