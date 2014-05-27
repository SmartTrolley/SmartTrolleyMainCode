/**
 * 
 */
package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import graphicshandler.ShapePoint;

import java.util.ArrayList;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Test;

import smarttrolleygui.ProductSlide;
import texthandler.SlideTextBody;
import Printing.SmartTrolleyPrint;

/**
 * @author Matthew Wells
 * @author Alick Jacklin
 *
 */
public class CanvasSlideTest {
	
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
	int numOfPoints = 4;
	
	private String audURL = "Music/Kalimba.mp3";
	private int audStartTime = 0;
	private int audDuration = 100;
	private double audVolume = 0.4;
	
	private ArrayList<SlideTextBody> texts;
	private String font = "Comic Sans MS";
	private String fontColor = "FF00FF";
	private int fontSize = 12;
	private int xTextStart = 350;
	private int yTextStart = 467;
	private int xTextEnd = 230;
	private int yTextEnd = 290;
	private double textStartTime = 0;
	private double textDuration = 0;
	private int numOfStrings = 3;
	
	@Before
	public void setUp(){
		
		productSlide= new ProductSlide(points, numOfPoints, graphicsWidth, graphicsHeight,
				graphicsFillColour, graphicsLineColour,
				graphicsStartTime, graphicsDuration,
				imageURL, xImageStart,  yImageStart,
				imageWidth, imageHeight, imageStartTime,
				imageDuration, audURL, audStartTime, audDuration, audVolume, texts,
				font, fontColor, numOfStrings, fontSize, xTextStart, yTextStart,
				xTextEnd, yTextEnd, textStartTime, textDuration);
	}
	
	@Test
	public void slideDisplayTest(){
		point1 = new ShapePoint(pointLow,pointLow,point1Num);
		point2 = new ShapePoint(width,pointLow,point2Num);
		point3 = new ShapePoint(width,height,point3Num);
		point4 = new ShapePoint(pointLow,width,point4Num);
		
		points = new PriorityQueue<ShapePoint>();
		points.add(point1);
		points.add(point3);
		points.add(point2);
		points.add(point4);
		
		assertTrue(productSlide.isVisible());
		
	}
	
	@Test
	public void displayingImageTest(){
		
		assertTrue(productSlide.imageSetup(imageURL, xImageStart, yImageStart, imageWidth,
				imageHeight, imageStartTime, imageDuration).isVisible());
	}
	
//	@Test
//	public void displayingVideoTest(){
//		assertTrue(productSlide.videoSetup().isVisible());
//	}
	
	@Test
	public void displayingGraphicsTest(){
		
		point1 = new ShapePoint(pointLow,pointLow,point1Num);
		point2 = new ShapePoint(width,pointLow,point2Num);
		point3 = new ShapePoint(width,height,point3Num);
		point4 = new ShapePoint(pointLow,width,point4Num);
		
		points = new PriorityQueue<ShapePoint>();
		points.add(point1);
		points.add(point3);
		points.add(point2);
		points.add(point4);
		
		
		assertTrue(productSlide.graphicsSetup(points, numOfPoints, graphicsWidth,
				graphicsHeight, graphicsFillColour, graphicsLineColour,
				graphicsStartTime, graphicsDuration).visibleProperty().get());
	}
	
		@Test
	public void audioIsPlayingTest(){
		SmartTrolleyDelay.delay(1000);
		assertTrue(productSlide.audio.isPlaying());
		productSlide.audio.stop();
	}
	
	@Test
	public void audioHasStoppedPlayingTest(){
		SmartTrolleyDelay.delay(1000);
		productSlide.audio.stop();
		assertFalse(productSlide.audio.isPlaying());
	}
	
	@Test
	public void displayingTextTest(){
		
		assertTrue(productSlide.textSetup(texts,
				font, fontColor, numOfStrings, fontSize, xTextStart, yTextStart,
				xTextEnd, yTextEnd, textStartTime, textDuration).isVisible());
	}
	
	
	@Test
	public void slideClearingTest(){
		
		point1 = new ShapePoint(pointLow,pointLow,point1Num);
		point2 = new ShapePoint(width,pointLow,point2Num);
		point3 = new ShapePoint(width,height,point3Num);
		point4 = new ShapePoint(pointLow,width,point4Num);
		
		points = new PriorityQueue<ShapePoint>();
		points.add(point1);
		points.add(point3);
		points.add(point2);
		points.add(point4);
		
		assertTrue(productSlide.imageSetup(imageURL, xImageStart,  yImageStart,
				imageWidth, imageHeight, imageStartTime, imageDuration).isVisible());
		
		SmartTrolleyPrint.print("First Test Passed, Image has loaded");
		
		assertTrue(productSlide.graphicsSetup(points, numOfPoints, graphicsWidth,
				graphicsHeight, graphicsFillColour, graphicsLineColour,
				graphicsStartTime, graphicsDuration).visibleProperty().get());
		
		SmartTrolleyPrint.print("Second Test passed, graphics have loaded" + productSlide);
		
		SmartTrolleyPrint.print(productSlide.getChildren());
		
		SmartTrolleyDelay.delay(1000);
		productSlide.audio.stop();
		assertFalse(productSlide.audio.isPlaying());
		
		productSlide.clearSlide();
		
		SmartTrolleyPrint.print(productSlide.getChildren());
		
		assertTrue(productSlide.getChildren().isEmpty());
		
	}
	

	

}
