/**
 * SmartTrolley
 *
 * Instances of this object contain all elements in a slideshow, as described in the PWS
 *
 * @author Prashant Chakravarty
 *
 * @author Checked By: Alasdair 29 May 2014
 *
 * @version V1.0 [Date Created: 24 May 2014]
 */

package smarttrolleygui.slideshow;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxType;
import slideshowdata.SlideDataImporter;
import slideshowdata.SlideShowData;
import toolBox.SmartTrolleyToolBox;

public class SlideShow {

	/**
	 * Enumerated type for play direction
	 */
	public enum PlayDirection {
		FOR, REV
	}

	/**Message Box Height*/
	private final double MSG_BX_H = 100.0;

	/**Message Box Width*/
	private final double MSG_BX_W = 400.0;

	/**The array list of slides*/
	private ArrayList<Slide> slides;

	/**The slideshow that is displayed in the pane*/
	private Slide displayedSlide;

	/**The pane which will contain the slideshow*/
	private Pane displayedPane;

	/**The index of the slideshow in the slideshow*/
	private int slideShowIndex;

	/**Message Box to notify user that they are trying to access outside the slideshow*/
	public MessageBox outOfSlideShowMessageBox;
	
	/**Field which is true when a slideshow is playing using slideshow durations rather than button presses*/
	private boolean autoPlay = false;
	
	/**Timer for slideshow duration*/
	private Timer slideTimer;
	
	private PlaySlide playSlide;
	
	/**Direction to play the slideshow*/
	private PlayDirection playDirection = PlayDirection.FOR;
	
	/**
	 *The constructor
	 *<p> User can view PWS Compatible slideshow
	 *@param slideList - The list of slides
	 *<p> Date Modified: 25 May 2014
	 */
	public SlideShow(SlideShowData slideShowData, Pane displayedPane) {
		
		this.slides = SlideDataImporter.getSlides(slideShowData);
		setPane(displayedPane);
	}
	
	/**
	 * DESCRIPTION OF CONSTRUCTOR
	 *<p> Date Modified: 4 Jun 2014
	 */
	public SlideShow(AnchorPane productAnchorPane) {
		setPane(productAnchorPane);
	}

	/**
	 *This method returns all the slides in the slideshow
	 *<p>User can load PWS compatible XML File into program
	 *@return
	 *<p> Date Modified: 24 May 2014
	 */
	public ArrayList<Slide> getSlides() {
		return slides;
	}
	
	protected void setPane(Pane displayedPane){
		this.displayedPane = displayedPane;
	}
	
	/**
	 * This method displays a particular slideshow by the index that is passed in
	 *<p> User can view PWS Compatible slideshow
	 *@param slideShowIndex - Slide index to be displayed
	 *<p> Date Modified: 28 May 2014
	 */
	public void displaySlide(int slideShowIndex) {
				
		this.slideShowIndex = slideShowIndex;
		
		displayedSlide = slides.get(slideShowIndex);
		
		displayedSlide.show();

		displayedPane.getChildren().add(displayedSlide);
		
		displayedSlide.setSlideShow(this);

		//Setup Timer for new duration if slideshow is autoplaying 
		if (isAutoPlay()) {
			if (displayedSlide.duration != 0) {
				playSlide.cancel();
				
				/*
				 * The multiplication by 1000 is because the slideshow duration is in seconds
				 * while timer durations are in milliseconds.
				 */
				playSlide = new PlaySlide(getPlayDirection());
				getSlideTimer().schedule(playSlide, (long) displayedSlide.duration * 1000);
				
				SmartTrolleyToolBox.print("In displaySlide for autoplay, scheduling timer now.");
			}
		}
	}

