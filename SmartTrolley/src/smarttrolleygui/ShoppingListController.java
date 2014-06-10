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
import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxResult;
import se.mbaeumer.fxmessagebox.MessageBoxType;
import slideshowdata.DataDownloader;
import slideshowdata.SlideData;
import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;
/**
 * ShoppingListController
 *
 * Class Description: ShoppingListController allows java interaction with
 * ExampleShoppingList.fxml
 *
 * @author V1.0 Arne
 * @author V1.1 Alick & Prashant [Delete Functionality added]
 * @author V2.0 Arash & Jonny [Add and remove product button, 
 *              total price update and total quantity update]
 * @author V2.1 Thomas [Commenting] 
 *                              
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [2.0] [Date Created: 08/06/14]
 * @version [2.1] [Date Created: 10/06/14]
 */
public class ShoppingListController extends ControllerGeneral implements Initializable {

	@FXML
	private ListView<String> categoriesList;
	@FXML
	private static TableView<ListProduct> productTable;
	@FXML
	private TableColumn<ListProduct, ListProduct> checkBoxColumn;
	@FXML
	private TableColumn<ListProduct, ListProduct> imageColumn;
	@FXML
	private TableColumn<ListProduct, String> productNameColumn;
	@FXML
	private TableColumn<ListProduct, String> priceColumn;
	@FXML
	private TableColumn<ListProduct, ListProduct> addColumn;
	@FXML
	public TableColumn<ListProduct, ListProduct> removeColumn;
	@FXML
	public TableColumn<ListProduct, Integer> quantityColumn;
	@FXML
	public Button deleteListButton;
	@FXML
	private Label listNameLabel;
	@FXML
	public Label lblTotalItems;
	@FXML
	public Label lblTotal;
	@FXML
	public Label lblTotalSavings;

	public MessageBox deleteMsgBx = new MessageBox("Would you really like to delete the list ?", MessageBoxType.YES_NO);

	private SmartTrolleyGUI application;
	private ObservableList<String> categories;
	private static ObservableList<ListProduct> productData;
	private final double MSG_BX_H = 100.0;
	private final double MSG_BX_W = 400.0;
	public static SqlConnection productsDatabase;
	private String categoryNumber = null;

	/**
	 * initialize is automatically called when the controller is created.
	 * <p>
	 * Date Modified: 06 Mar 2014
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Fill list on the LHS of the screen with different product categories
		categories = initializeCategories();
		categoriesList.setItems(categories);

		// Create new SqlConnection to retrieve product data
		SqlConnection sqlConnector = new SqlConnection();

		// Store selected products
		SmartTrolleyToolBox.print("List ID before product table init is: " + SmartTrolleyGUI.getcurrentListID());
		productData = sqlConnector.getListItems(SmartTrolleyGUI.getcurrentListID());
		productTable.setItems(productData);

		// Set the table empty text
		productTable.setPlaceholder(new Label("No Items in list, please add"));

		// show name of current shopping list
		listNameLabel.setText(SmartTrolleyGUI.getCurrentListName());		

		// Fill table with selected products
		initializeProductTable();
	}

	/** 
	* Any FXML item with a mouse click handle will use this method to dictate its reaction when clicked
	* 
	* This should only be for the Category List (ListView)
	*@param arg0
	*<p> Date Modified: 10 Jun 2014
	*/
	@FXML
	public void handleMouseClick(MouseEvent arg0) {

		SqlConnection sqlConnector = new SqlConnection();
		setCategoryNumber(sqlConnector.getSpecificCategoryNumber(categoriesList.getSelectionModel().getSelectedItem()));
		SmartTrolleyToolBox.print(getCategoryNumber());

		if (Integer.valueOf(getCategoryNumber()) == 1) {
			productData = sqlConnector.getListItems(SmartTrolleyGUI.getcurrentListID());
		} else {
			productData = sqlConnector.getListByCategory(SmartTrolleyGUI.getcurrentListID(), getCategoryNumber());
		}

		productTable.setItems(productData);
	}

	/**
	 * Tells JavaFX that the application class is SmarttrolleyGUI
	 * 
	 * @param application
	 * <p> Date Modified: 06 Mar 2014
	 */
	public void setApp(SmartTrolleyGUI application) throws SQLException {
		this.application = application;
		
		DataDownloader dataDownloader = new DataDownloader();
		
		application.setSlideshowData(dataDownloader.populateSlideshow(SmartTrolleyGUI.getcurrentListID()));				
		
		// ToDo move to initialize
		// Set the total labels
		ObservableList<Double> data = SetTotals();
		lblTotal.setText("Total: £" + data.get(0).floatValue());
		lblTotalItems.setText("Items: " + data.get(1).toString().replace(".0", ""));
	}

