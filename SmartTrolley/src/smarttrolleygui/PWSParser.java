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

import java.util.List;

import javafx.stage.FileChooser;

import org.xml.sax.helpers.DefaultHandler;

import Printing.SmartTrolleyPrint;

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

	//Refer to slide child elements using xyz = Slide.SlideChildElements. i.e. currentElement = Slide.SlideChildElements.TEXT;
	
	public static final String slideShowPath = "../../XMLDocs/trialPWS.xml";
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

		SmartTrolleyPrint.print("In PWS Parser");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open PWS File");

		// TODO Now only display XML files and perhaps set a default location to show
		// TODO Also pass the file into the XML Parser
		fileChooser.showOpenDialog(SmartTrolleyGUI.stage);

		readXMLFile(slideShowPath);
		writeSlideShow();
	}

	/**
	 * Method/Test Description
	 * <p>
	 * User can load PWS compatible XML File into program
	 * <p>
	 * Date Modified: 24 May 2014
	 */
	private void writeSlideShow() {
		// TODO Auto-generated method stub

	}

	/**
	 * Method/Test Description
	 * <p>
	 * User can load PWS compatible XML File into program
	 * 
	 * @param slideShowPath2
	 *            <p>
	 *            Date Modified: 24 May 2014
	 */
	private void readXMLFile(String slideShowPath2) {
		// TODO Auto-generated method stub

	}

	/**
	 * Method/Test Description
	 * <p>
	 * User can load PWS compatible XML File into program
	 * <p>
	 * Date Modified: 24 May 2014
	 */
	public void parsePWS() {

		/*
		 * if (elementName.equals("slideshow")) { if (slideShow == null) {
		 * slideShow = new SlideShow();
		 * 
		 * } } else if (elementName.equals("documentinfo")){ //TODO Complete this
		 * this } else if (elementName.equals("defaults")){ //TODO Complete this
		 * } else if (elementName.equals("slide")){ currentSlide = new
		 * Slide(attributes.getValue(0), attributes.getValue(1),
		 * attributes.getValue(2)); } //TODO Add else if statements for attributes of slide sub-child elements
		 */
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
		// TODO Auto-generated method stub
		return slideShow.getSlides();
	}

}

/************** End of PWSParser.java **************/
