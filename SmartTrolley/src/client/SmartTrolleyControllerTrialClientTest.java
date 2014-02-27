/**
* SmartTrolley
*
* JUnit 4 Test Case containing tests for SmartTrolleyControllerTrialClient
*
* @author Alick Jacklin
* @author Prashant Chakravarty
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 27 Feb 2014]
*/

package client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.SmartTrolleyControllerTrialServer;

public class SmartTrolleyControllerTrialClientTest {

	private SmartTrolleyControllerTrialClient client;
	/**
	 *Method/Test Description
	 *<p>Spike to connect a server to a client
	 *@throws java.lang.Exception
	 *<p> Date Modified: 27 Feb 2014
	 */
	@Before
	public void setUp() throws Exception {
		server.SmartTrolleyControllerTrialServer.main(null);
		client = new SmartTrolleyControllerTrialClient();
	}

	/**
	*Test that the object is correctly received from the Server
	*<p>Spike to connect a server to a client
	*<p> Date Modified: 27 Feb 2014
	*/
	@Test
	public void ObjectRxdFromServer() {
		fail("Not yet implemented");
	}

}

/**************End of SmartTrolleyControllerTrialClientTest.java**************/