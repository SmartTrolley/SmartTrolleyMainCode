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
public class SlideData {
	
	@Attribute (name = "id", required=true)
	public int id;
	
	@Attribute (name = "duration", required=false)
	public int duration;
	
	@Attribute (name = "lastSlide", required=false)
	public Boolean lastSlide;
	
	@Element (name = "text", required = false) 
	TextData  textdata;
	
	@Element (name = "shape", required = false)
	ShapeData  shapedata;
	
	@Element (name = "image", required = false) 
	ImageData  imagedata;
	
	@Element (name = "audio", required = false)
	AudioData  audiodata;
	
	@Element (name = "video", required = false)
	VideoData  videodata;
}
