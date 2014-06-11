package smarttrolleygui;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;
/**
 * FavouritesScreenController
 *
 * Class Description: FavouritesScreenController allows java interaction with
 * Favourites.fxml
 *
 * @author V1.0 Arne
 * @author V2.0 Arash & Jonny [Add button was added, total price update and total quantity update]
 * @author V1.1 Thomas [Commenting]
 * @author [Checked By:] [Checker(s) fill here]
 * 
 * @version [2.0] [Date Created: 8/06/14]
 * @version [2.1] [Date Created: 10/06/2014]
 */
public class FavouritesScreenController extends ControllerGeneral implements Initializable {

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
    public Label lblTotalItems;
    @FXML
    public Label lblTotal;
	@FXML
	public Label lblTotalSavings;

	private SmartTrolleyGUI application;

	private ObservableList<String> categories;
	private ObservableList<ListProduct> productData;

	private String categoryNumber = null;
	/**
	 * Initialise is automatically called when the controller is created.
	 * <p>
	 * Date Modified: 22 Feb 2014
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Fill list on the LHS of the screen with different ListProduct categories
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
    *@param arg0
    *<p> Date Modified: 10 Jun 2014
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
	 * Tells JavaFX that the application class is SmarttrolleyGUI
	 * 
	 * @param application
	 * <p>
	 * Date Modified: 28 Feb 2014
	 */
	public void setApp(SmartTrolleyGUI application) {
		this.application = application;
		//TODO Move this code to initialize
		//Set the total labels
		try {
			ObservableList<Double> data = setTotals();
			lblTotal.setText("Total: £" + data.get(0).floatValue());
	        lblTotalItems.setText("Items: " + data.get(1).toString().replace(".0", ""));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
	 * initializeProductTable fills the TableView with data and sets up cell
	 * factories
	 * <p> User can navigate through product database
	 * <p> Date Modified: 9 Mar 2014
	 */
	private void initializeProductTable() {
		
		//Set the table empty text
		productTable.setPlaceholder(new Label("No Favorites, please add"));
		
        // set up column cell value factories
		setUpCellValueFactory(addColumn);
		setUpCellValueFactory(imageColumn);
		
		 productNameColumn.setCellValueFactory(new PropertyValueFactory<ListProduct, String>("name"));
	        priceColumn.setCellValueFactory(new PropertyValueFactory<ListProduct, String>("price"));
	       
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
									
									application.productClicked = true;
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
                                            conn.addProductToList(SmartTrolleyGUI.getcurrentListID(), 
                                            		product.getID(), 1);
                                        }else {
                                            //If product exists then add 1 to the quantity
                                            conn.updateQuantity(SmartTrolleyGUI.getcurrentListID(), 
                                            		product.getID(), quantity + 1);
                                }
										resultSet.close();
										conn.closeConnection();
										
										//Now updated the totals
										ObservableList<Double> data = setTotals();
                                        lblTotal.setText("Total: £" + data.get(0).floatValue());
                                        lblTotalItems.setText("Items: " + data.get(1).toString().replace(".0", ""));
										
									
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
                            button.getStyleClass().add("buttonImage");
							setGraphic(button);
							button.getStyleClass().add("buttonProductNameTable");

							// Button Event Handler
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									SqlConnection sqlConnection = new SqlConnection();
									SmartTrolleyToolBox.print("Pressed image of product: " + product.getName());
									// TODO: add code to move to product screen here and refactor individual controllers	
									SmartTrolleyGUI.setCurrentProductID(sqlConnection.getProductByName(product.getName()).getID());
									
									application.productClicked = true;
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
	
	/**
	* Sets the totals for the amount spent and saved 
	*@return ObservableList<Double>
	*@throws SQLException
	*<p> Date Modified: 10 Jun 2014
	*/
	private ObservableList<Double> setTotals() throws SQLException{
        double total = 0;
        double totalItems = 0;
        
        SqlConnection conn = new SqlConnection();
        
        ResultSet resultSet = conn.getAllListItems(SmartTrolleyGUI.getcurrentListID());
        
        
      //Hash map of product ID & quantity
      		Map<Integer, Integer> productsInList = new HashMap<Integer, Integer>();
      		List<Integer> productIDsInList = new ArrayList<Integer>();

      		while (resultSet.next()) {
      			
      			
      			total += resultSet.getDouble("Price") * resultSet.getInt("Quantity");
      			totalItems += resultSet.getInt("Quantity");

      			productIDsInList.add(resultSet.getInt("ProductID"));
      			productsInList.put(resultSet.getInt("ProductID"),resultSet.getInt("Quantity"));
      		}
      		ResultSet results = conn.getAllListItems(SmartTrolleyGUI.getcurrentListID());
      		
      		if (!SqlConnection.isResultSetEmpty(results)){
      		lblTotalSavings.setText("Saved: £" + String.format("%.2g%n", conn.calculateSavings(productsInList, (ArrayList<Integer>) productIDsInList)));
      		} else {
      			lblTotalSavings.setText("Saved: £0");
      		}
      		ObservableList<Double> data = FXCollections.observableArrayList(total, totalItems, 0.00);
      		return data;

      	}

}
/**
 * ************End of FavouritesScreenController*************
 */
