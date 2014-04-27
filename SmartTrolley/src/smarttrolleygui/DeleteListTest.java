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
 * @version version of this file [Date Created: 25 Apr 2014]
 */

/*YOUR CODE HERE*/

/**************End of DeleteListTest.java**************/
package smarttrolleygui;

import static org.junit.Assert.*;
import javafx.application.Application;
import javafx.scene.control.Button;

import org.junit.Before;
import org.junit.Test;

import Printing.SmartTrolleyPrint;

public class DeleteListTest {
	private SmartTrolleyGUI GUIboot;

	/**
	 * Setup method that runs before every test
	 * <p>
	 * Test(s)/User Story that it satisfies
	 * 
	 * @throws java.lang.Exception
	 *             [If applicable]@see [Reference URL OR Class#Method]
	 *             <p>
	 *             Date Modified: 25 Apr 2014
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Tests to see if list is deleted?
	 * <p>
	 * Test(s)/User Story that it satisfies [If applicable]@see [Reference URL
	 * OR Class#Method]
	 * <p>
	 * Date Modified: 25 Apr 2014
	 */
	@Test
	public void deletingListTest() {
		Thread newGUIThread;

		GUIboot = new SmartTrolleyGUI();			
		
		// TODO May need to put this into a separate thread
		newGUIThread = new Thread("New GUI") {
			public void run() {				
				SmartTrolleyPrint.print("GUI thread");
				Application.launch(SmartTrolleyGUI.class, (java.lang.String[]) null);
				//GUIboot.main(null);
			}
		};		

		newGUIThread.start();
		
		try { Thread.sleep(3000); } catch (InterruptedException e1) {
		 e1.printStackTrace(); }
		
		SmartTrolleyPrint.print("Firing Button");
		Button testButton = StartScreenController.viewAllShoppingListsButton;
		testButton.fire();
		//GUIboot.startScreen.viewAllShoppingListsButton.setText("Hi");
		SmartTrolleyPrint.print("Fired Button");
		
	//	while (true);

	}

}
