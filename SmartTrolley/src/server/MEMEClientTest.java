package client;

import static org.junit.Assert.*;

import java.util.List;

import javax.swing.JComboBox;

import org.junit.Before;
import org.junit.Test;

import server.VideoFile;

/**
 * @author cn583
 *
 */
public class MEMEClientTest {
	private MEMEClient client;
	
	// Send list of videos from server and verify reception by client
	@Before
	public void setUp() throws Exception {
		server.MEMEServer.main(null);
		client = new MEMEClient();
	}

	/*@Test
	public void videoFileReturnsCorrectValue() {
		VideoFile videoFile = client.videoList.get(0);
		assertEquals("20120213a2", videoFile.getID());
		assertEquals("Monsters Inc.", videoFile.getTitle());
		assertEquals("monstersinc_high.mpg", videoFile.getFilename());
		System.out.println(client.videoList.get(0));
		
	}*/
	
	//Invalid Test
	/*@Test
	public void checkSelectedVideoInList() {
		JComboBox<String> comboBox = client.videoSelectionBox;
		comboBox.setSelectedIndex(2);
		assertEquals("Prometheus", comboBox.getSelectedItem());
		assertEquals("20120102b7", client.selectedVideoFile.getID());

	}*/
	
	//Invalid Test
	/*
	@Test
	public void checkForListPresence() {
		
		VideoFile videoFile = client.videoList.get(0);
		assertNotNull(videoFile.getTitle());
		assertEquals(client.statusBox.getText(), "");
	}*/
	
	@Test
	public void checkListFormat() {
		
		assertTrue(client.videoList instanceof List);	
		assertEquals(client.statusBox.getText(), "");
}
	
	//All buttons in mainframe created in for...each loop, untestable using automatic tests
	
	
}
