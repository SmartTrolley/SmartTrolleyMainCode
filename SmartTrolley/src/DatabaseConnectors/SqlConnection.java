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

import Printing.SmartTrolleyPrint;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import smarttrolleygui.Offer;
import smarttrolleygui.Product;
import smarttrolleygui.SmartTrolleyGUI;

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
	public ObservableList<Product> getProductByName(String productName) {

		openConnection();

		String query = "SELECT * FROM products WHERE Name = '" + productName
				+ "';";

		try {

			ResultSet results = sendQuery(query);

			while (results.next()) {
				storeProductDetails(results);
			}
			closeConnection();
			return products;

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

		products = FXCollections.observableArrayList();
		String query = "SELECT * FROM products;";

		try {
			ResultSet results = sendQuery(query);
			while (results.next()) {
				storeProductDetails(results);
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

		products = FXCollections.observableArrayList();

		String query = null;

		if (category == "1") {
			query = "SELECT * FROM products WHERE IsFavourite = 1;";
		} else {
			query = "SELECT * FROM products WHERE IsFavourite = 1 AND CategoryID = '"
					+ category + "';";
		}

		try {
			ResultSet results = sendQuery(query);
			while (results.next()) {
				storeProductDetails(results);
			}
			closeConnection();
			return products;
		} catch (SQLException e) {
			System.out.println("Product could not be found");
			return null;
		}
	}

	/**
	 * 
	 * @param criteria
	 * @param value
	 * @return product
	 */
	public Product getSpecificProduct(String criteria, String value) {

		Product product = new Product();

		openConnection();

		String query = null;
		query = "Select * From products where " + criteria + " = " + value
				+ ";";

		try {
			ResultSet results = sendQuery(query);

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
	public ObservableList<Product> getProductsWithinSpecificCategory(
			String Number) {

		openConnection();

		products = FXCollections.observableArrayList();

		String query = "SELECT * FROM products WHERE CategoryID = " + Number
				+ ";";

		try {
			ResultSet results = sendQuery(query);

			if (isResultSetEmpty(results) == false) {

				SmartTrolleyPrint.print("Results found");
				results.absolute(1);

				while (results.next()) {
					storeProductDetails(results);
				}
				SmartTrolleyPrint.print("Results stored");

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
	 * Anytime the results of a query need to store a product, this method is
	 * called.
	 * 
	 * @param results
	 * @return
	 * @throws SQLException
	 */
	public ObservableList<Product> storeProductDetails(ResultSet results)
			throws SQLException {

		Product product = new Product();

		// get id
		product.setId(results.getInt("ProductID"));

		// get Name
		product.setName(results.getString("Name"));

		// get Image
		product.setImage(results.getString("Image"));

		// get Price
		product.setPrice(results.getFloat("Price"));

		products.add(product);

		return products;
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

			while (results.next()) {

				Offer offer = new Offer();
				Product product = new Product();
				product = productsDatabase.getSpecificProduct("ProductID",
						String.valueOf(results.getInt("ProductId")));

				if (!(product == null)) {
					// get Offer id
					offer.setOfferId(results.getInt("OfferID"));

					// get Product id
					offer.setProductId(results.getInt("ProductID"));

					// get Price
					offer.setOfferPrice(results.getFloat("OfferPrice"));
					product.setOfferPrice(results.getFloat("OfferPrice"));

					float savings = product.getPrice()
							- product.getOfferPrice();

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

	public ObservableList<Product> getListByCategory(int listID,
			String categoryNumber) {
		openConnection();

		products = FXCollections.observableArrayList();

		String query = "SELECT * FROM lists_products WHERE listID = "
				+ listID;
		SmartTrolleyPrint.print("query is: " + query);

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
				query = "SELECT * FROM products WHERE ProductID = '"
						+ productIDsInList.getInt("ProductID")
						+ "' AND CategoryID = '" + categoryNumber + "';";
				SmartTrolleyPrint.print("query is: " + query);
				listProducts = sendQuery(query);
				SmartTrolleyPrint.print("Query Sent");

				Product product = new Product();
				SmartTrolleyPrint.print("Initializing Product");

				boolean emptySet = isResultSetEmpty(listProducts);

				if (!emptySet) {

					listProducts.absolute(1);

					do {

						SmartTrolleyPrint.print("Found Item to be stored");

						storeProductDetails(listProducts);

						SmartTrolleyPrint.print("Product Stored");

						SmartTrolleyPrint.print(product.getId() + "  "
								+ product.getName() + "  " + product.getImage()
								+ "  " + product.getPrice());
					} while (listProducts.next());
				} else {
					SmartTrolleyPrint
							.print("empty result, moving to next item");
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
	 * Checks to see if the result set is empty
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
			SmartTrolleyPrint
					.print("Problem parsing ResultSet, probably empty");
		}

		if (empty) {
			// Empty result set
		}
		return empty;
	}

	/**
	 * Retrieves the list items from the SQL server
	 * <p>
	 * User can view list of lists
	 * 
	 * @param listID
	 * @return <p>
	 *         Date Modified: 9 May 2014
	 */
	public ObservableList<Product> getList(int listID) {
		openConnection();

		products = FXCollections.observableArrayList();

		String query = "SELECT * FROM lists_products WHERE listID = "
				+ listID;
		SmartTrolleyPrint.print("query is: " + query);

		ResultSet productIDsInList = null;

		try {
			productIDsInList = sendQuery(query);

		} catch (SQLException e) {
			System.out.println("lists could not be found");

		}

		try {

			ResultSet listProducts;

			while (productIDsInList.next()) {

				query = "SELECT * FROM products WHERE ProductID = "
						+ productIDsInList.getInt("ProductID");
				SmartTrolleyPrint.print("query is: " + query);

				listProducts = sendQuery(query);

				SmartTrolleyPrint.print("Query Sent");

				Product product = new Product();
				SmartTrolleyPrint.print("Initializing Product");

				SmartTrolleyPrint.print(isResultSetEmpty(listProducts));
				listProducts.absolute(1);
				SmartTrolleyPrint.print("Row Size is = "
						+ listProducts.getRow());

				storeProductDetails(listProducts);

				SmartTrolleyPrint.print("Product Stored");

				SmartTrolleyPrint.print(product.getId() + "  "
						+ product.getName() + "  " + product.getImage() + "  "
						+ product.getPrice());

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
		openConnection();

		products = FXCollections.observableArrayList();

		String query = "SELECT * FROM offers";
		SmartTrolleyPrint.print("query is: " + query);

		ResultSet productIDsInOffer = null;

		try {
			productIDsInOffer = sendQuery(query);
		} catch (SQLException e) {
			System.out.println("lists could not be found");
		}

		try {
			ResultSet offerProducts = null;

			while (productIDsInOffer.next()) {

				System.out.println(productIDsInOffer.getInt("ProductID"));
				query = "SELECT * FROM products WHERE ProductID = '"
						+ productIDsInOffer.getInt("ProductID")
						+ "' AND CategoryID = '" + categoryNumber + "';";
				SmartTrolleyPrint.print("query is: " + query);
				offerProducts = sendQuery(query);
				SmartTrolleyPrint.print("Query Sent");

				Product product = new Product();
				Offer offer = new Offer();
				SmartTrolleyPrint.print("Initializing Product");
				boolean emptySet = isResultSetEmpty(offerProducts);

				if (!emptySet) {

					offerProducts.absolute(1);

					do {

						SmartTrolleyPrint.print("Found Item to be stored");

						storeProductDetails(offerProducts);

						// get Offer id
						offer.setOfferId(productIDsInOffer.getInt("OfferID"));

						// get Product id
						offer.setProductId(offerProducts.getInt("ProductID"));

						// get Price
						offer.setOfferPrice(offerProducts
								.getFloat("OfferPrice"));
						product.setOfferPrice(offerProducts
								.getFloat("OfferPrice"));

						float savings = product.getPrice()
								- product.getOfferPrice();

						product.setSavings(savings);

						SmartTrolleyPrint.print("Product Stored");

						SmartTrolleyPrint.print(product.getId() + "  "
								+ product.getName() + "  " + product.getImage()
								+ "  " + product.getPrice());
					} while (offerProducts.next());
				} else {
					SmartTrolleyPrint
							.print("empty result, moving to next item");
				}
			}

			closeConnection();
			return products;

		} catch (SQLException e) {
			System.out.println("Product could not be found");
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

		SmartTrolleyPrint.print(query);
		try {
			preparedStatement = null;
			SmartTrolleyPrint
					.print("Reset preparedStatment, preparing to give it information");
			preparedStatement = connection.prepareStatement(query);
			SmartTrolleyPrint
					.print("Statement now prepared, now begining to execute");
			statementExecuted = preparedStatement.execute();

		} catch (SQLException ex) {
			SmartTrolleyPrint
					.print("Cannot execute statement due to unknown error");
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
