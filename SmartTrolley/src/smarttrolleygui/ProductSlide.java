/**
 * SmartTrolley
 *
 * This file sets up an anchor pane to display all pws specified media inputs on,
 * for slide show
 *
 * @author Alick Jacklin
 * @author Matthew Wells
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.2 [Date Created: 26 May 2014]
 */

package smarttrolleygui;

import graphicshandler.ShapePoint;
import graphicshandler.SlideShapeFactory;
import imagehandler.SlideImage;

import java.util.ArrayList;
import java.util.PriorityQueue;

import texthandler.SlideText;
import texthandler.SlideTextBody;
import videohandler.SlideVideo;

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

	private String oneString = "A Long-Expected Party. When Mr. Bilbo Baggins of Bag End announced\n" + "that he would shortly be celebrating his eleventy-first birthday";
	private String twoString = "He knew he would need the Smart Trolley app to purchase some party food!";
	private String threeString = "He then realised, Smart Trolley was shit, and used the\n Tesco App instead; stupid fat hobbit!!!";
	protected int maxStrings = 3;

	/**
	*Contructor method for product slide, adds all media handlers
	*<p>display slide
	*@param points - an array list of points for a graphical shape
	*@param numOfPoints - number of points desired for graphical shape, currently set between 1 and 5
	*@param graphicsWidth - sets the width of the graphical shape
	*@param graphicsHeight - sets the height of the graphical shape
	*@param graphicsFillColour - sets the fill colour, string must be in Hex
	*@param graphicsLineColour - sets the line colour, string must be in Hex
	*@param graphicsStartTime - sets the graphical start time at which point the graphic will appear in seconds
	*@param graphicsDuration - sets duration the graphic is displayed for in seconds
	*@param imgURL - gives an image url to the image handler
	*@param xImgStart - the starting point of the image in the x direction
	*@param yImgStart - the starting point of the image in the y direction
	*@param imgWidth - sets image width
	*@param imgHeight - sets image height
	*@param imgStartTime - sets initial display time of image after slide in seconds
	*@param imgDuration - sets how long the image will display for in seconds
	*@param audioURL - gives an audio URL to the audio handler
	*@param audioStartTime - gives a start time in seconds
	*@param audioDuration - gives a duration in seconds
	*@param audioVolume - sets volume between 0.0 and 1.0
	*@param texts - sets an array list of texts
	*@param font - sets font style, all MS fonts seem to work
	*@param fontColor - sets font colour, string must be in HEX
	*@param numOfStrings - sets the number of strings you wish to display
	*@param fontSize - sets font size
	*@param xTextStart - sets a start position for the text in the x direction
	*@param yTextStart - sets a start position for the text in the y direction
	*@param xTextEnd - sets an end location for the text in the x direction
	*@param yTextEnd - sets an end location for the text in the x direction
	*@param textStartTime - displays text at a set time after loading in seconds
	*@param textDuration - sets a duration the text is displayed in seconds
	*@param vidURL
	*@param xVidStart
	*@param yVidStart
	*@param VidWidth
	*@param VidHeight
	*@param vidLoop
	*@param vidStartTime
	*@param vidDuration
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 27 May 2014
	*/
	public ProductSlide(PriorityQueue<ShapePoint> points, int numOfPoints, int graphicsWidth,
			int graphicsHeight, String graphicsFillColour, String graphicsLineColour,
			int graphicsStartTime, int graphicsDuration,
			String imgURL, int xImgStart, int yImgStart,
			int imgWidth, int imgHeight, int imgStartTime, 
			int imgDuration, String audioURL,int audioStartTime, int audioDuration,
			double audioVolume, ArrayList<SlideTextBody> texts, String font,
			String fontColor,int numOfStrings, int fontSize, int xTextStart, int yTextStart,
			int xTextEnd, int yTextEnd, double textStartTime, double textDuration,
			String vidURL, int xVidStart, int yVidStart, int VidWidth,
			int VidHeight, boolean vidLoop,
			double vidStartTime, double vidDuration){
		
		getChildren().add(graphicsSetup(points, numOfPoints, graphicsWidth, graphicsHeight,
				graphicsFillColour, graphicsLineColour, graphicsStartTime,
				graphicsDuration));
		getChildren().add(imageSetup(imgURL, xImgStart, yImgStart, imgWidth,
				imgHeight, imgStartTime, imgDuration));
		getChildren().add(textSetup(texts, font,
				fontColor, numOfStrings, fontSize, xTextStart, yTextStart,
				xTextEnd, yTextEnd, textStartTime, textDuration));
		getChildren().add(videoSetup(vidURL, xVidStart,
				yVidStart, VidWidth, VidHeight,vidLoop, vidStartTime, vidDuration));
		audioSetup(audioURL, audioStartTime, audioDuration, audioVolume);
			
		setVisible(true);
					
	}
	

	/**
	*sets up an image in the image handler 
	*ready to be displayed in the anchorpane.
	*
	*@param imgURL - gives an image url to the image handler
	*@param xImgStart - the starting point of the image in the x direction
	*@param yImgStart - the starting point of the image in the y direction
	*@param imgWidth - sets image width
	*@param imgHeight - sets image height
	*@param imgStartTime - sets initial display time of image after slide in seconds
	*@param imgDuration - sets how long the image will display for in seconds
	*@return image1
	*
	*<p> Date Modified: 27 May 2014
	*/
	public SlideImage imageSetup(String imgURL, int xImgStart, int yImgStart, int imgWidth,
			int imgHeight, int imgStartTime, int imgDuration){
		
		SlideImage image1 = new SlideImage(imgURL, xImgStart,
				yImgStart, imgWidth, imgHeight, imgStartTime, imgDuration);
		image1.show();
		
		return image1;
		
	}
	
		public SlideVideo videoSetup(String vidURL, int xVidStart, 
				int yVidStart, int VidWidth,
				int VidHeight, boolean vidLoop,
				double vidStartTime, double vidDuration) {
		
			SlideVideo video1 = new SlideVideo(vidURL, xVidStart,
				yVidStart, VidWidth, VidHeight,vidLoop, vidStartTime, vidDuration);
			video1.setVisible(true);
			video1.show();
			
	return video1;
	}

		/**
		*Sends a graphic to the graphics handler, 
		*ready to be displayed on the anchorpane.
		*@param points - an array list of points for a graphical shape
		*@param numOfPoints - number of points desired for graphical shape, currently set between 1 and 5
		*@param graphicsWidth - sets the width of the graphical shape
		*@param graphicsHeight - sets the height of the graphical shape
		*@param graphicsFillColour - sets the fill colour, string must be in Hex
		*@param graphicsLineColour - sets the line colour, string must be in Hex
		*@param graphicsStartTime - sets the graphical start time at which point the graphic will appear in seconds
		*@param graphicsDuration - sets duration the graphic is displayed for in seconds
		*@return shape
		*<p> Date Modified: 27 May 2014
		*/
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
		
		/**
		*Sets up text in the texthandler, ready to be displayed
		*by the anchorpane.
		*
		*@param texts - sets an array list of texts
		*@param font - sets font style, all MS fonts seem to work
		*@param fontColor - sets font colour, string must be in HEX
		*@param numOfStrings - sets the number of strings you wish to display
		*@param fontSize - sets font size
		*@param xTextStart - sets a start position for the text in the x direction
		*@param yTextStart - sets a start position for the text in the y direction
		*@param xTextEnd - sets an end location for the text in the x direction
		*@param yTextEnd - sets an end location for the text in the x direction
		*@param textStartTime - displays text at a set time after loading in seconds
		*@param textDuration - sets a duration the text is displayed in seconds
		*@return text1
		*<p> Date Modified: 27 May 2014
		*/
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
		
		/**
		*sets audio, ready to be played
		*@param audioURL - gives an audio URL to the audio handler
		*@param audioStartTime - gives a start time in seconds
		*@param audioDuration - gives a duration in seconds
		*@param audioVolume - sets volume between 0.0 and 1.0
		*<p> Date Modified: 27 May 2014
		*/
		public void audioSetup(String audioURL, int audioStartTime, int audioDuration, double audioVolume) {
			
			audio = new AudioHandler(audioURL, audioStartTime, audioDuration, audioVolume);
			audio.begin();
	
		}
		
		/**
		*clears all children from anchor pane and stop audio.
		*<p> Date Modified: 27 May 2014
		*/
		public void clearSlide(){
				SmartTrolleyPrint.print(getChildren());	
				audio.stop();
				getChildren().clear();
		}

}

/************** End of ProductSlide.java **************/
