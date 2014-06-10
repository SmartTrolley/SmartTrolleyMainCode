package slideshowdata;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
/**
* SmartTrolley Data Class for DocumentInfo
* includes getters and setters for this data and any attributes or elements for parsing
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 27 May 2014]
**/
@Root (strict = false)
public class DocumentInfoData{
	
	private int listid;
	
	@Element (name = "author")
	private String author;
	@Element (name = "version")
	private String version;
	@Element (name = "title")
	private String title;
	@Element (name = "comment")
	private String comment;
	@Element (name = "width")
	private int width;
	@Element (name = "height")
	private int height;
	
//	@Attribute (name = "noList", required = false)
//	private String noList;
	
	public String getAuthor() {
		return author;
	}
	public String getVersion() {
		return version;
	}
	public String getTitle() {
		return title;
	}
	public String getComment() {
		return comment;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public int getListId() {
		return listid;
	}	
	
	
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setListId(int listid) {
		this.listid = listid;
	}
	
//	public void setNoList(String noList) {
//		this.noList = noList;
//	}

}