package tests;

import graphicshandler.ShapePoint;

import java.util.PriorityQueue;

import smarttrolleygui.ProductSlide;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class TestLauncherForSlide extends Application {

	private String imageURL = "http://th03.deviantart.net/fs70/PRE/i/2013/077/8/9/cookie_monster_by_xenia_cat-d5yhjwj.jpg";
	private int xImageStart = 100;
	private int yImageStart = 100;
	private int imageWidth = 78;
	private int imageHeight = 128;
	private int imageStartTime = 0;
	private int imageDuration = 0;
	
	private Shape slideShape;
	private String fillColour = "#0000FF";
	private String lineColour = "#0000FF";
	private int graphicsWidth = 50;
	private int graphicsHeight = 75;
	
	private PriorityQueue<ShapePoint> points;
	public ShapePoint point1, point2, point3, point4;
	int point1Num = 1, point2Num = 2, point3Num = 3, point4Num = 4;
	int width = 50, height = 50, pointLow = 0, pentagonX = 25, pentagonY = 25;
	
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane border = new BorderPane();
		
		
		//border.setCenter(productSlide.setupAnchorPane(points, imageURL, xImageStart,  yImageStart, imageWidth, imageHeight, imageStartTime, imageDuration));
		
		 Scene scene = new Scene(border);
	        stage.setScene(scene);
	        stage.setTitle("Layout Sample");
	        stage.show();
		
	}	

}
