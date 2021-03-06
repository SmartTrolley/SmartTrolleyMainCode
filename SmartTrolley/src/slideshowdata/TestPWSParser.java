package slideshowdata;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;
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
public class TestPWSParser {
	
	private PWSParser parser;
	private SlideShowData data;
	private String fileName = " ../../XMLDocs/dynamDomfinal.xml";
	private SlideData firstSlide;
	private TextData firstText;
	private VideoData firstVideo;
	private ImageData firstImage;
	private AudioData firstAudio;	
	private ShapeData firstShape; 	
	private PointData firstPoint; 
	
	/**
	*setup class that parses the xml file and defines some fields
	*<p> Date Modified: 10 Jun 2014
	*/
	@Before
	public void setUp(){
		
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
	
	/**
	* Tests that the correct slide attribute info is parsed
	*<p> Date Modified: 10 Jun 2014
	*/
	@Test
	public void correctSlideAttributeTest(){
		assertEquals(data.getSlides().get(0).getId(), 0);
		assertEquals(data.getSlides().get(0).getDuration(), 60);
		assertEquals(data.getSlides().get(0).getLastSlide(), true);
	}
	
	/**
	* Tests that the correct Document info is parsed
	*<p> Date Modified: 10 Jun 2014
	*/
	@Test
	public void correctDocumentInfoTest(){
		
		assertEquals(data.getDocumentinfo().getAuthor() , "D6 Digital");
		assertEquals(data.getDocumentinfo().getVersion() , "2.1");
		assertEquals(data.getDocumentinfo().getTitle(), "This is the first chapter ");
		assertEquals(data.getDocumentinfo().getComment() , "example comment for chapter 1");
		assertEquals(data.getDocumentinfo().getWidth() , 720);
		assertEquals(data.getDocumentinfo().getHeight() , 540);
		
		SmartTrolleyToolBox.print("DocumentInfo Data: " + data.getDocumentinfo().getAuthor() + ", " + data.getDocumentinfo().getAuthor() 
				+ ", "  + data.getDocumentinfo().getTitle() + ", " 	+ data.getDocumentinfo().getComment() + ", "  + data.getDocumentinfo().getWidth() 
				+ ", "  + data.getDocumentinfo().getHeight() + "\n");
	}
	
	/**
	* Tests that the correct defaults info is parsed
	*<p> Date Modified: 10 Jun 2014
	*/
	@Test
	public void correctDefaultsTest(){
		
		assertEquals(data.getDefaults().getBackgroundcolor() ,"#948A54");
		assertEquals(data.getDefaults().getFont() ,"Papyrus");
		assertEquals(data.getDefaults().getFontsize() , 18);
		assertEquals(data.getDefaults().getFontcolor() ,"#FFFFFF");
		assertEquals(data.getDefaults().getLinecolor() ,"#5C4776");
		assertEquals(data.getDefaults().getFillcolor() ,"#8064A2");
		
		SmartTrolleyToolBox.print("Defaults Data: " + data.getDefaults().getBackgroundcolor() + ", " + data.getDefaults().getFont()
				+ ", "  + data.getDefaults().getFontsize()	+ ", " 	+ data.getDefaults().getFontcolor() + ", "  + data.getDefaults().getLinecolor() 
				+ ", "  + data.getDefaults().getFillcolor() + "\n");
		
	}
	
	/**
	* Tests that the correct defaults info is parsed
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 10 Jun 2014
	*/
	@Test
	public void correctSlideTest(){
		
		assertEquals(firstSlide.getId() , 0);
		assertEquals(firstSlide.getDuration() , 60);
		assertEquals(firstSlide.getLastSlide() , true);
		assertEquals(firstSlide.getProductName(), "Test Product Name");
		
		SmartTrolleyToolBox.print("Slide Data: " + firstSlide.getId() + ", " + firstSlide.getDuration() 
				+ ", "  + firstSlide.getLastSlide() + ", "  + firstSlide.getLastSlide() + firstSlide.getProductName() + "\n");
		
	}
	
	/**
	* Tests that the correct text info is parsed
	*<p> Date Modified: 10 Jun 2014
	*/
	@Test
	public void correctTextAttributeTest(){
		
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
		
		SmartTrolleyToolBox.print("Text Attribute Data: " + firstText.getXstart() + ", " + firstText.getYstart() + ", "  + firstText.getXend()
				+ ", " 	+ firstText.getYend() + ", "  + firstText.getLayer() + ", "  + firstText.getDuration() + ", "  + firstText.getStarttime()
				+ ", "  + firstText.getFont() + ", " + firstText.getFontcolor() + ", " + firstText.getFontsize() + "\n");
	}
	
	/**
	* Tests that the correct textbody info is parsed
	*<p> Date Modified: 10 Jun 2014
	*/
	@Test
	public void correctTextbodyTest(){
		
		int i = 0;
		
		assertEquals(firstText.getTextbodies().get(i).getBranch() , 3);
		assertEquals(firstText.getTextbodies().get(i).getItalic() , true);
		assertEquals(firstText.getTextbodies().get(i).getBold() , true);
		assertEquals(firstText.getTextbodies().get(i).getUnderlined() , false);
		assertEquals(firstText.getTextbodies().get(i).getTextstring() , "Text must be within a “bounding” empty text box with the desired formatting S1T1TB1");
		
		
		SmartTrolleyToolBox.print("Textbody Data: " + firstText.getTextbodies().get(i).getBranch() + ", " + firstText.getTextbodies().get(i).getItalic() 
				+ ", " + firstText.getTextbodies().get(i).getBold()	+ ", " + firstText.getTextbodies().get(i).getUnderlined()
				+  ", " + firstText.getTextbodies().get(i).getTextstring() + "\n");
		
		i = 1;
		
		assertEquals(firstText.getTextbodies().get(i).getBranch() , 15);
		assertEquals(firstText.getTextbodies().get(i).getItalic() , false);
		assertEquals(firstText.getTextbodies().get(i).getBold() , false);
		assertEquals(firstText.getTextbodies().get(i).getUnderlined() , true);
		assertEquals(firstText.getTextbodies().get(i).getTextstring() , "If this works, I will be happy S1T1TB2");
		
		SmartTrolleyToolBox.print("Textbody Data: " + firstText.getTextbodies().get(i).getBranch() + ", " + firstText.getTextbodies().get(i).getItalic() 
				+ ", " + firstText.getTextbodies().get(i).getBold()	+ ", " + firstText.getTextbodies().get(i).getUnderlined()
				+  ", " + firstText.getTextbodies().get(i).getTextstring() + "\n");
	}
	
	/**
	* Tests that the correct video info is parsed
	*<p> Date Modified: 10 Jun 2014
	*/
	@Test
	public void correctVideoTest(){
		
		assertEquals(firstVideo.getUrlname() ,"FirstVideoUrlCheck");
		assertEquals(firstVideo.getXstart() , 456);
		assertEquals(firstVideo.getYstart() , 402);
		assertEquals(firstVideo.getWidth(), 242);
		assertEquals(firstVideo.getHeight() , 126);
		assertEquals(firstVideo.getLayer() , 23);
		assertEquals(firstVideo.getStarttime() , 0);
		assertEquals(firstVideo.getDuration() , 0);
		assertEquals(firstVideo.getLoop() , false);
		
		SmartTrolleyToolBox.print("Video Data: " + firstVideo.getUrlname() + ", " + firstVideo.getXstart() + ", "  + firstVideo.getYstart()
				+ ", " 	+ firstVideo.getWidth()+ ", "  + firstVideo.getHeight() + ", "  + firstVideo.getLayer() + firstVideo.getStarttime()
				+ ", "  + firstVideo.getDuration() + ", "  + firstVideo.getLoop() + "\n");
		
	}
	
	/**
	* Tests that the correct image info is parsed
	*<p> Date Modified: 10 Jun 2014
	*/
	@Test
	public void correctImageTest(){
		
		assertEquals(firstImage.getUrlname() ,"resources/images/Desert.jpg");
		assertEquals(firstImage.getXstart() ,383);
		assertEquals(firstImage.getYstart() , 15);
		assertEquals(firstImage.getWidth() , 272);
		assertEquals(firstImage.getHeight() , 122);
		assertEquals(firstImage.getLayer() , 3);
		assertEquals(firstImage.getDuration() , 0);
		assertEquals(firstImage.getStarttime() , 0);
		assertEquals(firstImage.getBranch() , 1);
		
		SmartTrolleyToolBox.print("Image Data: " + firstImage.getUrlname() + ", " + firstImage.getXstart() + ", "  + firstImage.getYstart()
				+ ", " 	+ firstImage.getWidth() + ", "  + firstImage.getHeight()+ ", "  + firstImage.getLayer() + firstImage.getDuration()
				+ ", "  + firstImage.getStarttime() + ", "  + firstImage.getBranch() + "\n");
		
	}
	
	/**
	* Tests that the correct shape info is parsed
	*<p> Date Modified: 10 Jun 2014
	*/
	@Test
	public void correctShapeTest(){
		
		assertEquals(firstShape.getDuration(), 0);
		assertEquals(firstShape.getStarttime(), 3);
		assertEquals(firstShape.getLayer(), 9);
		assertEquals(firstShape.getHeight(), 106);
		assertEquals(firstShape.getWidth(), 235);
		assertEquals(firstShape.getTotalpoints(), 8);
		assertEquals(firstShape.getLinecolor(), "#FFFFFF");
		assertEquals(firstShape.getFillcolor(), "#F79646");
		
		SmartTrolleyToolBox.print("Shape Data: " + firstShape.getDuration() + ", " + firstAudio.getStarttime() + ", "  + firstShape.getLayer() 
				+ ", "  + firstShape.getHeight() + ", "  + firstShape.getWidth() + ", "  + firstShape.getTotalpoints() + ", "  + firstShape.getLinecolor() 
				+ ", "  + firstShape.getFillcolor() + "\n");
		
	}
		
	/**
	* Tests that the correct point info is parsed
	*<p> Date Modified: 10 Jun 2014
	*/
	@Test
	public void correctPointTest(){
		
		assertEquals(firstPoint.getX(), 269);
		assertEquals(firstPoint.getY(), 38);
		assertEquals(firstPoint.getNum(), 0);
		
		SmartTrolleyToolBox.print("Number of points in shape: " + firstShape.getPoints().size());
		SmartTrolleyToolBox.print("Point Data: " + firstPoint.getX() + ", " + firstPoint.getY() + ", "  + firstPoint.getNum() +  "\n");
		
	}
	
	
	/**
	* Tests that a product is created correctly in the database and then deletes that product
	*<p> Date Modified: 10 Jun 2014
	*/
//	@Test
	public void createProductTest(){
		SqlConnection sqlConnector = new SqlConnection();
		
		
		int test = sqlConnector.createNewProduct("TestProduct", 1, null, 0, 1, 0);
		
		int returnedId = sqlConnector.getSpecificProduct("Name", "TestProduct 1", "1").getID();
		
		sqlConnector.deleteLastProduct();
		
		assertEquals(test, returnedId);
		
	}
	
}

/**************End of TestPWSParser.java**************/