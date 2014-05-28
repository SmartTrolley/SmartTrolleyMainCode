/**
 * SmartTrolley
 *
 * This file sets up an anchor pane to display all pws specified media inputs on,
 * for slide show
 *
 * @author Alick Jacklin
 * @author Matthew Wells
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.2 [Date Created: 26 May 2014]
 */

package smarttrolleygui;

import graphicshandler.ShapePoint;
import graphicshandler.SlideShapeFactory;
import imagehandler.SlideImage;

import java.util.ArrayList;
import java.util.PriorityQueue;

import texthandler.SlideText;
import texthandler.SlideTextBody;
import videohandler.SlideVideo;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import Printing.SmartTrolleyPrint;
import audiohandler.AudioHandler;



public class ProductSlide extends AnchorPane{
	

	private ArrayList<Shape> graphicsList;
	private ArrayList<SlideImage> imageList;
	private ArrayList<SlideVideo> videoList;
	private ArrayList<SlideText> textList; 
	private ArrayList<AudioHandler> audioList;
	private double xScaler;
	private double yScaler;
	private double paneWidth;
	private double paneHeight;


	/**
	 * DESCRIPTION OF CONSTRUCTOR
	 *<p> Date Modified: 28 May 2014
	 */
	//duration should be double
	public ProductSlide( double xScaler, double yScaler, ArrayList<Shape> graphicsList,
			ArrayList<SlideImage> imageList, ArrayList<AudioHandler> audioList, ArrayList<SlideText> textList,
			ArrayList<SlideVideo> videoList){
		
//		this.paneWidth = paneWidth;
//		this.paneHeight = paneHeight;
		
//		setPrefHeight(paneHeight);
//		setPrefWidth(paneWidth);
		
		this.xScaler = xScaler;
		this.yScaler = yScaler;
		
		this.graphicsList = graphicsList;
		
		for(Shape shape: graphicsList){
			addGraphics(shape);
		}
		
		this.audioList = audioList;
		
		this.imageList = imageList;
		
		for(SlideImage image: imageList){
			addImage(image);
		}
		
		this.textList = textList;
		
		for(SlideText text: textList){
			addText(text);
		}
		
		this.videoList = videoList;
		
		for(SlideVideo video: videoList){
			addVideo(video);
		}
		
		show();
		
		setVisible(true);				
	}
	


	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param video
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 28 May 2014
	*/
	private void addVideo(SlideVideo video) {
		video.setScaleX(xScaler);
		video.setScaleY(yScaler);
		
		SmartTrolleyPrint.print("video xPosition is: " + video.getLayoutX());	
		video.setLayoutX((xScaler*video.getLayoutX()));
		SmartTrolleyPrint.print("video xpositon is now: " + video.getLayoutX());	
		video.setLayoutY(yScaler*video.getLayoutY());

		
		getChildren().add(video);
	}



	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param text
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 28 May 2014
	*/
	private void addText(SlideText text) {
		text.setScaleX(xScaler);
		text.setScaleY(yScaler);
		
		SmartTrolleyPrint.print(text.getLayoutX());		
		text.setLayoutX((xScaler*text.getLayoutX()));
		SmartTrolleyPrint.print(text.getLayoutX());
		SmartTrolleyPrint.print(text.getLayoutY());
		text.setLayoutY((yScaler*text.getLayoutY()));
		SmartTrolleyPrint.print(text.getLayoutY());
		
		getChildren().add(text);
		
	}


	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param shape
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 28 May 2014
	*/
	private void addGraphics(Shape shape) {
		shape.setScaleX(xScaler);
		shape.setScaleY(yScaler);
		
		shape.setLayoutX((xScaler*shape.getBoundsInParent().getMinX()));
		shape.setLayoutY((yScaler*shape.getBoundsInParent().getMinY()));
		
		getChildren().add(shape);
		
	}


	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param image
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 28 May 2014
	*/
	private void addImage(SlideImage image) {
		
		image.setScaleX(xScaler);
		image.setScaleY(yScaler);
		
		image.setLayoutX((xScaler*image.getLayoutX()));
		image.setLayoutY((yScaler*image.getLayoutY()));

		
		getChildren().add(image);
		
	}
	
	public void show(){
		for(SlideImage image: imageList){
			image.setVisible(true);
			image.show();
		}
		for(Shape shape: graphicsList){
			shape.setVisible(true);
		}
		for(SlideText text: textList){
			text.setVisible(true);
		}
		for(AudioHandler audio: audioList){
			audio.begin();
		}
		for(SlideVideo video: videoList){
			video.setVisible(true);
			video.show();
		}
		
		
		
	}
		
		/**
		*clears all children from anchor pane and stop audio.
		*<p> Date Modified: 27 May 2014
		*/
		public void clearSlide(){
				SmartTrolleyPrint.print(getChildren());	
				for(AudioHandler audio: audioList){
					audio.stop();
				}
				getChildren().clear();
		}

}

/************** End of ProductSlide.java **************/
