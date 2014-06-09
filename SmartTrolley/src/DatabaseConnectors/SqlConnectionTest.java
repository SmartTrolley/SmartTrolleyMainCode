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

import smarttrolleygui.ListProduct;
import smarttrolleygui.Product;
import toolBox.SmartTrolleyToolBox;

/**
 * @author Thomas & Sam
 * @author V2.0 Prashant [Final Refactoring and commenting]
 * Test case for testing the SqlConnection
 * 
* @version V2.0 [Date Created: 6 Jun 2014] 
 * 
 */
public class SqlConnectionTest {

	private static SqlConnection productsDatabase;
	private ObservableList<ListProduct> products;
	private ObservableList<ListProduct> offers;

	public String query;

	/**
	* Creates an instance of SqlConnection before the tests are performed
	*<p> Date Modified: 6 Jun 2014
	*/
	@Before
	public void setup() {
		productsDatabase = new SqlConnection();
		productsDatabase.openConnection();
	}

	/**
	* THis method runs after every test, and cleans up after the test 
	* Closes the connection after all tests are run
	*<p> Date Modified: 6 Jun 2014
	*/
	@After
	public  void tearDown(){
		productsDatabase.closeConnection();
	}
	
	/**
	* Tests for an instance of connection and that the connection is not closed
	*@throws SQLException
	*<p> Date Modified: 6 Jun 2014
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
	*@throws SQLException
	*<p> Date Modified: 6 Jun 2014
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

		assertEquals(product.getID(), 1);
		assertEquals(product.getName(), "Ariel 3in1 Pods Colour & Style");

		assertEquals(product.getImage(), "img/SampleProducts/ariel.jpg");
		assert (product.getPrice() == 4.75);

	}

	/**
	* Tests that the database returns the correct category number when a category name is given
	*<p> Date Modified: 6 Jun 2014
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
	 * <p> Date Modified: 6 Jun 2014
	 */
	@Test
	public void getProductByNameTest() {
		SmartTrolleyToolBox.print("\n getProductByNameTest Start ---------------\n");

		// retrieve results from server
		Product product = productsDatabase.getProductByName("Cravendale Pure Whole Milk (2L)");

		assertEquals(product.getID(), 2);

		assertEquals(product.getName(), "Cravendale Pure Whole Milk (2L)");
		assertEquals(product.getImage(), "img/SampleProducts/cravendale_2L_milk.jpg");
		assert (product.getPrice() == 2.19);
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

		ObservableList<ListProduct> listProducts = productsDatabase.getListOfAllProducts();

		assertNotNull(listProducts);
		Product product;
		int i = 0;
		while (i < listProducts.size()) {

			product = listProducts.get(i);
			SmartTrolleyToolBox.print(product.getID() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice());
			i++;
		}
	}

	/**
	 * Test that getListOfFavourites by sending a category number
	 * first test - "1" should return all favourites
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

			SmartTrolleyToolBox.print(product.getID() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + "  " + product.getOfferPrice());
			i++;
		}

		SmartTrolleyToolBox.print("Second Test");
		products = productsDatabase.getListOfFavourites("3");
		i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyToolBox.print(product.getID() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + "  " + product.getOfferPrice());
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

		assertEquals(product.getID(), 5);
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

		products = productsDatabase.getListOfProductsInList(1);
		assertNotNull(products);
		Product product;
		int i = 0;
		while (i < products.size()) {

			product = products.get(i);

			SmartTrolleyToolBox.print(product.getID() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice());
			i++;
		}
	}

	/**
	 * Test getListByCategory to ensure that it returns a filtered version of a
	 * list by a selected category. This is printed out to the console and visually compared
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

			SmartTrolleyToolBox.print(product.getID() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice());
			i++;
		}

	}

	/**
	 * Test that the getListOfOffers() returns the full list of offers from the
	 * database by printing it to the console and then comparing it.
	 * <p> User Story: User views products
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

			SmartTrolleyToolBox.print(product.getID() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + "  " + product.getOfferPrice() + "  "
					+ product.getSavings());

			j++;
		}

	}

	/**
	* Test that the getOfferByCatergory returns a filtered list of the offers available.
	* This is then printed to the console and visually compared to the actual data
	* <p> User Story: User views products 
	*<p> Date Modified: 6 Jun 2014
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

			SmartTrolleyToolBox.print(product.getID() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + " " + product.getOfferPrice());

			i++;
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
	* Test that creates a new product, searches for it in the database, then prints the productid to the console
	* This value is then visually compared, and the product is deleted.
	*<p> Date Modified: 6 Jun 2014
	*/
	@Test
	public void createProductTest(){
		SqlConnection sqlConnector = new SqlConnection();
		
		int createdProductID = sqlConnector.createNewProduct("TestProduct", 1, null, 0, 1, 0);	
		
		SmartTrolleyToolBox.print(createdProductID);
		
		sqlConnector.deleteLastProduct();
	}

}
