/** 
* OffersScreenController
* 
* Class Description: class that allows java interaction with HomeScreen.fxml file.
*
* @author Arne
* @author [Name2]
*
* @author [Checked By:] [Checker(s) fill here]
*
* @version [1.0] [Date Created: 22/02/14]
*/

package smarttrolleygui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

/**
 *
 * @author me
 */
public class OffersScreenController implements Initializable {
    
    HBox hbox1;
    private SmartTrolleyGUI application; 
    
    public void setApp(SmartTrolleyGUI application){
        this.application = application;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        
    public void loadFavourites(ActionEvent event) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("my error message: application == null");
        } else {
            application.goToFavourites();            
        }
    }
    
    public void loadShoppingList(ActionEvent event) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("my error message: application == null");
        } else {
            application.goToShoppingList();
        }
    }     
}
/**************End of OffersScreenController**************/