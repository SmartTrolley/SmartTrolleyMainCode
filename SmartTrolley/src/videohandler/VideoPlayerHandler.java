package videohandler;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Programmer: Roger Tan & Zayyad Tagwai
 * 
 * Date Created: 01/03/2014
 * 
 * Description: Handler class for Videos. Creates a MediaPlayer object to play the content,
 * 				and overlays a MediaControl bar to interface with the MediaPlayer object.
 */
public class VideoPlayerHandler {
	
	protected MediaControl mediaControl;
	private MediaPlayer mediaPlayer;
	protected Media media;
	
	/** 
	 * Constructor for VideoHandler. Accepts both required and optional parameters from PWS.
	 * Creates a MediaPlayer object and MediaControl object, before setting the location 
	 * and adding both to the scene.
	 * 
	 * @param pathLocation 
	 * @param xStart
	 * @param yStart
	 * @param width
	 * @param height
	 * @param loop
	 * @param startTime
	 * @param duration
	 */
	public VideoPlayerHandler(String pathLocation, int xStart, int yStart, Integer width, Integer height, Boolean loop, double startTime, double duration){
        
        // Create a MediaPlayer which plays the URL provided
		try{
			media = new Media(pathLocation);
		}catch(IllegalArgumentException e){
			mediaControl = null;
			return;
		}
        mediaPlayer = new MediaPlayer(media);
        
        // Pass the mediaPlayer into the MediaControl class to have it's interface setup with the appropriate conditions 
        mediaControl = new MediaControl(mediaPlayer, width, height, loop, startTime, duration);
        
	}
}