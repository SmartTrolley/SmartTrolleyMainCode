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

import DatabaseConnectors.SqlConnection;
import Printing.SmartTrolleyPrint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class AllShoppingListsScreenController implements Initializable {

	public SmartTrolleyGUI application;

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
			CreateList();

			
		} catch (SQLException e) {
			SmartTrolleyPrint.print("cannot create button list");
		}		finally{
            productsDatabase.closeConnection();
        }
		 //Add the buttons to the grid pane
            int listNo = 1;
            for (Button button : buttonList) {
                grdPaneLists.setConstraints(button, 0, listNo);
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
	public void CreateList() throws SQLException {
		String query = "SELECT ListID ,Name from lists";
		SmartTrolleyPrint.print("returned lists from DB");
		ResultSet resultSet = productsDatabase.sendQuery(query);
		

		buttonList = new ArrayList<Button>();

		while (resultSet.next()) {

			final String listName = resultSet.getString("Name");
			final int listID = resultSet.getInt("ListID");

			SmartTrolleyPrint.print("List Name: " + listName);

			Button newButton = new Button();
			newButton.setId(String.valueOf(listID));
			newButton.setText(listName);
			newButton.setPrefSize(300, 80);
			newButton.setMinHeight(50);
			// Load shoppingLists if clicked
			newButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					SmartTrolleyPrint.print("ListID pressed is: " + listID);				
					SmartTrolleyGUI.setCurrentListID(listID);
					SmartTrolleyPrint.print("Application ListID: " + SmartTrolleyGUI.getcurrentListID());
					loadShoppingList(event);
				}
			});

			buttonList.add(newButton);
		}
		
		resultSet.close();
		SmartTrolleyPrint.print("Total Number of Lists: " + buttonList.size());

		// Add the Go Back button
		Button newButton = new Button();
		newButton.setMaxHeight(Integer.MAX_VALUE);
		newButton.setMinHeight(100);
		newButton.setText("Go Back");
		newButton.getStyleClass().add("largeButton");
		newButton.getStyleClass().add("button");
		newButton.setPrefSize(300, 80);
		newButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				loadStartScreen(event);
			}
		});

		buttonList.add(newButton);
		
		SmartTrolleyPrint.print("Arraylist size is: " + buttonList.size());

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
		/*
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null");
        } else {
            application.goToStartScreen();
        }
        */
    	
    	ControllerGeneral controller = new ControllerGeneral(); 
    	
    	controller.loadStartScreen(event);
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
		/*
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            System.out.println("error: application == null");
        } else {
            application.goToShoppingList();
        }
        */
    	
    	ControllerGeneral controller = new ControllerGeneral();
    	
    	controller.loadShoppingList(event);
    }
}
/************** End of AllShoppingListsScreenController **************/
