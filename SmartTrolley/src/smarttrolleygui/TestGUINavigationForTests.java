/**
* SmartTrolley
*
* This class is to be used by tests ONLY. It contains methods which will navigate the GUI
*
* @author Prashant Chakravarty
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 28 May 2014]
*/

package smarttrolleygui;

import javafx.application.Application;
import javafx.application.Platform;
import toolBox.SmartTrolleyToolBox;

public class TestGUINavigationForTests {
	/**
	* Launches the SmartTrolley Application required for the test to take place
	*<p> Date Modified: 31 May 2014
	 * @param smartTrolleyApplication
	 * @return The launched application
	*/
	protected static SmartTrolleyGUI launchTestApplication(SmartTrolleyGUI smartTrolleyApplication) {

		final SmartTrolleyGUI testApplication = smartTrolleyApplication;
		/*
		 * Create a new thread which launches the application. If the main
		 * thread launches the application, the rest of the test will only run
		 * after the application closes i.e. pointless.
		 */
		Thread newGUIThread;

		newGUIThread = new Thread("New GUI") {
			public void run() {
				SmartTrolleyToolBox.print("GUI thread");
				Application.launch(SmartTrolleyGUI.class, (java.lang.String[]) null);

			}
		};
		newGUIThread.start();

		// Delay to allow the application to launch
		// If you get NullPointer errors around this line, increase the delay
		SmartTrolleyToolBox.delay(200);

		// Now launch the instance of SmartTrolleyGUI, which takes over the displayed stage
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				testApplication.start(SmartTrolleyGUI.stage);
			}
		});

		/*
		 * Note that at this point, there are 3 threads running: 1. Main (test)
		 * thread - Runs this class 2. newGUIThread - Launches the Application
		 * 3. JavaFX Thread - This thread actually is the application.
		 */

		/*
		 * It is necessary to pause the main (test) thread for some time to
		 * allow the application to catch up. Failure to implement this delay
		 * results in a nullPointerException, since the scene has not yet been
		 * created.
		 */
		SmartTrolleyToolBox.delay(2500);
		
		return testApplication;
	}

	/**
	* Go to the next slide in the test slideshow
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
	* Go to the previous slide in the test slideshow
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

