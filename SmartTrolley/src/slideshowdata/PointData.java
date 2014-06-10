package slideshowdata;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
/**
* SmartTrolley Data Class for Point
* includes getters and setters for this data and any attributes or elements for parsing
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 27 May 2014]
**/
@Root (strict = false)
public class PointData{
	
	@Attribute (name = "num", required=true)
	private int num;
	
	@Attribute (name = "x", required=true)
	private int x;
	
	@Attribute (name = "y", required=true)
	private int y;

	public int getNum() {
		return num;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}	

	public void setNum(int num) {
		this.num = num;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	

}
