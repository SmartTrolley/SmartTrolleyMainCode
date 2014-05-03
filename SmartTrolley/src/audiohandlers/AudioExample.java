package audiohandlers;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AudioExample extends Application {

	private AudioHandler vvvvvv, tweet, tweetOnce;

	public void start(Stage stage) throws Exception {
		Group root;
		Scene scene;
		root = new Group();
		scene = new Scene(root, 800, 600, Color.BLACK);

		vvvvvv = new AudioHandler("file:assets/positive_force.mp3", 5, 0.8);
		tweet = new AudioHandler("file:assets/tweet.wav", 2, 5, 1.0);
		tweetOnce = new AudioHandler("file:assets/tweet.wav", 0, 0.2);

		tweet.setRepeat(true);

		vvvvvv.begin();
		tweet.begin();
		tweetOnce.begin();

		stage.setTitle("Audio Example");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.setFullScreen(false);
		stage.show();

		// when the window is closed stop the audio
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				vvvvvv.stop();
				tweet.stop();
				tweetOnce.stop();
			}
		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}