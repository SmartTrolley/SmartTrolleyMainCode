package slideshowdata;

import java.util.ArrayList;

import smarttrolleygui.Product;
import javafx.collections.ObservableList;
import DatabaseConnectors.SqlConnection;

public class DataDownloader extends SqlConnection{

	private ObservableList<Product> slides;
	private ArrayList<SlideData> slidelist;
	
	public SlideShowData populateSlideshow(int ListId){
		
		int i = 0;
		
		slides = getList(ListId);
		
		SlideShowData slideshowdata = new SlideShowData();
		
		while(i<slides.size()){
			
			int		j = 0,
					k = 0;
			
			int productid = slides.get(i).getId();
			
			SlideData  slide = (SlideData)getSpecificData("slide", "ProductID", ""+productid);
			
			ArrayList<TextData> texts = (ArrayList<TextData>)getSpecificData("text", "ProductID", ""+productid);
			
			while(j<texts.size()){
				ArrayList<TextBodyData> textbodies = (ArrayList<TextBodyData>)getSpecificData("textbody", "TextNo", ""+j);
				texts.get(j).setTextbodies(textbodies);
				
				j++;
			}
			
			ArrayList<ShapeData>  shapes = (ArrayList<ShapeData>)getSpecificData("shape", "ProductID", ""+productid);
			
			while(k<shapes.size()){
				
				ArrayList<PointData> points = (ArrayList<PointData>)getSpecificData("point", "ShapeNo", ""+k);
				shapes.get(k).setPoints(points);
				
				k++;
			}
			
			ArrayList<ImageData>  images = (ArrayList<ImageData>)getSpecificData("shape", "ProductID", ""+productid);
			
			ArrayList<VideoData>  videos = (ArrayList<VideoData>)getSpecificData("video", "ProductID", ""+productid);
			
			ArrayList<AudioData>  audios = (ArrayList<AudioData>)getSpecificData("audio", "ProductID", ""+productid);
			
			
			slide.setTexts(texts);
			slide.setShapes(shapes);
			slide.setVideos(videos);
			slide.setAudios(audios);
			slide.setImages(images);
			
			
			slide.setId(slide.getId());
			
			
			slidelist.add(slide);
					
			i++;
		}
		
		slideshowdata.setSlides(slidelist);
//		slideshowdata.setDefaults(defaults);
//		slideshowdata.setDocumentinfo(documentinfo);
		
		return slideshowdata;
		
		
	}
}

