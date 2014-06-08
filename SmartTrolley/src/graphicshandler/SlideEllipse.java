package graphicshandler;

import smarttrolleygui.slideshow.Layerable;
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
 * @author [Checked By:] [Prashant Chakravarty - 07/06/14]
 *
 * @version [v1.0] [Date Created: 25/04/14]
 */
public class SlideEllipse extends Ellipse implements Branchable, Layerable {

	/**Duration of the ellipse in milliseconds*/
	private SlideElementDuration duration;
	private int layer;
	private int branch;

	/**
	* Creates an ellipse according to PWS standard by taking in the points, width and height
	*@param point
	*@param width
	*@param height
	* @see SlideShapeFactory#getShape()
	*<p> Date Modified: 2 May 2014
	*/
	public SlideEllipse(ShapePoint point, int width, int height) {
		super(point.getxCoordinate(), point.getyCoordinate(), width / 2, height / 2);
		duration = new SlideElementDuration(this);
		setVisible(false);
	}

	/**
	 * Method converts from seconds to milliseconds for setDuration
	 *@param seconds - The ellipse duration to be set, in seconds 
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
	 * inherent height of the shape, to determine a scaling factor.
	 *@param newHeight
	 *<p> Date Modified: 2 May 2014
	 */
	public void setHeight(int newHeight) {

		double inherentHeight = super.getBoundsInLocal().getHeight();

		// calculate the ratio of inherent Height to current Height
		double scaler = newHeight / inherentHeight;

		// scale by this ratio
		this.setScaleY(scaler);
	}

	/**
	 * Finds the ratio of the newWidth of the shape once it has been resized to the 
	 * inherent width of the shape, to determine a scaling factor
	 *@param newWidth
	 *<p> Date Modified: 2 May 2014
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
	 *Method converts from seconds to milliseconds for setStartTime
	 *@param seconds - The amount of time, in seconds, after which the ellipse is to be made visible
	 *<p> Date Modified: 2 May 2014
	 */
	public void setStartTime(int seconds) {
		duration.setStartTime(seconds);
	}

	/**Method sets fillColor for ellipse
	 *@param fillColor
	 *<p> Date Modified: 2 May 2014
	 */
	public void setFillColor(String fillColor) {
		this.setFill(Color.web(fillColor));

	}

	/**
	 * Method returns ellipse's fillColor
	 * @return The fill colour of the ellipse as a Paint object
	 *<p> Date Modified: 2 May 2014
	 */
	public Paint getFillColor() {
		return this.getFill();
	}

	/**
	 *Method sets lineColor for ellipse
	 *@param lineColor - The linecolor to set for the ellipse
	 *<p> Date Modified: 2 May 2014
	 */
	public void setLineColor(String lineColor) {
		this.setStroke(Color.web(lineColor));

	}

	/**
	 *Method returns ellipse's lineColor
	 *@return A Paint object containing the line colour of the ellipse
	 *<p> Date Modified: 2 May 2014
	 */
	public Paint getLineColor() {
		return this.getStroke();
	}

	@Override
	public int getLayer() {
		
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	@Override
	public int getBranch() {
		// TODO Auto-generated method stub
		return branch;
	}

	@Override
	public void setBranch(int branch) {
		this.branch = branch;
		
	}

}
/**************End of slideEllipse.java**************/
