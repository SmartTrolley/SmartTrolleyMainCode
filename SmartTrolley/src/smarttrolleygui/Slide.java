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


import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.PGNode;

import Printing.SmartTrolleyPrint;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Slide extends Node {
	
	/**An image's x-co-ordinate in the slide*/
	static final double IMAGE_X_COORD = 25;
	
	/**An image's y-co-ordinate in the slide*/
	static final double IMAGE_Y_COORD = 25;

	/**
	 * Enumerated type of the slide child elements in the PWS
	 */
	// Note that these child elements have attributes as well
	static enum SlideChildElements {
		TEXT, TEXTBODY, SHAPE, AUDIO, IMAGE, VIDEO
	};
	
	/**
	*Sets the duration of the slide 
	*<p>User can view PWS Compatible slideshow
	*@param seconds - Duration in seconds
	*<p> Date Modified: 25 May 2014
	*/
	//TODO Unimplemented method
	public void setDuration(int seconds){
		
	}

	//TODO Arne's implementation of this method should be preferred to mine
	/**
	*Adds the Node parameter to the pane parameter
	*<p>User can view PWS Compatible slideshow
	*@param insertInSlide - Node to be inserted into the slide
	 * @param type - Subchild element type of the node
	*/
	public void addNodeToSlide(Node insertInSlide, SlideChildElements type){
		
		SmartTrolleyPrint.print("In Slide.addNodeToSlide");
		if (type == SlideChildElements.IMAGE){
			
			ImageView imageToInsert = (ImageView) insertInSlide;
			imageToInsert.setX(IMAGE_X_COORD);
			imageToInsert.setY(IMAGE_Y_COORD);
			SmartTrolleyPrint.print("Image added to slide");			
		}			
	}

	/* (non-Javadoc)
	 * @see javafx.scene.Node#impl_computeContains(double, double)
	 */
	@Override
	@Deprecated
	protected boolean impl_computeContains(double arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javafx.scene.Node#impl_computeGeomBounds(com.sun.javafx.geom.BaseBounds, com.sun.javafx.geom.transform.BaseTransform)
	 */
	@Override
	@Deprecated
	public BaseBounds impl_computeGeomBounds(BaseBounds arg0, BaseTransform arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javafx.scene.Node#impl_createPGNode()
	 */
	@Override
	@Deprecated
	protected PGNode impl_createPGNode() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javafx.scene.Node#impl_processMXNode(com.sun.javafx.jmx.MXNodeAlgorithm, com.sun.javafx.jmx.MXNodeAlgorithmContext)
	 */
	@Override
	@Deprecated
	public Object impl_processMXNode(MXNodeAlgorithm arg0, MXNodeAlgorithmContext arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

/**************End of Slide.java**************/

