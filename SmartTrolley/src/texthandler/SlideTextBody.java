/**
* SmartTrolley
*
* A DESCRIPTION OF THE FILE
*
* @author Name1
* @author Name2
*
* @author Checked By: Checker(s) fill here
*
* @version version of this file [Date Created: 22 May 2014]
*/

/*YOUR CODE HERE*/

/**************End of SlideTextBody.java**************/
package texthandler;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/** 
 * SmartTrolley
 * 
 * The PWS TextBody class to be used by the Text element class
 *
 * @author Alasdair Munday
 *
 * @author [Checked By:] [Checker(s) fill here]
 *
 * @version [v1.0] [Date Created: 23/05/2014]
 */

public class SlideTextBody extends Text implements Comparable<SlideTextBody> {

	protected String fontFamily;
	protected double fontSize;
	private FontPosture italic;
	private FontWeight bold;
	private int number;

	/**
	 * @param one
	 * @param bold
	 * @param italic
	 * @param underlined
	 * @param textNumber 
	 */
	public SlideTextBody(String text, boolean bold, boolean italic, boolean underlined, int number) {
		setText(text);
		setBold(bold);
		setItalic(italic);
		setUnderline(underlined);
		
		this.number = number;
	
	}


	public SlideTextBody(String text, Boolean bold, Boolean italic,
			Boolean underlined) {
		setText(text);
		setBold(bold);
		setItalic(italic);
		setUnderline(underlined);
	}


	/**
	*Sets the font Weight according to an input boolean
	*<p>Test: weightTest, weightBoolTest
	*@param bold
	*
	*<p> Date Modified: 23 May 2014
	*/
	private void setBold(boolean bold) {
		
		if (bold){
			this.bold = FontWeight.BOLD;
		}
		
		else{
			this.bold = FontWeight.NORMAL;
		}
		
		setupFont();
		
	}
	
	/**
	*Sets the Font Posture according to an input boolean
	*
	*<p>Tests: PostureTest, PostureBoolTest
	*@param italic
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 23 May 2014
	*/
	private void setItalic(boolean italic) {
		
		if (italic){
			this.italic = FontPosture.ITALIC;
		}
		
		else{
			this.italic = FontPosture.REGULAR;
		}
		
		setupFont();
		
	}

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 23 May 2014
	*/
	private void setupFont() {
		
		setFont(Font.font(fontFamily, bold, italic, fontSize));
		
	}

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@return
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 23 May 2014
	*/
	public boolean isBold() {
		
		return getFont().getStyle().contains("Bold");
	
	}
	
	public boolean isItalic() {
		
		return getFont().getStyle().contains("Italic");
		
	}

	
	/**
	 * Sets the natural ordering of the class to ascending number
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * 
	 * <p> Date Modified: 23 May 2014
	 */
	@Override
	public int compareTo(SlideTextBody o) {
		return Double.compare(this.number, o.number);
	}
	
	
	@Override
	public String toString(){
		return getText();
	}


	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param font
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 25 May 2014
	*/
	public void setFontFamily(String font) {
		this.fontFamily = font;
		setupFont();
	}


	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param fontSize
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 25 May 2014
	*/
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;	
		setupFont();
	}
	
	
}
