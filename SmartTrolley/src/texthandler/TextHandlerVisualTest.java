/**
* SmartTrolley
*
* A DESCRIPTION OF THE FILE
*
* @author Name1
* @author Name2
*
* @author Checked By: Checker(s) fill here
*
* @version version of this file [Date Created: 26 May 2014]
*/

/*YOUR CODE HERE*/

/**************End of TextHandlerVisualTest.java**************/
package texthandler;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** 
 * Workspace_Name
 * 
 * A DESCRIPTION OF THE CLASS
 *
 * @author Matthew Wells
 * @author Alasdair Munday
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [version of this class] [Date Created: DD/MM/YY]
 */

public class TextHandlerVisualTest extends Application {

	private ArrayList<SlideTextBody> texts;
	private String font = "Verdana";
	private String fontColor = "#FF00FF";
	private int fontSize = 20;
	private int xStart = 30;
	private int xEnd = 500;
	private double startTime = 400;
	private double duration = 300;
	private int yStart = 100;
	private int yEnd = 200;
	
	public static void main (String[] args){
		launch(args);
	}
	

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage mainStage) throws Exception {
		
		SlideTextBody text1, text2, text3, text4;
		texts = new ArrayList<SlideTextBody>();
		text1 = new SlideTextBody("hello", true, false, false, 1);
		text2 = new SlideTextBody("World", false, true, false, 2);
		text3 = new SlideTextBody("How are you Today", false, true, true, 3);
		text4 = new SlideTextBody("food please", true, true,true,4);
		
		texts.add(text1);
		texts.add(text2);
		texts.add(text3);
		texts.add(text4);
		
		SlideText textBox = new SlideText(texts,font, fontColor, fontSize,xStart, yStart, xEnd, yEnd, startTime,duration);
		
		Scene scene = new Scene(textBox);
		
		mainStage.setScene(scene);
		
		mainStage.show();
		
	}

}
