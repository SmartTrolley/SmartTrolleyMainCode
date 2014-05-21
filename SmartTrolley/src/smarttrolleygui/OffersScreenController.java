/**
 * OffersScreenController
 * 
 * Class Description: OffersScreenController allows java interaction with
 * OffersScreen.fxml
 * 
 * @author Arne
 * 
 * @author [Checked By:] [Checker(s) fill here]
 * 
 * @version [1.0] [Date Created: 07/03/14]
 */
package smarttrolleygui;

import java.net.URL;
import java.util.ResourceBundle;

import DatabaseConnectors.SqlConnection;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class OffersScreenController implements Initializable {

	@FXML
	private ListView<String> categoriesList;
	@FXML
	private TableView<Product> productTable;
	@FXML
	private TableColumn<Product, Product> imageColumn;
	@FXML
	private TableColumn<Product, Product> productNameColumn;
	@FXML
	private TableColumn<Product, Float> priceColumn;
	@FXML
	private TableColumn<Product, Float> offerPriceColumn;
	@FXML
	private TableColumn<Product, Product> addColumn;

	private SmartTrolleyGUI application;
	private ObservableList<String> categories;
	private ObservableList<Product> productData;

	private ControllerGeneral controller = new ControllerGeneral();

	/**
	 * initialize is automatically called when the controller is created.
	 * <p>
	 * Date Modified: 07 Mar 2014
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Fill list on the LHS of the screen with different product categories
		categories = initializeCategories();
		categoriesList.setItems(categories);

		initializeProductTable();
	}

	/**
	 * setApp
	 * 
	 * @param application
	 *            <p>
	 *            Date Modified: 28 Feb 2014
	 */
	public void setApp(SmartTrolleyGUI application) {
		this.application = application;
	}

	/**
	 * loadStartScreen is called when the smart trolley logo is pressed. It
	 * calls the goToStartScreen method in SmartTrolleyGUI.java
	 * 
	 * @param event
	 *            - response to click on smart trolley logo in navigation bar
	 *            <p>
	 *            Date Modified: 6 Mar 2014
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
	 * @param event
	 *            - response to click on 'home' button
	 *            <p>
	 *            Date Modified: 28 Feb 2014
	 */
	public void loadHomeScreen(ActionEvent event) {
		controller.loadHomeScreen(event, application);
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
	 * loadShoppingList is called when the 'list' button is pressed. It calls
	 * the goToShoppingList method in SmartTrolleyGUI.java
	 * <p>
	 * User can view shopping list
	 * 
	 * @param event
	 *            - response to click on 'offers' button
	 *            <p>
	 *            Date Modified: 6 Mar 2014
	 */
	public void loadShoppingList(ActionEvent event) {
		controller.loadShoppingList(event, application);
	}

	/**
	 * initializeCategories sets up the list of categories that will be
	 * displayed on screen.
	 * <p>
	 * User can navigate through product database.
	 * 
	 * @return categories - list of categories
	 *         <p>
	 *         Date Modified: 7 Mar 2014
	 */
	private ObservableList<String> initializeCategories() {
		categories = FXCollections.observableArrayList("All", "Bakery",
				"Fruit & Vegetables", "Dairy & Eggs", "Meat & Seafood",
				"Frozen", "Drinks", "Snacks & Sweets", "Desserts");

		return categories;
	}

	/**
	 * initializeProductTable fills the TableView with data and sets up cell
	 * factories
	 * <p>
	 * User can navigate through product database
	 * <p>
	 * Date Modified: 9 Mar 2014
	 */
	private void initializeProductTable() {

		// Create new SqlConnection to retrieve product data
		SqlConnection sqlConnector = new SqlConnection();

		// Get offers data
		productData = sqlConnector.getListOfOffers();

		// set up column cell value factories
		priceColumn
				.setCellValueFactory(new PropertyValueFactory<Product, Float>(
						"price"));
		offerPriceColumn
				.setCellValueFactory(new PropertyValueFactory<Product, Float>(
						"offerPrice"));
		controller.setUpCellValueFactory(productNameColumn);
		controller.setUpCellValueFactory(addColumn);
		controller.setUpCellValueFactory(imageColumn);

		// set up cell factories for columns with 'interactive' cells 
		controller.setUpImageCellFactory(imageColumn);
		controller.setUpProductNameCellFactory(productNameColumn);
		controller.setUpAddButtonCellFactory(addColumn);
		
		// populate table with product data
		productTable.setItems(productData);
	}
}
/************** End of OffersScreenController **************/
