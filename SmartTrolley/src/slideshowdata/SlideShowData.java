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

	
/**************Start of Document Info**************/
	
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

/**************End of Document Info**************/
	
/**************Start of Defaults*****************/
	
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
	
/****************End of Defaults*****************/
	
/****************Start of Slide******************/
	
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

/***************End of Slide****************/
	
/**************Start of Text****************/
		
	/**
	 * @return branch
	 */
	public int getTextXstart()	{
		return slide.textdata.xstart;
	}
	
	/**
	 * @return ystart
	 */
	public int getTextYstart()	{
		return slide.textdata.ystart;
	}
	
	/**
	 * @return xend
	 */
	public int getTextXend()	{
		return slide.textdata.xend;
	}
	
	/**
	 * @return yend
	 */
	public int getTextYend()	{
		return slide.textdata.yend;
	}
	
	/**
	 * @return layer
	 */
	public int getTextLayer()	{
		return slide.textdata.layer;
	}
	
	/**
	 * @return duration
	 */
	public int getTextDuration()	{
		return slide.textdata.duration;
	}
	
	/**
	 * @return starttime
	 */
	public int getTextStarttime()	{
		return slide.textdata.starttime;
	}
	
	/**
	 * @return font
	 */
	public String getTextFont()	{
		return slide.textdata.font;
	}
	
	/**
	 * @return fontcolor
	 */
	public String getTextFontcolor()	{
		return slide.textdata.fontcolor;
	}
	
	/**
	 * @return fontsize
	 */
	public int getTextFontsize()	{
		return slide.textdata.fontsize;
	}
	
/***************End of Text*****************/

/**************Start of TextBody****************/
	
	/**
	 * @return branch
	 */
	public int getTextbodyBranch(int index)	{
		return slide.textdata.textbodydatalist.get(index).branch;
	}
	
	/**
	 * @return italic
	 */
	public Boolean getTextbodyItalic(int index)	{
		return slide.textdata.textbodydatalist.get(index).italic;
	}
	
	/**
	 * @return bold
	 */
	public Boolean getTextbodyBold(int index)	{
		return slide.textdata.textbodydatalist.get(index).bold;
	}
	
	/**
	 * @return underlined
	 */
	public Boolean getTextbodyUnderlined(int index)	{
		return slide.textdata.textbodydatalist.get(index).underlined;
	}
	
	/**
	 * @return textstring
	 */
	public String getTextbodyTextstring(int index)	{
		return slide.textdata.textbodydatalist.get(index).textstring;
	}
		
	
/**************End of TextBody****************/
	
/**************Start of Images****************/
	
	/**
	 * @return urlname
	 */
	public String getImageURL()	{
		return slide.imagedata.urlname;
	}
	
	/**
	 * @return xstart
	 */
	public int getImageXstart()	{
		return slide.imagedata.xstart;
	}
	
	/**
	 * @return ystart
	 */
	public int getImageYstart()	{
		return slide.imagedata.ystart;
	}
	
	/**
	 * @return width
	 */
	public int getImageWidth()	{
		return slide.imagedata.width;
	}
	
	/**
	 * @return height
	 */
	public int getImageHeight()	{
		return slide.imagedata.height;
	}
	
	/**
	 * @return layer
	 */
	public int getImageLayer()	{
		return slide.imagedata.layer;
	}
	
	/**
	 * @return duration
	 */
	public int getImageDuration()	{
		return slide.imagedata.duration;
	}
	
	/**
	 * @return starttime
	 */
	public int getImageStarttime()	{
		return slide.imagedata.starttime;
	}
	
	/**
	 * @return branch
	 */
	public int getImageBranch()	{
		return slide.imagedata.branch;
	}
	
/***************End of Images*****************/
	
/**************Start of Videos****************/
	
	/**
	 * @return urlname
	 */
	public String getVideoURL()	{
		return slide.videodata.urlname;
	}
	
	/**
	 * @return xstart
	 */
	public int getVideoXstart()	{
		return slide.videodata.xstart;
	}
	
	/**
	 * @return ystart
	 */
	public int getVideoYstart()	{
		return slide.videodata.ystart;
	}
	
	/**
	 * @return width
	 */
	public int getVideoWidth()	{
		return slide.videodata.width;
	}
	
	/**
	 * @return height
	 */
	public int getVideoHeight()	{
		return slide.videodata.height;
	}
	
	/**
	 * @return layer
	 */
	public int getVideoLayer()	{
		return slide.videodata.layer;
	}
	
	/**
	 * @return duration
	 */
	public int getVideoDuration()	{
		return slide.videodata.duration;
	}
	
	/**
	 * @return starttime
	 */
	public int getVideoStarttime()	{
		return slide.videodata.starttime;
	}
	
	/**
	 * @return loop
	 */
	public Boolean getVideoLoop()	{
		return slide.videodata.loop;
	}
	
/***************End of Videos*****************/
	
/***************Start of Audio*****************/	
	
	/**
	 * @return urlname
	 */
	public String getAudioURL()	{
		return slide.audiodata.urlname;
	}
	
	/**
	 * @return starttime
	 */
	public int getAudioStarttime()	{
		return slide.audiodata.starttime;
	}
	
	/**
	 * @return loop
	 */
	public Boolean getAudioLoop()	{
		return slide.audiodata.loop;
	}
	
/***************End of Audio*****************/
}

