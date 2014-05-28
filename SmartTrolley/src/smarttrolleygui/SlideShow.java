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

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxType;
import Printing.SmartTrolleyPrint;

public class SlideShow {

	/**Message Box Height*/
	private final double MSG_BX_H = 100.0;

	/**Message Box Width*/
	private final double MSG_BX_W = 400.0;

	/**The array list of slides*/
	private ArrayList<Slide> slides;

	/**The slide that is displayed in the pane*/
	private Slide displayedSlide;

	/**The pane which will contain the slide*/
	private Pane displayedPane;

	/**The index of the slide in the slideshow*/
	private int slideShowIndex;

	protected MessageBox endOfListMsgBx;
	
	protected MessageBox startOfListMsgBx;

	/**
	*The constructor
	*<p> User can view PWS Compatible slideshow
	*@param slideList - The list of slides
	*<p> Date Modified: 25 May 2014
	*/
	public SlideShow(Pane displayedPane) {
		this.displayedPane = displayedPane;
		slides = new ArrayList<Slide>();
	}

	/**
	*This method returns all the slides in the slideshow
	*<p>User can load PWS compatible XML File into program
	*@return
	*<p> Date Modified: 24 May 2014
	*/
	protected ArrayList<Slide> getSlides() {
		return slides;
	}

	/**
	* This method displays the particular slide that is passed in
	*<p> User can view PWS Compatible slideshow
	*@param slideToDisplay - Slide that is displayed
	*<p> Date Modified: 26 May 2014
	*/
	public void displaySlide(Slide slideToDisplay) {
		displayedPane.getChildren().add(slideToDisplay);
	}

	/**
	* This method starts the slideshow by displaying the first slide
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 26 May 2014
	*/
	public void startSlideshow() {
		SmartTrolleyPrint.print("Starting Slideshow");

		SmartTrolleyPrint.print("The slideshow has " + slides.size() + " slides.");
		slideShowIndex = 0;

		displayedSlide = slides.get(slideShowIndex);

		displaySlide(displayedSlide);

		SmartTrolleyPrint.print("Started Slideshow");
	}

	/**
	*This method sets the slides in the slideshow
	*<p> User can load PWS compatible XML File into program
	*@param slideList - The list of slides
	*<p> Date Modified: 25 May 2014
	*/
	public void setSlides(ArrayList<Slide> slideList) {
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
	* Goes to the next slide in the slideshow and displays it
	*  If there is no next slide, it displays a message box notifying the user
	*<p>  User can view PWS Compatible slideshow
	*<p> Date Modified: 27 May 2014
	*/
	public void nextSlide() {

		SmartTrolleyPrint.print("In nextSlide method");
		// TODO Clear slide here
		if (slideShowIndex < slides.size() - 1) {
			SmartTrolleyPrint.print("The next slide will now be displayed");
			slideShowIndex++;
			displayedSlide = slides.get(slideShowIndex);
			displaySlide(displayedSlide);
		} else {
			SmartTrolleyPrint.print("Reached end of slideshow.");
			endOfListMsgBx = new MessageBox("Reached end of slideShow, no next slide", MessageBoxType.OK_ONLY);

			/*
			 * The two lines below to set the
			 * message box height and width
			 * are there because the message box
			 * resized itself (became very small)
			 * when multiple lists were deleted.
			 */
			endOfListMsgBx.setHeight(MSG_BX_H);
			endOfListMsgBx.setWidth(MSG_BX_W);

			endOfListMsgBx.showAndWait();
		}
	}

	/**
	* Goes to the previous slide in the slideshow and displays it.
	* If there is no previous slide, it displays a message box notifying the user
	*<p>  User can view PWS Compatible slideshow
	*<p> Date Modified: 27 May 2014
	*/
	public void prevSlide() {

		SmartTrolleyPrint.print("In prevSlide method");
		// TODO Clear slide here
		if (slideShowIndex > 0) {
			SmartTrolleyPrint.print("The previous slide will now be displayed");
			slideShowIndex--;
			displayedSlide = slides.get(slideShowIndex);
			displaySlide(displayedSlide);
		} else {
			SmartTrolleyPrint.print("Reached start of slideshow.");
			startOfListMsgBx = new MessageBox("Reached start of slideShow, no previous slide", MessageBoxType.OK_ONLY);

			/*
			 * The two lines below to set the
			 * message box height and width
			 * are there because the message box
			 * resized itself (became very small)
			 * when multiple lists were deleted.
			 */
			startOfListMsgBx.setHeight(MSG_BX_H);
			startOfListMsgBx.setWidth(MSG_BX_W);

			startOfListMsgBx.showAndWait();
		}
	}

	/**
	*This method returns the displayed slide
	*<p> User can view PWS Compatible slideshow
	*@return displayedSlide - The displayed Slide object
	*<p> Date Modified: 25 May 2014
	*/
	protected Slide getDisplayedSlide() {

		return displayedSlide;
	}

}

/**************End of SlideShow.java**************/
