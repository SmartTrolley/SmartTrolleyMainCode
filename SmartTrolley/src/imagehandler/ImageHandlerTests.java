/**
* SmartTrolley
*
* A complete test class to ensure correct funtionality
* of the Image Handler module
*
* @author Matthew Wells
* @author Alasdair Munday
*
* @author Checked By: Checker(s) fill here
*
* @version v1.0 [Date Created: May 3, 2014]
*/

package imagehandler;

import static org.junit.Assert.*;
import javafx.scene.image.Image;

import org.junit.Before;
import org.junit.Test;



/**
 * @author Alasdair
 *
 */
public class ImageHandlerTests {
	
	public String url = "http://th03.deviantart.net/fs70/PRE/i/2013/077/8/9/cookie_monster_by_xenia_cat-d5yhjwj.jpg";
	public int x, y, width = 10, height = 10, duration = 1, startTime = 1;
	ImageHandler imageHandler;

	/**
	*Setup the image handler with arbitrary PWS input 
	*<p> Date Modified: 5 May 2014
	*/
	@Before
	public void setup(){
		imageHandler = new ImageHandler(url, x, y, width, height, startTime, duration);
	}
	
	/**
	*Test that the getImage method returns an image
	*<p> Date Modified: 5 May 2014
	*/
	@Test
	public void classTest() {
		assertEquals(Image.class, imageHandler.getImage().getClass());
	}
	
	/**
	*Test that the height and width of the image in its parent are equal to
	*the height and width specified in the constructor
	*<p> Date Modified: 5 May 2014
	*/
	@Test
	public void heightWidthTest(){
		
		double imageHeight =imageHandler.getBoundsInParent().getHeight();
		assertEquals(imageHeight, height, 0.0001);
		
		double circleWidth =imageHandler.getBoundsInParent().getWidth();
		assertEquals(circleWidth, width, 0.0001);
	}
	
	/**
	*Check that the X and Y positions in the slide are those fed to the constructor
	*<p> Date Modified: 5 May 2014
	*/
	@Test
	public void xyPosTest(){
		// + getMinX required because the layoutX checks the translation 
		// from its initial position (not neceserally 0, 0).
		double xPos = imageHandler.getLayoutX() + imageHandler.getLayoutBounds().getMinX();
		assertEquals(x, xPos, 0.0001);
		
		double yPos = imageHandler.getLayoutY() + imageHandler.getLayoutBounds().getMinY();
		assertEquals(y, yPos, 0.0001);
		
	}
	
	/**
	*Test that the image appears and disappears at the times 
	*specified by startTime and duration.
	*
	*<p> Date Modified: 5 May 2014
	*/
	@Test
	public void durationTest(){
		
		imageHandler.show();
		
		assertFalse(imageHandler.isVisible());
		
		// sleep for a little longer than start time
		try {
			Thread.sleep(startTime*1001);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//shape should have appeared
		assertTrue(imageHandler.isVisible());

		// sleep for a little longer than duration
		try {
			Thread.sleep(duration*1001);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// shape should have disappeared
		assertFalse(imageHandler.isVisible());		
	}
	
	
	/**
	*Test ensures the image is visible if duration is set to 0 	
	*<p> Date Modified: 5 May 2014
	*/
	@Test
	public void zeroDurationTest(){
		imageHandler.setDuration(0);
		imageHandler.show();
		
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(imageHandler.isVisible());
	}

}
