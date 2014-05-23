/**
 * ControllerGeneral
 * 
 * Class Description: This class will hold methods that are used by multiple controller classes.
 * 
 * @author Sam
 * 
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.0] [Date Created: 15/05/14]
 *
 */
package smarttrolleygui;

import Printing.SmartTrolleyPrint;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
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
	 * User navigates through product database
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
	protected void setUpCellValueFactory(TableColumn<Product, Product> tableColumn) {
		tableColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
			@Override
			public ObservableValue<Product> call(CellDataFeatures<Product, Product> features) {
				return new ReadOnlyObjectWrapper<Product>(features.getValue());
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
			TableColumn<Product, Product> checkBoxColumn) {
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
	}
	
	/**
	 * setUpImageCellFactory generates the cell factory code for the column containing
	 * the product images.
	 * Syntax: TableColumn<S,T> where S is the type of the TableView and 
	 * T is the type of the content in all cells of this TableColumn.
	 * @param imageColumn - column whose cells the cellFactory will be applied to
	 * Date Modified: 21 May 2014
	 */
	public void setUpImageCellFactory(TableColumn<Product, Product> imageColumn) {
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
	}
	
	/**
	 * setUpProductNameCellFactory generates the cell factory code for the column containing
	 * the product name.
	 * Syntax: TableColumn<S,T> where S is the type of the TableView and 
	 * T is the type of the content in all cells of this TableColumn.
	 * @param productNameColumn - column whose cells the cellFactory will be applied to
	 * Date Modified: 21 May 2014
	 */
	public void setUpProductNameCellFactory(
			TableColumn<Product, Product> productNameColumn) {
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
											// TODO: add code to move to product screen here and refactor individual controllers
											SmartTrolleyGUI.setCurrentProductID(product.getId());
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
	 */
	public void setUpAddButtonCellFactory(
			TableColumn<Product, Product> addColumn) {
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
	}
	
	/**
	 * setUpRemoveButtonCellFactory generates the cell factory code for the column containing
	 * the remove buttons.
	 * Syntax: TableColumn<S,T> where S is the type of the TableView and 
	 * T is the type of the content in all cells of this TableColumn.
	 * @param removeColumn - column whose cells the cellFactory will be applied to
	 * Date Modified: 21 May 2014
	 */
	public void setUpRemoveButtonCellFactory(
			TableColumn<Product, Product> removeColumn) {
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
	}
}
/************** End of ControllerGeneral **************/
