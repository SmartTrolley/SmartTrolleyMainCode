/**
 * ListProduct
 *
 * Class Description: 
 *
 * @author Arash & Jonny
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.0] [Date Created: 2/06/14]
 */

package smarttrolleygui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
