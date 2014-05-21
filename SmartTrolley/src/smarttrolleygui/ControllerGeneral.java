/**
 * ControllerGeneral
 * 
 * Class Description: This class will hold methods that are used by multiple controller classes.
 * 
 * @author Sam
 * 
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.0] [Date Created: 15/05/14]
 *
 */


package smarttrolleygui;

import javafx.event.ActionEvent;

public class ControllerGeneral {
	
    public void loadStartScreen(ActionEvent event, SmartTrolleyGUI application) {
        
		if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null1");
        } else {
            application.goToStartScreen();
        }
    }
    
    public void loadFavourites(ActionEvent event, SmartTrolleyGUI application) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null2");
        } else {
            application.goToFavourites();
        }
    }

    public void loadShoppingList(ActionEvent event, SmartTrolleyGUI application) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null3");
        } else {
            application.goToShoppingList();
        }
    }
    
    public void loadOffers(ActionEvent event, SmartTrolleyGUI application) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null4");
        } else {
            application.goToOffers();
        }
    }

	public void loadHomeScreen(ActionEvent event, SmartTrolleyGUI application) {

		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			System.out.println("error: application == null5");
		} else {
			application.goToHomeScreen();
		}
	}
}
/************** End of ControllerGeneral **************/