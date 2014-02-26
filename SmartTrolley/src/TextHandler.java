import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class TextHandler extends Application {
	
	
	Text title;
	Text body;
	
		public static void main(String[] args){
			launch(args);			
		}

		@Override
		public void start(Stage stage) throws Exception {
			// TODO Auto-generated method stub	
			
			title = new Text();
			body = new Text();
			
			title.setText( "hello world \nI hate java\nWhat am I even doing?");
			body.setText("here is more text!!!");
			
			
			title.getStyleClass().add("title");
			body.getStyleClass().add("body");
			
			VBox root = new VBox();
			root.getChildren().add(title);
			root.getChildren().add(body);
			Scene scene = new Scene(root, 500, 500);
			stage.setScene(scene);
			
			scene.getStylesheets().add("styleTest.css");
			
			
			stage.show();
		}
		
		public Node getPNode() {
			
			Node pNode = new Node();
			return pNode;
		}
		
}
