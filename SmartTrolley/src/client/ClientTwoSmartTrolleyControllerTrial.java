/**
 * SmartTrolley
 *
 * Client class to run the second trial client
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

public class ClientTwoSmartTrolleyControllerTrial {

	public static Socket serverSocket;
	String host = "127.0.0.1";
	int port = 2001;
	int cycle = 0;
	ObjectInputStream inputFromServer;
	private ObjectOutputStream outputToServer;
	public Object objectFromServer;
	static public String objectToServer = "This is a test string from the client.";

	/**
	 * Opens the client socket, and gets the object from server
	 * <p>
	 * Date Modified: 27 Feb 2014
	 */
	public ClientTwoSmartTrolleyControllerTrial() {
		try {
			openSocket();
			do {
				Printing.SmartTrolleyPrint.print("Good Morning Dave");
				if (cycle == 0) {
					outputObjectToServer(objectToServer);
					Printing.SmartTrolleyPrint.print("I'm sending you a gift, Dave - From Client");
					cycle++;
				}
				getObjectFromSocket();
				Printing.SmartTrolleyPrint.print("Received Object from Dave");

			} while (!(objectFromServer instanceof Object));

			inputFromServer.close();
			outputToServer.close();
			serverSocket.close();
			Printing.SmartTrolleyPrint.print("Goodnight Dave");

		} catch (UnknownHostException e) {
			Printing.SmartTrolleyPrint.print("Don't know about host:" + host);
			System.exit(-1);

		} catch (IOException e) {
			Printing.SmartTrolleyPrint.print("Couldn't open I/O connection " + host + ":" + port);
			System.exit(-1);

		} catch (ClassNotFoundException e) {
			Printing.SmartTrolleyPrint.print("Class definition not found for incoming object.");
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
		new ClientTwoSmartTrolleyControllerTrial();

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

		Printing.SmartTrolleyPrint.print("Hello Master -Client");

		serverSocket = new Socket(host, port);

		Printing.SmartTrolleyPrint.print("Created new socket. Now trying to setup ObjectInputStream -Client");

		inputFromServer = new ObjectInputStream(serverSocket.getInputStream());

		Printing.SmartTrolleyPrint.print("Setup ObjectInputStream. Now trying to setup ObjectOutputStream");

		outputToServer = new ObjectOutputStream(serverSocket.getOutputStream());
	}

	/**
	 * Gets the object which is in the socket, tests it is an instance of Object
	 * <p>
	 * Spike to connect a server to a client
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *             <p>
	 *             Date Modified: 27 Feb 2014
	 */
	private void getObjectFromSocket() throws IOException, ClassNotFoundException {

		try {
			objectFromServer = (Object) inputFromServer.readObject();
		} catch (ClassNotFoundException e) {
			Printing.SmartTrolleyPrint.print("Could not find Object class.");
		}
		Printing.SmartTrolleyPrint.print("Client Recieved:" + objectFromServer);
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
			Printing.SmartTrolleyPrint.print("Could not output to server");
			System.exit(-1);
		}
		Printing.SmartTrolleyPrint.print("Client Output Stream Ready");
	}
}

/************** End of ClientSmartTrolleyControllerTrial.java **************/
