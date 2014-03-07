/**
 * HomeScreenController
 * 
* Class Description: class that allows java interaction with HomeScreen.fxml
 * file.
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 *
 * @author me
 */
public class HomeScreenController implements Initializable {

    private SmartTrolleyGUI application;
    
    @FXML
    private ListView categoriesList;

    private ObservableList categories;

    public void setApp(SmartTrolleyGUI application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fill list on the LHS of the screen with different categories
        categories = initializeCategories();
        categoriesList.setItems(categories);
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

    public void loadOffers(ActionEvent event) {

        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("my error message: application == null");
        } else {
            application.goToOffers();
        }
    }

    private ObservableList initializeCategories() {
        categories = FXCollections.observableArrayList(
                "All",
                "Bakery",
                "Fruit & Vegetables",
                "Dairy & Eggs",
                "Meat & Seafood",
                "Frozen",
                "Drinks",
                "Snacks & Sweets",
                "Desserts"
        );

        return categories;
    }
}
/**************End of HomeScreenController**************/
