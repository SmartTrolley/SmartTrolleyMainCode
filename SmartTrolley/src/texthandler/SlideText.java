

package texthandler;

import graphicshandler.SlideElementDuration;

import java.util.ArrayList;

import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import toolBox.SmartTrolleyToolBox;
/**
 * SmartTrolley
 *
 * A DESCRIPTION OF THE FILE
 *
 * @author Thomas Lea
 * @author Sam Geering
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version version of this file [Date Created: 25 May 2014]
 */
public class SlideText extends FlowPane{

	private int width, height;
	private int xStart;
	private int yStart;
	private int layer;
	private SlideElementDuration duration;
	

	/**
	 * DESCRIPTION OF CONSTRUCTOR
	 *<p> Date Modified: 25 May 2014
	 */
	public SlideText(ArrayList<SlideTextBody> texts, String font,
			String fontColor, int fontSize, int xStart, int yStart, int xEnd,
			int yEnd, double startTime, double duration, int layer) {
		//Setup as horizontal FlowPlane with vgap/hgap 0
		super();
		
		this.layer = layer;
		
		setupDuration(startTime, duration);

		getChildren().addAll(texts);

		setupBounds(xStart, yStart, xEnd, yEnd);

		for ( SlideTextBody body : texts){

			body.setFontFamily(font);
			body.setFontSize(fontSize);
			body.setFill(Color.web(fontColor));
			setupWrappingWidth(body);			
		}
		
		this.xStart = xStart;
		this.yStart = yStart;

	}

	/**
	* sets up the duration
	*@param startTime
	*@param duration
	*<p> Date Modified: 10 Jun 2014
	*/
	private void setupDuration(double startTime, double duration) {
		this.duration = new SlideElementDuration(this);
		
		this.duration.setDuration(duration);
		this.duration.setStartTime(startTime);
	}
	
	public int getXStart(){
		return xStart;
	}
	
	public int getYStart(){
		return yStart;
	}

	/**
	 * Sets up the boundaries of the text
	 *@param xStart
	 *@param yStart
	 *@param xEnd
	 *@param yEnd
	 *<p> Date Modified: 25 May 2014
	 */
	private void setupBounds(int xStart, int yStart, int xEnd, int yEnd) {

		width = xEnd - xStart;
		height = yEnd - yStart;

		resizeRelocate(xStart, yStart, width, height);

	}
	
	/**
	* sets up the wrapping width of the text
	*@param body
	*<p> Date Modified: 22 May 2014
	*/
	private void setupWrappingWidth(SlideTextBody body) {
		
		body.setWrappingWidth(width);
	}

	/**
	* Allows text to be relocated
	*@param x_coord
	*@param y_coord
	*<p> Date Modified: 5 Jun 2014
	*/
	public void relocateText(double x_coord, double y_coord) {
		
		SmartTrolleyToolBox.print("Rescaled x-coord for text is: " + x_coord);
		SmartTrolleyToolBox.print("Rescaled y-coord for text is: " + y_coord);
		
		relocate(x_coord, y_coord);
	}

	public int getLayer() {
		return layer;
	}

	public void show() {
		duration.show();
	}

}

/**************End of SlideText.java**************/
