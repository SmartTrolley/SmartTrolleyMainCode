/**
* SmartTrolley
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 27 May 2014]
**/
package slideshowdata;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root (name = "slideshow", strict = false)
public class SlideShowData {

	@Element (name = "documentinfo")
	private DocumentInfoData  documentinfo;
	
	@Element (name = "defaults")
	private DefaultsData  defaults;
	
	@Element (name = "slide")
	private SlideData  slide;

	/**
	 * @return author
	 */
	public String getAuthor()	{
		return documentinfo.author;
	}
	
	/**
	 * @return version
	 */
	public String getVersion()	{
		return documentinfo.version;
	}
	
	/**
	 * @return title
	 */
	public String getTitle()	{
		return documentinfo.title;
	}
	
	/**
	 * @return comment
	 */
	public String getComment()	{
		return documentinfo.comment;
	}
	
	/**
	 * @return width
	 */
	public int getWidth()	{
		return documentinfo.width;
	}
	
	/**
	 * @return height
	 */
	public int getHeight()	{
		return documentinfo.height;
	}

	/**
	 * @return backgroundcolor
	 */
	public String getDefaultBackgroundColor()	{
		return defaults.backgroundcolor;
	}
	
	/**
	 * @return font
	 */
	public String getDefaultFont()	{
		return defaults.font;
	}

	/**
	 * @return fontsize
	 */
	public int getDefaultFontSize()	{
		return defaults.fontsize;
	}
	
	/**
	 * @return fontcolor
	 */
	public String getDefaultFontColor()	{
		return defaults.fontcolor;
	}
	
	/**
	 * @return linecolor
	 */
	public String getDefaultLineColor()	{
		return defaults.linecolor;
	}
	
	/**
	 * @return fillcolor
	 */
	public String getDefaultFillColor()	{
		return defaults.fillcolor;
	}
	
	/**
	 * @return id
	 */
	public int getSlideId()	{
		return slide.id;
	}
	
	/**
	 * @return duration
	 */
	public int getSlideDuration()	{
		return slide.duration;
	}
	
	/**
	 * @return id
	 */
	public Boolean getSlideLastSlide()	{
		return slide.lastSlide;
	}
	
	/**
	 * @return id
	 */
	public String getTextString()	{
		return slide.textdata.textbodydata.textstring;
	}
	
	
}

