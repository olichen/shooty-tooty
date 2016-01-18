package shootytooty.engine;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class World {

	private Scene gameScene;
	private Group rootNode;
	private final int FPS;
	private final String TITLE;
	private final int WINDOWHEIGHT = 600;
	private final int WINDOWWIDTH = 800;

    private final static List<Bullet> bullets = new ArrayList<>();
	private Circle p1;
	ArrayList<String> input = new ArrayList<String>();

	private static Timeline gameLoop;
	

	public World(int fps, String title) {
		FPS = fps;
		TITLE = title;
		initGameLoop();
	}

	//initialize the stage
	public void initialize(final Stage primaryStage) {
		primaryStage.setTitle(TITLE);
		rootNode = new Group();
		gameScene = new Scene(rootNode, WINDOWWIDTH, WINDOWHEIGHT, Color.color(.7, .7, .9));
		
		//add player
		p1 = new Circle(WINDOWWIDTH/2,WINDOWHEIGHT/2,6,Color.RED);
		rootNode.getChildren().add(p1);
		primaryStage.setScene(gameScene);

		//keyboard event handlers
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

	//set up the game loop
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

	//start the game loop
	public void playGameLoop() {
		gameLoop.play();
	}

	//update sprites
	private void updateSprites() {

        if (input.contains("LEFT"))
            p1.setCenterX(p1.getCenterX()-2);
        if (input.contains("RIGHT"))
            p1.setCenterX(p1.getCenterX()+2);
        if (input.contains("UP"))
            p1.setCenterY(p1.getCenterY()-2);
        if (input.contains("DOWN"))
            p1.setCenterY(p1.getCenterY()+2);
        if (input.contains("Z")) {
        	Bullet newBullet = new Bullet(p1.getCenterX(),p1.getCenterY(),0,-2,2);
        	bullets.add(newBullet);
        	rootNode.getChildren().add(newBullet.bullet);
        }
        for(Bullet bulletA : bullets) {
        	bulletA.update();
        }
	}
}
