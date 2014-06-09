/**
* SmartTrolley
*
* Image handler coherent with all PWS required inputs and standards
*
* @author Alasdair Munday
*
* @author Checked By: Matthew Wells
*
* @version v1.0 [Date Created: May 3, 2014]
*/

package imagehandler;

import graphicshandler.Branchable;
import graphicshandler.SlideElementDuration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import toolBox.SmartTrolleyToolBox;

/**
 * @author Alasdair
 *
 */
public class SlideImage extends ImageView implements Branchable{

	private SlideElementDuration duration;
	private int x;
	private int y;
	private int branch;
	private int layer;

	/**
	 * The Full PWS Constructor for images. completely fulfills PWS Specification for images
	 * 
	 * @param url The url of the image to be added
	 * @param x The top left x coordinate of the bounding rectangle
	 * @param y The top left y coordinate of the bounding rectangle
	 * @param width The width the image should be shown with
	 * @param height The height the image should be shown with
	 * @param startTime The number of seconds before the image appears
	 * @param duration The number of seconds the image appears for
	 */
	public SlideImage(String url, int x, int y, int width, int height,
			int startTime, int duration, int branch, int layer) {
		super();
		try{
		this.setImage(new Image(url));
		}catch(IllegalArgumentException e){
			SmartTrolleyToolBox.print("Could not find image at url: "+url);
			this.setImage(new Image("http://www.martyranodes.com/sites/default/files/images/kits/no_0.jpg"));
		}
		
		this.layer = layer;
		this.branch = branch;
		
		this.duration = new SlideElementDuration(this);
		this.x = x;
		this.y = y;
		
		setDuration(duration);
		setStartTime(startTime);
		
		setFitWidth(width);
		setFitHeight(height);
		
		relocate(x,y);
		
	}

	/**
	*Image is made visible after show has been called and the start time has elapsed
	*<p>Fulfills ImageDurationTest
	*@param seconds start delay in seconds
	*<p> Date Modified: 3 May 2014
	*/
	protected void setStartTime(int seconds) {
		
		duration.setStartTime(seconds);
		
	}

	/**
	*Image disappears after show is called and start time and duration has elapsed
	*<p> Fulfills ImageDurationTest
	*@param duration
	*<p> Date Modified: May 3, 2014
	*/
	protected void setDuration(double seconds) {
		
		duration.setDuration(seconds);
		
	}
	
	/**
	 * @return the x-coordinate of the image
	 */
	public int getx(){
		return x;
	}

	/**
	*When Show is called, the image appears after the start time and is visible for the duration.
	*<p>Fulfills ImageDurationTest
	*<p> Date Modified: May 3, 2014
	*/
	public void show() {
		
		duration.show();
	}

	/**
	 * @return the y-coordinate of the image
	 */
	public int gety() {
		return y;
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

	public int getLayer() {
		return layer;
	}

}
