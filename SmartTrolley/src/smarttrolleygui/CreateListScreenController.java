/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smarttrolleygui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;



/**
 * FXML Controller class
 *
 * @author Omid
 */
public class CreateListScreenController implements Initializable {
    
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private SmartTrolleyGUI application;
    
    @FXML
    public TextField txtListName;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void setApp(SmartTrolleyGUI application){
        this.application = application; 
    }
    
    public void CreateNewList(){
        try {
            if(txtListName.getText().isEmpty()){
                return;
            }
            connect = DriverManager.getConnection("jdbc:mysql://localhost/smarttrolley?", "root","");
            String listName = txtListName.getText();
            
            //Now escape characters like ' or "
            if(listName.contains("'")){
               listName = listName.replace("'", "\\'");
            }
            
            if(listName.contains("\"")){
                listName = listName.replace("\"", "\\\"");
            }
            preparedStatement = connect.prepareStatement(String.format("INSERT INTO lists (Name) VALUES ('%s')", listName));
            
            preparedStatement.execute();
            
            application.goToAllShoppingListsScreen();
        } catch (SQLException ex) {
            Logger.getLogger(CreateListScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Back(){
        application.goToStartScreen();
    }
    
}
