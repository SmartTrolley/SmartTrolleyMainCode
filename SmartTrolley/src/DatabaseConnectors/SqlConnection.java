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

import smarttrolleygui.Product;
import smarttrolleygui.SmartTrolleyGUI;

/**
 * SmartTrolley
 *
 * This file tests for list deletion
 * - Inspired from Alasdair's spike
 *
 * @author Thomas 
 * @author Sam 
 * @author V2.1 Alick & Prashant [boolean class added for ResultSet testing]
 * 								 [also added execute statement]
 * @author Checked By: Checker(s) fill here
 *
 * @version V2.1 [Date Created: 3 May 2014]
 */

public class SqlConnection {

	public static final String IP = "79.170.44.157";
	public static final String USERNAME = "cl36-st";
	public static final String PASSWORD = "Smarttrolley";
	private ObservableList<Product> products;

	private String url;
	Connection connection;
	PreparedStatement preparedStatement;

	/**
	 * Method that receives relevant productsDatabase info
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
	 * Opens a productsDatabase (if not already open) for sending mysql queries
	 * to the database
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
	*executes statement to SQL server, allow for creation and deleted of further
	*lists and tables
	*@param query
	*@return
	*<p> Date Modified: 4 May 2014
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
	 * Send a query to the predefined database and returns the results returned
	 * by the server
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet sendQuery(String query) throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery(query);

		return results;
	}

	/**
	*Checks to see if the result set is empty
	*<p>N/A
	*@param resultSet
	*@return
	*@see [http://stackoverflow.com/questions/2938812/how-to-find-out-if-a-java-resultset-obtained-is-empty]
	*<p> Date Modified: 4 May 2014
	*/
	public static boolean isResultSetEmpty(ResultSet resultSet) {
		boolean empty = true;
		try {
			while (resultSet.next()) {
				// ResultSet processing here
				empty = false;
			}
		} catch (SQLException e) {
			SmartTrolleyPrint.print("Problem parsing ResultSet, probably empty");
		}

		if (empty) {
			// Empty result set
		}
		return empty;
	}

	/**
	 * Executes SQL query that returns the information for a particular product
	 * (by name)
	 * 
	 * @param productName
	 * @return
	 */
	public Product getProductByName(String productName) {

		Product product = new Product();

		openConnection();

		String query = "SELECT * FROM products WHERE Name = '" + productName
				+ "';";

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

		} catch (SQLException e) {

			System.out.println("Product could not be found");

		}

		closeConnection();
		return product;
	}

	/**
	 * @return
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
			}
			closeConnection();
			return products;

		} catch (SQLException e) {

			System.out.println("Product could not be found");
			return null;
		}

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
	 * Compiles a valid URL from the productsDatabase info provided
	 */
	private void compileUrl() {
		// construct the url assuming use of mysql and the standard port.
		url = "jdbc:mysql://" + IP + "/" + USERNAME + "?";
	}

}
