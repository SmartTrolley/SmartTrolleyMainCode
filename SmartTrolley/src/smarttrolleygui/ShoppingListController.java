/**
 * ShoppingListController
 *
 * Class Description: ShoppingListController allows java interaction with
 * ExampleShoppingList.fxml
 *
 * @author V1.0 Arne
 * @author V1.1 Alick & Prashant [Delete Functionality added]
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.1] [Date Created: 06/03/14]
 */
package smarttrolleygui;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import smarttrolleygui.slideshow.SlideShow;
import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;

public class ShoppingListController extends ControllerGeneral implements Initializable {

	@FXML
	private ListView<String> categoriesList;
	@FXML
	private static TableView<Product> productTable;
	@FXML
	private TableColumn<Product, Product> checkBoxColumn;
	@FXML
	private TableColumn<Product, Product> imageColumn;
	@FXML
	private TableColumn<Product, String> productNameColumn;
	@FXML
	private TableColumn<Product, String> priceColumn;
	@FXML
	private TableColumn<Product, Product> addColumn;
	@FXML
	public TableColumn<Product, Product> removeColumn;
	@FXML
	public Button deleteListButton;
	@FXML
	private Label listNameLabel;

	public MessageBox deleteMsgBx = new MessageBox("Would you really like to delete the list ?", MessageBoxType.YES_NO);

	private SmartTrolleyGUI application;
	private ObservableList<String> categories;
	private static ObservableList<Product> productData;
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
		productData = sqlConnector.getList(SmartTrolleyGUI.getcurrentListID());
		productTable.setItems(productData);

		// Set the table empty text
		productTable.setPlaceholder(new Label("No Items in list, please add"));

		// show name of current shopping list
		listNameLabel.setText(SmartTrolleyGUI.getCurrentListName());

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
			productData = sqlConnector.getList(SmartTrolleyGUI.getcurrentListID());
		} else {
			productData = sqlConnector.getListByCategory(SmartTrolleyGUI.getcurrentListID(), getCategoryNumber());
		}

		productTable.setItems(productData);
	}

	/**
	 * setApp
	 * 
	 * @param application
	 * <p> Date Modified: 06 Mar 2014
	 */
	public void setApp(SmartTrolleyGUI application) {
		this.application = application;
	}

	/**
	 * Brings up deleted confirmation screen when delete button is pushed if
	 * yes, then deletes list from database
	 * 
	 * @param event - response to click on delete button
	 * <p> Date Modified: 9 May 2014
	 */
	public void deleteList(ActionEvent event) throws SQLException {
		
		//TODO check if list selected for deletion is the last list in the database.
		//If so, use sqlconnection.deleteLastList()

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
			SmartTrolleyToolBox.print("could not close connection, you are eternally fucked");
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

		// set up cell factories for columns with 'interactive' cells 
		setUpCheckBoxCellFactory(checkBoxColumn);
		setUpAddButtonCellFactory(addColumn);
		setUpRemoveButtonCellFactory(removeColumn);
		
		
		productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
//		checkBoxColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
//			@Override
//			public ObservableValue<Product> call(CellDataFeatures<Product, Product> features) {
//				return new ReadOnlyObjectWrapper<Product>(features.getValue());
//			}
//		});
//		imageColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
//			@Override
//			public ObservableValue<Product> call(CellDataFeatures<Product, Product> features) {
//				return new ReadOnlyObjectWrapper<Product>(features.getValue());
//			}
//		});
//		addColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
//			@Override
//			public ObservableValue<Product> call(CellDataFeatures<Product, Product> features) {
//				return new ReadOnlyObjectWrapper<Product>(features.getValue());
//			}
//		});
//		removeColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
//			@Override
//			public ObservableValue<Product> call(CellDataFeatures<Product, Product> features) {
//				return new ReadOnlyObjectWrapper<Product>(features.getValue());
//			}
//		});

		// set up cell factories for columns containing images / buttons
//		checkBoxColumn.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
//			@Override
//			public TableCell<Product, Product> call(TableColumn<Product, Product> imageColumn) {
//				return new TableCell<Product, Product>() {
//					final CheckBox checkBox = new CheckBox();
//
//					@Override
//					public void updateItem(final Product product, boolean empty) {
//						super.updateItem(product, empty);
//						if (product != null) {
//							// button.getStyleClass().add("buttonImage");
//							setGraphic(checkBox);
//
//							// Button Event Handler
//							checkBox.setOnAction(new EventHandler<ActionEvent>() {
//								@Override
//								public void handle(ActionEvent event) {
//									SmartTrolleyToolBox.print("Pressed checkbox of product: " + product.getName());
//								}
//							});
//						} else {
//							setGraphic(null);
//						}
//					}
//				};
//			}
//		});
		imageColumn.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
			@Override
			public TableCell<Product, Product> call(TableColumn<Product, Product> imageColumn) {
				return new TableCell<Product, Product>() {
					final Button button = new Button();

					@Override
					public void updateItem(final Product product, boolean empty) {
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
									SmartTrolleyGUI.setCurrentProductID(sqlConnection.getProductByName(product.getName()).getId());
									
									SmartTrolleyGUI.setCurrentSlideID((((Product) sqlConnection.getSpecificData("slide", "productID", product.getName())).getId()));
									
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
//		addColumn.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
//			@Override
//			public TableCell<Product, Product> call(TableColumn<Product, Product> addColumn) {
//				return new TableCell<Product, Product>() {
//					final Button button = new Button();
//
//					@Override
//					public void updateItem(final Product product, boolean empty) {
//						super.updateItem(product, empty);
//						if (product != null) {
//							button.setText("+");
//							button.getStyleClass().add("buttonChangeQuantity");
//							setGraphic(button);
//
//							// Button Event Handler
//							button.setOnAction(new EventHandler<ActionEvent>() {
//								@Override
//								public void handle(ActionEvent event) {
//									SmartTrolleyToolBox.print("Pressed add button for product: " + product.getName());
//								}
//							});
//						} else {
//							setGraphic(null);
//						}
//					}
//				};
//			}
//		});
//
//		removeColumn.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
//			@Override
//			public TableCell<Product, Product> call(TableColumn<Product, Product> removeColumn) {
//				return new TableCell<Product, Product>() {
//					final Button button = new Button();
//
//					@Override
//					public void updateItem(final Product product, boolean empty) {
//						super.updateItem(product, empty);
//						if (product != null) {
//							button.setText("-");
//							button.getStyleClass().add("buttonChangeQuantity");
//							setGraphic(button);
//
//							// Button Event Handler
//							button.setOnAction(new EventHandler<ActionEvent>() {
//								@Override
//								public void handle(ActionEvent event) {
//									SmartTrolleyToolBox.print("Pressed remove button for product: " + product.getName());
//
//								}
//							});
//						} else {
//							setGraphic(null);
//						}
//					}
//				};
//			}
//		});

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
	}

	public static int getProductDataSize() {
		return productData.size();
	}

}
/************** End of ShoppingListController **************/
