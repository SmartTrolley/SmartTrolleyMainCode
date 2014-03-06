package graphicsHandler;

import java.util.PriorityQueue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class GraphicsHandlerVisualTest extends Application {

	public PriorityQueue<ShapePoint> points;
	int width = 50, height = 50, pointLow = 0, pentagonX = 25, pentagonY = 25;
	int point1Num = 1, point2Num = 2, point3Num = 3, point4Num = 4, point5Num = 5;
	public SlideShapeFactory shapeFactory;
	public ShapePoint point1, point2, point3, point4, point5, testingPoint;
	public Shape square, pentagon, circle;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage mainStage) throws Exception {
		//create a square using slideShapeFactory
		point1 = new ShapePoint(pointLow,pointLow,point1Num);
		point2 = new ShapePoint(width,pointLow,point2Num);
		point3 = new ShapePoint(width,height,point3Num);
		point4 = new ShapePoint(pointLow,width,point4Num);
		point5 = new ShapePoint(pentagonX, pentagonY, point5Num);
		
		points.add(point1);
		points.add(point3);
		points.add(point2);
		points.add(point4);
		
		//Instantiate a shapeFactory with the current values
		shapeFactory = new SlideShapeFactory(points, width, height);
		
		//get the shape that is created by the values previously given to the factory
		square = shapeFactory.getShape();
		
		Pane squarePane = new Pane();
		Pane pentagonPane = new Pane();
		Pane circlePane = new Pane();
		VBox vBox = new VBox();
		
		squarePane.getChildren().add(square);
		
		vBox.getChildren().add(squarePane);
		
		Scene scene = new Scene(vBox);
		
		mainStage.setScene(scene);
		
		
	}

}
