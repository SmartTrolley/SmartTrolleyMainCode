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
	}
	
	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void correctInfoTest() throws Exception{
		data = parser.read(fileName);
		
		//DocumentInfo data
		assertEquals(data.getAuthor() ,"D6 Digital");
		assertEquals(data.getVersion() ,"2.1");
		assertEquals(data.getTitle() ,"This is the first chapter ");
		assertEquals(data.getComment() ,"example comment for chapter 1");
		assertEquals(data.getWidth() , 720);
		assertEquals(data.getHeight() , 540);
		
		SmartTrolleyPrint.print("DocumentInfo Data: " + data.getAuthor() + ", " + data.getVersion()+ ", "  + data.getTitle()
				+ ", " 	+ data.getComment()+ ", "  + data.getWidth()+ ", "  + data.getHeight() + "\n");
		
		//Defaults Data
		assertEquals(data.getDefaultBackgroundColor() ,"#948A54");
		assertEquals(data.getDefaultFont() ,"Papyrus");
		assertEquals(data.getDefaultFontSize() , 18);
		assertEquals(data.getDefaultFontColor() ,"#FFFFFF");
		assertEquals(data.getDefaultLineColor() ,"#5C4776");
		assertEquals(data.getDefaultFillColor() ,"#8064A2");
		
		SmartTrolleyPrint.print("Defaults Data: " + data.getDefaultBackgroundColor() + ", " + data.getDefaultFont()+ ", "  + data.getDefaultFontSize()
				+ ", " 	+ data.getDefaultFontColor()+ ", "  + data.getDefaultLineColor()+ ", "  + data.getDefaultFillColor() + "\n");
	
		//Slide Data
		assertEquals(data.getTextString() ,"Text must be within a �bounding� empty text box with the desired formatting"  );
		SmartTrolleyPrint.print("Slide Data: " + data.getTextString() + "\n");
	
	}
	
}

/**************End of TestPWSParser.java**************/