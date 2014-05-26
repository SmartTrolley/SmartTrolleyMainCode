/**
 * 
 */
package tests;

import static org.junit.Assert.*;
import graphicshandler.ShapePoint;

import java.util.PriorityQueue;

import javafx.scene.canvas.Canvas;

import org.junit.Before;
import org.junit.Test;

import Printing.SmartTrolleyPrint;

import smarttrolleygui.ProductSlide;

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
	
	
	@Before
	public void setUp(){
		productSlide = new ProductSlide();
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
		
		assertTrue(productSlide.setupAnchorPane(points, imageURL, xImageStart,  yImageStart, imageWidth, imageHeight, imageStartTime, imageDuration).isVisible());
		
	}
	
	@Test
	public void displayingImageTest(){
		
		assertTrue(productSlide.imageSetup(imageURL, xImageStart,  yImageStart, imageWidth, imageHeight, imageStartTime, imageDuration).isVisible());
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
		
		
		assertTrue(productSlide.graphicsSetup(points).visibleProperty().get());
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
		
		assertTrue(productSlide.imageSetup(imageURL, xImageStart,  yImageStart, imageWidth, imageHeight, imageStartTime, imageDuration).isVisible());
		
		SmartTrolleyPrint.print("First Test Passed, Image has loaded");
		
		assertTrue(productSlide.graphicsSetup(points).visibleProperty().get());
		
		SmartTrolleyPrint.print("Second Test passed, graphics have loaded");
		
		SmartTrolleyDelay.delay(2000);
		
		productSlide.clearSlide(points, imageURL, xImageStart, yImageStart, imageWidth, imageHeight, imageStartTime, imageDuration);
		
		//assertFalse(productSlide.graphicsSetup(points).visibleProperty().get());
		//assertFalse(productSlide.imageSetup(imageURL, xImageStart, yImageStart, imageWidth, imageHeight, imageStartTime, imageDuration).isVisible());
		
		
	}
}
