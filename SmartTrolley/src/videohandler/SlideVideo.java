
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
 * @version V1.0 created: 27/05/2014
 */
public class SlideVideo extends Pane {

	VideoPlayerHandler handler;
	SlideElementDuration duration;
	private int layer;

	/**
	* The constructor for the slide video takes in all the parameters required to locate, position and setup the video on the slide 
	*@param pathLocation - The URL or path to the video file
	*@param xStart - The starting x-coordinate for the video on the slide
	*@param yStart - The starting y-coordinate for the video on the slide
	*@param width - The width of the video pane
	*@param height - The height of the video pane
	*@param loop
	*@param startTime
	*@param duration
	*<p> Date Modified: 27 May 2014
	*/
	public SlideVideo(String pathLocation, int xStart, int yStart, int width, int height, boolean loop, double startTime, double duration, int layer) {
		
		handler = new VideoPlayerHandler(pathLocation, xStart, yStart, width, height, loop, startTime, duration);

		this.layer = layer;
		
		if (handler.mediaControl != null) {
			this.getChildren().add(handler.mediaControl.overallBox);
		}

		setLayoutY(yStart);
		setLayoutX(xStart);

		setupDuration(startTime, duration, loop);
	}

	/**
	* Sets up the duration for the video within the the slide
	*@param startTime - How long is to elapse (in seconds) from the time the slide starts until the video is displayed
	*@param duration - The duration of the video in seconds
	*@param loop - Whether the video loops or not
	*<p> Date Modified: 27 May 2014
	*/
	private void setupDuration(double startTime, double duration, boolean loop) {

		this.duration = new SlideElementDuration(this);
		this.duration.setStartTime(startTime);

		// if its set to loop stop it from disappearing after duration
		if (loop) {
			this.duration.setDuration(0);
		} else {
			this.duration.setDuration(duration);
		}
	}

	/**
	* Shows the slide video
	*<p> Date Modified: 27 May 2014
	*/
	public void show() {
		try {
			handler.mediaControl.show();
			duration.show();
		} catch (NullPointerException e) {
			return;
		}
	}

	public int getLayer() {
		return layer;
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
/**************End of SlideVideo.java**************/
