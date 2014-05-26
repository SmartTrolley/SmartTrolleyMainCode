package smarttrolleygui;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root (name = "slideshow")
public class SlideShowData {

//	Tagged Element text
	@Element (name = "documentinfo")
private DocumentInfoData  documentinfo;

	/**
	 * @return author to caller
	 */
	public String getAuthor()	{
		return documentinfo.author;
	}
	
	/**
	 * @return author to caller
	 */
	public String getVersion()	{
		return documentinfo.version;
	}
	
	/**
	 * @return author to caller
	 */
	public String getTitle()	{
		return documentinfo.title;
	}
	
	/**
	 * @return author to caller
	 */
	public String getComment()	{
		return documentinfo.comment;
	}
	
	/**
	 * @return author to caller
	 */
	public String getWidth()	{
		return documentinfo.width;
	}
	
	/**
	 * @return author to caller
	 */
	public String getHeight()	{
		return documentinfo.height;
	}
}

