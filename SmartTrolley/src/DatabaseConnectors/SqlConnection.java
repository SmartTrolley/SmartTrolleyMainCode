package DatabaseConnectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Printing.SmartTrolleyPrint;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import smarttrolleygui.Category;
import smarttrolleygui.Offer;
import smarttrolleygui.Product;

/**
 * 
 * @author Thomas & Sam
 * Inspired from Alasdair's spike
 *
 */
public class SqlConnection {
	
	private static SqlConnection productsDatabase;

	public static final String ip = "79.170.44.157" ;
	public static final String userName = "cl36-st";
	public static final String password= "Smarttrolley";
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
	 * Opens a connection (if not already open) for sending mysql queries to the database
	 * @throws SQLException
	 */
	public void openConnection(){	

		try{
			connection = null;
			connection = DriverManager.getConnection(url, userName,password);
		} catch (SQLException ex) {
			
			System.out.println("Connection failed to open");
			
		}
	}
	
	/**
	 * Send a query to the predefined database and returns the results returned by the server
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
	 * Executes SQL query that returns the information for a particular product (by name)
	 * @param productName
	 * @return
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
	 * 
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
	 * 
	 */
	public String getSpecificCategory(String value){
		
		String categoryNumber = null;
		
		openConnection();
		
		String query = "Select * From categories where name = " + value + ";";
		System.out.println(query);
		
		try {
			ResultSet results = sendQuery(query);
			
			while (results.next()){
				categoryNumber = results.getString("CategoryID");

			}
		} catch (SQLException e) {
			
			System.out.println("Product could not be found");
		}
		
		closeConnection();
		return categoryNumber;
	}
	
	/**
	 * When called, this method will return the list of offers
	 * and store them as a type offer.
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
	 * Method returns the list of category names. Could be modified to return a list of Categories if needed in future
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
	 * provides public access to close the connection
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
		url = "jdbc:mysql://" + ip  + "/" + userName + "?";	
	}

	


}
