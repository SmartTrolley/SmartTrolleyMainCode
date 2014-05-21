package tests;

import static org.junit.Assert.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DatabaseConnectors.SqlConnection;
import Printing.SmartTrolleyPrint;

import smarttrolleygui.ExampleShoppingListController;
import smarttrolleygui.HomeScreenController;
import smarttrolleygui.SmartTrolleyGUI;
import smarttrolleygui.StartScreenController;

public class CategoryFilteringTests {
	
	private HomeScreenController shoppingList;
	private String categoryListNumber;
	private static SqlConnection productsDatabase;
	private SmartTrolleyGUI GUIboot;
	private int i = 1;
	
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
	 * This test will ensure that when a category is clicked, the category number is returned from the database
	 */
	
	
	@Test
	public void getCategoryIDTest(){
		if (i == 1){
		SmartTrolleyDelay.delay(10000);
		
		shoppingList = new HomeScreenController();
		categoryListNumber = shoppingList.getCategoryNumber();
		System.out.println(categoryListNumber);
		assertFalse(categoryListNumber == null);
		}
	}
	
	
	
}
