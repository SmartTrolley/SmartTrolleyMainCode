package smarttrolleygui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Arash
 */
public class ListProduct extends Product{

    private IntegerProperty quantity = new SimpleIntegerProperty();
    
    public ListProduct() {
		// TODO Auto-generated constructor stub
    	super();
	}
    
    public ListProduct(String imageURL, String productName, float productPrice, Integer categoryID, Boolean isFavourite, Integer quantity, Integer ID) {
        super(imageURL, productName, productPrice, categoryID, isFavourite, ID);
        
        this.quantity.set(quantity);
    }
 
	public Integer getQuantity(){
        return this.quantity.getValue();
    }
    
    public void setQuantity(Integer quantity){
        this.quantity.set(quantity);
    }
    
    public IntegerProperty getQuantityProperty(){
        return this.quantity;
    }
}
