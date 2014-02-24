/**
* SmartTrolley
*
* @file	Slideshow.java
*
* @brief A DESCRIPTION OF THE FILE
*
*@author Alasdair
*@author Matthew
*
*@author Checked By: Checker(s) fill here
*
*@version 0.1 [date created: 24 Feb 2014]
*/

package xmlhandling;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * @author alasdairmunday
 *
 */
public class SlideshowFactory {

	public Serializer xmlProcessor = new Persister();
	private Slideshow newSlideshow;

	/**
	* @brief Method/Test Description
	*
	* @brief Test(s)/User Story that it satisfies ?? TEST THIS??
	*
	* @param string
	* @throws Exception 
	*
	@exception [Repeat for all exceptions thrown]
	*
	* [If applicable]@see [Reference URL]
	*/
	public Slideshow read(String xmlFileName) throws Exception {
		
		//bind the xml document to the class structure slideshow.
		newSlideshow = xmlProcessor.read(Slideshow.class, xmlFileName);
		
		return newSlideshow;
	}
	
}
/**************End of Slideshow.java**************/