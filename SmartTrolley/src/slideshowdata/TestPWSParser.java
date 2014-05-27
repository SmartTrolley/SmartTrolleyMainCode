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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import Printing.SmartTrolleyPrint;


public class TestPWSParser {
	
	private PWSParser parser;
	private SlideShowData data;
	private String fileName = " ../../XMLDocs/dynamDomfinal.xml";
	


	@Before
	public void setUp() throws Exception {
		
		parser  =  new PWSParser();
		data = new SlideShowData();
		data = parser.read(fileName);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void correctDocumentInfoTest() throws Exception{
		
		assertEquals(data.getAuthor() ,"D6 Digital");
		assertEquals(data.getVersion() ,"2.1");
		assertEquals(data.getTitle() ,"This is the first chapter ");
		assertEquals(data.getComment() ,"example comment for chapter 1");
		assertEquals(data.getWidth() , 720);
		assertEquals(data.getHeight() , 540);
		
		SmartTrolleyPrint.print("DocumentInfo Data: " + data.getAuthor() + ", " + data.getVersion()+ ", "  + data.getTitle()
				+ ", " 	+ data.getComment()+ ", "  + data.getWidth()+ ", "  + data.getHeight() + "\n");
		
	}
	
	@Test
	public void correctDefaultsTest() throws Exception{
		
		assertEquals(data.getDefaultBackgroundColor() ,"#948A54");
		assertEquals(data.getDefaultFont() ,"Papyrus");
		assertEquals(data.getDefaultFontSize() , 18);
		assertEquals(data.getDefaultFontColor() ,"#FFFFFF");
		assertEquals(data.getDefaultLineColor() ,"#5C4776");
		assertEquals(data.getDefaultFillColor() ,"#8064A2");
		
		SmartTrolleyPrint.print("Defaults Data: " + data.getDefaultBackgroundColor() + ", " + data.getDefaultFont()+ ", "  + data.getDefaultFontSize()
				+ ", " 	+ data.getDefaultFontColor()+ ", "  + data.getDefaultLineColor()+ ", "  + data.getDefaultFillColor() + "\n");
		
	}
	
	@Test
	public void correctTextTest() throws Exception{
		
		assertEquals(data.getTextXstart() , 20);
		assertEquals(data.getTextYstart() , 291);
		assertEquals(data.getTextXend() , 505);
		assertEquals(data.getTextYend() , 441);
		assertEquals(data.getTextLayer() , 17);
		assertEquals(data.getTextDuration() , 0);
		assertEquals(data.getTextStarttime() , 0);
		assertEquals(data.getTextFont() , "Papyrus");
		assertEquals(data.getTextFontcolor() , "#000000");
		assertEquals(data.getTextFontsize() , 16);
		
		SmartTrolleyPrint.print("Text Data: " + data.getTextXstart() + ", " + data.getTextYstart()+ ", "  + data.getTextXend()
				+ ", " 	+ data.getTextYend()+ ", "  + data.getTextLayer()+ ", "  + data.getTextDuration() + ", "  + data.getTextStarttime()
				+ ", "  + data.getTextFont() + ", " + data.getTextFontcolor() + ", " + data.getTextFontsize() + "\n");
	}
	
	@Test
	public void correctTextbodyTest() throws Exception{
		
//		assertEquals(data.getTextbodyBranch(0) , 3);
//		assertEquals(data.getTextbodyItalic(0) , true);
//		assertEquals(data.getTextbodyBold(0) , true);
//		assertEquals(data.getTextbodyUnderlined(0) , false);
//		assertEquals(data.getTextbodyTextstring(0) , "Text must be within a “bounding” empty text box with the desired formatting");
		
		SmartTrolleyPrint.print("Textbody Data: " + data.getTextbodyBranch(0) + ", " + data.getTextbodyItalic(0) + ", " + data.getTextbodyBold(0) 
				+ ", " + data.getTextbodyUnderlined(0) +  ", " + data.getTextbodyTextstring(0) + "\n");
	}
	
	@Test
	public void correctImageTest() throws Exception{
		
		assertEquals(data.getImageURL() ,"resources/images/Desert.jpg");
		assertEquals(data.getImageXstart() ,383);
		assertEquals(data.getImageYstart() , 15);
		assertEquals(data.getImageWidth() , 272);
		assertEquals(data.getImageHeight() , 122);
		assertEquals(data.getImageLayer() , 3);
		assertEquals(data.getImageDuration() , 0);
		assertEquals(data.getImageStarttime() , 0);
		assertEquals(data.getImageBranch() , 1);
		
		SmartTrolleyPrint.print("Image Data: " + data.getImageURL() + ", " + data.getImageXstart()+ ", "  + data.getImageYstart()
				+ ", " 	+ data.getImageWidth()+ ", "  + data.getImageHeight()+ ", "  + data.getImageLayer() + data.getImageDuration()
				+ ", "  + data.getImageStarttime()+ ", "  + data.getImageBranch() + "\n");
		
	}
	
}

/**************End of TestPWSParser.java**************/