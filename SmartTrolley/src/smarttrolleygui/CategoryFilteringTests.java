package smarttrolleygui;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryFilteringTests {
	
	private ExampleShoppingListController shoppingList;
	private String categoryID;
	
	@Before
	public void setup(){
		shoppingList = new ExampleShoppingListController();
	}
	
	@Test
	public void getCategoryIDTest(){
		
		
		assertFalse(categoryID == null);
		
	}
	
	
}
