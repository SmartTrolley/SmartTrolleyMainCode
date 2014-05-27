/**
 * SmartTrolley
 *
 * This file contains the test case for the product screen {@link smarttrolleygui.ProductScreenController}
 *
 * @author Prashant Chakravarty
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.0 [Date Created: 24 May 2014]
 */

package smarttrolleygui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.junit.Before;
import org.junit.Test;

import Printing.SmartTrolleyPrint;

public class TestProductScreenController {

	/**An instance of the application*/
	private SmartTrolleyGUI smartTrolleyApplication;

	/**The displayed slide*/
	private Slide displayedSlide;

	/**A SlideShow created for this test*/
	private SlideShow testSlideShow;

	/**
	 * This method runs before every test.
	 * @throws java.lang.Exception
	 *<p> Date Modified: 24 May 2014
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

		// Delay to allow the application to launch
		// If you get NullPointer errors around this line, increase the delay
		SmartTrolleyDelay.delay(200);

		// Now launch the instance of SmartTrolleyGUI, which takes over the displayed stage
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smartTrolleyApplication.start(SmartTrolleyGUI.stage);
			}
		});

		// Delay to allow the instance to launch.
		// If you get NullPointer errors around this line, increase the delay
		SmartTrolleyDelay.delay(1500);

		createAndStartTestSlideshow();

		// TODO At the moment, the test assumes the product screen is the first
		// screen that appears.
		// Delay to allow the application state to settle before running the test
		// If you get NullPointer errors around this line, increase the delay
		SmartTrolleyDelay.delay(1000);
		while(true);

	}

	/**
	* Creates a slideshow for testing with
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 25 May 2014
	*/
	private void createAndStartTestSlideshow() {

		displayedSlide = new Slide();
		testSlideShow = new SlideShow(smartTrolleyApplication.productScreen.getProductAnchorPane());

		// TODO User IMAGE_HEIGHT & IMAGE_WIDTH constants instead of magic numbers
		Image productImage = new Image(getClass().getResourceAsStream("img/SampleProducts/Activia.jpg"), 100, 100, true, true);
		ImageView productImageView = new ImageView(productImage);

		// Platform.runLater(new Runnable() {
		// @Override
		// public void run() {
		displayedSlide.addNodeToSlide(productImageView, Slide.SlideChildElements.IMAGE);
		testSlideShow.addSlideToSlideShow(displayedSlide);
		// }
		// });

		// Platform.runLater(new Runnable() {
		// @Override
		// public void run() {
		productImage = new Image(getClass().getResourceAsStream("img/SampleProducts/alpen_blueberry_cranberry.jpg"), 100, 100, true, true);
		productImageView = new ImageView(productImage);
		displayedSlide.addNodeToSlide(productImageView, Slide.SlideChildElements.IMAGE);
		testSlideShow.addSlideToSlideShow(displayedSlide);
		// }
		// });

		 Platform.runLater(new Runnable() {
		 @Override
		 public void run() {
		testSlideShow.startSlideshow();
			}
		});
	}

	/**
	*Tests that the displayed slide is not null and slide 
	*<p>User views products
	*<p> Date Modified: 25 May 2014
	*/
	@Test
	public final void testLoadSlide() {
		assertNotNull(smartTrolleyApplication.productScreen.getCurrentSlideShow().getDisplayedSlide());
		assertTrue(smartTrolleyApplication.productScreen.getCurrentSlideShow().getDisplayedSlide() instanceof Slide);
	}

	/**
	*Tests that the slideshow is not null and contains a list of slides
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 25 May 2014
	*/
	@Test
	public final void slideShowIsList() {

		assertNotNull(smartTrolleyApplication.productScreen.getCurrentSlideShow());
		assertTrue(smartTrolleyApplication.productScreen.getCurrentSlideShow().getSlides() instanceof LinkedList);

		// I think this is the syntax, but not entirely sure
		for (Slide slide : smartTrolleyApplication.productScreen.getCurrentSlideShow().getSlides()) {
			assertNotNull(slide);
			assertTrue(slide instanceof Slide);
		}
	}

}

/************** End of TestProductScreenController.java **************/
