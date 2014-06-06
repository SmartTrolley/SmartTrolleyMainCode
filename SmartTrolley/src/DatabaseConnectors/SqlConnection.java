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
import smarttrolleygui.ListProduct;
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

	private ObservableList<ListProduct> products;
	private ObservableList<ListProduct> offers;
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
			SmartTrolleyToolBox.print("couldnt launch sql driver");
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

			SmartTrolleyToolBox.print("Connection failed to open");

		}
	}

	//#################### OMID
	public void execute(String query) throws SQLException {
		
		Statement statement = connection.createStatement();
		statement.execute(query);
		
	}
	
	//Removes all product references to the lists (i.e. removes items within lists)
	public void removeAllProductsInTheLists() throws SQLException{
		execute("DELETE FROM lists_products");
	}
	
	public ResultSet getListByName(String name) throws SQLException{
		String query = "SELECT * FROM lists WHERE Name = '" + name + "'";
		return sendQuery(query);
	}
	
	public void removeProductFromList(int listId, int productId) throws SQLException{
		execute("DELETE FROM lists_products WHERE ProductID = " + String.valueOf(productId) + " AND ListID = " + String.valueOf(listId));
	}
	
	public void updateQuantity(int listId, int productId, int quantity) throws SQLException{
		execute("UPDATE lists_products SET Quantity = " + String.valueOf(quantity) + " WHERE ProductID = " + String.valueOf(productId) + " AND ListID = " + String.valueOf(listId));
	}
	
	public void removeAllLists() throws SQLException{
		execute("DELETE FROM lists");
	}
	
	public void AddList(int Id, String listName) throws SQLException{
		String qry = "INSERT INTO lists VALUES (" + String.valueOf(Id) + ", " + listName + ")";
		execute(qry);
	}
	
	public void AddList(String listName) throws SQLException{
		String qry = "INSERT INTO lists (Name) VALUES ('" + listName + "')";
		execute(qry);
	}
	
	public void AddProductToList(int ListId, int ProductId, int quantity) throws SQLException{
		String qry = "INSERT INTO lists_products VALUES (" + String.valueOf(ProductId) + ", " + String.valueOf(ListId) + ", " + quantity + ")";
		execute(qry);
	}
	
	public int getItemsTotal() throws SQLException{
		String query = "SELECT sum(Quantity) as 'Total' FROM lists_products";
		ResultSet results = sendQuery(query);
		
		results.next();
		return results.getInt("Total");
	}
	
	public int getProductQuantity(int listId, int productId) throws SQLException{
		String query = "SELECT Quantity FROM lists_products WHERE ProductID = " + String.valueOf(productId) + " AND ListID = " + String.valueOf(listId);
		ResultSet results = sendQuery(query);
		
		results.next();
		return results.getInt("Quantity");
	}
	
	public ResultSet getProductInList(int listId, int productId) throws SQLException{
		String query = "SELECT * FROM lists_products WHERE ProductID = " + String.valueOf(productId) + " AND ListID = " + String.valueOf(listId);
		return sendQuery(query);
		
	}
	
	public void updateListName(int ListId, String name) throws SQLException{
		String query = "UPDATE lists SET Name = '"+ name +"' WHERE ListID = " + String.valueOf(ListId);
		execute(query);
	}
	
	public ResultSet getList(int listId) throws SQLException{
		String query = "SELECT * FROM lists WHERE ListID = " + String.valueOf(listId);
		return sendQuery(query);
	}
	
	public ResultSet getCategories() throws SQLException{
		String query = "SELECT * FROM categories";
		return sendQuery(query);
	}
	
	public ResultSet getAllListItems(int listId) throws SQLException{
		openConnection();
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.ProductID, p.Name, p.Price, p.CategoryID, p.IsFavourite, p.Image, lp.Quantity FROM lists l join lists_products lp on lp.ListID = l.ListID join products p on p.ProductID = lp.ProductID WHERE l.ListID = ?");

		preparedStatement.setInt(1, listId);
		
		ResultSet set = preparedStatement.executeQuery();
		return set;
	}
	
	public ResultSet getListItemsByCategory(int listId, String categoryName) throws SQLException{
		openConnection();
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.ProductID, p.Name, p.Price, p.CategoryID, p.IsFavourite, p.Image, lp.Quantity FROM smarttrolley.lists l\n"
				+ "join smarttrolley.lists_products lp on lp.ListID = l.ListID\n" + "join smarttrolley.products p on p.ProductID = lp.ProductID\n"
				+ "join smarttrolley.categories c on c.CategoryID = p.CategoryID\n" + "where c.Name = ? AND l.ListID = ?");

		preparedStatement.setString(1, categoryName);
		preparedStatement.setInt(2, listId);
		
		ResultSet set = preparedStatement.executeQuery();
		return set;
	}
	
	public void removeList(int listId) throws SQLException{
		execute("DELETE FROM lists WHERE ListID = " + String.valueOf(listId));
	}
	
	public ResultSet getAllLists() throws SQLException{
		String query = "SELECT * FROM lists";
		return sendQuery(query);
	}
	
	public ResultSet getAllProducts() throws SQLException{
		String query = "SELECT * FROM products";
		return sendQuery(query);
	}
	
	public ResultSet getProductsByCategory(String categoryName) throws SQLException{
		openConnection();
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from smarttrolley.products p " +
                "join smarttrolley.categories c on c.CategoryID = p.CategoryID where c.Name = ?");

		preparedStatement.setString(1, categoryName);
		
		ResultSet set = preparedStatement.executeQuery();
		return set;
	}
	//#######################
	
	/**
	 * Send a query to the predefined database and returns the results returned
	 * by the server
	 * Connection needs to be opened and closed externally to this method
	 * @param query
	 * @return results
	 * @throws SQLException
	 */
	public ResultSet sendQuery(String query) throws SQLException {
		openConnection();
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
				product = createProductFromResultSet(results, 0);
			}
			closeConnection();
			return product;

		} catch (SQLException e) {
			closeConnection();
			SmartTrolleyToolBox.print("Product could not be found");
			return null;
		}

	}

	/**
	 * 
	 * @return products
	 * @throws SQLException
	 * 
	 */
	public ObservableList<ListProduct> getListOfProducts() {

		openConnection();
		ListProduct product = new ListProduct();

		products = FXCollections.observableArrayList();
		String query = "SELECT * FROM products;";

		try {
			ResultSet results = sendQuery(query);
			while (results.next()) {
				product = createProductFromResultSet(results, 0);
				products.add(product);
			}
			closeConnection();
			return products;
		} catch (SQLException e) {
			SmartTrolleyToolBox.print("Product could not be found");
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
	public ObservableList<ListProduct> getListOfFavourites(String category) {

		openConnection();
		ListProduct product = new ListProduct();

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
				product = createProductFromResultSet(results, 0);
				products.add(product);
			}
			closeConnection();
			return products;
		} catch (SQLException e) {
			SmartTrolleyToolBox.print("Product could not be found");
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
	public ObservableList<ListProduct> wildcardSearchForProduct(String searchString) throws SQLException {

		String query;
		ResultSet resultSet = null;
		openConnection();
		ObservableList<ListProduct> products = FXCollections.observableArrayList();

		SmartTrolleyToolBox.print(searchString);

		query = "SELECT * FROM products WHERE name LIKE '" + searchString + "%';";

		try {
			resultSet = sendQuery(query);
		} catch (SQLException e) {
			SmartTrolleyToolBox.print("unable to send query");
		}

		while (resultSet.next()) {

			ListProduct product = createProductFromResultSet(resultSet, 0);

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
	private ListProduct createProductFromResultSet(ResultSet resultSet, int quantity) throws SQLException {
		
		ListProduct product = new ListProduct();

		SmartTrolleyToolBox.print(resultSet.getString("Name"));
		// get id
		product.setId(resultSet.getInt("ProductID"));

		// get Name
		product.setName(resultSet.getString("Name"));

		// get Image
		product.setImage(resultSet.getString("Image"));

		// get Price
		product.setPrice(resultSet.getFloat("Price"));
		
		//get Quantity
		product.setQuantity(quantity);

		// get Favorite status
		product.setFavorite(resultSet.getInt("IsFavourite"));
		
		SmartTrolleyToolBox.print("Product Created where: " + product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + " " + product.getFavorite());
		
		return product;
	}

	/**
	 * 
	 * @param criteria
	 * @param value
	 * @return product
	 */
	public ListProduct getSpecificProduct(String criteria, String value, String category) {

		ListProduct product = new ListProduct();

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
					product = createProductFromResultSet(results, 0);

				} while (results.next());
				closeConnection();
				return product;
			} else {
				closeConnection();
				return null;
			}
		} catch (SQLException e) {
			closeConnection();
			SmartTrolleyToolBox.print("Product could not be found");
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

			SmartTrolleyToolBox.print("Product could not be found");
			SmartTrolleyToolBox.print(e.getMessage());
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
	public ObservableList<ListProduct> getProductsWithinSpecificCategory(String Number) {

		openConnection();
		ListProduct product = new ListProduct();

		products = FXCollections.observableArrayList();

		String query = "SELECT * FROM products WHERE CategoryID = " + Number + ";";

		try {
			ResultSet results = sendQuery(query);

			if (isResultSetEmpty(results) == false) {

				SmartTrolleyToolBox.print("Results found");
				results.absolute(1);

				do {
					product = createProductFromResultSet(results, 0);
					products.add(product);
				} while (results.next());
				SmartTrolleyToolBox.print("Results stored");

				closeConnection();
				return products;

			} else {
				closeConnection();
				SmartTrolleyToolBox.print("No result");
				return null;
			}

		} catch (SQLException e) {
			closeConnection();
			SmartTrolleyToolBox.print("Product could not be found");
			return null;
		}
	}	

	/**
	 * When called, this method will return the list of offers and store them as
	 * a type offer.
	 * 
	 * @return offers
	 */
	public ObservableList<ListProduct> getListOfOffers() {

		productsDatabase = new SqlConnection();

		openConnection();

		offers = FXCollections.observableArrayList();

		String query = "SELECT * FROM offers;";

		try {
			ResultSet results = sendQuery(query);

			Offer offer = new Offer();
			ListProduct product = new ListProduct();

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

			SmartTrolleyToolBox.print("Offers could not be found");
			return null;
		}

	}

	/**
	 * When called, it will return a the customers list filtered by a selected category
	 * @param listID
	 * @param categoryNumber
	 * @return
	 */
	public ObservableList<ListProduct> getListByCategory(int listID, String categoryNumber) {
		openConnection();

		ListProduct product = new ListProduct();
		products = FXCollections.observableArrayList();

		String query = "SELECT * FROM lists_products WHERE listID = " + listID;
		SmartTrolleyToolBox.print("query is: " + query);

		ResultSet productIDsInList = null;

		try {
			productIDsInList = sendQuery(query);

		} catch (SQLException e) {
			SmartTrolleyToolBox.print("lists could not be found");

		}

		try {

			ResultSet listProducts = null;

			while (productIDsInList.next()) {

				SmartTrolleyToolBox.print(productIDsInList.getInt("ProductID"));
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

						product = createProductFromResultSet(listProducts, 0);
						products.add(product);

						SmartTrolleyToolBox.print("Product Stored");

						SmartTrolleyToolBox.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + " " + product.getFavorite());
					} while (listProducts.next());
				} else {
					SmartTrolleyToolBox.print("empty result, moving to next item");
				}
			}
		} catch (SQLException e) {
			SmartTrolleyToolBox.print("Product could not be found");
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
			SmartTrolleyToolBox.print("Categories could not be found");
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
	public ObservableList<ListProduct> getListItems(int listID) {
		openConnection();

		products = FXCollections.observableArrayList();

		String query = "SELECT * FROM lists_products WHERE listID = " + SmartTrolleyGUI.getcurrentListID();
		SmartTrolleyToolBox.print("query is: " + query);

		ResultSet productIDsInList = null;

		try {
			productIDsInList = sendQuery(query);

		} catch (SQLException e) {
			SmartTrolleyToolBox.print("lists could not be found");

		}

		try {

			ResultSet listProducts;

			while (productIDsInList.next()) {

				query = "SELECT * FROM products WHERE ProductID = " + productIDsInList.getInt("ProductID");
				SmartTrolleyToolBox.print("query is: " + query);

				listProducts = sendQuery(query);

				SmartTrolleyToolBox.print("Query Sent");

				ListProduct product = new ListProduct();
				SmartTrolleyToolBox.print("Initializing Product");

				SmartTrolleyToolBox.print(isResultSetEmpty(listProducts));

				listProducts.absolute(1);

				SmartTrolleyToolBox.print("Row Size is = " + listProducts.getRow());

				product = createProductFromResultSet(listProducts, productIDsInList.getInt("Quantity"));
				SmartTrolleyToolBox.print("Product Set");

				products.add(product);

				SmartTrolleyToolBox.print("Product Stored");

				SmartTrolleyToolBox.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + " " + product.getFavorite());

			}
		} catch (SQLException e) {
			SmartTrolleyToolBox.print("Product could not be found");
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
	public ObservableList<ListProduct> getOfferByCategory(String categoryNumber) {
		Offer offer = new Offer();
		ListProduct product = new ListProduct();
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
			SmartTrolleyToolBox.print("Offers could not be found");
			SmartTrolleyToolBox.print("SQL ERROR: " + e);
			return null;
		}

	}

	/**
	 * executes statement to SQL server, allow for creation and deleted of
	 * further lists and tables
	 * Connection needs to be opened and closed externally to this method
	 * @param query
	 * @return boolean statementExecuted
	 * Date Modified: 4 May 2014
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
			SmartTrolleyToolBox.print("Cannot execute statement due to error: "+ ex);
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
			SmartTrolleyToolBox.print("Connection did not close properly");
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
