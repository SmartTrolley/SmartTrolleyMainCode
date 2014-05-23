package SpikeSlide;


import videohandler.VideoPlayerHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaneTesting extends Application{
	
	private String imageURL = "http://th03.deviantart.net/fs70/PRE/i/2013/077/8/9/cookie_monster_by_xenia_cat-d5yhjwj.jpg";
	private String local = "file: Koala.jpg";
	private String videoURL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
	private int windowWidth = 1000;
	private int windowHeight = 800;
	private double border = 5.0;
	
	private double imgPaneLeftAnchor = border;
	private double imgPaneTopAnchor = border;
	private double imgPaneRightAnchor = windowWidth/2;
	private double imgPaneBotAnchor = windowHeight/2;
	//private double imgPaneHeight = windowHeight - imgPaneTopAnchor - imgPaneBotAnchor;
	//private double imgPaneWidth = windowWidth - imgPaneRightAnchor - imgPaneLeftAnchor;
	
	private int imgWidth = (int) (imgPaneRightAnchor - imgPaneLeftAnchor - 40);
	private int imgHeight = (int) (imgPaneBotAnchor - imgPaneTopAnchor - 40);
	
	private double vidPaneRightAnchor = border;
	private double vidPaneBotAnchor = border;
	private double vidPaneLeftAnchor = windowWidth/2;
	private double vidPaneTopAnchor = windowHeight/2;
	//private double vidPaneHeight = windowHeight - vidPaneBotAnchor - vidPaneTopAnchor;
	//private double vidPaneWidth = windowWidth - vidPaneLeftAnchor - vidPaneRightAnchor;
	
	private int vidWidth = (int) (vidPaneLeftAnchor - vidPaneRightAnchor - 60);
	private int vidHeight = (int) (vidPaneTopAnchor - vidPaneBotAnchor - 60);
	
	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    
	    Label version = new Label ("V1.0");
	    Label title = new Label ("Test Title");
	    Label author = new Label ("Smart Trolley");
	   
	    hbox.getChildren().add(version);
	    hbox.getChildren().add(title);
	    hbox.getChildren().add(author);
	    

	    return hbox;
	}


	public AnchorPane addAnchorPane() {
	    AnchorPane anchorpane = new AnchorPane();
	    anchorpane.setStyle("-fx-background-colour: #336699;");
	    
	    imagehandler.SlideImage image1 = new imagehandler.SlideImage(imageURL, 1, 1, imgWidth, imgHeight, 0, 0);
	    image1.show();
	    imagehandler.SlideImage image2 = new imagehandler.SlideImage(imageURL, 1, 1, 30, 60, 2, 9);
	    image2.show();
	    
	   VideoPlayerHandler video1 = new videohandler.VideoPlayerHandler(videoURL, 1, 1, vidWidth, vidHeight, true, 0, 0);
	   Group root = new Group();
	   root.getChildren().add(video1.mediaControl.overallBox);
	   
	   anchorpane.getChildren().add(root);
	   AnchorPane.setTopAnchor(root, vidPaneTopAnchor);
	   AnchorPane.setRightAnchor(root, vidPaneRightAnchor);
	   AnchorPane.setLeftAnchor(root, vidPaneLeftAnchor);
	   AnchorPane.setBottomAnchor(root, vidPaneBotAnchor);
	
	   	anchorpane.getChildren().add(image1);
	    AnchorPane.setTopAnchor(image1, imgPaneTopAnchor);
	    AnchorPane.setLeftAnchor(image1, imgPaneLeftAnchor);
	    AnchorPane.setBottomAnchor(image1, imgPaneBotAnchor);
	    AnchorPane.setRightAnchor(image1, imgPaneRightAnchor);
	    
	   	anchorpane.getChildren().add(image2);
	    AnchorPane.setTopAnchor(image2, imgPaneTopAnchor);
	    AnchorPane.setLeftAnchor(image2, imgPaneLeftAnchor);
	    AnchorPane.setBottomAnchor(image2, imgPaneBotAnchor);
	    AnchorPane.setRightAnchor(image2, imgPaneRightAnchor);
	    
	    
	


	    return anchorpane;
	}


	@Override
	public void start(Stage stage) throws Exception {
		BorderPane border = new BorderPane();
		border.setPrefSize(windowWidth, windowHeight);
		HBox hbox = addHBox();
		border.setTop(hbox);

		
		border.setCenter(addAnchorPane());
		
		 Scene scene = new Scene(border);
	        stage.setScene(scene);
	        stage.setTitle("Layout Sample");
	        stage.show();
		
	}

	
	public static void main(String[] args) {
		
		launch(PaneTesting.class, args);
	}


	


}
