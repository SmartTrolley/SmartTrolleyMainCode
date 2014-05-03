/** 
* CreateNewListScreenController
* 
* Class Description: 
* CreateNewListScreenController allows java interaction with CreateNewListScreen.fxml
*
* @author Arne
*
* @author [Checked By:] [Checker(s) fill here]
*
* @version [1.0] [Date Created: 03/05/14]
*/

package smarttrolleygui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class CreateNewListScreenController implements Initializable {
    
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
    *createNewList is called when the 'Create new list' button is pressed.
    *TODO: This should read the user input and create a new entry in the 'lists' table of the SQL database.
    *It should then load the HomeScreen and pass it the name of the list, so that it can be displayed.
    *<p>User can create a new shopping list
    *@param event - response to click on 'Create new list' button
    *<p> Date Modified: 3 May 2014
    */
    public void createNewList(ActionEvent event) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null");
        } else {
        	// TODO: code to be added here.
        	
        	// move to HomeScreen
            //application.goToHomeScreen();
        }
    }
}
/**************End of CreateNewListScreenController**************/