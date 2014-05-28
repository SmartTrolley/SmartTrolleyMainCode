/**
 * SmartTrolley
 *
 * This file contains all automated tests for ProductSlide
 *
 * @author Alick Jacklin
 * @author Matthew Wells
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.4 [Date Created: 26 May 2014]
 */
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import graphicshandler.ShapePoint;

import java.util.ArrayList;
import java.util.PriorityQueue;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import org.junit.Before;
import org.junit.Test;

import smarttrolleygui.ProductSlide;
import texthandler.SlideTextBody;
import Printing.SmartTrolleyPrint;

public class ProductSlideTest {

	private ProductSlideVisualTest visualTesting;
	private Stage stage;
	private ProductSlide productSlide;
	public PriorityQueue<ShapePoint> points;
	public ShapePoint point1, point2, point3, point4;
	int point1Num = 1, point2Num = 2, point3Num = 3, point4Num = 4;
	int width = 50, height = 50, pointLow = 0, pentagonX = 25, pentagonY = 25;

	private String imageURL = "http://th03.deviantart.net/fs70/PRE/i/2013/077/8/9/cookie_monster_by_xenia_cat-d5yhjwj.jpg";
	private int xImageStart = 100;
	private int yImageStart = 100;
	private int imageWidth = 78;
	private int imageHeight = 128;
	private int imageStartTime = 0;
	private int imageDuration = 0;

	private String graphicsFillColour = "#0000FF";
	private String graphicsLineColour = "#0000FF";
	private int graphicsWidth = 50;
	private int graphicsHeight = 75;
	int graphicsStartTime = 0;
	int graphicsDuration = 0;
	int numOfPoints = 3;

	private String audURL = "Music/Kalimba.mp3";
	private int audStartTime = 0;
	private int audDuration = 4;
	private double audVolume = 0.4;

	private ArrayList<SlideTextBody> texts;
	private String font = "Times New Roman";
	private String fontColor = "FF00FF";
	private int fontSize = 12;
	private int xTextStart = 350;
	private int yTextStart = 467;
	private int xTextEnd = 230;
	private int yTextEnd = 290;
	private double textStartTime = 0;
	private double textDuration = 0;
	private int numOfStrings = 3;

	private String vidURL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
	private int xVidStart = 200;
	private int yVidStart = 20;
	private int VidWidth = 150;
	private int VidHeight = 75;
	private boolean vidLoop = true;
	private double vidStartTime = 2.0;
	private double vidDuration = 30.0;
	
	//for resizing

	private int slideHeight = 600;
	private int slideWidth = 800;
	
	private double yScaler = slideHeight/1000;
	private double xScaler = slideWidth/1000;

