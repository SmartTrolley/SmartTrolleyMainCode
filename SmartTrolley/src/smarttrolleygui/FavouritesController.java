/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smarttrolleygui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author me
 */
public class FavouritesController implements Initializable {
    @FXML
    private Label currentTotalLabel;
    @FXML
    private Label currentSavingsTotal;
    @FXML
    private Button favouritesButton;
    @FXML
    private ListView categoriesList;
    @FXML
    private TableView productTable;
    @FXML
    private TableColumn imageColumn;
    @FXML
    private TableColumn productNameColumn;
    @FXML
    private TableColumn priceColumn;
    
    private SmartTrolleyGUI application;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fill list on the LHS of the screen with different categories
        ObservableList categories;
        categories = FXCollections.observableArrayList("One", "Two", "Three", "Four");
        categoriesList.setItems(categories);   
        
        // Fill table on RHS with sample products
        final ObservableList<Product> productData = FXCollections.observableArrayList(
            new Product("imageURL1", "Name1", "£0.99"),
            new Product("imageURL2", "Name2", "£1.99"),
            new Product("imageURL3", "Name3", "£2.99"),
            new Product("imageURL4", "Name4", "£3.99"),
            new Product("imageURL5", "Name5", "£4.99"),
            new Product("imageURL6", "Name6", "£5.99"),
            new Product("imageURL7", "Name7", "£6.99"),
            new Product("imageURL8", "Name8", "£7.99"),
            new Product("imageURL9", "Name9", "£8.99"),
            new Product("imageURL10", "Name10", "£9.99"),
            new Product("imageURL11", "Name11", "£10.99"),
            new Product("imageURL12", "Name12", "£11.99"),
            new Product("imageURL13", "Name13", "£12.99"),
            new Product("imageURL14", "Name14", "£13.99"),
            new Product("imageURL15", "Name15", "£14.99"),
            new Product("imageURL16", "Name16", "£15.99"),
            new Product("imageURL17", "Name17", "£16.99")
        );
        
        imageColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("imageURL"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productPrice"));
        
        productTable.setItems(productData);
    }    

    void setApp(SmartTrolleyGUI application) {
        this.application = application;
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
}
