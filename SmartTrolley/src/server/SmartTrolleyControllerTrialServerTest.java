/**
* SmartTrolley
*
* JUnit 4 Test Case containing SmartTrolleyControllerTrialServer test cases
*
* @author Alick Jacklin
* @author Prashant Chakravarty
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 27 Feb 2014]
*/

package server;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SmartTrolleyControllerTrialServerTest {

	private SmartTrolleyControllerTrialServer server;
	
	/**
	 *Start SmartTrolleyControllerTrialServer before tests are run 
	 *<p>Spike to connect a server to a client
	 *@throws java.lang.Exception
	 *<p> Date Modified: 27 Feb 2014
	 */
	@Before
	public void setUp() throws Exception {
		server = new SmartTrolleyControllerTrialServer();
	}

	/**
	*Tests that the correct object is received from the Client
	*<p>Spike to connect a server to a client
	*<p> Date Modified: 27 Feb 2014
	*/
	@Test
	public void testObjectRxdFromClient() {
		fail("Not yet implemented");
	}
	
	

}
/**************End of SmartTrolleyControllerTrialServer.java**************/