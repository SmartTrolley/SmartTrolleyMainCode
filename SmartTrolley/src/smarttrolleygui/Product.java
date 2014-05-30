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

import slideshowdata.DataType;

public class Product implements DataType{
	private int id;
    private String image;
    private String name;
    private float price;
    private float offerPrice;
    private float savings;

	public int getId() {
		return id;
	}
    
    public void setId(int id) {
		this.id = id;
	}
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
		this.name = name;
	}
    
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
		this.image = image;
	}
    
     public float getPrice() {
        return this.price;
    }
     
    public void setPrice(float price) {
		this.price = price;
	}  
    
    public float getOfferPrice(){
    	return this.offerPrice;
    }
    
    public void setOfferPrice(float offerPrice){
    	this.offerPrice = offerPrice;
    }
    
    public float getSavings(){
    	return this.savings;
    }
    
    public void setSavings(float savings){
    	this.savings = savings;
    }
   
}
/**************End of Product**************/