/**
 * ExampleShoppingListController
 *
 * Class Description: ExampleShoppingListController allows java interaction with
 * ExampleShoppingList.fxml
 *
 * @author V1.0 Arne
 * @author V1.1 Alick & Prashant [Delete Functionality added]
 * @author V1.2 Arash & Jonny
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.2] [Date Created: 02/06/14]
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
	private static TableView<ListProduct> productTable;
	@FXML
	private TableColumn<ListProduct, ListProduct> checkBoxColumn;
	@FXML
	private TableColumn<ListProduct, ListProduct> imageColumn;
	@FXML
	private TableColumn<ListProduct, ListProduct> productNameColumn;
	@FXML
	private TableColumn<ListProduct, Float> priceColumn;
	@FXML
	private TableColumn<ListProduct, ListProduct> addColumn;
	@FXML
	public TableColumn<ListProduct, ListProduct> removeColumn;
	@FXML
	public static Button deleteListButton;
	@FXML
	private Label listNameLabel;
	
	public static MessageBox deleteMsgBx = new MessageBox(
			"Would you really like to delete the list ?", MessageBoxType.YES_NO);

	private SmartTrolleyGUI application;
	private ObservableList<String> categories;
	private static ObservableList<ListProduct> productData;
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
		// Fill list on the LHS of the screen with different ListProduct categories
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

			try {
				result = productsDatabase.getList(SmartTrolleyGUI.getcurrentListID());
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

				productsDatabase.removeList(listID);
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
	 * User navigates through ListProduct database
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
	 * User can navigate through ListProduct database.
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
	 * User can navigate through ListProduct database
	 * <p>
	 * Date Modified: 21 May 2014
	 */
	private void initializeProductTable() {
		// Create new SqlConnection to retrieve ListProduct data
		SqlConnection sqlConnector = new SqlConnection();

		// Get ListProduct data from current list
		productData = sqlConnector.getListProducts(SmartTrolleyGUI.getcurrentListID());
		// TODO: I don't know why this next line was added but does it maybe
		// require an if statement?
		productTable.setPlaceholder(new Label("No Items in list, please add"));

		// set up column cell value factories
		priceColumn
				.setCellValueFactory(new PropertyValueFactory<ListProduct, Float>(
						"price"));

		controller.setUpCellValueFactory(productNameColumn);
		controller.setUpCellValueFactory(checkBoxColumn);
		controller.setUpCellValueFactory(imageColumn);
		controller.setUpCellValueFactory(addColumn);
		controller.setUpCellValueFactory(removeColumn);

		// set up cell factories for columns with 'interactive' cells 
		controller.setUpCheckBoxCellFactory(checkBoxColumn);
		controller.setUpImageCellFactory(imageColumn);
		controller.setUpAddButtonCellFactory(addColumn, productTable);
		controller.setUpRemoveButtonCellFactory(removeColumn, productTable);
		
//		controller.setUpProductNameCellFactory(productNameColumn);
		// TODO: once refactored remove following code and uncomment previous line to set up cell factory for ListProduct name column
		productNameColumn
		.setCellFactory(new Callback<TableColumn<ListProduct, ListProduct>, TableCell<ListProduct, ListProduct>>() {
			@Override
			public TableCell<ListProduct, ListProduct> call(
					TableColumn<ListProduct, ListProduct> productNameColumn) {
				return new TableCell<ListProduct, ListProduct>() {
					final Button button = new Button();

					@Override
					public void updateItem(final ListProduct ListProduct,
							boolean empty) {
						super.updateItem(ListProduct, empty);
						if (ListProduct != null) {
							setGraphic(button);
							button.setText(ListProduct.getName());
							button.setPrefHeight(80);
							button.getStyleClass().add("buttonProductNameTable");

							// Button Event Handler
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									System.out
											.println("Pressed name of ListProduct: "
													+ ListProduct.getName());
									SmartTrolleyGUI.setCurrentProductID(ListProduct.getId());
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
		
		// populate table with ListProduct data
		productTable.setItems(productData);
	}

	// TODO: This method is neither used nor commented - can it be removed?
	public static int getProductDataSize() {
		return productData.size();
	}

}
/************** End of ExampleShoppingListController **************/
