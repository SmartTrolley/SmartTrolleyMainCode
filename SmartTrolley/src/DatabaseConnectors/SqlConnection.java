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

		private static final String IP = "79.170.44.157" ;
		private static final String USERNAME = "cl36-st";
		private static final String PASSWORD= "Smarttrolley";
				
		private ObservableList<Product> products;
		private ObservableList<Product> offers;
		
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
		 * Opens a connection (if not already open) for sending mysql queries to the database
		 * @throws SQLException
		 */
		public void openConnection(){	

			try{
				connection = null;
				connection = DriverManager.getConnection(url, USERNAME,PASSWORD);
			} catch (SQLException ex) {
				
				System.out.println("Connection failed to open");
				
			}
		}
		
		/**
		 * Send a query to the predefined database and returns the results returned by the server
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
		 * Executes SQL query that returns the information for a particular product (by name)
		 * @param productName
		 * @return product
		 */
		public Product getProductByName(String productName) {
			
			Product product = new Product();
			
			openConnection();
			
			String query = "SELECT * FROM products WHERE Name = '" + productName+"';";
			
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
		 * 
		 * @param criteria
		 * @param value
		 * @return product
		 */
		public Product getSpecificProduct(String criteria, String value){
			
			Product product = new Product();
			
			openConnection();
			
			String query = "Select * From products where " + criteria +" = " + value + ";";
			
			try {
				ResultSet results = sendQuery(query);
				
				while (results.next()){
					
					//get id
					product.setId(results.getInt("ProductID"));
					
					//get Name
					product.setName(results.getString("Name"));
					
					//get Image
					product.setImage(results.getString("Image"));
					
					//get Price
					product.setPrice(results.getFloat("Price"));
				}
			} catch (SQLException e) {
				
				System.out.println("Product could not be found");
			}
			
			closeConnection();
			return product;
		}
		
	
		/**
		 * When called, this method will return the list of offers
		 * and store them as a type offer.
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
					
					// get Offer id
					offer.setOfferId(results.getInt("OfferID"));
					
					// get Product id
					offer.setProductId(results.getInt("ProductID"));
					
					product = productsDatabase.getSpecificProduct("ProductID", String.valueOf(results.getInt("ProductId")));
					
					// get Price
					offer.setOfferPrice(results.getFloat("OfferPrice"));
					product.setOfferPrice(results.getFloat("OfferPrice"));
					
					float savings = product.getPrice() - product.getOfferPrice();
					
					product.setSavings(savings);
					
					offers.add(product);
				}
				
				closeConnection();
				return offers;
				
			} catch (SQLException e) {
				
				System.out.println("Offers could not be found");
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
			SmartTrolleyToolBox
					.print("Problem parsing ResultSet, probably empty");
		}

		if (empty) {
			// Empty result set
		}
		return empty;
	}
	

	/**
	*Retrieves the list items from the SQL server
	*<p>User can view list of lists
	*@param listID
	*@return
	*<p> Date Modified: 9 May 2014
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

				// get id
				product.setId(listProducts.getInt("ProductID"));

				// get Name
				product.setName(listProducts.getString("Name"));

				// get Image
				product.setImage(listProducts.getString("Image"));

				// get Price
				product.setPrice(listProducts.getFloat("Price"));
				
				SmartTrolleyToolBox.print("Product Set");

				products.add(product);
				
				SmartTrolleyToolBox.print("Product Stored");

				SmartTrolleyToolBox.print(product.getId() + "  "
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
	 * executes statement to SQL server, allow for creation and deleted of
	 * further lists and tables
	 * 
	 * @param query
	 * @return boolean statementExecuted
	 *         Date Modified: 4 May 2014
	 */
	public boolean executeStatement(String query) {
		boolean statementExecuted = false;

		SmartTrolleyToolBox.print(query);
		try {
			preparedStatement = null;
			SmartTrolleyToolBox
					.print("Reset preparedStatment, preparing to give it information");
			preparedStatement = connection.prepareStatement(query);
			SmartTrolleyToolBox
					.print("Statement now prepared, now begining to execute");
			statementExecuted = preparedStatement.execute();

		} catch (SQLException ex) {
			SmartTrolleyToolBox
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
		//construct the url assuming use of mysql and the standard port.
		url = "jdbc:mysql://" + IP  + "/" + USERNAME + "?";	
	}


}
