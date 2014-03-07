package graphicsHandler;

import static org.junit.Assert.*;

import java.util.PriorityQueue;
import java.util.Queue;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import org.junit.Before;
import org.junit.Test;

public class GraphicsHandlerTest {

	public PriorityQueue<ShapePoint> points;
	int width = 50, height = 50, pointLow = 0, pentagonX = 25, pentagonY = 25;
	int point1Num = 1, point2Num = 2, point3Num = 3, point4Num = 4, point5Num = 5;
	public SlideShapeFactory shapeFactory, circleFactory;
	public ShapePoint point1, point2, point3, point4, point5, testingPoint;
	public Shape square, pentagon, circle;

	@Before
	public void setUp() throws Exception {
		pointsSetup();		
		squareSetUp();
		circleSetup();
		
	}

	private void circleSetup() {
		PriorityQueue<ShapePoint> point = new PriorityQueue<ShapePoint>();
		point.add(point4);
		
		circleFactory = new SlideShapeFactory(point, height,width);
		
		circle = circleFactory.getShape();
		
		
	}

	private void pointsSetup() {
		point1 = new ShapePoint(pointLow,pointLow,point1Num);
		point2 = new ShapePoint(width,pointLow,point2Num);
		point3 = new ShapePoint(width,height,point3Num);
		point4 = new ShapePoint(pointLow,width,point4Num);
		point5 = new ShapePoint(pentagonX, pentagonY, point5Num);
		
		
		points = new PriorityQueue<ShapePoint>();
	}

	private void squareSetUp() {
		//set points with the first 4 ShapePoints to create a square
		points.add(point1);
		points.add(point3);
		points.add(point2);
		points.add(point4);
		
		//Instantiate a shapeFactory with the current values
		shapeFactory = new SlideShapeFactory(points, width, height);
		
		//get the shape that is created by the values previously given to the factory
		square = shapeFactory.getShape();
	}
	
	/*..................SQUARE TESTS...............................*/

	/**
	 * Calling class creates a rectangle
	 * 		tests a constructor with values to initialise with
	 * 		tests the ability to create a 4 pointed polygon in the shape of a square
	*/		
	@Test
	public void squareClassTest() {
		
		String shapetype = square.getClass().getName();
		// print the class of square to the console for manual test
		System.out.println("square is a: " + shapetype);
		
		//check that getShape returns a polygon using junit
		assertEquals(Polygon.class, square.getClass());
	}
	
	@Test
	public void squareHeightTest()	{
		Bounds squareBounds = square.getBoundsInLocal();
		double squareHeight =squareBounds.getHeight();
		assertEquals(squareHeight, height, 0.0001);
	}
	
	//tests that 
	@Test
	public void squarePointsTest()	{
		double[] testingPointDouble;
		
//		check points held in square against points specified
		int i = 1;
		while(!points.isEmpty())	{
			testingPoint = points.remove();
			testingPointDouble = new double[]{testingPoint.getxCoordinate(), testingPoint.getyCoordinate()};
			assertEquals(testingPointDouble, shapeFactory.getPoint(i));
			i++;
		}
	}
	
	@Test
	public void fillColorTest(){
		//specify RGB string for blue
		String blue = "#0000FF";
		//Paint for holding returned color from class under test
		Paint squareColor;
		
		shapeFactory.setFillColor(blue);
		squareColor = shapeFactory.getFillColor();
		
		assertEquals(Color.BLUE, squareColor);
	}


	@Test
	public void lineColorTest(){
		//specify RGB string for blue
		String blue = "#0000FF";
		//Paint for holding returned color from class under test
		Paint squareLineColor;
		
		shapeFactory.setLineColor(blue);
		squareLineColor = shapeFactory.getLineColor();
		
		assertEquals(Color.BLUE, squareLineColor);
	}
	
	@Test
	public void polygonResizeTest(){
		
		int extra = 8;
		
		int newWidth = width + extra;
		int newHeight = height + extra;
		shapeFactory.setWidth(newWidth);
		shapeFactory.setHeight(newHeight);
		
		Shape shape = shapeFactory.getShape();
		
		//
		assertEquals(newWidth, shape.getBoundsInLocal().getWidth(), 0.0001);
		assertEquals(newHeight, shape.getBoundsInLocal().getHeight(), 0.0001);
	}
	
	@Test
	public void durationTest(){
		fail("test not implemented");
	}
	
	
	/*..................Circle TESTS...............................*/
	
	@Test
	public void circleClassTest()	{
		assertEquals(Ellipse.class, circle.getClass());
	}
	
	@Test
	public void circleDiameterTest(){
		double circleHeight = circle.getBoundsInLocal().getHeight();
		
		assertEquals(height, circleHeight, 0.0001);
	}
	
	
}
