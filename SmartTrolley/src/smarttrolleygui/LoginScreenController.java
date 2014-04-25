/**
 * SmartTrolley
 *
 * Controls the interaction of the login screen (FXML: loginScreen.fxml)
 *
 * @author Prashant Chakravarty
 * @author Alick Jacklin
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.0 [Date Created: 16 Apr 2014]
 */

package smarttrolleygui;

import java.net.URL;
import java.util.ResourceBundle;

import Printing.SmartTrolleyPrint;

import sql.ExecuteSQLScriptClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class LoginScreenController implements Initializable {

	private SmartTrolleyGUI application;

	@FXML
	// ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML
	// URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	// fx:id="loginButton"
	private Button loginButton; // Value injected by FXMLLoader

	@FXML
	// fx:id="usernameTextField"
	private TextField usernameTextField;

	@FXML
	// fx:id="passwordTextField"
	private TextField passwordTextField;

	@FXML
	// fx:id="rememberLoginDetailsChkbx"
	private CheckBox rememberLoginDetailsChkbx;

	// Handler for Button[fx:id="loginButton"] onAction
	// @FXML
	public void login(ActionEvent event) {

		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			SmartTrolleyPrint.print("error: application == null");
		} else {

			SmartTrolleyPrint.print("here");
			
			application.USERNAME = usernameTextField.getText();
		
			SmartTrolleyPrint.print("here1");
			
			 application.PASSWORD = passwordTextField.getText();			

			 ExecuteSQLScriptClass readSQL = new ExecuteSQLScriptClass();
			 readSQL.ExecuteSQLScript(usernameTextField.getText(),
			passwordTextField.getText());
			

			 application.goToStartScreen();
		}

	}

	/**
	 * This method is called automatically when an instance of the controller is
	 * created
	 * <p>
	 * Test(s)/User Story that it satisfies [If applicable]@see [Reference URL
	 * OR Class#Method]
	 * <p>
	 * Date Modified: 16 Apr 2014
	 */
	// @FXML
	// This method is called by the FXMLLoader when initialization is complete
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'loginScreen.fxml'.";
		
		//TODO REMOVE LATER
		usernameTextField.setText("root");

		if (rememberLoginDetailsChkbx.isSelected()) {
			// Autofill textfields for username and password from stored values

			// Hit Begin
			// loginButton.fire();
		}
	}

	/**
	 * setApp
	 * <p>
	 * Test(s)/User Story that it satisfies
	 * 
	 * @param application
	 *            <p>
	 *            Date Modified: 16 Apr 2014
	 */
	public void setApp(SmartTrolleyGUI application) {
		this.application = application;
	}

	// TODO Get this method working
	/**
	 * This method stores the users login details so they can be used the next
	 * time
	 * <p>
	 * Test(s)/User Story that it satisfies
	 * <p>
	 * Date Modified: 16 Apr 2014
	 */
	public void rememberLoginDetails() {

		// Store login details
	}

}

/************** End of LoginScreenController.java **************/
