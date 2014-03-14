/**
 * HomeScreenController
 *
 * Class Description: HomeScreenController allows java interaction with
 * HomeScreen.fxml
 *
 * @author Arne
 *
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.0] [Date Created: 22/02/14]
 */
package smarttrolleygui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class HomeScreenController implements Initializable {

    @FXML
    private ListView<String> categoriesList;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Product> imageColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, String> priceColumn;
    @FXML
    private TableColumn<Product, Product> addColumn;

    private SmartTrolleyGUI application;
    private ObservableList<String> categories;
    private ObservableList<Product> productData;

    /**
     * initialize is automatically called when the controller is created.
     * <p>
     * Date Modified: 22 Feb 2014
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
     * Date Modified: 28 Feb 2014
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
     * loadShoppingList is called when the 'list' button is pressed. It calls
     * the goToShoppingList method in SmartTrolleyGUI.java
     * <p>
     * User can view shopping list
     *
     * @param event - response to click on 'offers' button
     * <p>
     * Date Modified: 6 Mar 2014
     */
    public void loadShoppingList(ActionEvent event) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null");
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
     * Date Modified: 9 Mar 2014
     */
    private void initializeProductTable() {
        // Fill table with sample products
        productData = initializeProductData();
        productTable.setItems(productData);

        // set up column cell value factories
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productPrice"));
        addColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
            @Override
            public ObservableValue<Product> call(TableColumn.CellDataFeatures<Product, Product> features) {
                return new ReadOnlyObjectWrapper<Product>(features.getValue());
            }
        });
        imageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
            @Override
            public ObservableValue<Product> call(TableColumn.CellDataFeatures<Product, Product> features) {
                return new ReadOnlyObjectWrapper<Product>(features.getValue());
            }
        });

        // set up cell factories for columns containing images / buttons
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
    }

    /**
     * initializeProductData sets up the list of products that will be displayed
     * on screen.
     * <p>
     * User can navigate through product database.
     *
     * @return productData - list of products
     * <p>
     * Date Modified: 7 Mar 2014
     */
    private ObservableList<Product> initializeProductData() {
        productData = FXCollections.observableArrayList(
                
                new Product("img/SampleProducts/Activia.jpg", "Activia 0% Fat Free Peach Yogurt", "1.84"),
                new Product("img/SampleProducts/alpen_blueberry_cranberry.jpg", "Alpen Cereal Bars Blueberry & Cranberry", "1.84"),
                new Product("img/SampleProducts/anchor_butter.jpg", "Anchor Butter", "1.70"),
                new Product("img/SampleProducts/ariel.jpg", "Ariel", "4.75"),
                new Product("img/SampleProducts/Arla_Lactofree Whole_Milk.jpg", "Arla Lactofree Whole Milk (1L)", "1.00"),
                new Product("img/SampleProducts/ben_and_jerrys.jpg", "Ben & Jerry's Cookie Dough Ice Cream", "3.00"),
                new Product("img/SampleProducts/birds_eye_fish_fingers.jpg", "Birds Eye Cod Fish Fingers", "3.98"),
                new Product("img/SampleProducts/British_Single_Cream.jpg", "British Single Cream", "1.05"),
                new Product("img/SampleProducts/chicago_town_pizza.jpg", "Chicago Town Deep Dish Chicken Melt Pizza", "2.00"),
                new Product("img/SampleProducts/Clover_Seedburst.jpg", "Clover Seedburst Spread", "1.00"),
                new Product("img/SampleProducts/coca_cola.jpg", "Coca Cola", "1.98"),
                new Product("img/SampleProducts/Corner_Blueberry_Yogurt.jpg", "Muller Fruit Corner Blueberry Yogurt", "0.68"),
                new Product("img/SampleProducts/cravendale_2L_milk.jpg", "Cravendale 2L", "2.99"),
                new Product("img/SampleProducts/crunchy_nut.jpg", "Cravendale (2L)", "2.20"),
                new Product("img/SampleProducts/Egg_For_Soldiers.jpg", "Eggs for Soldiers Free Range Eggs(6)", "1.80"),
                new Product("img/SampleProducts/Frijj_Sticky_Toffee_Pudding_Milkshake.jpg", "Frijj Sticky Toffee Pudding Milkshake", "1.49"),
                new Product("img/SampleProducts/haagen_dasz.jpg", "Haagen-Dazs Belgian Chocolate Ice Cream", "4.45"),
                new Product("img/SampleProducts/heinz_beanz.jpg", "Heinz Baked Beanz in Tomato Sauce", "0.68"),
                new Product("img/SampleProducts/holme_farmed_venison_steak.jpg", "Holme Farmed Venison Steak", "5.00"),
                new Product("img/SampleProducts/hovis_bread.jpg", "Hovis Bread", "1.35"),
                new Product("img/SampleProducts/Ilchester_Applewood_Cheddar_Slices.jpg", "Ilchester Applewood Cheddar Slices", "1.90"),
                new Product("img/SampleProducts/innocent_noodle_pot.jpg", "Innocent Noodle Pot", "3.90"),
                new Product("img/SampleProducts/kelloggs_cornflakes.jpg", "Kellogg's Corn Flakes", "1.84"),
                new Product("img/SampleProducts/Lassi_Yogurt.jpg", "Bio Green Lassi Yogurt Smoothie with Mango", "1.60"),
                new Product("img/SampleProducts/lavazza_espresso.jpg", "Lavazza Espresso", "2.50"),
                new Product("img/SampleProducts/McCain_chips.jpg", "McCain Home Chips Crinkle Cut", "2.00"),
                new Product("img/SampleProducts/nivea_shower_cream.jpg", "Nivea Shower Creme", "1.50"),
                new Product("img/SampleProducts/Nutty_Hazelnut.jpg", "Nutty Hazelnut Low Fat Yogurt", "1.00"),
                new Product("img/SampleProducts/oreo_cookies.jpg", "Oreo Cookies - Vanilla", "1.08"),
                new Product("img/SampleProducts/PG_Tips_Tea_Bags.jpg", "PG Tips Pyramid Tea Bags", "4.18"),
                new Product("img/SampleProducts/pink_lady_apple.jpg", "Pink Lady Apple", "0.48"),
                new Product("img/SampleProducts/pringles_salt_vinegar.jpg", "Pringles Salt & Vinegar", "2.30"),
                new Product("img/SampleProducts/Pure_Dairy_Free_Soya_Spread.jpg", "Pure Dairy Free Soya Spread", "1.50"),
                new Product("img/SampleProducts/sherert_dib_dab.jpg", "Candyland Sherbert Dib Dab", "0.35"),
                new Product("img/SampleProducts/Soya_Milk.jpg", "Alpro Soya Milk Alternative 1+ Years (1L)", "1.32"),
                new Product("img/SampleProducts/squares.jpg", "Rice Krispies Squares Chewy Marshmallow", "1.50"),
                new Product("img/SampleProducts/star-wars-lollies.jpg", "Star Wars Lollies", "2.00"),
                new Product("img/SampleProducts/strawberry_conserve.jpg", "Strawberry Conserve", "2.69"),
                new Product("img/SampleProducts/sugar_puffs.jpg", "Sugar Puffs", "2.29"),
                new Product("img/SampleProducts/uncle_bens_sweet_and_sour.jpg", "Uncle Ben's Sweet & Sour Sauce", "2.38"),
                new Product("img/SampleProducts/Vitality_Multifruit_Yoghurt.jpg", "Muller Vitality Multifruit Yoghurt Drink", "1.00"),
                new Product("img/SampleProducts/walkers_wotsits.jpg", "Walkers Baked Wotsits Really Cheesy", "4.08"),
                new Product("img/SampleProducts/Yakult_Original_Fermented_Milk_Drink.jpg", "Yakult Original Fermented Milk Drink", "2.74"),
                new Product("img/SampleProducts/yorkie.jpg", "Nestle Yorkie Milk Chocolate Bar", "0.60")
        );
        return productData;
    }
}
/**
 * ************End of HomeScreenController*************
 */
