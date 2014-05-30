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

import slideshowdata.AudioData;
import slideshowdata.DataType;
import slideshowdata.DefaultsData;
import slideshowdata.DocumentInfoData;
import slideshowdata.ImageData;
import slideshowdata.PointData;
import slideshowdata.ShapeData;
import slideshowdata.SlideData;
import slideshowdata.TextBodyData;
import slideshowdata.TextData;
import slideshowdata.VideoData;
import smarttrolleygui.Offer;
import smarttrolleygui.Product;
import smarttrolleygui.SmartTrolleyGUI;

	public class SqlConnection {
		
		
		PreparedStatement preparedStatement;
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
			
			String query = "Select * From products where " + criteria +" = '" + value + "';";
			
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
		 * @param table
		 * @param criteria
		 * @param value
		 * @return data
		 */
		public DataType getSpecificData(String table, String criteria, String value) {
			
			ResultSet results = null;
			
			DataType data = null;
			
			openConnection();
			
			String query = "Select * From " + table + " where " + criteria +" = '" + value + "';";
			
			
				try {
					results = sendQuery(query);
				
				
			switch (table){
			case "products":
				Product product = new Product();
				
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
				closeConnection();
				data = product;
				break;
				
			case "audio":
				AudioData audiodata = new AudioData();
				
				while (results.next()){
					
					//get urlname
					audiodata.setUrlname(results.getString("urlname"));
					
					//get starttime
					audiodata.setStarttime(results.getInt("starttime"));
					
					//get loop
					audiodata.setLoop(results.getBoolean("loop"));
				}
				closeConnection();
				data = audiodata;
				break;
				
			case "defaults":
				DefaultsData defaultsdata = new DefaultsData();
				
				while (results.next()){
					
					//get backgroundcolor
					defaultsdata.setBackgroundcolor(results.getString("backgroundcolor"));
					
					//get font
					defaultsdata.setFont(results.getString("font"));
					
					//get fontsize
					defaultsdata.setFontsize(results.getInt("fontsize"));
					
					//get fontcolor
					defaultsdata.setFontcolor(results.getString("fontcolor"));
					
					//get linecolor
					defaultsdata.setLinecolor(results.getString("linecolor"));
					
					//get fillcolor
					defaultsdata.setFillcolor(results.getString("fillcolor"));
				}
				closeConnection();
				data = defaultsdata;
				break;
				
				case "document_info_data":
					DocumentInfoData documentinfodata = new DocumentInfoData();
					
					while (results.next()){
						
						//get author
						documentinfodata.setAuthor(results.getString("author"));
						
						//get version
						documentinfodata.setVersion(results.getString("version"));
						
						//get title
						documentinfodata.setTitle(results.getString("title"));
						
						//get comment
						documentinfodata.setComment(results.getString("comment"));
						
						//get width
						documentinfodata.setWidth(results.getInt("width"));
						
						//get height
						documentinfodata.setHeight(results.getInt("height"));
					}
					data = documentinfodata;
					break;
					
				case "image_slide":
					ImageData imagedata = new ImageData();
					
					while (results.next()){
						
						//get urlname
						imagedata.setUrlname(results.getString("urlname"));
						
						//get xstart
						imagedata.setXstart(results.getInt("xstart"));
						
						//get ystart
						imagedata.setYstart(results.getInt("ystart"));
						
						//get width
						imagedata.setWidth(results.getInt("width"));
						
						//get height
						imagedata.setHeight(results.getInt("height"));
						
						//get layer
						imagedata.setLayer(results.getInt("layer"));
						
						//get duration
						imagedata.setDuration(results.getInt("duration"));
						
						//get starttime
						imagedata.setStarttime(results.getInt("starttime"));
						
						//get branch
						imagedata.setBranch(results.getInt("branch"));
					}
					data = imagedata;
					break;
					
				case "point":
					PointData pointdata = new PointData();
					
					while (results.next()){
						
						//get num
						pointdata.setNum(results.getInt("num"));
						
						//get x
						pointdata.setX(results.getInt("x"));
						
						//get y
						pointdata.setY(results.getInt("y"));
					}
					data = pointdata;
					break;
					
				case "shape":
					ShapeData shapedata = new ShapeData();
					
					while (results.next()){
															
						//get fillcolor
						shapedata.setFillcolor(results.getString("fillcolor"));
						
						//get linecolor
						shapedata.setLinecolor(results.getString("linecolor"));
						
						//get layer
						shapedata.setLayer(results.getInt("layer"));
						
						//get duration
						shapedata.setDuration(results.getInt("duration"));
						
						//get layer
						shapedata.setStarttime(results.getInt("layer"));
						
						//get totalpoints
						shapedata.setTotalpoints(results.getInt("totalpoints"));
						
						//get width
						shapedata.setWidth(results.getInt("width"));
						
						//get height
						shapedata.setHeight(results.getInt("height"));
					}
					data = shapedata;
					break;
					
				case "slide":
					SlideData slidedata = new SlideData();
					
					while (results.next()){
																		
						//get id
						slidedata.setId(results.getInt("id"));
						
						//get duration
						slidedata.setDuration(results.getInt("duration"));
						
						//get lastSlide
						slidedata.setLastSlide(results.getBoolean("lastSlide"));
					}
					data = slidedata;
					break;
					
				case "text":
					TextData textdata = new TextData();
					
					while (results.next()){
															
						//get xstart
						textdata.setXstart(results.getInt("xstart"));
						
						//get ystart
						textdata.setYstart(results.getInt("ystart"));
						
						//get xend
						textdata.setXend(results.getInt("xend"));
						
						//get yend
						textdata.setYend(results.getInt("yend"));
						
						//get layer
						textdata.setLayer(results.getInt("layer"));
						
						//get duration
						textdata.setDuration(results.getInt("duration"));
						
						//get starttime
						textdata.setStarttime(results.getInt("starttime"));
						
						//get font
						textdata.setFont(results.getString("font"));
						
						//get fontcolor
						textdata.setFontcolor(results.getString("fontcolor"));
						
						//get fontsize
						textdata.setFontsize(results.getInt("fontsize"));
					}
					data = textdata;
					break;
					
				case "textbody":
					TextBodyData textbodydata = new TextBodyData();
					
					while (results.next()){
														
						//get branch
						textbodydata.setBranch(results.getInt("branch"));
						
						//get italic
						textbodydata.setItalic(results.getBoolean("italic"));
						
						//get bold
						textbodydata.setBold(results.getBoolean("bold"));
						
						//get underlined
						textbodydata.setUnderlined(results.getBoolean("underlined"));
						
						//get textstring
						textbodydata.setTextstring(results.getString("textstring"));
					}
					data = textbodydata;
					break;
					
				case "video":
					VideoData videodata = new VideoData();
					
					while (results.next()){
														
						//get urlname
						videodata.setUrlname(results.getString("urlname"));
						
						//get xstart
						videodata.setXstart(results.getInt("xstart"));
						
						//get ystart
						videodata.setYstart(results.getInt("ystart"));
						
						//get width
						videodata.setWidth(results.getInt("width"));
						
						//get height
						videodata.setHeight(results.getInt("height"));
						
						//get layer
						videodata.setLayer(results.getInt("layer"));
						
						//get duration
						videodata.setDuration(results.getInt("duration"));
						
						//get starttime
						videodata.setStarttime(results.getInt("starttime"));

						//get loop
						videodata.setLoop(results.getBoolean("loop"));	
						
					}
					data = videodata;
					break;
				
			default: 
				closeConnection();
				data = null;
			}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			return data;
		}
		
	
		/**
		 * When called, this method will return the list of offers
		 * and store them as a type offer.
		 * @return offers
		 */
		public ObservableList<Product> getListOfOffers() {
						
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
					
					product = getSpecificProduct("ProductID", String.valueOf(results.getInt("ProductId")));
					
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
				SmartTrolleyPrint
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
		*@return products
		*<p> Date Modified: 9 May 2014
		*/
		public ObservableList<Product> getList(int listID) {
			openConnection();
	
			products = FXCollections.observableArrayList();
	
			String query = "SELECT * FROM lists_products WHERE listID = " + SmartTrolleyGUI.getcurrentListID();
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
	
					query = "SELECT * FROM products WHERE ProductID = " + productIDsInList.getInt("ProductID");
					SmartTrolleyPrint.print("query is: " + query);
	
					listProducts = sendQuery(query);
					
					SmartTrolleyPrint.print("Query Sent");
	
					Product product = new Product();
					SmartTrolleyPrint.print("Initializing Product");
					
					SmartTrolleyPrint.print(isResultSetEmpty(listProducts));
					listProducts.absolute(1);
					SmartTrolleyPrint.print("Row Size is = " + listProducts.getRow());
	
					// get id
					product.setId(listProducts.getInt("ProductID"));
	
					// get Name
					product.setName(listProducts.getString("Name"));
	
					// get Image
					product.setImage(listProducts.getString("Image"));
	
					// get Price
					product.setPrice(listProducts.getFloat("Price"));
					
					SmartTrolleyPrint.print("Product Set");
	
					products.add(product);
					
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
		 * executes statement to SQL server, allow for creation and deleted of
		 * further lists and tables
		 * 
		 * @param query
		 * @return boolean statementExecuted
		 *         Date Modified: 4 May 2014
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
				SmartTrolleyPrint
				.print("Error: " + ex);
			}
	
			return statementExecuted;
		}
	
		/**
		 * Creates a new product with a title and defaults for other fields
		 * returns the id of the added product
		 * @param Title
		 * @param id
		 * @return productid
		 * @throws SQLException
		 */
		public int createNewProduct(String Title, int id){
			
			int productid = 0;
			
			openConnection();
			
			String query = "INSERT INTO `cl36-st`.`products` (`image`, `Name`, `Price`, `CategoryID`, `IsFavourite`) VALUES ('"
					+ "null"
					+ "', '"
					+ Title + " " + id
					+ "', '"
					+ 0
					+ "', '"
					+ 1
					+ "', '"
					+ 0
					+ "');";
			executeStatement(query);
			query = "SELECT MAX(ProductID) AS ProductID FROM products;";
			ResultSet results=null;
			try {
				results = sendQuery(query);
				results.absolute(1);
				productid = results.getInt("ProductID");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			closeConnection();
			
			return productid;
		}
		
		/**
		 * Deletes the last product in the product list and resets the auto increment 
		 */
		public void deleteLastProduct(){

			openConnection();
			int productid = 0;
			String query = "SELECT MAX(ProductID) AS ProductID FROM products;";
			ResultSet results;
			
			try {
				results = sendQuery(query);
				results.absolute(1);
				productid = results.getInt("ProductID");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			query = "DELETE FROM products where ProductID = " + productid + ";";
			executeStatement(query);
			
			query = "ALTER TABLE products" + " AUTO_INCREMENT =" + productid + ";";
			executeStatement(query);
			closeConnection();	
			
		}
		
		/**
		 * 
		 * @param enteredListName
		 * @return listid
		 */
		public int createNewList(String enteredListName){
			
			int listid = 0;
			
			openConnection();
			
			String query = "INSERT INTO `cl36-st`.`lists` (`Name`) VALUES ('"
					+ enteredListName + "');";
			executeStatement(query);
			SmartTrolleyPrint.print("Created new list: " + enteredListName);
			
			query = "SELECT MAX(ListID) AS ListID FROM lists;";
			ResultSet results=null;
			try {
				results = sendQuery(query);
				results.absolute(1);
				listid = results.getInt("ListID");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			closeConnection();
			
			return listid;
		}
		
		/**
		 * Deletes the last list in the List of lists and resets the auto increment
		 */
		public void deleteLastList(){

			int listid = 0;
			
			openConnection();
			
			String query = "SELECT MAX(ListID) AS ListID FROM lists;";
			ResultSet results;
			
			try {
				results = sendQuery(query);
				results.absolute(1);
				listid = results.getInt("ListID");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			query = "DELETE FROM lists where ListID = " + listid + ";";
			executeStatement(query);
			
			query = "ALTER TABLE lists" + " AUTO_INCREMENT =" + listid + ";";
			executeStatement(query);
			
			query = "DELETE FROM lists_products where ListID = " + listid + ";";
			executeStatement(query);
			
			closeConnection();
		}

		/**
		 * adds product to a list 
		 * @param productid
		 */
		public void addProductToList(int productid, int listid){
			
			openConnection();
			
			String query = "INSERT INTO lists_products (ProductID, ListID, Quantity) VALUES ('"
					+ productid
					+ "', '"
					+ listid
					+ "', '"
					+ 1
					+ "');";
			
			executeStatement(query);
			closeConnection();
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
