package slideshowdata;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import smarttrolleygui.Product;
import DatabaseConnectors.SqlConnection;

public class DataDownloader extends SqlConnection{

	private ObservableList<Product> slides;
	private ArrayList<SlideData> slidelist;
	
	public SlideShowData populateSlideshow(int ListId){
		
		int i = 0,
			index = 0;
		
		slides = getList(ListId);
		
		ArrayList<SlideData> slidelist = new ArrayList<SlideData>();
		SlideShowData slideshowdata = new SlideShowData();
		
		DefaultsData defaults = (DefaultsData)getSpecificData("defaults", "ListID", ""+ListId);
		DocumentInfoData documentinfo = (DocumentInfoData)getSpecificData("document_info_data", "ListID", ""+ListId);
		
		while(i<slides.size()){
			
			int		j = 0,
					k = 0;
			
			int productid = slides.get(i).getId();
			
			SlideData  slide = (SlideData)getSpecificData("slide", "ProductID", ""+productid);
			
			ArrayList<TextData> texts = (ArrayList<TextData>)getSpecificData("text", "ProductID", ""+productid);
			
			while(j<texts.size()){
				int textno = texts.get(j).getTextNo();
				ArrayList<TextBodyData> textbodies = (ArrayList<TextBodyData>)getSpecificData("textbody", "TextNo", ""+textno);
				texts.get(j).setTextbodies(textbodies);
				
				j++;
			}
			
			ArrayList<ShapeData>  shapes = (ArrayList<ShapeData>)getSpecificData("shape", "ProductID", ""+productid);
			
			while(k<shapes.size()){

				int shapeno = shapes.get(k).getShapeNo();
				ArrayList<PointData> points = (ArrayList<PointData>)getSpecificData("point", "ShapeNo", ""+shapeno);
				shapes.get(k).setPoints(points);
				
				k++;
			}
			
			ArrayList<ImageData>  images = (ArrayList<ImageData>)getSpecificData("image_slide", "ProductID", ""+productid);
			
			ArrayList<VideoData>  videos = (ArrayList<VideoData>)getSpecificData("video", "ProductID", ""+productid);
			
			ArrayList<AudioData>  audios = (ArrayList<AudioData>)getSpecificData("audio", "ProductID", ""+productid);
			
			
			slide.setTexts(texts);
			slide.setShapes(shapes);
			slide.setVideos(videos);
			slide.setAudios(audios);
			slide.setImages(images);
		
			
			slidelist.add(index, slide);
			
			index++;		
			i++;
		}
		
		
		
		slideshowdata.setSlides(slidelist);
		slideshowdata.setDefaults(defaults);
		slideshowdata.setDocumentinfo(documentinfo);
		
		return slideshowdata;
		
		
	}
}

