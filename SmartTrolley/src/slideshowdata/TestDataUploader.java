package slideshowdata;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import Printing.SmartTrolleyPrint;


/**
 * @author Thomas & Sam
 * 
 * Test case for testing the SqlConnection
 * 
 */
public class TestDataUploader {
	
	private static DataUploader dataUploader; 
	private String fileName = " ../../XMLDocs/dynamDomfinal.xml";
	
	/**
	 * Creates an instance of SqlConnection before the tests are performed
	 */
	@BeforeClass
	public static void setup(){
		dataUploader = new DataUploader();
	}
	
	@After
	public  void tearDown(){
	}
	
	/**
	 * test that the addDocumentDataContent() method adds to the table
	 * documents_info_data on the database
	 */
	@Test
	public void addDocumentDataContentTest() {
		SmartTrolleyPrint.print("\n Start of addDocumentDataTest............");

		String author = "Jack", 
			   version = "1.0", 
			   title = "some title",
			   comment = "some trolling comment";
		int width = 20, height = 50;

//		dataUploader.addDocumentDataContent(author, version, title, comment, width, height);
		
		
		dataUploader.deleteDocumentDataContent();
	}


	/**
	 * test the addDefaultsContent() method adds to the table
	 * defaults on the database
	 */
	@Test
	public void addDefaultsContentTest(){
		SmartTrolleyPrint.print("\n Start of addDefaultsContentTest............");

		String backgroundcolor = "black",
			   font = "comic Sans",
			   fontcolor = "black",
			   linecolor = "black",
			   fillcolor = "white";
		int fontsize = 25;

		dataUploader.addDefaultsContent(backgroundcolor, font, fontsize, fontcolor, linecolor, fillcolor);
		DefaultsData defaults = (DefaultsData)dataUploader.getSpecificData("defaults", "backgroundcolor", "black");
		assertEquals("comic Sans", defaults.getFont());
		assertEquals("black", defaults.getLinecolor());
		assertEquals("white", defaults.getFillcolor());
		dataUploader.deleteDefaultsContent();
	}

	/**
	 * Tests that all data is deleted on the defaults list on the database
	 */
//	@Test
	public void deleteDefaultsContentTest(){
		SmartTrolleyPrint.print("\n Start of deleteDefaultsContentTest............");

		dataUploader.deleteLastContentAndResetAutoIncrement("defaults");
	}

	/**
	 * Test that contents is added to the audio table
	 */
	@Test
	public void addAudioTableContentsTest(){
		SmartTrolleyPrint.print("\n Start of addAudioTableContentsTest............");

		int productID = 1,
			startTime = 0,
			slideID = 1;
		String urlName = "img/SampleProducts/large/ariel.jpg";
		double volume = 50;
		boolean loop = false;

		dataUploader.addAudioTableContents(productID, slideID, urlName, startTime,  volume, loop);
		
		dataUploader.deleteLastContentAndResetAutoIncrement("audio");
	}

	/**
	 * Test that all data is deleted on the audio table on the database
	 */
//	@Test
	public void deleteAudioTableContentsTest(){
		SmartTrolleyPrint.print("\n Start of deleteAudioTableContentsTest............");

		dataUploader.deleteContentAndResetAutoIncrement("audio");
	}

	/**
	 * Test that contents is added to the point table
	 */
	@Test
	public void addPointContentsTest(){
		SmartTrolleyPrint.print("\n Start of addPointContentsTest............");

		int productid = 1,
			shapeNo = 1,
			slideID = 1,
			individualPointNo = 3,
			x = 56,
			y = 75;

		dataUploader.addPointContents(productid, slideID, shapeNo, individualPointNo, x, y);
		
		dataUploader.deleteLastContentAndResetAutoIncrement("point");
	}

	/**
	 * Test that all data is deleted on the point table on the database
	 */
//	@Test
	public void deletePointContentsTest(){
		SmartTrolleyPrint.print("\n Start of deleteAudioTableContentsTest............");

		dataUploader.deleteLastContentAndResetAutoIncrement("point");
	}

	/**
	 * Test that contents is added to the shape table
	 */
	@Test
	public void addShapeContentsTest(){
		SmartTrolleyPrint.print("\n Start of addShapeContentsTest............");

		int productid = 1,
			slideID = 1,
			totalPoints = 4,
			width = 30,
			height = 30,
			starttime = 0,
			duration = 10,
			layer = 3,
			branch = 4;
		String fillcolor = "black",
			   linecolor = "blue";

		dataUploader.addShapeContents(productid, slideID, totalPoints, width, height, starttime, duration, layer, branch, fillcolor, linecolor);
		
		dataUploader.deleteLastContentAndResetAutoIncrement("shape");
	}

