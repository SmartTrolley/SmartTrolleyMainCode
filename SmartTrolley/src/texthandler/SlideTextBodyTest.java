/**
* SmartTrolley
*
* A DESCRIPTION OF THE FILE
*
* @author Name1
* @author Name2
*
* @author Checked By: Checker(s) fill here
*
* @version version of this file [Date Created: 22 May 2014]
*/

/*YOUR CODE HERE*/

/**************End of SlideTextBodyTest.java**************/
package texthandler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.text.Text;

/** 
 * SmartTrolley
 * 
 * Unit tests for Text body, compliant with PWS regulations
 *
 * @author Alasdair Munday
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version 1.0 Date Created: 22/05/2014
 */

public class SlideTextBodyTest {
	//Bold Italic and underlined booleans, default true
	boolean bold= false, italic = false, underlined = false;
	SlideTextBody regularFontBodyOne, boldItalicUnderlinedBodyTwo, bodyThree;
	String one = "one", two = "two", three = "three", oneStyle, twoStyle, threeStyle;
	
	/**
	*Setup three different text bodies, one with no formatting, one with all formating and one with some formatting
	*<p>Allows for simple testing that bold and italic input has been recognised
	*
	*<p> Date Modified: 23 May 2014
	*/
	@Before
	public void setUp(){
		
		regularFontBodyOne = new SlideTextBody(one, bold, italic, underlined, 1);
		
		bold = true;
		italic = true;
		underlined = true;	
		boldItalicUnderlinedBodyTwo = new SlideTextBody(two, bold, italic, underlined ,2 );
		
		underlined = false;
		bodyThree = new SlideTextBody(three, bold, italic, underlined, 3);
		
		//Strings that describe the font styles of the bodies. useful for tests.
		oneStyle = regularFontBodyOne.getFont().getStyle();
		twoStyle = boldItalicUnderlinedBodyTwo.getFont().getStyle();
	}
	
	
	@Test
	public void textTest(){
		
		assertEquals(bodyThree.getText(), "three");
		
	}
	
	/**
	*Passes if the boolean returned for isBold() is the same as the value passed in,
	*<p> This is designed to follow the pattern of a similar inherited method from text called isUnderline().
	*
	*@see Text#isUnderline()
	*@see SlideTextBody#isBold()
	*<p> Date Modified: 23 May 2014
	*/
	@Test
	public void weightBoolTest(){
		
		assertTrue(boldItalicUnderlinedBodyTwo.isBold());
		assertFalse(regularFontBodyOne.isBold());
	}
	

	
	/**
	*Passes if the posture boolean returned is correct.
	*<p> The method is designed to work similarly to isUnderline()
	*<p>Test(s)/User Story that it satisfies
	*
	*@see Text#isUnderline()
	*@see SlideTextBody#isItalic()
	*<p> Date Modified: 23 May 2014
	*/
	@Test
	public void postureBoolTest(){
		
		assertFalse(regularFontBodyOne.isItalic());
		assertTrue(boldItalicUnderlinedBodyTwo.isItalic());
	}
	
	/**
	*Passes if the weight that the javafx Text has is bold
	*
	*@see Text#getFont()
	*<p> Date Modified: 23 May 2014
	*/
	@Test
	public void weightTest(){
		
		assertTrue(twoStyle.contains("Bold"));
		assertFalse(oneStyle.contains("Bold"));
		
	}
	
	/**
	*
	*<p>Test(s)/User Story that it satisfies
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 23 May 2014
	*/
	@Test
	public void postureTest(){
		
		assertTrue(twoStyle.contains("Italic"));
		assertFalse(oneStyle.contains("Italic"));
		
	}
	
	/**
	*Underlining in the inherited Text class works more similarly to how we require.
	*<p> Therefore all that is required is to test the boolean isUnderline() method.
	*
	*<p> Date Modified: 23 May 2014
	*
	*/
	@Test
	public void underlinedTest(){
		
		assertTrue(boldItalicUnderlinedBodyTwo.isUnderline());
		assertFalse(regularFontBodyOne.isUnderline());
	
	}
	
	/**
	*Tests that the natural ordering of the textbodies is by ascending Number
	*<p>Test(s)/User Story that it satisfies
	*
	*@see Comparable#compareTo(Object)
	*
	*<p> Date Modified: 23 May 2014
	*/
	@Test
	public void orderTest(){
		
		ArrayList<SlideTextBody> texts = new ArrayList<SlideTextBody>();
		texts.add(regularFontBodyOne);
		texts.add(bodyThree);
		texts.add(boldItalicUnderlinedBodyTwo);
		
		Collections.sort(texts);
		
		assertEquals("one", texts.get(0).getText());
	
	}

}
