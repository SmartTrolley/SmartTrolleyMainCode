/**
 * SmartTrolley
 * 
 * This file tests for list deletion - Inspired from Alasdair's spike
 * 
 * @author Thomas
 * @author Sam
 * @author V2.1 Alick & Prashant [boolean class added for ResultSet testing]
 *         [also added execute statement]
 * @author Checked By: Checker(s) fill here
 * 
 * @version V2.1 [Date Created: 3 May 2014]
 */
package DatabaseConnectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import smarttrolleygui.Offer;
import smarttrolleygui.Product;
import smarttrolleygui.SmartTrolleyGUI;
import toolBox.SmartTrolleyToolBox;

public class SqlConnection {

	PreparedStatement preparedStatement;
	private static SqlConnection productsDatabase;

	private static final String IP = "79.170.44.157";
	private static final String USERNAME = "cl36-st";
	private static final String PASSWORD = "Smarttrolley";

	private ObservableList<Product> products;
	private ObservableList<Product> offers;
	private ObservableList<String> categories;

	private String url;
	Connection connection;

	/**
	 * Method that receives relevant connection info
	 */
	public SqlConnection() {

		try {
			Class.forName("java.sql.DriverManager");
		} catch (ClassNotFoundException e) {
			System.out.println("couldnt launch sql driver");
		}

		compileUrl();
	}

	/**
	 * Opens a connection (if not already open) for sending mysql queries to the
	 * database
	 * 
	 * @throws SQLException
	 */
	public void openConnection() {

		try {
			connection = null;
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		} catch (SQLException ex) {

			System.out.println("Connection failed to open");

		}
	}

	/**
	 * Send a query to the predefined database and returns the results returned
	 * by the server
	 * 
	 * @param query
	 * @return results
	 * @throws SQLException
	 */
	public ResultSet sendQuery(String query) throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery(query);

