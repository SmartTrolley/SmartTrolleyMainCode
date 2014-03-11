/** 
 * SmartTrolley
 * 
 * Server Class to run the server 
 *
 * @author Alick Jacklin 
 * @author Prashant Chakravarty 
 * 
 * @author Checked By: Checker(s) fill here 
 * @version V1.0 [Date Created: 27 Feb 2014] 
 */
package server;


import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import Printing.SmartTrolleyPrint;

public class ServerSmartTrolleyControllerTrial {
	static ServerSocket serverSocket;
	public int waitForClient = 0;
	static Socket clientSocket;
	int port = 2001;
	Thread socketThread;
	String serverAddress = "127.0.0.1";
	public int maxClientsCount = 10;
	public final ClientThread[] threads = new ClientThread[maxClientsCount];
	int num_cncted_clients;

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
	 * 
	 * <p>
	 * Date Modified: 27 Feb 2014
	 */
	public ServerSmartTrolleyControllerTrial() {
		socketThread = new Thread("Socket") {
			public void run() {
				// TODO remove later after completed testing
				//Printing.SmartTrolleyPrint.smartTrolleyPrint("YAY! Dave started Socket Thread");
				Printing.SmartTrolleyPrint.smartTrolleyPrint("YAY! Dave started Socket Thread");
				try {
					openSocket();
				} catch (IOException e) {
					Printing.SmartTrolleyPrint.smartTrolleyPrint("ERROR on socket connection.");
					System.exit(-1);
				}
			}
		};
		socketThread.start();
	}

	/**
	 * Opens the server's sockets, and waits for connection from client *
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
				Printing.SmartTrolleyPrint.smartTrolleyPrint("Could not listen. Dave deaf on port(server):" + port);
				System.exit(-1);
			}
			while (true) {

				waitForClient++;

				Printing.SmartTrolleyPrint.smartTrolleyPrint("Opened socket on: " + port + ", waiting for client.");
				clientSocket = serverSocket.accept();
				
				//TODO WHAT DOES THIS DO?
				for (num_cncted_clients = 0; num_cncted_clients < maxClientsCount; ++num_cncted_clients) {
					if (threads[num_cncted_clients] == null) {
						(threads[num_cncted_clients] = new ClientThread(clientSocket, threads)).start();
						Printing.SmartTrolleyPrint.smartTrolleyPrint("Dave connected to client on port: " + port);
						Printing.SmartTrolleyPrint.smartTrolleyPrint(num_cncted_clients + " clients connected.");
						break;
					}
				}
				if (num_cncted_clients == maxClientsCount) {
					PrintStream os = new PrintStream(clientSocket.getOutputStream());
					os.println("Dave too busy. Try later.");
					Printing.SmartTrolleyPrint.smartTrolleyPrint("Dave too busy. Try later.");
					os.close();
					clientSocket.close();
				}
			}
		} catch (IOException e) {
			Printing.SmartTrolleyPrint.smartTrolleyPrint("Could not accept client.");
			System.out.flush();
		}
	}

	/**
	 * Closes the server down
	 * <p>
	 * Spike to connect a server to a client
	 * 
	 * @throws IOException
	 *             <p>
	 *             Date Modified: 27 Feb 2014
	 */
	public static void serverClose() throws IOException {
		serverSocket.close();
		Printing.SmartTrolleyPrint.smartTrolleyPrint("Dave Now Closed");
	}
}
/************** End of ServerSmartTrolleyControllerTrial.java **************/
