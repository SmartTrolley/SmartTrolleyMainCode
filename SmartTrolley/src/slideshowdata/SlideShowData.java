package slideshowdata;


import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
* SmartTrolley Data Class for SlideShow
* includes getters and setters for this data and any attributes or elements for parsing
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 27 May 2014]
**/
@Root (name = "slideshow", strict = false)
public class SlideShowData{

	@Element (name = "documentinfo")
	private DocumentInfoData  documentinfo;
	
	@Element (name = "defaults")
	private DefaultsData  defaults;
	
	@ElementList (entry = "slide", inline = true) 
	private ArrayList<SlideData>  slides;
	
	

	public DocumentInfoData getDocumentinfo() {
		return documentinfo;
	}

	public DefaultsData getDefaults() {
		return defaults;
	}

	public ArrayList<SlideData> getSlides() {
		return slides;
	}

	public void setDocumentinfo(DocumentInfoData documentinfo) {
		this.documentinfo = documentinfo;
	}

	public void setDefaults(DefaultsData defaults) {
		this.defaults = defaults;
	}

	public void setSlides(ArrayList<SlideData> slides) {
		this.slides = slides;
	}
	
	
	
}

