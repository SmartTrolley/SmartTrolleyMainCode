/**
 * SmartTrolley
 *
 * This file tests for list deletion
 *
 * @author Alick Jacklin
 * @author Prashant Chakravarty
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V2.0 [Date Created: 3 May 2014]
 */

package tests;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smarttrolleygui.ExampleShoppingListController;
import smarttrolleygui.SmartTrolleyGUI;
import smarttrolleygui.StartScreenController;
import DatabaseConnectors.SqlConnection;
import Printing.SmartTrolleyPrint;

public class DeleteListTest {

	private static SqlConnection productsDatabase;
	String query;
	private SmartTrolleyGUI GUIboot;
	Stage stage;
	
	
	/**
	 * This method runs before every test. It sets up a
	 * database connection and moves to the shopping list screen
	 * <p>N/A
	 * 
	 * @throws java.lang.Exception
	 * <p> Date Modified: 3 May 2014
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

		GUIboot = new SmartTrolleyGUI();

		newGUIThread = new Thread("New GUI") {
			public void run() {
				SmartTrolleyPrint.print("GUI thread");
				Application.launch(SmartTrolleyGUI.class,
						(java.lang.String[]) null);
				stage = new Stage();
				stage.setScene(new Scene(new Group(new Button(
						"my second window"))));
				/*
				 * GUIboot.start(stage); stage.show();
				 */
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
		SmartTrolleyDelay.delay(1000);

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
				SmartTrolleyPrint.print("Firing Button");
				// GUIboot.startScreen.viewAllShoppingListsButton.fire();
				Button viewLists = new Button();
				viewLists = StartScreenController.viewAllShoppingListsButton;
				viewLists.fire();
				SmartTrolleyPrint.print("Fired Button");
			}
		});

		/*
		 * Sets a robot to scroll through the buttons of the list
		 * of lists for two buttons then enters the third list in the column
		 */
		Robot listDroid = new Robot();
		
		//listDroid moves button selection down 1
		listDroid.keyPress(KeyEvent.VK_DOWN);
		listDroid.keyRelease(KeyEvent.VK_DOWN);
		//listDroid moves button selection down 1
		listDroid.keyPress(KeyEvent.VK_DOWN);
		listDroid.keyRelease(KeyEvent.VK_DOWN);
		//listDroid pushes enter button
		listDroid.keyPress(KeyEvent.VK_ENTER);
		listDroid.keyRelease(KeyEvent.VK_ENTER);
		
		

		/*
		 * You can visually see where your test ends up if you uncomment the two
		 * lines below. This delay is also required to allow the JavaFX thread
		 * to load the screens and catch up. Running the test without it means
		 * some of the UI commands may not run.
		 */
		SmartTrolleyDelay.delay(1000);
	}

	/**
	*Confirms a message box is shown when the delete list button,
	*on the shopping list screen, is pressed
	*<p>Confirm deletion with message box
	*<p> Date Modified: 4 May 2014
	*/
	@Test
	public void deleteListMessageBoxTest() {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Button deleteButton = ExampleShoppingListController.deleteListButton;
				deleteButton.fire();
			}
		});

		// Allow the GUI to catch up
		SmartTrolleyDelay.delay(500);

		assertTrue(ExampleShoppingListController.deleteMsgBx.isShowing());
		
		SmartTrolleyDelay.delay(500);
		
		try{
		
			//The robot is for controlling the button pushes on the message box.
			//The user must be in the test window for the robot to work.
			Robot doesNotDeleteBot = new Robot();
			
			//Moves the cursor to the no button		
			doesNotDeleteBot.keyPress(KeyEvent.VK_RIGHT);
			doesNotDeleteBot.keyRelease(KeyEvent.VK_RIGHT);
			//Pushes the no button
			doesNotDeleteBot.keyPress(KeyEvent.VK_ENTER);
			doesNotDeleteBot.keyRelease(KeyEvent.VK_ENTER);
		
		} catch (AWTException e) {
			SmartTrolleyPrint.print("doesNotDeleteBot cannot find buttons.");
	        e.printStackTrace();
		};
	}
	
	/**
	*Check that the list is deleted when the user confirms 
	*deletion from the delete message box
	*@throws AWTException
	*@throws SQLException
	*<p> Date Modified: 4 May 2014
	*/
	@Test
	public void listHasBeenDeletedTest() throws AWTException, SQLException{
		int listIDForDeletion = SmartTrolleyGUI.getcurrentListID();		

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Button deleteButton = ExampleShoppingListController.deleteListButton;
				deleteButton.fire();
			}
		});
		
		// Allow the GUI to catch up
		SmartTrolleyDelay.delay(3000);
		
		try{
			
			//The robot is for controlling the button pushes on the message box.
			//The user must be in the test window for the robot to work.		
			Robot deletionBot = new Robot();
			
			//this moves the selection back to the Yes button.
			deletionBot.keyPress(KeyEvent.VK_LEFT);
			deletionBot.keyRelease(KeyEvent.VK_LEFT);
			//this fires the yes button.
			deletionBot.keyPress(KeyEvent.VK_ENTER);
			deletionBot.keyRelease(KeyEvent.VK_ENTER);
		
		} catch (AWTException e) {
			SmartTrolleyPrint.print("deletionBot unable to locate buttons.");
	        e.printStackTrace();
		};
		
		SmartTrolleyDelay.delay(500);
		
		query = "SELECT * FROM lists WHERE name = " + listIDForDeletion;
		ResultSet results = productsDatabase.sendQuery(query);
		
		assertTrue(SqlConnection.isResultSetEmpty(results));

	}

	/**
	 * Closes productsDatabase between client and server
	 * <p>
	 * Date Modified: 3 May 2014
	 * @throws Exception
	 */
	@After
	public void closeAll() throws Exception {
		productsDatabase.closeConnection();

		SmartTrolleyPrint.print("Closing Test.");
	}

}

/************** End of DeleteListTest.java **************/

