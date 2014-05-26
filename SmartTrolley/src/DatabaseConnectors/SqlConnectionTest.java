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
 *         Test case for testing the SqlConnection
 * 
 */
public class SqlConnectionTest {

	private static SqlConnection productsDatabase;
	private ObservableList<Product> products;
	private ObservableList<Product> offers;
	private ObservableList<String> categories;

	public String query;

	/**
	 * Creates an instance of SqlConnection before the tests are performed
	 */
	@Before
	public void setup() {
		productsDatabase = new SqlConnection();
		productsDatabase.openConnection();
	}

	@After
	public void setup2() {
		productsDatabase.closeConnection();
	}

	/**
	 * Tests for an instance of connection and that the connection is not closed
	 * 
	 * @throws SQLException
	 */

	@Test
	public void connectionOpenTest() throws SQLException {

		assertFalse(productsDatabase.connection == null);
		assertFalse(productsDatabase.connection.isClosed());

	}

	/**
	 * Sends and example query to the database Checks that the database returns
	 * something then tests that the returned values are correct for the sent
	 * query
	 * 
	 * @throws SQLException
	 */
	@Test
	public void sendQueryTest() throws SQLException {

		SmartTrolleyPrint.print("sendQueryTest Start ---------------\n");

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
		assert (product.getPrice() == 4.75);
	}
	
	/**
	 * Test getSpecificCategoryNumber returns the number of a category when given the name
	 */
	@Test
	public void getSpecificCategoryNumberTest(){
		SmartTrolleyPrint
		.print("\n getSpecificCategoryNumberTest Start ---------------\n");
		
		String catNumber = productsDatabase.getSpecificCategoryNumber("All");
		
		assertNotNull(catNumber);
		assertEquals(catNumber, "1");
	}
	
	/**
	 * Test that the database returns the correct information corresponding to a
	 * product when queried by name
	 */
	@Test
	public void getProductByNameTest() {
		SmartTrolleyPrint
				.print("\n getProductByNameTest Start ---------------\n");

		// retrieve results from server
		Product product = productsDatabase.getProductByName("Cravendale Pure Whole Milk (2L)");

		assertEquals(product.getId(), 2);
		
		assertEquals(product.getName(), "Cravendale Pure Whole Milk (2L)");
		assertEquals(product.getImage(),
				"img/SampleProducts/cravendale_2L_milk.jpg");
		assert(product.getPrice() == 3.99);
	}


	/**
	 * Tests that the getListOfProducts() returns a full list of the product
	 * data from the database by printing it to the console and manually
	 * comparing it.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void getListOfProductsTest() throws SQLException {
		SmartTrolleyPrint
				.print("\n getListOfProductsTest Start ---------------\n");

		products = productsDatabase.getListOfProducts();

		assertNotNull(products);
		Product product;
		int i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyPrint.print(product.getId() + "  " + product.getName()
					+ "  " + product.getImage() + "  " + product.getPrice());
			i++;
		}
	}

	/**
	 * Test that getListOfFavourites by sending a category number
	 * 
	 * first test - "1" should return all favourites
	 * 
	 * second test - "3" should return all favourties in that category.
	 */
	@Test
	public void getListOfFavouritesTest() {
		SmartTrolleyPrint
				.print("\n getListOfFavouritesTest Start ---------------\n");

		SmartTrolleyPrint.print("First Test");
		products = productsDatabase.getListOfFavourites("1");
		assertNotNull(products);
		Product product;
		int i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyPrint.print(product.getId() + "  " + product.getName()
					+ "  " + product.getImage() + "  " + product.getPrice()
					+ "  " + product.getOfferPrice());
			i++;
		}

