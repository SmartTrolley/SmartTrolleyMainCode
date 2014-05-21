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

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class ControllerGeneral {

	/**
	 * loadStartScreen is called when the smart trolley logo is pressed. It
	 * calls the goToStartScreen method in SmartTrolleyGUI.java
	 * 
	 * @param event
	 *            - response to click on smart trolley logo in navigation bar
	 *            <p>
	 *            Date Modified: 16 May 2014
	 */
	protected void loadStartScreen(ActionEvent event, SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			System.out.println("error: application == null1");
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
	 * @param event
	 *            - response to click on 'home' button
	 *            <p>
	 *            Date Modified: 16 May 2014
	 */
	protected void loadHomeScreen(ActionEvent event, SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			System.out.println("error: application == null5");
		} else {
			application.goToHomeScreen();
		}
	}
	
    /**
     * loadShoppingList is called when the 'list' button is pressed. It calls
     * the goToShoppingList method in SmartTrolleyGUI.java
     * <p>
     * User can view shopping list
     *
     * @param event - response to click on 'list' button
     * <p>
     * Date Modified: 16 May 2014
     */
	protected void loadShoppingList(ActionEvent event,
			SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			System.out.println("error: application == null3");
		} else {
			application.goToShoppingList();
		}
	}
	
	/**
	 * loadOffers is called when the 'offers' button is pressed. It calls the
	 * goToOffers method in SmartTrolleyGUI.java
	 * <p>
	 * User can browse store's offers
	 * 
	 * @param event
	 *            - response to click on 'offers' button
	 *            <p>
	 *            Date Modified: 16 May 2014
	 */
	protected void loadOffers(ActionEvent event, SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			System.out.println("error: application == null4");
		} else {
			application.goToOffers();
		}
	}
	
	/**
	 * loadFavourites is called when the 'favourites' button is pressed. It
	 * calls the goToFavourites method in SmartTrolleyGUI.java
	 * <p>
	 * User can maintain list of favourite products
	 * 
	 * @param event
	 *            - response to click on 'favourites' button
	 *            <p>
	 *            Date Modified: 16 May 2014
	 */
	protected void loadFavourites(ActionEvent event, SmartTrolleyGUI application) {
		if (application == null) {
			// We are running in isolated FXML
			System.out.println("error: application == null2");
		} else {
			application.goToFavourites();
		}
	}

	/**
	 * setUpCellValueFactory generates the cell value factories for interactive table cells,
	 * i.e. cells with buttons, checkboxes, etc.
	 * @param tableColumn - column whose cells the cellValueFactory will be applied to
	 * Date Modified: 21 May 2014
	 */
	protected void setUpCellValueFactory(TableColumn<Product, Product> tableColumn) {
		tableColumn.setCellValueFactory(new Callback<CellDataFeatures<Product, Product>, ObservableValue<Product>>() {
			@Override
			public ObservableValue<Product> call(CellDataFeatures<Product, Product> features) {
				return new ReadOnlyObjectWrapper<Product>(features.getValue());
			}
		});
	}
	
	
}
/************** End of ControllerGeneral **************/
