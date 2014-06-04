/**
 * SmartTrolley
 ** An instance of this object contains all the information required to render a particular slideshow.
*
* @author Prashant Chakravarty
*
* @author Checked By: Alasdair 29 May2014
*
* @version V1.0 [Date Created: 24 May 2014]
* 
 * This class also sets up an anchor pane to display all PWS specified media inputs on,
 * for slideshow show
 *
 * @author Alick Jacklin
 * @author Matthew Wells
 *
 * @author Checked By: Prashant Chakravarty [29 May 2014]
 *
 * @version V2.0 [Date Created: 26 May 2014]
 */

package smarttrolleygui.slideshow;

import imagehandler.SlideImage;

import java.util.ArrayList;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import texthandler.SlideText;
import toolBox.SmartTrolleyToolBox;
import videohandler.SlideVideo;
import audiohandler.AudioHandler;

public class Slide extends AnchorPane {

	private ArrayList<Shape> graphicsList;
	private ArrayList<SlideImage> imageList;
	private ArrayList<SlideVideo> videoList;
	private ArrayList<SlideText> textList;
	private ArrayList<AudioHandler> audioList;
	private double xScaler;
	private double yScaler;
	protected double duration;
	private int slideID;

	/**
	*Constructor takes in lists, and places objects from those lists into the Slide, based on self defined locations. Also takes in scaling factors
	*and scales objects and their locations based on them.
	*<p>Displays product information
	*@param xScaler - scales the width of objects placed on the slideshow
	*@param yScaler - scales the height of objects placed on the slideshow
	*@param graphicsList - sorts a list of objects of type graphics, contains all relevant information on the graphic
	*@param imageList - sorts a list of objects of type image, contains all relevant information on the image
	*@param audioList - sorts a list of objects of type audio, contains all relevant information on the audio
	*@param textList - sorts a list of objects of type text, contains all relevant information on the text
	*@param videoList - sorts a list of objects of type video, contains all relevant information on the video
	*@param seconds - Duration in seconds
	*<p> Date Modified: 29 May 2014 
	*/
	public Slide(double xScaler, double yScaler, ArrayList<Shape> graphicsList, ArrayList<SlideImage> imageList, ArrayList<AudioHandler> audioList, ArrayList<SlideText> textList,
			ArrayList<SlideVideo> videoList, double seconds) {

		this.duration = seconds;
		this.xScaler = xScaler;
		this.yScaler = yScaler;

		// TODO Test if graphics is null here
		/*
		 * if (graphicsList == null){
		 * SmartTrolleyToolBox.print("Graphics list is null for this slideshow.")
		 */
		this.graphicsList = graphicsList;

		for (Shape shape : graphicsList) {
			addGraphics(shape);
		}

		// TODO Test if audio is null here
		/*
		 * if (audioList == null){
		 * SmartTrolleyToolBox.print("Audio list is null for this slideshow.")
		 */
		this.audioList = audioList; // file path should have no spaces

		// TODO Test if image is null here
		/*
		 * if (imageList == null){
		 * SmartTrolleyToolBox.print("Image list is null for this slideshow.")
		 */
		this.imageList = imageList;// file path should have no spaces

		for (SlideImage image : imageList) {
			addImage(image);
		}

		// TODO Test if text is null here
		/*
		 * if (textList == null){
		 * SmartTrolleyToolBox.print("Text list is null for this slideshow.")
		 */
		this.textList = textList;

		for (SlideText text : textList) {
			addText(text);
		}

		// TODO Test if video is null here
		/*
		 * if (videoList == null){
		 * SmartTrolleyToolBox.print("Video list is null for this slideshow.")
		 */
		this.videoList = videoList;// file path should have no spaces

		for (SlideVideo video : videoList) {
			addVideo(video);
		}

	}

	/**
	*adds video to the Slide in a position depending on the scaling factors
	*@param video - is added to pane in this method
	*<p> Date Modified: 28 May 2014
	*/
	private void addVideo(SlideVideo video) {
		video.setScaleX(xScaler);
		video.setScaleY(yScaler);

		SmartTrolleyToolBox.print("video xPosition is: " + video.getLayoutX());
		video.setLayoutX((xScaler * video.getLayoutX()));
		SmartTrolleyToolBox.print("video xpositon is now: " + video.getLayoutX());
		video.setLayoutY(yScaler * video.getLayoutY());

		getChildren().add(video);
	}

	/**
	*adds text to the Slide in a position depending on the scaling factors
	*@param text - is added to the pane in this method
	*<p> Date Modified: 28 May 2014
	*/
	private void addText(SlideText text) {
		text.setScaleX(xScaler);
		text.setScaleY(yScaler);

		SmartTrolleyToolBox.print(text.getLayoutX());
		text.setLayoutX((xScaler * text.getLayoutX()));
		SmartTrolleyToolBox.print(text.getLayoutX());
		SmartTrolleyToolBox.print(text.getLayoutY());
		text.setLayoutY((yScaler * text.getLayoutY()));
		SmartTrolleyToolBox.print(text.getLayoutY());

		getChildren().add(text);

	}

	/**
	*adds graphics to the Slide in a position depending on the scaling factors
	*@param shape - is added to the pane in this method
	*<p> Date Modified: 28 May 2014
	*/
	private void addGraphics(Shape shape) {
		shape.setScaleX(xScaler);
		shape.setScaleY(yScaler);

		shape.setLayoutX((xScaler * shape.getBoundsInParent().getMinX()));
		shape.setLayoutY((yScaler * shape.getBoundsInParent().getMinY()));

		getChildren().add(shape);

	}

	/**
	*Adds images to the Slide in a position depending on the scaling factors
	*@param image - is added to the pane in this method
	*<p> Date Modified: 28 May 2014
	*/
	private void addImage(SlideImage image) {

		image.setScaleX(xScaler);
		image.setScaleY(yScaler);

		image.setLayoutX((xScaler * image.getLayoutX()));
		image.setLayoutY((yScaler * image.getLayoutY()));

		getChildren().add(image);

	}

	/**
	*sets all elements which have been added to the slideshow, to a visible or show-able state, and plays audio.
	*<p> Date Modified: 29 May 2014
	*/
	public void show() {
		for (SlideImage image : imageList) {
			image.setVisible(true);
			image.show();
		}
		for (Shape shape : graphicsList) {
			shape.setVisible(true);
		}
		for (SlideText text : textList) {
			text.setVisible(true);
		}
		for (AudioHandler audio : audioList) {
			audio.begin();
		}
		for (SlideVideo video : videoList) {
			video.setVisible(true);
			video.show();
		}
		
		setVisible(true);

	}

	/**
	*clears all children from slideshow and stops audio.
	*<p> Date Modified: 27 May 2014
	*/
	public void clearSlide() {
		SmartTrolleyToolBox.print(getChildren());
		 for(AudioHandler audio: audioList){
		 audio.stop();
		 }

		getChildren().clear();
	}

	/**
	 * @return the slideID
	 */
	public int getSlideID() {
		return slideID;
	}

	/**
	 * @param slideID the slideID to set
	 */
	public void setSlideID(int slideID) {
		this.slideID = slideID;
	}

}

/************** End of Slide.java **************/
