/**
 * SmartTrolley
 *
 * Runs tests to check deletion of a list from the database
 *
 * @author Alick Jacklin
 * @author Prashant Chakravarty
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version 2.0 [Date Created: 25 Apr 2014]
 */

package tests;

import static org.junit.Assert.*;
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
import smarttrolleygui.ExampleShoppingListController;
import smarttrolleygui.Lists;
import smarttrolleygui.SmartTrolleyGUI;
import smarttrolleygui.StartScreenController;

import DatabaseConnectors.SqlConnection;
import Printing.SmartTrolleyPrint;

import java.sql.*;

public class DeleteItemsFromListTest {

	public Stage stage;

	private SmartTrolleyGUI GUIboot;
	private static SqlConnection productsDatabase;
	String query;

	/**
	 * Setup method that runs before every test
	 *  It sets up a
	 * database connection and moves to the shopping list screen
	 * @throws Exception
	 * @see http
	 *      ://stackoverflow.com/questions/10492886/how-to-create-two-windows
	 *      -simultaneously-in-javafx
	 *      <p>
	 *      Date Modified: 29 Apr 2014
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
				Application.launch(SmartTrolleyGUI.class, (java.lang.String[]) null);
				stage = new Stage();
				stage.setScene(new Scene(new Group(new Button("my second window"))));
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
		try {
			Thread.sleep(1000);
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
				SmartTrolleyPrint.print("Firing Button");
				// GUIboot.startScreen.viewAllShoppingListsButton.fire();
				Button viewLists = new Button();
				viewLists = StartScreenController.viewAllShoppingListsButton;
				viewLists.fire();
				SmartTrolleyPrint.print("Fired Button");
			}
		});

		/*
		 * It appears that another button press can be run immediately after. If
		 * this causes problems, try adding a delay before the next button
		 * press.
		 */
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				SmartTrolleyPrint.print("Firing list Button");
				// GUIboot.allShoppingLists.list1Button.fire();
				Button viewList = new Button();
				viewList = AllShoppingListsScreenController.list1Button;
				viewList.fire();
				SmartTrolleyPrint.print("Fired list Button");
			}
		});

		/*
		 * You can visually see where your test ends up if you uncomment the two
		 * lines below. This delay is also required to allow the JavaFX thread
		 * to load the screens and catch up. Running the test without it means
		 * some of the UI commands may not run.
		 */
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Tests to see if list is deleted?
	 * <p>
	 * Remove products from list
	 * <p>
	 * Date Modified: 29 Apr 2014
	 * 
	 * @Test // TODO Rename this as JAVAFXTDDEx in spikes repo public void
	 *       deletingListTest() {
	 * 
	 *       }
	 */

	/**
	 * Tests to see if the item quantity increase
	 * <p>
	 * Update total items in list and quantity of item
	 * <p>
	 * Date Modified: 3 May 2014
	 * 
	 * @throws SQLException
	 */
	@Test
	public void QuantityUpdateTest() throws SQLException {
		query = "SELECT * FROM lists_products WHERE listid = 2";
		ResultSet results = productsDatabase.sendQuery(query);
		SmartTrolleyPrint.print("sending query to sql server to retreive list information");

		/*
		 * Lists partyList = new Lists();
		 * SmartTrolleyPrint.print("Creating new local list named partyList");
		 */
		results.absolute(1);

		int oldQuantity = results.getInt("Quantity");

		// sets product ID
		SmartTrolleyPrint.print("PiD: " + results.getInt("ProductID"));
		// sets List ID
		SmartTrolleyPrint.print("LiD: " + results.getInt("ListID"));
		// sets Quantity of product in list
		SmartTrolleyPrint.print("Q: " + oldQuantity);
		
		//removeButton = ExampleShoppingListController.button;
		//removeButton.fire();
		

		// Now hit - button, update database
		/*Button viewLists = new Button();
		viewLists = ExampleShoppingListController.
		viewLists.fire();
		SmartTrolleyPrint.print("Fired Button");*/
		
		// Recheck database
		/*
		 * query = "SELECT * FROM lists_products WHERE listid = 2"; results =
		 * productsDatabase.sendQuery(query); SmartTrolleyPrint.print(
		 * "sending query to sql server to retreive list information");
		 */
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		/*
		 * int Quantity = partyList.getProductQuantity(1);
		 * SmartTrolleyPrint.print("the quantity of product 1 is: " + Quantity);
		 * int startingQuantity = Quantity;
		 */

		/*
		 * while(results.next()){ //sets product ID
		 * partyList.setProductID(results.getInt("ProductID")); //sets List ID
		 * partyList.setListID(results.getInt("ListID")); //sets Quantity of
		 * product in list partyList.setQuantity(results.getInt("Quantity"));
		 * 
		 * } SmartTrolleyPrint.print("All data now inserted into list");
		 */

		// assertTrue(startingQuantity < Quantity);

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

/************** End of DeleteItemsFromListTest.java **************/
