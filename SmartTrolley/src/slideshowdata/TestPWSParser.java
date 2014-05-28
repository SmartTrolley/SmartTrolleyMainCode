/**
* SmartTrolley
*
* Tests PWSParser.java with the user story: User can load PWS compatible XML File into program
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 26 May 2014]
**/
package slideshowdata;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import DatabaseConnectors.SqlConnection;


import Printing.SmartTrolleyPrint;

public class TestPWSParser {
	
	private PWSParser parser;
	private SlideShowData data;
	//private String fileName = " ../../XMLDocs/dynamDomfinal.xml";
	private String fileName = "/Volumes/Macintosh HD/Users/samgeering/Documents/SWENG/SmartTrolleyMainCode/SmartTrolley/XMLDocs/dynamDomfinal.xml";
	private SlideData firstSlide;
	private TextData firstText;
	private VideoData firstVideo;
	private ImageData firstImage;
	private AudioData firstAudio;	
	private ShapeData firstShape; 	
	private PointData firstPoint; 
	
	@Before
	public void setUp() throws Exception {
		
		parser  =  new PWSParser();
		data = new SlideShowData();
		data = parser.read(fileName);
		firstSlide = data.getSlides().get(0);
		firstText = firstSlide.getTexts().get(0);
		firstVideo = firstSlide.getVideos().get(0);
		firstImage = firstSlide.getImages().get(0);
		firstAudio = firstSlide.getAudios().get(0);
		firstShape = firstSlide.getShapes().get(0);
		firstPoint = firstShape.getPoints().get(0);
		
	}
	
	@Test
	public void correctSlideAttributeTest(){
		assertEquals(data.getSlides().get(0).getId(), 0);
		assertEquals(data.getSlides().get(0).getDuration(), 60);
		assertEquals(data.getSlides().get(0).getLastSlide(), true);
	}
	
	@Test
	public void correctDocumentInfoTest() throws Exception{
		
		assertEquals(data.getDocumentinfo().getAuthor() , "D6 Digital");
		assertEquals(data.getDocumentinfo().getVersion() , "2.1");
		assertEquals(data.getDocumentinfo().getTitle(), "This is the first chapter ");
		assertEquals(data.getDocumentinfo().getComment() , "example comment for chapter 1");
		assertEquals(data.getDocumentinfo().getWidth() , 720);
		assertEquals(data.getDocumentinfo().getHeight() , 540);
		
		SmartTrolleyPrint.print("DocumentInfo Data: " + data.getDocumentinfo().getAuthor() + ", " + data.getDocumentinfo().getAuthor() 
				+ ", "  + data.getDocumentinfo().getTitle() + ", " 	+ data.getDocumentinfo().getComment() + ", "  + data.getDocumentinfo().getWidth() 
				+ ", "  + data.getDocumentinfo().getHeight() + "\n");
	}
	
	@Test
	public void correctDefaultsTest() throws Exception{
		
		assertEquals(data.getDefaults().getBackgroundcolor() ,"#948A54");
		assertEquals(data.getDefaults().getFont() ,"Papyrus");
		assertEquals(data.getDefaults().getFontsize() , 18);
		assertEquals(data.getDefaults().getFontcolor() ,"#FFFFFF");
		assertEquals(data.getDefaults().getLinecolor() ,"#5C4776");
		assertEquals(data.getDefaults().getFillcolor() ,"#8064A2");
		
		SmartTrolleyPrint.print("Defaults Data: " + data.getDefaults().getBackgroundcolor() + ", " + data.getDefaults().getFont()
				+ ", "  + data.getDefaults().getFontsize()	+ ", " 	+ data.getDefaults().getFontcolor() + ", "  + data.getDefaults().getLinecolor() 
				+ ", "  + data.getDefaults().getFillcolor() + "\n");
		
	}
	
	@Test
	public void correctSlideTest() throws Exception{
		
		assertEquals(firstSlide.getId() , 0);
		assertEquals(firstSlide.getDuration() , 60);
		assertEquals(firstSlide.getLastSlide() , true);
		
		SmartTrolleyPrint.print("Slide Data: " + firstSlide.getId() + ", " + firstSlide.getDuration() 
				+ ", "  + firstSlide.getLastSlide() + "\n");
		
	}
	
