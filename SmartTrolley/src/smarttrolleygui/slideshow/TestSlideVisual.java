/**
 * SmartTrolley
 *
 * This file contains all visual tests for Slide
 * when run displays a cookie monster jpeg, a graphic shape, some text, video
 * and runs audio.
 *
 * @author Alick Jacklin
 * @author Matthew Wells
 *
 * @author Checked By: Prashant Chakravarty [29 May 2014]
 *
 * @version V1.5 [Date Created: 26 May 2014]
 */
package smarttrolleygui.slideshow;

import graphicshandler.ShapePoint;
import graphicshandler.SlidePolygon;
import graphicshandler.SlideShapeFactory;
import imagehandler.SlideImage;

import java.util.ArrayList;
import java.util.PriorityQueue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import texthandler.SlideText;
import texthandler.SlideTextBody;
import videohandler.SlideVideo;
import audiohandler.AudioHandler;

public class TestSlideVisual extends Application {
	
	private Slide productSlide;

	/**sets parameters for image*/
	private String imageURL = "http://th03.deviantart.net/fs70/PRE/i/2013/077/8/9/cookie_monster_by_xenia_cat-d5yhjwj.jpg";
	private int xImageStart = 10;
	private int yImageStart = 10;
	private int xImageStart2 = 200;
	private int yImageStart2 = 300;
	private int imageWidth = 100;
	private int imageHeight = 150;
	private int imageStartTime = 0;
	private int imageDuration = 0;
	
	
	
	/**sets parameters for graphics*/
	private String graphicsFillColour = "#0000FF";
	private String graphicsLineColour = "#0000FF";
	private int graphicsWidth = 50;
	private int graphicsHeight = 75;
	int graphicsStartTime = 5;
	int graphicsDuration = 5;
	int numOfPoints = 1;
	private PriorityQueue<ShapePoint> points;
	private SlidePolygon pentagon;
	protected int point1Num = 1, point2Num = 2, point3Num = 3, point4Num = 4, point5Num = 5;
	protected int pointLow = 60, pentagonX = 25, pentagonY = 25;
	protected int maxPoints= 5;
	
	/**sets parameters for audio*/
	public AudioHandler audio;
	private String audURL = "Music/Kalimba.mp3";
	private String audURL2 = "Music/Shop2.mp3";
	private int audStartTime = 0;
	private int audDuration = 5;
	private double audVolume = 0.4;
	private double audVolume2 = 1.0;
	
	
	/**sets parameters for text*/
	ArrayList<SlideTextBody> texts;
	String font = "Comic Sans MS";
	String fontColor = "FF00FF";
	int fontSize = 12;
	int xTextStart = 355;
	int yTextStart = 467;
	int xTextEnd = 385;
	int yTextEnd = 487;
	double textStartTime = 0;
	double textDuration = 0;
	private String oneString = "A Long-Expected Party. When Mr. Bilbo Baggins of Bag End announced\n" + "that he would shortly be celebrating his eleventy-first birthday";
	
	/**sets Parameters for video*/
	private String vidURL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
	private int xVidStart = 200;
	private int yVidStart = 20;
	private int vidWidth = 480;
	private int vidHeight = 240;
	private boolean vidLoop = true;
	private double vidStartTime = 0;
	private double vidDuration = 5.0;

	/**sets scaler values*/
	private double xScaler =1;
	private double yScaler = 1;
	
	/**sets up a container for the panes*/
	public static Stage stage;	
	
	/**ArrayLists setup for storing information*/
	private ArrayList<Shape> graphicsList;
	private ArrayList<SlideImage> imageList;
	private ArrayList<SlideVideo> videoList;
	private ArrayList<SlideText> textList; 
	private ArrayList<AudioHandler> audioList;
	
	/**Duration of Slide*/
	private int slideDuration = 1;

	
	
		

