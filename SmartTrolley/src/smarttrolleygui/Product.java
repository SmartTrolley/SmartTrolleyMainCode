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
    private Integer productID;
    private String imageURL;
    private  String productName;
    private  Double productPrice;
    private  Integer categoryID;
    private  Boolean IsFavourite;

    /**
     * Constructor creates a new product
     *<p> Date Modified: 28 Feb 2014
     */
    Product(String imageURL, String productName, Double productPrice, Integer categoryID, Boolean isFavourite, Integer productID) {
    	this.imageURL = imageURL;
        this.productName = productName;
        this.productPrice = productPrice;
        this.categoryID = categoryID;
        this.IsFavourite = isFavourite;
        this.productID = productID;
    }
    
    public String getImageURL() {
        return this.imageURL;
    }
    
    public Integer getID(){
        return this.productID;
    }
    
    public String getProductName() {
        return this.productName;
    }
    
    public Double getProductPrice() {
        return this.productPrice;
    }
    
    public int getCategoryID(){
        return this.categoryID;
    }
    
    public boolean getIsFavourite(){
        return this.IsFavourite;
    }
    
    //Setters
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    public void setProductName(String productName) {
         this.productName = productName;
    }
    
    public void setProductPrice(Double productPrice) {
         this.productPrice = productPrice;
    }
    
    public void setcategoryID(Integer categoryID){
         this.categoryID = categoryID;
    }
    
    public void setIsFavourite(Boolean IsFavourite){
         this.IsFavourite = IsFavourite;
    }
}
/**************End of Product**************/