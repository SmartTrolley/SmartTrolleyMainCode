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
import graphicshandler.ShapePoint;
import graphicshandler.SlidePolygon;
import graphicshandler.SlideShapeFactory;
import imagehandler.SlideImage;

import java.util.ArrayList;
import java.util.PriorityQueue;

import javafx.application.Platform;
import javafx.scene.shape.Shape;

import org.junit.Before;
import org.junit.Test;

import texthandler.SlideText;
import texthandler.SlideTextBody;
import toolBox.SmartTrolleyToolBox;
import videohandler.SlideVideo;
import audiohandler.AudioHandler;

public class TestProductScreenController {

	/**An instance of the application*/
	private SmartTrolleyGUI smartTrolleyApplication;

	/**A SlideShow created for this test*/
	private SlideShow testSlideShow;

	/**Duration of Slide*/
	private int slideDuration = 1;
	
	private ArrayList<Shape> graphicsList;
	private ArrayList<SlideImage> imageList;
	private ArrayList<SlideVideo> videoList;
	private ArrayList<SlideText> textList; 
	private ArrayList<AudioHandler> audioList;
	private double xScaler = 0.5;
	private double yScaler = 0.5;

	/**sets parameters for image*/
	private String imageURL = "http://th03.deviantart.net/fs70/PRE/i/2013/077/8/9/cookie_monster_by_xenia_cat-d5yhjwj.jpg";
	private String imageURL2 = "http://th00.deviantart.net/fs71/PRE/i/2013/160/9/b/tess_will_show_you_how_to_burlesque_by_xariamaru-d68fitd.png";
	private int xImageStart = 10;
	private int yImageStart = 10;
	private int xImageStart2 = 200;
	private int yImageStart2 = 300;
	private int imageWidth = 100;
	private int imageHeight = 150;
	private int imageStartTime = 0;
	private int imageDuration = 0;
	
	
	
	/**sets parameters for graphics*/
	private String graphicsFillColour = "#0000FF";
	private String graphicsLineColour = "#0000FF";
	private int graphicsWidth = 50;
	private int graphicsHeight = 75;
	int graphicsStartTime = 5;
	int graphicsDuration = 5;
	int numOfPoints = 1;
	private PriorityQueue<ShapePoint> points;
	private SlidePolygon pentagon;
	protected int point1Num = 1, point2Num = 2, point3Num = 3, point4Num = 4, point5Num = 5;
	protected int pointLow = 60, pentagonX = 25, pentagonY = 25;
	protected int maxPoints= 5;
	
	/**sets parameters for audio*/
	public AudioHandler audio;
	private String audURL = "Music/Kalimba.mp3";
	private String audURL2 = "Music/Shop2.mp3";
	private int audStartTime = 0;
	private int audDuration = 5;
	private double audVolume = 0.4;
	private double audVolume2 = 1.0;
	
	
	/**sets parameters for text*/
	ArrayList<SlideTextBody> texts;
	String font = "Times New Roman";
	String fontColor = "FF00FF";
	int fontSize = 12;
	int xTextStart = 355;
	int yTextStart = 467;
	int xTextEnd = 375;
	int yTextEnd = 487;
	double textStartTime = 0;
	double textDuration = 0;
	private String oneString = "A Long-Expected Party. When Mr. Bilbo Baggins of Bag End announced\n" + "that he would shortly be celebrating his eleventy-first birthday";
	
	/**sets Parameters for video*/
	private String vidURL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
	private int xVidStart = 200;
	private int yVidStart = 20;
	private int vidWidth = 480;
	private int vidHeight = 240;
	private boolean vidLoop = true;
	private double vidStartTime = 0;
	private double vidDuration = 5.0;
	
	
	/**
	 * This method runs before every test.
	 * @throws java.lang.Exception
	 *<p> Date Modified: 24 May 2014
	 */
	@Before
	public void setUp() throws Exception {

		smartTrolleyApplication = new SmartTrolleyGUI();

		smartTrolleyApplication = TestGUINavigationForTests.launchTestApplication(smartTrolleyApplication);
		TestGUINavigationForTests.goToProductScreen(smartTrolleyApplication);
		 setupImage();
		 setupGraphics();
		 setupAudio();
		 setupVideo();
		 setupText();	
		
		 SmartTrolleyGUI.setCurrentProductID(21);


		createAndStartTestSlideshow();

		// screen that appears.
		// Delay to allow the application state to settle before running the test
		// If you get NullPointer errors around this line, increase the delay
		SmartTrolleyToolBox.delay(500);

	}

	
	/**
	*sets up images to be stored in the imageList ArrayList
	*<p> Date Modified: 29 May 2014
	*/
	public void setupImage(){
		SlideImage image = new SlideImage(imageURL, xImageStart, yImageStart, imageWidth, imageHeight,
				imageStartTime, 3);
		
		SlideImage image2 = new SlideImage(imageURL, xImageStart2, yImageStart2, imageWidth/2, imageHeight/2,
				imageStartTime, imageDuration);
	
		imageList = new ArrayList<SlideImage>();
		
		imageList.add(image);
		imageList.add(image2);
	}
	

