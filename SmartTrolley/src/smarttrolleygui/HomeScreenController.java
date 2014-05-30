/**
 * HomeScreenController
 *
 * Class Description: HomeScreenController allows java interaction with
 * HomeScreen.fxml
 *
 * @author Arne
 * @author Alick Jacklin & Prashant Chakravarty V 1.1
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.0] [Date Created: 22/02/14]
 * @Verison [1.1] [Date Created: 30/05/2014] 
 */
package smarttrolleygui;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import DatabaseConnectors.SqlConnection;
import Printing.SmartTrolleyPrint;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class HomeScreenController implements Initializable {

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
	private TableColumn<Product, Product> addColumn;
	@FXML
	private Label listNameLabel;
	@FXML
	private TextField searchBox;
	@FXML
	private Button searchButton;
	
	

	private SmartTrolleyGUI application;
	private ObservableList<String> categories;
	private ObservableList<Product> productData;
	private ControllerGeneral controller = new ControllerGeneral();
	private String query;
	public static SqlConnection productsDatabase;
	private ResultSet resultSet;

	/**
	 * initialize is automatically called when the controller is created.
	 * <p>
	 * Date Modified: 22 Feb 2014
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
	 *            - response to click on 'list' button
	 *            <p>
	 *            Date Modified: 6 Mar 2014
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
	 * @param event
	 *            - response to click on 'offers' button
	 *            <p>
	 *            Date Modified: 7 Mar 2014
	 */
	public void loadOffers(ActionEvent event) {
		controller.loadOffers(event, application);
	}
	
	/**
	*Searches database for product entered into the TextField.
	*<p>User is able to search for product
	*@param event
	*@throws SQLException
	*<p> Date Modified: 30 May 2014
	*/
	public void searchForProducts(ActionEvent event) throws SQLException{
		
		productsDatabase = new SqlConnection();
		productsDatabase.openConnection();
		ObservableList<Product> products = FXCollections.observableArrayList();
		
		SmartTrolleyPrint.print(searchBox.getText());
		
		query  = "SELECT * FROM products WHERE name LIKE '" + searchBox.getText() + "%';";	
		
		try {
		 resultSet = productsDatabase.sendQuery(query);
		} catch (SQLException e) {
			SmartTrolleyPrint.print("unable to send query");
		}
		

			
			
			while (resultSet.next()) {
				
				Product product = new Product();
				
				SmartTrolleyPrint.print(resultSet.getString("Name"));
				// get id
				product.setId(resultSet.getInt("ProductID"));
				
				// get Name
				product.setName(resultSet.getString("Name"));
				
				// get Image
				product.setImage(resultSet.getString("Image"));
				
				// get Price
				product.setPrice(resultSet.getFloat("Price"));
				
				products.add(product);
			}
			
			 productTable.setItems(products);
			// initializeProductTable();
			
			
		productsDatabase.closeConnection();
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

		// Get product data
		productData = sqlConnector.getListOfProducts();

		// set up column cell value factories
		priceColumn
				.setCellValueFactory(new PropertyValueFactory<Product, Float>(
						"price"));
		controller.setUpCellValueFactory(productNameColumn);
		controller.setUpCellValueFactory(addColumn);
		controller.setUpCellValueFactory(imageColumn);

		// set up cell factories for columns with 'interactive' cells 
		controller.setUpImageCellFactory(imageColumn);
		controller.setUpAddButtonCellFactory(addColumn);

//		controller.setUpProductNameCellFactory(productNameColumn);
		// TODO: once refactored remove following code and uncomment previous line to set up cell factory for product name column
		productNameColumn
		.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
			@Override
			public TableCell<Product, Product> call(
					TableColumn<Product, Product> productNameColumn) {
				return new TableCell<Product, Product>() {
					final Button button = new Button();

					@Override
					public void updateItem(final Product product,
							boolean empty) {
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
									System.out
											.println("Pressed name of product: "
													+ product.getName());
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

/**
 * ************End of HomeScreenController*************
 */
