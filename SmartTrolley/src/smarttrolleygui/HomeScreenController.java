/** 
* FXMLDocumentController
* 
* Class Description: class that allows java interaction with FXML file.
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author me
 */
public class HomeScreenController implements Initializable {
    
    HBox hbox1;
    
    private TextObject text1;
    @FXML
    private Label currentTotalLabel;
    @FXML
    private Label currentSavingsTotal;
    @FXML
    private Button favouritesButton;
    
    private SmartTrolleyGUI application;
    
    
    public void setApp(SmartTrolleyGUI application){
        this.application = application;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // temporary method to create a TextObject
        // this object will eventually be created by the XML reader
        tempCreateTextObject();
        
        // code to display text object
//        Text text2;
//        text2 = new Text(Double.parseDouble(text1.getXStart()), Double.parseDouble(text1.getYStart()), text1.getText());
//        text2.setFont(Font.font(text1.getFont(), FontWeight.NORMAL, Double.parseDouble(text1.getSize())));
//        text2.setFill(Color.web(text1.getColor()));
//        hbox1.getChildren().add(text2);
//        Text text3 = new Text("bla");        
//        hbox1.getChildren().add(text3);
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
    

    private void tempCreateTextObject() {        
        text1 = new TextObject();        
        text1.setText("Some text to be displayed");
        text1.setFont("Courier New");
        text1.setSize("30");
        text1.setColor("#00FF00");
        text1.setXStart("0");
        text1.setYStart("0");    
    } 
}
/**************End of FXMLDocumentController**************/