	@Test
	public void correctTextAttributeTest() throws Exception{
		
		assertEquals(firstText.getXstart() , 20);
		assertEquals(firstText.getYstart() , 291);
		assertEquals(firstText.getXend() , 505);
		assertEquals(firstText.getYend() , 441);
		assertEquals(firstText.getLayer() , 17);
		assertEquals(firstText.getDuration() , 0);
		assertEquals(firstText.getStarttime() , 0);
		assertEquals(firstText.getFont() , "Papyrus");
		assertEquals(firstText.getFontcolor() , "#000000");
		assertEquals(firstText.getFontsize() , 16);
		
		SmartTrolleyPrint.print("Text Attribute Data: " + firstText.getXstart() + ", " + firstText.getYstart() + ", "  + firstText.getXend()
				+ ", " 	+ firstText.getYend() + ", "  + firstText.getLayer() + ", "  + firstText.getDuration() + ", "  + firstText.getStarttime()
				+ ", "  + firstText.getFont() + ", " + firstText.getFontcolor() + ", " + firstText.getFontsize() + "\n");
	}
	
	@Test
	public void correctTextbodyTest() throws Exception{
		
		int i = 0;
		
		assertEquals(firstText.getTextbodies().get(i).getBranch() , 3);
		assertEquals(firstText.getTextbodies().get(i).getItalic() , true);
		assertEquals(firstText.getTextbodies().get(i).getBold() , true);
		assertEquals(firstText.getTextbodies().get(i).getUnderlined() , false);
		assertEquals(firstText.getTextbodies().get(i).getTextstring() , "Text must be within a “bounding” empty text box with the desired formatting");
		
		
		SmartTrolleyPrint.print("Textbody Data: " + firstText.getTextbodies().get(i).getBranch() + ", " + firstText.getTextbodies().get(i).getItalic() 
				+ ", " + firstText.getTextbodies().get(i).getBold()	+ ", " + firstText.getTextbodies().get(i).getUnderlined()
				+  ", " + firstText.getTextbodies().get(i).getTextstring() + "\n");
		
		i = 1;
		
		assertEquals(firstText.getTextbodies().get(i).getBranch() , 15);
		assertEquals(firstText.getTextbodies().get(i).getItalic() , false);
		assertEquals(firstText.getTextbodies().get(i).getBold() , false);
		assertEquals(firstText.getTextbodies().get(i).getUnderlined() , true);
		assertEquals(firstText.getTextbodies().get(i).getTextstring() , "If this works, I will be happy");
		
		SmartTrolleyPrint.print("Textbody Data: " + firstText.getTextbodies().get(i).getBranch() + ", " + firstText.getTextbodies().get(i).getItalic() 
				+ ", " + firstText.getTextbodies().get(i).getBold()	+ ", " + firstText.getTextbodies().get(i).getUnderlined()
				+  ", " + firstText.getTextbodies().get(i).getTextstring() + "\n");
	}
	
	@Test
	public void correctVideoTest() throws Exception{
		
		assertEquals(firstVideo.getUrlname() ,"resources/video/video/monstersinc_high.mpg");
		assertEquals(firstVideo.getXstart() , 456);
		assertEquals(firstVideo.getYstart() , 402);
		assertEquals(firstVideo.getWidth(), 242);
		assertEquals(firstVideo.getHeight() , 126);
		assertEquals(firstVideo.getLayer() , 23);
		assertEquals(firstVideo.getStarttime() , 0);
		assertEquals(firstVideo.getDuration() , 0);
		assertEquals(firstVideo.getLoop() , false);
		
		SmartTrolleyPrint.print("Video Data: " + firstVideo.getUrlname() + ", " + firstVideo.getXstart() + ", "  + firstVideo.getYstart()
				+ ", " 	+ firstVideo.getWidth()+ ", "  + firstVideo.getHeight() + ", "  + firstVideo.getLayer() + firstVideo.getStarttime()
				+ ", "  + firstVideo.getDuration() + ", "  + firstVideo.getLoop() + "\n");
		
	}
	
	@Test
	public void correctImageTest() throws Exception{
		
		assertEquals(firstImage.getUrlname() ,"resources/images/Desert.jpg");
		assertEquals(firstImage.getXstart() ,383);
		assertEquals(firstImage.getYstart() , 15);
		assertEquals(firstImage.getWidth() , 272);
		assertEquals(firstImage.getHeight() , 122);
		assertEquals(firstImage.getLayer() , 3);
		assertEquals(firstImage.getDuration() , 0);
		assertEquals(firstImage.getStarttime() , 0);
		assertEquals(firstImage.getBranch() , 1);
		
		SmartTrolleyPrint.print("Image Data: " + firstImage.getUrlname() + ", " + firstImage.getXstart() + ", "  + firstImage.getYstart()
				+ ", " 	+ firstImage.getWidth() + ", "  + firstImage.getHeight()+ ", "  + firstImage.getLayer() + firstImage.getDuration()
				+ ", "  + firstImage.getStarttime() + ", "  + firstImage.getBranch() + "\n");
		
	}
	
