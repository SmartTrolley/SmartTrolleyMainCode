package smarttrolleygui.slideshow;

import graphicshandler.Branchable;
import imagehandler.SlideImage;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import texthandler.SlideText;
import texthandler.SlideTextBody;
import toolBox.SmartTrolleyToolBox;
import videohandler.SlideVideo;
import audiohandler.AudioHandler;
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
 * @author Matthew Wells(V2.0)
 * 
 * @author Alasadair Munday (V3.0)
 *
 * @author Checked By: Prashant Chakravarty [29 May 2014]
 *
 * @version V3.0 [Date Created: 26 May 2014]
 */
public class Slide extends AnchorPane {

	private ArrayList<Shape> graphicsList;
	private ArrayList<SlideImage> imageList;
	private ArrayList<SlideVideo> videoList;
	private ArrayList<SlideText> textList;
	private ArrayList<AudioHandler> audioList;
	private ArrayList<Pane> layers = new ArrayList<Pane>();

	/**Ratio of our slide width to the slideshow's XML slide width.*/
	private double xScaler;

	/**Ratio of our slide width to the slideshow's XML slide width.*/
	private double yScaler;

	/**Duration of slide.*/
	protected double duration;

	/**Unique identifier of the slide in the slideshow.*/
	private int slideID;
	private SlideShow slideShow;

	/**
	 *Constructor takes in lists, and places objects from those lists into the Slide, based on self defined locations. Also takes in scaling factors
	 *and scales objects and their locations based on them.
	 *<p>Displays product information
	 *@param xScaler - scales the width and x-position of objects placed on the slideshow
	 *@param yScaler - scales the height and y-position  of objects placed on the slideshow
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

		if (graphicsList == null) {
			SmartTrolleyToolBox.print("Graphics list is null for this slideshow.");
		} else {
			this.graphicsList = graphicsList;

			for (Shape shape : graphicsList) {
				addGraphics(shape);
			}
		}

		this.audioList = audioList; // file path should have no spaces

		this.imageList = imageList;// file path should have no spaces

		if (imageList == null) {
			SmartTrolleyToolBox.print("Image list is null for this slideshow.");
		} else {
			for (SlideImage image : imageList) {
				addImage(image);
			}
		}

		this.textList = textList;

		if (textList == null) {
			SmartTrolleyToolBox.print("Text list is null for this slideshow.");
		} else {
			for (SlideText text : textList) {
				addText(text);
			}
		}

		this.videoList = videoList;// file path should have no spaces

		if (videoList == null) {
			SmartTrolleyToolBox.print("Video list is null for this slideshow.");
		} else {
			for (SlideVideo video : videoList) {
				addVideo(video);
			}
		}

		getChildren().addAll(layers);

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

		addToLayer(video, video.getLayer());

	}

	private void addToLayer(Node node, int layer) {
		try{
			layers.get(layer);
		}catch(IndexOutOfBoundsException e){
			for(int i = layers.size(); i<layer+1; i++){
				layers.add(new AnchorPane());
			}
		}

		layers.get(layer).getChildren().add(node);
	}

	/**
	 *adds text to the Slide in a position depending on the scaling factors
	 *@param text - is added to the pane in this method
	 *<p> Date Modified: 28 May 2014
	 */
	private void addText(SlideText text) {

		SmartTrolleyToolBox.print("Original x-coord for text was: " + text.getXStart());
		SmartTrolleyToolBox.print("Original y-coord for text was: " + text.getYStart());

		text.relocateText(text.getXStart() * xScaler, text.getYStart() * yScaler);

		addToLayer(text, text.getLayer());

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

		addToLayer(shape,((Layerable)shape).getLayer());

	}

	/**
	 *Adds images to the Slide in a position depending on the scaling factors
	 *@param image - is added to the pane in this method
	 *<p> Date Modified: 28 May 2014
	 */
	private void addImage(SlideImage image) {

		image.setScaleX(xScaler);
		image.setScaleY(yScaler);

		image.setLayoutX((xScaler * image.getx()));
		image.setLayoutY((yScaler * image.gety()));

		addToLayer(image, image.getLayer());

	}

	/**
	 *sets all elements which have been added to the slideshow, to a visible or show-able state, and plays audio.
	 *<p> Date Modified: 29 May 2014
	 */
	public void show() {
		if (imageList != null) {
			for (SlideImage image : imageList) {
				image.setVisible(true);
				image.show();
				final SlideImage finalImage = image;
					//setup action Listener for branching
					image.setOnMouseClicked( new EventHandler<MouseEvent>(){
						@Override
						public void handle(MouseEvent arg0) {
							slideShow.deleteSlidePane();
							slideShow.displaySlide(finalImage.getBranch());
						}
					});
			}
		}


		if (graphicsList != null) {
			for (final Shape shape : graphicsList) {
				shape.setVisible(true);

					//Setup action listener for branching
					shape.setOnMouseClicked( new EventHandler<MouseEvent>(){
						@Override
						public void handle(MouseEvent arg0) {
							slideShow.deleteSlidePane();
							slideShow.displaySlide(((Branchable)shape).getBranch());
						}
					});

			}
		}

		if (textList != null) {
			for (SlideText text : textList) {
				text.setVisible(true);
				
				// setup action listeners for branching
				for(Node bodyNode : text.getChildren()){
					final SlideTextBody body = (SlideTextBody)bodyNode;
					body.setOnMouseClicked( new EventHandler<MouseEvent>(){
						@Override
						public void handle(MouseEvent arg0) {
							slideShow.deleteSlidePane();
							slideShow.displaySlide(body.getBranch());
						}
					});
				}
			}
		}

		if (audioList != null) {
			for (AudioHandler audio : audioList) {
				audio.begin();
			}
		}

		if (videoList != null) {
			for (SlideVideo video : videoList) {
				video.setVisible(true);
				video.show();
			}
		}

		setVisible(true);

	}

	/**
	 *clears all children from slideshow and stops audio.
	 *<p> Date Modified: 27 May 2014
	 */
	public void stop() {
		SmartTrolleyToolBox.print(getChildren());
		for (AudioHandler audio : audioList) {
			
//				audio.stop();
			
//			This is commented out due to the error in the AudioHandler which does not recognise the paths provided. 
//			Audio never plays and therefore errors if tried to stop
//			The Audio Handler was written by an external company and will be fixed by them
			
		}
		for (SlideVideo video : videoList){
			
				video.stop();
		}
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

	public void setSlideShow(SlideShow slideShow) {
		this.slideShow = slideShow;

	}

}

/************** End of Slide.java **************/
