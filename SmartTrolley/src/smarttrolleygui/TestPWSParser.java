/**
* SmartTrolley
*
* Tests PWSParser.java with the user story: User can load PWS compatible XML File into program
*
* @author Prashant Chakravarty
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 24 May 2014]
*/

package smarttrolleygui;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TestPWSParser {
	
	private PWSParser parser;
	private List<Slide> slideShow;

	/**
	 * This method runs before every test. Here it ??
	 *@throws java.lang.Exception
	 *<p> Date Modified: 24 May 2014
	 */
	@Before
	public void setUp() throws Exception {
	   
        parser = new PWSParser();
       
        slideShow = parser.getList(PWSParser.slideShowPath); 
	}

	
	/**
	*Tests if the slideshow is a list
	*<p>User can load PWS compatible XML File into program
	*@throws Exception
	*<p> Date Modified: 24 May 2014
	*/
	@Test
	public void slideShowIsList() throws Exception {
		assertTrue(slideShow instanceof List);
	}
}


/**************End of TestPWSParser.java**************/