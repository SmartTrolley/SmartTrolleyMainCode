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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private TableView<ListProduct> productTable;
    @FXML
    private TableColumn<ListProduct, ListProduct> imageColumn;
    @FXML
    private TableColumn<ListProduct, String> productNameColumn;
    @FXML
    private TableColumn<ListProduct, String> priceColumn;
    @FXML
    private TableColumn<ListProduct, Integer> quantityColumn;
    @FXML
    private TableColumn<ListProduct, ListProduct> addColumn;
    @FXML
    private TableColumn<ListProduct, ListProduct> removeColumn;
    
    @FXML
    public Label lblTotalItems;
    @FXML
    public Label lblTotal;
    @FXML
    public Label lblTotalSavings;
    @FXML
    public Button btnSaveList;
    @FXML
    public Button btnDeleteList;
    @FXML
    public Label lblCurrentListName;

    private SmartTrolleyGUI application;
    private ObservableList<String> categories;
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

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
        
        //Set the selection listener
        categoriesList.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
                    FilterProducts(new_val);
            }
        });
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
        
        //Set the total labels
        ObservableList<Double> data = SetTotals();
        lblTotal.setText("Total: ��" + data.get(0).floatValue());
        lblTotalItems.setText("Total Items: " + data.get(1).toString().replace(".0", ""));
        
        categoriesList.getSelectionModel().select("All");
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
            String listName = "";
            try {
                try {
                    // this will load the MySQL driver, each DB has its own driver
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }

                // setup the connection with the DB.
                connect = DriverManager
                        .getConnection("jdbc:mysql://localhost/smarttrolly?", "root","");

                preparedStatement = connect.prepareStatement("SELECT ListID, Name from smarttrolly.lists where ListID = ?");
                preparedStatement.setInt(1, (Integer)application.session.get("currentListID"));
                
                resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    listName = "Current List Name: " + resultSet.getString("Name");
                }

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
              catch(Exception ex){
                Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);  
            }
            finally{
                try {
                    connect.close();
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            application.goToHomeScreen(listName);
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
            application.goToFavourites(lblCurrentListName.getText());
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
            application.goToOffers(lblCurrentListName.getText());
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
        try {
            try {
                // this will load the MySQL driver, each DB has its own driver
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // setup the connection with the DB.
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/smarttrolly?", "root","");
            
            preparedStatement = connect.prepareStatement("SELECT CategoryID, Name from smarttrolly.categories");
            resultSet = preparedStatement.executeQuery();
            
            categories = FXCollections.observableArrayList();
            categories.add("All");
           while(resultSet.next()){
               categories.add(resultSet.getString("Name"));
           }
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
          catch(Exception ex){
            Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);  
        }
        finally{
            try {
                connect.close();
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    
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
       // set up column cell value factories
        productNameColumn.setCellValueFactory(new PropertyValueFactory<ListProduct, String>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<ListProduct, String>("productPrice"));  
        quantityColumn.setCellValueFactory(new PropertyValueFactory<ListProduct, Integer>("Quantity"));
        
        
        addColumn.setCellValueFactory(new Callback<CellDataFeatures<ListProduct, ListProduct>, ObservableValue<ListProduct>>() {
            @Override
            public ObservableValue<ListProduct> call(CellDataFeatures<ListProduct, ListProduct> features) {
                return new ReadOnlyObjectWrapper<ListProduct>(features.getValue());
            }
        });
        removeColumn.setCellValueFactory(new Callback<CellDataFeatures<ListProduct, ListProduct>, ObservableValue<ListProduct>>() {
            @Override
            public ObservableValue<ListProduct> call(CellDataFeatures<ListProduct, ListProduct> features) {
                return new ReadOnlyObjectWrapper<ListProduct>(features.getValue());
            }
        });
        imageColumn.setCellValueFactory(new Callback<CellDataFeatures<ListProduct, ListProduct>, ObservableValue<ListProduct>>() {
            @Override
            public ObservableValue<ListProduct> call(CellDataFeatures<ListProduct, ListProduct> features) {
                return new ReadOnlyObjectWrapper<ListProduct>(features.getValue());
            }
        });
       
        // set up cell factories for columns containing images / buttons
        addColumn.setCellFactory(new Callback<TableColumn<ListProduct, ListProduct>, TableCell<ListProduct, ListProduct>>() {
            @Override
            public TableCell<ListProduct, ListProduct> call(TableColumn<ListProduct, ListProduct> addColumn) {
                return new TableCell<ListProduct, ListProduct>() {
                    final Button button = new Button();

                    @Override
                    public void updateItem(final ListProduct product, boolean empty) {
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
                                    
                                    ObservableList<ListProduct> data = productTable.getItems();
                                    product.setQuantity(product.getQuantity() + 1);
                                    
                                    //Now refresh the table
                                    refreshTable(data, product);
                                    
                                }
                                
                            });
                        } else {
                            setGraphic(null);
                        }
                    }
                    
                   
                };
            }
        });
        removeColumn.setCellFactory(new Callback<TableColumn<ListProduct, ListProduct>, TableCell<ListProduct, ListProduct>>() {
            @Override
            public TableCell<ListProduct, ListProduct> call(TableColumn<ListProduct, ListProduct> removeColumn) {
                return new TableCell<ListProduct, ListProduct>() {
                    final Button button = new Button();

                    @Override
                    public void updateItem(final ListProduct product, boolean empty) {
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
                                    
                                    ObservableList<ListProduct> data = productTable.getItems();
                                    Integer qty = product.getQuantity() - 1;
                                    
                                    if(qty < 0){
                                        qty = 0;
                                    }
                                    product.setQuantity(qty);
                                    
                                    //Now refresh the table
                                    refreshTable(data, product);
                                }
                            });
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        imageColumn.setCellFactory(new Callback<TableColumn<ListProduct, ListProduct>, TableCell<ListProduct, ListProduct>>() {
            @Override
            public TableCell<ListProduct, ListProduct> call(TableColumn<ListProduct, ListProduct> imageColumn) {
                return new TableCell<ListProduct, ListProduct>() {
                    final Button button = new Button();

                    @Override
                    public void updateItem(final ListProduct product, boolean empty) {
                        super.updateItem(product, empty);
                        if (product != null) {
                            Image productImage = new Image(getClass().getResourceAsStream(product.getImageURL()));
                            button.setGraphic(new ImageView(productImage));
                            button.setPrefSize(80, 60);
                            button.getStyleClass().add("imageButton");
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

    
     private void FilterProducts(String categoryName) {
        try {
            try {
                // this will load the MySQL driver, each DB has its own driver
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // setup the connection with the DB.
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/smarttrolly?", "root","");
            
            ObservableList<ListProduct> filteredProducts = FXCollections.observableArrayList();
            
            productTable.getItems().clear();
            filteredProducts.clear();
            if(categoryName == "All"){
                //Get all products
                preparedStatement = connect.prepareStatement(
                    "SELECT p.ProductID, p.Name, p.Price, p.CategoryID, p.IsFavourite, p.Image, lp.Quantity FROM smarttrolly.lists l " +
                    "join smarttrolly.lists_products lp on lp.ListID = l.ListID " +
                    "join smarttrolly.products p on p.ProductID = lp.ProductID " +
                    "where l.ListID = ?");
                
                preparedStatement.setInt(1, application.session.get("currentListID"));
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    ListProduct p = new ListProduct(resultSet.getString("Image"), 
                            resultSet.getString("Name"), 
                            resultSet.getDouble("Price"), 
                            resultSet.getInt("CategoryID"), 
                            resultSet.getBoolean("IsFavourite"),
                            resultSet.getInt("Quantity"),
                            resultSet.getInt("ProductID")
                    );
                    
                    
                    filteredProducts.add(p);
                }
                
                //Now populate the products table
                productTable.getItems().addAll(filteredProducts);
                
            } else{
                //Get all products within the selected category
                preparedStatement = connect.prepareStatement(
                    "SELECT p.ProductID, p.Name, p.Price, p.CategoryID, p.IsFavourite, p.Image, lp.Quantity FROM smarttrolly.lists l\n" +
                    "join smarttrolly.lists_products lp on lp.ListID = l.ListID\n" +
                    "join smarttrolly.products p on p.ProductID = lp.ProductID\n" +
                    "join smarttrolly.categories c on c.CategoryID = p.CategoryID\n" +
                    "where c.Name = ? AND l.ListID = ?");  
                
                preparedStatement.setString(1, categoryName);
                preparedStatement.setInt(2, application.session.get("currentListID"));
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    ListProduct p = new ListProduct(resultSet.getString("Image"), 
                            resultSet.getString("Name"), 
                            resultSet.getDouble("Price"), 
                            resultSet.getInt("CategoryID"), 
                            resultSet.getBoolean("IsFavourite"),
                            resultSet.getInt("Quantity"),
                            resultSet.getInt("ProductID")
                    );
                    
                    
                    filteredProducts.add(p);
                }
                
                //Now populate the products table
                productTable.getItems().addAll(filteredProducts);
            }
           
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
          catch(Exception ex){
            Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);  
        }
        finally{
            try {
                connect.close();
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
     
     private void refreshTable(ObservableList<ListProduct> data, ListProduct selectedProduct) {
         
         productTable.setItems(null);
         productTable.layout();
         productTable.setItems(data);
         productTable.getSelectionModel().select(selectedProduct);
     }

    private ObservableList<Double> SetTotals(){
        double total = 0;
        double totalItems = 0;
        double totalSavings = 0;
        //Set total and total items first
        try {
            try {
                // this will load the MySQL driver, each DB has its own driver
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // setup the connection with the DB.
            connect = DriverManager.getConnection("jdbc:mysql://localhost/smarttrolly?", "root","");
            String sqlStatement = "SELECT lp.ProductID, ListID, Quantity, Price FROM lists_products lp join products p on p.ProductID = lp.ProductID WHERE ListID = ?";
            
            preparedStatement = connect.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, application.session.get("currentListID"));
            resultSet = preparedStatement.executeQuery();
            
            
            
            while(resultSet.next()){
                total += resultSet.getDouble("Price") * resultSet.getInt("Quantity");
                totalItems += resultSet.getInt("Quantity");
                
            }
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
          catch(Exception ex){
            Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);  
        }
        finally{
            try {
                connect.close();
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllShoppingListsScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        
        ObservableList<Double> data = FXCollections.observableArrayList(total, totalItems, totalSavings);
        return data;
    }
    
    public void deleteList(){
        try {
            // setup the connection with the DB.
            connect = DriverManager.getConnection("jdbc:mysql://localhost/smarttrolly?", "root","");
            String sqlStatement = "DELETE FROM lists WHERE ListID = ?";
            
            preparedStatement = connect.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, application.session.get("currentListID"));
            preparedStatement.execute();
            
            application.goToAllShoppingListsScreen();
        } catch (SQLException ex) {
            Logger.getLogger(ExampleShoppingListController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {  
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExampleShoppingListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void saveList(){
        try {
            // setup the connection with the DB.
            connect = DriverManager.getConnection("jdbc:mysql://localhost/smarttrolly?", "root","");
            
            //Loop through the products and update the quantity in the lists_products table (if quantity is 0 then remove the product from the list)
            ObservableList<ListProduct> data = productTable.getItems();
            ObservableList<ListProduct> productsToRemove = FXCollections.observableArrayList();
            
            for (ListProduct p : data) {
                Integer qty = p.getQuantity();
                
                if(qty <= 0){
                    productsToRemove.add(p);
                    preparedStatement = connect.prepareStatement("DELETE FROM lists_products WHERE ProductID = ?");
                    preparedStatement.setInt(1, p.getID());
                }else{
                    preparedStatement = connect.prepareStatement(String.format("UPDATE lists_products SET Quantity = %s WHERE ProductID = ?", qty));
                    preparedStatement.setInt(1, p.getID());
                }
                
                preparedStatement.execute();
            }
            
            ObservableList<Double> totals = SetTotals();
            lblTotal.setText("Total: ��" + totals.get(0).floatValue());
            lblTotalItems.setText("Total Items: " + totals.get(1).toString().replace(".0", ""));
            
            for (ListProduct p : productsToRemove) {
                data.remove(p);
            }
            refreshTable(data, null);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ExampleShoppingListController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {  
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExampleShoppingListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
/**************End of ExampleShoppingListController**************/
