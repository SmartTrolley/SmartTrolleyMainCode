/**
 * SmartTrolley
 *
 * This class is going to trial button functionality for the 
 * Slideshow
 *
 * @author Prashant Chakravarty
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.0 [Date Created: 22 May 2014]
 */

/*YOUR CODE HERE*/

/**************End of GUIControlSlideTrial.java**************/
package smarttrolleygui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Printing.SmartTrolleyPrint;

public class GUIControlSlideTrial extends Application {

	private final double MIN_WINDOW_WIDTH = 600.0;
	private final double MIN_WINDOW_HEIGHT = 600.0;
	public static Stage stage;

	/**
	 * The main() method is ignored in correctly deployed JavaFX applications.
	 * main() serves only as fallback in case the application cannot be launched
	 * through deployment artifacts, e.g., in IDEs with limited FX support.
	 * 
	 * @param args
	 *            the command line arguments
	 *            <p>
	 *            Date Modified: 22 May 2014
	 */
	public static void main(String[] args) {
		Application.launch(GUIControlSlideTrial.class,
				(java.lang.String[]) null);

	}

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			stage.setTitle("GUIControlSlideTrial");
			stage.getIcons().add(
					new Image("smarttrolleygui/img/windowIcon.jpg"));
			stage.setMinWidth(MIN_WINDOW_WIDTH);
			stage.setMinHeight(MIN_WINDOW_HEIGHT);

	//		Button prevSLideButton = new Button("<");

//			createSlideButton(prevSLideButton, 200, 200);

			primaryStage.show();
		} catch (Exception ex) {
			// TODO Show a message box to the user here
			SmartTrolleyPrint
					.print("Could not get FXML file for next scene. Application crashed ;-(");
			Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE,
					null, ex);
			System.exit(-1);
		}
	}

}
