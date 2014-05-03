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
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param startTime
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: May 3, 2014
	*/
	public void setStartTime(int seconds) {
		int milliseconds;
		milliseconds = seconds * 1000;
		
		duration.setStartTime(milliseconds);
		
	}

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param duration
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: May 3, 2014
	*/
	public void setDuration(int seconds) {
		int milliseconds;
		milliseconds = seconds * 1000;
		
		duration.setDuration(milliseconds);
		
	}

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: May 3, 2014
	*/
	public void show() {
		duration.show();
	}

}
