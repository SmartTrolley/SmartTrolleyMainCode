/**
 * SmartTrolley
 *
 * This file contains all visual tests for ProductSlide
 * when run displays a cookie monster jpeg, a graphic shape, some text
 * and run audio for 5 seconds.
 *
 * @author Alick Jacklin
 * @author Matthew Wells
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.3 [Date Created: 26 May 2014]
 */
package tests;

import graphicshandler.ShapePoint;
import graphicshandler.SlidePolygon;
import graphicshandler.SlideShapeFactory;

import imagehandler.SlideImage;

import java.util.ArrayList;
import java.util.PriorityQueue;

import audiohandler.AudioHandler;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import smarttrolleygui.ProductSlide;
import texthandler.SlideText;
import texthandler.SlideTextBody;
import videohandler.SlideVideo;

public class ProductSlideVisualTest extends Application {
	
	private ProductSlide productSlide;

	private int paneHeight = 900;
	private int paneWidth = 1200;
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
	public AudioHandler audio;
	
	/**sets parameters for audio*/
	private String audURL = "Music/Kalimba.mp3";
	private int audStartTime = 0;
	private int audDuration = 10;
	private double audVolume = 0.4;
	
	
	/**sets parameters for text*/
	ArrayList<SlideTextBody> texts;
	String font = "Comic Sans MS";
	String fontColor = "FF00FF";
	int fontSize = 12;
	int xTextStart = 355;
	int yTextStart = 467;
	int xTextEnd = 375;
	int yTextEnd = 487;
	double textStartTime = 0;
	double textDuration = 0;
	
	private String oneString = "A Long-Expected Party. When Mr. Bilbo Baggins of Bag End announced\n" + "that he would shortly be celebrating his eleventy-first birthday";
	private String twoString = "He knew he would need the Smart Trolley app to purchase some party food!";
	private String threeString = "He then realised, Smart Trolley was shit, and used the\n Tesco App instead; stupid fat hobbit!!!";
	
	
	
	/**sets Parameters for video*/
	private String vidURL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
	private int xVidStart = 200;
	private int yVidStart = 20;
	private int vidWidth = 480;
	private int vidHeight = 240;
	private boolean vidLoop = true;
	private double vidStartTime = 0;
	private double vidDuration = 30.0;


	private double xScaler =1.5;
	private double yScaler = 1;

	public static Stage stage;
	
	private ArrayList<Shape> graphicsList;
	private ArrayList<SlideImage> imageList;
	private ArrayList<SlideVideo> videoList;
	private ArrayList<SlideText> textList; 
	private ArrayList<AudioHandler> audioList;
	
	
		

	public void setupImage(){
		SlideImage image = new SlideImage(imageURL, xImageStart, yImageStart, imageWidth, imageHeight,
				imageStartTime, 3);
		
		SlideImage image2 = new SlideImage(imageURL, xImageStart2, yImageStart2, imageWidth/2, imageHeight/2,
				imageStartTime, imageDuration);
	
		imageList = new ArrayList<SlideImage>();
		
		imageList.add(image);
		imageList.add(image2);
	}
	
	public void setupGraphics(){
		points = new PriorityQueue<ShapePoint>();
		points.add(new ShapePoint(pointLow,pointLow,point1Num));
		points.add(new ShapePoint(graphicsWidth,pointLow,point2Num));
		points.add(new ShapePoint(graphicsWidth,graphicsHeight,point3Num));
		points.add(new ShapePoint(pointLow, graphicsWidth,point4Num));
		points.add(new ShapePoint(pentagonX, pentagonY, point5Num));
		
		SlideShapeFactory shapeFactory = new SlideShapeFactory(points, graphicsHeight,graphicsWidth,
				graphicsFillColour,
				graphicsLineColour, graphicsStartTime, graphicsDuration);
		pentagon = (SlidePolygon) shapeFactory.getShape();
		
		graphicsList = new ArrayList<Shape>();
		
		graphicsList.add(pentagon);
	}
	
	public void setupAudio(){
		AudioHandler audio = new AudioHandler(audURL, audStartTime, audDuration, audVolume);
		
		audioList = new ArrayList<AudioHandler>();
		audioList.add(audio);
	}
	
	public void setupVideo(){
		SlideVideo video = new SlideVideo(vidURL, xVidStart, yVidStart, vidWidth, vidHeight, vidLoop,
				vidStartTime, vidDuration);
		
		videoList = new ArrayList<SlideVideo>();
		videoList.add(video);
	}
	
	public void setupText(){
		texts = new ArrayList<SlideTextBody>();
		texts.add(new SlideTextBody(oneString, true, true, true, 1));
		
		SlideText textBox = new SlideText(texts, font, fontColor, fontSize, xTextStart, yTextStart,
				xTextEnd, yTextEnd, textStartTime, textDuration);
		
		textList = new ArrayList<SlideText>();
		textList.add(textBox);
		
	}
	
	@Override
	public void start(Stage startStage) throws Exception {
		BorderPane border = new BorderPane();
		
		 setupImage();
		 setupGraphics();
		 setupAudio();
		 setupVideo();
		 setupText();		
	
		productSlide = new ProductSlide(xScaler, yScaler, graphicsList, imageList, audioList, textList,
				videoList);
		
		border.setCenter(productSlide);
	
		
		 Scene scene = new Scene(border);
		 stage = startStage;
	        stage.setScene(scene);
	        stage.setTitle("Layout Sample");
	        stage.show();
		
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}
/************** End of ProductSlideVisualTest.java **************/
