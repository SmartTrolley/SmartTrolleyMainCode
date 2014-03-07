/**
 * SmartTrolley This class contains a thread which the server runs for each client that is connected This code is adapted from http://www.ase.md/~aursu/ClientServerThreads.html (Accessed on 1 Mar '14)
 * 
 * @author Prashant Chakravarty
 * @author Alick Jacklin
 * @author Checked By: Checker(s) fill here
 * @version V0.1 [Date Created: 1 Mar 2014]
 */

package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * The chat client thread. This client thread opens the input and the output streams for a particular client, ask the client's name, informs all the clients connected to the server about the fact that
 * a new client has joined the chat room, and as long as it receive data, echos that data back to all other clients. The thread broadcast the incoming messages to all clients and routes the private
 * message to the particular client. When a client leaves the chat room this thread informs also all the clients about that and terminates.
 */
public class ClientThread extends Thread {

	private ObjectInputStream inputFromClient;
	private Object receivedObject;
	static Object objectFromClient;

	static Socket clientSocket = null;
	private final ClientThread[] threads;
	private int maxClientsCount;

	public static Object objectToClient;
	ObjectOutputStream outputToClient;

	String name = null;

	/**
	 * Readies the clientthread, sets up server for multiple clients
	 * <p> clientReconnectstoServer()
	 * 
	 * @param clientSocket
	 * @param threads
	 *            <p>
	 *            Date Modified: 6 Mar 2014
	 */
	public ClientThread(Socket clientSocket, ClientThread[] threads) {
		ClientThread.clientSocket = clientSocket;
		this.threads = threads;
		maxClientsCount = threads.length;
	}

	public void run() {
		int maxClientsCount = this.maxClientsCount;
		ClientThread[] threads = this.threads;

		try {
			// setup input stream
			System.out.println("Hello Client and welcome to the future -ClientThread");

			outputToClient = new ObjectOutputStream(clientSocket.getOutputStream());

			System.out.println("connections made sending object -ClientThread");

			inputFromClient = new ObjectInputStream(clientSocket.getInputStream());

			System.out.println("Input stream made -ClientThread");

			System.out.println("connections made sending object -ClientThread");

			writeObjectToSocket();

			System.out.println("I sent a thing -ClientThread");

			name = (String) getFileFromSocket();

			System.out.println("I gottsa thing! :) -ClientThread: " + name);

		} catch (IOException e) {

			System.out.println("ERROR on socket connection. -ClientThread");

			System.exit(-1);

		} catch (ClassNotFoundException e) {

			System.out.println("Class definition not found for incoming object. -ClientThread");

			System.exit(-1);
		}

		synchronized (this) {
			for (int i = 0; i < maxClientsCount; i++) {
				/*
				 * if (threads[i] != null && threads[i] == this) { clientName = "@" + name; break; }
				 */
			}
		}

		/*
		 * Clean up. Set the current thread variable to null so that a new client could be accepted by the server.
		 */
		synchronized (this) {
			for (int i = 0; i < maxClientsCount; i++) {
				if (threads[i] == this) {
					threads[i] = null;
				}
			}
		}
		/*
		 * Close the output stream, close the input stream, close the socket.
		 */
		try {
			inputFromClient.close();
			outputToClient.close();
			clientSocket.close();
			System.out.println("Bye Bye from Client Thread");
		} catch (IOException e) {
			System.out.println("Could not close streams and sockets...DO NOT CROSS THE STREAMS!");
		}
	}

	/**
	 * Gets the Object received (from the client) from the socket
	 * <p>
	 * Spike to connect a server to a client
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *             <p>
	 *             Date Modified: 27 Feb 2014
	 */
	private Object getFileFromSocket() throws IOException, ClassNotFoundException {

		do {
			try {
				receivedObject = inputFromClient.readObject();

				if (receivedObject instanceof Object) {
					// TODO cast to correct type of object
					objectFromClient = (Object) receivedObject;
				}
				// TODO Use this later??
				/*
				 * if (objectFromClient == null) { break; }
				 */
			} catch (ClassNotFoundException e) {
				System.out.println("Could not find object class. -ClientThread");
			}
			System.out.println("Received from Client: " + objectFromClient + " -ClientThread");

		} while (!(objectFromClient instanceof Object));

		return objectFromClient;

		// TODO Some method of detecting client closure, do this as a test
		/*
		 * if (selectedVideoFile == null) { System.out.println("Server detects client closure."); //Now call close sockets }
		 */

	}

	/**
	 * Writes an Object to the server's socket
	 * <p>
	 * Spike to connect a server to a client
	 * 
	 * @throws IOException
	 *             <p>
	 *             Date Modified: 27 Feb 2014
	 */
	private void writeObjectToSocket() throws IOException {

		objectToClient = new String("Hi, From ClientThread!- Oh and Dave says Hi too...");
		outputToClient.writeObject(objectToClient);
		System.out.println("Ive sent it!  -ClientThread");

	}

}

/************** End of ClientThread.java **************/
