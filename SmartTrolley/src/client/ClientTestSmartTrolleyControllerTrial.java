/**
 * SmartTrolley
 *
 * JUnit 4 Test Case containing tests for ClientSmartTrolleyControllerTrial
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

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientTestSmartTrolleyControllerTrial {

	private ClientSmartTrolleyControllerTrial client;

	/**
	 * Sets up server and client for testing
	 * 
	 * @throws java.lang.Exception
	 *             <p>
	 *             Date Modified: 28 Feb 2014
	 */
	@Before
	public void setUp() throws Exception {
		server.ServerSmartTrolleyControllerTrial.main(null);
		client = new ClientSmartTrolleyControllerTrial();
	}

	@After
	public void tearDown() throws Exception {
		server.ServerSmartTrolleyControllerTrial.serverClose();
		ClientSmartTrolleyControllerTrial.serverSocket.close();

	}

	/**
	 * Tests that the correct object is received from the server
	 * <p>
	 * Date Modified: 28 Feb 2014
	 */
	@Test
	public void ObjectRxdFromServer() {
		assertEquals(server.ClientThread.objectToClient,
				client.objectFromServer);
	}

	/**
	 * Tests that the client closes all sockets
	 * <p>
	 * Date Modified: 28 Feb 2014
	 * 
	 * @throws IOException
	 */
	@Test
	public void clientClosesAllSockets() throws IOException {
		assertTrue(ClientSmartTrolleyControllerTrial.serverSocket.isClosed());
	}

}

/************** End of ClientTestSmartTrolleyControllerTrial.java **************/
