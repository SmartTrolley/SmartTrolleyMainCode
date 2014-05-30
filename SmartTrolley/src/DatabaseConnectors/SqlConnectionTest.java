package DatabaseConnectors;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Printing.SmartTrolleyPrint;

import smarttrolleygui.Product;

import java.sql.*;

import javafx.collections.ObservableList;

/**
 * @author Thomas & Sam
 * 
 * Test case for testing the SqlConnection
 * 
 */
public class SqlConnectionTest {

	private static SqlConnection productsDatabase; 
	private ObservableList<Product> products;
	private ObservableList<Product> offers;
	
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
	public  void tearDown(){
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
	
		query = "SELECT * FROM products WHERE Name = 'Ariel 3in1 Pods Colour & Style'";
		
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
		assertEquals(product.getName(), "Ariel 3in1 Pods Colour & Style");		
		assertEquals(product.getImage(), "img/SampleProducts/ariel.jpg");
		assert(product.getPrice() == 4.75);		
	}
	
	/**
	 * Tests that the database returns the correct information corresponding to a product when queried by name
	 */
	@Test
	public void getProductByNameTest(){
		
		
		// retrieve results from server
		Product product = productsDatabase.getProductByName("Cravendale Pure Whole Milk (2L)");
		
		assertEquals(product.getId(), 2);
		assertEquals(product.getName(), "Cravendale Pure Whole Milk (2L)");		
		assertEquals(product.getImage(), "img/SampleProducts/cravendale_2L_milk.jpg");
		assert(product.getPrice() == 2.19);	
	}
	
	/**
	 * Tests that the getListOfProducts() returns a full list of the product data 
	 * from the database by printing it to the console and manually comparing it.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void getListOfProductsTest() throws SQLException{
		
	products = productsDatabase.getListOfProducts();
	Product product;
	int i = 0;
		while(i<products.size()){
			
			product = products.get(i);
			
			SmartTrolleyPrint.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice());
			i++;
		}
	}
	
	/**
	 * Tests that the correct information is returned for an example product search
	 */
	@Test
	public void getSpecificProductTest(){
		
		Product product;
		
		product = productsDatabase.getSpecificProduct("ProductID","5");
		
		assertEquals(product.getId(), 5);
		assertEquals(product.getName(), "Innocent Vietnamese Noodles Pot");
		assertEquals(product.getImage(), "img/SampleProducts/innocent_noodle_pot.jpg");
		assert(product.getPrice() == 3.88);
	}
	
	/**
	 * Tests that the correct info is returned from a specified list and column
	 * @throws SQLException 
	 */
//	@Test
//	public void getSpecificDataTest() throws SQLException{
//
//		Product product = null;
//		ResultSet results;
//		
//		//product = productsDatabase.getSpecificData("products","ProductID", "5");
//		//assertEquals("Innocent Vietnamese Noodles Pot", product.getName());
//		
//		results = productsDatabase.getSpecificData("products","ProductID", "5");
//		
//		product.setName(results.getString("Name"));
//		
//		assertEquals("Innocent Vietnamese Noodles Pot", product.getName());
//	}
	
	/**
	 * Test that the getListOfOffers() returns the full list of offers
	 * from the database by printing it to the console and then comparing it.
	 */
	@Test
	public void getListOfOffersTest(){
		
		offers = productsDatabase.getListOfOffers();
		
		Product product;
		int j = 0;
		while(j<offers.size()){
			
			product = offers.get(j);
			
			SmartTrolleyPrint.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + "  " + product.getOfferPrice() + "  " + product.getSavings());
					
			j++;
		}
		
	}
	
	
	/**
	 * Tests that the connection closes correctly
	 */
	@Test
	public void connectionCloseTest(){
		
		productsDatabase.closeConnection();
	
		try {
			assertTrue(productsDatabase.connection.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 */
	@Test
	public void createProductTest(){
		SqlConnection sqlConnector = new SqlConnection();
		
		int test = sqlConnector.createNewProduct("TestProduct", 1);	
		
		SmartTrolleyPrint.print(test);
		
		sqlConnector.deleteLastProduct();
	}

}
