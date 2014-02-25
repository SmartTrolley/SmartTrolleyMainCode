
package xmlhandling;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SlideshowFactoryTest {
	private SlideshowFactory testSlideshowFactory;
	private Slideshow pwsTestSlideshow;
	
	@Before
	public void setUp()	{
		// create a new slideshow factory
		testSlideshowFactory = new SlideshowFactory();
	}

	/**
	*Test that the xml processor can read in PWS documents
	*<p>User story: User Loads shopping list from xml document
	*<p> Date Modified: 25 Feb 2014
	*/
	@Test
	public void pwsAcceptanceTest() {
		
//		Read in the project wide specification style test slideshow XML document
		try {
			pwsTestSlideshow = testSlideshowFactory.read("XMLDocs/pwsSlideshow.xml");
		} catch (Exception e) {
			System.out.println("Xml Read Error");
			e.printStackTrace();
		}
		
//		check that the created class is a slide show
		assertTrue(pwsTestSlideshow instanceof Slideshow);  
	}
	
	/**
	*Test that the xml processor can read in smartTrolley shopping list XMLs
	*<p> User story: User Loads shopping list from xml document
	*
	*<p> Date Modified: 25 Feb 2014
	*/
	@Test
	public void smartTrolleyFileAcceptanceTest(){
		
	}
}
