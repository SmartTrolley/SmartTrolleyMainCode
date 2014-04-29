package DatabaseConnectors;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;

import smarttrolleygui.Product;

import java.sql.*;

/**
 * @author Thomas & Sam
 * 
 * Test case for testing the SqlConnection
 * 
 */
public class SqlConnectionTest {

	private static SqlConnection productsDatabase; 
	
	public String query;
	
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
	
	/**
	 * Tests for an instance of connection and that the connection is not closed
	 * @throws SQLException
	 */
	
	@Test
	public void connectionOpenTest() throws SQLException {		
		
		assertFalse(productsDatabase.connection == null);
		assertFalse(productsDatabase.connection.isClosed());

	}
	
	/**
	 * Sends and example query to the database
	 * Checks that the database returns something then tests that the returned values are correct for the sent query
	 * @throws SQLException
	 */
	@Test
	public void sendQueryTest() throws SQLException {
	
		query = "SELECT * FROM products WHERE Name = 'Ariel'";
		
		ResultSet results = productsDatabase.sendQuery(query);
		
		assertFalse(results == null);
		
		Product product = new Product();
		
		
		while (results.next()) {
			
			// get id
			product.setId(results.getInt("ProductID"));
			
			// get Name
			product.setName(results.getString("Name"));
			
			// get Image
			product.setImage(results.getString("Image"));
			
			// get Price
			product.setPrice(results.getFloat("Price"));
			
		}
		
		assertEquals(product.getId(), 1);
		assertEquals(product.getName(), "Ariel");		
		assertEquals(product.getImage(), "img/SampleProducts/ariel.jpg");
		assert(product.getPrice() == 2.99);		
	}
	
	/**
	 * Test that the database returns the correct information corresponding to a product when queried by name
	 */
	@Test
	public void getProductByNameTest(){
		
		productsDatabase = new SqlConnection();
		
		// retrieve results from server
		Product product = productsDatabase.getProductByName("Cravendale 2L");
		
		assertEquals(product.getId(), 2);
		assertEquals(product.getName(), "Cravendale 2L");		
		assertEquals(product.getImage(), "img/SampleProducts/cravendale_2L_milk.jpg");
		assert(product.getPrice() == 3.99);	
	}
	
	
	/**
	 * Tests that the connection closes correctly
	 * @throws SQLException
	 */
	@Test
	public void connectionCloseTest() throws SQLException  {
		
		productsDatabase.closeConnection();
	
		assertTrue(productsDatabase.connection.isClosed());

	}

}
