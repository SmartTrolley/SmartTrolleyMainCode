//TODO THis test needs to be refactored to launch the application as an instance
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

package smarttrolleygui;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;

public class TestDeleteList {

	private static SqlConnection productsDatabase;
	String query;
	private SmartTrolleyGUI smartTrolleyApplication;
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


		smartTrolleyApplication = new SmartTrolleyGUI();

		smartTrolleyApplication = TestGUINavigationForTests.launchTestApplication(smartTrolleyApplication);

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
				SmartTrolleyToolBox.print("Firing Button");
				smartTrolleyApplication.startScreen.viewAllShoppingListsButton.fire();
				/*Button viewLists = new Button();
				viewLists = StartScreenController.viewAllShoppingListsButton;
				viewLists.fire();*/
				SmartTrolleyToolBox.print("Fired Button");
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
		SmartTrolleyToolBox.delay(1000);
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
				Button deleteButton = ShoppingListController.deleteListButton;
				deleteButton.fire();
			}
		});

		// Allow the GUI to catch up
		SmartTrolleyToolBox.delay(500);

		assertTrue(ShoppingListController.deleteMsgBx.isShowing());
		
		SmartTrolleyToolBox.delay(500);
		
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
			SmartTrolleyToolBox.print("doesNotDeleteBot cannot find buttons.");
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
				Button deleteButton = ShoppingListController.deleteListButton;
				deleteButton.fire();
			}
		});
		
		// Allow the GUI to catch up
		SmartTrolleyToolBox.delay(3000);
		
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
			SmartTrolleyToolBox.print("deletionBot unable to locate buttons.");
	        e.printStackTrace();
		};
		
		SmartTrolleyToolBox.delay(500);
		
		query = "SELECT * FROM lists WHERE name = " + listIDForDeletion;
		ResultSet results = productsDatabase.sendQuery(query);
		
		//This asserts that the list no longer exists
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

		SmartTrolleyToolBox.print("Closing Test.");
	}

}

/************** End of TestDeleteList.java **************/

