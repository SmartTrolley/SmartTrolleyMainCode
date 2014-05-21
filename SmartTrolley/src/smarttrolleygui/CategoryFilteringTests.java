package smarttrolleygui;

import static org.junit.Assert.*;
import javafx.collections.ObservableList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryFilteringTests {
	
	private ExampleShoppingListController shoppingList;
	private String categoryListNumber;
	
	@Before
	public void setup(){
		shoppingList = new ExampleShoppingListController();
		
	}
	
	/**
	 * This test will ensure that when a category is clicked, the category number is returned from the database
	 */
	@Test
	public void getCategoryIDTest(){
		
		
		categoryListNumber = shoppingList.getCategoryNumber();
		assertFalse(categoryListNumber == null);
		
	}
	
	
}
