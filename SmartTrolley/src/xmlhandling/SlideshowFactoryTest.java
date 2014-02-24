/**
* SmartTrolley
*
* @file	SlideshowTest.java
*
* @brief A DESCRIPTION OF THE FILE
*
*@author Name1
*@author Name2
*
*@author Checked By: Checker(s) fill here
*
*@version version of this file [date created: 24 Feb 2014]
*/


/**************End of SlideshowTest.java**************/
package xmlhandling;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Alasdair Munday
 *
 */

public class SlideshowFactoryTest {
	private SlideshowFactory testSlideshowFactory;
	private Slideshow pwsTestSlideshow;
	
	@Before
	public void setUp()	{
		// create a new slideshow factory
		testSlideshowFactory = new SlideshowFactory();
		
		

	}

	@Test
	public void pwsAcceptanceTest() {
		
//		Read in the project wide specification style test slideshow XML document
		try {
			pwsTestSlideshow = testSlideshowFactory.read("XMLDocs/MMPE_Schema_v0.4.xml");
		} catch (Exception e) {
			System.out.println("Xml Read Error");
			e.printStackTrace();
		}
		
//		check that the created class is a slide show
		assertTrue(pwsTestSlideshow instanceof Slideshow);  
		
		
		
	}
	
	@Test
	public void smartTrolleyFileAcceptanceTest(){
		
	}
}
