/** 
 * StartScreenController
 * 
 * Class Description: 
 * StartScreenController allows java interaction with StartScreen.fxml
 *
 * @author Arne
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.0] [Date Created: 06/03/14]
 */

package smarttrolleygui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class StartScreenController extends ControllerGeneral implements Initializable {

	private SmartTrolleyGUI application;
	// createNewListButton is only made public so that it can be accessed by automated tests
	@FXML
	public static Button createNewListButton;

	@FXML
	public static Button viewAllShoppingListsButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	/**
	*setApp
	*@param application
	*<p> Date Modified: 28 Feb 2014
	*/
	public void setApp(SmartTrolleyGUI application) {
		this.application = application;
	}

	/**
	*loadCreateNewListScreen is called when the 'Create a new shopping list' button is pressed.
	*It calls the goToCreateNewListScreen method in SmartTrolleyGUI.java
	*<p> User can create a new shopping list
	*@param event - response to click on 'Create a new shopping list' button
	*<p> Date Modified: 03 May 2014
	*/
	public void loadCreateNewListScreen(ActionEvent event) {

		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			System.out.println("error: application == null");
		} else {
			loadScreen(Screen.CREATENEWLISTSCREEN, application);
		}
	}

	/**
	*
	*parsePWS is called when the Parse a PWS XML File button is pressed
	*TODO No user story exists for XML Parser, create this.
	*<p>Test(s)/User Story that it satisfies
	*@param event
	*<p> Date Modified: 24 May 2014
	*/
	public void parsePWS(ActionEvent event) {
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			System.out.println("error: application == null");
		} else {
			// TODO Replace with appropriate method
			PWSParser PWSParser = new PWSParser();
			PWSParser.parsePWS();
		}
	}

	/**
	 * loadAllShoppingListsScreen is called when the 'view your shopping lists'
	 * button is pressed. It calls the goToAllShoppingListsScreen method in
	 * SmartTrolleyGUI.java
	 * <p> User can view previously created shopping lists
	 * 
	 * @param event - response to click on 'view your shopping lists' button
	 * <p> Date Modified: 06 Mar 2014
	 */
	public void loadAllShoppingListsScreen(ActionEvent event) {
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			System.out.println("error: application == null");
		} else {
			loadScreen(Screen.ALLSHOPPINGLISTSSCREEN, application);
		}
	}

}
/************** End of StartScreenController **************/
