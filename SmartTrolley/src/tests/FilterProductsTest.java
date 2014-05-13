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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smarttrolleygui.Product;

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
	private Product product;
	
	
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
	public void test() throws SQLException {
		
					product = new Product();
		
							
					System.out.println(product.getId());
					System.out.println(product.getName());
					System.out.println(product.getPrice());
					
	}

}
