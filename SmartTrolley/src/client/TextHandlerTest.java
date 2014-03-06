/** 
 * SmartTrolley
 * Text Handler JUnit Test class 
 *
 * @author Thomas Lea
 * @author Sam Geering
 *
 * @author [Checked By: Prashant Chakravarty]
 *
 * @version V1.0 Date Created: 24 Feb 2014
 **/

package client;

import static org.junit.Assert.*;
import org.junit.Test;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TextHandlerTest {

	// Variables for Font options
	int xStart = 200;
	int yStart = 200;
	int xEnd = 300;
	String txtData = "Sample text...";
	String txtFont = "Verdana";
	int txtFontSize = 25;
	String txtFontColor = "#003300";
	String txtLineColor = "#FF0000";
	int wrappingWidth = xEnd - xStart;

	/**
	 * Test checking the selected options have been passed to a Text Node
	 * <p> Moshi-Mushi User Story 1.1
	 * Date Modified: 6 Mar 2014
	 */
	@Test
	public void TextOptionsTest() {
		TextHandler t = new TextHandler();
		Text text = t.getPNode();

		assertEquals((int) text.getX(), xStart);
		assertEquals((int) text.getY(), yStart);
		assertEquals((int) text.getWrappingWidth(), wrappingWidth);
		assertEquals(text.getText(), txtData);
		assertEquals(text.getFont().getName(), txtFont);
		assertEquals((int) text.getFont().getSize(), txtFontSize);
		assertEquals(text.getFill().toString(), Color.web(txtFontColor).toString());
		assertEquals(text.getStroke().toString(), Color.web(txtLineColor).toString());
		assertFalse(text.isVisible());
	}
}

/************** End of TextHandlerTest.java **************/
