package smarttrolleygui;

import javafx.application.Platform;
import toolBox.SmartTrolleyToolBox;
/**
* SmartTrolley
*
* This class is to be used by tests ONLY. It contains methods which will navigate the GUI
*
* @author Prashant Chakravarty
* @author V1.1 Thomas [Commenting]
*
* @author Checked By: Checker(s) fill here
*
* @version [1.0] [Date Created: 28 May 2014]
 * @version [1.1] [Date Created: 10/06/2014]
*/
public class TestGUINavigationForTests {

	/**
	* Navigates to the all shopping lists screen from the start screen by pressing the "View your shopping Lists" button
	*<p> Date Modified: 2 Jun 2014
	*/
	protected static void goToAllShoppingListsFromStartScreen(final SmartTrolleyGUI smartTrolleyApplication) {
		/*
		 * In order to do anything with the user interface, the JavaFX thread must be modified using Platform.
		 * runlater etc etc If you try to monitor the UX outside this thread, there will be errors.
		 * Please note that for any elements of the UX that you want to modify,
		 * there must be the corresponding variable (with an @FXML tag above it i.e.
		 * 
		 * @FXML protected static Button viewAllShoppingListsButton;) in order to use it.
		 */
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				SmartTrolleyToolBox.print("Firing view lists Button");
				smartTrolleyApplication.startScreen.viewAllShoppingListsButton.fire();
				SmartTrolleyToolBox.print("Fired view lists Button");
			}
		});
	}	

	/**
	* Go to the next slideshow in the test slideshow
	*<p> Date Modified: 31 May 2014
	*/
	protected static void goToNextTestSlide(final SmartTrolleyGUI smartTrolleyApplication) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smartTrolleyApplication.productScreen.nextSLideButton.fire();
				SmartTrolleyToolBox.print("Next button fired");
			}
		});
		SmartTrolleyToolBox.delay(500);
	}

	/**
	* Go to the previous slideshow in the test slideshow
	*<p> Date Modified: 31 May 2014
	*/
	protected static void goToPrevTestSlide(final SmartTrolleyGUI smartTrolleyApplication) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smartTrolleyApplication.productScreen.prevSLideButton.fire();
				SmartTrolleyToolBox.print("Prev button fired");
			}
		});
		SmartTrolleyToolBox.delay(500);
	}

	/**
	* Press the play/pause button on the slideshow
	*<p> Date Modified: 31 May 2014
	*/
	protected static void playPauseSlideshow(final SmartTrolleyGUI smartTrolleyApplication) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smartTrolleyApplication.productScreen.playPauseButton.fire();
				SmartTrolleyToolBox.print("playPause button fired");
			}
		});
	}

	/**
	* Method that takes the test to the product screen
	*@param smartTrolleyApplication - The launched application
	*<p> Date Modified: 1 Jun 2014
	*/
	public static void goToProductScreen(final SmartTrolleyGUI smartTrolleyApplication) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smartTrolleyApplication.goToProductScreen();
			}
		});

	}
}

/**************End of TestGUINavigationForTests.java**************/

