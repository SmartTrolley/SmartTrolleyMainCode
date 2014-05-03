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
 * @version V1.0 [Date Created: 3 May 2014]
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
	 * Method/Test Description
	 * <p>
	 * Test(s)/User Story that it satisfies
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

	@Test
	public void deleteListTest() {
		
		Button deleteButton = ExampleShoppingListController.deleteListButton;
		deleteButton.fire();

	}

	/**
	 * Closes connection between client and server
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