		SmartTrolleyPrint.print("Second Test");
		products = productsDatabase.getListOfFavourites("3");
		i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyPrint.print(product.getId() + "  " + product.getName()
					+ "  " + product.getImage() + "  " + product.getPrice()
					+ "  " + product.getOfferPrice());
			i++;
		}

	}

	/**
	 * Test getSpecificProduct method by sending a column name and a value and
	 * should return a result
	 */
	public void getSpecificProductTest() {
		SmartTrolleyPrint
				.print("\n getSpecificProductTest Start ---------------\n");

		Product product;

		product = productsDatabase.getSpecificProduct("productID", "5", "1");

		assertEquals(product.getId(), 5);
		assertEquals(product.getName(), "Innocent Noodle Pot");
		assertEquals(product.getImage(),
				"img/SampleProducts/innocent_noodle_pot.jpg");
		assert (product.getPrice() == 6.99);
	}

	/**
	 * Test getList() to ensure it returns the contents of a List
	 */
	@Test
	public void getListTest() {
		SmartTrolleyPrint.print("\n getListTest Start ---------------\n");

		products = productsDatabase.getList(1);
		assertNotNull(products);
		Product product;
		int i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyPrint.print(product.getId() + "  " + product.getName()
					+ "  " + product.getImage() + "  " + product.getPrice());
			i++;
		}
	}

	/**
	 * Test getListByCategory to ensure that it returns a filtered version of a
	 * list by a selected category
	 */
	@Test
	public void getListByCategoryTest() {
		SmartTrolleyPrint
				.print("\n getListByCategoryTest Start ---------------\n");

		products = productsDatabase.getListByCategory(1, "4");
		assertNotNull(products);
		Product product;
		int i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyPrint.print(product.getId() + "  " + product.getName()
					+ "  " + product.getImage() + "  " + product.getPrice());
			i++;
		}

	}

	/**
	 * Test that the getListOfOffers() returns the full list of offers from the
	 * database by printing it to the console and then comparing it.
	 */
	@Test
	public void getListOfOffersTest() {
		SmartTrolleyPrint
				.print("\n getListOfOffersTest Start ---------------\n");

		offers = productsDatabase.getListOfOffers();
		assertNotNull(offers);
		Product product;
		int j = 0;
		while (j < offers.size()) {

			product = offers.get(j);

			SmartTrolleyPrint.print(product.getId() + "  " + product.getName()
					+ "  " + product.getImage() + "  " + product.getPrice()
					+ "  " + product.getOfferPrice() + "  "
					+ product.getSavings());

			j++;
		}

	}

	/**
	 * Test that gets the getProductsWithinSpecificCategory(String) returns a
	 * list of products from the database that correspond to a category request
	 */
	@Test
	public void getProductsWithinSpecificCategoryTest() {

		SmartTrolleyPrint
				.print("\n getProducsWithinSpecificCategoryTest Start ---------------\n");

		products = productsDatabase.getProductsWithinSpecificCategory("2");

		assertNotNull(products);

		Product product;
		int i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyPrint.print(product.getId() + "  " + product.getName()
					+ "  " + product.getImage() + "  " + product.getPrice());
			i++;
		}
	}

	/**
	 * Test that the getListOfCategories returns the correct list of categories
	 * from the database
	 */
	@Test
	public void getListOfCategoriesTest() {
		SmartTrolleyPrint
				.print("\n getListOfCategoriesTest Start ---------------\n");
		categories = productsDatabase.getListOfCategories();

		String category;

		int i = 0;
		while (i < categories.size()) {

			category = categories.get(i);
			SmartTrolleyPrint.print(category);

			i++;
		}
	}

	/**
	 * Test that the getOfferByCatergory returns a filtered list of the offers available
	 */
	
	@Test
	public void getOffersByCategoryTest(){
		SmartTrolleyPrint
		.print("\n getOffersByCategoryTest Start ---------------\n");
		
		products = productsDatabase.getOfferByCategory("4");
		
		assertNotNull(products);
		Product product;
		int i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyPrint.print(product.getId() + "  " + product.getName()
					+ "  " + product.getImage() + "  " + product.getPrice() + " " + product.getOfferPrice());
			i++;
		}
		
		
	}
	
	/**
	 * Tests that the connection closes correctly
	 * 
	 * @throws SQLException
	 */
	@Test	public void connectionCloseTest() throws SQLException {

		productsDatabase.closeConnection();

		assertTrue(productsDatabase.connection.isClosed());

	}

}
