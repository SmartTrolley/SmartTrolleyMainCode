/**
 * HomeScreenController
 *
 * Class Description: HomeScreenController allows java interaction with
 * HomeScreen.fxml
 *
 * @author Arne
 * @author samgeering [Category Filtering added]
 * @author Alick Jacklin & Prashant Chakravarty V 1.1
 *
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
import java.util.ResourceBundle;

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
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;

public class HomeScreenController extends ControllerGeneral implements Initializable {

	@FXML
	private ListView<String> categoriesList;
	@FXML
	private TableView<ListProduct> productTable;
	@FXML
	private TableColumn<ListProduct, ListProduct> imageColumn;
	@FXML
	private TableColumn<ListProduct, String> productNameColumn;
	@FXML
	private TableColumn<ListProduct, String> priceColumn;
	@FXML
	private TableColumn<ListProduct, ListProduct> addColumn;
	@FXML
	private Label listNameLabel;
	@FXML
	private TextField searchBox;
	@FXML
	private Button searchButton;
	@FXML
    public Label lblTotalItems;
    @FXML
    public Label lblTotal;

	private SmartTrolleyGUI application;
	private ObservableList<String> categories;
	private ObservableList<ListProduct> productData;
	private String categoryNumber;

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

		// Create new SqlConnection to retrieve product data
		SqlConnection sqlConnector = new SqlConnection();

		// Get selected products
		productData = sqlConnector.getListOfAllProducts();
		productTable.setItems(productData);

		// Fill table with selected products
		initializeProductTable();
	}

	/** Any FXML item with a mouse click handle will use this method to dictate its reaction when clicked
	 * 
	 * This should only be for the Category List (ListView)
	 * 
	 */
	@FXML
	public void handleMouseClick(MouseEvent arg0) {

		SqlConnection sqlConnector = new SqlConnection();
		setCategoryNumber(sqlConnector.getSpecificCategoryNumber(categoriesList.getSelectionModel().getSelectedItem()));
		SmartTrolleyToolBox.print(getCategoryNumber());

		if (Integer.valueOf(getCategoryNumber()) == 1) {
			// Fill table with sample products
			productData = sqlConnector.getListOfAllProducts();
		} else {
			// Fill table with sample products
			productData = sqlConnector.getProductsWithinSpecificCategory(getCategoryNumber());
		}

		productTable.setItems(productData);
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
	public void setApp(SmartTrolleyGUI application) throws SQLException {
		this.application = application;
		//TODO Move to initialise
		//Set the total labels
        ObservableList<Double> data = SetTotals();
        lblTotal.setText("Total: £" + data.get(0).floatValue());
        lblTotalItems.setText("Total Items: " + data.get(1).toString().replace(".0", ""));
	}

	/**
	 * loadStartScreen is called when the smart trolley logo is pressed. It
	 * calls the static loadStartScreen method in ControllerGeneral.java
	 * 
	 * @param event - response to click on smart trolley logo in navigation bar
	 * <p> Date Modified: 6 Mar 2014
	 */
	public void loadStartScreen(ActionEvent event) {
		loadScreen(Screen.STARTSCREEN, application);
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
	 * <p> User can browse store's offers
	 * @param event- response to click on 'offers' button
	 * <p> Date Modified: 7 Mar 2014
	 */
	public void loadOffers(ActionEvent event) {
		loadScreen(Screen.OFFERSSCREEN, application);
	}

	/**
	 * initializeCategories sets up the list of categories that will be
	 * displayed on screen.
	 * <p> User can navigate through product database.
	 * @return categories - list of categories
	 * <p> Date Modified: 7 Mar 2014
	 */
	public ObservableList<String> initializeCategories() {
		// Create new SqlConnection to retrieve product data
		SqlConnection sqlConnector = new SqlConnection();

		categories = sqlConnector.getListOfCategories();

		return categories;
	}

	public String getCategoryNumber() {
		return categoryNumber;
	}

	public void setCategoryNumber(String categoryNumber) {
		this.categoryNumber = categoryNumber;
	}

	/**
	*Searches database for product entered into the TextField.
	*<p>User is able to search for product
	*@param event
	*@throws SQLException
	*<p> Date Modified: 30 May 2014
	*/
	public void searchForProducts(ActionEvent event) throws SQLException {
		
		SqlConnection Sqlconnection = new SqlConnection();

		productTable.setItems(Sqlconnection.wildcardSearchForProduct(searchBox.getText()));
	}

	/**
	 * initializeProductTable fills the TableView with data and sets up cell
	 * factories
	 * <p> User can navigate through product database
	 * <p> Date Modified: 9 Mar 2014
	 */
	private void initializeProductTable() {

		// Set the table empty text
		productTable.setPlaceholder(new Label("No products in database."));

		// set up column cell value factories
		productNameColumn.setCellValueFactory(new PropertyValueFactory<ListProduct, String>("name"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<ListProduct, String>("price"));
		addColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ListProduct, ListProduct>, ObservableValue<ListProduct>>() {
			@Override
			public ObservableValue<ListProduct> call(TableColumn.CellDataFeatures<ListProduct, ListProduct> features) {
				return new ReadOnlyObjectWrapper<ListProduct>(features.getValue());
			}
		});
		imageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ListProduct, ListProduct>, ObservableValue<ListProduct>>() {
			@Override
			public ObservableValue<ListProduct> call(TableColumn.CellDataFeatures<ListProduct, ListProduct> features) {
				return new ReadOnlyObjectWrapper<ListProduct>(features.getValue());
			}
		});

		productNameColumn.setCellFactory(new Callback<TableColumn<ListProduct, String>, TableCell<ListProduct, String>>() {
			@Override
			public TableCell<ListProduct, String> call(TableColumn<ListProduct, String> productNameColumn) {
				return new TableCell<ListProduct, String>() {
					final Button button = new Button();

					@Override
					public void updateItem(final String productName, boolean empty) {
						super.updateItem(productName, empty);
						if (productName != null) {

							setGraphic(button);
							button.setText(productName);
							button.setPrefHeight(80);
							button.getStyleClass().add("buttonProductNameTable");

							// Button Event Handler
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									SqlConnection sqlConnection = new SqlConnection();

									SmartTrolleyToolBox.print("Pressed name of product: " + productName);
									// TODO: add code to move to product screen here and refactor individual controllers
									SmartTrolleyGUI.setCurrentProductID(sqlConnection.getProductByName(productName).getID());
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

		// set up cell factories for columns containing images / buttons
		addColumn.setCellFactory(new Callback<TableColumn<ListProduct, ListProduct>, TableCell<ListProduct, ListProduct>>() {
			@Override
			public TableCell<ListProduct, ListProduct> call(TableColumn<ListProduct, ListProduct> addColumn) {
				return new TableCell<ListProduct, ListProduct>() {
					final Button button = new Button();

					@Override
					public void updateItem(final ListProduct product, boolean empty) {
						super.updateItem(product, empty);
						if (product != null) {
							button.setText("+");
							button.getStyleClass().add("buttonChangeQuantity");
							setGraphic(button);

							// Button Event Handler
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									SmartTrolleyToolBox.print("Pressed add button for product: " + product.getName());
									
									try {
										SqlConnection conn = new SqlConnection();
										Boolean productFound = false;
										int quantity = 0;
										conn.openConnection();
										ResultSet resultSet = conn.getProductsInList(SmartTrolleyGUI.getcurrentListID(), 
																					product.getID());
										
										while(resultSet.next()){
                                            productFound = true;
                                            quantity = resultSet.getInt("Quantity");
                                        }
										
										if(productFound == false){
                                            conn.AddProductToList(SmartTrolleyGUI.getcurrentListID(), 
                                            		product.getID(), 1);
                                        }else {
                                            //If product exists then add 1 to the quantity
                                            conn.updateQuantity(SmartTrolleyGUI.getcurrentListID(), 
                                            		product.getID(), quantity + 1);
                                        }
										
										resultSet.close();
										conn.closeConnection();
										
										//Now updated the totals
										ObservableList<Double> data = SetTotals();
                                        lblTotal.setText("Total: £" + data.get(0).floatValue());
                                        lblTotalItems.setText("Total Items: " + data.get(1).toString().replace(".0", ""));
										
									
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									
								}
							});
						} else {
							setGraphic(null);
						}
					}
				};
			}
		});

		imageColumn.setCellFactory(new Callback<TableColumn<ListProduct, ListProduct>, TableCell<ListProduct, ListProduct>>() {
			@Override
			public TableCell<ListProduct, ListProduct> call(TableColumn<ListProduct, ListProduct> imageColumn) {
				return new TableCell<ListProduct, ListProduct>() {
					final Button button = new Button();

					@Override
					public void updateItem(final ListProduct product, boolean empty) {
						super.updateItem(product, empty);
						if (product != null) {
							try{
							Image productImage = new Image(getClass().getResourceAsStream(product.getImage()));
							button.setGraphic(new ImageView(productImage));
							}
							catch (NullPointerException noImage) {
								SmartTrolleyToolBox.print("Image URL invalid or null.");
								button.setGraphic(null);
							}								
                        button.setPrefSize(80, 60);
                        button.getStyleClass().add("buttonProductNameTable");
						setGraphic(button);	

							// Button Event Handler
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									SqlConnection sqlConnection = new SqlConnection();
									SmartTrolleyToolBox.print("Pressed image of product: " + product.getName());
									SmartTrolleyGUI.setCurrentProductID(sqlConnection.getProductByName(product.getName()).getID());
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
	}
	
	private ObservableList<Double> SetTotals() throws SQLException{
        double total = 0;
        double totalItems = 0;
        
        SqlConnection conn = new SqlConnection();
        
        ResultSet resultSet = conn.getAllListItems(SmartTrolleyGUI.getcurrentListID());
        
        
        while(resultSet.next()){
            total += resultSet.getDouble("Price") * resultSet.getInt("Quantity");
            totalItems += resultSet.getInt("Quantity");
            
        }
        
        ObservableList<Double> data = FXCollections.observableArrayList(total, totalItems, 0.00);
        return data;
        
    }
}

/**
 * ************End of HomeScreenController*************
 */
