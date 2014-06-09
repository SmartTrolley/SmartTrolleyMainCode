package DatabaseConnectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slideshowdata.AudioData;
import slideshowdata.DefaultsData;
import slideshowdata.DocumentInfoData;
import slideshowdata.ImageData;
import slideshowdata.PointData;
import slideshowdata.ShapeData;
import slideshowdata.SlideData;
import slideshowdata.TextBodyData;
import slideshowdata.TextData;
import slideshowdata.VideoData;
import smarttrolleygui.ListProduct;
import smarttrolleygui.Offer;
import smarttrolleygui.Product;
import smarttrolleygui.SmartTrolleyGUI;
import toolBox.SmartTrolleyToolBox;

/**
 * SmartTrolley
 * 
 * Class that handles any connections and queries to the SQL database - Inspired from Alasdair's spike
 * 
 * @author Thomas
 * @author Sam
 * @author V2.1 Alick & Prashant [boolean class added for ResultSet testing]
 *         [also added execute statement]
 * @author V4.0 Prashant [Final Refactoring and commenting]
 * 
 * @author Checked By: Matthew Wells [06/06/14]
 * 
 * @version V3.0 [Date Created: 4 Jun 2014]
 * @version V4.0 [Date Created: 6 Jun 2014] 
 */
public class SqlConnection {

	PreparedStatement preparedStatement;
	private static final String IP = "79.170.44.157";
	private static final String USERNAME = "cl36-st";
	private static final String PASSWORD = "Smarttrolley";

	private ObservableList<ListProduct> products;

	private ObservableList<String> categories;

	private String url;
	protected Connection connection;

	/**
	 * Launches the java sql driver and compiles the url for the database
	 *<p> Date Modified: 26 Mar 2014
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
	* Compiles a valid URL from the connection info provided
	*<p> Date Modified: 6 Jun 2014
	*/
	private void compileUrl() {
		// construct the url assuming use of mysql and the standard port.
		url = "jdbc:mysql://" + IP + "/" + USERNAME + "?" + "allowMultiQueries=true";

	}

	/**
	*Opens a connection (if not already open) for sending mysql queries to the database
	*
	*<p> Date Modified: 6 Jun 2014
	*/
	public void openConnection() {

		try {
			connection = null;
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			SmartTrolleyToolBox.print("Connection Opened");
		} catch (SQLException ex) {

			SmartTrolleyToolBox.print("Connection failed to open");

		}
	}

	/**
	* Sends a query to the predefined database and returns the results returned
	* by the server
	* Connection needs to be opened and closed externally to this method
	*@param query
	*@return ResultSet results
	*@throws SQLException
	*
	*<p> Date Modified: 6 Jun 2014
	*/
	public ResultSet sendQuery(String query) throws SQLException {

		Statement statement = connection.createStatement();
		SmartTrolleyToolBox.print("Query to send is: " + query);

		ResultSet results = statement.executeQuery(query);

		return results;
	}

	/**
	* Executes SQL query that returns the information for all products in the database
	*@return ObservableList<Product>
	*<p> Date Modified: 6 Jun 2014
	*/
	public ObservableList<ListProduct> getListOfAllProducts() {

		openConnection();
		ListProduct listProduct = new ListProduct();

		ObservableList<ListProduct> listProducts = FXCollections.observableArrayList();
		String query = "SELECT * FROM products;";

		try {
			ResultSet results = sendQuery(query);
			while (results.next()) {
				listProduct = createListProductFromResultSet(results);
				listProducts.add(listProduct);
			}
			closeConnection();
			return listProducts;
		} catch (SQLException e) {
			SmartTrolleyToolBox.print("Product could not be found");
			return null;
		}
	}

