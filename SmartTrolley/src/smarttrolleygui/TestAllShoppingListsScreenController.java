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

package smarttrolleygui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;

public class TestAllShoppingListsScreenController {

	public Stage stage;
	private static SqlConnection productsDatabase;
	private SmartTrolleyGUI smartTrolleyApplication;

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

		smartTrolleyApplication = new SmartTrolleyGUI();

		/*
		 * Create a new thread which launches the application. If the main
		 * thread launches the application, the rest of the test will only run
		 * after the application closes i.e. pointless.
		 */
		Thread newGUIThread;

		newGUIThread = new Thread("New GUI") {
			public void run() {
				SmartTrolleyToolBox.print("GUI thread");
				Application.launch(SmartTrolleyGUI.class, (java.lang.String[]) null);

			}
		};
		newGUIThread.start();

		// Delay to allow the application to launch
		// If you get NullPointer errors around this line, increase the delay
		SmartTrolleyToolBox.delay(200);

		// Now launch the instance of SmartTrolleyGUI, which takes over the displayed stage
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				smartTrolleyApplication.start(SmartTrolleyGUI.stage);
			}
		});

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
		SmartTrolleyToolBox.delay(2500);

		TestGUINavigationForTests.goToAllShoppingListsFromStartScreen(smartTrolleyApplication);
		
		/*
		 * You can visually see where your test ends up if you uncomment the two lines below.
		 * This delay is also required to allow the JavaFX thread to load the screens and catch up.
		 * Running the test without it means some of the UI commands may not run.
		 */
		SmartTrolleyToolBox.delay(1000);
	}

	/**
	 * Closes productsDatabase between client and server
	 * @throws java.lang.Exception
	 * <p>
	 * Date Modified: 4 May 2014
	 */
	@After
	public void tearDown() throws Exception {

		SmartTrolleyToolBox.delay(1000);

		productsDatabase.closeConnection();

		smartTrolleyApplication.stop();

		SmartTrolleyToolBox.print("Closing Test.");

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
			SmartTrolleyToolBox.print("unable to send query to database for unknown reasons");
			e.printStackTrace();
		}

		try {
			results.absolute(1);
			SmartTrolleyToolBox.print("Number of rows Database is: " + results.getInt(1));
			rowSize = results.getInt(1);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		SmartTrolleyToolBox.delay(500);

		SmartTrolleyToolBox.print("rowSize is " + rowSize);
		SmartTrolleyToolBox.print("array size is" + AllShoppingListsScreenController.buttonList.size());

		assertTrue(rowSize == AllShoppingListsScreenController.buttonList.size()-1);

	}

	/**
	 * Tests that the correct list is displayed to the user User can view list of lists
	 * <p>
	 * Date Modified: 5 May 2014
	 */
	@Test
	public void correctListDisplayedTest() {

		SmartTrolleyToolBox.delay(3000);

		ResultSet results = null;
		String query;
		int rowSize = 0;
		int listSize = 0;

		query = "SELECT * FROM lists_products WHERE listID = 1";

		try {
			results = productsDatabase.sendQuery(query);
		} catch (SQLException e1) {
			SmartTrolleyToolBox.print("unable to send query, unknown reason");
		}

		assertFalse(SqlConnection.isResultSetEmpty(results));

		try {
			Robot menuRobot = new Robot();

			menuRobot.keyPress(KeyEvent.VK_TAB);
			menuRobot.keyRelease(KeyEvent.VK_TAB);
			menuRobot.keyPress(KeyEvent.VK_ENTER);
			menuRobot.keyRelease(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
			SmartTrolleyToolBox.print("Robot cannot function, reasons unknown");

		}

		try {
			results.last();

			rowSize = results.getRow();

		} catch (SQLException e1) {
			SmartTrolleyToolBox.print("no results in ResultSet");
		}

		SmartTrolleyToolBox.delay(1000);

		SmartTrolleyToolBox.print("row Size is " + rowSize);

		listSize = ShoppingListController.getProductDataSize();

		SmartTrolleyToolBox.print("list Size is " + listSize);

		assertTrue(rowSize == listSize);

		SmartTrolleyToolBox.delay(1000);

	}

}
/**************End of TestAllShoppingListsScreenController.java**************/
