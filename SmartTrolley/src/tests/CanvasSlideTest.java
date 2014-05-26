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
	
	
	@Before
	public void setUp(){
		productSlide = new ProductSlide();
	}
	
	@Test
	public void slideDisplayTest(){
		assertTrue(productSlide.setupAnchorPane().isVisible());
		
	}
	
	@Test
	public void displayingImageTest(){
		

		assertTrue(productSlide.imageSetup().isVisible());
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
		
		
		assertTrue(productSlide.graphicsSetup(points));
	}
}
