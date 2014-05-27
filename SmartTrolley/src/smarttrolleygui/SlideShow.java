/**
* SmartTrolley
*
* Instances of this object contain all elements in a slideshow, as described in the PWS
*
* @author Prashant Chakravarty
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 24 May 2014]
*/

package smarttrolleygui;

import java.util.LinkedList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import Printing.SmartTrolleyPrint;

public class SlideShow {
	
	/**The linked list of slides*/
	private LinkedList<Slide> slides;

	
	/**The slide that is displayed in the pane*/
	private Slide displayedSlide;
	
	/**The pane which will contain the slide*/
	private Pane displayedPane;

	/**The index of the slide in the slideshow*/
	private int slideShowIndex;

	/**
	*The constructor
	*<p> User can view PWS Compatible slideshow
	*@param slideList - The list of slides
	*<p> Date Modified: 25 May 2014
	*/
	public SlideShow(Pane displayedPane) {
		this.displayedPane = displayedPane;
		slides = new LinkedList<Slide>();
	}

	/**
	*This method returns all the slides in the slideshow
	*<p>User can load PWS compatible XML File into program
	*@return
	*<p> Date Modified: 24 May 2014
	*/
	protected LinkedList<Slide> getSlides() {
		return slides;
	}
	
	/**
	* This method displays the particular slide that is passed in
	*<p> User can view PWS Compatible slideshow
	*@param slideToDisplay - Slide that is displayed
	*<p> Date Modified: 26 May 2014
	*/
	public void displaySlide(Slide slideToDisplay){
		displayedPane.getChildren().add(slideToDisplay);	
	}
	
	/**
	* This method starts the slideshow by displaying the first slide
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 26 May 2014
	*/
	public void startSlideshow(){
		SmartTrolleyPrint.print("Starting Slideshow");
			
		SmartTrolleyPrint.print("The slideshow has " + slides.size() + " slides.");
		displayedSlide = slides.getFirst();
		
		slideShowIndex = 1;
		
		displaySlide(displayedSlide);
		
		SmartTrolleyPrint.print("Started Slideshow");
	}

	/**
	*This method sets the slides in the slideshow
	*<p> User can load PWS compatible XML File into program
	*@param slideList - The list of slides
	*<p> Date Modified: 25 May 2014
	*/
	public void setSlides(LinkedList<Slide> slideList) {
		this.slides = slideList;
	}
	
	/**
	*This method adds a slide to the slideshow
	*<p>User can load PWS compatible XML File into program
	*@param slide - A Slide object
	*<p> Date Modified: 25 May 2014
	*/
	public void addSlideToSlideShow(Slide slide) {
		slides.add(slide);
	}

	/**
	*This method returns the displayed slide
	*<p> User can view PWs Compatible slideshow
	*@return displayedSlide - The displayed Slide object
	*<p> Date Modified: 25 May 2014
	*/
	protected Slide getDisplayedSlide() {

		return displayedSlide;
	}

	/**
	* Goes to the next slide in the slideshow and displays it
	*<p>  User can view PWs Compatible slideshow
	*<p> Date Modified: 27 May 2014
	*/
	public void nextSlide() {
		displayedSlide = slides.get(slideShowIndex+1);
		displaySlide(displayedSlide);
	}


}

/**************End of SlideShow.java**************/
