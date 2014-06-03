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

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Printing.SmartTrolleyPrint;

public class TestDataDownloader {

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
		
		dataUploader.uploadXmlData(fileName);	
		SlideShowData slideshow = dataDownloader.populateSlideshow(1);
		
		SmartTrolleyPrint.print("Product name :" + slideshow.getSlides().get(0).getProductName());
		SmartTrolleyPrint.print("Price  :" + slideshow.getSlides().get(0).getPrice());
		SmartTrolleyPrint.print("Text 1 duration :" + slideshow.getSlides().get(0).getTexts().get(0).getDuration());
		SmartTrolleyPrint.print("Text 1 Text number :" + slideshow.getSlides().get(0).getTexts().get(0).getTextNo());
		SmartTrolleyPrint.print("Text 1 Text number :" + slideshow.getSlides().get(0).getTexts().get(0));		
		SmartTrolleyPrint.print("Text 1 textbody 1 textstring :" + slideshow.getSlides().get(0).getTexts().get(0).getTextbodies().get(0).getTextstring());
		SmartTrolleyPrint.print("Text 1 textbody 1 Italic :" + slideshow.getSlides().get(0).getTexts().get(0).getTextbodies().get(0).getItalic());
		SmartTrolleyPrint.print("Text 1 textbody 1 Underlined :" + slideshow.getSlides().get(0).getTexts().get(0).getTextbodies().get(0).getUnderlined());
		
//		dataUploader.deleteContentAndResetAutoIncrement("defaults");
//		dataUploader.deleteContentAndResetAutoIncrement("document_info_data");
//		dataUploader.deleteContentAndResetAutoIncrement("slide");
//		dataUploader.deleteContentAndResetAutoIncrement("audio");
//		dataUploader.deleteContentAndResetAutoIncrement("image_slide");
//		dataUploader.deleteContentAndResetAutoIncrement("point");
//		dataUploader.deleteContentAndResetAutoIncrement("shape");
//		dataUploader.deleteContentAndResetAutoIncrement("text");
//		dataUploader.deleteContentAndResetAutoIncrement("textbody");
//		dataUploader.deleteContentAndResetAutoIncrement("video");
//		
//		dataUploader.deleteLastList();
//		dataUploader.deleteLastProduct();
//		dataUploader.deleteLastProduct();
	}

}

