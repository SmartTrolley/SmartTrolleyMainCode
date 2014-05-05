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

import graphicshandler.SlideElementDuration;
import javafx.scene.image.ImageView;

/**
 * @author Alasdair
 *
 */
public class ImageHandler extends ImageView{

	private SlideElementDuration duration;

	/**
	 * The Full PWS Constructor for images. completely fulfills PWS Specification for images
	 * 
	 * @param url The url of the image to be added
	 * @param x The top left x coordinate of the bounding rectangle
	 * @param y The top left y coordinate of the bounding rectangle
	 * @param width The width the image should be shown with
	 * @param height The height the image should be shown with
	 * @param duration The number of seconds the image appears for
	 * @param startTime The number of seconds before the image appears
	 */
	public ImageHandler(String url, int x, int y, int width, int height,
			int duration, int startTime) {
		super(url);
		
		this.duration = new SlideElementDuration(this);
		
		setDuration(duration);
		setStartTime(startTime);
		
		setFitWidth(width);
		setFitHeight(height);
		
		relocate(x,y);
		
	}

	/**
	*Image is made visible after show has been called and the start time has elapsed
	*<p>Fulfills ImageDurationTest
	*@param seconds start delay in seconds
	*/
	public void setStartTime(int seconds) {
		int milliseconds;
		milliseconds = seconds * 1000;
		
		duration.setStartTime(milliseconds);
		
	}

	/**
	*Image disappears after show is called and start time and duration has elapsed
	*<p> Fulfills ImageDurationTest
	*@param duration
	*<p> Date Modified: May 3, 2014
	*/
	public void setDuration(int seconds) {
		int milliseconds;
		milliseconds = seconds * 1000;
		
		duration.setDuration(milliseconds);
		
	}

	/**
	*When Show is called, the image appears after the start time and is visible for the duration.
	*<p>Fulfills ImageDurationTest
	*<p> Date Modified: May 3, 2014
	*/
	public void show() {
		duration.show();
	}

}
