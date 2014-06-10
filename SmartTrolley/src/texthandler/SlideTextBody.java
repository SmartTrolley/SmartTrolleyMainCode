package texthandler;

import graphicshandler.Branchable;
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

public class SlideTextBody extends Text implements Comparable<SlideTextBody>, Branchable {

	protected String fontFamily;
	protected double fontSize;
	private FontPosture italic;
	private FontWeight bold;
	private int number;
	private int branch;


	/**
	 * DESCRIPTION OF CONSTRUCTOR
	 *<p> Date Modified: 4 Jun 2014
	 */
	public SlideTextBody(String textString, Boolean bold, Boolean italic, Boolean underlined, int branch) {
		setText(textString);
		setBold(bold);
		setItalic(italic);
		setUnderline(underlined);
		setBranch(branch);
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
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	*Sets the Font Posture according to an input boolean
	*
	*<p>Tests: PostureTest, PostureBoolTest
	*@param italic
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
	*Sets up the font
	*<p> Date Modified: 23 May 2014
	*/
	private void setupFont() {
		
		setFont(Font.font(fontFamily, bold, italic, fontSize));
		
	}

	/**
	*Sets up the Bold value
	*@return
	*<p> Date Modified: 23 May 2014
	*/
	public boolean isBold() {
		
		return getFont().getStyle().contains("Bold");
	
	}
	
	/**
	* returns whether the text body is italic
	*@return boolean
	*<p> Date Modified: 10 Jun 2014
	*/
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
	*Sets the font family
	*@param font
	*<p> Date Modified: 25 May 2014
	*/
	public void setFontFamily(String font) {
		this.fontFamily = font;
		setupFont();
	}


	/**
	*Sets the font size
	*@param fontSize
	*<p> Date Modified: 25 May 2014
	*/
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;	
		setupFont();
	}

	@Override
	public int getBranch() {
		return branch;
	}


	@Override
	public void setBranch(int branch) {
		this.branch = branch;
	}
	
}