		return results;
	}

	/**
	 * Executes SQL query that returns the information for a particular product
	 * (by name)
	 * 
	 * @param productName
	 * @return product
	 */
	public Product getProductByName(String productName) {

		openConnection();

		Product product = new Product();

		String query = "SELECT * FROM products WHERE Name = '" + productName + "';";

		try {

			ResultSet results = sendQuery(query);

			while (results.next()) {
				product = createProductFromResultSet(results);
			}
			closeConnection();
			return product;

		} catch (SQLException e) {
			closeConnection();
			System.out.println("Product could not be found");
			return null;
		}

	}

	/**
	 * 
	 * @return products
	 * @throws SQLException
	 * 
	 */
	public ObservableList<Product> getListOfProducts() {

		openConnection();
		Product product = new Product();

		products = FXCollections.observableArrayList();
		String query = "SELECT * FROM products;";

		try {
			ResultSet results = sendQuery(query);
			while (results.next()) {
				product = createProductFromResultSet(results);
				products.add(product);
			}
			closeConnection();
			return products;
		} catch (SQLException e) {
			System.out.println("Product could not be found");
			return null;
		}
	}

	/**
	 * Returns either the entire list of favourites, or a version filtered by
	 * category
	 * 
	 * @param category
	 * @return products
	 */
	public ObservableList<Product> getListOfFavourites(String category) {

		openConnection();
		Product product = new Product();

		products = FXCollections.observableArrayList();

		String query = null;

		if (category == "1") {
			query = "SELECT * FROM products WHERE IsFavourite = 1;";
		} else {
			query = "SELECT * FROM products WHERE IsFavourite = 1 AND CategoryID = '" + category + "';";
		}

		try {
			ResultSet results = sendQuery(query);
			while (results.next()) {
				product = createProductFromResultSet(results);
				products.add(product);
			}
			closeConnection();
			return products;
		} catch (SQLException e) {
			System.out.println("Product could not be found");
			return null;
		}
	}

	/**
	*Searches database for product entered into the TextField.
	*<p>User is able to search for product
	*@param event
	* @return list of products matching search term
	*@throws SQLException
	*<p> Date Modified: 30 May 2014
	*/
	public ObservableList<Product> wildcardSearchForProduct(String searchString) throws SQLException {

		String query;
		ResultSet resultSet = null;
		openConnection();
		ObservableList<Product> products = FXCollections.observableArrayList();

		SmartTrolleyToolBox.print(searchString);

		query = "SELECT * FROM products WHERE name LIKE '" + searchString + "%';";

		try {
			resultSet = sendQuery(query);
		} catch (SQLException e) {
			SmartTrolleyToolBox.print("unable to send query");
		}

		while (resultSet.next()) {

			Product product = createProductFromResultSet(resultSet);

			products.add(product);
		}

		closeConnection();

		return products;
	}

	/**
	* This method creates and returns a product from a ResultSet passed in
	*@param resultSet
	*@return product - The product created
	*@throws SQLException
	*<p> Date Modified: 3 Jun 2014
	*/
	private Product createProductFromResultSet(ResultSet resultSet) throws SQLException {
		
		Product product = new Product();

		SmartTrolleyToolBox.print(resultSet.getString("Name"));
		// get id
		product.setId(resultSet.getInt("ProductID"));

		// get Name
		product.setName(resultSet.getString("Name"));

		// get Image
		product.setImage(resultSet.getString("Image"));

		// get Price
		product.setPrice(resultSet.getFloat("Price"));

		// get Favorite status
		product.setFavorite(resultSet.getInt("IsFavourite"));
		return product;
	}

	/**
	 * 
	 * @param criteria
	 * @param value
	 * @return product
	 */
	public Product getSpecificProduct(String criteria, String value, String category) {

		Product product = new Product();

		openConnection();

		String query = null;

		SmartTrolleyToolBox.print("category = " + category);

		if (category == "1") {
			query = "Select * From products where " + criteria + " = " + value + ";";
		} else if (!(category == "1")) {
			query = "Select * From products where " + criteria + " = " + value + " AND CategoryID = " + category + ";";
		}

		SmartTrolleyToolBox.print(query);

		try {
			ResultSet results = sendQuery(query);

			boolean emptySet = isResultSetEmpty(results);

			if (!(emptySet)) {
				results.absolute(1);

				do {
					product = createProductFromResultSet(results);

				} while (results.next());
				closeConnection();
				return product;
			} else {
				closeConnection();
				return null;
			}
		} catch (SQLException e) {
			closeConnection();
			System.out.println("Product could not be found");
			return null;
		}

	}

	/**
		 * 
		 */
	public String getSpecificCategoryNumber(String value) {

		String categoryNumber = null;

		openConnection();

		String query = "Select * From categories where name = '" + value + "';";

		try {
			ResultSet results = sendQuery(query);

			while (results.next()) {
				categoryNumber = results.getString("CategoryID");

			}
		} catch (SQLException e) {

			System.out.println("Product could not be found");
			System.out.println(e.getMessage());
		}

		closeConnection();
		return categoryNumber;
	}

	/**
	 * 
	 * @param List
	 * @param Number
	 * @return
	 */
	public ObservableList<Product> getProductsWithinSpecificCategory(String Number) {

		openConnection();
		Product product = new Product();

		products = FXCollections.observableArrayList();

		String query = "SELECT * FROM products WHERE CategoryID = " + Number + ";";

		try {
			ResultSet results = sendQuery(query);

			if (isResultSetEmpty(results) == false) {

				SmartTrolleyToolBox.print("Results found");
				results.absolute(1);

				do {
					product = createProductFromResultSet(results);
					products.add(product);
				} while (results.next());
				SmartTrolleyToolBox.print("Results stored");

				closeConnection();
				return products;

			} else {
				closeConnection();
				System.out.println("No result");
				return null;
			}

		} catch (SQLException e) {
			closeConnection();
			System.out.println("Product could not be found");
			return null;
		}
	}	

	/**
	 * When called, this method will return the list of offers and store them as
	 * a type offer.
	 * 
	 * @return offers
	 */
	public ObservableList<Product> getListOfOffers() {

		productsDatabase = new SqlConnection();

		openConnection();

		offers = FXCollections.observableArrayList();

		String query = "SELECT * FROM offers;";

		try {
			ResultSet results = sendQuery(query);

			Offer offer = new Offer();
			Product product = new Product();

			while (results.next()) {

				product = productsDatabase.getSpecificProduct("productID", String.valueOf(results.getInt("ProductId")), "1");

				if (!(product == null)) {
					// get Offer id
					offer.setOfferId(results.getInt("OfferID"));

					// get Product id
					offer.setProductId(results.getInt("ProductID"));

					// get Price
					offer.setOfferPrice(results.getFloat("OfferPrice"));
					product.setOfferPrice(results.getFloat("OfferPrice"));

					float savings = product.getPrice() - product.getOfferPrice();

					product.setSavings(savings);

					offers.add(product);
				} else {
					return null;
				}
			}

			closeConnection();
			return offers;

		} catch (SQLException e) {

			System.out.println("Offers could not be found");
			return null;
		}

	}

	/**
	 * When called, it will return a the customers list filtered by a selected category
	 * @param listID
	 * @param categoryNumber
	 * @return
	 */
	public ObservableList<Product> getListByCategory(int listID, String categoryNumber) {
		openConnection();

		Product product = new Product();
		products = FXCollections.observableArrayList();

		String query = "SELECT * FROM lists_products WHERE listID = " + listID;
		SmartTrolleyToolBox.print("query is: " + query);

		ResultSet productIDsInList = null;

		try {
			productIDsInList = sendQuery(query);

		} catch (SQLException e) {
			System.out.println("lists could not be found");

		}

		try {

			ResultSet listProducts = null;

			while (productIDsInList.next()) {

				System.out.println(productIDsInList.getInt("ProductID"));
				query = "SELECT * FROM products WHERE ProductID = '" + productIDsInList.getInt("ProductID") + "' AND CategoryID = '" + categoryNumber + "';";
				SmartTrolleyToolBox.print("query is: " + query);
				listProducts = sendQuery(query);
				SmartTrolleyToolBox.print("Query Sent");

				SmartTrolleyToolBox.print("Initializing Product");

				boolean emptySet = isResultSetEmpty(listProducts);

				if (!emptySet) {

					listProducts.absolute(1);

					do {

						SmartTrolleyToolBox.print("Found Item to be stored");

						product = createProductFromResultSet(listProducts);
						products.add(product);

						SmartTrolleyToolBox.print("Product Stored");

						SmartTrolleyToolBox.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice());
					} while (listProducts.next());
				} else {
					SmartTrolleyToolBox.print("empty result, moving to next item");
				}
			}
		} catch (SQLException e) {
			System.out.println("Product could not be found");
			return null;
		}

		closeConnection();
		return products;

	}

	/**
	 * Method returns the list of category names. Could be modified to return a
	 * list of Categories if needed in future
	 * 
	 * @return
	 */
	public ObservableList<String> getListOfCategories() {

		productsDatabase = new SqlConnection();

		openConnection();

		categories = FXCollections.observableArrayList();

		String query = "SELECT * FROM categories;";

		try {
			ResultSet results = sendQuery(query);

			while (results.next()) {
				String category = results.getString("Name");
				categories.add(category);
			}
			closeConnection();
			return categories;
		} catch (SQLException e) {
			System.out.println("Categories could not be found");
			return null;
		}
	}

	/**
	 * Checks to see if the result set is empty. Returns true if it is.
	 * <p>
	 * N/A
	 * 
	 * @param resultSet
	 * @return boolean empty
	 * @see 
	 *      [http://stackoverflow.com/questions/2938812/how-to-find-out-if-a-java
	 *      -resultset-obtained-is-empty]
	 *      <p>
	 *      Date Modified: 4 May 2014
	 */
	public static boolean isResultSetEmpty(ResultSet resultSet) {
		boolean empty = true;
		try {
			while (resultSet.next()) {
				// ResultSet processing here
				empty = false;
			}
		} catch (SQLException e) {
			SmartTrolleyToolBox.print("Problem parsing ResultSet, probably empty");
		}

		if (empty) {
			// Empty result set
		}
		return empty;
	}

	/**
	 * Retrieves the list items from the SQL server
	 * <p> User can view list of lists
	 * @param listID
	 * @return <p>
	 *         Date Modified: 9 May 2014
	 */
	public ObservableList<Product> getList(int listID) {
		openConnection();

		products = FXCollections.observableArrayList();

		String query = "SELECT * FROM lists_products WHERE listID = " + SmartTrolleyGUI.getcurrentListID();
		SmartTrolleyToolBox.print("query is: " + query);

		ResultSet productIDsInList = null;

		try {
			productIDsInList = sendQuery(query);

		} catch (SQLException e) {
			System.out.println("lists could not be found");

		}

		try {

			ResultSet listProducts;

			while (productIDsInList.next()) {

				query = "SELECT * FROM products WHERE ProductID = " + productIDsInList.getInt("ProductID");
				SmartTrolleyToolBox.print("query is: " + query);

				listProducts = sendQuery(query);

				SmartTrolleyToolBox.print("Query Sent");

				Product product = new Product();
				SmartTrolleyToolBox.print("Initializing Product");

				SmartTrolleyToolBox.print(isResultSetEmpty(listProducts));

				listProducts.absolute(1);

				SmartTrolleyToolBox.print("Row Size is = " + listProducts.getRow());

				product = createProductFromResultSet(listProducts);
				SmartTrolleyToolBox.print("Product Set");

				products.add(product);

				SmartTrolleyToolBox.print("Product Stored");

				SmartTrolleyToolBox.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice());

			}
		} catch (SQLException e) {
			System.out.println("Product could not be found");
			return null;
		}

		closeConnection();
		return products;

	}

	/**
	 * This class will filter the list by category and return what products in
	 * that list are in a selected category
	 * 
	 * @param listID
	 * @param categoryNumber
	 * @return products
	 */
	public ObservableList<Product> getOfferByCategory(String categoryNumber) {
		Offer offer = new Offer();
		Product product = new Product();
		productsDatabase = new SqlConnection();

		openConnection();

		offers = FXCollections.observableArrayList();

		String query = "SELECT * FROM offers;";

		try {
			ResultSet results = sendQuery(query);

			results.absolute(1);

			do {

				product = productsDatabase.getSpecificProduct("ProductID", String.valueOf(results.getInt("ProductId")), categoryNumber);

				if (!(product == null)) {

					// get Offer id
					offer.setOfferId(results.getInt("OfferID"));

					// get Product id
					offer.setProductId(results.getInt("ProductID"));

					// get Price
					offer.setOfferPrice(results.getFloat("OfferPrice"));
					product.setOfferPrice(results.getFloat("OfferPrice"));

					float savings = product.getPrice() - product.getOfferPrice();

					product.setSavings(savings);

					offers.add(product);
				} else {
					SmartTrolleyToolBox.print("No Offers in that category found");
				}

			} while (results.next());

			closeConnection();
			return offers;

		} catch (SQLException e) {
			closeConnection();
			System.out.println("Offers could not be found");
			System.out.println("SQL ERROR: " + e);
			return null;
		}

	}

	/**
	 * executes statement to SQL server, allow for creation and deleted of
	 * further lists and tables
	 * 
	 * @param query
	 * @return boolean statementExecuted Date Modified: 4 May 2014
	 */
	public boolean executeStatement(String query) {
		boolean statementExecuted = false;

		SmartTrolleyToolBox.print(query);
		try {
			preparedStatement = null;
			SmartTrolleyToolBox.print("Reset preparedStatment, preparing to give it information");
			preparedStatement = connection.prepareStatement(query);
			SmartTrolleyToolBox.print("Statement now prepared, now begining to execute");
			statementExecuted = preparedStatement.execute();

		} catch (SQLException ex) {
			SmartTrolleyToolBox.print("Cannot execute statement due to unknown error");
		}

		return statementExecuted;
	}

	/**
	 * provides public access to close the productsDatabase
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException ex) {
			System.out.println("Connection did not close properly");
		}
	}

	/**
	 * Compiles a valid URL from the connection info provided
	 */
	private void compileUrl() {
		// construct the url assuming use of mysql and the standard port.
		url = "jdbc:mysql://" + IP + "/" + USERNAME + "?";
	}

}
