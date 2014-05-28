//TODO This test class will not work in the final product since the Product Screen cannot be accessed without user interaction
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
		SmartTrolleyDelay.delay(2500);

		createAndStartTestSlideshow();

		// TODO At the moment, the test assumes the product screen is the first
		// screen that appears.
		// Delay to allow the application state to settle before running the test
		// If you get NullPointer errors around this line, increase the delay
		SmartTrolleyDelay.delay(500);

	}

	/**
	* Creates a slideshow for testing with
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 25 May 2014
	*/
	private void createAndStartTestSlideshow() {

		Slide firstSlide = new Slide();
		testSlideShow = new SlideShow(smartTrolleyApplication.productScreen.getProductAnchorPane());

		// TODO User IMAGE_HEIGHT & IMAGE_WIDTH constants instead of magic numbers
		Image productImage = new Image(getClass().getResourceAsStream("img/SampleProducts/Activia.jpg"), 100, 100, true, true);
		ImageView productImageView = new ImageView(productImage);

		firstSlide.addNodeToSlide(productImageView, Slide.SlideChildElements.IMAGE);
		testSlideShow.addSlideToSlideShow(firstSlide);

		productImage = new Image(getClass().getResourceAsStream("img/SampleProducts/alpen_blueberry_cranberry.jpg"), 100, 100, true, true);
		productImageView = new ImageView(productImage);

		Slide secondSlide = new Slide();
		secondSlide.addNodeToSlide(productImageView, Slide.SlideChildElements.IMAGE);

		testSlideShow.addSlideToSlideShow(secondSlide);

		SmartTrolleyPrint.print("testSlideShow is " + (testSlideShow == null) + " null.");
		smartTrolleyApplication.productScreen.setSlideShow(testSlideShow);

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
		assertTrue(smartTrolleyApplication.productScreen.getCurrentSlideShow().getSlides() instanceof ArrayList);

		for (Slide slide : smartTrolleyApplication.productScreen.getCurrentSlideShow().getSlides()) {
			assertNotNull(slide);
			assertTrue(slide instanceof Slide);
		}
	}

	/**
	*Tests that the first slide is the slide shown when the slideshow is launched
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 27 May 2014
	*/
	@Test
	public final void testFirstSlideisDefaultSlideShown() {

		assertEquals(smartTrolleyApplication.productScreen.getCurrentSlideShow().getSlides().get(0), smartTrolleyApplication.productScreen.getCurrentSlideShow()
				.getDisplayedSlide());
	}

	/**
	*Tests that the next slide button goes to the next slide
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 27 May 2014
	*/
	@Test
	public final void testNextSlideButton() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smartTrolleyApplication.productScreen.nextSLideButton.fire();
			}
		});
		SmartTrolleyDelay.delay(500);
		assertEquals(smartTrolleyApplication.productScreen.getCurrentSlideShow().getSlides().get(1), smartTrolleyApplication.productScreen.getCurrentSlideShow()
				.getDisplayedSlide());
	}
	
	/**
	*Tests that the user is told when there are no more slides
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 27 May 2014
	*/
	@Test
	public final void testSlideShowEndsWhenNoMoreSlides() {
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				for (Slide slide : smartTrolleyApplication.productScreen.getCurrentSlideShow().getSlides()) {
					smartTrolleyApplication.productScreen.nextSLideButton.fire();
					SmartTrolleyPrint.print("Next button fired in testSlideShowEndsWhenNoMoreSlides Test");
				}
				
			}
		});
		
		SmartTrolleyDelay.delay(500);
		assertTrue(smartTrolleyApplication.productScreen.getCurrentSlideShow().endOfListMsgBx.isShowing());
	}
	
	/**
	*Tests that user is notified when prev button is pressed when on 1st slide
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 27 May 2014
	*/
	@Test
	public final void testPrevButtonOnFirstSlide() {
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {				
					smartTrolleyApplication.productScreen.prevSLideButton.fire();
					SmartTrolleyPrint.print("Prev button fired in testPrevButtonOnFirstSlide Test");
			}
		});
		
		SmartTrolleyDelay.delay(500);
		assertTrue(smartTrolleyApplication.productScreen.getCurrentSlideShow().startOfListMsgBx.isShowing());
	}
	
	//TODO testStartScreenDisplayedOnNoSlideshow test
	/**
	* This test checks that if a slideshow is not loaded, the currentSlideshow is null
	* and the user is taken to the start screen
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 27 May 2014
	
	@Test
	public void testStartScreenDisplayedOnNoSlideshow() {
		smartTrolleyApplication.productScreen.getCurrentSlideShow().setSlides(null);	
		assertTrue(smartTrolleyApplication.productScreen.getCurrentSlideShow().getSlides() == null);
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smartTrolleyApplication.productScreen.nextSLideButton.fire();
			}
		});
		
		//OK now needs to be pressed on the message box to go to the start screen
		
		SmartTrolleyGUI.stage.getClass(); 		//This may return the class of the screen that the program is in
		//assertTrue(smartTrolleyApplication.stage.getClass());
		
	}*/
	
	
}

/************** End of TestProductScreenController.java **************/
