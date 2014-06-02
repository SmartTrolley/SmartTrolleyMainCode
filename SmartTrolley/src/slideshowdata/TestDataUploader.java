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

		dataUploader.addDocumentDataContent(author, version, title, comment, width, height);
		
		DocumentInfoData docinfo = (DocumentInfoData)dataUploader.getSpecificData("document_info_data", "author", "Jack");
		assertEquals("1.0", docinfo.getVersion());
		assertEquals("some title", docinfo.getTitle());
		assertEquals("some trolling comment", docinfo.getComment());
		assertEquals(20, docinfo.getWidth());
		assertEquals(50, docinfo.getHeight());
		
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
		
		AudioData audiodata = (AudioData)dataUploader.getSpecificData("audio", "starttime", "0");
		assertEquals(false, audiodata.getLoop());
		assertEquals(0, audiodata.getStarttime());
		assertEquals("img/SampleProducts/large/ariel.jpg", audiodata.getUrlname());
		
		dataUploader.deleteLastContentAndResetAutoIncrement("audio");
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
		
		PointData pointdata = (PointData)dataUploader.getSpecificData("point", "ShapeNo", "1");
		assertEquals(3,pointdata.getNum());
		assertEquals(56,pointdata.getX());
		assertEquals(75,pointdata.getY());
		
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
		
		ShapeData shapedata = (ShapeData)dataUploader.getSpecificData("shape", "totalpoints", "4");
		assertEquals("black", shapedata.getFillcolor());
		assertEquals("blue", shapedata.getLinecolor());
		assertEquals(3, shapedata.getLayer());
		assertEquals(10, shapedata.getDuration());
		assertEquals(0, shapedata.getStarttime());
		assertEquals(4, shapedata.getTotalpoints());
		assertEquals(30, shapedata.getWidth());
		assertEquals(30, shapedata.getHeight());
		
		dataUploader.deleteLastContentAndResetAutoIncrement("shape");
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
		
		TextData textdata = (TextData)dataUploader.getSpecificData("text", "font", "comic sans");
		assertEquals(30, textdata.getFontsize());
		assertEquals(30, textdata.getXstart());
		assertEquals(0, textdata.getYstart());
		assertEquals(10, textdata.getStarttime());
		assertEquals(3, textdata.getDuration());
		assertEquals(4, textdata.getLayer());
		assertEquals(50, textdata.getXend());
		assertEquals(50, textdata.getYend());
		
		dataUploader.deleteLastContentAndResetAutoIncrement("text");
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
		
		TextBodyData textbodydata = (TextBodyData)dataUploader.getSpecificData("textbody", "TextNo", "30");
		assertEquals(false, textbodydata.getBold());
		assertEquals(false, textbodydata.getItalic());
		assertEquals(false, textbodydata.getUnderlined());
		assertEquals("LLLLLOOOOKKIIIE, some text", textbodydata.getTextstring());
		assertEquals(30, textbodydata.getBranch());
		
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
		
		VideoData videodata = (VideoData)dataUploader.getSpecificData("video", "slideID", "1");
		assertEquals(15,videodata.getDuration());
		assertEquals(1,videodata.getLayer());
		assertEquals(60,videodata.getHeight());
		assertEquals(100,videodata.getWidth());
		assertEquals(false,videodata.getLoop());
		assertEquals(0,videodata.getStarttime());
		assertEquals(40,videodata.getXstart());
		assertEquals(70,videodata.getYstart());
		assertEquals(15,videodata.getDuration());
		assertEquals("SOME URL",videodata.getUrlname());
		
		dataUploader.deleteLastContentAndResetAutoIncrement("video");
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
		
		ImageData imagedata = (ImageData)dataUploader.getSpecificData("image_slide", "xstart", "30");
		assertEquals(3,imagedata.getBranch());
		assertEquals(10,imagedata.getDuration());
		assertEquals(60,imagedata.getHeight());
		assertEquals(2,imagedata.getLayer());
		assertEquals(3,imagedata.getStarttime());
		assertEquals("SOME URL",imagedata.getUrlname());
		assertEquals(50,imagedata.getWidth());
		assertEquals(30,imagedata.getXstart());
		assertEquals(40,imagedata.getYstart());
		
		dataUploader.deleteLastContentAndResetAutoIncrement("image_slide");
	}

	@Test
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