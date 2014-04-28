/**
 * Product
 * 
 * Class Description: 
 * Product stores the product's data including:
 * path to product image, product name, product price
 * 
 * @author Arne
 * 
 * @author [Checked By:] [Checker(s) fill here]
 * 
 * @version [1.0] [Date Created: 28/02/14]
 */

package smarttrolleygui;

public class Product {
    private final String imageURL;
    private final String productName;
    private final String productPrice;

    /**
     * Constructor creates a new product
     *<p> Date Modified: 28 Feb 2014
     */
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
/**************End of Product**************/