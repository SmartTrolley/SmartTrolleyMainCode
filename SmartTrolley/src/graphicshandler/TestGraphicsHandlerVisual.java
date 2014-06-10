package graphicshandler;

import java.util.PriorityQueue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/** 
* SmartTrolley
* 
* Visual test for graphics handler, displays a square, pentagon and circle after 
* a set delay, for a certain duration
*
* @author Matthew Wells
* @author Alasdair Munday
*
* @author [Checked By:] [Prashant Chakravarty - 06/06/14]
*
* @version [v1.0] [Date Created: 28/04/2014]
*/
public class TestGraphicsHandlerVisual extends Application {

	private PriorityQueue<ShapePoint> points;
	private int width = 50, height = 50, pointLow = 0, pentagonX = 25, pentagonY = 25;
	private int point1Num = 1, point2Num = 2, point3Num = 3, point4Num = 4, point5Num = 5;
	private SlideShapeFactory shapeFactory;
	private ShapePoint point1, point2, point3, point4, point5;
	private SlidePolygon square, pentagon;
	private SlideEllipse circle;
	private String blue = "#0000FF";
	private int startTime = 5;
	private int duration = 7;
	
	public static void main(String[] args) {
		launch(args);
	}
	

	/**
	 * Method to call setup methods for Points, Square, Pentagon, Circle, Video and Scene
	 * <p> Date Modified: 28 Apr 2014
	 */
	@Override
	public void start(Stage mainStage) throws Exception {
		
		setupPoints();
		setupSquare();
		setupPentagon();
		
		setupCircle();
		
		Scene scene = setupScene();
		
		mainStage.setScene(scene);
		
		mainStage.show();		
	}
	
	
	/**
	*Setup method for circle inputs points, width, height, fill colour, line colour,
	*start time and duration to SlideShapeFactory to generate an Ellipse	
	*<p> Date Modified: 28 April 2014	
	*/
	private void setupCircle() {
		
		points = new PriorityQueue<ShapePoint>();
		
		points.add(point3);
		
		shapeFactory = new SlideShapeFactory(points, 50, 50,blue,blue,startTime, duration,1,1);
		
		circle = (SlideEllipse) shapeFactory.getShape();
		
	}

	/**
	*Creates panes to hold each shape and adds each shape to its respective pane	
	*<p> Date Modified: 28 April 2014	
	*/
	private Scene setupScene() {
		
		//Create panes to hold the test shapes
		Pane squarePane = new Pane();
		Pane pentagonPane = new Pane();
		Pane circlePane = new Pane();
		VBox vBox = new VBox();
		
		//put the shapes into their panes.
		squarePane.getChildren().add(square);		
		pentagonPane.getChildren().add(pentagon);		
		circlePane.getChildren().add(circle);
		
		//Adds the shapes to a VBox
		vBox.getChildren().addAll(squarePane, pentagonPane, circlePane);
		
		Scene scene = new Scene(vBox);
		
		showShapes();
		return scene;
	}
	
	/**
	 * Calls the show() method for each shape
	*<p> Date Modified: 28 April 2014	
	*/
	private void showShapes(){
		square.show();
		pentagon.show();
		circle.show();
	}
	
	/**
	*Setup method for pentagon inputs points, width, height, fill colour, line colour,
	*start time, duration, to SlideShapeFactory to generate a pentagon
	*The stroke width and colour are also set	
	*<p> Date Modified: 28 April 2014	
	*/
	private void setupPentagon() {
		
		points = new PriorityQueue<ShapePoint>();
		points.add(point1);
		points.add(point3);
		points.add(point2);
		points.add(point4);
		points.add(point5);
		
		shapeFactory = new SlideShapeFactory(points, height,width,blue,blue,startTime, duration,1,1);
		pentagon = (SlidePolygon) shapeFactory.getShape();
		
		pentagon.setStroke(Color.AQUA);
		pentagon.setStrokeType(StrokeType.OUTSIDE);
		pentagon.setStrokeWidth(5);
	}

	/**
	*Setup method for square inputs points, width, height, fill colour, line colour,
	*start time and duration to SlideShapeFactory to generate a square	
	*<p> Date Modified: 28 April 2014
	*/
	private void setupSquare() {
		
		points = new PriorityQueue<ShapePoint>();
		points.add(point1);
		points.add(point3);
		points.add(point2);
		points.add(point4);
		
		//Instantiate a shapeFactory with the current values
		shapeFactory = new SlideShapeFactory(points, width, height,blue,blue,startTime, duration,1,1);
		
		shapeFactory.setHeight(100);
		//get the shape that is created by the values previously given to the factory
		square = (SlidePolygon) shapeFactory.getShape();
	}

	/**
	 * Sets up 5 ShapePoint objects for use in the visual test
	*<p> Date Modified: 28 April 2014
	*/
	private void setupPoints() {
		point1 = new ShapePoint(pointLow,pointLow,point1Num);
		point2 = new ShapePoint(width,pointLow,point2Num);
		point3 = new ShapePoint(width,height,point3Num);
		point4 = new ShapePoint(pointLow,width,point4Num);
		point5 = new ShapePoint(pentagonX, pentagonY, point5Num);
	}

}
