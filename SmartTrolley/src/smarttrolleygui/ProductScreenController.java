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

import Printing.SmartTrolleyPrint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

	// TODO Make this comment proper
	/** Application that is running */
	private SmartTrolleyGUI application;

	@FXML
	private AnchorPane productAnchorPane;	

	private SlideShow currentSlideShow;

	/**
	 * initialize is automatically called when the controller is created.
	 * <p> Date Modified: 22 Feb 2014
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		createPrevMoreNxtSlideButtons();
	}
	
	//TODO Perhaps make the constructor:
	//public ProductScreenController(SlideShow slideShow) {
		//this.currentSlideShow = slideShow;
	//}
	
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
	 * @param application
	 * <p> Date Modified: 28 Feb 2014
	 */
	public void setApp(SmartTrolleyGUI application) {
		this.application = application;
	}

	/**
	 * loadStartScreen is called when the smart trolley logo is pressed. It
	 * calls the goToStartScreen method in SmartTrolleyGUI.java
	 * 
	 * @param event - response to click on smart trolley logo in navigation bar
	 * <p>Date Modified: 6 Mar 2014
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
	 * <p> User navigates through product database
	 * @param event - response to click on 'home' button
	 * <p> Date Modified: 28 Feb 2014
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
	 * <p> User can view shopping list
	 * @param event - response to click on 'list' button
	 * <p> Date Modified: 6 Mar 2014
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
	 * <p> User can browse store's offers
	 * @param event - response to click on 'offers' button
	 * <p> Date Modified: 7 Mar 2014
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
	 * <p> User can maintain list of favourite products
	 * @param event - response to click on 'favourites' button
	 * <p> Date Modified: 28 Feb 2014
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
