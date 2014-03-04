/** * SmartTrolley * * Server Class to run the server * * @author Alick Jacklin * @author Prashant Chakravarty * * @author Checked By: Checker(s) fill here * * @version V1.0 [Date Created: 27 Feb 2014] */
// TODO We will start this with a spike to make sure we can connect to a client
package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSmartTrolleyControllerTrial {
	static ServerSocket serverSocket;
	private int waitForClient = 0;
	Socket clientSocket;
	int port = 2001;
	Thread socketThread;
	String serverAddress = "127.0.0.1";
	public int maxClientsCount = 2;
	public final ClientThread[] threads = new ClientThread[maxClientsCount];
	int num_cncted_clients;

	/**
	 * * Main method, simply creates a new instance of server *
	 * <p>
	 * Spike to connect a server to a client * * @param args *
	 * <p>
	 * Date Modified: 24 Feb 2014
	 */
	public static void main(String[] args) {
		new ServerSmartTrolleyControllerTrial();
	}

	/**
	 * * Contains the threads to run/rerun the server *
	 * <p>
	 * Date Modified: 27 Feb 2014
	 */
	public ServerSmartTrolleyControllerTrial() {
		socketThread = new Thread("Socket") {
			public void run() { // TODO remove later after completed testing
				System.out.println("YAY! Dave started Socket Thread");
				try {
					openSocket();
				} catch (IOException e) {
					System.out.println("ERROR on socket connection.");
					System.exit(-1);
				} // do{
				/*
				 * try { clientSocketClose();
				 * System.out.println("Client Socket Closed");
				 * System.out.println("Rerunning server"); // serverClose(); /*
				 * * try { Thread.sleep(3000); socketThread.interrupt(); } *
				 * catch (InterruptedException e1) { e1.printStackTrace(); } * *
				 * socketThread.start(); } catch (IOException e) {
				 * System.out.println("Could not close socket on port: " +
				 * port); System.exit(-1); }
				 */}// while (true); //TODO Take a look at this condition
		};
		socketThread.start();
	}

	/**
	 * * Opens the server's sockets, and waits for connection from client *
	 * <p>
	 * Spike to connect a server to a client * *
	 * 
	 * @throws IOException
	 *             *
	 *             <p>
	 *             Date Modified: 27 Feb 2014
	 */
	private void openSocket() throws IOException {
		try {
			try {
				serverSocket = new ServerSocket(port);
			} catch (IOException e) {
				System.out.println("Could not listen. Dave deaf on port(server):" + port);
				System.exit(-1);
			}
			while(waitForClient < maxClientsCount){
				waitForClient ++;
				System.out.println("Opened socket on: " + port
					+ ", waiting for client.");
			clientSocket = serverSocket.accept();
			for (num_cncted_clients = 0; num_cncted_clients < maxClientsCount; num_cncted_clients++) {
				if (threads[num_cncted_clients] == null) {
					(threads[num_cncted_clients] = new ClientThread(
							clientSocket, threads)).start();
					System.out.println("Dave connected to client on port: "
							+ port);
					System.out.println(num_cncted_clients
							+ " clients connected.");
					break;
				}
			}
			if (num_cncted_clients == maxClientsCount) {
				PrintStream os = new PrintStream(clientSocket.getOutputStream());
				os.println("Dave too busy. Try later.");
				System.out.println("Dave too busy. Try later.");
				os.close();
				clientSocket.close();
			}
			}
		} catch (IOException e) {
			System.out.println("Could not accept client.");
			System.exit(-1);
		}
	}

	public static void serverClose() throws IOException {		
		serverSocket.close();
		System.out.println("Dave Now Closed");
	}
}
/************** End of ServerSmartTrolleyControllerTrial.java **************/
