package graphicshandler;

import java.util.PriorityQueue;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

/** 
* SmartTrolley
* @author Matthew Wells
* @author Alasdair Munday
*
* @author [Checked By:] [Prashant Chakravarty]
*
* @version [v1.0] [Date Created: 28/04/2014]
*/
public class SlideShapeFactory {
	
	Shape shape;
	double[] coordinates;

	/**
	*Constructor that takes in the points  and various parameters required by the PWS to display the slide shape
	*@param points - a priority queue of the points
	*@param width - The width as an integer
	*@param height - The height as an integer
	*@param fillColor - The fillColor as a string
	*@param lineColor - The lineColor as a string
	*@param startTime - an int in seconds
	*@param duration - an int in seconds
	*<p> Date Modified: 7 Jun 2014
	*/
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
	*@param duration - int in seconds
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
	*@param startTime - an int in seconds
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
	* @return shape - The shape created by the factory
	*<p> Date Modified: 28 April 2014
	*/
	public Shape getShape() {
		
		return shape;
	}

	/**
	* Method to return the x and y coordinates of Point
	* @param i
	* @return an Array of double containing the x & y coordinates of the point
	*<p> Date Modified: 28 April 2014	
	*/
	public double[] getPoint(int i) {
		double xCoordinate = coordinates[i * 2 - 1];
		double yCoordinate = coordinates[i * 2];
		return new double[] { xCoordinate, yCoordinate };
	}

	/**
	* Method to set the fill color of the shape
	* @param color - a String
	*<p> Date Modified: 28 April 2014	
	*/
	public void setFillColor(String color) {
		shape.setFill(Color.web(color));
	}
	
	/**
	* Method to return the fill color of the shape
	* @return color - The FillColor as a Paint object
	*<p> Date Modified: 28 April 2014	
	*/
	public Paint getFillColor() {
		Paint color = shape.getFill();
		return color;
	}
	
	/**
	* Method to return the line color of the shape
	* @return color - The LineColor as a Paint object
	*<p> Date Modified: 28 April 2014	
	*/
	public Paint getLineColor() {
		Paint color = shape.getStroke();
		return color;
	}
	
	/**
	*  Method to set the line color of the shape
	*  @param lineColor - the lineColor as String
	*<p> Date Modified: 28 April 2014	
	*/
	public void setLineColor(String lineColor) {
		shape.setStroke(Color.web(lineColor));
	}

	/**
	*Method sets the width of the shape
	* @param newWidth - the width as an int
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
	*@param newHeight - the new height as an int
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