	/**
	 * Sets up ProductSlide for tests
	 * <p>
	 * Date Modified: 27 May 2014
	 */
	@Before
	public void setUp() {

		visualTesting = new ProductSlideVisualTest();

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

		// Now launch the instance of SmartTrolleyGUI, which takes over the
		// displayed stage
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				try {
					visualTesting.start(ProductSlideVisualTest.stage);
				} catch (Exception e) {
					SmartTrolleyPrint.print("Could not load application");
				}
			}
		});

		SmartTrolleyDelay.delay(1000);

		productSlide = new ProductSlide(xScaler, yScaler, graphicsList, imageList, audioList, textList,
				videoList);

	}

	/**
	 * Test that the AnchorPane is visible.
	 * <p>
	 * Date Modified: 27 May 2014
	 */
	@Test
	public void slideDisplayTest() {
		point1 = new ShapePoint(pointLow, pointLow, point1Num);
		point2 = new ShapePoint(width, pointLow, point2Num);
		point3 = new ShapePoint(width, height, point3Num);
		point4 = new ShapePoint(pointLow, width, point4Num);

		points = new PriorityQueue<ShapePoint>();
		points.add(point1);
		points.add(point3);
		points.add(point2);
		points.add(point4);

		assertTrue(productSlide.isVisible());

	}

	/**
	 * tests that the image is visible on the AnchorPane
	 * <p>
	 * Date Modified: 27 May 2014
	 */
	@Test
	public void displayingImageTest() {

		assertTrue(productSlide.imageSetup(imageURL, xImageStart, yImageStart,
				imageWidth, imageHeight, imageStartTime, imageDuration, xScaler, yScaler)
				.isVisible());
	}

	@Test
	public void displayingVideoTest() {
		SmartTrolleyDelay.delay(1000);
		assertTrue(productSlide.videoSetup(vidURL, xVidStart, yVidStart,
				VidWidth, VidHeight, vidLoop, vidStartTime, vidDuration, xScaler, yScaler)
				.isVisible());

	}

	/**
	 * tests that the graphic is visible on the AnchorPane
	 * <p>
	 * Date Modified: 27 May 2014
	 */
	@Test
	public void displayingGraphicsTest() {

		point1 = new ShapePoint(pointLow, pointLow, point1Num);
		point2 = new ShapePoint(width, pointLow, point2Num);
		point3 = new ShapePoint(width, height, point3Num);
		point4 = new ShapePoint(pointLow, width, point4Num);

		points = new PriorityQueue<ShapePoint>();
		points.add(point1);
		points.add(point3);
		points.add(point2);
		points.add(point4);

		assertTrue(productSlide
				.graphicsSetup(points, numOfPoints, graphicsWidth,
						graphicsHeight, graphicsFillColour, graphicsLineColour,
						graphicsStartTime, graphicsDuration, xScaler, yScaler).visibleProperty()
				.get());
	}

	/**
	 * tests that the audio is playing
	 * <p>
	 * Date Modified: 27 May 2014
	 */
	@Test
	public void audioIsPlayingTest() {
		SmartTrolleyDelay.delay(500);
		assertTrue(productSlide.audio.isPlaying());
		productSlide.audio.stop();
	}

	/**
	 * tests that the audio has stopped
	 * <p>
	 * Date Modified: 27 May 2014
	 */
	@Test
	public void audioHasStoppedPlayingTest() {
		SmartTrolleyDelay.delay(6000);
		assertFalse(productSlide.audio.isPlaying());
	}

	/**
	 * Tests that the text is visible
	 * <p>
	 * Date Modified: 27 May 2014
	 */
	@Test
	public void displayingTextTest() {

		assertTrue(productSlide.textSetup(texts, font, fontColor, numOfStrings,
				fontSize, xTextStart, yTextStart, xTextEnd, yTextEnd,
				textStartTime, textDuration, xScaler, yScaler).isVisible());
	}
	

	
	
	/**
	 * Tests that all media have been removed from the slide
	 * <p>
	 * Date Modified: 27 May 2014
	 */
	@Test
	public void slideClearingTest() {

		point1 = new ShapePoint(pointLow, pointLow, point1Num);
		point2 = new ShapePoint(width, pointLow, point2Num);
		point3 = new ShapePoint(width, height, point3Num);
		point4 = new ShapePoint(pointLow, width, point4Num);

		points = new PriorityQueue<ShapePoint>();
		points.add(point1);
		points.add(point3);
		points.add(point2);
		points.add(point4);

		assertTrue(productSlide.imageSetup(imageURL, xImageStart, yImageStart,
				imageWidth, imageHeight, imageStartTime, imageDuration, xScaler, yScaler)
				.isVisible());

		SmartTrolleyPrint.print("First Test Passed, Image has loaded");

		assertTrue(productSlide
				.graphicsSetup(points, numOfPoints, graphicsWidth,
						graphicsHeight, graphicsFillColour, graphicsLineColour,
						graphicsStartTime, graphicsDuration, xScaler, yScaler).visibleProperty()
				.get());

		SmartTrolleyPrint.print("Second Test passed, graphics have loaded"
				+ productSlide);

		SmartTrolleyPrint.print(productSlide.getChildren());

		SmartTrolleyDelay.delay(1000);

		productSlide.clearSlide();

		SmartTrolleyPrint.print(productSlide.getChildren());

		assertFalse(productSlide.audio.isPlaying());

		assertTrue(productSlide.getChildren().isEmpty());

	}


	@Test
	public void imageLocationScaleTest(){

		double imageScaledXPos = productSlide.imageSetup(imageURL, xImageStart, yImageStart,
				imageWidth, imageHeight, imageStartTime, imageDuration, xScaler, yScaler)
				.getLayoutX(); 
		assertEquals(imageScaledXPos, xImageStart*xScaler, 0.1);
		
		double imageScaledYPos = productSlide.imageSetup(imageURL, xImageStart, yImageStart,
				imageWidth, imageHeight, imageStartTime, imageDuration, xScaler, yScaler)
				.getLayoutY(); 
		assertEquals(imageScaledYPos, yImageStart*yScaler, 0.1);
		}		
	
	@Test
	public void graphicsLocationScaleTest(){

		double graphicsScaledXPos = productSlide.graphicsSetup(points, numOfPoints, graphicsWidth,
				graphicsHeight, graphicsFillColour, graphicsLineColour, 
				graphicsStartTime, graphicsDuration, xScaler, yScaler).
				getLayoutX(); 
		assertEquals(graphicsScaledXPos, pointLow*xScaler, 0.1);
		
		double graphicsScaledYPos = productSlide.graphicsSetup(points, numOfPoints, graphicsWidth,
				graphicsHeight, graphicsFillColour, graphicsLineColour, 
				graphicsStartTime, graphicsDuration, xScaler, yScaler).
				getLayoutY(); 
		assertEquals(graphicsScaledYPos, pointLow*yScaler, 0.1);
		}	
	
	@Test
	public void textLocationScaleTest(){

		double textScaledXPos = productSlide.textSetup(texts, font, fontColor,
				numOfStrings, fontSize, xTextStart, yTextStart, xTextEnd, 
				yTextEnd, textStartTime, textDuration, xScaler, yScaler)
				.getLayoutX(); 
		assertEquals(textScaledXPos, xTextStart*xScaler, 0.1);
		
		double textScaledYPos = productSlide.textSetup(texts, font, fontColor,
				numOfStrings, fontSize, xTextStart, yTextStart, xTextEnd, 
				yTextEnd, textStartTime, textDuration, xScaler, yScaler)
				.getLayoutY(); 
		assertEquals(textScaledYPos, yTextStart*yScaler, 0.1);
		}		
	
	@Test
	public void videoLocationScaleTest(){

		double videoScaledXPos = productSlide.videoSetup(vidURL, xVidStart,
				yVidStart, VidWidth, VidHeight, vidLoop, vidStartTime, 
				vidDuration, xScaler, yScaler)
				.getLayoutX(); 
		assertEquals(videoScaledXPos, xVidStart*xScaler, 0.1);
		
		double videoScaledYPos = productSlide.videoSetup(vidURL, xVidStart,
				yVidStart, VidWidth, VidHeight, vidLoop, vidStartTime, 
				vidDuration, xScaler, yScaler)
				.getLayoutY(); 
		assertEquals(videoScaledYPos, yVidStart*yScaler, 0.1);
		}		

}

/************** End of ProductSlideTest.java **************/
