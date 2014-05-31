package DatabaseConnectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smarttrolleygui.Product;
import toolBox.SmartTrolleyToolBox;

/**
 * @author Thomas & Sam
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

		SmartTrolleyToolBox.print("sendQueryTest Start ---------------\n");

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
	public void getSpecificCategoryNumberTest() {
		SmartTrolleyToolBox.print("\n getSpecificCategoryNumberTest Start ---------------\n");

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
		SmartTrolleyToolBox.print("\n getProductByNameTest Start ---------------\n");

		// retrieve results from server
		Product product = productsDatabase.getProductByName("Cravendale Pure Whole Milk (2L)");

		assertEquals(product.getId(), 2);

		assertEquals(product.getName(), "Cravendale Pure Whole Milk (2L)");
		assertEquals(product.getImage(), "img/SampleProducts/cravendale_2L_milk.jpg");
		assert (product.getPrice() == 3.99);
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
		SmartTrolleyToolBox.print("\n getListOfProductsTest Start ---------------\n");

		products = productsDatabase.getListOfProducts();

		assertNotNull(products);
		Product product;
		int i = 0;
		while (i < products.size()) {

			product = products.get(i);
			SmartTrolleyToolBox.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice());
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
		SmartTrolleyToolBox.print("\n getListOfFavouritesTest Start ---------------\n");

		SmartTrolleyToolBox.print("First Test");
		products = productsDatabase.getListOfFavourites("1");
		assertNotNull(products);
		Product product;
		int i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyToolBox.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + "  " + product.getOfferPrice());
			i++;
		}

		SmartTrolleyToolBox.print("Second Test");
		products = productsDatabase.getListOfFavourites("3");
		i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyToolBox.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + "  " + product.getOfferPrice());
			i++;
		}

	}

	/**
	 * Test getSpecificProduct method by sending a column name and a value and
	 * should return a result
	 */
	@Test
	public void getSpecificProductTest() {
		SmartTrolleyToolBox.print("\n getSpecificProductTest Start ---------------\n");

		Product product;

		product = productsDatabase.getSpecificProduct("productID", "5", "1");

		assertEquals(product.getId(), 5);
		assertEquals(product.getName(), "Innocent Vietnamese Noodles Pot");
		assertEquals(product.getImage(), "img/SampleProducts/innocent_noodle_pot.jpg");
		assert (product.getPrice() == 3.88);
	}

	/**
	 * Test getList() to ensure it returns the contents of a List
	 */
	@Test
	public void getListTest() {
		SmartTrolleyToolBox.print("\n getListTest Start ---------------\n");

		products = productsDatabase.getList(1);
		assertNotNull(products);
		Product product;
		int i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyToolBox.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice());
			i++;
		}
	}

	/**
	 * Test getListByCategory to ensure that it returns a filtered version of a
	 * list by a selected category
	 */
	@Test
	public void getListByCategoryTest() {
		SmartTrolleyToolBox.print("\n getListByCategoryTest Start ---------------\n");

		products = productsDatabase.getListByCategory(1, "4");
		assertNotNull(products);
		Product product;
		int i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyToolBox.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice());
			i++;
		}

	}

	/**
	 * Test that the getListOfOffers() returns the full list of offers from the
	 * database by printing it to the console and then comparing it.
	 */
	@Test
	public void getListOfOffersTest() {
		SmartTrolleyToolBox.print("\n getListOfOffersTest Start ---------------\n");

		offers = productsDatabase.getListOfOffers();
		assertNotNull(offers);
		Product product;
		int j = 0;
		while (j < offers.size()) {

			product = offers.get(j);

			SmartTrolleyToolBox.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + "  " + product.getOfferPrice() + "  "
					+ product.getSavings());

			j++;
		}

	}

	/**
	 * Test that the getOfferByCatergory returns a filtered list of the offers available
	 */

	@Test
	public void getOffersByCategoryTest() {
		SmartTrolleyToolBox.print("\n getOffersByCategoryTest Start ---------------\n");

		products = productsDatabase.getOfferByCategory("1");

		assertNotNull(products);
		Product product;
		int i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyToolBox.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + " " + product.getOfferPrice());

			i++;
		}
	}

	/**
	 * Tests that the connection closes correctly
	 * 
	 * @throws SQLException
	 */
	@Test
	public void connectionCloseTest() throws SQLException {

		productsDatabase.closeConnection();

		assertTrue(productsDatabase.connection.isClosed());

	}

}
