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

import DatabaseConnectors.SqlConnection;
import Printing.SmartTrolleyPrint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateNewListScreenController implements Initializable {

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
		// TODO remove if left unused
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

		/*
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			System.out.println("error: application == null");
		} else {
			application.goToStartScreen();
		}
		*/
		
    	ControllerGeneral controller = new ControllerGeneral(); 
    	
    	controller.loadStartScreen(event);
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
			System.out.println("error: application == null");
		} else {
			SmartTrolleyPrint.print("Create List button has been pressed.");
			
			// TODO: ensure name entered does not conflict with previously created list
			// and if it does notify user.
			
			// check input is not empty
			if (listNameTextField.getText() != null
					&& !listNameTextField.getText().isEmpty()) {
				String enteredListName = listNameTextField.getText();

				// open SQL connection and create new entry in 'lists' table
				SqlConnection sqlConnection = new SqlConnection();
				sqlConnection.openConnection();
				
				String sqlStatement = "INSERT INTO `cl36-st`.`lists` (`Name`) VALUES ('"
						+ enteredListName + "');";
				sqlConnection.executeStatement(sqlStatement);
				SmartTrolleyPrint.print("Created new list: " + enteredListName);

				// move to HomeScreen
				// TODO: pass on enteredListName, so that it can be displayed / call setCurrentListID 
				application.goToHomeScreen();
			} else {
				// Display error message if no name is entered.
				String noInputError = "Please enter a name for the list";
				SmartTrolleyPrint.print(noInputError);
				notifierLabel.setText(noInputError);
				notifierLabel.setVisible(true);
			}
		}
	}
}
/************** End of CreateNewListScreenController **************/
