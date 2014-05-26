package smarttrolleygui;

import java.util.PriorityQueue;

import videohandler.VideoPlayerHandler;
import graphicshandler.ShapePoint;
import graphicshandler.SlideEllipse;
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
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Printing.SmartTrolleyPrint;


public class ProductSlide extends Application {
	
	private AnchorPane anchorPane; 

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
			
	/**
	 * @param args
	 * @return 
	 */
	public AnchorPane setupAnchorPane(PriorityQueue<ShapePoint> points, String imgURL, int xImgStart, int yImgStart, int imgWidth, int imgHeight, int imgStartTime, int imgDuration){
		anchorPane = new AnchorPane();
		
		anchorPane.getChildren().add(imageSetup(imgURL, xImgStart, yImgStart, imgWidth, imgHeight, imgStartTime, imgDuration));
		
		anchorPane.getChildren().add(graphicsSetup(points));
		
		return anchorPane;	
	}
	
	public SlideImage imageSetup(String imgURL, int xImgStart, int yImgStart, int imgWidth, int imgHeight, int imgStartTime, int imgDuration){
		
		imagehandler.SlideImage image1 = new imagehandler.SlideImage(imgURL, xImgStart, yImgStart, imgWidth, imgHeight, imgStartTime, imgDuration);
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

		public Shape graphicsSetup(PriorityQueue<ShapePoint> points) {
			
			point1 = new ShapePoint(pointLow,pointLow,point1Num);
			point2 = new ShapePoint(width,pointLow,point2Num);
			point3 = new ShapePoint(width,height,point3Num);
			point4 = new ShapePoint(pointLow,width,point4Num);
			
			points = new PriorityQueue<ShapePoint>();
			points.add(point1);
			points.add(point3);
			points.add(point2);
			points.add(point4);
			
		 Shape shape = new SlideShapeFactory(points , width, height, fillColour, lineColour, 0, 0).getShape();
		 shape.visibleProperty().set(true);
			
		return shape;
	}
		
		public void clearSlide(PriorityQueue<ShapePoint> points, String imgURL, int xImgStart, int yImgStart, int imgWidth, int imgHeight, int imgStartTime, int imgDuration){
			anchorPane.getChildren().removeAll(graphicsSetup(points), imageSetup(imgURL, xImgStart, yImgStart, imgWidth, imgHeight, imgStartTime, imgDuration));
		}
		
	public static void main(String[] args) {
		
		launch(args);
	}


	@Override
	public void start(Stage stage) throws Exception {
		BorderPane border = new BorderPane();
		
		border.setCenter(setupAnchorPane(points, imageURL, xImageStart,  yImageStart, imageWidth, imageHeight, imageStartTime, imageDuration));
		
		 Scene scene = new Scene(border);
	        stage.setScene(scene);
	        stage.setTitle("Layout Sample");
	        stage.show();
		
		
	}








}
