package smarttrolleygui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxType;
import slideshowdata.DataUploader;
import slideshowdata.PWSParser;
import slideshowdata.SlideShowData;
import toolBox.SmartTrolleyToolBox;
/** 
 * StartScreenController
 * 
 * Class Description: 
 * StartScreenController allows java interaction with StartScreen.fxml
 *
 * @author Arne
 * @author V1.1 Thomas [Commenting] 
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [1.0] [Date Created: 06/03/14]
 * @version [1.1] [Date Created: 10/06/2014]
 */
public class StartScreenController extends ControllerGeneral implements Initializable {

	private SmartTrolleyGUI application;
	// createNewListButton is only made public so that it can be accessed by automated tests
	@FXML
	public static Button createNewListButton;

	@FXML
	public Button viewAllShoppingListsButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	/**
	 * Tells JavaFX that the application class is SmarttrolleyGUI
	 * 
	 * @param application
	 * <p> Date Modified: 06 Mar 2014
	 */
	public void setApp(SmartTrolleyGUI application) {
		this.application = application;
	}

	/**
	*loadCreateNewListScreen is called when the 'Create a new shopping list' button is pressed.
	*It calls the goToCreateNewListScreen method in SmartTrolleyGUI.java
	*<p> User can create a new shopping list
	*@param event - response to click on 'Create a new shopping list' button
	*<p> Date Modified: 03 May 2014
	*/
	public void loadCreateNewListScreen(ActionEvent event) {

		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			SmartTrolleyToolBox.print("error: application == null");
		} else {
			loadScreen(Screen.CREATENEWLISTSCREEN, application);
		}
	}

	/**
	*
	*parsePWS is called when the Parse a PWS XML File button is pressed
	*<p>User can load PWS compatible XML File into program
	*@param event
	*<p> Date Modified: 24 May 2014
	*/
	public void parsePWS(ActionEvent event) {
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			SmartTrolleyToolBox.print("error: application == null");
		} else {
			
			// TODO Replace with appropriate method
			PWSParser parser = new PWSParser();
			
			SmartTrolleyToolBox.print("In PWS Parser");
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open PWS File");
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("(XML) Xtensible Markup Language Files", "*.xml"));
			// TODO Perhaps set a default location to show

			// TODO Also pass the file into the XML Parser

			File file = fileChooser.showOpenDialog(SmartTrolleyGUI.getStage());

			if (file == null) {
				MessageBox noFileMsgBx = new MessageBox("The file you have selected does not exist.", MessageBoxType.OK_ONLY);
			} else {

				// TODO The below line can be used to load in a user selected file
				// slideShowPath = PWSfile.getAbsolutePath();
				SmartTrolleyToolBox.print(file.getAbsolutePath());
				SlideShowData slideShowData = parser.read(file);
				
				DataUploader dataUploader = new DataUploader();
				dataUploader.uploadXmlData(slideShowData);								
			}
			
		}
	}

	/**
	 * loadAllShoppingListsScreen is called when the 'view your shopping lists'
	 * button is pressed. It calls the goToAllShoppingListsScreen method in
	 * SmartTrolleyGUI.java
	 * <p> User can view previously created shopping lists
	 * 
	 * @param event - response to click on 'view your shopping lists' button
	 * <p> Date Modified: 06 Mar 2014
	 */
	public void loadAllShoppingListsScreen(ActionEvent event) {
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			SmartTrolleyToolBox.print("error: application == null");
		} else {
			loadScreen(Screen.ALLSHOPPINGLISTSSCREEN, application);
		}
	}

}
/************** End of StartScreenController **************/
