/**
 * 
 */
package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import server.VideoFile;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

@SuppressWarnings("serial")
public class MEMEClient extends JFrame implements ActionListener {
	public List<VideoFile> videoList;
	Socket serverSocket;
	String host = "127.0.0.1";
	int port = 2000;
	ObjectInputStream inputFromServer;
	JComboBox<String> videoSelectionBox;
	Container contentPane;
	JLabel statusBox = new JLabel();
	String selectedTitle;
	String vlcLibraryPath = "N:/examples/java/Year2/SWEng/VLC/vlc-2.0.1";
	JFrame mainFrame;
	JPanel videoSelectionPanel;
	VideoFile selectedVideoFile;
	private ObjectOutputStream outputToServer;
	JFrame mediaPlayerFrame;
	GraphicsDevice screenResolution;
	EmbeddedMediaPlayerComponent mediaPlayerComponent;
	private int userRating;
	int i = 0;
	int screenHeight;
	int screenWidth;
	Thread ratingThread;
	private boolean firstVideo = true;
	EmbeddedMediaPlayer mediaPlayer;
	ModifiedControlPanel controlsPanel;
	private boolean mediaPlayerClosed;
	String[] lastPlayed;
	JComboBox lastPlayedCombo;
	JPanel statusLastPlayedPanel;
	JLabel lastPlayedLabel;

	public static void main(String[] args) {

		new MEMEClient();

	}

	public MEMEClient() {

		try {
			openSocket();
			getListFromSocket();

		} catch (UnknownHostException e) {
			System.out.println("Don't know about host:" + host);
			System.exit(-1);

		} catch (IOException e) {
			System.out.println("Couldn't open I/O connection" + host + ":"
					+ port);
			System.exit(-1);

		} catch (ClassNotFoundException e) {
			System.out
					.println("Class definition not found for incoming object.");
			e.printStackTrace();
			System.exit(-1);
		}

		getVLCLibrary();

		setupGUI();

	}

