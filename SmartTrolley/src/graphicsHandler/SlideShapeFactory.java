package graphicsHandler;

import java.util.PriorityQueue;

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
		int i = 1;
		int coordinate;
		
		coordinates = new double[points.size() + 1];
		
		while(!points.isEmpty()){
			if(i%2 == 1){
				coordinate = points.remove().getyCoordinate();
			}
			else{
				coordinate = points.remove().getxCoordinate();
			}
			coordinates[i] = coordinate;
			i++;
		}
	}

	public Shape getShape() {
		return shape;
	}

	public double[] getPoint(int i) {
		double xCoordinate = coordinates[i*2-1];
		double yCoordinate = coordinates[i*2];
		return new double[]{xCoordinate, yCoordinate};
	}

}
