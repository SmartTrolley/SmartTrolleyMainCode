/**
 * Product
 * 
 * Class Description: 
 * Offer stores the offers data:
 * OfferID, productID, Offer price
 * 
 * @author Sam
 * 
 * @author [Checked By:] [Checker(s) fill here]
 * 
 * @version [1.0] [Date Created: 5/05/14]
 */

package smarttrolleygui;

public class Offer {
	private int offerId;
	private int productId;
    private float offerPrice;

	public int getOfferId() {
		return offerId;
	}
    
    public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
    
	public int getProductId() {
		return productId;
	}
    
    public void setProductId(int productId) {
		this.productId = productId;
	}
    
     public float getOfferPrice() {
        return this.offerPrice;
    }
     
    public void setOfferPrice(float offerPrice) {
		this.offerPrice = offerPrice;
	}  
   
   
}
/**************End of Offer**************/