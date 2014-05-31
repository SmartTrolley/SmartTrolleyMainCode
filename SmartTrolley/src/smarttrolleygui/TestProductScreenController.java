/**
 * SmartTrolley
 *
 * This file contains the test case for the product screen {@link smarttrolleygui.ProductScreenController}
 *
 * @author Prashant Chakravarty
 *
 * @author Checked By: Alasdair: 29 May 2014
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

import toolBox.SmartTrolleyToolBox;

public class TestProductScreenController {

	/**An instance of the application*/
	private SmartTrolleyGUI smartTrolleyApplication;

	/**A SlideShow created for this test*/
	private SlideShow testSlideShow;

	/**Duration of Slide*/
	private int slideDuration = 1;

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
				SmartTrolleyToolBox.print("GUI thread");
				Application.launch(smartTrolleyApplication.getClass(), (java.lang.String[]) null);

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
				smartTrolleyApplication.start(SmartTrolleyGUI.stage);
				smartTrolleyApplication.goToProductScreen();
			}
		});
		
		SmartTrolleyGUI.setCurrentProductID(21);

		// Delay to allow the instance to launch.
		// If you get NullPointer errors around this line, increase the delay
		SmartTrolleyToolBox.delay(2500);

		createAndStartTestSlideshow();

		// screen that appears.
		// Delay to allow the application state to settle before running the test
		// If you get NullPointer errors around this line, increase the delay
		SmartTrolleyToolBox.delay(500);

	}

	/**
	* Creates a slideshow for testing with
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 25 May 2014
	*/
	private void createAndStartTestSlideshow() {

		Slide firstSlide = new Slide(slideDuration);
		testSlideShow = new SlideShow(smartTrolleyApplication.productScreen.getProductAnchorPane());

		// TODO User IMAGE_HEIGHT & IMAGE_WIDTH constants instead of magic numbers
		Image productImage = new Image(getClass().getResourceAsStream("img/SampleProducts/Activia.jpg"), 100, 100, true, true);
		ImageView productImageView = new ImageView(productImage);

		firstSlide.addNodeToSlide(productImageView, Slide.SlideChildElements.IMAGE);
		testSlideShow.addSlideToSlideShow(firstSlide);

		productImage = new Image(getClass().getResourceAsStream("img/SampleProducts/alpen_blueberry_cranberry.jpg"), 100, 100, true, true);
		productImageView = new ImageView(productImage);

		Slide secondSlide = new Slide(slideDuration);
		secondSlide.addNodeToSlide(productImageView, Slide.SlideChildElements.IMAGE);

		testSlideShow.addSlideToSlideShow(secondSlide);

		SmartTrolleyToolBox.print("testSlideShow is " + (testSlideShow == null) + " null.");

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smartTrolleyApplication.productScreen.setSlideShow(testSlideShow);
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
		SmartTrolleyToolBox.delay(500);
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
				for (int i = 0; i < smartTrolleyApplication.productScreen.getCurrentSlideShow().getSlides().size(); i++) {
					smartTrolleyApplication.productScreen.nextSLideButton.fire();
					SmartTrolleyToolBox.print("Next button fired in testSlideShowEndsWhenNoMoreSlides Test");
				}

			}
		});

		SmartTrolleyToolBox.delay(500);
		assertTrue(smartTrolleyApplication.productScreen.getCurrentSlideShow().outOfSlideShowMessageBox.isShowing());
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
				SmartTrolleyToolBox.print("Prev button fired in testPrevButtonOnFirstSlide Test");
			}
		});

		SmartTrolleyToolBox.delay(500);
		assertTrue(smartTrolleyApplication.productScreen.getCurrentSlideShow().outOfSlideShowMessageBox.isShowing());
	}

	/**
	*Tests that slide changes after the duration has elapsed
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 27 May 2014
	*/
	@Test
	public final void testSlideDuration() {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smartTrolleyApplication.productScreen.playPauseButton.fire();
			}
		});
		SmartTrolleyToolBox.delay(3000 * slideDuration);
		assertTrue(smartTrolleyApplication.productScreen.getCurrentSlideShow().outOfSlideShowMessageBox.isShowing());
	}
	
	/**
	*Tests the product is a favorite when the favorite button is pressed
	*<p> User can set product as favorite
	*<p> Date Modified: 27 May 2014
	*/
	@Test
	public final void testFavoriteButton() {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smartTrolleyApplication.productScreen.favoritesButton.fire();
			}
		});		
		//assertTrue(smartTrolleyApplication.productScreen.product.);

	}

}

/************** End of TestProductScreenController.java **************/
