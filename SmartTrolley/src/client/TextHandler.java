package client;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class TextHandler {
	
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
	
		
		public void initNode(){
			
			Text pNode = new Text(xStart, yStart, txtData);			
			pNode.setFill(Color.web(txtFontColor));
			pNode.setFont(Font.font(txtFont, txtFontSize));			
			pNode.setWrappingWidth(wrappingWidth);
			pNode.setStroke(Color.web(txtLineColor));
			pNode.setVisible(visible);
			
			this.pNode = pNode;
		}
		
		
		public Text getPNode() {
			return (Text)pNode;
		}

}
