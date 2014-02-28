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
	 *@throws java.lang.Exception
	 *<p> Date Modified: 28 Feb 2014
	 */
	@Before
	public void setUp() throws Exception {
		server = new SmartTrolleyControllerTrialServer();
		client.SmartTrolleyControllerTrialClient.main(null);
	}

	/**
	*@Test Tests that the correct object is received from the Client
	*<p> Date Modified: 28 Feb 2014
	*/
	public void testObjectRxdFromClient() {
		assertEquals(client.SmartTrolleyControllerTrialClient.objectToServer, server.objectFromClient);
	}
	
	/**
	*@Test Tests that the server remains open
	*<p> Date Modified: 28 Feb 2014
	*/
	public void clientClosesServerOpen() {
		assertTrue(!(server.clientSocket.isClosed()));
		//assertTrue(!(server.serverSocket.isClosed()));
		assertTrue(client.SmartTrolleyControllerTrialClient.serverSocket.isClosed());
	}
	
//	@Test
//	public void serverClosesAllSockets(){
//		assertTrue(server.clientSocket.isClosed());
//		assertTrue(server.serverSocket.isClosed());
//	}

}
/**************End of SmartTrolleyControllerTrialServer.java**************/