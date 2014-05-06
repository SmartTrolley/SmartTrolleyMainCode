package graphicshandler;

import java.util.PriorityQueue;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

/** 
* 
*
*
* @author Matthew Wells
* @author Alasdair Munday
*
* @author [Checked By:] [Checker(s) fill here]
*
* @version [v1.0] [Date Created: 28/04/2014]
*/

public class SlideShapeFactory {
	
	Shape shape;
	double[] coordinates;

	public SlideShapeFactory(PriorityQueue<ShapePoint> points, int width,
			int height, String fillColor, String lineColor, int startTime, int duration) {
		
		if(points.size() == 1){
			shape = new SlideEllipse(points.remove(), width, height);
		}
		else{
			//create the shape with the new coordinates
			shape = new SlidePolygon(points);
		}
		
		setWidth(width);
		setHeight(height);
		setFillColor(fillColor);
		setLineColor(lineColor);
		setDuration(duration);
		setStartTime(startTime);
	}

	/**
	* Sets the duration of slidePolygon and slideEllipse
	*@param duration
	*<p> Date Modified: 28 April 2014	
	*/
	public void setDuration(int duration) {
		
		if(SlidePolygon.class == shape.getClass()){
			((SlidePolygon) shape).setDuration(duration);
		}else{
			((SlideEllipse) shape).setDuration(duration);
		}
	}
	
	/**
	* Sets the start times of slidePolygon and slideEllipse
	*@param startTime
	*<p> Date Modified: 28 April 2014	
	*/
	public void setStartTime(int startTime) {
		
		if(SlidePolygon.class == shape.getClass()){
			((SlidePolygon) shape).setStartTime(startTime);
		}else{
			((SlideEllipse) shape).setStartTime(startTime);
		}
	}
	

	/**
	* Method returns shape 
	*<p> Date Modified: 28 April 2014
	*/
	public Shape getShape() {
		
		return shape;
	}

	/**
	* Method to return the x and y coordinates of Point
	*<p> Date Modified: 28 April 2014	
	*/
	public double[] getPoint(int i) {
		double xCoordinate = coordinates[i * 2 - 1];
		double yCoordinate = coordinates[i * 2];
		return new double[] { xCoordinate, yCoordinate };
	}

	/**
	* Method to set the fill color of the shape
	*<p> Date Modified: 28 April 2014	
	*/
	public void setFillColor(String color) {
		shape.setFill(Color.web(color));
	}
	
	/**
	* Method to return the fill color of the shape
	*<p> Date Modified: 28 April 2014	
	*/
	public Paint getFillColor() {
		Paint color = shape.getFill();
		return color;
	}
	
	/**
	* Method to return the line color of the shape
	*<p> Date Modified: 28 April 2014	
	*/
	public Paint getLineColor() {
		Paint color = shape.getStroke();
		return color;
	}
	
	/**
	*  Method to set the line color of the shape
	*<p> Date Modified: 28 April 2014	
	*/
	public void setLineColor(String lineColor) {
		shape.setStroke(Color.web(lineColor));
	}

	/**
	*Method sets the width of the shape
	*<p> Date Modified: 28 April 2014
	*/
	public void setWidth(int newWidth) {
		
		if(SlidePolygon.class == shape.getClass()){
			((SlidePolygon) shape).setWidth(newWidth);
		}else{
			((SlideEllipse) shape).setWidth(newWidth);
		}
	}

	/**
	*Method sets the height of the shape
	*<p> Date Modified: 28 April 2014
	*/
	public void setHeight(int newHeight) {
		
		if(SlidePolygon.class == shape.getClass()){
			((SlidePolygon) shape).setHeight(newHeight);
		}else{
			((SlideEllipse) shape).setHeight(newHeight);
		}
	}

}
