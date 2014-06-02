/**
 * SmartTrolley
 *
 * This file will parse the PWS file and add it to the database
 *
 * @author Prashant Chakravarty
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.0 [Date Created: 24 May 2014]
 */

package smarttrolleygui;

import java.io.File;
import java.util.List;

import javafx.stage.FileChooser;

import org.xml.sax.helpers.DefaultHandler;

import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxType;
import smarttrolleygui.slideshow.Slide;
import smarttrolleygui.slideshow.SlideShow;
import toolBox.SmartTrolleyToolBox;

/**
 * Enumerated type of the main elements in the PWS
 */
enum MainElement {
	DOCUMENTINFO, DEFAULTS, SLIDE
};

/**
 * Enumerated type of the documentinfo child elements in the PWS
 */
enum DocInfoChildElements {
	AUTHOR, VERSION, TITLE, COMMENT, WIDTH, HEIGHT
};

/**
 * Enumerated type of the defaults child elements in the PWS
 */
enum DefaultsChildElements {
	BACKGROUNDCOLOR, FONT, FONTSIZE, FONTCOLOR, LINECOLOR, FILLCOLOR
};

public class PWSParser extends DefaultHandler {

	// Refer to slideshow child elements using xyz = Slide.SlideChildElements. i.e. currentElement = Slide.SlideChildElements.TEXT;

	public static String slideShowPath = "../../XMLDocs/trialPWS.xml";
	/* /SmartTrolley/XMLDocs/trialPWS.xml */
	private SlideShow slideShow;

	/**
	 * Constructor method that runs when an instance of this object is created
	 * <p>
	 * User can load PWS compatible XML File into program
	 * <p>
	 * Date Modified: 24 May 2014
	 */
	public PWSParser() {

		SmartTrolleyToolBox.print("In PWS Parser");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open PWS File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("(XML) Xtensible Markup Language Files", "*.xml"));
		// TODO Perhaps set a default location to show

		// TODO Also pass the file into the XML Parser

		File PWSfile = fileChooser.showOpenDialog(SmartTrolleyGUI.stage);

		if (PWSfile == null) {
			MessageBox noFileMsgBx = new MessageBox("The file you have selected does not exist.", MessageBoxType.OK_ONLY);
		} else {

			// TODO The below line can be used to load in a user selected file
			// slideShowPath = PWSfile.getAbsolutePath();
			SmartTrolleyToolBox.print(PWSfile.getAbsolutePath());
		}
	}	

	/**
	 * Method/Test Description
	 * <p>
	 * Test(s)/User Story that it satisfies
	 * 
	 * @param slideShowPath2
	 * @return [If applicable]@see [Reference URL OR Class#Method]
	 *         <p>
	 *         Date Modified: 24 May 2014
	 */
	public List<Slide> getList(String slideShowPath2) {
		return slideShow.getSlides();
	}

}

/************** End of PWSParser.java **************/
