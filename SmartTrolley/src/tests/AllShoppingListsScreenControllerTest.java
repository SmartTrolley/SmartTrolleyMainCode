/**
 * SmartTrolley
 *
 * Tests the AllShoppingListsController Screen for functionality
 *
 * @author Alick Jacklin
 * @author Prashant Chakravarty
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version version of this file [Date Created: 4 May 2014]
 */


package tests;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smarttrolleygui.AllShoppingListsScreenController;
import smarttrolleygui.ExampleShoppingListController;
import smarttrolleygui.SmartTrolleyGUI;
import smarttrolleygui.StartScreenController;
import DatabaseConnectors.SqlConnection;
import Printing.SmartTrolleyPrint;

public class AllShoppingListsScreenControllerTest {

	public Stage stage;
	private static SqlConnection productsDatabase;
	private SmartTrolleyGUI GUIboot;
	

	/**
	 * It sets up a database connection and moves to the lists screen
	 * @throws java.lang.Exception
	 * <p>
	 * Date Modified: 4 May 2014
	 */
	@Before
	public void setUp() throws Exception {

		productsDatabase = new SqlConnection();
		productsDatabase.openConnection();

		/*
		 * Create a new thread which launches the application. 
		 * If the main thread launches the application, 
		 * the rest of the test will only run after the application closes i.e. pointless.
		 */
		Thread newGUIThread;
		

		GUIboot = new SmartTrolleyGUI();

		newGUIThread = new Thread("New GUI") {
			public void run() {
				SmartTrolleyPrint.print("GUI thread");
				Application.launch(SmartTrolleyGUI.class, (java.lang.String[]) null);
			}
		};

		newGUIThread.start();

		/*
		 * Note that at this point, there are 3 threads running: 
		 * 1. Main (test) thread - Runs this class 
		 * 2. newGUIThread - Launches the Application 
		 * 3. JavaFX Thread - This thread actually is the application.
		 */

		/*
		 * It is necessary to pause the main (test) thread for some time to allow the application to catch up.
		 * Failure to implement this delay results in a nullPointerException,
		 * since the scene has not yet been created.
		 */
		SmartTrolleyDelay.delay(1000);

		/*
		 * In order to do anything with the user interface, the JavaFX thread must be modified using Platform.
		 * runlater etc etc If you try to monitor the UX outside this thread, there will be errors.
		 * Please note that for any elements of the UX that you want to modify,
		 *  there must be the corresponding variable (with an @FXML tag above it i.e.
		 * @FXML protected static Button viewAllShoppingListsButton;) in order to use it.
		 */
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				SmartTrolleyPrint.print("Firing view lists Button");
				Button viewLists = new Button();
				viewLists = StartScreenController.viewAllShoppingListsButton;
				viewLists.fire();
				SmartTrolleyPrint.print("Fired view lists Button");
			}
		});

		/*
		 * You can visually see where your test ends up if you uncomment the two lines below.
		 * This delay is also required to allow the JavaFX thread to load the screens and catch up.
		 * Running the test without it means some of the UI commands may not run.
		 */
		SmartTrolleyDelay.delay(1000);
	}

	/**
	 * Closes productsDatabase between client and server
	 * @throws java.lang.Exception
	 * <p>
	 * Date Modified: 4 May 2014
	 */
	@After
	public void tearDown() throws Exception {
		
		SmartTrolleyDelay.delay(1000);
		
		productsDatabase.closeConnection();

		GUIboot.stop();

		SmartTrolleyPrint.print("Closing Test.");

		SmartTrolleyDelay.delay(1000);

	}

	/**
	*Tests that the correct number of buttons are displayed
	*<p> Date Modified: 9 May 2014
	*/
	@Test
	public void correctNumberofButtonsDisplayedTest() {
		String enquiry = "SELECT COUNT(*) FROM lists";
		ResultSet results = null;
		int rowSize = 0;

		try {
			results = productsDatabase.sendQuery(enquiry);
		} catch (SQLException e) {
			SmartTrolleyPrint.print("unable to send query to database for unknown reasons");
			e.printStackTrace();
		}

		try {
			results.absolute(1);
			SmartTrolleyPrint.print("Number of rows Database is: " + results.getInt(1));
			rowSize = results.getInt(1);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		SmartTrolleyDelay.delay(5000);

		SmartTrolleyPrint.print("rowSize is " + rowSize);
		SmartTrolleyPrint.print("array size is" + AllShoppingListsScreenController.buttonList.size());

		assertTrue(rowSize == AllShoppingListsScreenController.buttonList.size() - 1);

	}

	/**
	 * Tests that the correct list is displayed to the user User can view list of lists
	 * <p>
	 * Date Modified: 5 May 2014
	 */
	@Test
	public void correctListDisplayedTest() {
		
		SmartTrolleyDelay.delay(3000);
		
		ResultSet results = null;
		String query;
		int rowSize = 0;
		int listSize = 0;

		query = "SELECT * FROM lists_products WHERE listID = 43";

		try {
			results = productsDatabase.sendQuery(query);
		} catch (SQLException e1) {
			SmartTrolleyPrint.print("unable to send query, unknown reason");
		}
		
		assertFalse(SqlConnection.isResultSetEmpty(results));

		
		 try { Robot menuRobot = new Robot();
		 
		 menuRobot.keyPress(KeyEvent.VK_ENTER);
		 menuRobot.keyRelease(KeyEvent.VK_ENTER);
		 
		 } catch (AWTException e) { 
			 SmartTrolleyPrint.print("Robot cannot function, reasons unknown");

			 }
		

		try {
			results.last();
			results.getRow();

			rowSize = results.getRow();
			
		} catch (SQLException e1) {
			SmartTrolleyPrint.print("no results in ResultSet");
		}
		
		SmartTrolleyDelay.delay(1000);

		SmartTrolleyPrint.print("row Size is " + rowSize);
		
		listSize = ExampleShoppingListController.getProductDataSize();
		
		SmartTrolleyPrint.print("list Size is " + listSize);
		
		assertTrue(rowSize == listSize);
		
		SmartTrolleyDelay.delay(1000);
	
	}

}
/**************End of AllShoppingListsScreenControllerTest.java**************/
