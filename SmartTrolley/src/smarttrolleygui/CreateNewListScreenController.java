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
import javafx.scene.control.TextField;

public class CreateNewListScreenController implements Initializable {

	private SmartTrolleyGUI application;
	// listNameTextField and createNewListButton are only made public so
	// that they can be accessed by tests
	@FXML
	public static TextField listNameTextField;
	@FXML
	public static Button createNewListButton;

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

		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			System.out.println("error: application == null");
		} else {
			application.goToStartScreen();
		}
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
			
			// TODO: ensure input is valid -> should be in its own method
			if (listNameTextField.getText() != null
					&& !listNameTextField.getText().isEmpty()) {
				String enteredListName = listNameTextField.getText();
				SmartTrolleyPrint.print("Entered list name is: " + enteredListName);

				// open SQL connection and create new entry in 'lists' table
				SqlConnection sqlConnection = new SqlConnection();
				sqlConnection.openConnection();
				
				// TODO: ensure name entered does not conflict with previously created list
				// and if it does notify user.
				String sqlStatement = "INSERT INTO `cl36-st`.`lists` (`Name`) VALUES ('"
						+ enteredListName + "');";
				sqlConnection.executeStatement(sqlStatement);

				// move to HomeScreen
				// TODO: pass on enteredListName, so that it can be displayed on screen.
				application.goToHomeScreen();
			} else {
				// TODO: create label in CreateNewListScreen.fxml, 
				// below the TextField for example, to allow error message 
				// to be displayed. Alternatively use a message box.
				SmartTrolleyPrint.print("No text has been entered");
			}
		}
	}
}
/************** End of CreateNewListScreenController **************/
