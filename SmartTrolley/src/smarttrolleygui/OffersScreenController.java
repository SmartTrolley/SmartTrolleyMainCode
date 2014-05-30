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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import DatabaseConnectors.SqlConnection;

public class OffersScreenController extends ControllerGeneral implements Initializable {

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
	@FXML
	private Label listNameLabel;

	private SmartTrolleyGUI application;
	private ObservableList<String> categories;
	private ObservableList<Product> productData;

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
		// show name of current shopping list
		listNameLabel.setText(SmartTrolleyGUI.getCurrentListName());
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
	 * calls the static loadStartScreen method in ControllerGeneral.java
	 * @param event- response to click on smart trolley logo in navigation bar
	 * <p> Date Modified: 6 Mar 2014
	 */
	public void loadStartScreen(ActionEvent event) {
		loadScreen(Screen.STARTSCREEN, application);
	}

	/**
	 * loadHomeScreen is called when the 'home' button is pressed. It 
	 * calls the static loadHomeScreen method in ControllerGeneral.java
	 * <p> User navigates through product database
	 * @param event - response to click on 'home' button
	 * <p> Date Modified: 28 Feb 2014
	 */
	public void loadHomeScreen(ActionEvent event) {
		loadScreen(Screen.STARTSCREEN, application);
	}

	/**
	 * loadFavourites is called when the 'favourites' button is pressed. It
	* calls the static loadFavourites method in ControllerGeneral.java
	 * <p>User can maintain list of favourite products
	 * @param event - response to click on 'favourites' button
	 * <p> Date Modified: 28 Feb 2014
	 */
	public void loadFavourites(ActionEvent event) {
		loadScreen(Screen.FAVORITESSCREEN, application);	}

	/**
	 * loadShoppingList is called when the 'list' button is pressed. It calls
	 * the goToShoppingList method in SmartTrolleyGUI.java
	 * <p> User can view shopping list
	 * 
	 * @param event - response to click on 'offers' button
	 * <p> Date Modified: 6 Mar 2014
	 */
	public void loadShoppingList(ActionEvent event) {
		loadScreen(Screen.SHOPPINGLISTSCREEN, application);	}

	/**
	 * initializeCategories sets up the list of categories that will be
	 * displayed on screen.
	 * <p> User can navigate through product database.
	 * @return categories - list of categories
	 * <p> Date Modified: 7 Mar 2014
	 */
	private ObservableList<String> initializeCategories() {
		categories = FXCollections.observableArrayList("All", "Bakery", "Fruit & Vegetables", "Dairy & Eggs", "Meat & Seafood", "Frozen", "Drinks", "Snacks & Sweets", "Desserts");

		return categories;
	}

	/**
	 * initializeProductTable fills the TableView with data and sets up cell
	 * factories
	 * <p> User can navigate through product database
	 * <p> Date Modified: 9 Mar 2014
	 */
	private void initializeProductTable() {

		// Create new SqlConnection to retrieve product data
		SqlConnection sqlConnector = new SqlConnection();

		// Get offers data
		productData = sqlConnector.getListOfOffers();

		// set up column cell value factories
		priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
		offerPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Float>("offerPrice"));
		ControllerGeneral.setUpCellValueFactory(productNameColumn);
		ControllerGeneral.setUpCellValueFactory(addColumn);
		ControllerGeneral.setUpCellValueFactory(imageColumn);

		// set up cell factories for columns with 'interactive' cells
		ControllerGeneral.setUpImageCellFactory(imageColumn);
		ControllerGeneral.setUpAddButtonCellFactory(addColumn);

		// ControllerGeneral.setUpProductNameCellFactory(productNameColumn);
		// TODO: once refactored remove following code and uncomment previous line to set up cell factory for product name column
		productNameColumn.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
			@Override
			public TableCell<Product, Product> call(TableColumn<Product, Product> productNameColumn) {
				return new TableCell<Product, Product>() {
					final Button button = new Button();

					@Override
					public void updateItem(final Product product, boolean empty) {
						super.updateItem(product, empty);
						if (product != null) {
							setGraphic(button);
							button.setText(product.getName());
							button.setPrefHeight(80);
							button.getStyleClass().add("buttonProductNameTable");

							// Button Event Handler
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									System.out.println("Pressed name of product: " + product.getName());
									SmartTrolleyGUI.setCurrentProductID(product.getId());
									application.goToProductScreen();
								}
							});
						} else {
							setGraphic(null);
						}
					}
				};
			}
		});

		// populate table with product data
		productTable.setItems(productData);
	}
}
/************** End of OffersScreenController **************/
