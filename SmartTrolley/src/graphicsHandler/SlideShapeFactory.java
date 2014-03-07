package graphicsHandler;

import java.util.PriorityQueue;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class SlideShapeFactory {

	//The shape that is currently being created by the factory
	Shape shape;
	double[] coordinates;

	public SlideShapeFactory(PriorityQueue<ShapePoint> points, int width,
			int height) {
		setCoordinates(points);
		shape = new Polygon(coordinates);
	}

	private void setCoordinates(PriorityQueue<ShapePoint> points) {
		int i = 0;
		int coordinate;
		
		coordinates = new double[points.size()*2];
		
		while(!points.isEmpty()){
			
			ShapePoint currentPoint = points.remove();
			//get the x coordinate from the current point
			coordinate = currentPoint.getxCoordinate();
			coordinates[i] = coordinate;
			i++;
			
			//get the y coordinate from the current point
			coordinate = currentPoint.getyCoordinate();
			coordinates[i] = coordinate;
			i++;
		}
	}

	public Shape getShape() {
		return shape;
	}

	//Method to return the x and y coordinates of Point
	public double[] getPoint(int i) {
		double xCoordinate = coordinates[i*2-1];
		double yCoordinate = coordinates[i*2];
		return new double[]{xCoordinate, yCoordinate};
	}

	public void setColor(String color) {
		shape.setFill(Color.web(color));
	}

	public Paint getColor() {
		Paint color = shape.getFill();
		return color;
	}

}
