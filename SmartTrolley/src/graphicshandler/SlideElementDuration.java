package graphicshandler;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Node;

/**
 * SmartTrolley
 * 
 * Handler for durations of media on a slideshow. uses timers to make the slide element
 * appear after startTime and then remain visible for the duration.
 * 
 * @author Matthew Wells
 * @author Alasdair Munday
 * 
 * @author [Checked By:] [Prashant Chakravarty]
 * 
 * @version 1.0 [Date Created: 25/04/14]
 */
public class SlideElementDuration {

	
	/**Timer for counting start time and duration*/
	private Timer timer;
	
	/**Node to which the duration is applied*/
	private Node node;
	
	/**Duration of the node in milliseconds*/
	private double duration = 0;
	
	/**startTime in milliseconds for that node*/
	private double startTime = 0;
	
	/**If the timer has started or not*/
	private boolean started;

	
	/**
	 * Method calls setVisible(false) so that node does not appear 
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

			timer = new Timer();

			started = true;

			TimerTask appear = new ShowTask();
			timer.schedule(appear, (long)this.startTime);
		}

	/**
	*Set the time after which the node should appear
	*@param seconds - The start time in seconds
	*<p> Date Modified: 11 Jun 2014
	*/
	public void setStartTime(double seconds) {
		
		//startTime is in milliseconds
		startTime = seconds*1000;
		
	}

	/**	
	*Set the time for which node should remain visible
	*@param seconds
	*<p> Date Modified: 11 Jun 2014
	*/
	public void setDuration(double seconds) {
		// duration is in milliseconds
		duration = seconds * 1000;
	}

	/**
	*Calls run() to make the shape appear visible after the set delay
	*and then disappear after its set duration
	*@param double seconds - The duration in seconds	
	*<p> Date Modified: 25 April 2014
	 */
	public class ShowTask extends TimerTask {
		@Override
		public void run() {
			if (started) {
				// after start time has finished
				node.setVisible(true);
				started = false;

				// 0 duration keeps shape visible
				if (duration != 0) {
					timer.schedule(new ShowTask(), (long)duration);
				}

			} else {
				// after duration has finished
				node.setVisible(false);
			}
		}
	}
}
/**************End of SlideElementDuration.java**************/
