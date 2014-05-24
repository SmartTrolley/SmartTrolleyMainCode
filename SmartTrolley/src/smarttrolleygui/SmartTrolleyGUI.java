/** 
* SmartTrolleyGUI
* 
* Class Description: 
* Main GUI class that launches application window.
*
* @author Arne
* 
*
* @author [Checked By:] Alick Jacklin and Prashant Chakravarty [13/05/2014]
*
* @version [1.0] [Date Created: 22/02/14]
*/

package smarttrolleygui;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


import Printing.SmartTrolleyPrint;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SmartTrolleyGUI extends Application {
    

    public static Stage stage; 
    
    private final double MIN_WINDOW_WIDTH = 600.0;
    private final double MIN_WINDOW_HEIGHT = 600.0;
    private static int currentListID = 0;
    
    StartScreenController startScreen = new StartScreenController();

	AllShoppingListsScreenController allShoppingLists;

	HomeScreenController homeScreen;
	
	ProductScreenController productScreen;

	public ExampleShoppingListController exampleShoppingList;

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("Smart Trolley");
            stage.getIcons().add(new Image("smarttrolleygui/img/windowIcon.jpg"));
            stage.setMinWidth(MIN_WINDOW_WIDTH);
            stage.setMinHeight(MIN_WINDOW_HEIGHT);
//            goToStartScreen();
            
            //for debugging purposes uncomment ONE of the following four lines and comment previous line
//            goToHomeScreen();
//            goToFavourites();
//            goToShoppingList();
//            goToNewOffers();
            goToProductScreen();
            
            primaryStage.show();
        } catch (Exception ex) {
        	//TODO Show a message box to the user here
        	SmartTrolleyPrint.print("Could not get FXML file for next scene. Application crashed ;-(");
        	Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        	System.exit(-1);
        }
    }

    /**
    *goToStartScreen is called in 'start' method and loads the application's start-up screen
    *<p>User can choose to create a new shopping list, open an existing one, or import an externally created list.
    *<p>Date Modified: 6 Mar 2014
    */
    public void goToStartScreen() {
        try {
            startScreen = (StartScreenController) replaceSceneContent("fxml/StartScreen.fxml");
            startScreen.setApp(this);         
        } catch (Exception ex) {
        	//TODO Show a message box to the user here
        	SmartTrolleyPrint.print("Could not get FXML file for next scene. Application crashed ;-(");
        	Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        	System.exit(-1);
        }
    }
    
    /**
    *goToCreateNewListScreen is called when the user chooses to create a new shopping list
    *on the start screen. It loads a screen which allows the user to enter the name for the new list.
    *TODO: add user story this method satisfies (in next line)
    *<p>
    *<p>Date Modified: 3 May 2014
    */
    public void goToCreateNewListScreen() {
        try {
        	CreateNewListScreenController createNewListScreen = (CreateNewListScreenController) replaceSceneContent("fxml/CreateNewListScreen.fxml");
            createNewListScreen.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
    *goToAllShoppingListsScreen is called when the user chooses to open a previously created shopping list
    *on the start screen. It loads the screen to show the user previously created lists.
    *<p>User can view previously created shopping lists
    *<p>Date Modified: 6 Mar 2014
    */
    public void goToAllShoppingListsScreen() {
        try {
            allShoppingLists = (AllShoppingListsScreenController) replaceSceneContent("fxml/AllShoppingListsScreen.fxml");
            allShoppingLists.setApp(this);
        } catch (Exception ex) {
        	SmartTrolleyPrint.print("Could not get FXML file for next scene. Application crashed ;-(");
        	Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        	System.exit(-1);
        }
    }
    
    /**
    *goToHomeScreen is called when the 'Home' button is pressed. It loads the screen which allows the user
    *to browse through different product categories, or alternatively search for a product directly.
    *<p>User navigates through product database
    *<p> Date Modified: 28 Feb 2014
     * @param enteredListName 
    */
    public void goToHomeScreen() {
        try {
            homeScreen = (HomeScreenController) replaceSceneContent("fxml/HomeScreen.fxml");
            homeScreen.setApp(this);
        } catch (Exception ex) {
        	SmartTrolleyPrint.print("Could not get FXML file for next scene. Application crashed ;-(");
        	Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        	System.exit(-1);
        }
    }
    
    /**
    *goToFavourites is called when the 'Favourites' button is pressed. It loads the screen which allows the user
    *to browse through his favourite products.
    *<p>User can maintain list of favourite products
    *<p> Date Modified: 28 Feb 2014
    */
    public void goToFavourites() {
        try {
            FavouritesScreenController favourites = (FavouritesScreenController) replaceSceneContent("fxml/FavouritesScreen.fxml");
            favourites.setApp(this);
        } catch (Exception ex) {
        	//TODO Show a message box to the user here
        	SmartTrolleyPrint.print("Could not get FXML file for next scene. Application crashed ;-(");
        	Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        	System.exit(-1);            
        }
    }
    
    /**
    *goToShoppingList is called when the 'List' button is pressed. It loads the screen which allows the user to
    *view the currently opened shopping list.
    *<p>User can view shopping list
    *<p> Date Modified: 6 Mar 2014
     * @param ListName 
    */
    public void goToShoppingList() {
        try {
            exampleShoppingList = (ExampleShoppingListController) replaceSceneContent("fxml/ExampleShoppingList.fxml");
            exampleShoppingList.setApp(this);
        } catch (IOException ex) {
        	//TODO Show a message box to the user here
        	SmartTrolleyPrint.print("Could not get FXML file for next scene. Application crashed ;-(");
        	Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        	System.exit(-1);
           
        }
    }
    
    /**
    *goToOffers is called when the 'Offers' button is pressed. It loads the screen which allows the user to view
    *the store's current offers.
    *<p>User can browse store's offers
    *<p> Date Modified: 7 Mar 2014
    */
    public void goToOffers() {
        try {
            OffersScreenController offers = (OffersScreenController) replaceSceneContent("fxml/OffersScreen.fxml");
            offers.setApp(this);
        } catch (Exception ex) {
        	//TODO Show a message box to the user here
        	SmartTrolleyPrint.print("Could not get FXML file for next scene. Application crashed ;-(");
        	Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        	System.exit(-1);
        }
    }
    
    /**
     *goToProductScreen is called when a product name is clicked. It loads the screen which holds the product information.
     *<p>User can view product information
     *<p> Date Modified: 22 May 2014
     */
    public void goToProductScreen() {
        try {
        	productScreen = (ProductScreenController) replaceSceneContent("fxml/ProductScreen.fxml");
            productScreen.setApp(this);
        } catch (Exception ex) {
        	SmartTrolleyPrint.print("Could not get FXML file for next scene. Application crashed ;-(");
        	Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        	System.exit(-1);
        }
	}
    
    //TODO This should throw a clearer exception(s)
    /**
    *replaceSceneContent loads in the content from the fxml file it is passed 
    *<p>Displays content on screen
    *@param fxml the name of the .fxml file to be loaded into the scene
    *@return fxml file controller
    *@throws IOException 
    *@throws Exception
    *<p> Date Modified: 28 Feb 2014
    */
    private Initializable replaceSceneContent(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        InputStream inputStream = SmartTrolleyGUI.class.getResourceAsStream(fxml);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        fxmlLoader.setLocation(SmartTrolleyGUI.class.getResource(fxml));
        VBox container;
        try {        	
            container = (VBox) fxmlLoader.load(inputStream);
        } finally {
            inputStream.close();
        }
        
        // Store the stage width and height in case the user has resized the window
        Scene scene = new Scene(container);
 
        double stageWidth = stage.getWidth();
        if (!Double.isNaN(stageWidth)) {
            stageWidth -= (stage.getWidth() - stage.getScene().getWidth());
            container.setPrefWidth(stageWidth);
        }
        
        double stageHeight = stage.getHeight();
        if (!Double.isNaN(stageHeight)) {
            stageHeight -= (stage.getHeight() - stage.getScene().getHeight());
            container.setPrefHeight(stageHeight);
        }
        
        // next line left in to show need for preceding ~10 lines        
        // Scene scene = new Scene(container, stage.getWidth(), stage.getHeight());
        // previous line alone does not work
        
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) fxmlLoader.getController();
    }

    /**
    *The main() method is ignored in correctly deployed JavaFX applications.
    *main() serves only as fallback in case the application cannot be launched
    *through deployment artifacts, e.g., in IDEs with limited FX support.
    *@param args the command line arguments
    *<p> Date Modified: 22 Feb 2014
    */
    public static void main(String[] args) {
        Application.launch(SmartTrolleyGUI.class, (java.lang.String[])null);
    }

    
	/**
	*gets ListID for use throughout all code
	*@return
	*<p> Date Modified: 9 May 2014
	*/
	public static int getcurrentListID() {		
		return currentListID;
	}

	/**
	*Sets the current list ID of the List currently being used or setup
	*@param listID
	*<p> Date Modified: 9 May 2014
	*/
	public static void setCurrentListID(int listID) {
		currentListID = listID;
		
	}

}
/**************End of SmartTrolleyGUI**************/
