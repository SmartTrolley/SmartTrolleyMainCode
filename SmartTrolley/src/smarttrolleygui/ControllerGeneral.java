/**
 * ControllerGeneral
 * 
 * Class Description: This class will hold methods that are used by multiple controller classes.
 * 
 * @author V1.0 Sam
 * @author V1.1 Arash & Jonny
 * 
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.1] [Date Created: 2/06/14]
 *
 */
package smarttrolleygui;

import Printing.SmartTrolleyPrint;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class ControllerGeneral {

	/**
	 * loadStartScreen is called when the smart trolley logo is pressed. It
	 * calls the goToStartScreen method in SmartTrolleyGUI.java
	 * 
	 * @param event
	 *            - response to click on smart trolley logo in navigation bar
	 *            <p>
	 *            Date Modified: 16 May 2014
	 */
	protected void loadStartScreen(ActionEvent event, SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			System.out.println("error: application == null1");
		} else {
			application.goToStartScreen();
		}
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
	 *            Date Modified: 16 May 2014
	 */
	protected void loadHomeScreen(ActionEvent event, SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			System.out.println("error: application == null5");
		} else {
			application.goToHomeScreen();
		}
	}
	
    /**
     * loadShoppingList is called when the 'list' button is pressed. It calls
     * the goToShoppingList method in SmartTrolleyGUI.java
     * <p>
     * User can view shopping list
     *
     * @param event - response to click on 'list' button
     * <p>
     * Date Modified: 16 May 2014
     */
	protected void loadShoppingList(ActionEvent event,
			SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			System.out.println("error: application == null3");
		} else {
			application.goToShoppingList();
		}
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
	 *            Date Modified: 16 May 2014
	 */
	protected void loadOffers(ActionEvent event, SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			System.out.println("error: application == null4");
		} else {
			application.goToOffers();
		}
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
	 *            Date Modified: 16 May 2014
	 */
	protected void loadFavourites(ActionEvent event, SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			System.out.println("error: application == null2");
		} else {
			application.goToFavourites();
		}
	}

	/**
	 * setUpCellValueFactory generates the cell value factories for interactive table cells,
	 * i.e. cells with buttons, checkboxes, etc.
	 * @param tableColumn - column whose cells the cellValueFactory will be applied to
	 * Date Modified: 21 May 2014
	 */
	protected void setUpCellValueFactory(TableColumn<ListProduct, ListProduct> tableColumn) {
		tableColumn.setCellValueFactory(new Callback<CellDataFeatures<ListProduct, ListProduct>, ObservableValue<ListProduct>>() {
			@Override
			public ObservableValue<ListProduct> call(CellDataFeatures<ListProduct, ListProduct> features) {
				return new ReadOnlyObjectWrapper<ListProduct>(features.getValue());
			}
		});
	}
	
	/**
	 * setUpCheckBoxCellFactory generates the cell factory code for the column containing
	 * the check boxes.
	 * Syntax: TableColumn<S,T> where S is the type of the TableView and 
	 * T is the type of the content in all cells of this TableColumn.
	 * @param checkBoxColumn - column whose cells the cellFactory will be applied to
	 * Date Modified: 21 May 2014
	 */
	public void setUpCheckBoxCellFactory(
			TableColumn<ListProduct, ListProduct> checkBoxColumn) {
		checkBoxColumn
				.setCellFactory(new Callback<TableColumn<ListProduct, ListProduct>, TableCell<ListProduct, ListProduct>>() {
					@Override
					public TableCell<ListProduct, ListProduct> call(
							TableColumn<ListProduct, ListProduct> checkBoxColumn) {
						return new TableCell<ListProduct, ListProduct>() {
							final CheckBox checkBox = new CheckBox();

							@Override
							public void updateItem(final ListProduct ListProduct,
									boolean empty) {
								super.updateItem(ListProduct, empty);
								if (ListProduct != null) {
									setGraphic(checkBox);

									// CheckBox Event Handler
									checkBox.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											System.out
													.println("Pressed checkbox of ListProduct: "
															+ ListProduct.getName());
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
	
	/**
	 * setUpImageCellFactory generates the cell factory code for the column containing
	 * the ListProduct images.
	 * Syntax: TableColumn<S,T> where S is the type of the TableView and 
	 * T is the type of the content in all cells of this TableColumn.
	 * @param imageColumn - column whose cells the cellFactory will be applied to
	 * Date Modified: 21 May 2014
	 */
	public void setUpImageCellFactory(TableColumn<ListProduct, ListProduct> imageColumn) {
		imageColumn
				.setCellFactory(new Callback<TableColumn<ListProduct, ListProduct>, TableCell<ListProduct, ListProduct>>() {
					@Override
					public TableCell<ListProduct, ListProduct> call(
							TableColumn<ListProduct, ListProduct> imageColumn) {
						return new TableCell<ListProduct, ListProduct>() {
							final Button button = new Button();

							@Override
							public void updateItem(final ListProduct ListProduct,
									boolean empty) {
								super.updateItem(ListProduct, empty);
								if (ListProduct != null) {
									Image productImage = new Image(getClass()
											.getResourceAsStream(
													ListProduct.getImage()));
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
													.println("Pressed image of ListProduct: "
															+ ListProduct.getName());
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
	
	/**
	 * setUpProductNameCellFactory generates the cell factory code for the column containing
	 * the ListProduct name.
	 * Syntax: TableColumn<S,T> where S is the type of the TableView and 
	 * T is the type of the content in all cells of this TableColumn.
	 * @param productNameColumn - column whose cells the cellFactory will be applied to
	 * Date Modified: 21 May 2014
	 */
	public void setUpProductNameCellFactory(
			TableColumn<ListProduct, ListProduct> productNameColumn) {
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
											// TODO: add code to move to ListProduct screen here and refactor individual controllers
											SmartTrolleyGUI.setCurrentProductID(ListProduct.getId());
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
	
	/**
	 * setUpAddButtonCellFactory generates the cell factory code for the column containing
	 * the add buttons.
	 * Syntax: TableColumn<S,T> where S is the type of the TableView and 
	 * T is the type of the content in all cells of this TableColumn.
	 * @param addColumn - column whose cells the cellFactory will be applied to
	 * Date Modified: 21 May 2014
	 * @param productTable 
	 */
	public void setUpAddButtonCellFactory(
			TableColumn<ListProduct, ListProduct> addColumn, final TableView<ListProduct> productTable) {
		addColumn
				.setCellFactory(new Callback<TableColumn<ListProduct, ListProduct>, TableCell<ListProduct, ListProduct>>() {
					@Override
					public TableCell<ListProduct, ListProduct> call(
							TableColumn<ListProduct, ListProduct> addColumn) {
						return new TableCell<ListProduct, ListProduct>() {
							final Button button = new Button();

							@Override
							public void updateItem(final ListProduct ListProduct,
									boolean empty) {
								super.updateItem(ListProduct, empty);
								if (ListProduct != null) {
									button.setText("+");
									button.getStyleClass().add(
											"buttonChangeQuantity");
									setGraphic(button);

									// Button Event Handler
									button.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											System.out
													.println("Pressed add button for ListProduct: "
															+ ListProduct.getName());
											
											ObservableList<ListProduct> data = productTable.getItems();
											ListProduct.setQuantity(ListProduct.getQuantity() + 1);

											// Now refresh the table
											refreshTable(data, ListProduct, productTable);
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
	
	private void refreshTable(ObservableList<ListProduct> data, ListProduct selectedProduct, final TableView<ListProduct> productTable) {

		productTable.setItems(null);
		productTable.layout();
		productTable.setItems(data);
		productTable.getSelectionModel().select(selectedProduct);
	}
	
	/**
	 * setUpRemoveButtonCellFactory generates the cell factory code for the column containing
	 * the remove buttons.
	 * Syntax: TableColumn<S,T> where S is the type of the TableView and 
	 * T is the type of the content in all cells of this TableColumn.
	 * @param removeColumn - column whose cells the cellFactory will be applied to
	 * Date Modified: 21 May 2014
	 * @param productTable 
	 */
	public void setUpRemoveButtonCellFactory(
			TableColumn<ListProduct, ListProduct> removeColumn, final TableView<ListProduct> productTable) {
		removeColumn
				.setCellFactory(new Callback<TableColumn<ListProduct, ListProduct>, TableCell<ListProduct, ListProduct>>() {
					@Override
					public TableCell<ListProduct, ListProduct> call(
							TableColumn<ListProduct, ListProduct> removeColumn) {
						return new TableCell<ListProduct, ListProduct>() {
							final Button button = new Button();

							@Override
							public void updateItem(final ListProduct ListProduct,
									boolean empty) {
								super.updateItem(ListProduct, empty);
								if (ListProduct != null) {
									button.setText("-");
									button.getStyleClass().add(
											"buttonChangeQuantity");
									setGraphic(button);

									// Button Event Handler
									button.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											System.out
													.println("Pressed remove button for ListProduct: "
															+ ListProduct.getName());
											
											ObservableList<ListProduct> data = productTable.getItems();
											Integer qty = ListProduct.getQuantity() - 1;

											if (qty < 0) {
												qty = 0;
											}
											ListProduct.setQuantity(qty);

											// Now refresh the table
											refreshTable(data, ListProduct, productTable);

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
}
/************** End of ControllerGeneral **************/
