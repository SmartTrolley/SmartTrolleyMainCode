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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxResult;
import se.mbaeumer.fxmessagebox.MessageBoxType;
import smarttrolleygui.SlideShow.PlayDirection;
import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;

public class ProductScreenController extends ControllerGeneral implements Initializable {

	/**Message Box Height*/
	private final double MSG_BX_H = 100.0;

	/**Message Box Width*/
	private final double MSG_BX_W = 400.0;

	/* Height of the button */
	private static final int BTN_HEIGHT = 20;

	/* Width of the button */
	private static final int BTN_WIDTH = 30;

	// Default button width is 30
	protected Button prevSLideButton = new Button("<");
	protected Button nextSLideButton = new Button(">");
	protected Button playPauseButton = new Button("P");

	public static SqlConnection productsDatabase;

	/** Application that is running */
	private SmartTrolleyGUI application;
	private Product product;
	// TODO Remove these if they're still not used after merge
	private String productName;
	private String productImageURL;
	private float productPrice;

	/**VBox that shows the price and slide control buttons*/
	@FXML
	private VBox priceAndSlideButtonsVBox;

	/**Anchor Pane containing the slide*/
	@FXML
	private AnchorPane productAnchorPane;
	@FXML
	private Label listNameLabel;

	/**Current slideshow that is playing*/
	private SlideShow currentSlideShow;

	Button favoritesButton = new Button();
	private CheckBox playDirectionCheckbox = new CheckBox("Play Direction");

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

