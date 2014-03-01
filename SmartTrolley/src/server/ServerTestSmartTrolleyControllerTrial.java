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

public class ServerTestSmartTrolleyControllerTrial {

	private ServerSmartTrolleyControllerTrial server;
	
	/**
	 *Start ServerSmartTrolleyControllerTrial before tests are run 
	 *@throws java.lang.Exception
	 *<p> Date Modified: 28 Feb 2014
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("STarted next Test!");
		server = new ServerSmartTrolleyControllerTrial();
		client.ClientSmartTrolleyControllerTrial.main(null);
	}
	

	@After
	public void tearDown() throws Exception {
		ServerSmartTrolleyControllerTrial.serverClose();
		client.ClientSmartTrolleyControllerTrial.serverSocket.close();
		
		
	}

	/**
	*Tests that the correct object is received from the Client
	*<p> Date Modified: 28 Feb 2014
	 * @throws IOException 
	*/
	@Test
	public void testObjectRxdFromClient() throws IOException {
		assertEquals(client.ClientSmartTrolleyControllerTrial.objectToServer, ClientThread.objectFromClient);
		server.clientSocket.close();
		ServerSmartTrolleyControllerTrial.serverSocket.close();
		System.out.println(("Finished testObjectRxdFromClient Test!"));		
	}
	
	/**
	*Tests that the server remains open
	*<p> Date Modified: 28 Feb 2014
	 * @throws IOException 
	*/
	@Ignore
	@Test
	public void clientClosesServerOpen() throws IOException {		
		assertTrue(!(ServerSmartTrolleyControllerTrial.serverSocket.isClosed()));
		ServerSmartTrolleyControllerTrial.serverClose();
		assertTrue(server.clientSocket.isClosed());
		assertTrue(ServerSmartTrolleyControllerTrial.serverSocket.isClosed());
		//assertTrue(!(server.serverSocket.isClosed()));
		assertTrue(client.ClientSmartTrolleyControllerTrial.serverSocket.isClosed());
		
		System.out.println(("Finished clientClosesServerOpen Test!"));
	}
	

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@throws IOException
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 1 Mar 2014
	*/
	@Ignore("Not Ready Yet")
	@Test
	public void clientReconnectstoServer() throws IOException, InterruptedException {
		
		assertTrue(client.ClientSmartTrolleyControllerTrial.serverSocket.isClosed());
		client.ClientSmartTrolleyControllerTrial.main(null);
		System.out.println("starting up new client");
		assertEquals(client.ClientSmartTrolleyControllerTrial.objectToServer, ClientThread.objectFromClient);
		ServerSmartTrolleyControllerTrial.serverClose();
		System.out.println(("Finished clientReconnectstoServer Test!"));
	}
	
	/*@Ignore(“Not Ready to Run”)
	@Test
	public void serverClosesAllSockets(){
		assertTrue(server.clientSocket.isClosed());
		assertTrue(server.serverSocket.isClosed());
	}*/

}
/**************End of ServerSmartTrolleyControllerTrial.java**************/