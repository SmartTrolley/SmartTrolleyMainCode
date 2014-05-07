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

* @version version of this file [Date Created: 7 May 2014]

*/
package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DatabaseConnectors.SqlConnection;

/**
 * @author mw823
 *
 */
public class FilterProductsTest {

	/**
	
	 *Method/Test Description
	
	 *<p>Test(s)/User Story that it satisfies
	
	 *@throws java.lang.Exception
	
	 *[If applicable]@see [Reference URL OR Class#Method]
	
	 *<p> Date Modified: 7 May 2014
	
	 */
	
	private static SqlConnection productsDatabase;
	
	/**
	 * Creates an instance of SqlConnection before the tests are performed
	 */
	@Before
	public  void setup(){
		productsDatabase = new SqlConnection();
		productsDatabase.openConnection();
	}
	
	@After
	public  void setup2(){
		productsDatabase.closeConnection();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
