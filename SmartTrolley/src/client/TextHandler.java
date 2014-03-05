/** 

* 

* Text Handler class 

*

* @author Thomas Lea

* @author Sam Geering

*

* @author [Checked By:]

*

* @version V1.0 Date Created: 24/02/14

**/

package client;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class TextHandler {
	
	//Class wide Variables for Text settings
	Node pNode;
	int xStart = 200;
	int yStart = 200;
	int xEnd = 300;
	String txtData = "Sample text...";
	String txtFont = "Verdana";
	int txtFontSize = 25;
	String txtFontColor = "#003300";
	String txtLineColor = "#FF0000";
	int wrappingWidth = xEnd - xStart;
	boolean visible = false;
	


		public TextHandler(){
			initNode();
		}
	
		//Method to initialise the Text node with user options
		public void initNode(){
			
			Text pNode = new Text(xStart, yStart, txtData);			
			pNode.setFill(Color.web(txtFontColor));
			pNode.setFont(Font.font(txtFont, txtFontSize));			
			pNode.setWrappingWidth(wrappingWidth);
			pNode.setStroke(Color.web(txtLineColor));
			pNode.setVisible(visible);
			
		//This assigns the local pNode settings to the class wide pNode
			this.pNode = pNode;
		}
		
		//Method used to return the Text node's current settings
		public Text getPNode() {
			return (Text)pNode;
		}

}
