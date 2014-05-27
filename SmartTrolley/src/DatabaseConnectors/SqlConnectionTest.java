package DatabaseConnectors;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Printing.SmartTrolleyPrint;

import smarttrolleygui.Product;

import java.sql.*;

import javafx.collections.ObservableList;

/**
 * @author Thomas & Sam
 * 
 * Test case for testing the SqlConnection
 * 
 */
public class SqlConnectionTest {

	private static SqlConnection productsDatabase; 
	private ObservableList<Product> products;
	private ObservableList<Product> offers;
	
	public String query;
	
	/**
	 * Creates an instance of SqlConnection before the tests are performed
	 */
	@Before
	public  void setup(){
		productsDatabase = new SqlConnection();
		productsDatabase.openConnection();
	}
	
	@After
	public  void setup2(){
		productsDatabase.closeConnection();
	}
	
	/**
	 * Tests for an instance of connection and that the connection is not closed
	 * @throws SQLException
	 */
	@Test
	public void connectionOpenTest() throws SQLException {		
		
		assertFalse(productsDatabase.connection == null);
		assertFalse(productsDatabase.connection.isClosed());

	}
	
	/**
	 * test that the addDocumentDataContent() method adds to the table
	 * documents_info_data on the database
	 */
	@Test
	public void addDocumentDataContentTest() {
		SmartTrolleyPrint.print("\n Start of addDocumentDataTest............");
		
		String author = "Jack", 
			   version = "1.0", 
			   title = "some title",
			   comment = "some trolling comment";
		int width = 20, height = 50;
		
		productsDatabase.addDocumentDataContent(author, version, title, comment, width, height);
	}
	
	/**
	 * Tests that all data is deleted on the documents_info_data list on the database
	 */
	@Test
	public void deleteDocumentDataTest(){
		SmartTrolleyPrint.print("\n Start of deleteDocumentDataTest............");
		
		productsDatabase.deleteDocumentDataContent();
	}
	
	/**
	 * test the addDefaultsContent() method adds to the table
	 * defaults on the database
	 */
	@Test
	public void addDefaultsContentTest(){
		SmartTrolleyPrint.print("\n Start of addDefaultsContentTest............");
		
		String backgroundcolor = "black",
			   font = "comic Sans",
			   linecolor = "black",
			   fillcolor = "white";
		int fontsize = 25;
		
		productsDatabase.addDefaultsContent(backgroundcolor, font, fontsize, linecolor, fillcolor);
	}

	/**
	 * Tests that all data is deleted on the defaults list on the database
	 */
	@Test
	public void deleteDefaultsContentTest(){
		SmartTrolleyPrint.print("\n Start of deleteDefaultsContentTest............");
		
		productsDatabase.deleteDefaultsContent();
	}
	
	/**
	 * Test that contents is added to the audio table
	 */
	@Test
	public void addAudioTableContentsTest(){
		SmartTrolleyPrint.print("\n Start of addAudioTableContentsTest............");
		
		int productID = 1,
			startTime = 0;
		String urlName = "img/SampleProducts/large/ariel.jpg";
		double volume = 50;
		boolean loop = false;
		
		productsDatabase.addAudioTableContents(productID, startTime, urlName, volume, loop);
	}
	
	/**
	 * Test that all data is deleted on the audio table on the database
	 */
	@Test
	public void deleteAudioTableContentsTest(){
		SmartTrolleyPrint.print("\n Start of deleteAudioTableContentsTest............");
		
		productsDatabase.deleteContentAndResetAutoIncrement("audio");
	}
	
	/**
	 * Test that contents is added to the point table
	 */
	@Test
	public void addPointContentsTest(){
		SmartTrolleyPrint.print("\n Start of addPointContentsTest............");
		
		int productid = 1,
			shapeNo = 1,
			individualPointNo = 3,
			x = 56,
			y = 75;
		
		productsDatabase.addPointContents(productid, shapeNo, individualPointNo, x, y);
	}
	
	/**
	 * Test that all data is deleted on the point table on the database
	 */
	@Test
	public void deletePointContentsTest(){
		SmartTrolleyPrint.print("\n Start of deleteAudioTableContentsTest............");
		
		productsDatabase.deleteContentAndResetAutoIncrement("point");
	}
	
	/**
	 * Test that contents is added to the shape table
	 */
	@Test
	public void addShapeContentsTest(){
		SmartTrolleyPrint.print("\n Start of addShapeContentsTest............");
		
		int productid = 1,
			totalPoints = 4,
			width = 30,
			height = 30,
			starttime = 0,
			duration = 10,
			layer = 3,
			branch = 4;
		String fillcolor = "black",
			   linecolor = "blue";
		
		productsDatabase.addShapeContents(productid, totalPoints, width, height, starttime, duration, layer, branch, fillcolor, linecolor);
	}
	
	/**
	 * Test that all data is deleted on the shape table on the database
	 */
	@Test
	public void deleteShapeContentsTest(){
		SmartTrolleyPrint.print("\n Start of deleteShapeContentsTest............");
		
		productsDatabase.deleteContentAndResetAutoIncrement("shape");
	}
	
	/**
	 * Test that contents is added to the text table
	 */
	@Test
	public void addTextContentsTest(){
		SmartTrolleyPrint.print("\n Start of addTextContentsTest............");
		
		int productid = 1,
			fontSize = 30,
			xStart = 30,
			yStart = 0,
			startTime = 10,
			duration = 3,
			layer = 4,
			xend = 50,
			yend = 50;
		String font = "comic sans",
			   FontColor = "blue";
		
		productsDatabase.addTextContents(productid, fontSize, xStart, yStart, startTime, duration, layer, xend, yend, font, FontColor);
	}
	
