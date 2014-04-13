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
    private final double productPrice;
    private int quantity = 0;
    //TODO Add total savings column

    /**
     * Constructor creates a new product
     *<p> Date Modified: 28 Feb 2014
     */
    Product(String imageURL, String productName, double productPrice) {
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
    
    public double getProductPrice() {
        return this.productPrice;
    }

    //TODO Comments for some methods in this class, esp. User stories
	/**Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@return The products' quantity
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 12 Apr 2014
	*/
	public int getQuantity() {
		return quantity;
	}
	
	/**
	*Sets the products' quantity
	*<p>Test(s)/User Story that it satisfies??
	*<p> Date Modified: 12 Apr 2014
	*/
	public void setQuantity(int newQuantity) {
		quantity = newQuantity;
	}

	/**
	*Increments the products' quantity
	*<p>Test(s)/User Story that it satisfies??
	*<p> Date Modified: 12 Apr 2014
	*/
	public void incrQuantity() {
		quantity++;		
	}

	/**
	*Decrements the products' quantity
	*<p>Test(s)/User Story that it satisfies??
	*<p> Date Modified: 12 Apr 2014
	*/
	public void decrQuantity() {
		quantity--;		
	}
}
/**************End of Product**************/