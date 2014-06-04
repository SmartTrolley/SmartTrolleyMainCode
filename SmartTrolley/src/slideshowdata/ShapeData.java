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

import java.util.ArrayList;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root (strict = false)
public class ShapeData implements DataType{
	
	private int shapeno;
	
	@ElementList (entry = "point", inline=true)
	private ArrayList<PointData> points;
	
	@Attribute (name = "fillcolor", required=false)
	private String fillcolor;
	
	@Attribute (name = "linecolor", required=false)
	private String linecolor;
	
	@Attribute (name = "layer", required=false)
	private int layer;
	
	@Attribute (name = "duration", required=false)
	private int duration;
	
	@Attribute (name = "starttime", required=false)
	private int starttime;
	
	@Attribute (name = "totalpoints", required=true)
	private int totalpoints;
	
	@Attribute (name = "width", required=true)
	private int width;
	
	@Attribute (name = "height", required=true)
	private int height;
	
	@Attribute (name = "branch", required=false)
	private int branch;

	public ArrayList<PointData> getPoints() {
		return points;
	}
	
	public int getShapeNo() {
			return shapeno;
		}
	
	public String getFillcolor() {
		return fillcolor;
	}

	public String getLinecolor() {
		return linecolor;
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

	public int getTotalpoints() {
		return totalpoints;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getBranch() {
		return branch;
	}
	

	public void setPoints(ArrayList<PointData> points) {
		this.points = points;
	}

	public void setFillcolor(String fillcolor) {
		this.fillcolor = fillcolor;
	}

	public void setLinecolor(String linecolor) {
		this.linecolor = linecolor;
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

	public void setTotalpoints(int totalpoints) {
		this.totalpoints = totalpoints;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setBranch(int branch) {
		this.branch = branch;
	}
	
	public void setShapeNo(int shapeno) {
		this.shapeno = shapeno;
	}
	
	
}
