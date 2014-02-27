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
	ShapePoint point1, point2, point3, point4, point5;


	@Before
	public void setUp() throws Exception {

		point1 = new ShapePoint(pointLow,pointLow,point1Num);
		point2 = new ShapePoint(width,pointLow,point2Num);
		point3 = new ShapePoint(width,height,point3Num);
		point4 = new ShapePoint(pointLow,width,point4Num);
		point5 = new ShapePoint(pentagonX, pentagonY, point5Num);
		
	}

	/**
	 * Calling class creates a rectangle
	*/
	@Test
	public void squareTest() {
		points = new PriorityQueue<ShapePoint>();
		
		//set points with the first 4 ShapePoints to create a square
		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);
		
		shapeFactory = new SlideShapeFactory(points, width, height);
		
		Shape square = shapeFactory.getShape();
		
		assertTrue(square instanceof Polygon);
		
		
		Pane shapePane = new Pane();
		shapePane.getChildren().add(square);
		double squareHeight = shapePane.getHeight();
		assertEquals(squareHeight, height, 0.001);
	}

}
