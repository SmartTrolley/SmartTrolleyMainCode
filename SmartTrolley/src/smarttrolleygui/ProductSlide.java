package smarttrolleygui;

import graphicshandler.ShapePoint;
import graphicshandler.SlideShapeFactory;
import imagehandler.SlideImage;

import java.util.ArrayList;
import java.util.PriorityQueue;

import texthandler.SlideText;
import texthandler.SlideTextBody;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import Printing.SmartTrolleyPrint;
import audiohandler.AudioHandler;


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

	private String oneString = "A Long-Expected Party. When Mr. Bilbo Baggins of Bag End announced\n" + "that he would shortly be celebrating his eleventy-first ";
	private String twoString = "He knew he would need the Smart Trolley app to purchase some party food!";
	private String threeString = "He then realised, Smart Trolley was shit, and used the\n Tesco App instead; stupid fat hobbit!!!";
	protected int maxStrings = 3;
			
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
			double audioVolume, ArrayList<SlideTextBody> texts, String font,
			String fontColor,int numOfStrings, int fontSize, int xTextStart, int yTextStart,
			int xTextEnd, int yTextEnd, double textStartTime, double textDuration){
		
		getChildren().add(graphicsSetup(points, numOfPoints, graphicsWidth, graphicsHeight,
				graphicsFillColour, graphicsLineColour, graphicsStartTime,
				graphicsDuration));
		getChildren().add(imageSetup(imgURL, xImgStart, yImgStart, imgWidth,
				imgHeight, imgStartTime, imgDuration));
		getChildren().add(textSetup(texts, font,
				fontColor, numOfStrings, fontSize, xTextStart, yTextStart,
				xTextEnd, yTextEnd, textStartTime, textDuration));
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
		
		public SlideText textSetup(ArrayList<SlideTextBody> texts, String font,
				String fontColor, int numOfStrings, int fontSize, int xTextStart, int yTextStart,
				int xTextEnd, int yTextEnd, double textStartTime, double textDuration){
			
			texts = new ArrayList<SlideTextBody>();
			
			texts.add(new SlideTextBody(oneString , true, true, true, 1));
			texts.add(new SlideTextBody(twoString , false, false, true, 2));
			texts.add(new SlideTextBody(threeString , false, false, false, 3));
			
			for( int i=0; i< maxStrings - numOfStrings; i++){
				texts.remove(i);
			}
		
			texthandler.SlideText text1 = new texthandler.SlideText(texts, font, fontColor, fontSize, xTextStart, yTextStart,
					xTextEnd, yTextEnd, textStartTime, textDuration);
			
			
			text1.setVisible(true);
			
			return text1;
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
