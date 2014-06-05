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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;

public class AllShoppingListsScreenController extends ControllerGeneral implements Initializable {

	private SmartTrolleyGUI application;

	private SqlConnection productsDatabase;

	public GridPane grdPaneLists = new GridPane();

	@FXML
	public static Button list1Button;

	public static ArrayList<Button> buttonList;

	/**
	 * initialize is automatically called when the controller is created.
	 * <p>
	 * Date Modified: 09 May 2014
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		productsDatabase = new SqlConnection();
		productsDatabase.openConnection();

		try {
			createButtonListFromLists();

			
		} catch (SQLException e) {
			SmartTrolleyToolBox.print("cannot create button list");
		}		finally{
            productsDatabase.closeConnection();
        }
		 //Add the buttons to the grid pane
            int listNo = 1;
            for (Button button : buttonList) {
                GridPane.setConstraints(button, 0, listNo);
                listNo++;
            }
            
            grdPaneLists.vgapProperty().set(20);
            grdPaneLists.getChildren().addAll(buttonList);
            //Set row height
            for (RowConstraints rc : grdPaneLists.getRowConstraints()) {
                rc.setMaxHeight(Integer.MAX_VALUE);
                rc.setPrefHeight(500);
              
            }
		
	}

	/**
	*Adds buttons to a list of buttons
	*<p>User can view list of lists
	*@throws SQLException
	*<p> Date Modified: 9 May 2014
	*/
	public void createButtonListFromLists() throws SQLException {
		String query = "SELECT ListID ,Name from lists";
		SmartTrolleyToolBox.print("returned lists from DB");
		ResultSet resultSet = productsDatabase.sendQuery(query);
		

		buttonList = new ArrayList<Button>();

		while (resultSet.next()) {

			final String listName = resultSet.getString("Name");
			final int listID = resultSet.getInt("ListID");

			SmartTrolleyToolBox.print("List Name: " + listName);

			Button newButton = new Button();
			newButton.setId(String.valueOf(listID));
			newButton.setText(listName);
			newButton.setPrefSize(300, 80);
			newButton.setMinHeight(50);
			newButton.getStyleClass().add("buttonLarge");
			
			// Load shoppingLists if clicked
			newButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					if (listID != SmartTrolleyGUI.getcurrentListID()){
						SmartTrolleyGUI.getNewList = true;
						SmartTrolleyToolBox.print("Not same list.");
					} else {
						SmartTrolleyGUI.getNewList = false;
						SmartTrolleyToolBox.print("Same list.");
					}
					
					SmartTrolleyToolBox.print("ListID pressed is: " + listID);				
					SmartTrolleyGUI.setCurrentListID(listID);
					SmartTrolleyGUI.setCurrentListName(listName);
					SmartTrolleyToolBox.print("Application ListID: " + SmartTrolleyGUI.getcurrentListID());
					
					loadShoppingList(event);
				}
			});

			buttonList.add(newButton);
		}
		
		resultSet.close();
		SmartTrolleyToolBox.print("Total Number of Lists: " + buttonList.size());

		// Add the Go Back button
		Button newButton = new Button();
		newButton.setMaxHeight(Integer.MAX_VALUE);
		newButton.setMinHeight(100);
		newButton.setText("Go Back");
		newButton.getStyleClass().add("buttonLarge");
		newButton.setPrefSize(300, 80);
		newButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				loadStartScreen(event);
			}
		});

		buttonList.add(newButton);
		
		SmartTrolleyToolBox.print("Arraylist size is: " + buttonList.size());

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
	 * loadStartScreen is called when the 'go back' button is pressed. It calls
	 * the goToStartScreen method in SmartTrolleyGUI.java
	 * @param event
	 * - response to click on 'go back' button
	 * <p>
	 * Date Modified: 6 Mar 2014
	 */
	public void loadStartScreen(ActionEvent event) {
		loadScreen(Screen.STARTSCREEN, application);
	}

	/**
	 * loadShoppingList is called when the 'list' button is pressed. It calls
	 * the goToShoppingList method in SmartTrolleyGUI.java
	 * <p>
	 * User can view shopping list
	 * @param event
	 * - response to click on 'offers' button
	 * <p>
	 * Date Modified: 6 Mar 2014
	 */
	public void loadShoppingList(ActionEvent event) {
		loadScreen(Screen.SHOPPINGLISTSCREEN, application);
    }
}
/************** End of AllShoppingListsScreenController **************/
