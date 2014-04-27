/**
 * SmartTrolley
 *
 * JUnit 4 Test Case containing ServerSmartTrolleyController test cases
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

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import client.ClientSmartTrolleyController;
import client.ClientTwoSmartTrolleyControllerTrial;

public class ServerTestSmartTrolleyController {

	private ServerSmartTrolleyController server;

	/**
	 * Start ServerSmartTrolleyController before tests are run
	 * @throws java.lang.Exception
	 * <p>Date Modified: 28 Feb 2014
	 */
	@Before
	public void setUp() throws Exception {
		Printing.SmartTrolleyPrint.print("Started next Test!");
		server = new ServerSmartTrolleyController();
		client.ClientSmartTrolleyController.main(null);
	}
	
	/**
	 * Shuts down all sockets that may still be running after the test has run
	 * @throws java.lang.Exception
	 * <p>Date Modified: 6 Mar 2014
	 */
	@After
	public void tearDown() throws Exception {
		ServerSmartTrolleyController.serverClose();
		client.ClientSmartTrolleyController.serverSocket.close();

	}

	/**
	 * Tests that the correct object is received from the Client
	 * <p>Date Modified: 28 Feb 2014
	 * @throws IOException
	 */

	@Test
	public void testObjectRxdFromClient() throws IOException {
		//server.waitForClient = 1;
		assertEquals(client.ClientSmartTrolleyController.objectToServer,
				ClientThread.objectFromClient);
		//server.clientSocket.close();
	    //ServerSmartTrolleyController.serverSocket.close();
		
		Printing.SmartTrolleyPrint.print(("Finished testObjectRxdFromClient Test!"));
	}

	/**
	 * Tests that the server remains open
	 * <p> Date Modified: 28 Feb 2014
	 * @throws IOException
	 */

	@Test
	public void clientClosesServerOpen() throws IOException {
		//server.waitForClient = 1;
		assertTrue(!(ServerSmartTrolleyController.serverSocket.isClosed()));
		assertTrue(client.ClientSmartTrolleyController.serverSocket
				.isClosed());

		Printing.SmartTrolleyPrint.print("Finished clientClosesServerOpen Test!");
	}

	/**
	 * Tests to check if client can reconnect once it has already shutdown
	 * <p>Ability to access multiple times throughout the day 
	 * @throws IOException]
	 * <p> Date Modified: 1 Mar 2014
	 */
	@Test
	public void clientReconnectstoServer() throws IOException,
			InterruptedException {

		assertTrue(client.ClientSmartTrolleyController.serverSocket
				.isClosed());
		
		for (int i = 0; i < server.num_cncted_clients; i++) {
			server.threads[i].isInterrupted();
		}

		client.ClientSmartTrolleyController.main(null);
		assertEquals(client.ClientSmartTrolleyController.objectToServer,
				ClientThread.objectFromClient);
		ServerSmartTrolleyController.serverClose();
		
		Printing.SmartTrolleyPrint.print("Finished clientReconnectstoServer Test!");
	}
	
	@Test
	public void twoClientsConnectToServer() {
		ClientTwoSmartTrolleyControllerTrial client2 = new client.ClientTwoSmartTrolleyControllerTrial();
		Printing.SmartTrolleyPrint.print("Client 2 started, establishing connection");
		assertEquals(client.ClientSmartTrolleyController.objectToServer,
				ClientThread.objectFromClient);
		
		assertEquals(client2.objectToServer,
				ClientThread.objectFromClient);
		
		Printing.SmartTrolleyPrint.print("Finished twoClientsConnectToServer Test!");
	}
}
/************** End of ServerSmartTrolleyController.java **************/