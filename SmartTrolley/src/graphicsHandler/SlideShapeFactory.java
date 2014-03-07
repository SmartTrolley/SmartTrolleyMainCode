package graphicsHandler;

import java.util.PriorityQueue;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class SlideShapeFactory {

	// The shape that is currently being created by the factory
	Shape shape;
	double[] coordinates;

	public SlideShapeFactory(PriorityQueue<ShapePoint> points, int width,
			int height) {
		//process the priority queue of ShapePoints into a polygon readable double[]
		setCoordinates(points);
		
		if(coordinates.length == 2){
			shape = new Ellipse(coordinates[0],coordinates[1], width/2, height/2);
		}
		else{
		//create the shape with the new coordinates
		shape = new Polygon(coordinates);
		//scale to accord with width and height
		setWidth(width);
		}
	}

	private void setCoordinates(PriorityQueue<ShapePoint> points) {
		int i = 0;
		int coordinate;

		coordinates = new double[points.size() * 2];

		while (!points.isEmpty()) {

			ShapePoint currentPoint = points.remove();
			// get the x coordinate from the current point
			coordinate = currentPoint.getxCoordinate();
			coordinates[i] = coordinate;
			i++;

			// get the y coordinate from the current point
			coordinate = currentPoint.getyCoordinate();
			coordinates[i] = coordinate;
			i++;
		}
	}

	public Shape getShape() {
		return shape;
	}

	// Method to return the x and y coordinates of Point
	public double[] getPoint(int i) {
		double xCoordinate = coordinates[i * 2 - 1];
		double yCoordinate = coordinates[i * 2];
		return new double[] { xCoordinate, yCoordinate };
	}

	// Method to set the fill color of the shape
	public void setFillColor(String color) {
		shape.setFill(Color.web(color));
	}

	// Method to return the fill color of the shape
	public Paint getFillColor() {
		Paint color = shape.getFill();
		return color;
	}

	// Method to return the line color of the shape
	public Paint getLineColor() {
		Paint color = shape.getStroke();
		return color;
	}

	// Method to set the line color of the shape
	public void setLineColor(String lineColor) {
		shape.setStroke(Color.web(lineColor));
	}

	public void setWidth(int newWidth) {
		
		//if its a polygon..........................................
		// create local shape to access polygon parameters
		Shape shape = (Polygon)this.shape;
		
		double inherentWidth = shape.getBoundsInLocal().getWidth();
		
		//calculate the ratio of new width to inherent width
		double scaler = newWidth/inherentWidth;
		
		//scale by this ratio
		shape.setScaleX(scaler);
		
		//replace old shape
		this.shape = shape;
		
		//if its a circle...............................................
		
	}

	public void setHeight(int newHeight) {
		//if its a polygon..........................................
		// create local shape to access polygon parameters
		Shape shape = (Polygon)this.shape;
		
		double inherentHeight = shape.getBoundsInLocal().getHeight();
		
		//calculate the ratio of inherent Height to current Height
		double scaler = newHeight/inherentHeight;
		
		//scale by this ratio
		shape.setScaleY(scaler);
		
		//replace old shape
		this.shape = shape;
		
		//if its a circle...............................................
		
	}

}
