package slideshowdata;

import java.io.File;

import javafx.application.Platform;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.core.ValueRequiredException;

import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxResult;
import se.mbaeumer.fxmessagebox.MessageBoxType;
import smarttrolleygui.ControllerGeneral.Screen;
import toolBox.SmartTrolleyToolBox;
/**
* SmartTrolley
*
*class for Simple XML parser
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 26 May 2014]
*/
public class PWSParser {
	
	/**Message Box Height*/
	private final double MSG_BX_H = 100.0;

	/**Message Box Width*/
	private final double MSG_BX_W = 400.0;
	
	public Serializer serializer = new Persister();
	
	/**
	 * Constructor method that runs when an instance of this object is created
	 * <p>
	 * User can load PWS compatible XML File into program
	 * <p>
	 * Date Modified: 24 May 2014
	 */
	public PWSParser() {
		
	}	
	
	
	/**
	* parses an xml file from its filename
	*@param document
	*@return SlideShowData
	*<p> Date Modified: 10 Jun 2014
	*/
	public SlideShowData read(String document){

		File source = new File(document);
		
		return read(source);
	}
	
	/**
	* parses an xml file from its file
	*@param source
	*@return SlideShowData
	*<p> Date Modified: 10 Jun 2014
	*/
	public SlideShowData read(File source){

		SlideShowData parsedSlideShowData = null;
		try {
			parsedSlideShowData = serializer.read(SlideShowData.class, source);
		} catch (ValueRequiredException e) {
			
			MessageBox noSldShowMsgBx = new MessageBox("Invalid XML File chosen: Program Closing", MessageBoxType.OK_ONLY);

			noSldShowMsgBx.setHeight(MSG_BX_H);
			noSldShowMsgBx.setWidth(MSG_BX_W);

			noSldShowMsgBx.showAndWait();

			if (noSldShowMsgBx.getMessageBoxResult() == MessageBoxResult.OK) {
				Platform.exit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return parsedSlideShowData;
	}
	
}

/**************End of PWSParser.java**************/