	/**
	 * Brings up deleted confirmation screen when delete button is pushed if
	 * yes, then deletes list from database
	 * 
	 * @param event - response to click on delete button
	 * <p> Date Modified: 9 May 2014
	 */
	public void deleteList(ActionEvent event) throws SQLException {

		// TODO check if list selected for deletion is the last list in the database.
		// If so, use sqlconnection.deleteLastList()

		productsDatabase = new SqlConnection();
		ResultSet result = null;
		productsDatabase.openConnection();

		int listID = 0;

		SmartTrolleyToolBox.print("Delete Button Pressed");
		deleteMsgBx.showAndWait();

		/*
		 * The two lines below to set the
		 * message box height and width
		 * are there because the message box
		 * resized itself (became very small)
		 * when multiple lists were deleted.
		 */
		deleteMsgBx.setHeight(MSG_BX_H);
		deleteMsgBx.setWidth(MSG_BX_W);

		if (deleteMsgBx.getMessageBoxResult() == MessageBoxResult.YES) {
			SmartTrolleyToolBox.print("YES");
			String sqlStatement;

			sqlStatement = "SELECT * FROM lists WHERE listID = " + SmartTrolleyGUI.getcurrentListID();
			try {
				result = productsDatabase.sendQuery(sqlStatement);
				SmartTrolleyToolBox.print("stored list in results for deletion.");
			} catch (SQLException e) {
				SmartTrolleyToolBox.print("Unable to send query due to unknown error");
			}

			SmartTrolleyToolBox.print("Result set to delete list is " + SqlConnection.isResultSetEmpty(result) + " empty.");

			if (SqlConnection.isResultSetEmpty(result)) {
				// Go to a specific result in the ResultSet, otherwise errors
				// are thrown
				result.absolute(1);

				listID = result.getInt("ListID");
				SmartTrolleyToolBox.print("LiD: " + result.getInt("ListID"));

				sqlStatement = "DELETE FROM `cl36-st`.`lists` WHERE listID = " + listID;

				productsDatabase.executeStatement(sqlStatement);
			} else {
				MessageBox noListMsgBx = new MessageBox("No such list exists", MessageBoxType.OK_ONLY);
				noListMsgBx.showAndWait();
				noListMsgBx.setHeight(MSG_BX_H);
				noListMsgBx.setWidth(MSG_BX_W);
			}

			loadStartScreen(event);

		} else {
			SmartTrolleyToolBox.print("NOOOOOOO");
		}

		try {
			productsDatabase.closeConnection();
			if (result != null) {
				result.close();
			}
		} catch (SQLException ex) {
			SmartTrolleyToolBox.print("could not close connection, you are eternally damned");
			SmartTrolleyToolBox.print("Beepboop son, BeepBoop");
		}
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
	 * loadHomeScreen is called when the 'home' button is pressed. It calls the
	 * calls the static loadHomeScreen method in ControllerGeneral.java
	 * <p> User navigates through product database
	 * @param event - response to click on 'home' button
	 * <p> Date Modified: 28 Feb 2014
	 */
	public void loadHomeScreen(ActionEvent event) {
		loadScreen(Screen.HOMESCREEN, application);
	}

	/**
	 * loadFavourites is called when the 'favourites' button is pressed. It
	 * calls the static loadFavourites method in ControllerGeneral.java
	 * <p> User can maintain list of favourite products
	 * @param event - response to click on 'favourites' button
	 * <p> Date Modified: 28 Feb 2014
	 */
	public void loadFavourites(ActionEvent event) {
		loadScreen(Screen.FAVORITESSCREEN, application);
	}

	/**
	 * loadOffers is called when the 'offers' button is pressed. It calls the
	 * calls the static loadOffers method in ControllerGeneral.java
	 * <p> User can browse store's offers
	 * @param event - response to click on 'offers' button
	 * <p> Date Modified: 7 Mar 2014
	 */
	public void loadOffers(ActionEvent event) {
		loadScreen(Screen.OFFERSSCREEN, application);
	}

	/**
	* initializeCategories sets up the list of categories that will be
	* displayed on screen.
	* <p>
	* User can navigate through product database.
	*
	* @return categories - list of categories
	* <p>
	* Date Modified: 7 Mar 2014
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
	 * initializeProductTable fills the TableView with data and sets up cell
	 * factories and cell value factories
	 * <p> User can navigate through product database
	 * <p> Date Modified: 21 May 2014
	 */
	private void initializeProductTable() {

		// set up column cell value factories
		setUpCellValueFactory(checkBoxColumn);
		setUpCellValueFactory(imageColumn);
		setUpCellValueFactory(addColumn);
		setUpCellValueFactory(removeColumn);

		productNameColumn.setCellValueFactory(new PropertyValueFactory<ListProduct, String>("name"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<ListProduct, String>("price"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<ListProduct, Integer>("Quantity"));

		// set up cell factories for columns with 'interactive' cells
		setUpCheckBoxCellFactory(checkBoxColumn);

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
									ObservableList<ListProduct> data = productTable.getItems();
									product.setQuantity(product.getQuantity() + 1);

									try {
										SqlConnection conn = new SqlConnection();
										Boolean productFound = false;
										int quantity = 0;
										conn.openConnection();
										ResultSet resultSet = conn.getProductsInList(SmartTrolleyGUI.getcurrentListID(), product.getID());

										while (resultSet.next()) {
											productFound = true;
											quantity = resultSet.getInt("Quantity");
										}

										if (productFound == true) {
											// If product exists then add 1 to the quantity
											conn.updateQuantity(SmartTrolleyGUI.getcurrentListID(), product.getID(), quantity + 1);

											product.setQuantity(quantity + 1);
										}

										// Now refresh the table
										refreshTable(data, product);
										
										resultSet.close();
										conn.closeConnection();

										// Now updated the totals
										ObservableList<Double> totalData = SetTotals();
										lblTotal.setText("Total: £" + totalData.get(0).floatValue());
										lblTotalItems.setText("Items: " + totalData.get(1).toString().replace(".0", ""));

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

		removeColumn.setCellFactory(new Callback<TableColumn<ListProduct, ListProduct>, TableCell<ListProduct, ListProduct>>() {
			@Override
			public TableCell<ListProduct, ListProduct> call(TableColumn<ListProduct, ListProduct> removeColumn) {
				return new TableCell<ListProduct, ListProduct>() {
					final Button button = new Button();

					@Override
					public void updateItem(final ListProduct product, boolean empty) {
						super.updateItem(product, empty);
						if (product != null) {
							button.setText("-");
							button.getStyleClass().add("buttonChangeQuantity");
							setGraphic(button);

							// Button Event Handler
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									SmartTrolleyToolBox.print("Pressed remove button for product: " + product.getName());

									ObservableList<ListProduct> data = productTable.getItems();
									product.setQuantity(product.getQuantity() + 1);

									try {
										SqlConnection conn = new SqlConnection();
										Boolean productFound = false;
										int quantity = 0;
										conn.openConnection();
										ResultSet resultSet = conn.getProductsInList(SmartTrolleyGUI.getcurrentListID(), product.getID());

										while (resultSet.next()) {
											productFound = true;
											quantity = resultSet.getInt("Quantity");
										}

										if (productFound == true) {
											Integer qty = quantity - 1;

											if (qty <= 0) {
												conn.removeProductFromList(SmartTrolleyGUI.getcurrentListID(), product.getID());
												data.remove(product);
											} else {
												conn.updateQuantity(SmartTrolleyGUI.getcurrentListID(), product.getID(), quantity - 1);

												product.setQuantity(quantity - 1);
											}
										}
										resultSet.close();
										conn.closeConnection();

										// Now refresh the table
										refreshTable(data, product);

										// Now updated the totals
										ObservableList<Double> totalData = SetTotals();
										lblTotal.setText("Total: £" + totalData.get(0).floatValue());
										lblTotalItems.setText("Items: " + totalData.get(1).toString().replace(".0", ""));

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

		productNameColumn.setCellValueFactory(new PropertyValueFactory<ListProduct, String>("name"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<ListProduct, String>("price"));

		imageColumn.setCellFactory(new Callback<TableColumn<ListProduct, ListProduct>, TableCell<ListProduct, ListProduct>>() {
			@Override
			public TableCell<ListProduct, ListProduct> call(TableColumn<ListProduct, ListProduct> imageColumn) {
				return new TableCell<ListProduct, ListProduct>() {
					final Button button = new Button();

					@Override
					public void updateItem(final ListProduct product, boolean empty) {
						super.updateItem(product, empty);
						if (product != null) {
							try {
								Image productImage = new Image(getClass().getResourceAsStream(product.getImage()));
								button.setGraphic(new ImageView(productImage));
							} catch (NullPointerException noImage) {
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
									// TODO Can delete the currentProductID field in SmartTrolleyGUI
									SmartTrolleyGUI.setCurrentProductID(sqlConnection.getProductByName(product.getName()).getID());

									SmartTrolleyGUI.setCurrentSlideID(((SlideData) sqlConnection.getSpecificData("slide", "productID", Integer.toString(product.getID()))).getId());
									SmartTrolleyToolBox.print("The current slideID is: " + SmartTrolleyGUI.getCurrentSlideID());
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

									SmartTrolleyGUI.setCurrentSlideID(((SlideData) sqlConnection.getSpecificData("slide", "productID",
											Integer.toString(sqlConnection.getProductByName(productName).getID()))).getId());
									SmartTrolleyToolBox.print("The current slideID is: " + SmartTrolleyGUI.getCurrentSlideID());
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

	private void refreshTable(ObservableList<ListProduct> data, ListProduct selectedProduct) {

		productTable.setItems(null);
		productTable.layout();
		productTable.setItems(data);
		productTable.getSelectionModel().select(selectedProduct);
	}

	public static int getProductDataSize() {
		return productData.size();
	}
	
	/**
	* Sets the totals for the amount spent and saved 
	*@return ObservableList<Double>
	*@throws SQLException
	*<p> Date Modified: 10 Jun 2014
	*/
	private ObservableList<Double> SetTotals() throws SQLException {
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

		if (!SqlConnection.isResultSetEmpty(resultSet)){
		lblTotalSavings.setText("Saved: £" + String.format("%.2g%n", conn.calculateSavings(productsInList, (ArrayList<Integer>) productIDsInList)));
		} else {
			lblTotalSavings.setText("Saved: £0");
		}
		ObservableList<Double> data = FXCollections.observableArrayList(total, totalItems, 0.00);
		return data;

	}

}
/************** End of ShoppingListController **************/
