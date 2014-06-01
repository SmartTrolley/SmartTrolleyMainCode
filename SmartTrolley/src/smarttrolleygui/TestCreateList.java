/**
 * SmartTrolley
 *
 * This file tests for list creation
 * Adapted from TestDeleteList.java
 * @author Alick Jacklin
 * @author Prashant Chakravarty
 * 
 * Adapted by Arne
 *
 * @version V1.0 [Date Created: 5 May 2014]
 */

package smarttrolleygui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;

public class TestCreateList {

	private static SqlConnection productsDatabase;
	String query;
	Stage stage;
	private int listID;
		
	/**
	 * This method runs before every test. It sets up a
	 * database connection and moves to the shopping list screen
	 * <p>N/A
	 * 
	 * @throws java.lang.Exception
	 *             [If applicable]@see [Reference URL OR Class#Method]
	 *             <p>
	 *             Date Modified: 9 May 2014
	 */
	@Before
	public void setUp() throws Exception {
		
		productsDatabase = new SqlConnection();
		productsDatabase.openConnection();

		/*
		 * Create a new thread which launches the application. If the main
		 * thread launches the application, the rest of the test will only run
		 * after the application closes i.e. pointless.
		 */
		Thread newGUIThread;

		newGUIThread = new Thread("New GUI") {
			public void run() {
				SmartTrolleyToolBox.print("GUI thread");
				Application.launch(SmartTrolleyGUI.class,
						(java.lang.String[]) null);
				stage = new Stage();
				stage.setScene(new Scene(new Group(new Button(
						"my second window"))));				 
			}
		};

		newGUIThread.start();

		/*
		 * Note that at this point, there are 3 threads running: 1. Main (test)
		 * thread - Runs this class 2. newGUIThread - Launches the Application
		 * 3. JavaFX Thread - This thread actually is the application.
		 */

		/*
		 * It is necessary to pause the main (test) thread for some time to
		 * allow the application to catch up. Failure to implement this delay
		 * results in a nullPointerException, since the scene has not yet been
		 * created.
		 */
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		/*
		 * In order to do anything with the user interface, the JavaFX thread
		 * must be modified using Platform.runlater etc etc If you try to
		 * monitor the UX outside this thread, there will be errors. Please note
		 * that for any elements of the UX that you want to modify, there must
		 * be the corresponding variable (with an @FXML tag above it i.e.
		 * 
		 * @FXML protected static Button viewAllShoppingListsButton;) in order
		 * to use it.
		 */
		Platform.runLater(new Runnable() {
			@Override
			public void run() {				
				// move to 'Create New List' screen
				Button createNewListButton = StartScreenController.createNewListButton;				
				createNewListButton.fire();			
			}
		});
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}	
	
	/**
	*Check that the list is created when the user
	*presses 'Create new list' button.
	*<p>User story: User can create new list
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 9 May 2014
	*/
	@Test
	public void listIsCreated() throws SQLException {
		
		Platform.runLater(new Runnable() {
			

			@Override
			public void run() {
				// set text of TextField to arbitrary input as an example name for a new list
				TextField listNameTextField = CreateNewListScreenController.listNameTextField;
				listNameTextField.setText("asd");
				String textInput = listNameTextField.getText();
				assertTrue(textInput instanceof String);

				// fire 'Create New List' button
				Button createNewListButton = CreateNewListScreenController.createNewListButton;
				createNewListButton.fire();
				
				// search database for newly created list name
				query = "SELECT * FROM lists WHERE name = '" + textInput + "'";
				ResultSet results;
				try {
					results = productsDatabase.sendQuery(query);
					assertFalse(results == null);
					while (results.next()) {
						SmartTrolleyToolBox.print("List with name: "
								+ results.getString("Name")
								+ " has been created in the SQL database.");
						listID = results.getInt("ListID");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		});
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	// TODO: create a test to check input is valid

	/**
	 * Closes productsDatabase between client and server
	 * <p>
	 * Date Modified: 9 May 2014
	 * 
	 * @throws Exception
	 */
	@After
	public void closeAll() throws Exception {
		
		String sqlStatement = "DELETE FROM `cl36-st`.`lists` WHERE listID = " + listID;

		productsDatabase.executeStatement(sqlStatement);
		
		productsDatabase.closeConnection();
		SmartTrolleyToolBox.print("Closing Test.");
	}

}

/************** End of TestCreateList.java **************/
