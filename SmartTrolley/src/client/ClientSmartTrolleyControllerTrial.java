/**
 * SmartTrolley
 *
 * Client class to run the trial client
 *
 * @author Alick Jacklin
 * @author Prashant Chakravarty
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.0 [Date Created: 27 Feb 2014]
 */

package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSmartTrolleyControllerTrial {

	public static Socket serverSocket;
	String host = "127.0.0.1";
	int port = 2001;
	ObjectInputStream inputFromServer;
	private ObjectOutputStream outputToServer;
	public Object objectFromServer;
	static public String objectToServer = "This is a test string from the client.";

	/**
	 * Opens the client socket, and gets the object from server
	 * <p>
	 * Date Modified: 27 Feb 2014
	 */
	public ClientSmartTrolleyControllerTrial() {
		try {
			openSocket();
			do {
				getObjectFromSocket();
				outputObjectToServer(objectToServer);
			} while (!(objectFromServer instanceof Object));
			
			serverSocket.close();

		} catch (UnknownHostException e) {
			System.out.println("Don't know about host:" + host);
			System.exit(-1);

		} catch (IOException e) {
			System.out.println("Couldn't open I/O connection " + host + ":"
					+ port);
			System.exit(-1);

		} catch (ClassNotFoundException e) {
			System.out
					.println("Class definition not found for incoming object.");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Main method, simply creates a new instance of the client
	 * <p>
	 * Spike to connect a server to a client
	 * 
	 * @param args
	 *            <p>
	 *            Date Modified: 27 Feb 2014
	 */
	public static void main(String[] args) {
		new ClientSmartTrolleyControllerTrial();

	}

	/**
	 * Opens a client side socket to accept connection from server
	 * <p>
	 * Spike to connect a server to a client
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 *             <p>
	 *             Date Modified: 27 Feb 2014
	 */
	private void openSocket() throws UnknownHostException, IOException {
		serverSocket = new Socket(host, port);
		inputFromServer = new ObjectInputStream(serverSocket.getInputStream());
		outputToServer = new ObjectOutputStream(serverSocket.getOutputStream());
	}

	/**
	 * Gets the object which is in the socket, tests it is an instance of Object
	 * <p>
	 * Spike to connect a server to a client
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *<p>Date Modified: 27 Feb 2014
	 */
	private void getObjectFromSocket() throws IOException,
			ClassNotFoundException {

		try {
			objectFromServer = (Object) inputFromServer.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find VideoFile class.");
		}
		System.out.println("Client Recieved:" + objectFromServer);
	}

	/**
	 * Sends an object to the server
	 * <p>
	 * Spike to connect a server to a client
	 * 
	 * @param objectToServer
	 *            <p>
	 *            Date Modified: 27 Feb 2014
	 */
	public void outputObjectToServer(Object objectToServer) {
		try {

			outputToServer.writeObject(objectToServer);

		} catch (IOException e) {
			System.out.println("Could not output to server");
			System.exit(-1);
		}
		System.out.println("Client Output Stream Ready");
	}
}

/************** End of ClientSmartTrolleyControllerTrial.java **************/
