package smarttrolleygui;

import static org.junit.Assert.*;

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
	
	/**
	 * Creates an instance of SqlConnection before the tests are performed
	 */
	@BeforeClass
	public static void Setup(){
		productsDatabase = new SqlConnection(ip, userName, password);
	}
	
	/**
	 * Tests for an instance of connection and that the connection is not closed
	 * @throws SQLException
	 */
	@Test
	public void connectionOpenTest() throws SQLException {		
				
		productsDatabase.openConnection();
		
		assertFalse(productsDatabase.connection == null);
		assertFalse(productsDatabase.connection.isClosed());

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
