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
public class SlideData implements DataType{
	
	@ElementList (entry = "text", inline = true, required = false) 
	private ArrayList<TextData>  texts;
	
	@ElementList (entry = "shape", inline = true, required = false)
	private ArrayList<ShapeData>  shapes;
	
	@ElementList (entry = "image", inline = true, required = false) 
	private ArrayList<ImageData>  images;
	
	@ElementList (entry = "audio", inline = true, required = false)
	private ArrayList<AudioData>  audios;
	
	@ElementList (entry = "video", inline = true, required = false)
	private ArrayList<VideoData>  videos;
	
	@Attribute (name = "id", required=true)
	private int id;
	
	@Attribute (name = "duration", required=false)
	private int duration;
	
	@Attribute (name = "lastSlide", required=false)
	private Boolean lastSlide;
	
	

	public int getId() {
		return id;
	}

	public int getDuration() {
		return duration;
	}

	public Boolean getLastSlide() {
		return lastSlide;
	}

	public ArrayList<TextData> getTexts() {
		return texts;
	}

	public ArrayList<ShapeData> getShapes() {
		return shapes;
	}

	public ArrayList<ImageData> getImages() {
		return images;
	}

	public ArrayList<AudioData> getAudios() {
		return audios;
	}

	public ArrayList<VideoData> getVideos() {
		return videos;
	}
	

	public void setTexts(ArrayList<TextData> texts) {
		this.texts = texts;
	}

	public void setShapes(ArrayList<ShapeData> shapes) {
		this.shapes = shapes;
	}

	public void setImages(ArrayList<ImageData> images) {
		this.images = images;
	}

	public void setAudios(ArrayList<AudioData> audios) {
		this.audios = audios;
	}

	public void setVideos(ArrayList<VideoData> videos) {
		this.videos = videos;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setLastSlide(Boolean lastSlide) {
		this.lastSlide = lastSlide;
	}
	
	
}
