package shootytooty;

import javafx.application.Application;
import javafx.stage.Stage;
import shootytooty.engine.World;

public class ShootyTooty extends Application {

	World shootyWorld = new World(60, "Shooty Tooty");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		shootyWorld.initialize(primaryStage);
		shootyWorld.playGameLoop();
		primaryStage.show();
	}

	//Usually ignored, only used if JavaFX cannot be started with start()
	public static void main(String[] args) {
		launch(args);
	}

}
