package smarttrolleygui;

import static org.junit.Assert.*;
import javafx.collections.ObservableList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryFilteringTests {
	
	private ExampleShoppingListController shoppingList;
	private ObservableList<String> categoryList;
	
	@Before
	public void setup(){
		shoppingList = new ExampleShoppingListController();
		
	}
	
	@Test
	public void getCategoryIDTest(){
		
		categoryList = shoppingList.initializeCategories();
		assertFalse(categoryList == null);
		
	}
	
	
}
