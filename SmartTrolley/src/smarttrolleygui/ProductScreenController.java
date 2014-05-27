/**
 * ProductScreenController
 *
 * Class Description: ProductScreenController allows java interaction with
 * ProductScreen.fxml
 *
 * @author Arne
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.0] [Date Created: 22/05/14]
 */
package smarttrolleygui;

import java.net.URL;
import java.util.ResourceBundle;

import DatabaseConnectors.SqlConnection;
import Printing.SmartTrolleyPrint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class ProductScreenController implements Initializable {    
    
	public static SqlConnection productsDatabase;
    private SmartTrolleyGUI application;
    private ControllerGeneral controller = new ControllerGeneral();
    private Product product;
    private String productName;
    private String productImageURL;
    private float productPrice;
    
    @FXML private AnchorPane productAnchorPane;
    @FXML private Label listNameLabel;
    
    /**
     * initialize is automatically called when the controller is created.
     * <p>
     * Date Modified: 22 Feb 2014
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	getCurrentProductData();
    	// show name of current shopping list
		listNameLabel.setText(SmartTrolleyGUI.getCurrentListName());
		
		// add product image to anchorpane
		Image productImage = new Image(getClass().getResourceAsStream(productImageURL));
		ImageView productImageView = new ImageView(productImage);
		productImageView.setX(400);
		productImageView.setY(400);
		productAnchorPane.getChildren().add(productImageView);
    }
    
    /**
     * getCurrentProductData retrieves the data of the selected product from the sql database
     * Date Modified: 22 May 2014
     */
    private void getCurrentProductData() {
        productsDatabase = new SqlConnection();
        String criteria = "productID";
        String value = String.valueOf(SmartTrolleyGUI.getCurrentProductID());
		product = productsDatabase.getSpecificProduct(criteria, value);
		productName = product.getName();
		productImageURL = product.getImage();
		productPrice = product.getPrice();
	}

	/**
     * setApp
     *
     * @param application
     * <p>
     * Date Modified: 28 Feb 2014
     */
    public void setApp(SmartTrolleyGUI application) {
        this.application = application;
    }

    /**
     * loadStartScreen is called when the smart trolley logo is pressed. It
     * calls the goToStartScreen method in SmartTrolleyGUI.java
     *
     * @param event - response to click on smart trolley logo in navigation bar
     * <p>
     * Date Modified: 6 Mar 2014
     */
	public void loadStartScreen(ActionEvent event) {
		controller.loadStartScreen(event, application);
	}

    /**
     * loadHomeScreen is called when the 'home' button is pressed. It calls the
     * goToHomeScreen method in SmartTrolleyGUI.java
     * <p>
     * User navigates through product database
     *
     * @param event - response to click on 'home' button
     * <p>
     * Date Modified: 28 Feb 2014
     */
	public void loadHomeScreen(ActionEvent event) {
		controller.loadHomeScreen(event, application);
	}

    /**
     * loadShoppingList is called when the 'list' button is pressed. It calls
     * the goToShoppingList method in SmartTrolleyGUI.java
     * <p>
     * User can view shopping list
     *
     * @param event - response to click on 'list' button
     * <p>
     * Date Modified: 6 Mar 2014
     */
	public void loadShoppingList(ActionEvent event) {
		controller.loadShoppingList(event, application);
	}

    /**
     * loadOffers is called when the 'offers' button is pressed. It calls the
     * goToOffers method in SmartTrolleyGUI.java
     * <p>
     * User can browse store's offers
     *
     * @param event - response to click on 'offers' button
     * <p>
     * Date Modified: 7 Mar 2014
     */
	public void loadOffers(ActionEvent event) {
		controller.loadOffers(event, application);
	}
    
	/**
	 * loadFavourites is called when the 'favourites' button is pressed. It
	 * calls the goToFavourites method in SmartTrolleyGUI.java
	 * <p>
	 * User can maintain list of favourite products
	 * 
	 * @param event
	 *            - response to click on 'favourites' button
	 *            <p>
	 *            Date Modified: 28 Feb 2014
	 */
	public void loadFavourites(ActionEvent event) {
		controller.loadFavourites(event, application);
	}
}
/**
 * ************End of ProductScreenController*************
 */
