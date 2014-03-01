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
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSmartTrolleyControllerTrial {

	static ServerSocket serverSocket;
	Socket clientSocket;
	int port = 2001;

	Thread socketThread;

	String serverAddress = "127.0.0.1";	

	private static final int maxClientsCount = 10;
	private static final ClientThread[] threads = new ClientThread[maxClientsCount];

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
					

				} catch (IOException e) {
					System.out.println("ERROR on socket connection.");
					System.exit(-1);
				}
				// do{

				/*try {
					clientSocketClose();
					System.out.println("Client Socket Closed");
					System.out.println("Rerunning server");

					// serverClose();
					/*
					 * try { Thread.sleep(3000); socketThread.interrupt(); }
					 * catch (InterruptedException e1) { e1.printStackTrace(); }
					 * 
					 * socketThread.start();
					 
				} catch (IOException e) {
					System.out.println("Could not close socket on port: "
							+ port);
					System.exit(-1);
				}*/
				
				}// while (true); //TODO Take a look at this condition
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
			int i = 0;
			for (i = 0; i < maxClientsCount; i++) {
				if (threads[i] == null) {
					(threads[i] = new ClientThread(clientSocket, threads))
							.start();
					System.out.println("Connected to client on port: " + port);
					break;
				}
			}
			if (i == maxClientsCount) {
				PrintStream os = new PrintStream(clientSocket.getOutputStream());
				os.println("Server too busy. Try later.");
				System.out.println("Server too busy. Try later.");
				os.close();
				clientSocket.close();
			}

		} catch (IOException e) {
			System.out.println("Could not accept client.");
			System.exit(-1);
		}
		

		System.out.println("Output Stream Ready");

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
}
	

/************** End of ServerSmartTrolleyControllerTrial.java **************/

