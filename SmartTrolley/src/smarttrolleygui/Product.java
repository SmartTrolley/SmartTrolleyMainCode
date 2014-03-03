/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smarttrolleygui;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author me
 */
public class Product {
    private final String imageURL;
    private final String productName;
    private final String productPrice;

    Product(String imageURL, String productName, String productPrice) {
        this.imageURL = imageURL;
        this.productName = productName;
        this.productPrice = productPrice;        
    }
    
    public String getImageURL() {
        return this.imageURL;
    }
    
    public String getProductName() {
        return this.productName;
    }
    
    public String getProductPrice() {
        return this.productPrice;
    }
}
