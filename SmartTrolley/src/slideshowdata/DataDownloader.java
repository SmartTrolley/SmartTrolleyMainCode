package slideshowdata;

import java.util.ArrayList;

import smarttrolleygui.Product;
import javafx.collections.ObservableList;
import DatabaseConnectors.SqlConnection;

public class DataDownloader extends SqlConnection{

	private ObservableList<Product> slides;
	private SlideShowData slideshowdata;
	private ArrayList<SlideData> slidedatalist;
	private ArrayList<ArrayList<TextData>>  texts;
	private ArrayList<TextBodyData>  textbodies;
	
	public SlideShowData populateSlideshow(int ListId){
		int i = 0;
		slides = getList(ListId);
		
		while(i>slides.size()){
			
			
			int productid = slides.get(i).getId();
			
			ArrayList<TextData> textdata = (ArrayList<TextData>)getSpecificData("text", "ProductID", ""+productid);
			
			ArrayList<TextBodyData> textbodydata = (ArrayList<TextBodyData>)getSpecificData("textbody", "ProductID", ""+productid);
			
			
			ArrayList<SlideData> slidedata = (ArrayList<SlideData>)getSpecificData("slide", "ProductID", ""+productid);
			
		
			textbodies.add(textbodydata);
			textdata.setTextbodies(textbodies);
			
			texts.add(textdata);
			slidedata.setTexts(texts);
			slidedatalist.add(slidedata);
			
			
			i++;
		}
		
		slideshowdata.setSlides(slidedatalist);
		
		return results;
		
		
	}
	
	
}

