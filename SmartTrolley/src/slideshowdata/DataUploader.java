package slideshowdata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DatabaseConnectors.SqlConnection;


public class DataUploader extends SqlConnection{
	
	private PWSParser parser;
	 
	private SlideData currentSlide; 
	private AudioData currentAudio;
	private ImageData currentImage;
	private ShapeData currentShape;
	private PointData currentPoint;
	private TextData  currentText;
	private TextBodyData currentTextbody;
	private VideoData currentVideo;
	
	public DataUploader(){
		super();
	}
	
	/**
	 * Method will add data to the document_info_data on the SQL database
	 * 
	 * @param author
	 * @param version
	 * @param title
	 * @param comment
	 * @param width
	 * @param height
	 */
	public void addDocumentDataContent(String author, String version,
			String title, String comment, int width, int height) {

		openConnection();
		String query = "INSERT INTO document_info_data (author, version, title, comment, width, height) VALUES ('"
				+ author
				+ "', '"
				+ version
				+ "', '"
				+ title
				+ "', '"
				+ comment
				+ "', '"
				+ width
				+ "', '"
				+ height + "');";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will delete data from the document_info_data table on the SQL
	 * database
	 */
	public void deleteDocumentDataContent() {

		openConnection();
		String query = "DELETE FROM document_info_data";
		executeStatement(query);
		closeConnection();
		}

	/**
	 * 
	 * @param backgroundcolor
	 * @param font
	 * @param fontsize
	 * @param linecolor
	 * @param fillcolor
	 */
	public void addDefaultsContent(String backgroundcolor, String font,
			int fontsize, String fontcolor, String linecolor, String fillcolor) {
		openConnection();
		String query = "INSERT INTO defaults (backgroundcolor, font, fontsize, fontcolor, linecolor, fillcolor) VALUES ('"
				+ backgroundcolor
				+ "', '"
				+ font
				+ "', '"
				+ fontsize
				+ "', '"
				+ fontcolor
				+ "', '"
				+ linecolor
				+ "', '"
				+ fillcolor + "');";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will delete data from the defaults table on the SQL
	 * database
	 */
	public void deleteDefaultsContent() {
		openConnection();
		String query = "DELETE FROM defaults";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will add contents to the audio table on the sql server
	 * 
	 * @param productID
	 * @param startTime
	 * @param urlName
	 * @param volume
	 * @param repeat
	 */
	public void addAudioTableContents(int productid, int slideid, 
			String urlName, int startTime, double volume, boolean loop) {
		openConnection();
		String query = "INSERT INTO `cl36-st`.`audio` (`productID`, `slideID`, `urlname`, `starttime`, `volume`, `loop`) VALUES ('"
				+ productid
				+ "', '"
				+ slideid
				+ "', '"
				+ urlName
				+ "', '"
				+ startTime
				+ "', '"
				+ volume
				+ "', '"
				+ loop
				+ "');";
		executeStatement(query);
		closeConnection();

	}

	/**
	 * Method will add contents to the image table on the sql server
	 * 
	 * @param imageNo
	 * @param urlname
	 * @param xstart
	 * @param ystart
	 * @param width
	 * @param height
	 * @param starttime
	 * @param duration
	 * @param layer
	 * @param branch
	 */
	public void addImageContents(int productid, int slideid, String urlname, int xstart,
			int ystart, int width, int height, int starttime, int duration,
			int layer, int branch) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`image_slide` (`productID`, `slideID`, `urlname`, `xstart`, `ystart`, `width`, `height`, `starttime`, `duration`, `layer`, `branch`"
				+ ") VALUES ('"
				+ productid
				+ "', '"
				+ slideid
				+ "', '"
				+ urlname
				+ "', '"
				+ xstart
				+ "', '"
				+ ystart
				+ "', '"
				+ width
				+ "', '"
				+ height
				+ "', '"
				+ starttime
				+ "', '"
				+ duration
				+ "', '"
				+ layer
				+ "', '"
				+ branch
				+ "');";
		executeStatement(query);
		closeConnection();

	}

	/**
	 * Method will add contents to the point table on the sql server
	 * 
	 * @param productid
	 * @param shapeNo
	 * @param individualPointNo
	 * @param x
	 * @param y
	 */
	public void addPointContents(int productid, int slideid, int shapeNo,
			int individualPointNo, int x, int y) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`point` (`productID`, `slideID`, `ShapeNo`, `IndividualPointNo`, `x`, `y`) VALUES ('"
				+ productid
				+ "', '"
				+ slideid
				+ "', '"
				+ shapeNo
				+ "', '"
				+ individualPointNo
				+ "', '"
				+ x
				+ "', '"
				+ y
				+ "');";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will add contents to the shape table on the sql server
	 * 
	 * @param productid
	 * @param totalPoints
	 * @param width
	 * @param height
	 * @param starttime
	 * @param duration
	 * @param layer
	 * @param branch
	 * @param fillcolor
	 * @param linecolor
	 */
	public void addShapeContents(int productid, int slideid, int totalPoints, int width,
			int height, int starttime, int duration, int layer, int branch,
			String fillcolor, String linecolor) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`shape` (`productID`, `slideID`, `totalpoints`, `width`, `height`, `fillcolor`, `starttime`, `duration`, `layer`, `linecolor`, `branch`"
				+ ") VALUES ('"
				+ productid
				+ "', '"
				+ slideid
				+ "', '"
				+ totalPoints
				+ "', '"
				+ width
				+ "', '"
				+ height
				+ "', '"
				+ fillcolor
				+ "', '"
				+ starttime
				+ "', '"
				+ duration
				+ "', '"
				+ layer
				+ "', '"
				+ linecolor
				+ "', '"
				+ branch
				+ "');";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will add contents to the text table on the sql server
	 * 
	 * @param productid
	 * @param totalPoints
	 * @param fontSize
	 * @param xStart
	 * @param yStart
	 * @param startTime
	 * @param duration
	 * @param layer
	 * @param xend
	 * @param yend
	 * @param font
	 * @param fontColor
	 */
	public void addTextContents(int productid, int slideid, int fontSize,
			int xStart, int yStart, int startTime, int duration, int layer,
			int xend, int yend, String font, String fontColor) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`text` (`productID`, `slideID`, `font`, `fontSize`, `FontColor`, `xStart`, `yStart`, `startTime`, `duration`, `layer`, `xend`, `yend`"
				+ ") VALUES ('"
				+ productid
				+ "', '"
				+ slideid
				+ "', '"
				+ font
				+ "', '"
				+ fontSize
				+ "', '"
				+ fontColor
				+ "', '"
				+ xStart
				+ "', '"
				+ yStart
				+ "', '"
				+ startTime
				+ "', '"
				+ duration
				+ "', '"
				+ layer
				+ "', '"
				+ xend
				+ "', '"
				+ yend
				+ "');";
		executeStatement(query);
		closeConnection();

	}

	/**
	 * Method will add contents to the textbody table on the sql server
	 * 
	 * @param productid
	 * @param textNo
	 * @param branch
	 * @param bold
	 * @param italic
	 * @param underlined
	 * @param text
	 * @param branch2
	 */
	public void addTextbodyContents(int productid, int slideid, int textNo, int branch,
			Boolean bold, Boolean italic, Boolean underlined, String text) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`textbody` (`productID`, `slideID`, `TextNo`, `Bold`, `Italic`, `Underlined`, `Text`, `Branch`"
				+ ") VALUES ('"
				+ productid
				+ "', '"
				+ slideid
				+ "', '"
				+ textNo
				+ "', '"
				+ bold
				+ "', '"
				+ italic
				+ "', '"
				+ underlined
				+ "', '"
				+ text
				+ "', '"
				+ branch
				+ "');";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will add contents to the video table on the sql server
	 * 
	 * @param productid
	 * @param urlname
	 * @param starttime
	 * @param loop
	 * @param xstart
	 * @param ystart
	 * @param width
	 * @param height
	 * @param layer
	 * @param duration
	 */
	public void addVideoContents(int productid, int slideid, String urlname, int starttime,
			boolean loop, int xstart, int ystart, int width, int height,
			int layer, int duration) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`video` (`productID`, `slideID`, `urlname`, `starttime`, `loop`, `xstart`, `ystart`, `width`, `height`, `layer`, `duration`"
				+ ") VALUES ('"
				+ productid
				+ "', '"
				+ slideid
				+ "', '"
				+ urlname
				+ "', '"
				+ starttime
				+ "', '"
				+ loop
				+ "', '"
				+ xstart
				+ "', '"
				+ ystart
				+ "', '"
				+ width
				+ "', '"
				+ height
				+ "', '"
				+ layer
				+ "', '"
				+ duration
				+ "');";
		executeStatement(query);
		closeConnection();
	}
	
	/**
	 * Method will add contents to the slide table on the sql server
	 * 
	 * @param slideid
	 * @param duration
	 * @param descriptor
	 * @param lastSlide
	 * @param backgroundcolor
	 */
	public void addSlideContents(int productid, int slideid, int duration, boolean lastSlide) {

		openConnection();
		String query = "INSERT INTO `cl36-st`.`slide` (`productID`, `slideID`, `duration`, `lastSlide`"
				+ ") VALUES ('"
				+ productid
				+ "', '"
				+ slideid
				+ "', '"
				+ duration
				+ "', '"
				+ lastSlide
				+ "');";
		executeStatement(query);
		closeConnection();
	}

	/**
	 * Method will delete data from the slide table on the SQL
	 * database
	 */
	public void deleteSlideContent() {

		openConnection();
		String query = "DELETE FROM slide";
		executeStatement(query);
		closeConnection();
		}
	
	/**
	 * Method will delete all entries of the table it is given
	 * 
	 * @param table
	 */
	public void deleteContentAndResetAutoIncrement(String table){
		openConnection();
		String query = "DELETE FROM " + table;
		executeStatement(query);
		query = "ALTER TABLE " + table + " AUTO_INCREMENT = 1";
		executeStatement(query);
		closeConnection();	
	}
	
	/**
	 * Method will delete all entries of the table it is given
	 * 
	 * @param table
	 */
	public void deleteLastContentAndResetAutoIncrement(String table){
		
		int productid = 0;
		
		openConnection();
		
		String query = "SELECT MAX(ProductID) AS ProductID FROM " + table + ";";
		
		ResultSet results;
		try {
			results = sendQuery(query);
			results.absolute(1);
			productid = results.getInt("ProductID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		query = "DELETE FROM " + table + " WHERE ProductID = " + productid + ";";
		executeStatement(query);
		query = "ALTER TABLE " + table + " AUTO_INCREMENT = "+ productid + ";";
		executeStatement(query);
		closeConnection();	
	}

	/**
	 * Method will upload default data to sql database
	 * 
	 * @param defaultdata
	 */
	public void uploadDefaultData(DefaultsData defaultdata){
		
		addDefaultsContent(defaultdata.getBackgroundcolor(), 
										defaultdata.getFont(), 
										defaultdata.getFontsize(),
										defaultdata.getFontcolor(),
										defaultdata.getLinecolor(), 
										defaultdata.getFillcolor());
	}
	
	/**
	 * Method will upload Document data to sql database
	 * 
	 * @param documentdata
	 */
	public void uploadDocumentData(DocumentInfoData documentdata){
		
		addDocumentDataContent(documentdata.getAuthor(),
											documentdata.getAuthor(), 
											documentdata.getTitle(), 
											documentdata.getComment(), 
											documentdata.getWidth(), 
											documentdata.getHeight());
	}
	
	/**
	 * Method will upload all data from the XML parser up to the SQL database
	 * 
	 * @param data
	 */
	public void uploadXmlData(String filename){
		
		parser  =  new PWSParser();
		SlideShowData data = parser.read(filename);
		
		
		int slideIndex = 0,
			audioIndex = 0,
			imageIndex = 0,
			pointIndex = 0,
			shapeIndex = 0,
			textIndex = 0,
			textbodyIndex = 0,
			videoIndex = 0,
			productid = 0;
		
		String productName = null;

		uploadDocumentData(data.getDocumentinfo());
		uploadDefaultData(data.getDefaults());
		
		
		int listid = createNewList(data.getDocumentinfo().getTitle());
		
		

		for(SlideData currentSlide : data.getSlides()){
			
			productName = currentSlide.getProductName();
			
			if (productName != null){
				productid = createNewProduct(productName, 0, 
											 currentSlide.getImgURL(), 
											 currentSlide.getPrice(), 
											 currentSlide.getCategoryID(),
											 currentSlide.getIsFavourite());
				
			}
		else{
				productid = createNewProduct(data.getDocumentinfo().getTitle(), currentSlide.getId()+1, null, 0, 1, 0);
				addProductToList(productid, listid);	
			}
			
			
			addSlideContents(productid,
							 currentSlide.getId()+1, 
							 currentSlide.getDuration(), 					
							 currentSlide.getLastSlide());
			
			while(audioIndex<currentSlide.getAudios().size()){
				
				currentAudio = currentSlide.getAudios().get(audioIndex);
				
				addAudioTableContents(productid,
									  slideIndex+1,
									  currentAudio.getUrlname(),
									  currentAudio.getStarttime(),
									  50, //volume: not in PWS
									  currentAudio.getLoop());
				audioIndex++;
			}
			
			while (imageIndex < currentSlide.getImages().size()) {
				
				currentImage = currentSlide.getImages().get(imageIndex);

				addImageContents(productid,
								 slideIndex+1,
							  	 currentImage.getUrlname(),
							  	 currentImage.getXstart(), 
							  	 currentImage.getYstart(),
							  	 currentImage.getWidth(), 
							  	 currentImage.getHeight(),
							  	 currentImage.getStarttime(), 
							  	 currentImage.getDuration(),
							  	 currentImage.getLayer(), 
							  	 currentImage.getBranch());
				imageIndex++;
			}
			
			while(shapeIndex < currentSlide.getShapes().size()){
				
				
				currentShape = currentSlide.getShapes().get(shapeIndex);
				
				addShapeContents(productid,
								 slideIndex+1,
								 currentShape.getTotalpoints(), 
								 currentShape.getWidth(), 
								 currentShape.getHeight(),
								 currentShape.getStarttime(), 
								 currentShape.getDuration(), 
								 currentShape.getLayer(), 
								 1, //branch
								 currentShape.getFillcolor(), 
								 currentShape.getLinecolor());
				
				while(pointIndex<currentShape.getPoints().size()){
					
					currentPoint = currentShape.getPoints().get(pointIndex);
					
					addPointContents(productid,
									 slideIndex+1,
			  						 shapeIndex+1, //ShapeNo
			  						 currentPoint.getNum(), 
			  						 currentPoint.getX(), 
			  						 currentPoint.getY());
					pointIndex++;
				}
				pointIndex = 0;
				shapeIndex++;
			}
			
			while(textIndex<currentSlide.getTexts().size()){
				
				currentText = currentSlide.getTexts().get(textIndex);
				
				addTextContents(productid,
								slideIndex+1,
								currentText.getFontsize(), 
								currentText.getXstart(), 
								currentText.getYstart(), 
								currentText.getStarttime(), 
								currentText.getDuration(), 
								currentText.getLayer(), 
								currentText.getXend(), 
								currentText.getYend(), 
								currentText.getFont(), 
								currentText.getFontcolor());
				
				ArrayList<TextData> textdata =(ArrayList<TextData>)getSpecificData("text", "ProductID", ""+productid);
				
				int TextNo = textdata.get(0).getTextNo();
				
				while(textbodyIndex<currentText.getTextbodies().size()){
					
					currentTextbody = currentText.getTextbodies().get(textbodyIndex);
					
					addTextbodyContents(productid, 
										slideIndex+1,
										TextNo,  
										currentTextbody.getBranch(), 
										currentTextbody.getBold(), 
										currentTextbody.getItalic(), 
										currentTextbody.getUnderlined(), 
										currentTextbody.getTextstring());
					textbodyIndex++;
				}
				
				textbodyIndex = 0;
				textIndex++;				
			}
			
			while(videoIndex<currentSlide.getVideos().size()){
				
				currentVideo = currentSlide.getVideos().get(videoIndex);
				
				addVideoContents(productid, 
								 slideIndex+1,
								 currentVideo.getUrlname(), 
								 currentVideo.getStarttime(), 
								 currentVideo.getLoop(), 
								 currentVideo.getXstart(), 
								 currentVideo.getYstart(), 
								 currentVideo.getWidth(), 
								 currentVideo.getHeight(), 
								 currentVideo.getLayer(), 
								  currentVideo.getDuration());
				videoIndex++;
			}
			
			audioIndex = 0;
			imageIndex = 0;
			pointIndex = 0;
			shapeIndex = 0;
			textIndex = 0;
			textbodyIndex = 0;
			videoIndex = 0;
			
			slideIndex++;
		}
		
	}

}
