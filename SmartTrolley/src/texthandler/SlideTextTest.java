//<<<<<<< HEAD
///**
//* SmartTrolley
//*
//* A DESCRIPTION OF THE FILE
//*
//* @author Name1
//* @author Name2
//*
//* @author Checked By: Checker(s) fill here
//*
//* @version version of this file [Date Created: 23 May 2014]
//*/
//
///*YOUR CODE HERE*/
//
//
//
///**************End of SlideTextTest.java**************/
//package texthandler;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontPosture;
//import javafx.scene.text.FontWeight;
//
//import org.junit.Before;
//import org.junit.Test;
//
//public class SlideTextTest {
//	
//	ArrayList<SlideTextBody> texts;
//	SlideText textBox;
//	String font = "Verdana";
//	// Magenta
//	String fontColor = "FF00FF";
//	int fontSize = 14;
//	int xStart = 20;
//	int yStart = 50;
//	int xEnd  = 300;
//	int yEnd = 400;
//	long startTime = 1;
//	long duration = 1;
//	SlideTextBody testBody;
//	Font testFont;
//	String oneString = "one", twoString = "two", threeString = "three Lots of text to make sure that the boundaries for the pane are correct";
//	
//	/**
//	 *Method/Test Description
//	 *<p>Test(s)/User Story that it satisfies
//	 *@throws java.lang.Exception
//	 *[If applicable]@see [Reference URL OR Class#Method]
//	 *<p> Date Modified: 23 May 2014
//	 */
//	@Before
//	public void setUp() throws Exception {
//		
//		//populate a list of textbodies
//		texts = new ArrayList<SlideTextBody>();
//		texts.add(new SlideTextBody(oneString, true, true, true, 1));
//		texts.add(new SlideTextBody(twoString, false, true, false, 2));
//		texts.add(new SlideTextBody(threeString, false, false, true, 2));
//		
//		textBox = new SlideText(texts, font, fontColor, 
//					fontSize, xStart, yStart, xEnd, yEnd, startTime, duration);
//		
//		// A text body to test that the text box input modifies it correctly
//		testBody = (SlideTextBody) textBox.getChildren().get(0);
//		
//		//The desired font of body one
//		testFont = Font.font(font, FontWeight.BOLD, FontPosture.ITALIC , fontSize);
//		
//	}
//
//	@Test
//	public void contentTest() {
//		assertEquals(oneString, testBody.toString());
//	}
//	
//	@Test
//	public void fontTest()	{
//		assertEquals(testFont.getFamily(), testBody.getFont().getFamily());
//	}
//	
//	@Test
//	public void fontSizeTest(){
//		assertEquals(testFont.getSize(), testBody.getFont().getSize(), 0.001);
//	}
//	
//	@Test
//	public void fontColorTest(){
//		assertEquals(Color.web(fontColor), testBody.getFill());
//	}
//	
//	@Test
//	public void heightWidthTest(){
//		//calculate width and height
//		int width = xStart- xEnd;
//		int height = yStart - yEnd;
//		
//		//check actual values
//		assertEquals(width, textBox.getLayoutBounds().getWidth(), 0.0001);
//		assertEquals(height, textBox.getLayoutBounds().getHeight(), 0.0001);
//		
//	}
//	
//	@Test
//	public void coordinateTest(){
//		
//		assertEquals(xStart, textBox.getLayoutX(), 0.001);
//		assertEquals(yStart, textBox.getLayoutY(), 0.001);
//		
//	}
//
//	@Test
//	public void durationTest(){
//
//		textBox.setStartTime(startTime);
//		textBox.setDuration(duration);
//		
//		textBox.show();
//		
//		assertFalse(textBox.isVisible());
//		
//		// sleep for a little longer than start time
//		try {
//			Thread.sleep(startTime*1001);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//shape should have appeared
//		assertTrue(textBox.isVisible());
//
//		// sleep for a little longer than duration
//		try {
//			Thread.sleep(duration*1001);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		// shape should have disappeared
//		assertFalse(textBox.isVisible());		
//	}
//	
//	
//	@Test
//	public void zeroDurationTest(){
//		textBox.setDuration(0);
//		textBox.show();
//		
//		try {
//			Thread.sleep(duration);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		assertTrue(textBox.isVisible());
//	}
//}
//=======
package texthandler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import org.junit.Before;
import org.junit.Test;

/**
* SmartTrolley
*
* A test class for the handling of the text element 
*
* @author Name1
* @author Name2
*
* @author Checked By: Checker(s) fill here
*
* @version version of this file [Date Created: 23 May 2014]
*/
public class SlideTextTest {
	
	ArrayList<SlideTextBody> texts;
	SlideText textBox;
	String font = "Verdana";
	// Magenta
	String fontColor = "FF00FF";
	int fontSize = 14;
	int xStart = 20;
	int yStart = 50;
	int xEnd  = 300;
	int yEnd = 400;
	double startTime = 1;
	double duration = 1;
	SlideTextBody testBody;
	Font testFont;
	String oneString = "one", twoString = "two", threeString = "three Lots of text to make sure that the boundaries for the pane are correct";
	
	/**
	 * This setup method runs before any of the tests. It populates, sets up and creates a new 
	 *@throws java.lang.Exception
	 *<p> Date Modified: 23 May 2014
	 */
	@Before
	public void setUp() throws Exception {
		
		//populate a list of textbodies
		texts = new ArrayList<SlideTextBody>();
		texts.add(new SlideTextBody(oneString, true, true, true, 1,1));
		texts.add(new SlideTextBody(twoString, false, true, false, 2,1));
		texts.add(new SlideTextBody(threeString, false, false, true, 2,1));
		
		textBox = new SlideText(texts, font, fontColor, 
					fontSize, xStart, yStart, xEnd, yEnd, startTime, duration,1);
		
		// A text body to test that the text box input modifies it correctly
		testBody = (SlideTextBody) textBox.getChildren().get(0);
		
		//The desired font of body one
		testFont = Font.font(font, FontWeight.BOLD, FontPosture.ITALIC , fontSize);
		
	}

	@Test
	public void contentTest() {
		assertEquals(oneString, testBody.toString());
	}
	
	@Test
	public void fontTest()	{
		assertEquals(testFont.getFamily(), testBody.getFont().getFamily());
	}
	
	@Test
	public void fontSizeTest(){
		assertEquals(testFont.getSize(), testBody.getFont().getSize(), 0.001);
	}
	
	@Test
	public void fontColorTest(){
		assertEquals(Color.web(fontColor), testBody.getFill());
	}
	
	@Test
	public void heightWidthTest(){
		//calculate width and height
		int width = xEnd - xStart;
		int height = yEnd - yStart;
		
		//check actual values
		assertEquals(width, textBox.getLayoutBounds().getWidth(), 0.0001);
		assertEquals(height, textBox.getBoundsInParent().getHeight(), 0.0001);
		
	}
	
	@Test
	public void coordinateTest(){
		
		assertEquals(xStart, textBox.getLayoutX(), 0.001);
		assertEquals(yStart, textBox.getLayoutY(), 0.001);
		
	}
	
	@Test
	public void durationTest(){
		
	}
}
/**************End of SlideTextTest.java**************/

