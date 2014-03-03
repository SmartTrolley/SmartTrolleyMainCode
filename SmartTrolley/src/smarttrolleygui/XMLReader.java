package smarttrolleygui;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * enum type for keeping track of when we need to store content in certain elements
 * 
 */
enum ProcessingElement {
	NONE, TEXT, ID, TITLE, FILENAME, YEAR, GENRE, DESCRIPTION
};

public class XMLReader extends DefaultHandler {
	private ProcessingElement currentElement = ProcessingElement.NONE;
	private final String inputFile = "slideShow.xml";	
        private TextObject text;
        
	public XMLReader() {
		readXMLFile(inputFile);
                System.out.println("\ntext of TextObject text is: " + text.getText());
                System.out.println("font of TextObject text is: " + text.getFont());
                System.out.println("size of TextObject text is: " + text.getSize());
                System.out.println("color of TextObject text is: " + text.getColor());
                System.out.println("xstart of TextObject text is: " + text.getXStart());
                System.out.println("xend of TextObject text is: " + text.getXEnd());
                System.out.println("ystart of TextObject text is: " + text.getYStart());
                System.out.println("yend of TextObject text is: " + text.getYEnd());
	}
	
	public TextObject getTextObject(){
		return this.text;
	}

	public void readXMLFile(String inputFile) {

		try {
			// use the default parser
			//videoList = new VideoList(inputFile);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			// parse the input
			saxParser.parse(inputFile, this);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Called by the parser when it encounters the start of the XML file.
     * @throws org.xml.sax.SAXException
	 */
        @Override
	public void startDocument() throws SAXException {
		System.out.println("\nXML Parser: starting to process document: " + inputFile);
		System.out.println("-----------------------------------------------------");
	}

	/**
	 * Called by the parser when it encounters any start element tag.
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws org.xml.sax.SAXException
	 */
        @Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// sort out element name if (no) namespace in use
		String elementName = localName;
		if ("".equals(elementName)) {
			elementName = qName;
		}
		System.out.println("\tStart of element: " + elementName);
		
		if (elementName.equals("slideshow")) {
		} 
                
                else if (elementName.equals("text")) {
                    currentElement = ProcessingElement.TEXT;
                    text = new TextObject();
                    text.setFont(attributes.getValue(0));
                    text.setSize(attributes.getValue(1));
                    text.setColor(attributes.getValue(2));
                    text.setXStart(attributes.getValue(3));
                    text.setXEnd(attributes.getValue(4));
                    text.setYStart(attributes.getValue(5));
                    text.setYEnd(attributes.getValue(6));
		} 
                
                else {			
			currentElement = ProcessingElement.NONE;
		} 

	}

	/**
	 * Called by the parser when it encounters characters in the main body of an
	 * element.
     * @param ch
     * @param start
     * @param length
     * @throws org.xml.sax.SAXException
	 */
        @Override
	public void characters(char[] ch, int start, int length) throws SAXException {		
		String elementValue = new String(ch,start,length); 
                //System.out.println("elementValue is: " + elementValue);
                //System.out.println("length is: " + length);
                
		switch (currentElement) {
		
                case TEXT:
                    text.setText(elementValue);
                    System.out.println("\tValue of element " + currentElement + ": \"" + text.getText() + "\"");
                    break;                       
		
		default:
			break;
		} 
	}

	/**
	 * Called by the parser when it encounters any end element tag.
     * @param uri
     * @param localName
     * @param qName
     * @throws org.xml.sax.SAXException
	 */
        @Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// sort out element name if (no) namespace in use
		String elementName = localName;
		if ("".equals(elementName)) {
			elementName = qName;
		}
		System.out.println("\tEnd of element: " + elementName);

		// finished adding stuff to current VideoFile, so add video to videoList
		if (elementName.equals("video")) {
			//videoList.addVideo(video);
		}
		// finished adding content
		else if (elementName.equals("text")) {
			currentElement = ProcessingElement.NONE;
		}		
	}

	/**
	 * Called by the parser when it encounters the end of the XML file.
     * @throws org.xml.sax.SAXException
	 */
        @Override
	public void endDocument() throws SAXException {
		System.out.println("XML Parser: finished processing document: " + inputFile);
		System.out.println("-----------------------------------------------------");
	}


	public static void main(String[] args) {
		DefaultHandler handler = new XMLReader();
	}

}