/**
 * OffersScreenController
 * 
* Class Description: OffersScreenController allows java interaction with
 * OffersScreen.fxml
 * 
* @author Arne
 * 
* @author [Checked By:] [Checker(s) fill here]
 * 
* @version [1.0] [Date Created: 07/03/14]
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

import Printing.SmartTrolleyPrint;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class OffersScreenController implements Initializable {

    private SmartTrolleyGUI application;
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * initialize is automatically called when the controller is created.
     * <p>
     * Date Modified: 07 Mar 2014
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO remove if left unused
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
            SmartTrolleyPrint.smartTrolleyPrint("error: application == null");
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
            SmartTrolleyPrint.smartTrolleyPrint("error: application == null");
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
                        .getConnection("jdbc:mysql://localhost/smarttrolley?", "root","");

                preparedStatement = connect.prepareStatement("SELECT ListID, Name from smarttrolley.lists where ListID = ?");
                preparedStatement.setInt(1, (Integer)application.session.get("currentListID"));
                
                resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    listName = "Current List Name: " + resultSet.getString("Name");
                }

            } catch (SQLException ex) {
                SmartTrolleyPrint.smartTrolleyPrint("SQLException: " + ex.getMessage());
                SmartTrolleyPrint.smartTrolleyPrint("SQLState: " + ex.getSQLState());
                SmartTrolleyPrint.smartTrolleyPrint("VendorError: " + ex.getErrorCode());
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
            
            application.goToProductsScreen(listName);
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
            SmartTrolleyPrint.smartTrolleyPrint("error: application == null");
        } else {
           // application.goToFavourites();
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
            SmartTrolleyPrint.smartTrolleyPrint("error: application == null");
        } else {
            //application.goToShoppingList();
        }
    }
}
/**************End of OffersScreenController**************/