	/**
	 * Test that all data is deleted on the shape table on the database
	 */
//	@Test
	public void deleteShapeContentsTest(){
		SmartTrolleyPrint.print("\n Start of deleteShapeContentsTest............");

		dataUploader.deleteContentAndResetAutoIncrement("shape");
	}

	/**
	 * Test that contents is added to the text table
	 */
	@Test
	public void addTextContentsTest(){
		SmartTrolleyPrint.print("\n Start of addTextContentsTest............");

		int productid = 1,
			slideID = 1,
			fontSize = 30,
			xStart = 30,
			yStart = 0,
			startTime = 10,
			duration = 3,
			layer = 4,
			xend = 50,
			yend = 50;
		String font = "comic sans",
			   FontColor = "blue";

		dataUploader.addTextContents(productid, slideID, fontSize, xStart, yStart, startTime, duration, layer, xend, yend, font, FontColor);
		
		dataUploader.deleteLastContentAndResetAutoIncrement("text");
	}

	/**
	 * Test that all data is deleted on the text table on the database
	 */
//	@Test
	public void deleteTextContentsTest(){
		SmartTrolleyPrint.print("\n Start of deleteTextContentsTest............");

		dataUploader.deleteContentAndResetAutoIncrement("text");
	}

	/**
	 * Test that contents is added to the textbody table
	 */
	@Test
	public void addTextbodyContentsTest(){
		SmartTrolleyPrint.print("\n Start of addTextbodyContentsTest............");

		int productid = 1,
			slideID = 1,
			TextNo = 30,
			Branch = 30;
		Boolean Bold = false,
				italic = false,
				underlined = false;
		String text = "LLLLLOOOOKKIIIE, some text";

		dataUploader.addTextbodyContents(productid, slideID, TextNo, Branch, Bold, italic, underlined, text);
		
		dataUploader.deleteContentAndResetAutoIncrement("textbody");
	}

	/**
	 * Test that all data is deleted on the textbody table on the database
	 */
//	@Test
	public void deleteTextbodyContentsTest(){
		SmartTrolleyPrint.print("\n Start of deleteTextbodyContentsTest............");

		dataUploader.deleteContentAndResetAutoIncrement("textbody");
	}

	/**
	 * Test that contents is added to the video table
	 */
	@Test
	public void addVideoContentsTest(){
		SmartTrolleyPrint.print("\n Start of addVideoContentsTest............");

		int productid = 1,
			slideID = 1,
			starttime = 0,
			xstart = 40,
			ystart = 70,
			width = 100,
			height = 60,
			layer = 1,
			duration = 15;
		String urlname = "SOME URL";
		boolean loop = false;

		dataUploader.addVideoContents(productid, slideID, urlname, starttime, loop, xstart, ystart, width, height, layer, duration);
		
		dataUploader.deleteLastContentAndResetAutoIncrement("video");
	}

	/**
	 * Test that all data is deleted on the video table on the database
	 */
//	@Test
	public void deleteVideoContentsTest(){
		SmartTrolleyPrint.print("\n Start of deleteVideoContentsTest............");

		dataUploader.deleteContentAndResetAutoIncrement("video");
	}

	/** 
	 * Test that contents is added to the Image table
	 */
	@Test
	public void addImageContentsTest(){
		SmartTrolleyPrint.print("\n Start of addImageContentsTest............");

		int productID = 1,
			slideID = 1,
			xstart = 30,
			ystart = 40,
			width = 50,
			height = 60,
			starttime = 3,
			duration = 10,
			layer = 2,
			branch = 3;
		String urlname = "SOME URL";

		dataUploader.addImageContents(productID, slideID, urlname, xstart, ystart, width, height, starttime, duration, layer, branch);
		
		dataUploader.deleteLastContentAndResetAutoIncrement("image");
	}

//	@Test
	public void uploadDataTest(){

		dataUploader.uploadXmlData(fileName);	
		
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
		
		dataUploader.deleteLastList();
		dataUploader.deleteLastProduct();
		dataUploader.deleteLastProduct();
	}
}