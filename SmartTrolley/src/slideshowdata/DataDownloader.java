package slideshowdata;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import smarttrolleygui.ListProduct;
import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;

public class DataDownloader extends SqlConnection {

	private ObservableList<ListProduct> slides;
	private ArrayList<SlideData> slidelist;

	public SlideShowData populateSlideshow(int ListId) {

		int i = 0, index = 0;

		slides = getListOfProductsInList(ListId);

		ArrayList<SlideData> slidelist = new ArrayList<SlideData>();
		SlideShowData slideshowdata = new SlideShowData();

		DefaultsData defaults = (DefaultsData) getSpecificData("defaults", "ListID", "" + ListId);

		DocumentInfoData documentinfo = (DocumentInfoData) getSpecificData("document_info_data", "ListID", "" + ListId);

		while (i < slides.size()) {

			int productid = slides.get(i).getID();

			SlideData slide = populateSlide(productid);

			slidelist.add(index, slide);

			index++;
			i++;
		}

		slideshowdata.setSlides(slidelist);
		slideshowdata.setDefaults(defaults);
		slideshowdata.setDocumentinfo(documentinfo);

		SmartTrolleyToolBox.print("Finished downloading slideshow.");

		return slideshowdata;
	}

	public SlideData populateSlide(int productid) {


		SlideData slide = (SlideData) getSpecificData("slide", "ProductID", "" + productid);

		ArrayList<TextData> texts = (ArrayList<TextData>) getSpecificData("text", "ProductID", "" + productid);
		
		setTextBodiesForSlide(texts);

		ArrayList<ShapeData> shapes = (ArrayList<ShapeData>) getSpecificData("shape", "ProductID", "" + productid);
		setPointsForSlide(shapes);

		ArrayList<ImageData> images = (ArrayList<ImageData>) getSpecificData("image_slide", "ProductID", "" + productid);

		ArrayList<VideoData> videos = (ArrayList<VideoData>) getSpecificData("video", "ProductID", "" + productid);

		ArrayList<AudioData> audios = (ArrayList<AudioData>) getSpecificData("audio", "ProductID", "" + productid);

		if (texts != null) {
			slide.setTexts(texts);
		}

		if (shapes != null) {
			slide.setShapes(shapes);
		}

		if (videos != null) {
			slide.setVideos(videos);
		}

		if (audios != null) {
			slide.setAudios(audios);
		}

		if (images != null) {
			slide.setImages(images);
		}

		return slide;
	}

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param shapes
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 8 Jun 2014
	*/
	private void setPointsForSlide(ArrayList<ShapeData> shapes) {
		String multipleQueries;
		int k=0;
		multipleQueries = "";

		while (shapes != null && k < shapes.size()) {

			int shapeno = shapes.get(k).getShapeNo();
			String query = "Select * From point where ShapeNo = '" + shapeno + "'; ";

			multipleQueries = multipleQueries.concat(query);
			k++;
		}
		if (shapes != null) {
			ArrayList<ArrayList<PointData>> points = (ArrayList<ArrayList<PointData>>) getMultipleDatas("point", multipleQueries);

			for (int i = 0; shapes != null && i < shapes.size(); i++) {
				shapes.get(i).setPoints(points.get(i));

			}
		}
	}

	/**
	*Method/Test Description
	*<p>Test(s)/User Story that it satisfies
	*@param texts
	*[If applicable]@see [Reference URL OR Class#Method]
	*<p> Date Modified: 8 Jun 2014
	*/
	private void setTextBodiesForSlide( ArrayList<TextData> texts) {
		int j=0;
		String multipleQueries = "";
		while (texts != null && j < texts.size()) {

			int textno = texts.get(j).getTextNo();
			String query = "Select * From textbody where TextNo = '" + textno + "'; ";

			multipleQueries = multipleQueries.concat(query);
			j++;
		}

		if (texts != null) {
			ArrayList<ArrayList<TextBodyData>> textbodies = (ArrayList<ArrayList<TextBodyData>>) getMultipleDatas("textbody", multipleQueries);

			for (int i = 0; texts != null && i < texts.size(); i++) {
				texts.get(i).setTextbodies(textbodies.get(i));

			}
		}
	}
}
