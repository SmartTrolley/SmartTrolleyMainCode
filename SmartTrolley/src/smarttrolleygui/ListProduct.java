/**
 * ListProduct
 *
 * Class Description: This Class extends product, it is used when the product is in a list. 
 *
 * @author V1.0 Arash & Jonny 
 * @author [Checked By:] [Checker(s) fill here]
 * 
 * @version [1.0] [Date Created: 8/06/14]
 */
package smarttrolleygui;

public class ListProduct extends Product{

    private int quantity;
    
    public ListProduct() {
		// TODO Auto-generated constructor stub
    	super();
	}
    
    public ListProduct(String imageURL, String productName, float productPrice, Integer categoryID, int isFavourite, Integer quantity, Integer ID) {
       // super(imageURL, productName, productPrice, categoryID, isFavourite, ID);
        
        setId(ID);
        setName(productName);
        setImage(imageURL);
        setPrice(productPrice);
        setFavorite(isFavourite);

       this.quantity = quantity;
    }
 
	public Integer getQuantity(){
        return this.quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    
    public int getQuantityProperty(){
        return this.quantity;
    }

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param createProductFromResultSet
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 6 Jun 2014
	*/
	public static ListProduct productToListProduct(Product product) {
		ListProduct listProduct = new ListProduct();
		
		listProduct.setImage(product.getImage());
		listProduct.setId(product.getID());
		listProduct.setName(product.getName());
		listProduct.setPrice(product.getPrice());
		listProduct.setFavorite(product.getFavorite());
		listProduct.setOfferPrice(product.getOfferPrice());
		//TODO get quantity of that product in the list
		listProduct.setQuantity(0);
		listProduct.setSavings(product.getSavings());
		
		return listProduct;
		
	}	
}
