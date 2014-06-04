/**
* SmartTrolley
*
* A DESCRIPTION OF THE FILE
*
* @author Name1
* @author Name2
*
* @author Checked By: Checker(s) fill here
*
* @version version of this file [Date Created: 3 May 2014]
*/

/*YOUR CODE HERE*/



/**************End of Lists.java**************/
package smarttrolleygui;


//TODO A possible way to add listInProducts functionality is to 
//create a listInProducts hash table. Then whenever a quantity is updated/ product added to the list
//first check that it is not already present in the hash table, if it is, just update the quantity
//else add it to the hash table, and update the quantity. You will also need to find a way to store the 
//list information when the program is terminated i.e. write it to a .txt file or local database
//This can be left to a later iteration though.
public class Lists {
	private int quantityOfProduct = 0;
	private int productID = 0;
    private int listID = 0;

	/**
	 *Method/Test Description
	 *<p>Test(s)/User Story that it satisfies
	 *@param args
	 *[If applicable]@see [Reference URL OR Class#Method]
	 *<p> Date Modified: 3 May 2014
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param i
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 3 May 2014
	*/
	public void setListID(int listid){
		this.listID = listid;	
	}
	
	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param int1
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 3 May 2014
	*/
	public void setProductID(int productid) {
		this.productID = productid;
		
	}


	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param i
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 3 May 2014
	 * @return 
	*/
	public int getProductQuantity(int productID) {
		return this.quantityOfProduct;	
	}

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param int1
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 3 May 2014
	*/
	public void setQuantity(int quantity) {
		this.quantityOfProduct = quantity;
		
	}


}

