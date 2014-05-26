package smarttrolleygui;

import java.util.PriorityQueue;

import Printing.SmartTrolleyPrint;

import graphicshandler.ShapePoint;
import graphicshandler.SlideShapeFactory;
import imagehandler.SlideImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;


public class ProductSlide extends AnchorPane {
	
	private ProductSlide anchorPane;

//	private Group root;
//	private String videoURL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
//	private int vidWidth = 200;
//	private int vidHeight = 120;
//	private int xVideoStart = 300;
//	private int yVideoStart = 200;
//	private int videoStartTime = 0;
//	private int videoDuration = 0;
	
	private String fillColour = "#0000FF";
	private String lineColour = "#0000FF";
	public ShapePoint point1, point2, point3, point4;
	int point1Num = 1, point2Num = 2, point3Num = 3, point4Num = 4;
	int width = 50, height = 50, pointLow = 0, pentagonX = 25, pentagonY = 25;
			
	/**
	 * @param args
	 * @return 
	 * @return 
	 */
	public void setupAnchorPane(PriorityQueue<ShapePoint> points, String imgURL, int xImgStart, int yImgStart, int imgWidth, int imgHeight, int imgStartTime, int imgDuration){
		
		anchorPaneStart().getChildren().addAll(graphicsSetup(points), imageSetup(imgURL, xImgStart, yImgStart, imgWidth, imgHeight, imgStartTime, imgDuration));		
		
	}
	
	public ProductSlide anchorPaneStart(){
		anchorPane = new ProductSlide();
		anchorPane.setVisible(true);
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
		
		public void clearSlide(){
				SmartTrolleyPrint.print(anchorPane.getChildren());		
		}
		



}