	@Test
	public void correctAudioTest() throws Exception{
		
		assertEquals(firstAudio.getUrlname() ,"C:/Users/Public/Music/Sample Music/Kalimba.mp3");
		assertEquals(firstAudio.getStarttime() , 0);
		assertEquals(firstAudio.getLoop() , false);
		
		SmartTrolleyPrint.print("Audio Data: " + firstAudio.getUrlname() + ", " + firstAudio.getStarttime() + ", "  + firstAudio.getLoop() + "\n");
		
	}
	
	@Test
	public void correctShapeTest() throws Exception{
		
		assertEquals(firstShape.getDuration(), 0);
		assertEquals(firstShape.getStarttime(), 3);
		assertEquals(firstShape.getLayer(), 9);
		assertEquals(firstShape.getHeight(), 106);
		assertEquals(firstShape.getWidth(), 235);
		assertEquals(firstShape.getTotalpoints(), 8);
		assertEquals(firstShape.getLinecolor(), "#FFFFFF");
		assertEquals(firstShape.getFillcolor(), "#F79646");
		
		SmartTrolleyPrint.print("Shape Data: " + firstShape.getDuration() + ", " + firstAudio.getStarttime() + ", "  + firstShape.getLayer() 
				+ ", "  + firstShape.getHeight() + ", "  + firstShape.getWidth() + ", "  + firstShape.getTotalpoints() + ", "  + firstShape.getLinecolor() 
				+ ", "  + firstShape.getFillcolor() + "\n");
		
	}
		
	@Test
	public void correctPointTest() throws Exception{
		
		assertEquals(firstPoint.getX(), 269);
		assertEquals(firstPoint.getY(), 38);
		assertEquals(firstPoint.getNum(), 0);
		
		SmartTrolleyPrint.print("Number of points in shape: " + firstShape.getPoints().size());
		SmartTrolleyPrint.print("Point Data: " + firstPoint.getX() + ", " + firstPoint.getY() + ", "  + firstPoint.getNum() +  "\n");
		
	}

	@Test
	public void uploadCorrectSlideAttributeTest(){
		
		int slideIndex = 0;
		SlideData slidedata;
		SqlConnection sqlConnector = new SqlConnection();
		
		sqlConnector.deleteContentAndResetAutoIncrement("slide");
		
		while (slideIndex<firstSlide.getImages().size()){
			slidedata = data.getSlides().get(slideIndex);
			
			sqlConnector.addSlideContents(slideIndex, 
										  slidedata.getDuration(), 
										  "null", //slide descriptor no method yet!
										  slidedata.getLastSlide(), 
										  "null"); //background color no method yet!
			
			slideIndex++;
		}
		
	}
	
	@Test
	public void uploadCorrectDocumentInfoTest() {
		SqlConnection sqlConnector = new SqlConnection();

		sqlConnector.deleteDocumentDataContent();
		sqlConnector.addDocumentDataContent(data.getDocumentinfo().getAuthor(),
											data.getDocumentinfo().getAuthor(), 
											data.getDocumentinfo().getTitle(), 
											data.getDocumentinfo().getComment(), 
											data.getDocumentinfo().getWidth(), 
											data.getDocumentinfo().getHeight());
	}
	
	@Test
	public void uploadCorrectDefaultsTest() {
		SqlConnection sqlConnector = new SqlConnection();

		sqlConnector.deleteDefaultsContent();
		sqlConnector.addDefaultsContent(data.getDefaults().getBackgroundcolor(), 
										data.getDefaults().getFont(), 
										data.getDefaults().getFontsize(), 
										data.getDefaults().getLinecolor() , 
										data.getDefaults().getFillcolor());
	}
	
