/**
 * ExampleShoppingListController
 *
 * Class Description: ExampleShoppingListController allows java interaction with
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

import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxResult;
import se.mbaeumer.fxmessagebox.MessageBoxType;
import DatabaseConnectors.SqlConnection;
import Printing.SmartTrolleyPrint;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class ExampleShoppingListController implements Initializable {

	@FXML
	private ListView<String> categoriesList;
	@FXML
	private static TableView<Product> productTable;
	@FXML
	private TableColumn<Product, Product> checkBoxColumn;
	@FXML
	private TableColumn<Product, Product> imageColumn;
	@FXML
	private TableColumn<Product, Product> productNameColumn;
	@FXML
	private TableColumn<Product, Float> priceColumn;
	@FXML
	private TableColumn<Product, Product> addColumn;
	@FXML
	public TableColumn<Product, Product> removeColumn;
	@FXML
	public static Button deleteListButton;
	public static MessageBox deleteMsgBx = new MessageBox(
			"Would you really like to delete the list ?", MessageBoxType.YES_NO);

	private SmartTrolleyGUI application;
	private ObservableList<String> categories;
	private static ObservableList<Product> productData;
	private final double MSG_BX_H = 100.0;
	private final double MSG_BX_W = 400.0;
	public static SqlConnection productsDatabase;
	private ControllerGeneral controller = new ControllerGeneral();

	/**
	 * initialize is automatically called when the controller is created.
	 * <p>
	 * Date Modified: 06 Mar 2014
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
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
	 *            Date Modified: 06 Mar 2014
	 */
	public void setApp(SmartTrolleyGUI application) {
		this.application = application;
	}

	/**
	 * Brings up deleted confirmation screen when delete button is pushed if
	 * yes, then deletes list from database
	 * 
	 * @param event
	 *            - response to click on delete button
	 *            <p>
	 *            Date Modified: 9 May 2014
	 */
	public void deleteList(ActionEvent event) throws SQLException {

		productsDatabase = new SqlConnection();
		ResultSet result = null;
		productsDatabase.openConnection();

		int listID = 0;

		SmartTrolleyPrint.print("Delete Button Pressed");
		deleteMsgBx.showAndWait();
		deleteMsgBx.setHeight(MSG_BX_H);
		deleteMsgBx.setWidth(MSG_BX_W);

		if (deleteMsgBx.getMessageBoxResult() == MessageBoxResult.YES) {
			SmartTrolleyPrint.print("YES");
			String sqlStatement;

			sqlStatement = "SELECT * FROM lists WHERE listID = "
					+ SmartTrolleyGUI.getcurrentListID();
			try {
				result = productsDatabase.sendQuery(sqlStatement);
				SmartTrolleyPrint.print("stored list in results for deletion.");
			} catch (SQLException e) {
				SmartTrolleyPrint
						.print("Unable to send query due to unknown error");
			}

			SmartTrolleyPrint.print(SqlConnection.isResultSetEmpty(result));

			if (SqlConnection.isResultSetEmpty(result)) {
				// Go to a specific result in the ResultSet, otherwise errors
				// are thrown
				result.absolute(1);

				listID = result.getInt("ListID");
				SmartTrolleyPrint.print("LiD: " + result.getInt("ListID"));

				sqlStatement = "DELETE FROM `cl36-st`.`lists` WHERE listID = "
						+ listID;

				productsDatabase.executeStatement(sqlStatement);
			} else {
				MessageBox noListMsgBx = new MessageBox("No such list exists",
						MessageBoxType.OK_ONLY);
				noListMsgBx.showAndWait();
				noListMsgBx.setHeight(MSG_BX_H);
				noListMsgBx.setWidth(MSG_BX_W);

			}

		} else {
			SmartTrolleyPrint.print("NOOOOOOO");
		}

		try {
			productsDatabase.closeConnection();
			result.close();
		} catch (SQLException ex) {
			SmartTrolleyPrint
					.print("could not close connection, you are eternally fucked");
			SmartTrolleyPrint.print("Beepboop son, BeepBoop");
		}
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
	 * factories and cell value factories
	 * <p>
	 * User can navigate through product database
	 * <p>
	 * Date Modified: 21 May 2014
	 */
	private void initializeProductTable() {
		// Create new SqlConnection to retrieve product data
		SqlConnection sqlConnector = new SqlConnection();

		// Get product data from current list
		productData = sqlConnector.getList(SmartTrolleyGUI.getcurrentListID());
		// TODO: I don't know why this next line was added but does it maybe
		// require an if statement?
		productTable.setPlaceholder(new Label("No Items in list, please add"));

		// set up column cell value factories
		priceColumn
				.setCellValueFactory(new PropertyValueFactory<Product, Float>(
						"price"));

		controller.setUpCellValueFactory(productNameColumn);
		controller.setUpCellValueFactory(checkBoxColumn);
		controller.setUpCellValueFactory(imageColumn);
		controller.setUpCellValueFactory(addColumn);
		controller.setUpCellValueFactory(removeColumn);

		// set up cell factories for columns containing buttons
		// syntax:
		// TableColumn<S,T> where S is the type of the TableView (i.e. Product
		// here),
		// and T is the type of the content in all cells of this TableColumn.
		checkBoxColumn
				.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
					@Override
					public TableCell<Product, Product> call(
							TableColumn<Product, Product> checkBoxColumn) {
						return new TableCell<Product, Product>() {
							final CheckBox checkBox = new CheckBox();

							@Override
							public void updateItem(final Product product,
									boolean empty) {
								super.updateItem(product, empty);
								if (product != null) {
									setGraphic(checkBox);

									// CheckBox Event Handler
									checkBox.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											System.out
													.println("Pressed checkbox of product: "
															+ product.getName());
										}
									});
								} else {
									setGraphic(null);
								}
							}
						};
					}
				});
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
									// TODO: if end up using same style, rename
									// styleClass
									button.getStyleClass().add(
											"buttonChangeQuantity");

									// Button Event Handler
									button.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											System.out
													.println("Pressed name of product: "
															+ product.getName());
											// TODO:
											// loadProduct(product.getName());
										}
									});
								} else {
									setGraphic(null);
								}
							}
						};
					}
				});

		imageColumn
				.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
					@Override
					public TableCell<Product, Product> call(
							TableColumn<Product, Product> imageColumn) {
						return new TableCell<Product, Product>() {
							final Button button = new Button();

							@Override
							public void updateItem(final Product product,
									boolean empty) {
								super.updateItem(product, empty);
								if (product != null) {
									Image productImage = new Image(getClass()
											.getResourceAsStream(
													product.getImage()));
									button.setGraphic(new ImageView(
											productImage));
									button.setPrefSize(80, 60);
									button.getStyleClass().add("buttonImage");
									setGraphic(button);

									// Button Event Handler
									button.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											System.out
													.println("Pressed image of product: "
															+ product.getName());
										}
									});
								} else {
									setGraphic(null);
								}
							}
						};
					}
				});

		addColumn
				.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
					@Override
					public TableCell<Product, Product> call(
							TableColumn<Product, Product> addColumn) {
						return new TableCell<Product, Product>() {
							final Button button = new Button();

							@Override
							public void updateItem(final Product product,
									boolean empty) {
								super.updateItem(product, empty);
								if (product != null) {
									button.setText("+");
									button.getStyleClass().add(
											"buttonChangeQuantity");
									setGraphic(button);

									// Button Event Handler
									button.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											System.out
													.println("Pressed add button for product: "
															+ product.getName());
										}
									});
								} else {
									setGraphic(null);
								}
							}
						};
					}
				});

		removeColumn
				.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
					@Override
					public TableCell<Product, Product> call(
							TableColumn<Product, Product> removeColumn) {
						return new TableCell<Product, Product>() {
							final Button button = new Button();

							@Override
							public void updateItem(final Product product,
									boolean empty) {
								super.updateItem(product, empty);
								if (product != null) {
									button.setText("-");
									button.getStyleClass().add(
											"buttonChangeQuantity");
									setGraphic(button);

									// Button Event Handler
									button.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											System.out
													.println("Pressed remove button for product: "
															+ product.getName());

										}
									});
								} else {
									setGraphic(null);
								}
							}
						};
					}
				});

		productTable.setItems(productData);
	}

	// TODO: This method is neither used nor commented - can it be removed?
	public static int getProductDataSize() {
		return productData.size();
	}

}
/************** End of ExampleShoppingListController **************/
