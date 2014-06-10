package smarttrolleygui;

import static org.junit.Assert.assertFalse;
import javafx.application.Application;
import javafx.application.Platform;

import org.junit.Before;
import org.junit.Test;

import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;
/**
 * SmartTrolley
 *
 * Tests the Category Filtering for functionality
 *
 * @author Alick Jacklin
 * @author Prashant Chakravarty
 * @author V1.1 Thomas [Commenting] 
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version [1.0] [Date Created: 4 May 2014]
 * @version [1.1] [Date Created: 10/06/2014]
 */
public class TestCategoryFiltering {

	private HomeScreenController shoppingList;
	private String categoryListNumber;
	private static SqlConnection productsDatabase;
	private SmartTrolleyGUI smartTrolleyApplication;
	private int i = 1;

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
				smartTrolleyApplication.start(SmartTrolleyGUI.getStage());
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
	* This test will ensure that when a category is clicked, the category number is returned from the database
	*<p> Date Modified: 10 Jun 2014
	*/
	@Test
	public void getCategoryIDTest() {
		if (i == 1) {
			SmartTrolleyToolBox.delay(10000);

			shoppingList = new HomeScreenController();
			categoryListNumber = shoppingList.getCategoryNumber();
			SmartTrolleyToolBox.print(categoryListNumber);
			assertFalse(categoryListNumber == null);
		}
	}

}
