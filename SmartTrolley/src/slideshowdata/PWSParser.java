/**
* SmartTrolley
*
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 26 May 2014]
*/
package slideshowdata;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class PWSParser {
	
	public Serializer serializer = new Persister();
	
	
	public SlideShowData read(String document){

		File source = new File(document);
		SlideShowData thingRead = null;
		try {
			thingRead = serializer.read(SlideShowData.class, source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return thingRead;
		
	}
	
}



/**************End of PWSParser.java**************/