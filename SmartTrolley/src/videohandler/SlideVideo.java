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
* @version version of this file [Date Created: 27 May 2014]
*/

/*YOUR CODE HERE*/

/**************End of SlideVideo.java**************/
package videohandler;

import graphicshandler.SlideElementDuration;
import javafx.scene.layout.Pane;

/** 
 * SmartTrolley
 * 
 * <p>A Wrapper for spoon's videoHandler that we purchased. It matches our protocols for elements that appear in slides in that it extends a type of node and positions it's self in its parent. 
 *
 * @author Alasdair Munday
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [version of this class] [Date Created: DD/MM/YY]
 */
public class SlideVideo extends Pane {

	VideoPlayerHandler handler;
	SlideElementDuration duration;
	
	public SlideVideo(String pathLocation, int xStart, int yStart, int width, int height, 
			boolean loop, double startTime, double duration) {
		
		handler = new VideoPlayerHandler(pathLocation, xStart, yStart, width, height, loop, startTime, duration);
		this.relocate(xStart, yStart);
		
		this.getChildren().add(handler.mediaControl.overallBox);
		
		setupDuration(startTime, duration);
		
	}

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param startTime
	*@param duration
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 27 May 2014
	*/
	private void setupDuration(double startTime, double duration) {
		this.duration = new SlideElementDuration(this);
		this.duration.setDuration(duration);
		this.duration.setStartTime(startTime);
	}
	
	public void show(){
		handler.mediaControl.show();
		duration.show();
	}

}
