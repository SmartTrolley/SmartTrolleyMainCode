package smarttrolleygui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.*;
import java.sql.*;

public class SqlConnectionTest {

	private static String ip = "79.170.44.157", userName = "cl36-st", password = "Smarttrolley";
	
	private static SqlConnection productsDatabase; 
	
	@BeforeClass
	public static void Setup(){
		productsDatabase = new SqlConnection(ip, userName, password);
	}
	
	@Test
	public void connectionOpenTest() throws SQLException {		
				
		productsDatabase.openConnection();
		
		assertFalse(productsDatabase.connection == null);
		assertFalse(productsDatabase.connection.isClosed());

	}
	
	@Test
	public void connectionCloseTest() throws SQLException  {
		
		productsDatabase.closeConnection();
	
		assertTrue(productsDatabase.connection.isClosed());

	}

}
