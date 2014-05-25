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
	private SmartTrolleyGUI GUIboot;
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

		/*
		 * Create a new thread which launches the application. If the main
		 * thread launches the application, the rest of the test will only run
		 * after the application closes i.e. pointless.
		 */Thread newGUIThread;
		GUIboot = new SmartTrolleyGUI();

		newGUIThread = new Thread("New GUI") {
			public void run() {
				SmartTrolleyPrint.print("GUI thread");
				Application.launch(GUIboot.getClass(), (java.lang.String[]) null);

			}
		};
		newGUIThread.start();

		// TODO Launch a stage+application, then replace its content with
		// SmartTrolleyGUI
		/*SmartTrolleyDelay.delay(1000);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage = new Stage();
			}
		});
		SmartTrolleyDelay.delay(1000);
		SmartTrolleyPrint.print("Here");
		stage.setTitle("Smart Trolley");
		stage.getIcons().add(new Image("smarttrolleygui/img/windowIcon.jpg"));
		stage.setMinWidth(MIN_WINDOW_WIDTH);
		stage.setMinHeight(MIN_WINDOW_HEIGHT);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.show();
			}
		});*/

		SmartTrolleyPrint.print("Showing a new stage");
		SmartTrolleyDelay.delay(1000);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				GUIboot.start(SmartTrolleyGUI.stage);
			}
		});

		SmartTrolleyDelay.delay(1000);

		// TODO At the moment, the test assumes the product screen is the first
		// screen that appears.
		// Button nextSlide = new Button();
		// nextSlide =
		GUIboot.productScreen.nextSLideButton.fire();
		
		while(true);

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
	@Test
	public final void testLoadFavourites() {
	}

}

/************** End of TestProductScreenController.java **************/
