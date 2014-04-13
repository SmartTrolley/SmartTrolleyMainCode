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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartScreenController implements Initializable {
    
    private SmartTrolleyGUI application;
    
    @FXML //  fx:id="createNewListButton"
    private Button createNewListButton; // Value injected by FXMLLoader
    
    /**
    *initialize is automatically called when the controller is created.
    *<p> Date Modified: 06 Mar 2014
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//TODO remove if left unused
    	
    	
    	
    	createNewListButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                  createNewListButton.setText("I've changed!"); 
               }
           });
    }
    
    /**
    *setApp
    *@param application
    *<p> Date Modified: 28 Feb 2014
    */
    public void setApp(SmartTrolleyGUI application){ 	
        this.application = application;
        
      //Test - Adding button to container
  		Button button = new Button("Click Me");
  		application.container.getChildren().add(button);
  		button.setTooltip(new Tooltip("Hi"));  		
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
            //Need to populate the shopping lists screen from the database before displaying the screen
        	//Inverstigate adding arrays of buttons in FXML.
        	application.goToAllShoppingListsScreen();
        }
    }    
}
/**************End of StartScreenController**************/