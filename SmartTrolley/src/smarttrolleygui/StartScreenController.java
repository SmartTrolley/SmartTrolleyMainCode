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

import Printing.SmartTrolleyPrint;

import sql.ExecuteSQLScriptClass;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class StartScreenController implements Initializable {
	
	public Boolean loadImages = true;
	
	Boolean appStart = true;
    
    private SmartTrolleyGUI application;
    
    /**
    *initialize is automatically called when the controller is created.
    *<p> Test(s)/User Story that it satisfies
    *<p> Date Modified: 06 Mar 2014
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	//TODO This runs every time the start page is loaded
    	/*if (appStart){
    	ExecuteSQLScriptClass readSQL = new ExecuteSQLScriptClass();
    	readSQL.ExecuteSQLScript();
    	appStart = false;
    	}*/
    }
    
    /**
    *setApp
    *<p> Test(s)/User Story that it satisfies
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
            SmartTrolleyPrint.print("error: application == null");
        } else {
            application.goToAllShoppingListsScreen();
        }
    }
    
    /**
    *loadCreateListScreen is called when the 'Create a new shopping list' button is pressed.
    *It calls the goToCreateListScreen method in SmartTrolleyGUI.java
    *<p>Test(s)/User Story that it satisfies
    *<p> Date Modified: 15 Mar 2014
    */
    public void loadCreateListScreen(){
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            SmartTrolleyPrint.print("error: application == null");
        } else {
            application.goToCreateListScreen();
        }
    }
    
    //TODO Implement this method + Ensure only shopping lists are accessible with this option
    /**
    *If the 'Offline Mode' checkbox is checked, this method is called.
    *It ensures that no images are loaded
    *<p>Test(s)/User Story that it satisfies
    *<p> Date Modified: 15 Apr 2014
    */
    public void loadNoImages(){
    	SmartTrolleyPrint.print("Load no Images un/checked!");
    	loadImages = false;
    }
}
/**************End of StartScreenController**************/