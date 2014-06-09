/** 
 * CreateNewListScreenController
 * 
 * Class Description: 
 * CreateNewListScreenController allows java interaction with CreateNewListScreen.fxml
 *
 * @author Arne
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.0] [Date Created: 03/05/14]
 */

package smarttrolleygui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;

public class CreateNewListScreenController extends ControllerGeneral implements Initializable {

	private SmartTrolleyGUI application;
	// listNameTextField and createNewListButton are only made public so
	// that they can be accessed by tests
	@FXML
	public static TextField listNameTextField;
	@FXML
	public static Button createNewListButton;
	@ FXML
	private Label notifierLabel;
	
	/**
	 * initialize is automatically called when the controller is created.
	 * <p>
	 * Date Modified: 06 Mar 2014
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	/**
	 * setApp
	 * 
	 * @param application
	 *            <p>
	 *            Date Modified: 28 Feb 2014
	 */
	public void setApp(SmartTrolleyGUI application) {
		this.application = application;
	}

	/**
	 * loadStartScreen is called when the 'go back' button is pressed. It calls
	 * the goToStartScreen method in SmartTrolleyGUI.java
	 * 
	 * @param event
	 *            - response to click on 'go back' button
	 *            <p>
	 *            Date Modified: 6 Mar 2014
	 */
	public void loadStartScreen(ActionEvent event) {
		loadScreen(Screen.STARTSCREEN, application);
	}

	/**
	 * createNewList is called when the 'Create new list' button is pressed.
	 * This reads the user input and creates a new entry in the
	 * 'lists' table of the SQL database. It then loads the HomeScreen and
	 * passes it the name of the list, so that it can be displayed on screen.
	 * <p>
	 * User can create a new shopping list
	 * 
	 * @param event
	 *            - response to click on 'Create new list' button
	 *            <p>
	 *            Date Modified: 3 May 2014
	 */
	public void createNewList(ActionEvent event) throws SQLException {
		
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			SmartTrolleyToolBox.print("error: application == null");
		} else {
			SmartTrolleyToolBox.print("Create List button has been pressed.");
			
			// TODO: ensure name entered does not conflict with previously created list
			// and if it does notify user.
			
			// check input is not empty
			if (listNameTextField.getText() != null
					&& !listNameTextField.getText().isEmpty() && !listNameTextField.getText().contains(";")) {
				String enteredListName = listNameTextField.getText();

				// open SQL connection and create new entry in 'lists' table
				SqlConnection sqlConnection = new SqlConnection();

				int listID = sqlConnection.createNewList(enteredListName);
				
				SmartTrolleyGUI.setCurrentListName(enteredListName);				
				
				SmartTrolleyToolBox.print("LiD: " +listID);
				SmartTrolleyGUI.setCurrentListID(listID);
				
				// move to HomeScreen
				application.goToHomeScreen();
			} else {
				// Display error message if no name is entered.
				String noInputError = "Please enter a valid name for the list";
				SmartTrolleyToolBox.print(noInputError);
				notifierLabel.setText(noInputError);
				notifierLabel.setVisible(true);
			}
		}
	}
}
/************** End of CreateNewListScreenController **************/
