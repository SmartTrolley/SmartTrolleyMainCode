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

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SlideShow {
	private List<Slide> slides;

	private Slide displayedSlide;
	
	private Pane displayedPane;

	/**
	*The constructor
	*<p> User can view PWs Compatible slideshow
	*@param slideList - The list of slides
	*<p> Date Modified: 25 May 2014
	*/
	public SlideShow(Pane displayedPane) {
		this.displayedPane = displayedPane;
	}

	/**
	*This method returns all the slides in the slideshow
	*<p>User can load PWS compatible XML File into program
	*@return
	*<p> Date Modified: 24 May 2014
	*/
	public List<Slide> getSlides() {
		return slides;
	}
	
	public void displaySlide(Slide slideToInsert){
		displayedPane.getChildren().add(slideToInsert);	
	}

	/**
	*This method sets the slides in the slideshow
	*<p>User can load PWS compatible XML File into program
	*@param slideList - The list of slides
	*<p> Date Modified: 25 May 2014
	*/
	public void setSlides(List<Slide> slideList) {
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
