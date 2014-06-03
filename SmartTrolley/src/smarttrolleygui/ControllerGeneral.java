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
import toolBox.SmartTrolleyToolBox;

public class ControllerGeneral {

	/**
	 * Enumerated type for the screen to load
	 */
	enum Screen {
		STARTSCREEN, HOMESCREEN, SHOPPINGLISTSCREEN, OFFERSSCREEN, FAVORITESSCREEN, CREATENEWLISTSCREEN, ALLSHOPPINGLISTSSCREEN
	}
	
	

	/**
	* loadScreen is called when a screen needs to be loaded
	*@param screenToLoad - The screen that needs to be loaded
	*@param application - The application instance that is running
	*<p> Date Modified: 30 May 2014
	*/
	protected static void loadScreen(Screen screenToLoad, SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			SmartTrolleyToolBox.print("error: application == null1");
		} else {
			switch (screenToLoad) {
			case STARTSCREEN:
				application.goToStartScreen();
				break;
			case FAVORITESSCREEN:
				application.goToFavourites();
				break;
			case HOMESCREEN:
				application.goToHomeScreen();
				break;
			case OFFERSSCREEN:
				application.goToOffers();
				break;
			case SHOPPINGLISTSCREEN:
				application.goToShoppingList();
				break;
			case CREATENEWLISTSCREEN:
				application.goToCreateNewListScreen();
				break;
			case ALLSHOPPINGLISTSSCREEN: application.goToAllShoppingListsScreen();
			break;
			default:
				break;
			}

		}
	}

	/**
	 * loadStartScreen is called when the smart trolley logo is pressed. It
	* calls the goToStartScreen method in SmartTrolleyGUI.java
	 */
	protected static void loadStartScreen(SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			SmartTrolleyToolBox.print("error: application == null1");
		} else {
			application.goToStartScreen();
		}
	}

	/**
	 * loadHomeScreen is called when the 'home' button is pressed. It calls the
	 * goToHomeScreen method in SmartTrolleyGUI.java
	 * <p>
	 * User navigates through product database
	 */
	protected static void loadHomeScreen(SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			SmartTrolleyToolBox.print("error: application == null5");
		} else {
			application.goToHomeScreen();
		}
	}

	/**
	 * loadShoppingList is called when the 'list' button is pressed. It calls
	 * the goToShoppingList method in SmartTrolleyGUI.java
	 * <p> User can view shopping list
	 */
	protected static void loadShoppingList(SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			SmartTrolleyToolBox.print("error: application == null3");
		} else {
			application.goToShoppingList();
		}
	}

	/**
	 * loadOffers is called when the 'offers' button is pressed. It calls the
	* goToOffers method in SmartTrolleyGUI.java
	 * <p>
	 * User can browse store's offers
	 */
	protected static void loadOffers(SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			SmartTrolleyToolBox.print("error: application == null4");
		} else {
			application.goToOffers();
		}
	}

	/**
	 * loadFavourites is called when the 'favourites' button is pressed. It
	* calls the goToFavourites method in SmartTrolleyGUI.java
	 * <p>
	 * User can maintain list of favourite products
	 */
	protected static void loadFavourites(SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			SmartTrolleyToolBox.print("error: application == null2");
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
	protected static void setUpCellValueFactory(TableColumn<Product, Product> tableColumn) {
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
	public static void setUpCheckBoxCellFactory(TableColumn<Product, Product> checkBoxColumn) {
		checkBoxColumn.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
			@Override
			public TableCell<Product, Product> call(TableColumn<Product, Product> checkBoxColumn) {
				return new TableCell<Product, Product>() {
					final CheckBox checkBox = new CheckBox();

					@Override
					public void updateItem(final Product product, boolean empty) {
						super.updateItem(product, empty);
						if (product != null) {
							setGraphic(checkBox);

							// CheckBox Event Handler
							checkBox.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									SmartTrolleyToolBox.print("Pressed checkbox of product: " + product.getName());
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
	public static void setUpImageCellFactory(TableColumn<Product, Product> imageColumn) {
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
								}						
							button.setPrefSize(80, 60);
							button.getStyleClass().add("buttonImage");
							setGraphic(button);

							// Button Event Handler
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									SmartTrolleyToolBox.print("Pressed image of product: " + product.getName());
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
	public static void setUpAddButtonCellFactory(TableColumn<Product, Product> addColumn) {
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
	}

	/**
	 * setUpRemoveButtonCellFactory generates the cell factory code for the column containing
	 * the remove buttons.
	 * Syntax: TableColumn<S,T> where S is the type of the TableView and 
	 * T is the type of the content in all cells of this TableColumn.
	 * @param removeColumn - column whose cells the cellFactory will be applied to
	 * Date Modified: 21 May 2014
	 */
	public static void setUpRemoveButtonCellFactory(TableColumn<Product, Product> removeColumn) {
		removeColumn.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
			@Override
			public TableCell<Product, Product> call(TableColumn<Product, Product> removeColumn) {
				return new TableCell<Product, Product>() {
					final Button button = new Button();

					@Override
					public void updateItem(final Product product, boolean empty) {
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
