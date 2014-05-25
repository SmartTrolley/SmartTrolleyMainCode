/**
 * SmartTrolley
 *
 * This file contains the test case for the product screen
 *
 * @author Prashant Chakravarty
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.0 [Date Created: 24 May 2014]
 */

package smarttrolleygui;

import static org.junit.Assert.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Printing.SmartTrolleyPrint;

public class TestProductScreenController {

	String query;
	private SmartTrolleyGUI smartTrolleyApplication;
	Stage stage;

	private final double MIN_WINDOW_WIDTH = 600.0;
	private final double MIN_WINDOW_HEIGHT = 600.0;

	/**
	 * This method runs before every test.
	 * 
	 * @throws java.lang.Exception
	 *             <p>
	 *             Date Modified: 24 May 2014
	 */
	@Before
	public void setUp() throws Exception {

		smartTrolleyApplication = new SmartTrolleyGUI();
		
		/*
		 * Create a new thread which launches the application. If the main
		 * thread launches the application, the rest of the test will only run
		 * after the application closes i.e. pointless.
		 */
		Thread newGUIThread;		

		newGUIThread = new Thread("New GUI") {
			public void run() {
				SmartTrolleyPrint.print("GUI thread");
				Application.launch(smartTrolleyApplication.getClass(), (java.lang.String[]) null);

			}
		};
		newGUIThread.start();

		//Delay to allow the application to launch
		// If you get NullPointer errors around this line, increase the delay
		SmartTrolleyDelay.delay(200);
		
		//Now launch the instance of SmartTrolleyGUI, which takes over the displayed stage
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smartTrolleyApplication.start(SmartTrolleyGUI.stage);
			}
		});

		//Delay to allow the instance to launch.
		// If you get NullPointer errors around this line, increase the delay
		SmartTrolleyDelay.delay(1000);

		// TODO At the moment, the test assumes the product screen is the first
		// screen that appears.
		smartTrolleyApplication.productScreen.nextSLideButton.fire();
		
		//Delay to allow the application state to settle before running the test
		// If you get NullPointer errors around this line, increase the delay
		SmartTrolleyDelay.delay(500);

	}

	/**
	 * Method/Test Description
	 * <p>
	 * Test(s)/User Story that it satisfies
	 * 
	 * @throws java.lang.Exception
	 *             <p>
	 *             Date Modified: 24 May 2014
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link smarttrolleygui.ProductScreenController#loadFavourites(javafx.event.ActionEvent)}
	 * .
	 */
	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 25 May 2014
	*/
	@Test
	public final void testLoadSlide() {
		assertTrue(smartTrolleyApplication.productScreen.getDisplayedSlide() instanceof Slide);
	}

}

/************** End of TestProductScreenController.java **************/
