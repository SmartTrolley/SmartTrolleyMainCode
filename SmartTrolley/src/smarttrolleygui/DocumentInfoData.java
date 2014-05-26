package smarttrolleygui;

import org.simpleframework.xml.Element;

public class DocumentInfoData {
	@Element (name = "author")
	public String author;
	@Element (name = "version")
	public String version;
	@Element (name = "title")
	public String title;
	@Element (name = "comment")
	public String comment;
	@Element (name = "width")
	public String width;
	@Element (name = "height")
	public String height;

	public DocumentInfoData() {
	}
}