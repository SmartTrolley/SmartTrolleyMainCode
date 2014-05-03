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
* @version version of this file [Date Created: May 3, 2014]
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
	public int x, y, width = 10, height = 10, duration = 5, startTime = 5;
	ImageHandler imageHandler;

	@Before
	public void setup(){
		imageHandler = new ImageHandler(url, x, y, width, height, duration, startTime);
	}
	
	@Test
	public void classTest() {
		assertEquals(Image.class, imageHandler.getImage().getClass());
	}
	
	@Test
	public void heightWidthTest(){
		
		double imageHeight =imageHandler.getBoundsInParent().getHeight();
		assertEquals(imageHeight, height, 0.0001);
		
		double circleWidth =imageHandler.getBoundsInParent().getWidth();
		assertEquals(circleWidth, width, 0.0001);
	}
	
	@Test
	public void xyPosTest(){
		double xPos = imageHandler.getLayoutX() + imageHandler.getLayoutBounds().getMinX();
		assertEquals(x, xPos, 0.0001);
		
		double yPos = imageHandler.getLayoutY() + imageHandler.getLayoutBounds().getMinY();
		assertEquals(y, yPos, 0.0001);
		
	}
	
	@Test
	public void durationTest(){

		imageHandler.setStartTime(startTime);
		imageHandler.setDuration(duration);
		
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
