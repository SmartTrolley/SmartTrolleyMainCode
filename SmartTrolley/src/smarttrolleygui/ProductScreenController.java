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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;


public class ProductScreenController implements Initializable {    
	
	/**An image's x-co-ordinate in the slide*/
	static final double IMAGE_X_COORD = 25;
	
	/**An image's y-co-ordinate in the slide*/
	static final double IMAGE_Y_COORD = 25;
	
	/* Height of the button */
	private static final int BTN_HEIGHT = 20;

	/* Width of the button */
	private static final int BTN_WIDTH = 30;

	private static final double X_COORD_CONTROLBUTTONS = 20.0;
	private static final double Y_COORD_CONTROLBUTTONS = 20.0;

	// Default button width is 30
	Button prevSLideButton = new Button("<");
	Button nextSLideButton = new Button(">");
	Button moreButton = new Button("...");
    
	public static SqlConnection productsDatabase;
	
	/** Application that is running */
    private SmartTrolleyGUI application;
    
    private ControllerGeneral controller = new ControllerGeneral();
    private Product product;
    private String productName;
    private String productImageURL;
    private float productPrice;
    
    @FXML private AnchorPane productAnchorPane;
    @FXML private Label listNameLabel;
    
    
    /**Current slideshow that is playing*/
    private SlideShow currentSlideShow;
    
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
		
		/*I am sending a slide in, so an image does not have to be added in.
		 * Hence, I am commenting out the code to add the image to anchorpane.
		 */
		// add product image to anchorpane
		/*Image productImage = new Image(getClass().getResourceAsStream(productImageURL));
		ImageView productImageView = new ImageView(productImage);
		productImageView.setX(400);
		productImageView.setY(400);
		productAnchorPane.getChildren().add(productImageView);*/
		
		createPrevMoreNxtSlideButtons();
    }
    
    /**
	*This method sets the slideshow to be played
	*<p>User can view PWS Compatible slideshow
	*@param slideShow
	*<p> Date Modified: 25 May 2014
	*/
	public void setSlideShow(SlideShow slideShow){
		this.currentSlideShow = slideShow;
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
	 * This method creates the previous slide, more and next slide buttons and adds their action listeners
	 * <p> User views products
	 * <p>Date Modified: 24 May 2014
	 */
	private void createPrevMoreNxtSlideButtons() {

		createSlideButton(prevSLideButton, X_COORD_CONTROLBUTTONS, Y_COORD_CONTROLBUTTONS);
		createSlideButton(nextSLideButton, X_COORD_CONTROLBUTTONS + 2 * BTN_WIDTH, Y_COORD_CONTROLBUTTONS);
		createSlideButton(moreButton, X_COORD_CONTROLBUTTONS + BTN_WIDTH, Y_COORD_CONTROLBUTTONS);

		prevSLideButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Add goto prev slide functionality here
				SmartTrolleyPrint.print("Pressed prev slide");
			}
		});

		nextSLideButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Add goto next slide functionality here
				SmartTrolleyPrint.print("Pressed next slide");
			}
		});

		moreButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Add goto more button functionality here
				SmartTrolleyPrint.print("Pressed more button");
			}
		});
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
	
	/**
	 * This method places the button as specified in the arguments
	 * <p> User views products
	 * @param btn
	 * @param x_coord
	 * @param y_coord
	 * <p> Date Modified: 22 May 2014
	 */
	private void createSlideButton(Button btn, Double x_coord, Double y_coord) {

		if (btn.getText() != null && !btn.getText().equals("")) {
			btn.setId(btn.getText());
		}

		btn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		// btn.setMinHeight(50);

		AnchorPane.setTopAnchor(btn, y_coord);
		AnchorPane.setLeftAnchor(btn, x_coord);

		getProductAnchorPane().getChildren().add(btn);

	}


	/**
	*Method/Test Description
	*<p> User views products
	*@return currentSlideShow - The current slideshow playing
	*<p> Date Modified: 25 May 2014
	*/
	public SlideShow getCurrentSlideShow() {
		return currentSlideShow;
	}

	/**
	* This method returns the anchorPane used
	*<p> User views products
	*@return productAnchorPane - The AnchorPane used
	*<p> Date Modified: 25 May 2014
	*/
	public AnchorPane getProductAnchorPane() {
		return productAnchorPane;
	}
}
/**
 * ************End of ProductScreenController*************
 */
