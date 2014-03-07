/**
 * SmartTrolley
 *
 * JUnit 4 Test Case containing ServerSmartTrolleyControllerTrial test cases
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

import client.ClientSmartTrolleyControllerTrial;

public class ServerTestSmartTrolleyControllerTrial {

	private ServerSmartTrolleyControllerTrial server;

	/**
	 * Start ServerSmartTrolleyControllerTrial before tests are run
	 * @throws java.lang.Exception
	 * <p>Date Modified: 28 Feb 2014
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("Started next Test!");
		server = new ServerSmartTrolleyControllerTrial();
		client.ClientSmartTrolleyControllerTrial.main(null);
	}
	
	/**
	 * Shuts down all sockets that may still be running after the test has run
	 * @throws java.lang.Exception
	 * <p>Date Modified: 6 Mar 2014
	 */
	@After
	public void tearDown() throws Exception {
		ServerSmartTrolleyControllerTrial.serverClose();
		client.ClientSmartTrolleyControllerTrial.serverSocket.close();

	}

	/**
	 * Tests that the correct object is received from the Client
	 * <p>Date Modified: 28 Feb 2014
	 * @throws IOException
	 */

	@Test
	public void testObjectRxdFromClient() throws IOException {
		server.waitForClient = 1;
		assertEquals(client.ClientSmartTrolleyControllerTrial.objectToServer,
				ClientThread.objectFromClient);
		//server.clientSocket.close();
		//ServerSmartTrolleyControllerTrial.serverSocket.close();
		
		System.out.println(("Finished testObjectRxdFromClient Test!"));
	}

	/**
	 * Tests that the server remains open
	 * <p> Date Modified: 28 Feb 2014
	 * @throws IOException
	 */

	@Test
	public void clientClosesServerOpen() throws IOException {
		server.waitForClient = 1;
		assertTrue(!(ServerSmartTrolleyControllerTrial.serverSocket.isClosed()));
		assertTrue(client.ClientSmartTrolleyControllerTrial.serverSocket
				.isClosed());

		System.out.println("Finished clientClosesServerOpen Test!");
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

		assertTrue(client.ClientSmartTrolleyControllerTrial.serverSocket
				.isClosed());
		
		for (int i = 0; i < server.num_cncted_clients; i++) {
			server.threads[i].isInterrupted();
		}

		client.ClientSmartTrolleyControllerTrial.main(null);
		assertEquals(client.ClientSmartTrolleyControllerTrial.objectToServer,
				ClientThread.objectFromClient);
		ServerSmartTrolleyControllerTrial.serverClose();
		
		System.out.println("Finished clientReconnectstoServer Test!");
	}
	
	@Test
	public void twoClientsConnectToServer() {
		ClientSmartTrolleyControllerTrial client2 = new client.ClientSmartTrolleyControllerTrial();
		
		assertEquals(client.ClientSmartTrolleyControllerTrial.objectToServer,
				ClientThread.objectFromClient);
		
		assertEquals(client2.objectToServer,
				ClientThread.objectFromClient);
		
		System.out.println("Finished twoClientsConnectToServer Test!");
	}
}
/************** End of ServerSmartTrolleyControllerTrial.java **************/