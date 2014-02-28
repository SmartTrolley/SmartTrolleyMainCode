package graphicsHandler;

import static org.junit.Assert.*;

import java.util.PriorityQueue;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import org.junit.Before;
import org.junit.Test;

public class GraphicsHandlerTest {

	PriorityQueue<ShapePoint> points;
	int width = 50, height = 50, pointLow = 0, pentagonX = 25, pentagonY = 25;
	int point1Num = 1, point2Num = 2, point3Num = 3, point4Num = 4, point5Num = 5;
	SlideShapeFactory shapeFactory;
	ShapePoint point1, point2, point3, point4, point5, testingPoint;
	Shape square, pentagon, circle;


	@Before
	public void setUp() throws Exception {

		point1 = new ShapePoint(pointLow,pointLow,point1Num);
		point2 = new ShapePoint(width,pointLow,point2Num);
		point3 = new ShapePoint(width,height,point3Num);
		point4 = new ShapePoint(pointLow,width,point4Num);
		point5 = new ShapePoint(pentagonX, pentagonY, point5Num);
		
	}
	
	/*..................SQUARE TESTS...............................*/

	/**
	 * Calling class creates a rectangle
	 * 		tests a constructor with values to initialise with
	 * 		tests the ability to create a 4 pointed polygon in the shape of a square
	*/		
	@Test
	public void squareClassTest() {
		points = new PriorityQueue<ShapePoint>();
		
		//set points with the first 4 ShapePoints to create a square
		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);
		
		//Instantiate a shapeFactory with the current values
		shapeFactory = new SlideShapeFactory(points, width, height);
		

		
		//get the shape that is created by the values previously given to the factory
		square = shapeFactory.getShape();
		
		String shapetype = square.getClass().getName();
		// print the class of square to the console for manual test
		System.out.println("square is a: " + shapetype);
		
		//check that getShape returns a polygon using junit
		assertEquals(Polygon.class, square.getClass());
	}
	
	@Test
	public void squareHeightTest()	{
		double squareHeight = square.getBoundsInLocal().getHeight();
		assertEquals(squareHeight, height, 0.001);
	}
	
	
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
}

