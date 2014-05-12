/**
 * SmartTrolley
 *
 * A DESCRIPTION OF THE FILE
 *
 * @author Name1
 * @author Name2
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version version of this file [Date Created: 4 May 2014]
 */

/*YOUR CODE HERE*/

/**************End of AllShoppingListsScreenControllerTest.java**************/
package tests;

import static org.junit.Assert.*;

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

import smarttrolleygui.AllShoppingListsScreenController;
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
	 * 
	 * @throws java.lang.Exception
	 *             <p>
	 *             Date Modified: 4 May 2014
	 */
	@Before
	public void setUp() throws Exception {

		productsDatabase = new SqlConnection();
		productsDatabase.openConnection();

		/*
		 * Create a new thread which launches the application. If the main thread launches the application, the rest of the test will only run after the application closes i.e. pointless.
		 */
		Thread newGUIThread;

		GUIboot = new SmartTrolleyGUI();

		newGUIThread = new Thread("New GUI") {
			public void run() {
				SmartTrolleyPrint.print("GUI thread");
				Application.launch(SmartTrolleyGUI.class, (java.lang.String[]) null);
				/*
				 * stage = new Stage();stage.setScene(new Scene(new Group(new Button("my second window"))));
				 * 
				 * GUIboot.start(stage); stage.show();
				 */
			}
		};

		newGUIThread.start();

		/*
		 * Note that at this point, there are 3 threads running: 1. Main (test) thread - Runs this class 2. newGUIThread - Launches the Application 3. JavaFX Thread - This thread actually is the application.
		 */

		/*
		 * It is necessary to pause the main (test) thread for some time to allow the application to catch up. Failure to implement this delay results in a nullPointerException, since the scene has not yet been created.
		 */
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		/*
		 * In order to do anything with the user interface, the JavaFX thread must be modified using Platform.runlater etc etc If you try to monitor the UX outside this thread, there will be errors. Please note that for any elements of the UX that you want to modify, there must be the corresponding variable (with an @FXML tag above it i.e.
		 * 
		 * @FXML protected static Button viewAllShoppingListsButton;) in order to use it.
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
		 * You can visually see where your test ends up if you uncomment the two lines below. This delay is also required to allow the JavaFX thread to load the screens and catch up. Running the test without it means some of the UI commands may not run.
		 */
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Closes productsDatabase between client and server
	 * <p>
	 * Test(s)/User Story that it satisfies
	 * 
	 * @throws java.lang.Exception
	 *             <p>
	 *             Date Modified: 4 May 2014
	 */
	@After
	public void tearDown() throws Exception {
		productsDatabase.closeConnection();

		GUIboot.stop();
		/*
		 * Platform.runLater(new Runnable() {
		 * 
		 * @Override public void run() { try { GUIboot.stop(); } catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); } SmartTrolleyGUI.stage.close(); com.sun.javafx.application.PlatformImpl.tkExit(); Platform.exit();
		 * 
		 * } });
		 */

		SmartTrolleyPrint.print("Closing Test.");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

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
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		ResultSet results = null;
		String query;
		int rowSize = 0;

		query = "SELECT * FROM lists_products WHERE listID = 43";

		try {
			results = productsDatabase.sendQuery(query);
		} catch (SQLException e1) {
			SmartTrolleyPrint.print("unable to send query, unknown reason");
		}
		
		assertFalse(productsDatabase.isResultSetEmpty(results));

		/*
		 * try { Robot menuRobot = new Robot();
		 * 
		 * menuRobot.keyPress(KeyEvent.VK_ENTER); menuRobot.keyRelease(KeyEvent.VK_ENTER);
		 * 
		 * } catch (AWTException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		try {
			results.last();
			results.getRow();

			rowSize = results.getRow();
		} catch (SQLException e) {
			SmartTrolleyPrint.print("no results in ResultSet");
		}

		SmartTrolleyPrint.print("row Size is " + rowSize);

	}

}