	/**
	*sets up images to be stored in the imageList Array
	*<p> Date Modified: 29 May 2014
	*/
	public void setupImage(){
		SlideImage image = new SlideImage(imageURL, xImageStart, yImageStart, imageWidth, imageHeight,
				imageStartTime, 3,1,1);
		
		SlideImage image2 = new SlideImage(imageURL, xImageStart2, yImageStart2, imageWidth/2, imageHeight/2,
				imageStartTime, imageDuration,1,1);
	
		imageList = new ArrayList<SlideImage>();
		
		imageList.add(image);
		imageList.add(image2);
	}
	

	/**
	*sets up graphics to be stored in the graphicsList Array
	*<p> Date Modified: 29 May 2014
	*/
	public void setupGraphics(){
		points = new PriorityQueue<ShapePoint>();
		points.add(new ShapePoint(pointLow,pointLow,point1Num));
		points.add(new ShapePoint(graphicsWidth,pointLow,point2Num));
		points.add(new ShapePoint(graphicsWidth,graphicsHeight,point3Num));
		points.add(new ShapePoint(pointLow, graphicsWidth,point4Num));
		points.add(new ShapePoint(pentagonX, pentagonY, point5Num));
		
		SlideShapeFactory shapeFactory = new SlideShapeFactory(points, graphicsHeight,graphicsWidth,
				graphicsFillColour,
				graphicsLineColour, graphicsStartTime, graphicsDuration,1,1);
		pentagon = (SlidePolygon) shapeFactory.getShape();
		
		graphicsList = new ArrayList<Shape>();
		
		graphicsList.add(pentagon);
	}
	
	/**
	*sets up audio to be stored in the audioList Array
	*<p> Date Modified: 29 May 2014
	*/
	public void setupAudio(){
		AudioHandler audio = new AudioHandler(audURL, audStartTime, audDuration, audVolume);
		AudioHandler audio2 = new AudioHandler(audURL2, (int) vidDuration, audDuration, audVolume2);
		audioList = new ArrayList<AudioHandler>();
		audioList.add(audio);
		audioList.add(audio2);
	}
	
	/**
	*sets up video to be stored in the videoList Array
	*<p> Date Modified: 29 May 2014
	*/
	public void setupVideo(){
		SlideVideo video = new SlideVideo(vidURL, xVidStart, yVidStart, vidWidth, vidHeight, vidLoop,
				vidStartTime, vidDuration,1);
		

		videoList = new ArrayList<SlideVideo>();
		videoList.add(video);

	}
	
	/**
	*sets up text to be stored in the textList Array
	*<p> Date Modified: 29 May 2014
	*/
	public void setupText(){
		texts = new ArrayList<SlideTextBody>();
		texts.add(new SlideTextBody(oneString, true, true, true, 1,1));
		
		SlideText textBox = new SlideText(texts, font, fontColor, fontSize, xTextStart, yTextStart,
				xTextEnd, yTextEnd, textStartTime, textDuration,1);
		
		textList = new ArrayList<SlideText>();
		textList.add(textBox);
		
	}
	
	/**
	*sets up a BorderPane to hold the Slide, the media are then handed to the Slide and displayed.
	*calls setup methods for all media types.
	*<p> Date Modified: 29 May 2014
	*/	
	@Override
	public void start(Stage startStage) throws Exception {
		BorderPane border = new BorderPane();
		
		 setupImage();
		 setupGraphics();
		 setupAudio();
		 setupVideo();
		 setupText();		
	
		productSlide = new Slide(xScaler, yScaler, graphicsList, imageList, audioList, textList,
				videoList,slideDuration);
		
		border.setCenter(productSlide);
		
		 Scene scene = new Scene(border);
		 stage = startStage;
	        stage.setScene(scene);
	        stage.setTitle("Layout Sample");
	        stage.show();
		
	}	
	
	/**
	*launches a visual version of productSlide so that it can be visually tested
	*<p>all tests that can be seen
	*@param args
	*<p> Date Modified: 29 May 2014
	*/
	public static void main(String[] args) {
		launch(args);
	}
}
/************** End of TestSlideVisual.java **************/
