/**
 * FavouritesScreenController
 *
 * Class Description: FavouritesScreenController allows java interaction with
 * Favourites.fxml
 *
 * @author Arne
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.0] [Date Created: 28/02/14]
 */
package smarttrolleygui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;

public class FavouritesScreenController extends ControllerGeneral implements Initializable {

	@FXML
	private ListView<String> categoriesList;
	@FXML
	private TableView<Product> productTable;
	@FXML
	private TableColumn<Product, Product> imageColumn;
	@FXML
	private TableColumn<Product, String> productNameColumn;
	@FXML
    private TableColumn<Product, String> priceColumn;
	@FXML
	private TableColumn<Product, Product> addColumn;
	@FXML
	private Label listNameLabel;

	private SmartTrolleyGUI application;

	private ObservableList<String> categories;
	private ObservableList<Product> productData;

	private String categoryNumber = null;
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
		
		 
    	//Create new SqlConnection to retrieve product data
    	SqlConnection sqlConnector = new SqlConnection();
    	
        // Fill table with sample products
        productData = sqlConnector.getListOfFavourites("1");
        productTable.setItems(productData);
        
        initializeProductTable();
    }
    
    /**
     * 
     */
    @FXML public void handleMouseClick(MouseEvent arg0){
    	
    	SqlConnection sqlConnector = new SqlConnection();
    	setCategoryNumber(sqlConnector.getSpecificCategoryNumber(categoriesList.getSelectionModel().getSelectedItem()));
    	SmartTrolleyToolBox.print(getCategoryNumber());
    	    	
    	if (Integer.valueOf(getCategoryNumber())  == 1) {
    		  // Fill table with sample products
            productData = sqlConnector.getListOfFavourites("1");
    		}
    	else{
    		// Fill table with sample products
    		//"products" should be changed with a favourites equivalent.
    		productData = sqlConnector.getListOfFavourites(getCategoryNumber());
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
	public void setApp(SmartTrolleyGUI application) {
		this.application = application;
	}

	/**
	 * loadStartScreen is called when the smart trolley logo is pressed. It
	 * calls the static loadStartScreen method in ControllerGeneral.java 
	 * @param event - response to click on smart trolley logo in navigation bar
	 * <p> Date Modified: 6 Mar 2014
	 */
	public void loadStartScreen(ActionEvent event) {
		loadScreen(Screen.STARTSCREEN, application);
	}

	/**
	 * loadHomeScreen is called when the 'home' button is pressed. It calls the
	 * calls the static loadHomeScreen method in ControllerGeneral.java
	 * <p> User navigates through product database
	 * 
     * @param event - response to click on 'home' button
	 * <p> Date Modified: 28 Feb 2014
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
		loadScreen(Screen.SHOPPINGLISTSCREEN, application);	}

	/**
	 * loadOffers is called when the 'offers' button is pressed. It calls the
* calls the static loadOffers method in ControllerGeneral.java
	 * <p> User can browse store's offers 
	 * @param event - response to click on 'offers' button
	 * <p> Date Modified: 7 Mar 2014
	 */
	public void loadOffers(ActionEvent event) {
		loadScreen(Screen.OFFERSSCREEN, application);	}

	/**
	 * initializeCategories sets up the list of categories that will be
	 * displayed on screen.
	 * <p> User can navigate through product database.
	 * @return categories - list of categories
	 *  <p> Date Modified: 7 Mar 2014
	 */
    public ObservableList<String> initializeCategories() {
    	//Create new SqlConnection to retrieve product data
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
initializeProductTable fills the TableView with data and sets up cell
	 * factories
	 * <p> User can navigate through product database
	 * <p> Date Modified: 9 Mar 2014
	 */
	private void initializeProductTable() {
		
		//Set the table empty text
		productTable.setPlaceholder(new Label("No Favorites, please add"));
		
        // set up column cell value factories
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        addColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
            @Override
            public ObservableValue<Product> call(CellDataFeatures<Product, Product> features) {
                return new ReadOnlyObjectWrapper<Product>(features.getValue());
            }
        });
        imageColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
            @Override
            public ObservableValue<Product> call(CellDataFeatures<Product, Product> features) {
                return new ReadOnlyObjectWrapper<Product>(features.getValue());
            }
        });
        
		productNameColumn.setCellFactory(new Callback<TableColumn<Product, String>, TableCell<Product, String>>() {
			@Override
			public TableCell<Product, String> call(TableColumn<Product, String> productNameColumn) {
				return new TableCell<Product, String>() {
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
									SmartTrolleyGUI.setCurrentProductID(sqlConnection.getProductByName(productName).getId());
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
        addColumn.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
            @Override
            public TableCell<Product, Product> call(TableColumn<Product, Product> addColumn) {
                return new TableCell<Product, Product>() {
                    final Button button = new Button();

                    @Override
                    public void updateItem(final Product product, boolean empty) {
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
                                }
                            });
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        imageColumn.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
			@Override
            public TableCell<Product, Product> call(TableColumn<Product, Product> imageColumn) {
				return new TableCell<Product, Product>() {
					final Button button = new Button();

					@Override
                    public void updateItem(final Product product, boolean empty) {
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
                            button.getStyleClass().add("buttonImage");
							setGraphic(button);
							button.getStyleClass().add("buttonProductNameTable");

							// Button Event Handler
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									SqlConnection sqlConnection = new SqlConnection();
									SmartTrolleyToolBox.print("Pressed image of product: " + product.getName());
									SmartTrolleyGUI.setCurrentProductID(sqlConnection.getProductByName(product.getName()).getId());
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
 * ************End of FavouritesScreenController*************
 */
