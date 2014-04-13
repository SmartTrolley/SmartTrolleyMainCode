/** 
* StartScreenController
* 
* Class Description: 
* StartScreenController allows java interaction with StartScreen.fxml
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

public class StartScreenController implements Initializable {
    
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
    *loadAllShoppingListsScreen is called when the 'view your shopping lists' button is pressed.
    *It calls the goToAllShoppingListsScreen method in SmartTrolleyGUI.java
    *<p>User can view previously created shopping lists
    *@param event - response to click on 'view your shopping lists' button
    *<p> Date Modified: 06 Mar 2014
    */
    public void loadAllShoppingListsScreen(ActionEvent event) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null");
        } else {
            application.goToAllShoppingListsScreen();
        }
    }
    
    public void loadCreateListScreen(){
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null");
        } else {
            application.goToCreateListScreen();
        }
    }
}
/**************End of StartScreenController**************/