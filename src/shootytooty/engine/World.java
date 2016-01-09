package shootytooty.engine;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class World {

	private Scene gameScene;
	private Group rootNode;
	private final int FPS;
	private final String TITLE;

	private Rectangle p1;

	private static Timeline gameLoop;
	
	ArrayList<String> input = new ArrayList<String>();

	public World(int fps, String title) {
		FPS = fps;
		TITLE = title;
		initGameLoop();
	}

	public void initialize(final Stage primaryStage) {
		primaryStage.setTitle(TITLE);
		rootNode = new Group();
		gameScene = new Scene(rootNode, 800, 600, Color.color(.7, .7, .9));
		p1 = new Rectangle(5, 5, Color.RED);
		rootNode.getChildren().add(p1);
		primaryStage.setScene(gameScene);


		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (!input.contains(code))
					input.add(code);
			}
		});

		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				input.remove(code);
			}
		});
	}

	private final void initGameLoop() {
		final KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						updateSprites();
					}
				});

		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		gameLoop.getKeyFrames().add(kf);
	}

	public void playGameLoop() {
		gameLoop.play();
	}

	private void updateSprites() {

        if (input.contains("LEFT"))
            p1.setX(p1.getX()-2);
        if (input.contains("RIGHT"))
            p1.setX(p1.getX()+2);
        if (input.contains("UP"))
            p1.setY(p1.getY()-2);
        if (input.contains("DOWN"))
            p1.setY(p1.getY()+2);
	}
}
