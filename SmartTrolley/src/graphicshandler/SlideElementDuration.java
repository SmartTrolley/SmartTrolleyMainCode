/**
 * Spikes
 *
 * A DESCRIPTION OF THE FILE
 *
 * @author Name1
 * @author Name2
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version version of this file [Date Created: 28 Apr 2014]
 */

/*YOUR CODE HERE*/

/**************End of SlideElementDuration.java**************/
package graphicshandler;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Node;

/**
 * SmartTrolley
 * 
 * Handler for durations of media on a slide. uses timers to make the slide
 * appear after startTime and then remain visible for the duration.
 * 
 * @author Matthew Wells
 * @author Alasdair Munday
 * 
 * @author [Checked By:] [Checker(s) fill here]
 * 
 * @version 1.0 [Date Created: 25/04/14]
 */

public class SlideElementDuration {

	private Timer timer;
	private Node node;
	private int duration = 0;
	private int startTime = 0;
	private boolean started;

	
	/**
	 * Method calls setVisible(false) such that node does not appear 
	 * @param node
	 * *<p> Date Modified: 25 April 2014
	 */
	public SlideElementDuration(Node node) {

		this.node = node;

		this.node.setVisible(false);

	}

	/**
	
	*Method instantiates a timer used to count the delay set by startTime.
	*If duration is set to 0, node will always be visible
	*<p> Date Modified: 25 April 2014
	
	*/
	public void show() {

		if (duration == 0) {
			node.setVisible(true);
		} else {

			timer = new Timer();

			started = true;

			TimerTask appear = new ShowTask();
			timer.schedule(appear, this.startTime);
		}
	}

	/**
	
	*Set the time after which node should appear
	*@param milliseconds	
	*<p> Date Modified: 25 April 2014
	
	*/
	public void setStartTime(int seconds) {
		
		//startTime is in milliseconds
		startTime = seconds*1000;
		
	}

	/**
	
	*Set the time for which node should remain visible
	*@param milliseconds	
	*<p> Date Modified: 25 April 2014
	
	*/
	public void setDuration(int seconds) {
		// duration is in milliseconds
		duration = seconds * 1000;
	}

	/**
	*Calls run() to make the shape appear visible after the set delay
	*and then disappear after its set duration
	*@param milliseconds	
	*<p> Date Modified: 25 April 2014
	 */
	private class ShowTask extends TimerTask {
		@Override
		public void run() {
			if (started) {
				// after start time has finished
				node.setVisible(true);
				started = false;

				// 0 duration keeps shape visible
				if (duration != 0) {
					timer.schedule(new ShowTask(), duration);
				}

			} else {
				// after duration has finished
				node.setVisible(false);
			}
		}

	}

}
