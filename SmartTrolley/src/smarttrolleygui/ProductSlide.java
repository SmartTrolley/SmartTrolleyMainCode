package smarttrolleygui;

import java.util.PriorityQueue;

import audiohandler.AudioHandler;

import Printing.SmartTrolleyPrint;

import graphicshandler.ShapePoint;
import graphicshandler.SlideShapeFactory;
import imagehandler.SlideImage;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;


public class ProductSlide extends AnchorPane{
	

//	private Group root;
//	private String videoURL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
//	private int vidWidth = 200;
//	private int vidHeight = 120;
//	private int xVideoStart = 300;
//	private int yVideoStart = 200;
//	private int videoStartTime = 0;
//	private int videoDuration = 0;
	
	protected int point1Num = 1, point2Num = 2, point3Num = 3, point4Num = 4, point5Num = 5;
	protected int pointLow = 60, pentagonX = 25, pentagonY = 25;
	protected int maxPoints= 5;
	public AudioHandler audio;
			
	/**
	 * @param args
	 * @return 
	 * @return 
	 */
	public ProductSlide(PriorityQueue<ShapePoint> points, int numOfPoints, int graphicsWidth,
			int graphicsHeight, String graphicsFillColour, String graphicsLineColour,
			int graphicsStartTime, int graphicsDuration,
			String imgURL, int xImgStart, int yImgStart,
			int imgWidth, int imgHeight, int imgStartTime, 
			int imgDuration, String audioURL,int audioStartTime, int audioDuration,
			double audioVolume){
		
		getChildren().add(graphicsSetup(points, numOfPoints, graphicsWidth, graphicsHeight,
				graphicsFillColour, graphicsLineColour, graphicsStartTime,
				graphicsDuration));
		getChildren().add(imageSetup(imgURL, xImgStart, yImgStart, imgWidth,
				imgHeight, imgStartTime, imgDuration));
		audioSetup(audioURL, audioStartTime, audioDuration, audioVolume);
			
		setVisible(true);
					
	}
	
	public SlideImage imageSetup(String imgURL, int xImgStart, int yImgStart, int imgWidth,
			int imgHeight, int imgStartTime, int imgDuration){
		
		imagehandler.SlideImage image1 = new imagehandler.SlideImage(imgURL, xImgStart,
				yImgStart, imgWidth, imgHeight, imgStartTime, imgDuration);
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

		public Shape graphicsSetup(PriorityQueue<ShapePoint> points, int numOfPoints, int graphicsWidth, 
				int graphicsHeight, String graphicsFillColour, String graphicsLineColour,
				int graphicsStartTime, int graphicsDuration) {
//			
			
			
			points = new PriorityQueue<ShapePoint>();
			
			points.add(new ShapePoint(pointLow,pointLow,point1Num));
			points.add( new ShapePoint(graphicsWidth,graphicsHeight,point3Num));
			points.add(new ShapePoint(graphicsWidth,pointLow,point2Num));
			points.add(new ShapePoint(pointLow,graphicsWidth,point4Num));
			points.add( new ShapePoint(pentagonX, pentagonY, point5Num));
			
			for( int i=0; i< maxPoints - numOfPoints; i++){
				points.remove();
			}
			
		 Shape shape = new SlideShapeFactory(points , graphicsWidth, graphicsHeight, graphicsFillColour, 
				 graphicsLineColour, graphicsStartTime, graphicsDuration).getShape();
		 shape.visibleProperty().set(true);
			
		return shape;
	}
		
		
		
		public void clearSlide(){
				SmartTrolleyPrint.print(getChildren());	
				audio.stop();
				getChildren().clear();
		}

		public void audioSetup(String audioURL, int audioStartTime, int audioDuration, double audioVolume) {
			
			audio = new AudioHandler(audioURL, audioStartTime, audioDuration, audioVolume);
			audio.begin();
	
		}
		



}
