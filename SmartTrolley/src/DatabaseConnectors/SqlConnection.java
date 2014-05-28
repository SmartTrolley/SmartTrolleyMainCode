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
	*@return
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

	/**************START: ADD XML to DATABASE***************/

	/**
	 * Method will add data to the document_info_data on the SQL database
	 * 
	 * @param author
	 * @param version
	 * @param title
	 * @param comment
	 * @param width
	 * @param height
	 */
	public void addDocumentDataContent(String author, String version,
			String title, String comment, int width, int height) {

		openConnection();
		String query = "INSERT INTO document_info_data (author, version, title, comment, width, height) VALUES ('"
				+ author
				+ "', '"
				+ version
				+ "', '"
				+ title
				+ "', '"
				+ comment
				+ "', '"
				+ width
				+ "', '"
				+ height + "');";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will delete data from the document_info_data table on the SQL
	 * database
	 */
	public void deleteDocumentDataContent() {

		openConnection();
		String query = "DELETE FROM document_info_data";
		executeStatement(query);
		closeConnection();
		}

	/**
	 * 
	 * @param backgroundcolor
	 * @param font
	 * @param fontsize
	 * @param linecolor
	 * @param fillcolor
	 */
	public void addDefaultsContent(String backgroundcolor, String font,
			int fontsize, String linecolor, String fillcolor) {
		openConnection();
		String query = "INSERT INTO defaults (backgroundcolor, font, fontsize, linecolor, fillcolor) VALUES ('"
				+ backgroundcolor
				+ "', '"
				+ font
				+ "', '"
				+ fontsize
				+ "', '"
				+ linecolor
				+ "', '"
				+ fillcolor + "');";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will delete data from the defaults table on the SQL
	 * database
	 */
	public void deleteDefaultsContent() {
		openConnection();
		String query = "DELETE FROM defaults";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will add contents to the audio table on the sql server
	 * 
	 * @param productID
	 * @param startTime
	 * @param urlName
	 * @param volume
	 * @param repeat
	 */
	public void addAudioTableContents(int productID, int startTime,
			String urlName, double volume, boolean loop) {
		openConnection();
		String query = "INSERT INTO `cl36-st`.`audio` (`productid`, `urlname`, `starttime`, `volume`, `loop`) VALUES ('"
				+ productID
				+ "', '"
				+ urlName
				+ "', '"
				+ startTime
				+ "', '"
				+ volume
				+ "', '"
				+ loop
				+ "');";
		executeStatement(query);
		closeConnection();

	}

	/**
	 * Method will add contents to the image table on the sql server
	 * 
	 * @param imageNo
	 * @param urlname
	 * @param xstart
	 * @param ystart
	 * @param width
	 * @param height
	 * @param starttime
	 * @param duration
	 * @param layer
	 * @param branch
	 */
	public void addImageContents(String urlname, int xstart,
			int ystart, int width, int height, int starttime, int duration,
			int layer, int branch) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`image_slide` (`urlname`, `xstart`, `ystart`, `width`, `height`, `starttime`, `duration`, `layer`, `branch`"
				+ ") VALUES ('"
				+ urlname
				+ "', '"
				+ xstart
				+ "', '"
				+ ystart
				+ "', '"
				+ width
				+ "', '"
				+ height
				+ "', '"
				+ starttime
				+ "', '"
				+ duration
				+ "', '"
				+ layer
				+ "', '"
				+ branch
				+ "');";
		executeStatement(query);
		closeConnection();

	}

	/**
	 * Method will add contents to the point table on the sql server
	 * 
	 * @param productid
	 * @param shapeNo
	 * @param individualPointNo
	 * @param x
	 * @param y
	 */
	public void addPointContents(int productid, int shapeNo,
			int individualPointNo, int x, int y) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`point` (`productid`, `ShapeNo`, `IndividualPointNo`, `x`, `y`) VALUES ('"
				+ productid
				+ "', '"
				+ shapeNo
				+ "', '"
				+ individualPointNo
				+ "', '"
				+ x
				+ "', '"
				+ y
				+ "');";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will add contents to the shape table on the sql server
	 * 
	 * @param productid
	 * @param totalPoints
	 * @param width
	 * @param height
	 * @param starttime
	 * @param duration
	 * @param layer
	 * @param branch
	 * @param fillcolor
	 * @param linecolor
	 */
	public void addShapeContents(int productid, int totalPoints, int width,
			int height, int starttime, int duration, int layer, int branch,
			String fillcolor, String linecolor) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`shape` (`productid`, `totalpoints`, `width`, `height`, `fillcolor`, `starttime`, `duration`, `layer`, `linecolor`, `branch`"
				+ ") VALUES ('"
				+ productid
				+ "', '"
				+ totalPoints
				+ "', '"
				+ width
				+ "', '"
				+ height
				+ "', '"
				+ fillcolor
				+ "', '"
				+ starttime
				+ "', '"
				+ duration
				+ "', '"
				+ layer
				+ "', '"
				+ linecolor
				+ "', '"
				+ branch
				+ "');";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will add contents to the text table on the sql server
	 * 
	 * @param productid
	 * @param totalPoints
	 * @param fontSize
	 * @param xStart
	 * @param yStart
	 * @param startTime
	 * @param duration
	 * @param layer
	 * @param xend
	 * @param yend
	 * @param font
	 * @param fontColor
	 */
	public void addTextContents(int productid, int fontSize,
			int xStart, int yStart, int startTime, int duration, int layer,
			int xend, int yend, String font, String fontColor) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`text` (`productid`, `font`, `fontSize`, `FontColor`, `xStart`, `yStart`, `startTime`, `duration`, `layer`, `xend`, `yend`"
				+ ") VALUES ('"
				+ productid
				+ "', '"
				+ font
				+ "', '"
				+ fontSize
				+ "', '"
				+ fontColor
				+ "', '"
				+ xStart
				+ "', '"
				+ yStart
				+ "', '"
				+ startTime
				+ "', '"
				+ duration
				+ "', '"
				+ layer
				+ "', '"
				+ xend
				+ "', '"
				+ yend
				+ "');";
		executeStatement(query);
		closeConnection();

	}

	/**
	 * Method will add contents to the textbody table on the sql server
	 * 
	 * @param productid
	 * @param textNo
	 * @param branch
	 * @param bold
	 * @param italic
	 * @param underlined
	 * @param text
	 * @param branch2
	 */
	public void addTextbodyContents(int productid, int textNo, int branch,
			Boolean bold, Boolean italic, Boolean underlined, String text) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`textbody` (`productid`, `TextNo`, `Bold`, `Italic`, `Underlined`, `Text`, `Branch`"
				+ ") VALUES ('"
				+ productid
				+ "', '"
				+ textNo
				+ "', '"
				+ bold
				+ "', '"
				+ italic
				+ "', '"
				+ underlined
				+ "', '"
				+ text
				+ "', '"
				+ branch
				+ "');";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will add contents to the video table on the sql server
	 * 
	 * @param productid
	 * @param urlname
	 * @param starttime
	 * @param loop
	 * @param xstart
	 * @param ystart
	 * @param width
	 * @param height
	 * @param layer
	 * @param duration
	 */
	public void addVideoContents(int productid, String urlname, int starttime,
			boolean loop, int xstart, int ystart, int width, int height,
			int layer, int duration) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`video` (`productid`, `urlname`, `starttime`, `loop`, `xstart`, `ystart`, `width`, `height`, `layer`, `duration`"
				+ ") VALUES ('"
				+ productid
				+ "', '"
				+ urlname
				+ "', '"
				+ starttime
				+ "', '"
				+ loop
				+ "', '"
				+ xstart
				+ "', '"
				+ ystart
				+ "', '"
				+ width
				+ "', '"
				+ height
				+ "', '"
				+ layer
				+ "', '"
				+ duration
				+ "');";
		executeStatement(query);
		closeConnection();
	}
	
	/**
	 * Method will add contents to the slide table on the sql server
	 * 
	 * @param slideid
	 * @param duration
	 * @param descriptor
	 * @param lastSlide
	 * @param backgroundcolor
	 */
	public void addSlideContents(int slideid, int duration, String descriptor, 
			boolean lastSlide, String backgroundcolor) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`slide` (`slideID`, `duration`, `descriptor`, `lastSlide`, `backgroundcolor`"
				+ ") VALUES ('"
				+ slideid
				+ "', '"
				+ duration
				+ "', '"
				+ descriptor
				+ "', '"
				+ lastSlide
				+ "', '"
				+ backgroundcolor
				+ "');";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will delete data from the slide table on the SQL
	 * database
	 */
	public void deleteSlideContent() {

		openConnection();
		String query = "DELETE FROM slide";
		executeStatement(query);
		closeConnection();
		}
	
	/**
	 * Method will delete all entries of the table it is given
	 * 
	 * @param table
	 */
	public void deleteContentAndResetAutoIncrement(String table){
		openConnection();
		String query = "DELETE FROM " + table;
		executeStatement(query);
		query = "ALTER TABLE " + table + " AUTO_INCREMENT = 1";
		executeStatement(query);
		closeConnection();	
	}

						/************** END: ADD XML to DATABASE ***************/

}
