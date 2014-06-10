package slideshowdata;

import java.util.ArrayList;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
/**
* SmartTrolley Data Class for Slide
* includes getters and setters for this data and any attributes or elements for parsing
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 27 May 2014]
**/
@Root (strict = false)
public class SlideData{
	
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
	
	@Attribute (name = "product_name", required = false)
	private String productName;
	
	@Attribute (name = "imgURL", required = false)
	private String imgURL;
	
	@Attribute (name = "price", required = false)
	private double price;
	
	@Attribute (name = "categoryID", required = false)
	private int categoryID;
	
	@Attribute (name = "isFavourite", required = false)
	private int isFavourite;

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
	
	public String getProductName() {
		return productName;
	}

	public String getImgURL() {
		return imgURL;
	}

	public double getPrice() {
		return price;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public int getIsFavourite() {
		return isFavourite;
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

	public void setIsFavourite(int isFavourite) {
		this.isFavourite = isFavourite;
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

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public void setFavourite(int isFavourite) {
		this.isFavourite = isFavourite;
	}
}