	/**
	 * This method starts the slideshow by displaying the first slideshow
	 *<p> User can view PWS Compatible slideshow
	 *<p> Date Modified: 26 May 2014
	 */
	public void startSlideshow() {
		SmartTrolleyToolBox.print("Starting Slideshow");
		
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
	protected void setSlides(SlideShowData slides) {
		
		this.slides = SlideDataImporter.getSlides(slides);
	}

	/**
	 *This method adds a slideshow to the slideshow
	 *<p>User can load PWS compatible XML File into program
	 *@param slideshow - A Slide object
	 *<p> Date Modified: 25 May 2014
	 */
	public void addSlideToSlideShow(Slide slide) {
		slides.add(slide);
	}

	/**
	 * Goes to the next slideshow in the slideshow and displays it
	 *  If there is no next slideshow, it displays a message box notifying the user
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
			String endOfSldShwString = "Reached end of slideshow, no next slide";
			displayOutOfSlideShowMessageBox(endOfSldShwString);
			displaySlide(slideShowIndex);
		}
	}

	/**
	 * This method deletes the slideshow pane
	 *<p>User can view PWS Compatible slideshow
	 *<p> Date Modified: 29 May 2014
	 */
	private void deleteSlidePane() {

		//displayedSlide.clearSlide();
		
		// Perhaps remove the slideshow children clearing in clearSlide()
		try{
		SmartTrolleyToolBox.print(displayedPane.getChildren());
		displayedPane.getChildren().clear();
		} catch (NullPointerException e){
			SmartTrolleyToolBox.print("No slide was displayed, so none deleted.");
		}
		SmartTrolleyToolBox.print("Deleting Slide Pane.");
		displayedPane.setVisible(true);
		SmartTrolleyToolBox.print(displayedPane.getChildren());

		SmartTrolleyToolBox.print("Slide Pane deleted.");
	}

	/**
	 * Displays a message box informing the user that they are at the edges of the slideshow
	 *<p> User can view PWS Compatible slideshow
	 *<p> Date Modified: 28 May 2014
	 * @param stringToDisplay - String that is shown in the message box
	 */
	private void displayOutOfSlideShowMessageBox(String stringToDisplay) {
		SmartTrolleyToolBox.print(stringToDisplay);

		outOfSlideShowMessageBox = new MessageBox(stringToDisplay, MessageBoxType.OK_ONLY);

		/*
		 * The two lines below to set the
		 * message box height and width
		 * are there because the message box
		 * resized itself (became very small)
		 * when multiple lists were deleted.
		 */
		outOfSlideShowMessageBox.setHeight(MSG_BX_H);
		outOfSlideShowMessageBox.setWidth(MSG_BX_W);

		outOfSlideShowMessageBox.showAndWait();

		// Autoplay should be false now regardless of its previous state
		setAutoPlay(false);
		SmartTrolleyToolBox.print("Autoplay now off");

	}

	/**
	 * Goes to the previous slideshow in the slideshow and displays it.
	 * If there is no previous slideshow, it displays a message box notifying the user
	 *<p>  User can view PWS Compatible slideshow
	 *<p> Date Modified: 27 May 2014
	 */
	public void prevSlide() {

		SmartTrolleyToolBox.print("In prevSlide method");

		deleteSlidePane();
		if (slideShowIndex > 0) {
			SmartTrolleyToolBox.print("The previous slideshow will now be displayed");
			displayedSlide = slides.get(--slideShowIndex);
			displaySlide(slideShowIndex);

		} else {
			String startOfSldShwString = "Reached start of slideshow.";
			displayOutOfSlideShowMessageBox(startOfSldShwString);
			displaySlide(slideShowIndex);
		}
	}

	/**
	 *This method returns the displayed slideshow
	 *<p> User can view PWS Compatible slideshow
	 *@return displayedSlide - The displayed Slide object
	 *<p> Date Modified: 25 May 2014
	 */
	public Slide getDisplayedSlide() {

		return displayedSlide;
	}

	/**
	 * Plays the slideshow from the current slideshow
	 *<p>Test(s)/User Story that it satisfies
	 *<p> Date Modified: 28 May 2014
	 */
	public void play() {

		setAutoPlay(true);
		SmartTrolleyToolBox.print("Autoplay now on");

		setSlideTimer(new Timer());

		playSlide = new PlaySlide(getPlayDirection());

		deleteSlidePane();

		displaySlide(slideShowIndex);

	}

	/**
	 * @return the playDirection
	 */
	public PlayDirection getPlayDirection() {
		return playDirection;
	}

	/**
	 * @param playDirection the playDirection to set
	 */
	public void setPlayDirection(PlayDirection playDirection) {
		this.playDirection = playDirection;
	}

	/**
	 * @return the autoPlay state
	 */
	public boolean isAutoPlay() {
		return autoPlay;
	}

	/**
	 * @param autoPlay the autoPlay to set
	 */
	public void setAutoPlay(boolean autoPlay) {
		this.autoPlay = autoPlay;
	}

	/**
	 * @return the slideTimer
	 */
	public Timer getSlideTimer() {
		return slideTimer;
	}

	/**
	 * @param slideTimer the slideTimer to set
	 */
	public void setSlideTimer(Timer slideTimer) {
		this.slideTimer = slideTimer;
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
					if (playDirection == PlayDirection.FOR) {
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