	public void getVLCLibrary() {

		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
				vlcLibraryPath);
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
	}

	private void setupGUI() {

		mainFrame = new JFrame();
		setTitle("Welcome to Media Player. Please select a video");
		setSize(650, 450);

		setVisible(true);
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		mainFrame.setPreferredSize(new Dimension(650, 450));
		contentPane.setMinimumSize(new Dimension(650, 450));

		videoSelectionPanel = new JPanel();

		lastPlayed = new String[videoList.size()];

		setupVideoSelectionPanel();

		findScreenResolution();

		setupMediaPlayerFrame();

		setupStatusBox();

		validate();
	}

	private void setupMediaPlayerFrame() {
		mediaPlayerFrame = new JFrame();

		mediaPlayerFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				mediaPlayer.stop();
				mediaPlayerClosed = true;
				statusBox.setText("");

				mediaPlayerFrame.setVisible(false);
				System.out.println("media player closed");
			}
		});

		mediaPlayerFrame.setSize(screenWidth, ((4 * screenHeight) / 5));
		mediaPlayerFrame
				.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mediaPlayerFrame.setLayout(new BorderLayout());
	}

	private void findScreenResolution() {
		screenResolution = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		screenWidth = screenResolution.getDisplayMode().getWidth();
		screenHeight = screenResolution.getDisplayMode().getHeight();
	}

	private void setupVideoSelectionPanel() {
		Icon image;
		JTextArea videoDescription;

		videoSelectionPanel.setBackground(Color.WHITE);

		add(videoSelectionPanel, BorderLayout.CENTER);
		videoSelectionPanel
				.setLayout(new GridLayout(videoList.size(), 3, 5, 5));

		for (VideoFile video : videoList) {

			switch (i) {
			case 0:
				image = new ImageIcon("MInc.jpg");
				break;
			case 1:
				image = new ImageIcon("avengers.jpg");
				break;
			case 2:
				image = new ImageIcon("prometheus.jpg");
				break;
			default:
				image = null;
				break;
			}
			i++;

			// image = getImageFromServer(video.getID());

			JLabel videoLabel = new JLabel(image);
			videoSelectionPanel.add(videoLabel);

			JButton videoButton = new JButton(video.getTitle());
			videoButton.setFont(new Font("Calibri", Font.BOLD, 18));

			videoSelectionPanel.add(videoButton);
			videoButton.addActionListener(this);

			videoDescription = new JTextArea(video.getDescription());
			videoDescription.setBackground(Color.WHITE);
			videoDescription.setLineWrap(true);
			videoDescription.setWrapStyleWord(true);
			videoDescription.setFont(new Font("Calibri", Font.PLAIN, 12));
			videoSelectionPanel.add(videoDescription);
		}
		add(new JScrollPane(videoSelectionPanel));
	}

	private void setupStatusBox() {
		statusLastPlayedPanel = new JPanel();
		lastPlayedCombo = new JComboBox();
		JPanel LastPlayedPanel = new JPanel();
		lastPlayedLabel = new JLabel("Last Played");

		statusBox.setText("");
		if (videoList instanceof List) {
			for (VideoFile video : videoList) {
				if (video.getTitle() == null) {
					statusBox.setText("No videos are available");
				}
			}
		} else {
			statusBox.setText("Invalid Video List");
		}
		statusBox.setVisible(true);
		lastPlayedCombo.setVisible(false);
		lastPlayedLabel.setVisible(false);
		
		LastPlayedPanel.add(lastPlayedLabel, BorderLayout.WEST);
		LastPlayedPanel.add(lastPlayedCombo, BorderLayout.WEST);

		statusLastPlayedPanel.add(statusBox, BorderLayout.WEST);
		statusLastPlayedPanel.add(LastPlayedPanel, BorderLayout.EAST);

		add(statusLastPlayedPanel, BorderLayout.SOUTH);

	}

	private EmbeddedMediaPlayerComponent mediaPlayerDisplay() {

		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		mediaPlayerFrame.add(mediaPlayerComponent, BorderLayout.CENTER);
		mediaPlayer = mediaPlayerComponent.getMediaPlayer();
		mediaPlayerControls(mediaPlayerComponent);

		final Color gold = new Color(207, 181, 59);
		controlsPanel.buttonColor = controlsPanel.sendRating.getBackground();

		controlsPanel.sendRating.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				controlsPanel.sendRating.setBackground(gold);
			}

			public void mouseExited(MouseEvent me) {
				controlsPanel.sendRating
						.setBackground(controlsPanel.buttonColor);
			}

			public void mousePressed(MouseEvent me) {
				userRating = controlsPanel.selectedRating;

				selectedVideoFile.setRating(userRating);

				System.out.println("Present user rating is: "
						+ selectedVideoFile.getRating());
				try {
					outputToServer.writeObject(selectedVideoFile.getRating());
					System.out.println(selectedVideoFile.getRating()
							+ " sent to server");
				} catch (IOException e) {
					System.out.println("Could not output to server");
					System.exit(-1);
				}

				controlsPanel.currentRating(selectedVideoFile.getRating());
			}
		});

		String media = "rtp://@127.0.0.1:2000";
		mediaPlayer.playMedia(media);

		return mediaPlayerComponent;
	}

	protected void processWindowEvent(WindowEvent e) {

		/*
		 * The following code was adapted from: Jeffrey M. Hunter,
		 * "ConfirmExitDialog.java", 2011
		 * http://www.idevelopment.info/data/Programming
		 * java/swing/ConfirmExitDialog.java Accessed on: 25/05/13
		 */

		if ((e.getID() == WindowEvent.WINDOW_CLOSING)) {

			int exitApp = JOptionPane.showConfirmDialog(mainFrame,
					"Do you really wish to exit the application?",
					"Please Confirm", JOptionPane.YES_NO_OPTION);

			if (exitApp == JOptionPane.YES_OPTION) {
				try {

					selectedVideoFile = null;
					outputVideoFileToServer(selectedVideoFile);
					serverSocket.close();
					System.out.println("Disposing mainFrame");

					System.exit(0);
				} catch (IOException e1) {
					System.out.println("Could not close socket on port: "
							+ port);
					System.exit(-1);
				}
			}
		}

	}

	private void mediaPlayerControls(
			EmbeddedMediaPlayerComponent mediaPlayerComponent) {
		EmbeddedMediaPlayer mediaPlayer = mediaPlayerComponent.getMediaPlayer();
		controlsPanel = new ModifiedControlPanel(mediaPlayer);
		mediaPlayerFrame.add(controlsPanel, BorderLayout.SOUTH);

	}

	// Opening the socket from the client end
	private void openSocket() throws UnknownHostException, IOException {
		serverSocket = new Socket(host, port);
		inputFromServer = new ObjectInputStream(serverSocket.getInputStream());
		outputToServer = new ObjectOutputStream(serverSocket.getOutputStream());
	}

	// Reading from server
	private void getListFromSocket() throws IOException, ClassNotFoundException {

		// Icon image;
		// ArrayList<Icon> imagesForButtons = null;
		try {
			videoList = (List<VideoFile>) inputFromServer.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find VideoFile class.");
		}

		/*
		 * for (VideoFile video : videoList) {
		 * 
		 * image = getImageFromServer(video.getID());
		 * imagesForButtons.add(image); }
		 */
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JButton) {
			JButton videoButton = (JButton) e.getSource();
			selectedTitle = (String) videoButton.getText();
		} else if (e.getSource() instanceof JComboBox) {
			JComboBox comboBox = (JComboBox) e.getSource();
			selectedTitle = (String) comboBox.getSelectedItem();
		}

		statusBox.setText("You are now watching " + selectedTitle);
		System.out.println("New title is " + selectedTitle);

		if (!firstVideo && controlsPanel.enableVideoAdjustCheckBox.isSelected()) {
			controlsPanel.enableVideoAdjustCheckBox.doClick();
		}

		mediaPlayerFrame.setTitle(selectedTitle);
		mediaPlayerFrame.setVisible(true);

		if (firstVideo) {
			firstVideo = false;
			mediaPlayerComponent = mediaPlayerDisplay();
			lastPlayedCombo.addActionListener(this);
			lastPlayedLabel.setVisible(true);
		}

		getSelectedVideoFile();

		outputVideoFileToServer(selectedVideoFile);

		if (mediaPlayerClosed) {
			mediaPlayer.start();
			mediaPlayerClosed = true;
		}

		if (!selectedTitle.equals(lastPlayedCombo.getItemAt(0))){
		lastPlayedCombo.addItem(selectedTitle);
		}
		lastPlayedCombo.setVisible(true);
		controlsPanel.currentRatingLabel.setText("Current Rating is: " + selectedVideoFile.getRating());

	}

	private void getSelectedVideoFile() {
		for (VideoFile video : videoList) {
			if (selectedTitle.equals(video.getTitle())) {
				selectedVideoFile = video;
				break;
			}
		}
	}

	private void outputVideoFileToServer(VideoFile selectedVideoFile) {
		try {

			outputToServer.writeObject(selectedVideoFile);

		} catch (IOException e) {
			System.out.println("Could not output to server");
			System.exit(-1);
		}
		System.out.println("Client Output Stream Ready");
	}

	private Icon getImageFromServer(String id) {

		Icon imageFromServer = null;

		try {

			outputToServer.writeObject(id);

		} catch (IOException e) {
			System.out.println("Could not output ID for image to server");
			System.exit(-1);
		}
		System.out.println("Client ID Output Stream for image Ready ");

		while (imageFromServer == null) {
			try {
				imageFromServer = (Icon) inputFromServer.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("Could not find Icon class.");
			} catch (IOException e) {
				System.out
						.println("I/O exception when reading input image from server");
			}
		}
		return imageFromServer;
	}

}