	/**
	*sets up images to be stored in the imageList ArrayList
	*<p> Date Modified: 29 May 2014
	*/
	public void setupImage2(){
		SlideImage image = new SlideImage(imageURL2, xImageStart, yImageStart, imageWidth, imageHeight,
				imageStartTime, 3);
	
		imageList = new ArrayList<SlideImage>();
		
		imageList.add(image);

	}
	
	/**
	*sets up graphics to be stored in the graphicsList ArrayList
	*<p> Date Modified: 29 May 2014
	*/
	public void setupGraphics(){
		points = new PriorityQueue<ShapePoint>();
		points.add(new ShapePoint(pointLow,pointLow,point1Num));
		points.add(new ShapePoint(graphicsWidth,pointLow,point2Num));
		points.add(new ShapePoint(graphicsWidth,graphicsHeight,point3Num));
		points.add(new ShapePoint(pointLow, graphicsWidth,point4Num));
		points.add(new ShapePoint(pentagonX, pentagonY, point5Num));
		
		SlideShapeFactory shapeFactory = new SlideShapeFactory(points, graphicsHeight,graphicsWidth,
				graphicsFillColour, graphicsLineColour, graphicsStartTime, graphicsDuration);
		pentagon = (SlidePolygon) shapeFactory.getShape();
		
		graphicsList = new ArrayList<Shape>();
		
		graphicsList.add(pentagon);
	}
	
	/**
	*sets up audio to be stored in the audioList ArrayList
	*<p> Date Modified: 29 May 2014
	*/
	public void setupAudio(){
		AudioHandler audio = new AudioHandler(audURL, audStartTime, audDuration, audVolume);
		
		audioList = new ArrayList<AudioHandler>();
		
		audioList.add(audio);
	}
	
	/**
	*sets up video to be stored in the videoList ArrayList
	*<p> Date Modified: 29 May 2014
	*/
	public void setupVideo(){
		SlideVideo video = new SlideVideo(vidURL, xVidStart, yVidStart, vidWidth, vidHeight, vidLoop,
				vidStartTime, vidDuration);
		

		videoList = new ArrayList<SlideVideo>();
		videoList.add(video);

	}
	
	/**
	*sets up text to be stored in the textList ArrayList
	*<p> Date Modified: 29 May 2014
	*/
	public void setupText(){
		texts = new ArrayList<SlideTextBody>();
		texts.add(new SlideTextBody(oneString, true, true, true, 1));
		
		SlideText textBox = new SlideText(texts, font, fontColor, fontSize, xTextStart, yTextStart,
				xTextEnd, yTextEnd, textStartTime, textDuration);
		
		textList = new ArrayList<SlideText>();
		textList.add(textBox);
		
	}


	/**
	* Creates a slideshow for testing with
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 25 May 2014
	*/
	private void createAndStartTestSlideshow() {

		Slide firstSlide = new Slide(xScaler, yScaler, graphicsList,
				 imageList, audioList,  textList,
				 videoList, slideDuration);
		testSlideShow = new SlideShow(smartTrolleyApplication.productScreen.getProductAnchorPane());

		testSlideShow.addSlideToSlideShow(firstSlide);
		
		setupImage2();
		setupGraphics();
		setupAudio();
		setupVideo();
		setupText();	

		Slide secondSlide = new Slide(xScaler, yScaler, graphicsList,
				 imageList, audioList, textList,
				 videoList, slideDuration);


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
		TestGUINavigationForTests.goToNextTestSlide(smartTrolleyApplication);
		
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

		TestGUINavigationForTests.goToPrevTestSlide(smartTrolleyApplication);
		
		assertTrue(smartTrolleyApplication.productScreen.getCurrentSlideShow().outOfSlideShowMessageBox.isShowing());
	}

	/**
	*Tests that slide changes after the duration has elapsed
	*<p> User can view PWS Compatible slideshow
	*<p> Date Modified: 27 May 2014
	*/
	@Test
	public final void testSlideDuration() {

		TestGUINavigationForTests.playPauseSlideshow(smartTrolleyApplication);
		
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

		//while(true);
//		Platform.runLater(new Runnable() {
//			@Override
//			public void run() {
//				smartTrolleyApplication.productScreen.favoritesButton.fire();
//			}
//		});		
		//assertTrue(smartTrolleyApplication.productScreen.product.);

	}

}

/************** End of TestProductScreenController.java **************/
