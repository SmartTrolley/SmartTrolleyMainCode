/**
 * SmartTrolley
 *
 * Server Class to run the server
 *
 * @author Alick Jacklin
 * @author Prashant Chakravarty
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.0 [Date Created: 27 Feb 2014]
 */

//TODO We will start this with a spike to make sure we can connect to a client
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSmartTrolleyControllerTrial {

	static ServerSocket serverSocket;
	Socket clientSocket;
	int port = 2001;
	ObjectOutputStream outputToClient;
	Thread socketThread;

	String serverAddress = "127.0.0.1";
	private ObjectInputStream inputFromClient;
	private Object receivedObject;
	Object objectFromClient;
	public static Object objectToClient;
	// need a way to stop this
	private static boolean acceptMore = true;

	/**
	 * Main method, simply creates a new instance of server
	 * <p>
	 * Spike to connect a server to a client
	 * 
	 * @param args
	 *            <p>
	 *            Date Modified: 24 Feb 2014
	 */
	public static void main(String[] args) {
		new ServerSmartTrolleyControllerTrial();
	}

	/**
	 * Contains the threads to run/rerun the server
	 * <p>
	 * Date Modified: 27 Feb 2014
	 */
	public ServerSmartTrolleyControllerTrial() {

		socketThread = new Thread("Socket") {
			public void run() {
				// TODO remove later after completed testing
				System.out.println("YAY!");
				try {
					openSocket();
					writeObjectToSocket();

				} catch (IOException e) {
					System.out.println("ERROR on socket connection.");
					System.exit(-1);
				}
				// do{
				try {
					System.out.println("CRAP!!");
					getFileFromSocket();

				} catch (IOException e) {
					System.out.println("ERROR on socket connection.");
					System.exit(-1);
				} catch (ClassNotFoundException e) {
					System.out
							.println("Class definition not found for incoming object.");
					System.exit(-1);
				}

				try {
					clientSocketClose();
					System.out.println("Client Socket Closed");
					System.out.println("Rerunning server");

					// serverClose();
					/*
					 * try { Thread.sleep(3000); socketThread.interrupt(); }
					 * catch (InterruptedException e1) { e1.printStackTrace(); }
					 * 
					 * socketThread.start();
					 */
				} catch (IOException e) {
					System.out.println("Could not close socket on port: "
							+ port);
					System.exit(-1);
				}
				// } while (true); //TODO Take a look at this condition
			}
		};
		socketThread.start();
	}

	/**
	 * Opens the server's sockets, and waits for connection from client
	 * <p>
	 * Spike to connect a server to a client
	 * 
	 * @throws IOException
	 *             <p>
	 *             Date Modified: 27 Feb 2014
	 */
	private void openSocket() throws IOException {

		try {
			try {
				serverSocket = new ServerSocket(port);
			} catch (IOException e) {
				System.out.println("Could not listen on port(server):" + port);
				System.exit(-1);
			}
			System.out.println("Opened socket on: " + port
					+ ", waiting for client.");
			clientSocket = serverSocket.accept();
			System.out.println("Connected to client on port: " + port);
		} catch (IOException e) {
			System.out.println("Could not accept client.");
			System.exit(-1);
		}
		outputToClient = new ObjectOutputStream(clientSocket.getOutputStream());

		System.out.println("Output Stream Ready");

		// setup input stream at the same time
		inputFromClient = new ObjectInputStream(clientSocket.getInputStream());
	}

	/**
	 * Closes the client and server sockets on the server
	 * <p>
	 * Spike to connect a server to a client
	 * 
	 * @throws IOException
	 *             <p>
	 *             Date Modified: 27 Feb 2014
	 */
	private void clientSocketClose() throws IOException {
		clientSocket.close();
	}

	public static void serverClose() throws IOException {
		serverSocket.close();
		System.out.println("Server Now Closed");
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

		objectToClient = new String("Hi, From Server!");
		outputToClient.writeObject(objectToClient);

	}

	/**
	 * Gets the Object received (from the client) from the socket
	 * <p>
	 * Spike to connect a server to a client
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *             <p>
	 *             Date Modified: 27 Feb 2014
	 */
	private void getFileFromSocket() throws IOException, ClassNotFoundException {

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
				System.out.println("Could not find object class.");
			}
			System.out.println("Received from Client: " + objectFromClient);

		} while (!(objectFromClient instanceof Object));

		// TODO Some method of detecting client closure, do this as a test
		/*
		 * if (selectedVideoFile == null) {
		 * System.out.println("Server detects client closure."); //Now call
		 * close sockets }
		 */

	}

}

/**************End of ServerSmartTrolleyControllerTrial.java**************/

