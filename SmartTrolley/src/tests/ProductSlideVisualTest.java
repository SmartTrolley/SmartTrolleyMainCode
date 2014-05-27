package tests;

import graphicshandler.ShapePoint;

import java.util.PriorityQueue;

import smarttrolleygui.ProductSlide;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProductSlideVisualTest extends Application {
	
	private ProductSlide productSlide;

	private String imageURL = "http://th03.deviantart.net/fs70/PRE/i/2013/077/8/9/cookie_monster_by_xenia_cat-d5yhjwj.jpg";
	private int xImageStart = 100;
	private int yImageStart = 100;
	private int imageWidth = 78;
	private int imageHeight = 128;
	private int imageStartTime = 0;
	private int imageDuration = 0;
	
	private String graphicsFillColour = "#0000FF";
	private String graphicsLineColour = "#0000FF";
	private int graphicsWidth = 50;
	private int graphicsHeight = 75;
	int graphicsStartTime = 5;
	int graphicsDuration = 5;
	int numOfPoints = 4;
	
	private PriorityQueue<ShapePoint> points;
	
	private String audURL = "Music/Kalimba.mp3";
	private int audStartTime = 0;
	private int audDuration = 100;
	private double audVolume = 0.4;
		

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane border = new BorderPane();
		
	
		productSlide = new ProductSlide(points, numOfPoints, graphicsWidth, graphicsHeight, graphicsFillColour,
				graphicsLineColour, graphicsStartTime, graphicsDuration,
				imageURL, xImageStart,  yImageStart, imageWidth,
				imageHeight, imageStartTime, imageDuration, audURL, audStartTime,
				audDuration, audVolume);
		
		border.setCenter(productSlide);
	
		
		 Scene scene = new Scene(border);
	        stage.setScene(scene);
	        stage.setTitle("Layout Sample");
	        stage.show();
		
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}
