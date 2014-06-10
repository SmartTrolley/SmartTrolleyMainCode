package smarttrolleygui;

/**
 * ProductsToListsTests
 * 
 * @author Arash & Jonny
 * @author Checked By: Checker(s) fill here
 * 
 * @version V2.1 [Date Created: 04/04/14]
 */

import static org.junit.Assert.*;
import javafx.collections.ObservableList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smarttrolleygui.ListProduct;
import smarttrolleygui.Product;
import DatabaseConnectors.*;

import java.sql.*;

import javafx.collections.ObservableList;


public class ProductsToListsTests {
	
	private static SqlConnection productsDatabase;
	private ObservableList<Product> products;
	
	public String query;
	
	/**
	 * Creates an instance of SqlConnection before the tests are performed
	 */
	@Before
	public  void setup() throws SQLException{
		productsDatabase = new SqlConnection();
		productsDatabase.openConnection();
		
		//Add these two temporary lists to the database for testing purposes
		//String qry = "INSERT INTO lists VALUES (4,'Sea'), (5,'Party')";
		
		productsDatabase.removeAllLists(); //Clean previous lists first
		productsDatabase.AddList(4, "Sea");
		productsDatabase.AddList(5, "Party");
	}
	
	@After
	public  void tearDown(){
		productsDatabase.closeConnection();
	}
	
	@Test
	public void NewItemShouldBeAddedToDB() throws SQLException {
		//Clear DB
//		query = "DELETE FROM lists_products";
//		productsDatabase.execute(query);
		productsDatabase.removeAllProductsInTheLists();
		
		//This query adds the product 'Hovis Bread' to the list 'Sea' with a quantity of 2
		//SqlConnection cn = new SqlConnection();
		//cn.addProductToList(4, 1, 22);
		productsDatabase.addProductToList(4, 1, 22);
		
		//Now check the result
		ObservableList<ListProduct> Info = productsDatabase.ReturnProductInfo(4, 1, 22);
		
		assertEquals(Info.get(0).getProductID(), 4);
		assertEquals(Info.get(0).getListId(), 1);
		assertEquals(Info.get(0).getProductQuantity(), 22);
		
		//clean up the database
		query = "DELETE FROM lists_products WHERE ProductID = 4 AND ListID = 1 AND Quantity = 22";
		productsDatabase.execute(query);
		
	}
	
	@Test
	public void TotalItemsOnListShouldBe15() throws SQLException {
		//Clear DB
		productsDatabase.removeAllProductsInTheLists();
				
		//This query adds two products to the list with ID of 2 (Total = 15)
		//String query = "INSERT INTO lists_products VALUES (2, 2, 12), (1, 2, 3)";
		productsDatabase.AddProductToList(2, 2, 12);
		productsDatabase.AddProductToList(1, 2, 3);
		
		//Now check the result
//		query = "SELECT sum(Quantity) as 'Total' FROM lists_products";
//		ResultSet results = productsDatabase.sendQuery(query);
//		
//		results.next();
		assertEquals(productsDatabase.getItemsTotal(), 15);
		
		//Clear DB
		productsDatabase.removeAllProductsInTheLists();
		
	}
	
	@Test
	public void ItemQuantityOnListShouldBe5() throws SQLException {
		//Clear DB
		productsDatabase.removeAllProductsInTheLists();
				
		//This query adds a product with quantity of 5 to list 2
		//String query = "INSERT INTO lists_products VALUES (3, 2, 5)";
		productsDatabase.AddProductToList(3, 2, 5);
		
		//Now check the result
		//query = "SELECT Quantity FROM lists_products WHERE ProductID = 3";
		//ResultSet results = productsDatabase.sendQuery(query);
		
		//results.next();
		assertEquals(productsDatabase.getProductQuantity(2, 3), 5);
		
		//Clear DB
		productsDatabase.removeAllProductsInTheLists();
		
	}
	
	@Test
	public void ListShoulBeUpdated() throws SQLException {
		//Clear DB
		productsDatabase.removeAllProductsInTheLists();
				
		//This query adds two lists to the lists table
		productsDatabase.AddList(4, "Sea");
		productsDatabase.AddList(5, "Party");
		
		//Now check the name of the first item in the resultset (does not matter which one is returned)
		//query = "SELECT * FROM lists WHERE ListID = 2";
		ResultSet results = productsDatabase.getList(2);
		
		results.next();
		
		Integer id = results.getInt("ListID");
		String name = results.getString("Name");
		String changedName = "Changed";
		
		//Now update the name
		//query = "UPDATE lists SET Name = '"+ changedName +"' WHERE ListID = 2 ";
		//productsDatabase.execute(query);
		productsDatabase.updateListName(2,  changedName);
		
		//Get the changed list
		
		//query = "SELECT * FROM lists WHERE ListID = 2";
		//ResultSet result1 = productsDatabase.sendQuery(query);
		ResultSet result1 = productsDatabase.getList(2);
		
		
		result1.next();
		assertEquals(changedName, result1.getString("Name"));
		
		
		//Clear DB
//		query = "DELETE FROM lists WHERE ListID = 4";
//		productsDatabase.execute(query);
		productsDatabase.removeList(4);
		
	}
}






