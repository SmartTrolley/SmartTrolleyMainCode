package slideshowdata;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return parsedSlideShowData;
	}
	
}

/**************End of PWSParser.java**************/