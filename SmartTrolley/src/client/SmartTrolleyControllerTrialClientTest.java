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
	 *@Before Sets up server and client for testing
	 *@throws java.lang.Exception
	 *<p> Date Modified: 28 Feb 2014
	 */
	public void setUp() throws Exception {
		server.SmartTrolleyControllerTrialServer.main(null);
		client = new SmartTrolleyControllerTrialClient();
	}

	/**
	*@Test Tests that the correct object is received from the server
	*<p> Date Modified: 28 Feb 2014
	*/
	public void ObjectRxdFromServer() {
		assertEquals(server.SmartTrolleyControllerTrialServer.objectToClient, client.objectFromServer);
	}
	
	/**
	*@Test Tests that the client closes all sockets
	*<p> Date Modified: 28 Feb 2014
	*/
	public void clientClosesAllSockets(){
		assertTrue(SmartTrolleyControllerTrialClient.serverSocket.isClosed());
	}

}

/**************End of SmartTrolleyControllerTrialClientTest.java**************/