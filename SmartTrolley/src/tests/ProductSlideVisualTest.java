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

import java.util.ArrayList;
import java.util.PriorityQueue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import smarttrolleygui.ProductSlide;
import texthandler.SlideTextBody;

public class ProductSlideVisualTest extends Application {
	
	private ProductSlide productSlide;

	
	/**sets parameters for image*/
	private String imageURL = "http://th03.deviantart.net/fs70/PRE/i/2013/077/8/9/cookie_monster_by_xenia_cat-d5yhjwj.jpg";
	private int xImageStart = 10;
	private int yImageStart = 10;
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
	int numOfStrings = 3;
	
	private String vidURL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
	private int xVidStart = 200;
	private int yVidStart = 20;
	private int VidWidth = 480;
	private int VidHeight = 240;
	private boolean vidLoop = true;
	private double vidStartTime = 0;
	private double vidDuration = 30.0;


	private int slideWidth = 500;
	private int slideHeight = 500;

	public static Stage stage;
		

	
	
	@Override
	public void start(Stage startStage) throws Exception {
		BorderPane border = new BorderPane();
		

	
		productSlide = new ProductSlide(slideWidth , slideHeight, points, numOfPoints, graphicsWidth, graphicsHeight, graphicsFillColour,
				graphicsLineColour, graphicsStartTime, graphicsDuration,
				imageURL, xImageStart,  yImageStart, imageWidth,
				imageHeight, imageStartTime, imageDuration, audURL, audStartTime,
				audDuration, audVolume, texts, font,
				fontColor, numOfStrings, fontSize, xTextStart, yTextStart,
				xTextEnd, yTextEnd, textStartTime, textDuration, vidURL,
				xVidStart, yVidStart, VidWidth,
				VidHeight, vidLoop,
				vidStartTime, vidDuration);
		
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
