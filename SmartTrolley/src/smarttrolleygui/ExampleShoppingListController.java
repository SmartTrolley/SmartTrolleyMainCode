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

import DatabaseConnectors.SqlConnection;

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
    private TableColumn<Product, String> priceColumn;
    @FXML
    private TableColumn<Product, Product> addColumn;
    @FXML
    private TableColumn<Product, Product> removeColumn;

    private SmartTrolleyGUI application;
    private ObservableList<String> categories;
    private ObservableList<Product> productData;
    
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

    /**
     * setApp
     *
     * @param application
     * <p>
     * Date Modified: 06 Mar 2014
     */
    public void setApp(SmartTrolleyGUI application) {
        this.application = application;
    }

    /**
     * loadStartScreen is called when the smart trolley logo is pressed. It
     * calls the goToStartScreen method in SmartTrolleyGUI.java
     *
     * @param event - response to click on smart trolley logo in navigation bar
     * <p>
     * Date Modified: 6 Mar 2014
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
     * @param event - response to click on 'home' button
     * <p>
     * Date Modified: 28 Feb 2014
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
     * @param event - response to click on 'favourites' button
     * <p>
     * Date Modified: 28 Feb 2014
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
     * @param event - response to click on 'offers' button
     * <p>
     * Date Modified: 7 Mar 2014
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
     * <p>
     * Date Modified: 7 Mar 2014
     */
    private ObservableList<String> initializeCategories() {
        categories = FXCollections.observableArrayList(
                "All",
                "Bakery",
                "Fruit & Vegetables",
                "Dairy & Eggs",
                "Meat & Seafood",
                "Frozen",
                "Drinks",
                "Snacks & Sweets",
                "Desserts"
        );

        return categories;
    }

    /**
     * initializeProductTable fills the TableView with data and sets up cell
     * factories
     * <p>
     * User can navigate through product database
     * <p>
     * Date Modified: 30 Apr 2014
     */
    private void initializeProductTable() {
    	//Create new SqlConnection to retrieve product data
    	SqlConnection sqlConnector = new SqlConnection();
    	
        // Fill table with sample products
        productData = sqlConnector.getListOfProducts();
        productTable.setItems(productData);

        // set up column cell value factories
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
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
//                            button.getStyleClass().add("buttonImage");
                            setGraphic(checkBox);

                            // Button Event Handler
                            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    System.out.println("Pressed checkbox of product: " + product.getName());
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
                            Image productImage = new Image(getClass().getResourceAsStream(product.getImage()));
                            button.setGraphic(new ImageView(productImage));
                            button.setPrefSize(80, 60);
                            button.getStyleClass().add("buttonImage");
                            setGraphic(button);

                            // Button Event Handler
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    System.out.println("Pressed image of product: " + product.getName());
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
                                    System.out.println("Pressed add button for product: " + product.getName());
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
                                    System.out.println("Pressed remove button for product: " + product.getName());
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
/**************End of ExampleShoppingListController**************/