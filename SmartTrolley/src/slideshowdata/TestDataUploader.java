package slideshowdata;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		
		ArrayList<AudioData> audiodatalist = (ArrayList<AudioData>)dataUploader.getSpecificData("audio", "starttime", "0");
		assertEquals(false, audiodatalist.get(0).getLoop());
		assertEquals(0, audiodatalist.get(0).getStarttime());
		assertEquals("img/SampleProducts/large/ariel.jpg", audiodatalist.get(0).getUrlname());
		
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
		
		ArrayList<PointData> pointdatalist = (ArrayList<PointData>)dataUploader.getSpecificData("point", "ProductID", "1");
		assertEquals(3,pointdatalist.get(0).getNum());
		assertEquals(56,pointdatalist.get(0).getX());
		assertEquals(75,pointdatalist.get(0).getY());
		
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
		
		ArrayList<ShapeData> shapedatalist = (ArrayList<ShapeData>)dataUploader.getSpecificData("shape", "totalpoints", "4");
		assertEquals("black", shapedatalist.get(0).getFillcolor());
		assertEquals("blue", shapedatalist.get(0).getLinecolor());
		assertEquals(3, shapedatalist.get(0).getLayer());
		assertEquals(10, shapedatalist.get(0).getDuration());
		assertEquals(0, shapedatalist.get(0).getStarttime());
		assertEquals(4, shapedatalist.get(0).getTotalpoints());
		assertEquals(30, shapedatalist.get(0).getWidth());
		assertEquals(30, shapedatalist.get(0).getHeight());
		
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
		
		ArrayList<TextData> textdatalist = (ArrayList<TextData>)dataUploader.getSpecificData("text", "font", "comic sans");
		assertEquals(30, textdatalist.get(0).getFontsize());
		assertEquals(30, textdatalist.get(0).getXstart());
		assertEquals(0, textdatalist.get(0).getYstart());
		assertEquals(10, textdatalist.get(0).getStarttime());
		assertEquals(3, textdatalist.get(0).getDuration());
		assertEquals(4, textdatalist.get(0).getLayer());
		assertEquals(50, textdatalist.get(0).getXend());
		assertEquals(50, textdatalist.get(0).getYend());
		
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
		
		ArrayList<TextBodyData>textbodydatalist = (ArrayList<TextBodyData>)dataUploader.getSpecificData("textbody", "TextNo", "30");
		assertEquals(false, textbodydatalist.get(0).getBold());
		assertEquals(false, textbodydatalist.get(0).getItalic());
		assertEquals(false, textbodydatalist.get(0).getUnderlined());
		assertEquals("LLLLLOOOOKKIIIE, some text", textbodydatalist.get(0).getTextstring());
		assertEquals(30, textbodydatalist.get(0).getBranch());
		
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
		
		ArrayList<VideoData> videodatalist = (ArrayList<VideoData>)dataUploader.getSpecificData("video", "slideID", "1");
		assertEquals(15,videodatalist.get(0).getDuration());
		assertEquals(1,videodatalist.get(0).getLayer());
		assertEquals(60,videodatalist.get(0).getHeight());
		assertEquals(100,videodatalist.get(0).getWidth());
		assertEquals(false,videodatalist.get(0).getLoop());
		assertEquals(0,videodatalist.get(0).getStarttime());
		assertEquals(40,videodatalist.get(0).getXstart());
		assertEquals(70,videodatalist.get(0).getYstart());
		assertEquals(15,videodatalist.get(0).getDuration());
		assertEquals("SOME URL",videodatalist.get(0).getUrlname());
		
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
		
		ArrayList<ImageData> imagedatalist = (ArrayList<ImageData>)dataUploader.getSpecificData("image_slide", "xstart", "30");
		assertEquals(3,imagedatalist.get(0).getBranch());
		assertEquals(10,imagedatalist.get(0).getDuration());
		assertEquals(60,imagedatalist.get(0).getHeight());
		assertEquals(2,imagedatalist.get(0).getLayer());
		assertEquals(3,imagedatalist.get(0).getStarttime());
		assertEquals("SOME URL",imagedatalist.get(0).getUrlname());
		assertEquals(50,imagedatalist.get(0).getWidth());
		assertEquals(30,imagedatalist.get(0).getXstart());
		assertEquals(40,imagedatalist.get(0).getYstart());
		
		dataUploader.deleteLastContentAndResetAutoIncrement("image_slide");
	}

//	@Test
	public void uploadDataTest(){

//		dataUploader.uploadXmlData(fileName);	
		
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
		
//		dataUploader.deleteLastList();
//		dataUploader.deleteLastProduct();
//		dataUploader.deleteLastProduct();
	}
}