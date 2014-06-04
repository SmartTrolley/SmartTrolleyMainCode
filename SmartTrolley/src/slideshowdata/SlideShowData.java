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


import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root (name = "slideshow", strict = false)
public class SlideShowData implements DataType{

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

