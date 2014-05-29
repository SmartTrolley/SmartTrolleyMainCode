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
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxResult;
import se.mbaeumer.fxmessagebox.MessageBoxType;
import toolBox.SmartTrolleyToolBox;

public class SlideShow {

	/**
	 * Enumerated type for play direction
	 */
	enum PlayDirection {
		NEXT, PREV
	}

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

	/**Message Box to notify user that they are trying to access outside the slideshow*/
	protected MessageBox outOfSldShwMsgBox;

	/**Field which is true when a slideshow is playing using slide durations rather than button presses*/
	boolean autoPlay = false;

	/**Timer for slide duration*/
	Timer slideTimer;

	private PlaySlide playSlide;

	/**Direction to play the slideshow*/
	private PlayDirection playDirection = PlayDirection.NEXT;

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
	 * This method displays a particular slide by the index that is passed in
	 *<p> User can view PWS Compatible slideshow
	 *@param slideShowIndex - Slide index to be displayed
	 *<p> Date Modified: 28 May 2014
	 */
	// TODO Consider changing the name to playSlide/startAutoPlay
	public void displaySlide(int slideShowIndex) {

		displayedSlide = slides.get(slideShowIndex);

		displayedPane.getChildren().add(displayedSlide);

		if (autoPlay) {

			if (displayedSlide.duration != 0) {
				playSlide.cancel();
				/*
				 * The multiplication by 1000 is because the slide duration is in seconds
				 * while timer durations are in milliseconds.
				 */
				playSlide = new PlaySlide(playDirection);
				slideTimer.schedule(playSlide, (long) displayedSlide.duration * 1000);

				SmartTrolleyToolBox.print("In displaySlide for autoplay, scheduling timer now.");
			}
		}
	}

	/**
	 * This method starts the slideshow by displaying the first slide
	 *<p> User can view PWS Compatible slideshow
	 *<p> Date Modified: 26 May 2014
	 */
	public void startSlideshow(PlayDirection playDirection) {
		SmartTrolleyToolBox.print("Starting Slideshow");

		this.playDirection = playDirection;
		SmartTrolleyToolBox.print("The slideshow has " + slides.size() + " slides.");
		slideShowIndex = 0;

		displaySlide(slideShowIndex);

		SmartTrolleyToolBox.print("Started Slideshow");
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

		SmartTrolleyToolBox.print("In nextSlide method");

		deleteSlidePane();
		if (slideShowIndex < slides.size() - 1) {

			SmartTrolleyToolBox.print("The next slide will now be displayed");

			displayedSlide = slides.get(++slideShowIndex);
			displaySlide(slideShowIndex);

		} else {
			String endOfSldShwString = "Reached end of slideShow, no next slide";
			displayOutOfSldShwMsgBx(endOfSldShwString);
			displaySlide(slideShowIndex);
		}
	}

	/**
	 * This method deletes the slide pane
	 *<p>User can view PWS Compatible slideshow
	 *<p> Date Modified: 29 May 2014
	 */
	private void deleteSlidePane() {

		// TODO Clear slide here: displayedSlide.clearSlide();
		// Perhaps remove the slide children clearing in clearSlide()
		SmartTrolleyToolBox.print(displayedPane.getChildren());
		displayedPane.getChildren().clear();
		SmartTrolleyToolBox.print("Deleting Slide Pane.");
		displayedPane.setVisible(true);
		SmartTrolleyToolBox.print(displayedPane.getChildren());

		// while (!displayedPane.getChildren().isEmpty());
		SmartTrolleyToolBox.print("Slide Pane deleted.");
	}

	/**
	 * Displays a message box informing the user that they are at the edges of the slideshow
	 *<p> User can view PWS Compatible slideshow
	 *<p> Date Modified: 28 May 2014
	 * @param stringToDisplay - String that is shown in the message box
	 */
	private void displayOutOfSldShwMsgBx(String stringToDisplay) {
		SmartTrolleyToolBox.print(stringToDisplay);

		outOfSldShwMsgBox = new MessageBox(stringToDisplay, MessageBoxType.OK_ONLY);

		/*
		 * The two lines below to set the
		 * message box height and width
		 * are there because the message box
		 * resized itself (became very small)
		 * when multiple lists were deleted.
		 */
		outOfSldShwMsgBox.setHeight(MSG_BX_H);
		outOfSldShwMsgBox.setWidth(MSG_BX_W);

		outOfSldShwMsgBox.showAndWait();

		// Autoplay should be false now regardless of its previous state
		autoPlay = false;
		SmartTrolleyToolBox.print("Autoplay now off");

	}

	/**
	 * Goes to the previous slide in the slideshow and displays it.
	 * If there is no previous slide, it displays a message box notifying the user
	 *<p>  User can view PWS Compatible slideshow
	 *<p> Date Modified: 27 May 2014
	 */
	public void prevSlide() {

		SmartTrolleyToolBox.print("In prevSlide method");

		deleteSlidePane();
		if (slideShowIndex > 0) {
			SmartTrolleyToolBox.print("The previous slide will now be displayed");
			displayedSlide = slides.get(--slideShowIndex);
			displaySlide(slideShowIndex);

		} else {
			String startOfSldShwString = "Reached start of slideshow.";
			displayOutOfSldShwMsgBx(startOfSldShwString);
			displaySlide(slideShowIndex);
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

	/**
	 * Plays the slideshow from the current slide
	 *<p>Test(s)/User Story that it satisfies
	 *<p> Date Modified: 28 May 2014
	 */
	public void play() {

		autoPlay = true;
		SmartTrolleyToolBox.print("Autoplay now on");

		slideTimer = new Timer();

		playSlide = new PlaySlide(playDirection);

		deleteSlidePane();

		displaySlide(slideShowIndex);

	}

	private class PlaySlide extends TimerTask {

		private PlayDirection playDirection;

		/**
		 * The constructor assignst the play direction
		 *<p> Date Modified: 28 May 2014
		 */
		public PlaySlide(PlayDirection playDirection) {
			this.playDirection = playDirection;
		}

		@Override
		public void run() {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					if (playDirection == PlayDirection.NEXT) {
						nextSlide();
					} else {
						prevSlide();
					}
				}

			});
		}

	}
}

/**************End of SlideShow.java**************/
