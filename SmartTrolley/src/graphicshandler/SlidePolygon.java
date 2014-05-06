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

/**************End of SlidePolygon.java**************/
package graphicshandler;

import java.util.PriorityQueue;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

/**
 * SmartTrolley
 * Generates a Polygon suitable to pass all the tests determined
 * by the PWS and contract with Spoon
 * 
 * @author Matthew Wells
 * @author Alasdair Munday
 * 
 * @author [Checked By:] [Checker(s) fill here]
 * 
 * @version [v1.0] [Date Created: 25/04/14]
 */

public class SlidePolygon extends Polygon implements SlideElement {

	private SlideElementDuration duration;

	/**
	 * Extracts the coordinates of the Polygon's points, instantiates a new
	 * duration and sets the visibility to false
	 * 
	 * @param points
	 *            <p>
	 *            Date Modified: 28 Apr 2014
	 */
	public SlidePolygon(PriorityQueue<ShapePoint> points) {
		extractCoordinates(points);

		duration = new SlideElementDuration(this);
		setVisible(false);
	}

	/**
	 * Adds the x and y coordinates of the polygon to the method getPoints()
	 * <p>
	 * Date Modified: 28 Apr 2014
	 */
	private void extractCoordinates(PriorityQueue<ShapePoint> points) {

		double yCoordinate;
		double xCoordinate;

		while (!points.isEmpty()) {
			ShapePoint currentPoint = points.remove();

			// get the x coordinate from the current point
			xCoordinate = currentPoint.getxCoordinate();

			getPoints().add(xCoordinate);

			// get the y coordinate from the current point
			yCoordinate = currentPoint.getyCoordinate();

			getPoints().add(yCoordinate);

		}
	}

	/**
	 * Converts from seconds to milliseconds for the polygon duration
	 * <p>
	 * Date Modified: 28 Apr 2014
	 */
	public void setDuration(int seconds) {
		int milliseconds;
		milliseconds = seconds * 1000;
		duration.setDuration(milliseconds);
	}

	public void show() {
		duration.show();
	}

	/**
	 * Finds the ratio of the newHeight of the polygon once it has been resized
	 * to the inherent height of the polygon, to determine a scaling factor
	 * 
	 * @see graphicsHandler.SlideElement#setHeight(int) <p>
	 *      Date Modified: 28 Apr 2014
	 */
	public void setHeight(int newHeight) {

		double inherentHeight = super.getBoundsInLocal().getHeight();

		// calculate the ratio of inherent Height to current Height
		double scaler = newHeight / inherentHeight;

		// scale by this ratio
		this.setScaleY(scaler);

	}

	/**
	 * Finds the ratio of the newWidth of the polygon once it has been resized
	 * to the inherent width of the polygon, to determine a scaling factor
	 * <p>
	 * Date Modified: 28 Apr 2014
	 */
	public void setWidth(int newWidth) {

		// create local shape to access polygon parameters
		double inherentWidth = super.getBoundsInLocal().getWidth();

		// calculate the ratio of new width to inherent width
		double scaler = newWidth / inherentWidth;

		// scale by this ratio
		this.setScaleX(scaler);

	}

	/**
	 * Method converts from seconds to milliseconds for setStartTime
	 * <p>
	 * Date Modified: 28 Apr 2014
	 */
	public void setStartTime(int seconds) {
		int milliseconds;
		milliseconds = seconds * 1000;
		duration.setStartTime(milliseconds);
	}

	/**
	 * Method sets fillColor
	 * 
	 * @param fillColor
	 *            <p>
	 *            Date Modified: 28 April 2014
	 */
	public void setFillColor(String fillColor) {
		this.setFill(Color.web(fillColor));

	}

	/**
	 * Method returns fillColor
	 * 
	 * @param fillColor
	 *            <p>
	 *            Date Modified: 28 April 2014
	 */
	public Paint getFillColor() {
		return this.getFill();
	}

	/**
	 * Method sets lineColor
	 * 
	 * @param lineColor
	 *            <p>
	 *            Date Modified: 28 April 2014
	 */
	public void setLineColor(String lineColor) {
		this.setStroke(Color.web(lineColor));

	}

	/**
	 * Method returns lineColor
	 * 
	 * @param lineColor
	 *            <p>
	 *            Date Modified: 28 April 2014
	 */
	public Paint getLineColor() {

		return this.getStroke();
	}

}
