

package graphicshandler;

import java.util.PriorityQueue;

import smarttrolleygui.slideshow.Layerable;

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
 * @author [Checked By:] [Prashant Chakravarty - 07 Jun 2014]
 * 
 * @version [v1.0] [Date Created: 25/04/14]
 */
public class SlidePolygon extends Polygon implements Branchable, Layerable{
	
	/**Duration of the polygon in millseconds*/
	private SlideElementDuration duration;
	private int branch;
	private int layer;

	/**
	 * Extracts the coordinates of the Polygon's points, instantiates a new
	 * duration and sets the visibility to false
	 * 
	 * @param points
	 * <p>Date Modified: 28 Apr 2014
	 */
	public SlidePolygon(PriorityQueue<ShapePoint> points) {
		extractCoordinates(points);

		duration = new SlideElementDuration(this);
		setVisible(false);
	}

	/**
	 * Adds the x and y coordinates of the polygon to the method getPoints()
	 * @param points - A PriorityQueue (points are sorted into the correct order regardless of insertion order by the pointNumber) of ShapePoints
	 * <p>Date Modified: 28 Apr 2014
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
	 * @param seconds - Duration of the polygon
	 * <p>Date Modified: 28 Apr 2014
	 */
	public void setDuration(double seconds) {
		
		duration.setDuration(seconds);
	}
	
	/**
	 * Shows the slideshow for its duration after startTime has elapsed
	 * 
	 * @see SlideElementDuration#show()
	 * @see this{@link #setDuration(int)}
	 * @see this{@link #setStartTime(int)}
	 */
	public void show() {
		duration.show();
	}

	/**
	 * Finds the ratio of the newHeight of the polygon once it has been resized
	 * to the inherent height of the polygon, to determine a scaling factor
	 * @param newHeight - The new height of the polygon as an integer
	 *<p>Date Modified: 28 Apr 2014
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
	 * @param newWidth - The new width of the polygon as an integer
	 * <p> Date Modified: 28 Apr 2014
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
	 * @param seconds - The start time for the polygon in seconds
	 * <p> Date Modified: 28 Apr 2014
	 */
	public void setStartTime(int seconds) {
		
		duration.setStartTime(seconds);
	}

	/**
	 * Method sets fillColor
	 * 
	 * @param fillColor - The fill color of the polygon
	 * <p> Date Modified: 28 April 2014
	 */
	public void setFillColor(String fillColor) {
		this.setFill(Color.web(fillColor));

	}

	/**
	 * Method returns fillColor
	 * @return fillColor - The fill colour of the polygon as a Paint object
	 * <p> Date Modified: 28 April 2014
	 */
	public Paint getFillColor() {
		return this.getFill();
	}

	/**
	 * Method sets lineColor
	 * 
	 * @param lineColor - The lineColor of the polygon as a String
	 * <p> Date Modified: 28 April 2014
	 */
	public void setLineColor(String lineColor) {
		this.setStroke(Color.web(lineColor));

	}

	/**
	 * Method returns lineColor
	 * 
	 * @return lineColor - The line colour of the polygon as a Paint object
	 *         
	 * <p> Date Modified: 28 April 2014
	 */
	public Paint getLineColor() {

		return this.getStroke();
	}

	@Override
	public void setBranch(int branch) {
		this.branch = branch;
	}

	@Override
	public int getBranch() {
		return this.branch;
	}
	
	@Override
	public int getLayer() {
		
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

}
/**************End of SlidePolygon.java**************/

