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
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.junit.Before;
import org.junit.Test;

import Printing.SmartTrolleyPrint;

public class DeleteListTest {
	
	public Stage stage; 
	
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
		Thread newGUIThread;

		GUIboot = new SmartTrolleyGUI();						
		
		newGUIThread = new Thread("New GUI") {
			public void run() {				
				SmartTrolleyPrint.print("GUI thread");
				Application.launch(SmartTrolleyGUI.class, (java.lang.String[]) null);
			}
		};		

		newGUIThread.start();		
		
		try { Thread.sleep(300); } catch (InterruptedException e1) {
		 e1.printStackTrace(); }
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {           
	            SmartTrolleyPrint.print("Firing Button");
	    		GUIboot.startScreen.viewAllShoppingListsButton.fire();
	    		SmartTrolleyPrint.print("Fired Button");	            
	        }
	    });
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
//TODO Rename this as JAVAFXTDDEx in spikes repo, add some comments
	public void deletingListTest() {	
		
		//You can visually see where your test ends up if you uncomment the two lines below.
		/*try { Thread.sleep(2000); } catch (InterruptedException e1) {
			 e1.printStackTrace(); }*/
	}
}

