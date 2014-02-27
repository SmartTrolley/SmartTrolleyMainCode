package client;

import static org.junit.Assert.*;

import org.junit.Test;
import javafx.scene.text.Text;

public class TextHandlerTest {

	Text text;
	int xStart = 200;
	int yStart = 200;
	int xEnd = 300;
	String txtData = "Sample text...";
	String txtFont = "Verdana";
	int txtFontSize = 25;
	String txtFontColor = "#003300";
	String txtLineColor = "#FF0000";
	int wrappingWidth = xEnd - xStart;
	
	
	@Test
	public void test() {
		TextHandler t = new TextHandler();
		text = t.getPNode();
		
		assertEquals(t.xStart, xStart);
		assertEquals(t.yStart, yStart);
		assertEquals(t.xEnd, xEnd);
		assertEquals(t.txtData, txtData);
		assertEquals(t.txtFont, txtFont);
		assertEquals(t.txtFontSize, txtFontSize);
		assertEquals(t.txtFontColor, txtFontColor);
		assertEquals(t.txtLineColor, txtLineColor);
		assertEquals(t.txtData, txtData);
		assertEquals(t.wrappingWidth, wrappingWidth);
		assertFalse(t.visible);
	}

}

