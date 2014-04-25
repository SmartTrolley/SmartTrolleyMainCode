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

import org.junit.Before;
import org.junit.Test;

public class DeleteListTest {
	private SmartTrolleyGUI GUIboot;

	/**
	 *Setup method that runs before every test
	 *<p>Test(s)/User Story that it satisfies
	 *@throws java.lang.Exception
	 *[If applicable]@see [Reference URL OR Class#Method]
	 *<p> Date Modified: 25 Apr 2014
	 */
	@Before
	public void setUp() throws Exception {
		GUIboot = new SmartTrolleyGUI();
		GUIboot.goToAllShoppingListsScreen();
	}

	/**
	*Tests to see if list is deleted?
	*<p>Test(s)/User Story that it satisfies
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 25 Apr 2014
	*/
	@Test
	public void deletingListTest() {
		fail("Not yet implemented");
	}

}

