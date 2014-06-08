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
 * @version version of this file [Date Created: 25 May 2014]
 */

/*YOUR CODE HERE*/



/**************End of SlideText.java**************/
package texthandler;

import java.util.ArrayList;

import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import toolBox.SmartTrolleyToolBox;

public class SlideText extends FlowPane{

	private int width, height;
	private int xStart;
	private int yStart;

	/**
	 * DESCRIPTION OF CONSTRUCTOR
	 *<p> Date Modified: 25 May 2014
	 */
	public SlideText(ArrayList<SlideTextBody> texts, String font,
			String fontColor, int fontSize, int xStart, int yStart, int xEnd,
			int yEnd, double startTime, double duration) {
		//Setup as horizontal FlowPlane with vgap/hgap 0
		super();

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
	
	public int getXStart(){
		return xStart;
	}
	
	public int getYStart(){
		return yStart;
	}

	/**
	 *Method/Test Description
	 *<p>Test(s)/User Story that it satisfies
	 *@param xStart
	 *@param yStart
	 *@param xEnd
	 *@param yEnd
	 *[If applicable]@see [Reference URL OR Class#Method]
	 *<p> Date Modified: 25 May 2014
	 */
	private void setupBounds(int xStart, int yStart, int xEnd, int yEnd) {

		width = xEnd - xStart;
		height = yEnd - yStart;

		resizeRelocate(xStart, yStart, width, height);

	}
	
	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param body
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 22 May 2014
	*/
	private void setupWrappingWidth(SlideTextBody body) {
		
		body.setWrappingWidth(width);
	}

	/**
	* Method to relocate the text to new coordinates
	*<p>Test(s)/User Story that it satisfies
	*@param x_coord
	*@param y_coord
	*<p> Date Modified: 5 Jun 2014
	*/
	public void relocateText(double x_coord, double y_coord) {
		
		SmartTrolleyToolBox.print("Rescaled x-coord for text is: " + x_coord);
		SmartTrolleyToolBox.print("Rescaled y-coord for text is: " + y_coord);
		
		relocate(x_coord, y_coord);
	}

}