		createPrevPlayNxtSlideButtons();
	}

	/**
	*This method sets the slideshow to be played
	*<p>User can view PWS Compatible slideshow
	*@param slideShow
	*<p> Date Modified: 25 May 2014
	*/
	protected void setSlideShow(SlideShow slideShow) {
		this.currentSlideShow = slideShow;

		playDirectionCheckbox.setText("Play Forwards");

		currentSlideShow.playDirection = PlayDirection.FOR;
		SmartTrolleyToolBox.print("Play direction is forward");

		playDirectionCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {

				playDirectionCheckbox.setText("Play Reverse");
				currentSlideShow.playDirection = PlayDirection.REV;
				SmartTrolleyToolBox.print("Play direction is reverse");

			}
		});

	}

	/**
	 * getCurrentProductData retrieves the data of the selected product from the sql database
	 * Date Modified: 22 May 2014
	 */
	protected void getCurrentProductData() {
		productsDatabase = new SqlConnection();
		String criteria = "productID";
		String value = String.valueOf(SmartTrolleyGUI.getCurrentProductID());
		product = productsDatabase.getSpecificProduct(criteria, value);
		productName = product.getName();
		productImageURL = product.getImage();
		productPrice = product.getPrice();
	}

	/**
	 * This method creates the previous slide, play and next slide buttons and adds their action listeners
	 * <p> User views products
	 * <p>Date Modified: 24 May 2014
	 */
	private void createPrevPlayNxtSlideButtons() {

		createSlideButton(prevSLideButton);
		createSlideButton(nextSLideButton);
		createSlideButton(playPauseButton);

		nextSLideButton.defaultButtonProperty().set(true);

		prevSLideButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SmartTrolleyToolBox.print("Pressed prev slide");

				if (currentSlideShow == null) {
					noSlideShowLoaded(event);
				} else {

					currentSlideShow.prevSlide();
				}
			}
		});

		nextSLideButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				SmartTrolleyToolBox.print("Pressed next slide");

				if (currentSlideShow == null) {
					noSlideShowLoaded(event);

				} else {

					currentSlideShow.nextSlide();
				}
			}
		});

		playPauseButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (currentSlideShow == null) {

					noSlideShowLoaded(event);

				} else {

					if (currentSlideShow.autoPlay) {
						disableAutoPlay();
					} else {
						currentSlideShow.play();
					}
					SmartTrolleyToolBox.print("Pressed play button");
				}
			}
		});

		priceAndSlideButtonsVBox.getChildren().add(playDirectionCheckbox);

		// Create a HBox for holding the control buttons
		HBox SlideControlButtonsHBox = new HBox();
		SlideControlButtonsHBox.getChildren().add(prevSLideButton);
		SlideControlButtonsHBox.getChildren().add(playPauseButton);
		SlideControlButtonsHBox.getChildren().add(nextSLideButton);
		priceAndSlideButtonsVBox.getChildren().add(SlideControlButtonsHBox);
	}

	/**
	* Disables auto play, called when the next or previous slide buttons are pressed
	*<p>User can view PWS Compatible slideShow
	*<p> Date Modified: 28 May 2014
	*/
	private void disableAutoPlay() {
		// Cancel timer and stop autoplay if autoplay is on
		if (currentSlideShow.autoPlay) {
			currentSlideShow.slideTimer.cancel();
			currentSlideShow.autoPlay = false;
			SmartTrolleyToolBox.print("Autoplay now off");
		}
	}

	/**
	* Displays a message box to the user and takes them to the start page to load in a slideShow
	*<p>User can view PWS Compatible slideshow
	*@param event
	*<p> Date Modified: 28 May 2014
	*/
	private void noSlideShowLoaded(ActionEvent event) {
		SmartTrolleyToolBox.print("No slideshow loaded");

		MessageBox noSldShowMsgBx = new MessageBox("No slideshow exists, please load one.", MessageBoxType.OK_ONLY);

		/*
		 * The two lines below to set the
		 * message box height and width
		 * are there because the message box
		 * resized itself (became very small)
		 * when multiple lists were deleted.
		 */
		noSldShowMsgBx.setHeight(MSG_BX_H);
		noSldShowMsgBx.setWidth(MSG_BX_W);

		noSldShowMsgBx.showAndWait();

		if (noSldShowMsgBx.getMessageBoxResult() == MessageBoxResult.OK) {
			loadScreen(Screen.STARTSCREEN, application);
		}
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
	* calls the static loadStartScreen method in ControllerGeneral.java
	 *
	 * @param event - response to click on smart trolley logo in navigation bar
	 * <p>
	 * Date Modified: 6 Mar 2014
	 */
	public void loadStartScreen(ActionEvent event) {
		loadScreen(Screen.STARTSCREEN, application);
	}

	/**
	 * loadHomeScreen is called when the 'home' button is pressed. It calls the
	 * calls the static loadHomeScreen method in ControllerGeneral.java
	 * <p>
	 * User navigates through product database
	 *
	 * @param event - response to click on 'home' button
	 * <p>
	 * Date Modified: 28 Feb 2014
	 */
	public void loadHomeScreen(ActionEvent event) {
		loadScreen(Screen.HOMESCREEN, application);
	}

	/**
	 * loadShoppingList is called when the 'list' button is pressed. It calls
	 * the goToShoppingList method in SmartTrolleyGUI.java
	 * <p> User can view shopping list
	 *
	 * @param event - response to click on 'list' button
	 * <p> Date Modified: 6 Mar 2014
	 */
	public void loadShoppingList(ActionEvent event) {
		loadScreen(Screen.SHOPPINGLISTSCREEN, application);
	}

	/**
	 * loadOffers is called when the 'offers' button is pressed. It calls the
	* calls the static loadOffers method in ControllerGeneral.java
	 * <p>
	 * User can browse store's offers
	 *
	 * @param event - response to click on 'offers' button
	 * <p>
	 * Date Modified: 7 Mar 2014
	 */
	public void loadOffers(ActionEvent event) {
		loadScreen(Screen.OFFERSSCREEN, application);
	}

	/**
	 * loadFavourites is called when the 'favourites' button is pressed. It
	 * calls the static loadFavourites method in ControllerGeneral.java
	 * <p>
	 * User can maintain list of favourite products
	 * 
	 * @param event - response to click on 'favourites' button
	 * <p> Date Modified: 28 Feb 2014
	 */
	public void loadFavourites(ActionEvent event) {
		loadScreen(Screen.FAVORITESSCREEN, application);
	}

	/**
	 * This method places the button as specified in the arguments
	 * <p> User views products
	 * @param btn
	 * @param x_coord
	 * @param y_coord
	 * <p> Date Modified: 22 May 2014
	 */
	private void createSlideButton(Button btn) {

		if (btn.getText() != null && !btn.getText().equals("")) {
			btn.setId(btn.getText());
		}

		btn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
	}

	/**
	* Returns the current slideshow that's running
	*<p> User views products
	*@return currentSlideShow - The current slideshow playing
	*<p> Date Modified: 25 May 2014
	*/
	protected SlideShow getCurrentSlideShow() {
		return currentSlideShow;
	}

	/**
	* This method returns the anchorPane which contains the slide
	*<p> User views products
	*@return productAnchorPane - The AnchorPane used
	*<p> Date Modified: 25 May 2014
	*/
	protected AnchorPane getProductAnchorPane() {
		return productAnchorPane;
	}
}
/**
 * ************End of ProductScreenController*************
 */
