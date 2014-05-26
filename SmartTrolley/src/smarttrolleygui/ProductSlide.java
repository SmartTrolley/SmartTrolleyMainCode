package smarttrolleygui;

import java.util.PriorityQueue;

import videohandler.VideoPlayerHandler;
import graphicshandler.ShapePoint;
import graphicshandler.SlideShapeFactory;
import imagehandler.SlideImage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Printing.SmartTrolleyPrint;


public class ProductSlide extends Application {

	private Group root;
	private String videoURL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
	private int vidWidth = 200;
	private int vidHeight = 120;
	private int xVideoStart = 300;
	private int yVideoStart = 200;
	private int videoStartTime = 0;
	private int videoDuration = 0;
	
	private String imageURL = "http://th03.deviantart.net/fs70/PRE/i/2013/077/8/9/cookie_monster_by_xenia_cat-d5yhjwj.jpg";
	private int xImageStart = 100;
	private int yImageStart = 100;
	private int imgWidth = 78;
	private int imgHeight = 128;
	
	private SlideShapeFactory slideShape;
	private String fillColour = "BLACK";
	private String lineColour = "BLUE";
	private int graphicsWidth = 50;
	private int graphicsHeight = 75;
			
	/**
	 * @param args
	 * @return 
	 */
	public AnchorPane setupAnchorPane(){
		AnchorPane anchorPane = new AnchorPane();
		
		anchorPane.getChildren().add(imageSetup());
		
		return anchorPane;	
	}
	
	public SlideImage imageSetup(){
		
		imagehandler.SlideImage image1 = new imagehandler.SlideImage(imageURL, xImageStart, yImageStart, imgWidth, imgHeight, 0, 0);
		image1.show();
		
		return image1;
		
	}
	
//		public Node videoSetup() {
//		
//			//VideoPlayerHandler video1 = new videohandler.VideoPlayerHandler(videoURL, xVideoStart, yVideoStart, vidWidth, vidHeight, true, 0, 0);
//			//Group root = new Group();
//			//root.getChildren().add(video1.mediaControl.overallBox);
//			
//		return null;
//	}

		public SlideShapeFactory graphicsSetup(PriorityQueue<ShapePoint> points) {
		
			slideShape = new SlideShapeFactory(null , graphicsWidth, graphicsHeight, fillColour, lineColour, 0, 0);
			
		return slideShape;
	}
		
	public static void main(String[] args) {
		
		launch(args);
	}


	@Override
	public void start(Stage stage) throws Exception {
		BorderPane border = new BorderPane();
		
		border.setCenter(setupAnchorPane());
		
		 Scene scene = new Scene(border);
	        stage.setScene(scene);
	        stage.setTitle("Layout Sample");
	        stage.show();
		
		
	}








}