	@Test
	public void uploadCorrectTextAttributeAndTextbody(){
		
		int textIndex = 0;
		int textBodyIndex = 0;
		TextData textData = null;
		SqlConnection sqlConnector = new SqlConnection();
		
		sqlConnector.deleteContentAndResetAutoIncrement("text");
		sqlConnector.deleteContentAndResetAutoIncrement("textbody");
		
		while (textIndex<firstSlide.getTexts().size()){
			textData = firstSlide.getTexts().get(textIndex);
			
			sqlConnector.addTextContents(1, // productID
										textData.getFontsize(), 
										textData.getXstart(), 
										textData.getYstart(), 
										textData.getStarttime(), 
										textData.getDuration(), 
										textData.getLayer(), 
										textData.getXend(), 
										textData.getYend(), 
										textData.getFont(), 
										textData.getFontcolor());
			while (textBodyIndex<firstText.getTextbodies().size()){
				
				sqlConnector.addTextbodyContents(1, //ProductID, 
												 textIndex, //TextNo 
												 firstText.getTextbodies().get(textBodyIndex).getBranch(), 
												 firstText.getTextbodies().get(textBodyIndex).getBold(), 
												 firstText.getTextbodies().get(textBodyIndex).getItalic(), 
												 firstText.getTextbodies().get(textBodyIndex).getUnderlined(), 
												 firstText.getTextbodies().get(textBodyIndex).getTextstring());
				textBodyIndex++;
			}
			
			textBodyIndex=0;
			textIndex++;
		}
	}
	
	@Test
	public void uploadCorrectVideoTest(){
		
		int videoIndex = 0;
		VideoData videodata;
		SqlConnection sqlConnector = new SqlConnection();
		
		sqlConnector.deleteContentAndResetAutoIncrement("video");
		
		while (videoIndex<firstSlide.getVideos().size()){
			videodata = firstSlide.getVideos().get(videoIndex);
			
			sqlConnector.addVideoContents(1, //ProductID 
										  videodata.getUrlname(), 
										  videodata.getStarttime(), 
										  videodata.getLoop(), 
										  videodata.getXstart(), 
										  videodata.getYstart(), 
										  videodata.getWidth(), 
										  videodata.getHeight(), 
										  videodata.getLayer(), 
										  videodata.getDuration());
			videoIndex++;
		}
		
	}
	
	
	@Test
	public void uploadCorrectImageTest(){
		
		int ImageIndex = 0;
		ImageData imagedata;
		SqlConnection sqlConnector = new SqlConnection();
		
		sqlConnector.deleteContentAndResetAutoIncrement("image_slide");
		
		while (ImageIndex<firstSlide.getImages().size()){
			imagedata = firstSlide.getImages().get(ImageIndex);
			
			sqlConnector.addImageContents(imagedata.getUrlname(), 
										  imagedata.getXstart(), 
										  imagedata.getYstart(), 
										  imagedata.getWidth(), 
										  imagedata.getHeight(), 
										  imagedata.getStarttime(), 
										  imagedata.getDuration(), 
										  imagedata.getLayer(), 
										  imagedata.getBranch());
			ImageIndex++;
		}
		
	}
	
	@Test
	public void uploadCorrectAudioTest(){
		
		int audioIndex = 0;
		AudioData audiodata;
		SqlConnection sqlConnector = new SqlConnection();
		
		sqlConnector.deleteContentAndResetAutoIncrement("audio");
		
		while (audioIndex<firstSlide.getAudios().size()){
			audiodata = firstSlide.getAudios().get(audioIndex);
			
			sqlConnector.addAudioTableContents(1, //ProductID
											   audiodata.getStarttime(), 
											   audiodata.getUrlname(), 
											   50, 
											   audiodata.getLoop());
			audioIndex++;
		}
		
	}
	
	@Test
	public void uploadCorrectShapeAndPointsTest(){
		
		int shapeIndex = 0;
		int pointIndex = 0;
		ShapeData shape = null;
		PointData point = null;
		SqlConnection sqlConnector = new SqlConnection();
		
		sqlConnector.deleteContentAndResetAutoIncrement("shape");
		sqlConnector.deleteContentAndResetAutoIncrement("point");
		
		while (shapeIndex<firstSlide.getShapes().size()){
			shape = firstSlide.getShapes().get(shapeIndex);
			
			sqlConnector.addShapeContents(1, //productID
											shape.getTotalpoints(), 
											shape.getWidth(), 
											shape.getHeight(),
											shape.getStarttime(), 
											shape.getDuration(), 
											shape.getLayer(), 
											1, //branch
											shape.getFillcolor(), 
											shape.getLinecolor());
			while (pointIndex<firstShape.getPoints().size()){
				point = firstShape.getPoints().get(pointIndex);
				
				sqlConnector.addPointContents(1, //productId
											  shapeIndex, //ShapeNo
											  point.getNum(), 
											  point.getX(), 
											  point.getY());
				pointIndex++;
			}
			
			pointIndex=0;
			shapeIndex++;
		}
	}

	//possibly change all the productID values for a value representing the slideID
	//Does not work for multiple slides
	//Need to make method in sqlconnection that will do what these upload tests do
	
}

/**************End of TestPWSParser.java**************/