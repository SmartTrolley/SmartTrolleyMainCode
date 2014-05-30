/*
 * Programmer: Roger Tan & Zayyad Tagwai
 * 
 * Date Created: 01/03/2014
 * 
 * Description: Class that creates a control bar that is overlaid on the video. Functionality
 * 				is play/pause, stop, scrubbing bar, volume slider, and fullscreen mode with same.
 */

package videohandler;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;

public class MediaControl {
	
	protected MediaPlayer mp;
    protected VBox overallBox;
	private InputStream inputStream;
	protected int mpWidth;
	protected int mpHeight;
	private Rectangle2D bounds;
	protected MediaView mediaView;
	private double startTime;
	private double playDuration;
	private Boolean mpLoop;
	protected HBox mediaBar;
	protected boolean continuePlaying = true;
	private boolean started;
	private Timer timer;
	
	/* 
	 * Constructor for the MediaControl class. Accepts optional parameters from PWS.
	 * Creates a visual control bar with a play/pause button, a stop button, and a 
	 * fullscreen button, which is overlayed onto the MediaPlayer. Also handles 
	 * entering into the fullscreen viewing mode.
	 * 
	 * @param mp The MediaPlayer object instantiated by the VideoHandler class
	 * @param width The PWS optional width for the MediaPlayer
	 * @param height The PWS optional height for the MediaPlayer
	 * @param loop The PWS optional loop value for the video
	 * @param startTime The PWS optional startTime to delay the video starting to play
	 * @param playDuration The PWS optional duration to play the video for
	 */
	public MediaControl(final MediaPlayer mp, Integer width, Integer height, Boolean loop, double startTime, double duration2){
		
		this.mp = mp;
		//convert to milliseconds
		this.startTime = startTime * 1000;
		this.playDuration = duration2 * 1000;
		
		mediaView = new MediaView(mp);
		
		// Retrieve the size of the Screen
		bounds = Screen.getPrimary().getVisualBounds();
		
		// Assign loop variable as necessary
			this.mpLoop = loop;
		
		if (width != null && height != null) {
			// Set the height and width of the MediaPlayer based on the values
			this.mpWidth = width;
			this.mpHeight = height;
			mediaView.setPreserveRatio(false);
            mediaView.setFitWidth(mpWidth);
            mediaView.setFitHeight(mpHeight-35);
		} else {
			// Set a default size of the MediaPlayer when no height and width are being indicated
			this.mpWidth = (int) (bounds.getWidth()/2);
			this.mpHeight = (int) (bounds.getHeight()/4);
			mediaView.setPreserveRatio(true);
            mediaView.setFitWidth(mpWidth);
		}
		
		// A VBox that contains the MediaView and Control Panel of the MediaPlayer
		overallBox = new VBox();
		overallBox.setMaxSize(mpWidth, mpHeight);
		mediaView.setFitHeight(mpHeight-35);
		overallBox.getChildren().add(mediaView);
		
		// A HBox that contains all the Controls of the MediaPlayer
		mediaBar = new HBox();
		mediaBar.setMaxWidth(mpWidth);
	    mediaBar.setPadding(new Insets(5, 10, 5, 10));
			
	    try {
	    	// Get and load images for buttons on MediaControl bar.
			inputStream = new FileInputStream("../Resources/play.png");
			new Image(inputStream);
			inputStream = new FileInputStream("../Resources/pause.png");
			new Image(inputStream);
			inputStream = new FileInputStream("../Resources/stop.png");
			new Image(inputStream);
			inputStream = new FileInputStream("../Resources/fullscreen.png");
			new Image(inputStream);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		

    }
	
	void show()
	{
		timer = new Timer();

		started = true;
		
		TimerTask appear = new ShowTask();
		timer.schedule(appear, (long)this.startTime);
	}

	/**
	*Calls run() to make the shape appear visible after the set delay
	*and then disappear after its set duration
	*@param milliseconds	
	*<p> Date Modified: 25 April 2014
	 */
	private class ShowTask extends TimerTask {
		
		@Override
		public void run() {
			if (started) {
				// after start time has finished
				mp.play();
				started = false;

				// 0 duration keeps shape visible
				if (playDuration != 0) {
					timer.schedule(new ShowTask(), (long)playDuration);
				}

			} else {
				
				try{
				
				// after duration has finished
				mp.stop();
				
				if(mpLoop){
					mp.play();
					timer.schedule(new ShowTask(), (long)playDuration);
					
				}
				}catch(NullPointerException e){
					System.out.println("Media Player has been destroyed");
				} 
			}
		}

	}
    
    



}