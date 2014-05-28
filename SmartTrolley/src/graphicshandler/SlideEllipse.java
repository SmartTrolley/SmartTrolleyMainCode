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

/**************End of slideEllipse.java**************/
package graphicshandler;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

/** 
 * SmartTrolley
 * Generates an Ellipse suitable to pass all the tests determined
 * by the PWS and contract with Spoon
 *
 * @author Matthew Wells
 * @author Alasdair Munday
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [v1.0] [Date Created: 25/04/14]
 */

public class SlideEllipse extends Ellipse{

	private SlideElementDuration duration;

	/**
	 * Creates an ellipse according to PWS standard.
	 * 
	 * @see SlideShapeFactory#getShape()
	 * 
	 *<p> Date Modified: 2 May 2014
	 */
	public SlideEllipse(ShapePoint point, int width, int height) {
		super(point.getxCoordinate(), point.getyCoordinate(), width/2, height/2);
		duration = new SlideElementDuration(this);
		setVisible(false);
	}

	
	/**
	 * Method converts from seconds to milliseconds for setDuration
	 *<p> Date Modified: 2 May 2014
	 */
	public void setDuration(double seconds) {
		
		duration.setDuration(seconds);
	}


	/**Calls duration.show()
	 *<p> Date Modified: 2 May 2014
	 */
	public void show() {
		duration.show();
	}

	
	/**
	 * Finds the ratio of the newHeight of the shape once it has been resized to the 
	 * inherent height of the shape, to determine a scaling factor
	 *<p> Date Modified: 2 May 2014
	 */
	public void setHeight(int newHeight) {
		
		double inherentHeight = super.getBoundsInLocal().getHeight();

		//calculate the ratio of inherent Height to current Height
		double scaler = newHeight/inherentHeight;

		//scale by this ratio
		this.setScaleY(scaler);

	}

	/**
	 * Finds the ratio of the newWidth of the shape once it has been resized to the 
	 * inherent width of the shape, to determine a scaling factor
	 */
	public void setWidth(int newWidth) {

		// create local shape to access polygon parameters
		double inherentWidth = super.getBoundsInLocal().getWidth();

		//calculate the ratio of new width to inherent width
		double scaler = newWidth/inherentWidth;

		//scale by this ratio
		this.setScaleX(scaler);

	}

	/**
	 *Method converts from seconds to milliseconds for setStartTime
	 *<p> Date Modified: 2 May 2014
	 */
	public void setStartTime(int seconds) {
		duration.setStartTime(seconds);		
	}

	/**Method sets fillColor
	 *@param fillColor
	 *<p> Date Modified: 2 May 2014
	 */
	public void setFillColor(String fillColor) {
		this.setFill(Color.web(fillColor));

	}

	/**
	 * Method returns fillColor
	 *<p> Date Modified: 2 May 2014
	 */
	public Paint getFillColor() {
		return this.getFill();
	}

	/**
	 *Method sets lineColor
	 *<p> Date Modified: 2 May 2014
	 */
	public void setLineColor(String lineColor) {
		this.setStroke(Color.web(lineColor));

	}

	/**
	 *Method returns lineColor
	 *<p> Date Modified: 2 May 2014
	 */
	public Paint getLineColor() {
		return this.getStroke();
	}

}
