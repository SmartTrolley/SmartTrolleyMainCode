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
 * @version V2.0 [Date Created: 26 May 2014]
 */

package smarttrolleygui;

import imagehandler.SlideImage;

import java.util.ArrayList;
import texthandler.SlideText;
import videohandler.SlideVideo;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import Printing.SmartTrolleyPrint;
import audiohandler.AudioHandler;



public class Slide extends AnchorPane{
	

	private ArrayList<Shape> graphicsList;
	private ArrayList<SlideImage> imageList;
	private ArrayList<SlideVideo> videoList;
	private ArrayList<SlideText> textList; 
	private ArrayList<AudioHandler> audioList;
	private double xScaler;
	private double yScaler;
	
	/**
	*Constructor for product slide
	*<p>Displays product information
	*@param xScaler - scales the width of objects placed on the slide
	*@param yScaler - scales the height of objects placed on the slide
	*@param graphicsList - sorts a list of objects of type graphics, contains all relevant information on the graphic
	*@param imageList - sorts a list of objects of type image, contains all relevant information on the image
	*@param audioList - sorts a list of objects of type audio, contains all relevant information on the audio
	*@param textList - sorts a list of objects of type text, contains all relevant information on the text
	*@param videoList - sorts a list of objects of type video, contains all relevant information on the video
	*<p> Date Modified: 29 May 2014
	*/
	public Slide(double xScaler, double yScaler, ArrayList<Shape> graphicsList,
			ArrayList<SlideImage> imageList, ArrayList<AudioHandler> audioList, ArrayList<SlideText> textList,
			ArrayList<SlideVideo> videoList){
		
		this.xScaler = xScaler;
		this.yScaler = yScaler;
		
		this.graphicsList = graphicsList;
		
		for(Shape shape: graphicsList){
			addGraphics(shape);
		}
		
		this.audioList = audioList; //file path should have no spaces
		
		this.imageList = imageList;//file path should have no spaces
		
		for(SlideImage image: imageList){
			addImage(image);
		}
		
		this.textList = textList;
		
		for(SlideText text: textList){
			addText(text);
		}
		
		this.videoList = videoList;//file path should have no spaces
		
		for(SlideVideo video: videoList){
			addVideo(video);
		}
		
		setVisible(true);		
		show();
		
						
	}
	


	/**
	*sets up the video in a position depending on the scaling factors
	*@param video - is added to pane in this method
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
	*sets up the Text in a position depending on the scaling factors
	*@param text - is added to the pane in this method
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
	*sets up the graphics in a position depending on the scaling factors
	*@param shape - is added to the pane in this method
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
	*sets up the image in a position depending on the scaling factors
	*@param image - is added to the pane in this method
	*<p> Date Modified: 28 May 2014
	*/
	private void addImage(SlideImage image) {
		
		image.setScaleX(xScaler);
		image.setScaleY(yScaler);
		
		image.setLayoutX((xScaler*image.getLayoutX()));
		image.setLayoutY((yScaler*image.getLayoutY()));

		
		getChildren().add(image);
		
	}
	
	/**
	*sets all elements that have been added to a visible or show-able state
	*<p> Date Modified: 29 May 2014
	*/
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
		*clears all children from anchor pane and stops audio.
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
