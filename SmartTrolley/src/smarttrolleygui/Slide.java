/**
* SmartTrolley
*
* An instance of this object contains all the information required to render a particular slide.
*
* @author Prashant Chakravarty
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 24 May 2014]
*/

package smarttrolleygui;


import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import Printing.SmartTrolleyPrint;

public class Slide extends AnchorPane {
	
	/**An image's x-co-ordinate in the slide*/
	static final double IMAGE_X_COORD = 100;
	
	/**An image's y-co-ordinate in the slide*/
	static final double IMAGE_Y_COORD = 100;

	/**
	 * Enumerated type of the slide child elements in the PWS
	 */
	// Note that these child elements have attributes as well
	static enum SlideChildElements {
		TEXT, TEXTBODY, SHAPE, AUDIO, IMAGE, VIDEO
	}

	protected double duration;;
		
	/**
	 * The constructor gets the slide duration as a parameter
	 *@param duration - Duration in seconds
	 *<p> Date Modified: 28 May 2014
	 */
	public Slide(double duration){
		this.duration = duration;
	}

	//TODO Matt & Alick's implementation of this method should be preferred to mine
	/**
	*Adds the Node parameter to the pane
	*<p>User can view PWS Compatible slideshow
	* @param insertInSlide - Node to be inserted into the slide
	* @param type - Subchild element type of the node
	*/
	public void addNodeToSlide(Node insertInSlide, SlideChildElements type){
		
		SmartTrolleyPrint.print("In Slide.addNodeToSlide");
		if (type == SlideChildElements.IMAGE){
			
			ImageView imageToInsert = (ImageView) insertInSlide;
			imageToInsert.setX(IMAGE_X_COORD);
			imageToInsert.setY(IMAGE_Y_COORD);	
			
			this.getChildren().add(imageToInsert);
			
			SmartTrolleyPrint.print("Image added to slide");			
		}			
	}
}

/**************End of Slide.java**************/

