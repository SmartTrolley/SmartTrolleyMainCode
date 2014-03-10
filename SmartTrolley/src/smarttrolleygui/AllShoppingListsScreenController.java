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
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class AllShoppingListsScreenController implements Initializable {
    
    private SmartTrolleyGUI application;

    /**
    *initialize is automatically called when the controller is created.
    *<p> Date Modified: 06 Mar 2014
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//TODO remove if left unused
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
    public void loadShoppingList(ActionEvent event) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null");
        } else {
            application.goToShoppingList();
        }
    }
}
/**************End of AllShoppingListsScreenController**************/