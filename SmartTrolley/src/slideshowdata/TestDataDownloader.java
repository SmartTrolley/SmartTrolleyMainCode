/**
* SmartTrolley
*
* Tests DataDownloader.java with the user story: User can download data from SQL database into data classes
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 03 Jun 2014]
**/
package slideshowdata;

import org.junit.BeforeClass;
import org.junit.Test;


public class TestDataDownloader {

	private PWSParser parser;
	
	private static DataUploader dataUploader; 
	private static DataDownloader dataDownloader; 
	private String fileName = " ../../XMLDocs/dynamDomfinal.xml";
	
	/** 
	* Creates an instance of SqlConnection before the tests are performed
	**/
	@BeforeClass
	public static void setup(){
		dataUploader = new DataUploader();
		dataDownloader = new DataDownloader();
	}
	
	@Test
	public void test() {
		
//		parser  =  new PWSParser();
//		SlideShowData slideShowData = parser.read(fileName);
//		dataUploader.uploadXmlData(slideShowData);
//		
//		SmartTrolleyToolBox.print("Starting download");
//		SlideShowData slideshow = dataDownloader.populateSlideshow(1);
//		SmartTrolleyToolBox.print("Finished download");
	
		try{
//			assertEquals(1, slideshow.getSlides().get(0).getId());
//			assertEquals(1060, slideshow.getSlides().get(0).getDuration());
//			assertEquals("resources/video/video/monstersinc_high.mpg", slideshow.getSlides().get(0).getVideos().get(0).getUrlname());
//			assertEquals(42, slideshow.getSlides().get(0).getTexts().get(0).getDuration());
//			assertEquals(true, slideshow.getSlides().get(0).getTexts().get(0).getTextbodies().get(0).getBold());
//			assertEquals("If this works, I will be happy S2T1TB2", slideshow.getSlides().get(0).getTexts().get(0).getTextbodies().get(1).getTextstring());
//			assertEquals("Text must be within a “bounding” empty text box with the desired formatting S2T2TB1", slideshow.getSlides().get(0).getTexts().get(1).getTextbodies().get(0).getTextstring());
//			assertEquals("C:/Users/Public/Music/Sample Music/Kalimba.mp3", slideshow.getSlides().get(0).getAudios().get(0).getUrlname());
//			assertEquals("resources/images/Desert.jpg", slideshow.getSlides().get(0).getImages().get(0).getUrlname());
//			assertEquals("#F79646", slideshow.getSlides().get(0).getShapes().get(1).getFillcolor());
//			assertEquals(2, slideshow.getSlides().get(0).getShapes().get(1).getPoints().get(2).getNum());
//			assertEquals(42, slideshow.getSlides().get(0).getShapes().get(1).getPoints().get(2).getY());
		}

		finally{
			//This section clears the database of what the test created
			dataUploader.deleteContentAndResetAutoIncrement("defaults");
			dataUploader.deleteContentAndResetAutoIncrement("document_info_data");
			dataUploader.deleteContentAndResetAutoIncrement("slide");
			dataUploader.deleteContentAndResetAutoIncrement("audio");
			dataUploader.deleteContentAndResetAutoIncrement("image_slide");
			dataUploader.deleteContentAndResetAutoIncrement("point");
			dataUploader.deleteContentAndResetAutoIncrement("shape");
			dataUploader.deleteContentAndResetAutoIncrement("text");
			dataUploader.deleteContentAndResetAutoIncrement("textbody");
			dataUploader.deleteContentAndResetAutoIncrement("video");

			dataUploader.deleteContentAndResetAutoIncrement("lists");
			dataUploader.deleteContentAndResetAutoIncrement("lists_products");
			dataUploader.deleteContentAndResetAutoIncrement("products");
			
			
//			dataUploader.deleteLastList();
//			dataUploader.deleteLastList();
//			dataUploader.deleteLastProduct();
//			dataUploader.deleteLastProduct();
//			dataUploader.deleteLastProduct();
//			dataUploader.deleteLastProduct();
//			dataUploader.deleteLastProduct();
//			dataUploader.deleteLastProduct();
//			dataUploader.deleteLastProduct();
//			dataUploader.deleteLastProduct();
//			dataUploader.deleteLastProduct();
//			dataUploader.deleteLastProduct();
			
		}
		
	}

}

