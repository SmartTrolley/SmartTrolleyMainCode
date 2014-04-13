/**
 * ExampleShoppingListController
 *
 * Class Description: ExampleShoppingListController allows java interaction with
 * ExampleShoppingList.fxml
 *
 * @author Arne
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.0] [Date Created: 06/03/14]
 */
package smarttrolleygui;

import java.net.URL;
import java.util.ResourceBundle;

import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxResult;
import se.mbaeumer.fxmessagebox.MessageBoxType;

import javafx.beans.property.ReadOnlyObjectWrapper;
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
	private TableView<Product> productTable;
	@FXML
	private TableColumn<Product, Product> checkBoxColumn;
	@FXML
	private TableColumn<Product, Product> imageColumn;
	@FXML
	private TableColumn<Product, String> productNameColumn;
	@FXML
	private TableColumn<Product, Double> QtyColumn;
	@FXML
	private TableColumn<Product, String> priceColumn;
	@FXML
	private TableColumn<Product, Product> addColumn;
	@FXML
	private TableColumn<Product, Product> removeColumn;
	@FXML
	private Button deleteListButton;
	@FXML
	private Label currentTotalLabel;

	private SmartTrolleyGUI application;
	private ObservableList<String> categories;
	private ObservableList<Product> productData;
	private double totalPrice;

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

		initializeProductTable();
	}

	public void deleteListPressed() {
		MessageBox mb = new MessageBox("Do you want to delete that?", MessageBoxType.YES_NO);
		mb.showAndWait();
		if (mb.getMessageBoxResult() == MessageBoxResult.YES) {
			// The following needs to be implemented.
			// sendDeleteToDatabse(listName);
		} else {

		}
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
	 * loadStartScreen is called when the smart trolley logo is pressed. It
	 * calls the goToStartScreen method in SmartTrolleyGUI.java
	 * 
	 * @param event
	 *            - response to click on smart trolley logo in navigation bar
	 *            <p>
	 *            Date Modified: 6 Mar 2014
	 */
	public void loadStartScreen(ActionEvent event) {

		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			System.out.println("error: application == null");
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
	 *            Date Modified: 28 Feb 2014
	 */
	public void loadHomeScreen(ActionEvent event) {

		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			System.out.println("error: application == null");
		} else {
			application.goToHomeScreen();
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
	 *            Date Modified: 28 Feb 2014
	 */
	public void loadFavourites(ActionEvent event) {

		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			System.out.println("error: application == null");
		} else {
			application.goToFavourites();
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
	 *            Date Modified: 7 Mar 2014
	 */
	public void loadOffers(ActionEvent event) {

		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			System.out.println("error: application == null");
		} else {
			application.goToOffers();
		}
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
		categories = FXCollections.observableArrayList("All", "Bakery", "Fruit & Vegetables", "Dairy & Eggs", "Meat & Seafood", "Frozen", "Drinks", "Snacks & Sweets", "Desserts");

		return categories;
	}

	/**
	 * initializeProductTable fills the TableView with data and sets up cell
	 * factories
	 * <p>
	 * User can navigate through product database
	 * <p>
	 * Date Modified: 9 Mar 2014
	 */
	private void initializeProductTable() {
		// Fill table with sample products
		productData = initializeProductData();
		productTable.setItems(productData);

		// set up column cell value factories
		productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
		QtyColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("quantity"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productPrice"));
		checkBoxColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
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
		addColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
			@Override
			public ObservableValue<Product> call(CellDataFeatures<Product, Product> features) {
				return new ReadOnlyObjectWrapper<Product>(features.getValue());
			}
		});
		removeColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
			@Override
			public ObservableValue<Product> call(CellDataFeatures<Product, Product> features) {
				return new ReadOnlyObjectWrapper<Product>(features.getValue());
			}
		});

		// set up cell factories for columns containing images / buttons
		checkBoxColumn.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
			@Override
			public TableCell<Product, Product> call(TableColumn<Product, Product> imageColumn) {
				return new TableCell<Product, Product>() {
					final CheckBox checkBox = new CheckBox();

					@Override
					public void updateItem(final Product product, boolean empty) {
						super.updateItem(product, empty);
						if (product != null) {
							// button.getStyleClass().add("buttonImage");
							setGraphic(checkBox);

							// Button Event Handler
							checkBox.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									System.out.println("Pressed checkbox of product: " + product.getProductName());
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
							Image productImage = new Image(getClass().getResourceAsStream(product.getImageURL()));
							button.setGraphic(new ImageView(productImage));
							button.setPrefSize(80, 60);
							button.getStyleClass().add("buttonImage");
							setGraphic(button);

							// Button Event Handler
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									System.out.println("Pressed image of product: " + product.getProductName());
								}
							});
						} else {
							setGraphic(null);
						}
					}
				};
			}
		});
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
									System.out.println("Pressed add button for product: " + product.getProductName());
									product.incrQuantity();
									QtyColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("quantity"));
									totalPrice += product.getProductPrice();
									currentTotalLabel.setText(product.getQuantity() + "£ " + totalPrice + " current total");
								}
							});
						} else {
							setGraphic(null);
						}
					}
				};
			}
		});

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
									System.out.println("Pressed remove button for product: " + product.getProductName());
									if (product.getQuantity() > 0) {
										totalPrice -= product.getProductPrice();
										product.decrQuantity();
										//QtyColumn.setCellValueFactory(arg0)
										currentTotalLabel.setText(product.getQuantity() + "£ " + totalPrice + " current total");
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
	}

	/**
	 * initializeProductData sets up the list of products that will be displayed
	 * on screen.
	 * <p>
	 * User can navigate through product database.
	 * 
	 * @return productData - list of products
	 *<p> Date Modified: 7 Mar 2014
	 */
	private ObservableList<Product> initializeProductData() {
		productData = FXCollections.observableArrayList(new Product("img/SampleProducts/ariel.jpg", "Ariel", 4.75), new Product("img/SampleProducts/cravendale_2L_milk.jpg", "Cravendale 2L", 2.99),
				new Product("img/SampleProducts/holme_farmed_venison_steak.jpg", "Holme Farmed Venison Steak", 5.00), new Product("img/SampleProducts/hovis_bread.jpg", "Hovis Bread", 1.35),
				new Product("img/SampleProducts/innocent_noodle_pot.jpg", "Innocent Noodle Pot", 3.90), new Product("img/SampleProducts/lavazza_espresso.jpg", "Lavazza Espresso", 2.50), new Product(
						"img/SampleProducts/nivea_shower_cream.jpg", "Nivea Shower Creme", 1.50), new Product("img/SampleProducts/pink_lady_apple.jpg", "Pink Lady Apple", 0.48), new Product(
						"img/SampleProducts/star-wars-lollies.jpg", "Star Wars Lollies", 2.00), new Product("img/SampleProducts/strawberry_conserve.jpg", "Strawberry Conserve", 2.69), new Product(
						"img/SampleProducts/sugar_puffs.jpg", "Sugar Puffs", 2.29), new Product("img/SampleProducts/yorkie.jpg", "Nestle Yorkie Milk Chocolate Bar", 0.60));
		return productData;
	}
}
/************** End of ExampleShoppingListController **************/
