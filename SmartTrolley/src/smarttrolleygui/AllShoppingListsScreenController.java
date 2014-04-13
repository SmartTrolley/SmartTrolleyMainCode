/** 
* AllShoppingListsScreenController
* 
* Class Description: 
* AllShoppingListsScreenController allows java interaction with AllShoppingListsScreen.fxml
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class AllShoppingListsScreenController implements Initializable {
    
    private SmartTrolleyGUI application;
    @FXML
    private GridPane grdPaneLists;
    private List<Button> lists;
    
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
    *initialize is automatically called when the controller is created.
    *<p> Date Modified: 06 Mar 2014
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            
            preparedStatement = connect.prepareStatement("SELECT ListID ,Name from smarttrolly.lists");
            resultSet = preparedStatement.executeQuery();
            
            CreateList(resultSet);
            
            //Add the buttons to the grid pane
            int listNo = 1;
            for (Button button : lists) {
                grdPaneLists.setConstraints(button, 0, listNo);
                listNo++;
            }
            
            grdPaneLists.vgapProperty().set(20);
            grdPaneLists.getChildren().addAll(lists);
            //Set row height
            for (RowConstraints rc : grdPaneLists.getRowConstraints()) {
                rc.setMaxHeight(Integer.MAX_VALUE);
                rc.setPrefHeight(500);
              
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
    
    /**
    *setApp
    *@param application
    *<p> Date Modified: 28 Feb 2014
    */
    public void setApp(SmartTrolleyGUI application){
        this.application = application;
    }
    
    /**
    *loadStartScreen is called when the 'go back' button is pressed.
    *It calls the goToStartScreen method in SmartTrolleyGUI.java
    *@param event - response to click on 'go back' button
    *<p> Date Modified: 6 Mar 2014
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
    *loadShoppingList is called when the 'list' button is pressed.
    *It calls the goToShoppingList method in SmartTrolleyGUI.java
    *<p>User can view shopping list
    *@param event - response to click on 'offers' button
    *<p> Date Modified: 6 Mar 2014
    */
    public void loadShoppingList(ActionEvent event, String listName) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null");
        } else {
            //application.goToShoppingList();
            application.goToHomeScreen("Current List Name: " + listName);
        }
    }

    private void CreateList(ResultSet resultSet) throws SQLException {
        lists = new ArrayList<Button>();
        
        while (resultSet.next()) {
        
        final String listName = resultSet.getString("Name");
        final int listID = resultSet.getInt("ListID");
     
        System.out.println("List Name: " + listName);

        Button newButton = new Button();
        newButton.setText(listName);
        newButton.setPrefSize(300, 80);
        newButton.setMinHeight(50);
        //Load shoppingLists if clicked
        newButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                application.session.put("currentListID", listID);
                loadShoppingList(event, listName);
            }
        });
        
        lists.add(newButton);
      }
      System.out.println("Total Number of Lists: " + lists.size());
      
      //Add the Go Back button
      Button newButton = new Button();
      newButton.setMaxHeight(Integer.MAX_VALUE);
      newButton.setMinHeight(100);
      newButton.setText("Go Back");
      newButton.getStyleClass().add("largeButton");
      newButton.getStyleClass().add("button");
      newButton.setPrefSize(300, 80);
      newButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                loadStartScreen(event);
            }
        });
        
      lists.add(newButton);
    }
}
/**************End of AllShoppingListsScreenController**************/