	/**
	* Returns either the entire list of favourites (category = 1)
	* or a version filtered by category
	*@param category
	*@return ObservableList<Product>
	*<p> Date Modified: 6 Jun 2014
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
				product = createProductFromResultSet(results);
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
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param results
	*@return
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 7 Jun 2014
	*/
	private ListProduct createProductFromResultSet(ResultSet results) {
		ListProduct listProduct = null;
		try {
			listProduct = createProductFromResultSet(results, 0);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listProduct;
	}

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param productsInList
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 9 Jun 2014
	*/
	public float calculateSavings(Map<Integer, Integer> productsInList, List<Integer> productIDsInList) {

		float savings = 0;
		openConnection();

		ObservableList<ListProduct> offers = FXCollections.observableArrayList();

		String query = "SELECT * FROM offers WHERE ProductID= " + productIDsInList.get(0);

		for (int i = 1; i < productIDsInList.size(); i++) {
			query = query.concat(" OR ProductID= " + productIDsInList.get(i));
		}

		query = query.concat(";");

		try {
			ResultSet results = sendQuery(query);
			
			ListProduct product = new ListProduct();

			while (results.next()) {

				product = ListProduct.productToListProduct(getSpecificProduct("productID", String.valueOf(results.getInt("ProductId")), "1"));

				
				if (!(product == null)) {

					product.setOfferPrice(results.getFloat("OfferPrice"));

					savings = savings+((product.getOfferPrice() - product.getPrice())*productsInList.get(product.getID()));

				} else {
					return 0;
				}
			}

			closeConnection();
			return savings;

		} catch (SQLException e) {
			closeConnection();
			SmartTrolleyToolBox.print("Offers could not be found");
			return 0;
		}

	}

	/**
	* When called, this method will return the list of the products on offer
	*@return ObservableList<Product>
	*<p> Date Modified: 6 Jun 2014
	*/
	public ObservableList<ListProduct> getListOfOffers() {

		openConnection();

		ObservableList<ListProduct> offers = FXCollections.observableArrayList();

		String query = "SELECT * FROM offers;";

		try {
			ResultSet results = sendQuery(query);

			Offer offer = new Offer();
			ListProduct product = new ListProduct();

			while (results.next()) {

				product = ListProduct.productToListProduct(getSpecificProduct("productID", String.valueOf(results.getInt("ProductId")), "1"));

				if (!(product == null)) {
					// get Offer id
					offer.setOfferId(results.getInt("OfferID"));

					// get Product id
					offer.setProductId(results.getInt("ProductID"));

					// get Price
					offer.setOfferPrice(results.getFloat("OfferPrice"));
					product.setOfferPrice(results.getFloat("OfferPrice"));

					float savings = product.getOfferPrice() - product.getPrice();

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
	* returns a list of all products that are in a specified list and specified category
	*@param listID
	*@param categoryNumber
	*@return ObservableList<Product>
	*<p> Date Modified: 6 Jun 2014
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

						product = createProductFromResultSet(listProducts);
						products.add(product);

						SmartTrolleyToolBox.print("Product Stored");

						SmartTrolleyToolBox.print(product.getID() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + " " + product.getFavorite());
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
	 * Retrieves the list items from the SQL server
	 * <p> User can view list of lists
	 * @param listID
	 * @return ObservableList<Product>
	 * <p> Date Modified: 9 May 2014
	 */
	public ObservableList<ListProduct> getListOfProductsInList(int listID) {
		openConnection();

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

				product = createProductFromResultSet(listProducts);
				SmartTrolleyToolBox.print("Product Set");

				products.add(product);

				SmartTrolleyToolBox.print("Product Stored");

				SmartTrolleyToolBox.print(product.getID() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + " " + product.getFavorite());

			}
		} catch (SQLException e) {
			SmartTrolleyToolBox.print("Product could not be found");
			return null;
		}

		closeConnection();
		return products;

	}

	/**
	* Executes SQL query that returns the information for a particular product
	* (by name)
	*@param productName
	*@return Product
	*<p> Date Modified: 6 Jun 2014
	*/
	public Product getProductByName(String productName) {

		Product product = new Product();

		openConnection();

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
			SmartTrolleyToolBox.print("Product could not be found");
			return null;
		}

	}

	/**
	*Searches database for product entered into the TextField
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

		query = "SELECT * FROM products WHERE name LIKE '%" + searchString + "%';";

		try {
			resultSet = sendQuery(query);
		} catch (SQLException e) {
			SmartTrolleyToolBox.print("unable to send query");
		}

		while (resultSet.next()) {

			ListProduct product = createProductFromResultSet(resultSet);

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
	protected ListProduct createProductFromResultSet(ResultSet resultSet, int quantity) throws SQLException {

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

		// get Quantity
		product.setQuantity(quantity);

		// get Favorite status
		product.setFavorite(resultSet.getInt("IsFavourite"));

		SmartTrolleyToolBox.print("Product Created where: " + product.getID() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + " "
				+ product.getFavorite());

		return product;
	}

	/**
	* This method creates and returns a product from a ResultSet passed in
	*@param resultSet
	*@return product - The product created
	*@throws SQLException
	*<p> Date Modified: 3 Jun 2014
	*/
	protected ListProduct createListProductFromResultSet(ResultSet resultSet) throws SQLException {

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

		// get Favorite status
		product.setFavorite(resultSet.getInt("IsFavourite"));

		SmartTrolleyToolBox.print("Product Created where: " + product.getID() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + " "
				+ product.getFavorite());

		return product;
	}

	/**
	* Executes SQL query that returns the information for a particular product
	* with a specified criteria (column in the products table)
	*@param criteria
	*@param value
	*@param category
	*@return Product
	*<p> Date Modified: 6 Jun 2014
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
			SmartTrolleyToolBox.print("Product could not be found");
			return null;
		}

	}

	/**
	* method to return any data from the SQL database from a specified table criteria and value.
	* returns an object that must be cast to the required type
	*@param table
	*@param criteria
	*@param value
	*@return Object to be cast
	*<p> Date Modified: 6 Jun 2014
	*/
	public Object getSpecificData(String table, String criteria, String value) {

		ResultSet results = null;

		Object data = null;

		openConnection();

		String query = "Select * From " + table + " where " + criteria + " = '" + value + "';";
		SmartTrolleyToolBox.print("Query sent from getSpecificData is: " + query);

		try {
			results = sendQuery(query);
			results.absolute(1);
			data = populateDataType(table, results);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			closeConnection();
		}
		return data;

	}

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param queries
	*@return
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 8 Jun 2014
	*/
	public Object getMultipleDatas(String table, String queries) {

		Statement statement = null;

		Object data = null;
		ArrayList<Object> dataList = new ArrayList<Object>();

		openConnection();
		SmartTrolleyToolBox.print("this is getting multiple ResultSets");
		try {
			statement = connection.createStatement();
			boolean results = statement.execute(queries);
			while (results) {

				data = new Object();
				if (results) {

					ResultSet rs = statement.getResultSet();
					data = populateDataType(table, rs);

					dataList.add(data);
					results = statement.getMoreResults();
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return dataList;
	}

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param table
	*@param results
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 8 Jun 2014
	*/
	protected Object populateDataType(String table, ResultSet results) {

		Object data = null;
		if (results != null) {
			try {
				results.absolute(1);
				switch (table) {
				case "products":

					Product product = null;

					do {
						product = new Product();

						// get id
						product.setId(results.getInt("ProductID"));

						// get Name
						product.setName(results.getString("Name"));

						// get Image
						product.setImage(results.getString("Image"));

						// get Price
						product.setPrice(results.getFloat("Price"));

					} while (results.next());

					data = product;
					break;

				case "audio":

					ArrayList<AudioData> audiodatalist = new ArrayList<AudioData>();

					do {
						AudioData audiodata = new AudioData();

						// get urlname
						audiodata.setUrlname(results.getString("urlname"));

						// get starttime
						audiodata.setStarttime(results.getInt("starttime"));

						// get loop
						audiodata.setLoop(results.getBoolean("loop"));

						audiodatalist.add(audiodata);

					} while (results.next());

					data = audiodatalist;
					break;

				case "defaults":

					DefaultsData defaultsdata = null;

					do {
						defaultsdata = new DefaultsData();

						// get listid
						defaultsdata.setListId(results.getInt("listid"));

						// get backgroundcolor
						defaultsdata.setBackgroundcolor(results.getString("backgroundcolor"));

						// get font
						defaultsdata.setFont(results.getString("font"));

						// get fontsize
						defaultsdata.setFontsize(results.getInt("fontsize"));

						// get fontcolor
						defaultsdata.setFontcolor(results.getString("fontcolor"));

						// get linecolor
						defaultsdata.setLinecolor(results.getString("linecolor"));

						// get fillcolor
						defaultsdata.setFillcolor(results.getString("fillcolor"));

					} while (results.next());

					data = defaultsdata;
					break;

				case "document_info_data":

					DocumentInfoData documentinfodata = null;

					do {
						documentinfodata = new DocumentInfoData();

						// get listid
						documentinfodata.setListId(results.getInt("listid"));

						// get author
						documentinfodata.setAuthor(results.getString("author"));

						// get version
						documentinfodata.setVersion(results.getString("version"));

						// get title
						documentinfodata.setTitle(results.getString("title"));

						// get comment
						documentinfodata.setComment(results.getString("comment"));

						// get width
						documentinfodata.setWidth(results.getInt("width"));

						// get height
						documentinfodata.setHeight(results.getInt("height"));

					} while (results.next());

					data = documentinfodata;
					break;

				case "image_slide":

					ArrayList<ImageData> imagedatalist = new ArrayList<ImageData>();

					do {
						ImageData imagedata = new ImageData();

						// get urlname
						imagedata.setUrlname(results.getString("urlname"));

						// get xstart
						imagedata.setXstart(results.getInt("xstart"));

						// get ystart
						imagedata.setYstart(results.getInt("ystart"));

						// get width
						imagedata.setWidth(results.getInt("width"));

						// get height
						imagedata.setHeight(results.getInt("height"));

						// get layer
						imagedata.setLayer(results.getInt("layer"));

						// get duration
						imagedata.setDuration(results.getInt("duration"));

						// get starttime
						imagedata.setStarttime(results.getInt("starttime"));

						// get branch
						imagedata.setBranch(results.getInt("branch"));

						imagedatalist.add(imagedata);

					} while (results.next());

					data = imagedatalist;
					break;

				case "point":

					ArrayList<PointData> pointdatalist = new ArrayList<PointData>();

					do {
						PointData pointdata = new PointData();

						// get point pointNo
						pointdata.setNum(results.getInt("IndividualPointNo"));

						// get x
						pointdata.setX(results.getInt("x"));

						// get y
						pointdata.setY(results.getInt("y"));
						pointdatalist.add(pointdata);

					} while (results.next());

					data = pointdatalist;
					break;

				case "shape":

					ArrayList<ShapeData> shapedatalist = new ArrayList<ShapeData>();

					do {
						ShapeData shapedata = new ShapeData();

						// get ShapeNo
						shapedata.setShapeNo(results.getInt("shapeno"));

						// get fillcolor
						shapedata.setFillcolor(results.getString("fillcolor"));

						// get linecolor
						shapedata.setLinecolor(results.getString("linecolor"));

						// get Starttime
						shapedata.setStarttime(results.getInt("starttime"));

						// get layer
						shapedata.setLayer(results.getInt("layer"));

						// get duration
						shapedata.setDuration(results.getInt("duration"));

						// get totalpoints
						shapedata.setTotalpoints(results.getInt("totalpoints"));

						// get width
						shapedata.setWidth(results.getInt("width"));

						// get height
						shapedata.setHeight(results.getInt("height"));

						// get branch
						shapedata.setBranch(results.getInt("branch"));

						shapedatalist.add(shapedata);

					} while (results.next());

					data = shapedatalist;
					break;

				case "slide":

					SlideData slidedata = null;

					do {
						slidedata = new SlideData();

						// get id
						slidedata.setId(results.getInt("slideid"));

						// get duration
						slidedata.setDuration(results.getInt("duration"));

						// get lastSlide
						slidedata.setLastSlide(results.getBoolean("lastSlide"));

					} while (results.next());

					data = slidedata;
					break;

				case "text":

					ArrayList<TextData> textdatalist = new ArrayList<TextData>();

					do {
						TextData textdata = new TextData();

						// get TextNo
						textdata.setTextNo(results.getInt("textno"));

						// get xstart
						textdata.setXstart(results.getInt("xstart"));

						// get ystart
						textdata.setYstart(results.getInt("ystart"));

						// get xend
						textdata.setXend(results.getInt("xend"));

						// get yend
						textdata.setYend(results.getInt("yend"));

						// get layer
						textdata.setLayer(results.getInt("layer"));

						// get duration
						textdata.setDuration(results.getInt("duration"));

						// get starttime
						textdata.setStarttime(results.getInt("starttime"));

						// get font
						textdata.setFont(results.getString("font"));

						// get fontcolor
						textdata.setFontcolor(results.getString("fontcolor"));

						// get fontsize
						textdata.setFontsize(results.getInt("fontsize"));

						textdatalist.add(textdata);

					} while (results.next());

					data = textdatalist;
					break;

				case "textbody":

					ArrayList<TextBodyData> textbodydatalist = new ArrayList<TextBodyData>();

					do {
						TextBodyData textbodydata = new TextBodyData();

						// get branch
						textbodydata.setBranch(results.getInt("branch"));

						// get italic
						textbodydata.setItalic(results.getBoolean("italic"));

						// get bold
						textbodydata.setBold(results.getBoolean("bold"));

						// get underlined
						textbodydata.setUnderlined(results.getBoolean("underlined"));

						// get textstring
						textbodydata.setTextstring(results.getString("Text"));

						textbodydatalist.add(textbodydata);

					} while (results.next());

					data = textbodydatalist;
					break;

				case "video":

					ArrayList<VideoData> videodatalist = new ArrayList<VideoData>();

					do {
						VideoData videodata = new VideoData();

						// get urlname
						videodata.setUrlname(results.getString("urlname"));

						// get xstart
						videodata.setXstart(results.getInt("xstart"));

						// get ystart
						videodata.setYstart(results.getInt("ystart"));

						// get width
						videodata.setWidth(results.getInt("width"));

						// get height
						videodata.setHeight(results.getInt("height"));

						// get layer
						videodata.setLayer(results.getInt("layer"));

						// get duration
						videodata.setDuration(results.getInt("duration"));

						// get starttime
						videodata.setStarttime(results.getInt("starttime"));

						// get loop
						videodata.setLoop(results.getBoolean("loop"));

						videodatalist.add(videodata);

					} while (results.next());

					data = videodatalist;
					break;

				default:
					data = null;
				}
			} catch (SQLException e) {

				SmartTrolleyToolBox.print("SQL Exception in populateDataType: " + e);
				return null;
			}
		} else {
			return null;
		}

		return data;

	}

	/**
	* returns the category id for a particular category name
	*@param categoryName
	*@return String categoryNumber
	*<p> Date Modified: 6 Jun 2014
	*/
	public String getSpecificCategoryNumber(String categoryName) {

		String categoryNumber = null;

		openConnection();

		String query = "Select * From categories where name = '" + categoryName + "';";

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
	*returns the list of products that correspond to a given categoryID
	*@param categoryID
	*@return ObservableList<Product>
	*<p> Date Modified: 6 Jun 2014
	*/
	public ObservableList<ListProduct> getProductsWithinSpecificCategory(String categoryID) {

		openConnection();
		ListProduct product = new ListProduct();

		products = FXCollections.observableArrayList();

		String query = "SELECT * FROM products WHERE CategoryID = " + categoryID + ";";

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
	* Method returns the list of category names. Used to display category names in GUI
	*@return ObservableList<String>
	*<p> Date Modified: 6 Jun 2014
	*/
	public ObservableList<String> getListOfCategories() {

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
	 * Checks to see if the result set is empty. Returns true if it is
	 * @param resultSet
	 * @return boolean empty
	 * @see 
	 * [http://stackoverflow.com/questions/2938812/how-to-find-out-if-a-java
	 * -resultset-obtained-is-empty]
	 *  <p>
	 *  Date Modified: 4 May 2014
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

		return empty;
	}

	/**
	* This class will filter the list of offers by category and return the
	* corresponding products
	*@param categoryNumber
	*@return ObservableList<Product>
	*<p> Date Modified: 6 Jun 2014
	*/
	public ObservableList<ListProduct> getOfferByCategory(String categoryNumber) {
		Offer offer = new Offer();
		Product product = new Product();

		openConnection();

		ObservableList<ListProduct> offers;
		offers = FXCollections.observableArrayList();

		String query = "SELECT * FROM offers;";

		try {
			ResultSet results = sendQuery(query);

			results.absolute(1);

			do {

				product = getSpecificProduct("ProductID", String.valueOf(results.getInt("ProductId")), categoryNumber);

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

					ListProduct listProduct = ListProduct.productToListProduct(product);

					offers.add(listProduct);
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
	 * executes statement to SQL server, allow for creation and deletion of
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
			SmartTrolleyToolBox.print("Cannot execute statement due to error: " + ex);
		}

		return statementExecuted;
	}

	/**
	* add a new product to the database with all relevant info provided
	*@param Title
	*@param id
	*@param Image
	*@param Price
	*@param CategoryID
	*@param IsFavourite
	*@return int productid of created product
	*<p> Date Modified: 6 Jun 2014
	*/
	public int createNewProduct(String Title, int id, String Image, double Price, int CategoryID, int IsFavourite) {

		int productid = 0, categoryID = 0;

		String productName = null;

		if (id == 0) {
			productName = Title;
		} else {
			productName = Title + " " + id;
		}

		if (CategoryID == 0) {
			categoryID = 1;
		} else {
			categoryID = CategoryID;
		}

		openConnection();

		String query = "INSERT INTO `cl36-st`.`products` (`image`, `Name`, `Price`, `CategoryID`, `IsFavourite`) VALUES ('" + Image + "', '" + productName + "', '" + Price
				+ "', '" + categoryID + "', '" + IsFavourite + "');";
		executeStatement(query);
		query = "SELECT MAX(ProductID) AS ProductID FROM products;";
		ResultSet results = null;
		try {
			results = sendQuery(query);
			results.absolute(1);
			productid = results.getInt("ProductID");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		closeConnection();

		return productid;
	}

	/**
	* Deletes the last product in the product list and resets the auto increment
	*<p> Date Modified: 6 Jun 2014
	*/
	public void deleteLastProduct() {

		openConnection();
		int productid = 0;
		String query = "SELECT MAX(ProductID) AS ProductID FROM products;";
		ResultSet results;

		try {
			results = sendQuery(query);
			results.absolute(1);
			productid = results.getInt("ProductID");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		query = "DELETE FROM products where ProductID = " + productid + ";";
		executeStatement(query);

		query = "ALTER TABLE products" + " AUTO_INCREMENT =" + productid + ";";
		executeStatement(query);
		closeConnection();

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

				SmartTrolleyToolBox.print(product.getID() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + " " + product.getFavorite());

			}
		} catch (SQLException e) {
			SmartTrolleyToolBox.print("Product could not be found");
			return null;
		}

		closeConnection();
		return products;

	}

	/**
	* creates a new list with a specified name and returns its id
	*@param enteredListName
	*@return int listid of created list
	*<p> Date Modified: 6 Jun 2014
	*/
	public int createNewList(String enteredListName) {

		int listid = 0;

		openConnection();

		String query = "INSERT INTO `cl36-st`.`lists` (`Name`) VALUES ('" + enteredListName + "');";
		executeStatement(query);
		SmartTrolleyToolBox.print("Created new list: " + enteredListName);

		query = "SELECT MAX(ListID) AS ListID FROM lists;";
		ResultSet results = null;
		try {
			results = sendQuery(query);
			results.absolute(1);
			listid = results.getInt("ListID");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		closeConnection();

		return listid;
	}

	/**
	* Deletes the last list in the List of lists and resets the auto increment
	*<p> Date Modified: 6 Jun 2014
	*/
	public void deleteLastList() {

		int listid = 0;

		openConnection();

		String query = "SELECT MAX(ListID) AS ListID FROM lists;";
		ResultSet results;

		try {
			results = sendQuery(query);
			results.absolute(1);
			listid = results.getInt("ListID");
		} catch (SQLException e) {

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
	* adds the product with a specified product id to a specified list
	*@param productid
	*@param listid
	*<p> Date Modified: 6 Jun 2014
	*/
	public void addProductToList(int productid, int listid) {

		openConnection();

		String query = "INSERT INTO lists_products (ProductID, ListID, Quantity) VALUES ('" + productid + "', '" + listid + "', '" + 1 + "');";

		executeStatement(query);
		closeConnection();
	}

	/**
	* provides public access to close the productsDatabase
	*<p> Date Modified: 6 Jun 2014
	*/
	public void closeConnection() {
		try {
			connection.close();
			SmartTrolleyToolBox.print("Connection Closed");
		} catch (SQLException ex) {
			SmartTrolleyToolBox.print("Connection did not close properly");
		}
	}

	// //////////////////////////////////////////////////////////////////////////////

	/**
	*Removes all products from all lists
	*<p>Test(s)/User Story that it satisfies
	*@throws SQLException
	*<p> Date Modified: 6 Jun 2014
	*/
	public void removeAllProductsFromAllLists() throws SQLException {
		executeStatement("DELETE FROM lists_products");
	}

	/**
	* Returns the resultset from querying the lists table with the list name passed in
	*@param name
	*@return resultset containing the list
	*@throws SQLException
	*<p> Date Modified: 6 Jun 2014
	*/
	public ResultSet getListByName(String name) throws SQLException {
		String query = "SELECT * FROM lists WHERE Name = '" + name + "'";
		return sendQuery(query);
	}

	/**
	*Removes the specified product from the specified list
	*<p>User adds or removes product from list 
	*@param listId
	*@param productId
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 Jun 2014
	*/
	public void removeProductFromList(int listId, int productId) throws SQLException {
		executeStatement("DELETE FROM lists_products WHERE ProductID = " + String.valueOf(productId) + " AND ListID = " + String.valueOf(listId));
	}

	/**
	* Updates the quantity for the specified product in the specified list
	*<p>User adds or removes product from list 
	*@param listId
	*@param productId
	*@param quantity
	*@throws SQLException
	*<p> Date Modified: 6 Jun 2014
	*/
	public void updateQuantity(int listId, int productId, int quantity) throws SQLException {
		executeStatement("UPDATE lists_products SET Quantity = " + String.valueOf(quantity) + " WHERE ProductID = " + String.valueOf(productId) + " AND ListID = "
				+ String.valueOf(listId));
	}

	/**
	* Removes all lists on the database
	*@throws SQLException
	*<p> Date Modified: 6 Jun 2014
	*/
	public void removeAllLists() throws SQLException {
		executeStatement("DELETE FROM lists");
	}

	//
	// public void AddList(int Id, String listName) throws SQLException{
	// String qry = "INSERT INTO lists VALUES (" + String.valueOf(Id) + ", " + listName + ")";
	// executeStatement(qry);
	// }

	// public void AddList(String listName) throws SQLException{
	// String qry = "INSERT INTO lists (Name) VALUES ('" + listName + "')";
	// executeStatement(qry);
	// }

	/**
	* Adds a specified quantity of the product (specified from the productid) to the list (specified by the listid)
	*<p>User adds or removes product from list 
	*@param ListId
	*@param ProductId
	*@param quantity
	*@throws SQLException
	*<p> Date Modified: 6 Jun 2014
	*/
	public void AddProductToList(int ListId, int ProductId, int quantity) throws SQLException {
		String qry = "INSERT INTO lists_products VALUES (" + String.valueOf(ProductId) + ", " + String.valueOf(ListId) + ", " + quantity + ")";
		executeStatement(qry);
	}

	/**
	* Returnss the total number of products in all the lists
	*@return int with total products 
	*@throws SQLException
	*<p> Date Modified: 6 Jun 2014
	*/
	public int getItemsTotal() throws SQLException {
		String query = "SELECT sum(Quantity) as 'Total' FROM lists_products";
		ResultSet results = sendQuery(query);

		results.next();
		return results.getInt("Total");
	}

	/**
	* Gets the quantity of the specified product in the specified list
	*<p>User adds or removes product from list 
	*@param listId
	*@param productId 
	*@return product quantity as int
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 Jun 2014
	*/
	public int getProductQuantity(int listId, int productId) throws SQLException {
		String query = "SELECT Quantity FROM lists_products WHERE ProductID = " + String.valueOf(productId) + " AND ListID = " + String.valueOf(listId);
		ResultSet results = sendQuery(query);

		results.next();
		return results.getInt("Quantity");
	}

	/**
	* Returns a resultset all the products in a specific list
	*<p>User adds or removes product from list 
	*@param listId
	*@param productId
	*@return
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 Jun 2014
	*/
	// TODO May not be needed
	public ResultSet getProductsInList(int listId, int productId) throws SQLException {
		String query = "SELECT * FROM lists_products WHERE ProductID = " + String.valueOf(productId) + " AND ListID = " + String.valueOf(listId);
		return sendQuery(query);
	}

	/**
	* It updates the lists table with a new name for a specified List ID 
	*<p>Test: ListShoulBeUpdated in TestProductToLists
	*@param ListId
	*@param name
	*@throws SQLException
	*<p> Date Modified: 6 Jun 2014
	*/
	public void updateListName(int ListId, String name) throws SQLException {
		String query = "UPDATE lists SET Name = '" + name + "' WHERE ListID = " + String.valueOf(ListId);
		executeStatement(query);
	}

	/**
	*
	*<p>Test(s)/User Story that it satisfies
	*@param listId
	*@return
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 Jun 2014
	*/
	// ToDo may not needed
	public ResultSet getList(int listId) throws SQLException {
		String query = "SELECT * FROM lists WHERE ListID = " + String.valueOf(listId);
		return sendQuery(query);
	}

	/**
	*It gets categories in DB
	*<p>User adds or removes product from list 
	*@return Resultset which contains categories
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 Jun 2014
	*/
	public ResultSet getCategories() throws SQLException {
		String query = "SELECT * FROM categories";
		return sendQuery(query);
	}

	/**
	*Gets the total number of items in a specified list
	*<p>TUser adds or removes product from list
	*@param listId
	*@return
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 Jun 2014
	*/
	// ToDo what does it do ?
	public ResultSet getAllListItems(int listId) throws SQLException {
		openConnection();
		java.sql.PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT p.ProductID, p.Name, p.Price, p.CategoryID, p.IsFavourite, p.Image, lp.Quantity FROM lists l join lists_products lp on lp.ListID = l.ListID join products p on p.ProductID = lp.ProductID WHERE l.ListID = ?");

		preparedStatement.setInt(1, listId);

		ResultSet set = preparedStatement.executeQuery();
		return set;
	}

	/**
	*Gets the items in a specified list by specified category
	*<p>Test(s)/User Story that it satisfies
	*@param listId
	*@param categoryName
	*@return
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 Jun 2014
	*/
	// ToDo may not be needed
	public ResultSet getListItemsByCategory(int listId, String categoryName) throws SQLException {
		openConnection();
		java.sql.PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT p.ProductID, p.Name, p.Price, p.CategoryID, p.IsFavourite, p.Image, lp.Quantity FROM smarttrolley.lists l\n"
						+ "join smarttrolley.lists_products lp on lp.ListID = l.ListID\n" + "join smarttrolley.products p on p.ProductID = lp.ProductID\n"
						+ "join smarttrolley.categories c on c.CategoryID = p.CategoryID\n" + "where c.Name = ? AND l.ListID = ?");

		preparedStatement.setString(1, categoryName);
		preparedStatement.setInt(2, listId);

		ResultSet set = preparedStatement.executeQuery();
		return set;
	}

	/**
	*It removes the list from the DB
	*<p>Test(s)/User Story that it satisfies
	*@param listId
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 Jun 2014
	*/
	// ToDo may not be needed
	public void removeList(int listId) throws SQLException {
		executeStatement("DELETE FROM lists WHERE ListID = " + String.valueOf(listId));
	}

	/**
	*Gets all the lists
	*<p>Test(s)/User Story that it satisfies
	*@return
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 Jun 2014
	*/
	// ToDo may not be needed
	public ResultSet getAllLists() throws SQLException {
		String query = "SELECT * FROM lists";
		return sendQuery(query);
	}

	/**
	*Gets all the products 
	*<p>Test(s)/User Story that it satisfies
	*@return resultset contains products
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 Jun 2014
	*/
	public ResultSet getAllProducts() throws SQLException {
		String query = "SELECT * FROM products";
		return sendQuery(query);
	}

	/**
	*Gets products by specified category name
	*<p>Test(s)/User Story that it satisfies
	*@param categoryName
	*@return
	*@throws SQLException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 Jun 2014
	*/
	public ResultSet getProductsByCategory(String categoryName) throws SQLException {
		openConnection();
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from smarttrolley.products p "
				+ "join smarttrolley.categories c on c.CategoryID = p.CategoryID where c.Name = ?");

		preparedStatement.setString(1, categoryName);

		ResultSet set = preparedStatement.executeQuery();
		return set;
	}
	// ///////////////////////////////////////////////////

}
/**************End of SqlConnection**************/
