package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class MEMEServer {
	List<VideoFile> videoList;
	ServerSocket serverSocket;
	Socket clientSocket;
	int port = 2000;
	ObjectOutputStream outputToClient;
	Thread socketThread;
	Thread newVideoThread;

	String vlcLibraryPath = "N:/examples/java/Year2/SWEng/VLC/vlc-2.0.1";
	String serverAddress = "127.0.0.1";
	String media = null;
	private VideoFile selectedVideoFile;
	private ObjectInputStream inputFromClient;

	String options = formatRtpStream(serverAddress, port);
	boolean newSelectionMade = true;

	HeadlessMediaPlayer mediaPlayer;
	MediaPlayerFactory mediaPlayerFactory;
	private Object receivedObject;
	boolean firstVideo = true;

	public MEMEServer() {
		readXMLFile();

		for (VideoFile video : videoList) {
			System.out.println("Video Description is: "
					+ video.getDescription());
		}

		socketThread = new Thread("Socket") {
			public void run() {
				try {
					openSocket();

					writeListToSocket();

					newVideoThread.start();

				} catch (IOException e) {
					System.out.println("ERROR on socket connection.");
					System.exit(-1);
				} 			}
		};
		socketThread.start();
		getVLCLibrary();

		newVideoThread = new Thread("New Video Selected") {
			public void run() {
					
				//Do...While loop to play new media
				//This loop exits when a new selection is made
				do {

						try {
							getFileFromSocket();							
							
							if (firstVideo ){
								// creating one instance of mediaplayer factory
								mediaPlayerFactory = new MediaPlayerFactory(media);
								mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
								firstVideo = false;
							}

						} catch (IOException e) {
							System.out.println("ERROR on socket connection.");
							System.exit(-1);
						} catch (ClassNotFoundException e) {
							System.out
									.println("Class definition not found for incoming object.");
							System.exit(-1);
						}

						if (newSelectionMade == true) {
							newSelectionMade = false;
							setupStream();
							System.out.println("video FILENAME: " + media);
						}

						if (media == "") {
							try {
								clientServerSocketClose();
								System.out.println("Sockets Closed");
								System.out.println("Rerunning server");
								try {
									socketThread.sleep(3000);
									socketThread.interrupt();
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}

								socketThread.start();
							} catch (IOException e) {
								System.out
										.println("Could not close socket on port: "
												+ port);
								System.exit(-1);
							}
						}

					} while (newSelectionMade == false);

			}
		};

	}

	private void readXMLFile() {
		System.out.println("Building XML reader and getting list.");
		XMLReader reader = new XMLReader();
		videoList = reader.getList("videoList.xml");
		System.out.println("Finished XML reader and getting list");
	}

	/**
	 * Method for setting up stream to client
	 */
	private void setupStream() {

		mediaPlayer.playMedia(media, options, ":no-sout-rtp-sap",
				":no-sout-standardsap", ":sout-all", ":sout-keep");
		System.out.println("In setupStream");
	}

	/**
	 * @throws IOException
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
			} catch (IOException e) {
				System.out.println("Could not accept client.");
				System.exit(-1);
			}
			outputToClient = new ObjectOutputStream(
					clientSocket.getOutputStream());
			
			System.out.println("Output Stream Ready");
			
			//setup input stream at the same time
			inputFromClient = new ObjectInputStream(
					clientSocket.getInputStream());
	}

	/**
	 * @throws IOException
	 */
	private void clientServerSocketClose() throws IOException {
		clientSocket.close();
		serverSocket.close();
	}

	/**
	 * @throws IOException
	 */
	private void writeListToSocket() throws IOException {

			outputToClient.writeObject(videoList);

	}

	public static void main(String[] args) {

		new MEMEServer();

	}

	public void getVLCLibrary() {

		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
				vlcLibraryPath);
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
	}

	/**
	 * @param serverAddress
	 * @param serverPort
	 * @return RTP Stream address as string
	 */
	private String formatRtpStream(String serverAddress, int serverPort) {
		StringBuilder sb = new StringBuilder(60);
		sb.append(":sout=#rtp{dst=");
		sb.append(serverAddress);
		sb.append(",port=");
		sb.append(serverPort);
		sb.append(",mux=ts}");
		return sb.toString();
	}

	private void getFileFromSocket() throws IOException, ClassNotFoundException {

		// String imagePath = null;
		// String buttonID = null;
		// Icon image;

		/*Do...While loop to listen to server
		This loop runs until the server sends a new Object
		It then ends and changes media to be played. */
		do {
			// send images back for buttons
			/*
			 * for (int i = 0; i < videoList.size(); i++) {
			 * System.out.println("In for loop" + i + "times"); try { buttonID =
			 * (String) inputFromClient.readObject(); } catch
			 * (ClassNotFoundException e) {
			 * System.out.println("Could not find String class.");
			 * 
			 * // for each to find image for (VideoFile video : videoList) {
			 * 
			 * if (buttonID == video.getID()) { imagePath =
			 * video.getImagePath(); } }
			 * 
			 * image = new ImageIcon(imagePath);
			 * 
			 * outputToClient.writeObject(image); } }
			 */

			try {
				receivedObject = inputFromClient.readObject();
				
				if (receivedObject instanceof VideoFile){
					selectedVideoFile = (VideoFile) receivedObject;
				} else if (receivedObject instanceof String){
					int newrating = (int) receivedObject;
					System.out.println("selected rating is " + newrating);
					selectedVideoFile.setRating(newrating);
				}
				if (selectedVideoFile == null || media == null) {
					break;
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Could not find VideoFile class.");
			}


		} while (media.equals(selectedVideoFile.getFilename()));

		if (selectedVideoFile == null) {
			System.out.println("Server detects client closure.");
			newSelectionMade = false;
			media = "";
		} else {
			media = selectedVideoFile.getFilename();
			newSelectionMade = true;
			System.out.println("New media is: " + media);
		}

	}
}
