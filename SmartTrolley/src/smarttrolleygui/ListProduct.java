/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smarttrolleygui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Omid
 */
public class ListProduct extends Product{

    private IntegerProperty quantity = new SimpleIntegerProperty();
    
    public ListProduct(String imageURL, String productName, Double productPrice, Integer categoryID, Boolean isFavourite, Integer quantity, Integer ID) {
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
