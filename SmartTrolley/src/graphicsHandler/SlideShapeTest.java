/**
* SmartTrolley
*
* A DESCRIPTION OF THE FILE
*
* @author Name1
* @author Name2
*
* @author Checked By: Checker(s) fill here
*
* @version version of this file [Date Created: 1 May 2014]
*/

/*YOUR CODE HERE*/

/**************End of SlideShapeTest.java**************/
package graphicsHandler;

import static org.junit.Assert.*;

import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafxSpikes.SlideElementDuration.ShowTask;

import org.junit.Before;
import org.junit.Test;

/** 
 * Workspace_Name
 * 
 * A DESCRIPTION OF THE CLASS
 *
 * @author Matthew Wells
 * @author Alasdair Munday
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [version of this class] [Date Created: DD/MM/YY]
 */

public class SlideShapeTest {
	
	public PriorityQueue<ShapePoint> points;
	int width = 50, height = 50, pointLow = 0, pentagonX = 25, pentagonY = 25;
	int point1Num = 1, point2Num = 2, point3Num = 3, point4Num = 4, point5Num = 5;
	public ShapePoint point1, point2, point3, point4, point5, testingPoint;
	public SlidePolygon square; 
	public SlideEllipse circle;
	public String fillColor = "#0000FF", lineColor = "#FF0000";
	private int timer1Done, timer2Done;
	private boolean timerFirstRun;
	Timer timer1;
	int duration = 100;
	
	@Before
	public void setup(){
		pointsSetup();
		squareSetUp();
		square = new SlidePolygon(points, width, height);
		
	}
	
	/**
	*setup points for drawing polygons in SlideShapeFactoryTest
	*<p> Date Modified: 25 Apr 2014
	*/
	private void pointsSetup() {
		point1 = new ShapePoint(pointLow,pointLow,point1Num);
		point2 = new ShapePoint(width,pointLow,point2Num);
		point3 = new ShapePoint(width,height,point3Num);
		point4 = new ShapePoint(pointLow,width,point4Num);
		point5 = new ShapePoint(pentagonX, pentagonY, point5Num);
		
		//priority queue orders the points by point number
		points = new PriorityQueue<ShapePoint>();
	}

	
	/**
	*Setup Squares for testing in SlideShapeFactoryTest
	*<p> Date Modified: 25 Apr 2014
	*/
	private void squareSetUp() {
		//set points with the first 4 ShapePoints to create a square
		points.add(point1);
		points.add(point3);
		points.add(point2);
		points.add(point4);
		
	}
	
	
	
	/**
	*Tests that the points in the list passed to the factory
	*correspond to the points the factory will use.
	*
	*The factory should internaly convert ShapePoints to 2 element arrays of 
	*Doubles.
	*
	*<p> Date Modified: 25 Apr 2014
	*/
	@Test
	public void squarePointsTest()	{
		// double array to hold the x and y values contained in the shape points
		double[] testingPointDouble, squarePointDouble;
		
		int i = 1;
		
		while(!points.isEmpty())	{
			
			// populate testingPoint with the coordinates of the current point.
			testingPoint = points.remove();
			testingPointDouble = new double[]{testingPoint.getxCoordinate(), testingPoint.getyCoordinate()};
			
			squarePointDouble = new double[]{square.getPoints().get(i*2).doubleValue(),square.getPoints().get(i*2+1).doubleValue()};
			
			// test the double[] returned from the factory for current point
			assertEquals(testingPointDouble, squarePointDouble);
			
			// Move to the next point
			i++;
		}
	}
	
	/**
	*Tests that the Height (According to its self) of the polygon 
	*is equal to the expected height.
	*
	*<p> Date Modified: 25 Apr 2014
	*/
	@Test
	public void squareHeightTest()	{
		Bounds squareBounds = square.getBoundsInLocal();
		double squareHeight =squareBounds.getHeight();
		assertEquals(squareHeight, height, 0.0001);
	}
	

	
	/**
	*Test that setfill in SlideShape factory receives the 
	*PWS input and changes the color of it's shape accordingly.
	*<p> Date Modified: 25 Apr 2014
	*/
	@Test
	public void fillColorTest(){
		
		//Paint for holding returned colour from class under test
		Paint squareColor;
		
		square.setFillColor(fillColor);
		squareColor = square.getFillColor();
		
		//Confirm the square is the specified colour
		assertEquals(Color.BLUE, squareColor);
	}


	
	/**
	*Test that setLineColor in SlideShape factory receives the 
	*PWS input and changes the color of its shape's outline accordingly.
	*<p> Date Modified: 25 Apr 2014
	*/
	@Test
	public void lineColorTest(){
	
		//Paint for holding returned color from class under test
		Paint squareLineColor;
		
		square.setLineColor(lineColor);
		squareLineColor = square.getLineColor();
		
		assertEquals(Color.RED, squareLineColor);
	}
	
	
	
	/**
	*Test that setWidth and setHeight correctly change the width and height of
	*the space the shape occupies in its parent.
	*<p> Date Modified: 25 Apr 2014
	*/
	@Test
	public void polygonResizeTest(){
		
		int extra = 8;
		
		int newWidth = width + extra;
		int newHeight = height + extra;
		
		square.setWidth(newWidth);
		square.setHeight(newHeight);
		
		
		assertEquals(newWidth, square.getBoundsInParent().getWidth(), 0.001);
		assertEquals(newHeight, square.getBoundsInParent().getHeight(), 0.001);
	}
	
	@Test
	public void polygonDurationTest(){
		
		timer1Done = 0;
		timer2Done = 0;
		
		timerFirstRun = true;
		
		DurationTestTimerTask task;
		
		assert !square.isVisible();
		
		square.show();
		timer1.schedule(task, startTime);
		while(timer1Done != 1);
		
		assert square.isVisible();
		
		while(timer2Done != 1);
		
		assert !square.isVisible();
		
	}
	
	private class DurationTestTimerTask extends TimerTask{
		@Override
		public void run() {
				if (timerFirstRun){
					timer1Done=1;
					timerFirstRun = false;
					timer1.schedule( new DurationTestTimerTask(), duration);
				}else{
					timer2Done = 1;
				}
		}
		
	}
	
}
