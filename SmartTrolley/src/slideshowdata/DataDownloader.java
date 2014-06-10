package slideshowdata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import smarttrolleygui.ListProduct;
import toolBox.SmartTrolleyToolBox;
import DatabaseConnectors.SqlConnection;
/**
* SmartTrolley Class for Downloading info from the Sql Database into the Data Classes
*
* @author Thomas Lea
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 27 May 2014]
**/
public class DataDownloader extends SqlConnection {

	private ObservableList<ListProduct> slides;

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

	/**
	* Downloads all of the media data for a particular slide/product into the data classes
	*@param productid
	*@return SlideData
	*<p> Date Modified: 10 Jun 2014
	*/
	public SlideData populateSlide(int productid) {

		int i = 0;
		String table = "";
		ArrayList<Object> dataList = new ArrayList<Object>();
		Statement statement = null;
		Object data = null;

		String multipleQueries = "";
		String query = "Select * From slide where ProductID = '" + productid + "';";
		multipleQueries = multipleQueries.concat(query);
		query = "Select * From text where ProductID = '" + productid + "';";
		multipleQueries = multipleQueries.concat(query);
		query = "Select * From shape where ProductID = '" + productid + "';";
		multipleQueries = multipleQueries.concat(query);
		query = "Select * From image_slide where ProductID = '" + productid + "';";
		multipleQueries = multipleQueries.concat(query);
		query = "Select * From video where ProductID = '" + productid + "';";
		multipleQueries = multipleQueries.concat(query);
		query = "Select * From audio where ProductID = '" + productid + "';";
		multipleQueries = multipleQueries.concat(query);

		try {
			openConnection();
			statement = connection.createStatement();
			boolean results = statement.execute(multipleQueries);
			while (results) {

				switch (i) {
				case 0:
					table = "slide";
					break;
				case 1:
					table = "text";
					break;
				case 2:
					table = "shape";
					break;
				case 3:
					table = "image_slide";
					break;
				case 4:
					table = "video";
					break;
				case 5:
					table = "audio";
					break;
				}

				data = new Object();
				if (results) {

					ResultSet rs = statement.getResultSet();

					data = populateDataType(table, rs);

					dataList.add(data);
					results = statement.getMoreResults();
					i++;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		SlideData slide = (SlideData) dataList.get(0);

		ArrayList<TextData> texts = (ArrayList<TextData>) dataList.get(1);
		setTextBodiesForSlide(texts);

		ArrayList<ShapeData> shapes = (ArrayList<ShapeData>) dataList.get(2);
		setPointsForSlide(shapes);

		ArrayList<ImageData> images = (ArrayList<ImageData>) dataList.get(3);

		ArrayList<VideoData> videos = (ArrayList<VideoData>) dataList.get(4);

		ArrayList<AudioData> audios = (ArrayList<AudioData>) dataList.get(5);

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
	*Creates one large query to be sent for downloading all relevant Points and sets them to the Shapes
	*@param shapes
	*<p> Date Modified: 8 Jun 2014
	*/
	private void setPointsForSlide(ArrayList<ShapeData> shapes) {
		String multipleQueries;
		int k = 0;
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
	*Creates one large query to be sent for downloading all relevant TextBodies and sets them to the Texts
	*@param texts
	*<p> Date Modified: 8 Jun 2014
	*/
	private void setTextBodiesForSlide(ArrayList<TextData> texts) {
		int j = 0;
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
