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

import java.io.InputStream;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class HomeScreenController implements Initializable {

    @FXML
    private ListView<String> lstViewCategories;
    @FXML
    public Label lblCurrentListName;
    @FXML
    public Label lblTotalItems;
    @FXML
    public Label lblTotal;
    @FXML
    public Label lblTotalSavings;
    @FXML
    public TableView<Product> productsTable;
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
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fill list on the LHS of the screen with different product categories
        categories = initializeCategories();
        lstViewCategories.setItems(categories);
        SetUpColumns();
        
        //Set the selection listener
        lstViewCategories.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
                    FilterProducts(new_val);
            }
        });
        
        lstViewCategories.getSelectionModel().select("All");
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
        
        //Set the total labels
        ObservableList<Double> data = SetTotals();
        lblTotal.setText("Total: ��" + data.get(0).floatValue());
        lblTotalItems.setText("Total Items: " + data.get(1).toString().replace(".0", ""));
        
    }

    /**
     * loadStartScreen is called when the smart trolley logo is pressed. 
     * It calls the goToStartScreen method in SmartTrolleyGUI.java
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
            application.goToFavourites(lblCurrentListName.getText());
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
            application.goToShoppingList(lblCurrentListName.getText());
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
            
            ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
            
            productsTable.getItems().clear();
            filteredProducts.clear();
            if(categoryName == "All"){
                //Get all products
                preparedStatement = connect.prepareStatement("SELECT * from smarttrolly.products");     
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    Product p = new Product(resultSet.getString("Image"), 
                            resultSet.getString("Name"), 
                            resultSet.getDouble("Price"), 
                            resultSet.getInt("CategoryID"), 
                            resultSet.getBoolean("IsFavourite"),
                            resultSet.getInt("ProductID"));
                    
                    
                    filteredProducts.add(p);
                }
                
                //Now populate the products table
                productsTable.getItems().addAll(filteredProducts);
                
            } else{
                //Get all products within the selected category
                preparedStatement = connect.prepareStatement("SELECT * from smarttrolly.products p " +
                        "join smarttrolly.categories c on c.CategoryID = p.CategoryID where c.Name = ?");     
                preparedStatement.setString(1, categoryName);
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    Product p = new Product(resultSet.getString("Image"), 
                            resultSet.getString("Name"), 
                            resultSet.getDouble("Price"), 
                            resultSet.getInt("CategoryID"), 
                            resultSet.getBoolean("IsFavourite"),
                            resultSet.getInt("ProductID"));
                    
                    
                    filteredProducts.add(p);
                }
                
                //Now populate the products table
                productsTable.getItems().addAll(filteredProducts);
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

    private void SetUpColumns() {
        // set up column cell value factories
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productPrice"));
        
        imageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
           @Override
           public ObservableValue<Product> call(TableColumn.CellDataFeatures<Product, Product> features) {
               return new ReadOnlyObjectWrapper<Product>(features.getValue());
           }
        });
        
        addColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
            @Override
            public ObservableValue<Product> call(TableColumn.CellDataFeatures<Product, Product> features) {
                return new ReadOnlyObjectWrapper<Product>(features.getValue());
            }
        });
         
        imageColumn.setCellFactory(new Callback<TableColumn<Product, Product>, TableCell<Product, Product>>() {
           @Override
           public TableCell<Product, Product> call(TableColumn<Product, Product> imageColumn) {
               return new TableCell<Product, Product>() {
                   final Button button = new Button();

                   @Override
                   public void updateItem(final Product product, boolean empty) {
                       //super.updateItem(product, empty);
                       if (product != null) {
                           InputStream st = getClass().getResourceAsStream(product.getImageURL());
                           
                           Image productImage = new Image(st);
                           button.setGraphic(new ImageView(productImage));
                           button.setPrefSize(80, 60);
                           button.setMinSize(80, 60);
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
                                    System.out.println("Pressed add button for product (saved to the DB): " + product.getProductName());
                                    
                                    Boolean productFound = false;
                                    String insertSql = "INSERT INTO lists_products VALUES (" + 
                                                        product.getID().toString() + "," + application.session.get("currentListID").toString() + ",1)";
                                    String updateSql = "UPDATE lists_products SET Quantity = Quantity + 1 WHERE ListID = ? AND ProductID = ?";
                                    String selectSql = "SELECT ProductID FROM lists_products WHERE ListID = ? AND ProductID = ?";
                                    Integer listID = (Integer)application.session.get("currentListID");
                                    
                                    try {
                                        connect = DriverManager.getConnection("jdbc:mysql://localhost/smarttrolly?", "root","");
                                        
                                        //If the product does not exist then add one to lists-products table with a quantity of one
                                        preparedStatement = connect.prepareStatement(selectSql);
                                        preparedStatement.setInt(1, listID);
                                        preparedStatement.setInt(2, product.getID());
                                        
                                        resultSet = preparedStatement.executeQuery();
                                        
                                        while(resultSet.next()){
                                            productFound = true;
                                        }
                                        
                                        if(productFound == false){
                                            preparedStatement = connect.prepareStatement(insertSql);
                                            preparedStatement.execute();
                                        }else {
                                            //If product exists then add 1 to the quantity
                                            preparedStatement = connect.prepareStatement(updateSql);
                                            preparedStatement.setInt(1, listID);
                                            preparedStatement.setInt(2, product.getID());
                                             
                                            preparedStatement.execute();
                                        }
                                        
                                        ObservableList<Double> data = SetTotals();
                                        lblTotal.setText("Total: ��" + data.get(0).floatValue());
                                        lblTotalItems.setText("Total Items: " + data.get(1).toString().replace(".0", ""));
                                        
                                    } catch (SQLException ex) {
                                        Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    finally{
                                        try {
                                            resultSet.close(); 
                                            connect.close();
                                        } catch (SQLException ex) {
                                            Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
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
}

/**
 * ************End of HomeScreenController*************
 */
