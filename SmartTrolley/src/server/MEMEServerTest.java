package server;

import java.util.List;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MEMEServerTest {
	private MEMEServer server;
	
	@Before
	public void setUp() throws Exception {
		server = new MEMEServer();
	}
	
	//Test 5 - Check if the list object holding all the data is present in the main server app
	@Test
	public void presenceOfVideoList() {
		assertTrue(server.videoList instanceof List);
	}

}
