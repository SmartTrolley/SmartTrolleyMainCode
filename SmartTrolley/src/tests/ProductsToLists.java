package tests;

import static org.junit.Assert.*;
import javafx.collections.ObservableList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smarttrolleygui.Product;
import DatabaseConnectors.*;

import java.sql.*;

import javafx.collections.ObservableList;

public class ProductsToLists {
	
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
		String delQry = "DELETE FROM lists";
		String qry = "INSERT INTO lists VALUES (1, 'Sea'), (2, 'Party')";
		
		productsDatabase.execute(delQry); //Clean previous lists first
		productsDatabase.execute(qry);
	}
	
	@After
	public  void setup2(){
		productsDatabase.closeConnection();
	}
	
	@Test
	public void NewItemShouldBeAddedToDB() throws SQLException {
		//Clear DB
		query = "DELETE FROM lists_products";
		productsDatabase.execute(query);
		
		//This query adds the product 'Hovis Bread' to the list 'Sea' with a quantity of 2
		String query = "INSERT INTO lists_products VALUES (4, 1, 22)";
		productsDatabase.execute(query);
		
		//Now check the result
		query = "SELECT * FROM lists_products WHERE ProductID = 4 AND ListID = 1 AND Quantity = 22";
		ResultSet results = productsDatabase.sendQuery(query);
		
		results.next();
		assertEquals(results.getInt("ProductID"), 4);
		assertEquals(results.getInt("ListID"), 1);
		assertEquals(results.getInt("Quantity"), 22);
		
		//clean up the database
		query = "DELETE FROM lists_products WHERE ProductID = 4 AND ListID = 1 AND Quantity = 22";
		productsDatabase.execute(query);
		
	}
	
	@Test
	public void TotalItemsOnListShouldBe15() throws SQLException {
		//Clear DB
		query = "DELETE FROM lists_products";
		productsDatabase.execute(query);
				
		//This query adds two products to the list with ID of 2 (Total = 15)
		String query = "INSERT INTO lists_products VALUES (2, 2, 12), (1, 2, 3)";
		productsDatabase.execute(query);
		
		//Now check the result
		query = "SELECT sum(Quantity) as 'Total' FROM lists_products";
		ResultSet results = productsDatabase.sendQuery(query);
		
		results.next();
		assertEquals(results.getInt("Total"), 15);
		
		//Clear DB
		query = "DELETE FROM lists_products";
		productsDatabase.execute(query);
		
	}
	
	@Test
	public void ItemQuantityOnListShouldBe5() throws SQLException {
		//Clear DB
		query = "DELETE FROM lists_products";
		productsDatabase.execute(query);
				
		//This query adds a product with quantity of 5 to list 2
		String query = "INSERT INTO lists_products VALUES (3, 2, 5)";
		productsDatabase.execute(query);
		
		//Now check the result
		query = "SELECT Quantity FROM lists_products WHERE ProductID = 3";
		ResultSet results = productsDatabase.sendQuery(query);
		
		results.next();
		assertEquals(results.getInt("Quantity"), 5);
		
		//Clear DB
		query = "DELETE FROM lists_products";
		productsDatabase.execute(query);
		
	}
	
	@Test
	public void ListShoulBeUpdated() throws SQLException {
		//Clear DB
		query = "DELETE FROM lists";
		productsDatabase.execute(query);
				
		//This query adds two lists to the lists table
		String query = "INSERT INTO lists VALUES (1, 'SeaFood'), (2, 'Italian')";
		productsDatabase.execute(query);
		
		//Now check the name of the first item in the resultset (does not matter which one is returned)
		query = "SELECT * FROM lists";
		ResultSet results = productsDatabase.sendQuery(query);
		
		results.next();
		
		Integer id = results.getInt("ListID");
		String name = results.getString("Name");
		String changedName = name + "Changed";
		
		//Now update the name
		query = "UPDATE lists SET Name = '"+ changedName +"' WHERE ListID = " + id;
		productsDatabase.execute(query);
		
		//Get the changed list
		query = "SELECT Name FROM lists WHERE ListID = " + id.toString();
		ResultSet results1 = productsDatabase.sendQuery(query);
		
		results1.next();
		assertEquals(changedName, results1.getString("Name"));
		
		//Clear DB
		query = "DELETE FROM lists";
		productsDatabase.execute(query);
		
	}
}






