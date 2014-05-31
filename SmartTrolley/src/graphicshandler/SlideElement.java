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
* @version version of this file [Date Created: 1 May 2014]
*/

/*YOUR CODE HERE*/

/**************End of SlideElement.java**************/
package graphicshandler;

/** 
 * SmartTrolley
 * 
 * A class to set the duration, height, width and start time
 * of each slideElement
 *
 * @author Matthew Wells
 * @author Alasdair Munday
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [v1.0] [Date Created: 25/04/14]
 */

public interface SlideElement {
	

	public void scale(int xScaler, int yScaler);
	
	// shows the element for the selected duration
	public void show();
	
	
}
