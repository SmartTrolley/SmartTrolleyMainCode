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
import java.util.List;

import Printing.SmartTrolleyPrint;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SlideShow {
	private LinkedList<Slide> slides;

	private Slide displayedSlide;
	
	private Pane displayedPane;

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
	public LinkedList<Slide> getSlides() {
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
		
		//TODO This is only for testing////
		//This creates a slide and displays it
		displayedSlide = new Slide();
		//testSlideShow = new SlideShow(displayedPane);

		// TODO User IMAGE_HEIGHT & IMAGE_WIDTH constants instead of magic numbers
		Image productImage = new Image(getClass().getResourceAsStream("img/SampleProducts/Activia.jpg"), 100, 100, true, true);
		ImageView productImageView = new ImageView(productImage);

		// Platform.runLater(new Runnable() {
		// @Override
		// public void run() {
		displayedSlide.addNodeToSlide(productImageView, Slide.SlideChildElements.IMAGE);
		//testSlideShow.addSlideToSlideShow(displayedSlide);
		//////////////////////////////////////
		
//		displayedPane.getChildren().add(slides.getFirst());
		displayedPane.getChildren().add(displayedSlide);
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
	public Slide getDisplayedSlide() {

		return displayedSlide;
	}

}

/**************End of SlideShow.java**************/
