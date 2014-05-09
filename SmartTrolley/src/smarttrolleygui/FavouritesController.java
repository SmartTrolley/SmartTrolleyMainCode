/**
 * FavouritesController
 * 
* Class Description: class that allows java interaction with Favourites.fxml
 * file.
 * 
* @author Arne
 * @author [Name2]
 * 
* @author [Checked By:] [Checker(s) fill here]
 * 
* @version [1.0] [Date Created: 22/02/14]
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author me
 */
public class FavouritesController implements Initializable {

    @FXML
    private ListView categoriesList;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn imageColumn;
    @FXML
    private TableColumn productNameColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TableColumn addColumn;

    private SmartTrolleyGUI application;
    private Stage stage;
    private ObservableList categories;
    private ObservableList<Product> productData;
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fill list on the LHS of the screen with different categories
        categories = initializeCategories();
        categoriesList.setItems(categories);
        
        //Create new SqlConnection to retrieve product data
    	SqlConnection sqlConnector = new SqlConnection();

        // Fill table on RHS with sample products
        productData = sqlConnector.getListOfProducts();    
        productTable.setItems(productData);

//        imageColumn.setCellValueFactory(new PropertyValueFactory("imageURL"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("productPrice"));
        // what's the difference between the last three lines and the next three? which is correct?
//        imageColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("imageURL"));
//        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
//        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productPrice"));

        /**
         * ************** source:
         * http://stackoverflow.com/questions/16360323/javafx-table-how-to-add-components *****************
         */
        addColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
            @Override
            public ObservableValue<Product> call(CellDataFeatures<Product, Product> features) {
                return new ReadOnlyObjectWrapper(features.getValue());
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
        /**
         * ************** END OF source:
         * http://stackoverflow.com/questions/16360323/javafx-table-how-to-add-components *****************
         */

        /**
         * ************** same thing for image column *****************
         */
        imageColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
            @Override
            public ObservableValue<Product> call(CellDataFeatures<Product, Product> features) {
                return new ReadOnlyObjectWrapper(features.getValue());
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
                            Image productImage = new Image(getClass().getResourceAsStream("img/SampleProducts/" + product.getName() + ".jpg"));
                            button.setGraphic(new ImageView(productImage));
                            button.getStyleClass().add("imageButton");
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
        /**
         * ************** end of image column stuff *****************
         */        
    }
    

    private ObservableList<String> initializeCategories() {
    	//Create new SqlConnection to retrieve product data
    	SqlConnection sqlConnector = new SqlConnection();    
         
        categories = sqlConnector.getListOfCategories();

        return categories;
    }

    void setApp(SmartTrolleyGUI application) {
        this.application = application;
        this.stage = application.stage;
    }

    public void loadHomeScreen(ActionEvent event) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("my error message: application == null");
        } else {
            application.goToHomeScreen();
        }
    }

    public void loadShoppingList(ActionEvent event) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("my error message: application == null");
        } else {
            application.goToShoppingList();
        }
    }

    public void loadOffers(ActionEvent event) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("my error message: application == null");
        } else {
            application.goToOffers();
        }
    }
}
/**************End of FavouritesController**************/
