/** 
* SmartTrolleyGUI
* 
* Class Description: Main GUI class that launches application window.
*
* @author Arne
* @author [Name2]
*
* @author [Checked By:] [Checker(s) fill here]
*
* @version [1.0] [Date Created: 22/02/14]
*/

package smarttrolleygui;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SmartTrolleyGUI extends Application {

    public Stage stage; 
    private final double MIN_WINDOW_WIDTH = 200.0;
    private final double MIN_WINDOW_HEIGHT = 200.0;

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("Smart Trolley");
            stage.getIcons().add(new Image("smarttrolleygui/img/windowIcon.jpg"));
            stage.setMinWidth(MIN_WINDOW_WIDTH);
            stage.setMinHeight(MIN_WINDOW_HEIGHT);
            goToStartScreen();
//            goToHomeScreen();
//            goToFavourites();
//            goToShoppingList();
//            goToNewOffers();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goToStartScreen() {
        try {
            StartScreenController startScreen = (StartScreenController) replaceSceneContent("fxml/StartScreen.fxml");
            startScreen.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goToAllShoppingListsScreen() {
        try {
            AllShoppingListsScreenController allShoppingLists = (AllShoppingListsScreenController) replaceSceneContent("fxml/AllShoppingListsScreen.fxml");
            allShoppingLists.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goToHomeScreen() {
        try {
            HomeScreenController homeScreen = (HomeScreenController) replaceSceneContent("fxml/HomeScreen.fxml");
            homeScreen.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goToFavourites() {
        try {
            FavouritesController favourites = (FavouritesController) replaceSceneContent("fxml/Favourites.fxml");
            favourites.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goToShoppingList() {
        try {
            ExampleShoppingListController exampleShoppingList = (ExampleShoppingListController) replaceSceneContent("fxml/ExampleShoppingList.fxml");
            exampleShoppingList.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goToOffers() {
        try {
            OffersScreenController offers = (OffersScreenController) replaceSceneContent("fxml/OffersScreen.fxml");
            offers.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(SmartTrolleyGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Initializable replaceSceneContent(String fxml) throws Exception {
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
        // this line alone does not work
        // Scene scene = new Scene(container, stage.getWidth(), stage.getHeight());
        
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) fxmlLoader.getController();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(SmartTrolleyGUI.class, (java.lang.String[])null);
    }
}
/**************End of SmartTrolleyGUI**************/