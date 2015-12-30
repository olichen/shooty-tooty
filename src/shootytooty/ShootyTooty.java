package shootytooty;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShootyTooty extends Application {

	private Scene initScene(){
		Scene scene = new Scene(root);
		return scene;
		//https://dzone.com/articles/javafx-2-game-tutorial-part-2
		//https://github.com/joerno/PegSolitaire/blob/master/src/main/java/de/ohmen/fxgame/PegSolitaire.java
		//http://smooth-java.blogspot.com/2013/12/tutorial-how-to-develop-simple-javafx.html
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Shooty Tooty");
		primaryStage.setScene(initScene());
	}

	//Usually ignored, only used if JavaFX cannot be started with start()
	public static void main(String[] args) {
		launch(args);
	}

}
