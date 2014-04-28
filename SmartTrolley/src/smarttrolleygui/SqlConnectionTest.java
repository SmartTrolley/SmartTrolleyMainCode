package smarttrolleygui;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;
import java.sql.*;

/**
 * @author Thomas & Sam
 * 
 * Test case for testing the SqlConnection
 * 
 */
public class SqlConnectionTest {

	private static String ip = "79.170.44.157", userName = "cl36-st", password = "Smarttrolley";
	private static SqlConnection productsDatabase; 
	
	public String query;
	
	/**
	 * Creates an instance of SqlConnection before the tests are performed
	 */
	@Before
	public  void setup(){
		productsDatabase = new SqlConnection(ip, userName, password);
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
	
	
	@Test
	public void sendQueryTest() throws SQLException {

	
		query = "SELECT * FROM products WHERE Name = 'Ariel'";
		
		productsDatabase.sendQuery(query);
		assertFalse(productsDatabase.sendQuery(query) == null);
		
		/**
		while (productsDatabase.sendQuery(query).next()) {
			// get id
			product.setId(productsDatabase.sendQuery(query).getInt("ProductID"));

			// get Name
			product.setName(productsDatabase.sendQuery(query).getString("Name"));

			// get Image
			product.setImage(productsDatabase.sendQuery(query).getString("Image"));

			// get Price
			product.setPrice(productsDatabase.sendQuery(query).getFloat("Price"));
		**/
		
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
