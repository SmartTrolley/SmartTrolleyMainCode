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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class ProductScreenController implements Initializable {    
    
    private SmartTrolleyGUI application;

    @FXML
    private AnchorPane productAnchorPane;
    
    /**
     * initialize is automatically called when the controller is created.
     * <p>
     * Date Modified: 22 Feb 2014
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	// add image to anchorpane
    	Image productImage = new Image(getClass().getResourceAsStream("img/SampleProducts/Activia.jpg"), 100, 100, true, true);
        ImageView productImageView = new ImageView(productImage);
        productImageView.setX(25);
        productImageView.setY(25);
        productAnchorPane.getChildren().add(productImageView);
        
        Button prevSLideButton = new Button("<");

		createSlideButton(prevSLideButton, 20.0, 20.0);			
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

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null");
        } else {
            application.goToStartScreen();
        }
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

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null");
        } else {
            application.goToHomeScreen();
        }
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

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null");
        } else {
            application.goToShoppingList();
        }
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

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null");
        } else {
            application.goToOffers();
        }
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

		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			System.out.println("error: application == null");
		} else {
			application.goToFavourites();
		}
	}



/**
 * This method places the button as specified in the arguments
 * <p>
 * User views products
 * 
 * @param btn
 * @param x_coord
 * @param y_coord
 *            <p>
 *            Date Modified: 22 May 2014
 */
private void createSlideButton(Button btn, Double x_coord, Double y_coord) {

	if (btn.getText() != null && !btn.getText().equals("")) {
		btn.setId(btn.getText());
	}
	
	//TODO See if a button is created without the prefsize and MinHeight params 
	//btn.setPrefSize(300, 80);
	//btn.setMinHeight(50);
	
	AnchorPane.setTopAnchor(btn, x_coord);
    AnchorPane.setLeftAnchor(btn, y_coord);
	
	productAnchorPane.getChildren().add(btn);

}
}
/**
 * ************End of ProductScreenController*************
 */