	/**
	 * Test that all data is deleted on the text table on the database
	 */
	@Test
	public void deleteTextContentsTest(){
		SmartTrolleyPrint.print("\n Start of deleteTextContentsTest............");
		
		productsDatabase.deleteContentAndResetAutoIncrement("text");
	}
	
	/**
	 * Test that contents is added to the textbody table
	 */
	@Test
	public void addTextbodyContentsTest(){
		SmartTrolleyPrint.print("\n Start of addTextbodyContentsTest............");
		
		int productid = 1,
			TextNo = 30,
			Branch = 30;
		Boolean Bold = false,
				italic = false,
				underlined = false;
		String text = "LLLLLOOOOKKIIIE, some text";
		
		productsDatabase.addTextbodyContents(productid, TextNo, Branch, Bold, italic, underlined, text);
	}
	
	/**
	 * Test that all data is deleted on the textbody table on the database
	 */
	@Test
	public void deleteTextbodyContentsTest(){
		SmartTrolleyPrint.print("\n Start of deleteTextbodyContentsTest............");
		
		productsDatabase.deleteContentAndResetAutoIncrement("textbody");
	}
	
	/**
	 * Test that contents is added to the video table
	 */
	@Test
	public void addVideoContentsTest(){
		SmartTrolleyPrint.print("\n Start of addVideoContentsTest............");
		
		int productid = 1,
			starttime = 0,
			xstart = 40,
			ystart = 70,
			width = 100,
			height = 60,
			layer = 1,
			duration = 15;
		String urlname = "SOME URL";
		boolean loop = false;
			
		productsDatabase.addVideoContents(productid, urlname, starttime, loop, xstart, ystart, width, height, layer, duration);
	}
	
	/**
	 * Test that all data is deleted on the video table on the database
	 */
	@Test
	public void deleteVideoContentsTest(){
		SmartTrolleyPrint.print("\n Start of deleteVideoContentsTest............");
		
		productsDatabase.deleteContentAndResetAutoIncrement("video");
	}
	
	
	
//	/**
//	 * Sends and example query to the database
//	 * Checks that the database returns something then tests that the returned values are correct for the sent query
//	 * @throws SQLException
//	 */
//	@Test
//	public void sendQueryTest() throws SQLException {
//	
//		query = "SELECT * FROM products WHERE Name = 'Ariel'";
//		
//		ResultSet results = productsDatabase.sendQuery(query);
//		
//		assertFalse(results == null);
//		
//		Product product = new Product();
//		
//		
//		while (results.next()) {
//			
//			// get id
//			product.setId(results.getInt("ProductID"));
//			
//			// get Name
//			product.setName(results.getString("Name"));
//			
//			// get Image
//			product.setImage(results.getString("Image"));
//			
//			// get Price
//			product.setPrice(results.getFloat("Price"));
//			
//		}
//		
//		assertEquals(product.getId(), 1);
//		assertEquals(product.getName(), "Ariel");		
//		assertEquals(product.getImage(), "img/SampleProducts/ariel.jpg");
//		assert(product.getPrice() == 2.99);		
//	}
//	
//	/**
//	 * Test that the database returns the correct information corresponding to a product when queried by name
//	 */
//	@Test
//	public void getProductByNameTest(){
//		
//		
//		// retrieve results from server
//		Product product = productsDatabase.getProductByName("Cravendale 2L");
//		
//		assertEquals(product.getId(), 2);
//		assertEquals(product.getName(), "Cravendale 2L");		
//		assertEquals(product.getImage(), "img/SampleProducts/cravendale_2L_milk.jpg");
//		assert(product.getPrice() == 3.99);	
//	}
//	
//	/**
//	 * Tests that the getListOfProducts() returns a full list of the product data 
//	 * from the database by printing it to the console and manually comparing it.
//	 * 
//	 * @throws SQLException
//	 */
//	@Test
//	public void getListOfProductsTest() throws SQLException{
//		
//	products = productsDatabase.getListOfProducts();
//	Product product;
//	int i = 0;
//		while(i<products.size()){
//			
//			product = products.get(i);
//			
//			SmartTrolleyPrint.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice());
//			i++;
//		}
//	}
//	
//	/**
//	 * 
//	 */
//	@Test
//	public void getSpecificProductTest(){
//		
//		Product product;
//		
//		product = productsDatabase.getSpecificProduct("productID","5");
//		
//		assertEquals(product.getId(), 5);
//		assertEquals(product.getName(), "Innocent Noodle Pot");
//		assertEquals(product.getImage(), "img/SampleProducts/innocent_noodle_pot.jpg");
//		assert(product.getPrice() == 6.99);
//	}
//	
//	/**
//	 * Test that the getListOfOffers() returns the full list of offers
//	 * from the database by printing it to the console and then comparing it.
//	 */
//	@Test
//	public void getListOfOffersTest(){
//		
//		offers = productsDatabase.getListOfOffers();
//		
//		Product product;
//		int j = 0;
//		while(j<offers.size()){
//			
//			product = offers.get(j);
//			
//			SmartTrolleyPrint.print(product.getId() + "  " + product.getName() + "  " + product.getImage() + "  " + product.getPrice() + "  " + product.getOfferPrice() + "  " + product.getSavings());
//					
//			j++;
//		}
//		
//	}
//	
//	/**
//	 * Tests that the connection closes correctly
//	 * @throws SQLException
//	 */
//	@Test
//	public void connectionCloseTest() throws SQLException  {
//		
//		productsDatabase.closeConnection();
//	
//		assertTrue(productsDatabase.connection.isClosed());
//
//	}

	
}
