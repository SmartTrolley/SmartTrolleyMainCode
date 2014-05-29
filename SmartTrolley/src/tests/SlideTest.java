/**
 * SmartTrolley
 *
 * This file contains all automated tests for Slide
 *
 * @author Alick Jacklin
 * @author Matthew Wells
 *
 * @author Checked By: Prashant Chakravarty [29 May 2014]
 *
 * @version V1.4 [Date Created: 26 May 2014]
 */
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import graphicshandler.ShapePoint;
import graphicshandler.SlidePolygon;
import graphicshandler.SlideShapeFactory;

import imagehandler.SlideImage;

import java.util.ArrayList;
import java.util.PriorityQueue;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;

import audiohandler.AudioHandler;

import smarttrolleygui.Slide;
import texthandler.SlideText;
import texthandler.SlideTextBody;
import videohandler.SlideVideo;
import Printing.SmartTrolleyPrint;

public class SlideTest {

	private SlideVisualTest visualTesting;
	private Slide productSlide;
	
	/**sets parameters for image*/
	private String imageURL = "http://th03.deviantart.net/fs70/PRE/i/2013/077/8/9/cookie_monster_by_xenia_cat-d5yhjwj.jpg";
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
	String font = "Comic Sans MS";
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
	
	/**sets up ArrayLists to store information on various PWS media types*/
	private ArrayList<Shape> graphicsList;
	private ArrayList<SlideImage> imageList;
	private ArrayList<SlideVideo> videoList;
	private ArrayList<SlideText> textList; 
	private ArrayList<AudioHandler> audioList;
	
	/**sets scalers for all visual based PWS media*/
	private double xScaler;
	private double yScaler;

	/**
	 * Sets up Slide for tests, by launching the application and setting up media handlers.
	 * <p>
	 * Date Modified: 27 May 2014
	 */
	@Before
	public void setUp() {

		visualTesting = new SlideVisualTest();

		Thread JFXApplThread;
		JFXApplThread = new Thread("New JFXAppl") {
			public void run() {
				SmartTrolleyPrint.print("JFXAppl thread");
				Application.launch(visualTesting.getClass(),
						(java.lang.String[]) null);
			}
		};
		JFXApplThread.start();

		// Delay to allow the application to launch // If you get NullPointer
		// errors around this line, increase the delay
		SmartTrolleyDelay.delay(1500);

		// Now launch the instance of SlideVisualTest, which takes over the
		// displayed stage
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				try {
					visualTesting.start(SlideVisualTest.stage);
				} catch (Exception e) {
					SmartTrolleyPrint.print("Could not load application");
				}
			}
		});
		
		 setupImage();
		 setupGraphics();
		 setupAudio();
		 setupVideo();
		 setupText();	

		SmartTrolleyDelay.delay(2000);
		
		productSlide = new Slide(xScaler, yScaler, graphicsList,
				 imageList,  audioList,  textList,
				 videoList);

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
		AudioHandler audio2 = new AudioHandler(audURL2, (int) vidDuration, audDuration, audVolume2);
		
		audioList = new ArrayList<AudioHandler>();
		
		audioList.add(audio);
		audioList.add(audio2);
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
	 * Test that the AnchorPane is visible.
	 * <p>Date Modified: 27 May 2014
	 */
	@Test
	public void slideDisplayTest() {
		SmartTrolleyDelay.delay(1000);

		assertTrue(productSlide.isVisible());

	}


	/**
	 * Tests that the images are visible on the AnchorPane
	 * <p> Date Modified: 27 May 2014
	 */
	@Test
	public void displayingImageTest() {
		assertTrue(imageList.get(0).isVisible());
		assertTrue(imageList.get(1).isVisible());
		}
	/**
	 * Tests that the audio is playing
	 * <p>
	 * Date Modified: 27 May 2014
	 */
	@Test
	public void audioIsPlayingTest() {

		assertTrue(audioList.get(0).isPlaying());
		audioList.get(0).stop();
	}

	/**
	*test that video is displayed
	*<p> Date Modified: 29 May 2014
	*/
	@Test
	public void displayingVideoTest() {
		SmartTrolleyDelay.delay(1000);
		assertTrue(videoList.get(0).isVisible());

	}

	/**
	 * tests that the graphic is visible on the AnchorPane
	 * <p>
	 * Date Modified: 27 May 2014
	 */
	@Test
	public void displayingGraphicsTest() {

		assertTrue(graphicsList.get(0).isVisible());
	}




	/**
	 * tests that the audio has stopped
	 * <p>
	 * Date Modified: 27 May 2014
	 */
	@Test
	public void audioHasStoppedPlayingTest() {
		SmartTrolleyDelay.delay(6000);
		assertFalse(audioList.get(0).isPlaying());
	}

	/**
	 * Tests that the text is visible
	 * <p> Date Modified: 27 May 2014
	 */
	@Test
	public void displayingTextTest() {

		assertTrue(textList.get(0).isVisible());
	}
	

	
	
	/**
	 * Tests that all media has been removed from the slide
	 * <p> Date Modified: 27 May 2014
	 */
	@Test
	public void slideClearingTest() {

		SmartTrolleyDelay.delay(1000);
		
		SmartTrolleyPrint.print(productSlide.getChildren());

		productSlide.clearSlide();

		SmartTrolleyPrint.print(productSlide.getChildren());

		assertFalse(audioList.get(0).isPlaying());

		assertTrue(productSlide.getChildren().isEmpty());

	}



	/**
	*Test that image location is scaled based on scaling factors
	*<p> Date Modified: 29 May 2014
	*/
	@Test
	public void imageLocationScaleTest(){

		double imageScaledXPos = imageList.get(0).getLayoutX();
		assertEquals(imageScaledXPos, xImageStart*xScaler, 0.1);
		
		double imageScaledYPos =  imageList.get(0).getLayoutY();
		assertEquals(imageScaledYPos, yImageStart*yScaler, 0.1);
		}	

	/**
	*Test that graphics location is scaled based on scaling factors
	*<p> Date Modified: 29 May 2014
	*/
	@Test
	public void graphicsLocationScaleTest(){

		double graphicsScaledXPos = graphicsList.get(0).getLayoutX(); 
		assertEquals(graphicsScaledXPos, pointLow*xScaler, 0.1);
		
		double graphicsScaledYPos = graphicsList.get(0).getLayoutY();
		assertEquals(graphicsScaledYPos, pointLow*yScaler, 0.1);
		}	

	/**
	*Test that text location is scaled based on scaling factors
	*<p> Date Modified: 29 May 2014
	*/
	@Test
	public void textLocationScaleTest(){

		double textScaledXPos = textList.get(0).getLayoutX();
		assertEquals(textScaledXPos, xTextStart*xScaler, 0.1);
		
		double textScaledYPos = textList.get(0).getLayoutY(); 
		assertEquals(textScaledYPos, yTextStart*yScaler, 0.1);
		}		

	/**
	*Test that video location is scaled based on scaling factors
	*<p> Date Modified: 29 May 2014
	*/
	@Test
	public void videoLocationScaleTest(){

		double videoScaledXPos = videoList.get(0).getLayoutX();
		assertEquals(videoScaledXPos, xVidStart*xScaler, 0.1);
		
		double videoScaledYPos = videoList.get(0).getLayoutY();
		assertEquals(videoScaledYPos, yVidStart*yScaler, 0.1);
		}
	

}

/************** End of SlideTest.java **************/
