/**
 * SmartTrolley
 *
 * This file tests for list creation
 * Adapted from DeleteListTest.java
 * @author Arne Sandschulte
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.0 [Date Created: 5 May 2014]
 */

package tests;

import static org.junit.Assert.*;

import java.awt.AWTException;
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
import smarttrolleygui.CreateNewListScreenController;
import smarttrolleygui.SmartTrolleyGUI;
import smarttrolleygui.StartScreenController;
import DatabaseConnectors.SqlConnection;
import Printing.SmartTrolleyPrint;

public class CreateList {

	private static SqlConnection productsDatabase;
	String query;
	Stage stage;
	
	
	/**
	 * This method runs before every test. It sets up a
	 * database connection and moves to the shopping list screen
	 * <p>N/A
	 * 
	 * @throws java.lang.Exception
	 *             [If applicable]@see [Reference URL OR Class#Method]
	 *             <p>
	 *             Date Modified: 3 May 2014
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
				SmartTrolleyPrint.print("GUI thread");
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
				Button createNewListButton = StartScreenController.createNewListButton;
				// move to 'Create New List' screen
				createNewListButton.fire();
				// set text of TextField to "Hello" and fire 'Create New List' button
				TextField listNameTextField = CreateNewListScreenController.listNameTextField;
				listNameTextField.setText("Hello");
				createNewListButton = CreateNewListScreenController.createNewListButton;
				createNewListButton.fire();	
			}
		});
		
		/*
		 * You can visually see where your test ends up if you uncomment the two
		 * lines below. This delay is also required to allow the JavaFX thread
		 * to load the screens and catch up. Running the test without it means
		 * some of the UI commands may not run.
		 */
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}	
	
	/**
	*Check that the list is created when the user
	*presses 'Create new list' button.
	*<p>Test(s)/User Story that it satisfies
	*@throws AWTException
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 May 2014
	*/
	@Test
	public void listIsCreated() throws AWTException, SQLException{
		TextField listNameTextField = CreateNewListScreenController.listNameTextField;
		// get text from TextField and assign to variable
		String textInput = listNameTextField.getText();
		assertTrue(textInput instanceof String);
		SmartTrolleyPrint.print("TextField reads: " + textInput);
		query = "INSERT INTO `cl36-st`.`lists` (`Name`) VALUES ('" + textInput + "');";
		productsDatabase.executeStatement(query);
		
		query = "SELECT * FROM lists WHERE name = '" + textInput + "'";
		ResultSet results = productsDatabase.sendQuery(query);
		
		assertFalse(results == null);
		
		while (results.next()) {
			SmartTrolleyPrint.print("List with name: " + results.getString("Name") + " has been created in the SQL database.");
		}	
	}

	/**
	 * Closes productsDatabase between client and server
	 * <p>
	 * Date Modified: 3 May 2014
	 * 
	 * @throws Exception
	 */
	@After
	public void closeAll() throws Exception {
		productsDatabase.closeConnection();

		/*
		 * GUIboot.stop(); Platform.runLater(new Runnable() {
		 * 
		 * @Override public void run() { try { GUIboot.stop(); } catch
		 * (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } Platform.exit(); } });
		 */
		SmartTrolleyPrint.print("Closing Test.");
	}

}

/************** End of DeleteListTest.java **************/
