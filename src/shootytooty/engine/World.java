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
import javafx.stage.Stage;
import javafx.util.Duration;
import shootytooty.engine.bullets.BulletManager;
import shootytooty.engine.enemy.EnemyManager;

public class World {

	private Scene gameScene;
	private Group rootNode;
	private final int FPS;
	private final String TITLE;
	private final static int WINDOWHEIGHT = 600;
	private final static int WINDOWWIDTH = 800;
	public final static boolean SHOWHITBOX = true;

	private int bulletCooldown = 0;
	private int timer = 0;

	private final static BulletManager playerBullet = new BulletManager();
	private final static BulletManager enemyBullet = new BulletManager();
	private final static EnemyManager enemyList = new EnemyManager();

	private static Player p1;
	ArrayList<String> input = new ArrayList<String>();

	private static Timeline gameLoop;

	public World(int fps, String title) {
		FPS = fps;
		TITLE = title;
		initGameLoop();
	}

	// initialize the stage
	public void initialize(final Stage primaryStage) {
		primaryStage.setTitle(TITLE);
		rootNode = new Group();
		gameScene = new Scene(rootNode, WINDOWWIDTH, WINDOWHEIGHT, Color.color(
				.7, .7, .9));
		primaryStage.setScene(gameScene);

		// set rootnodes
		BulletManager.setRootNode(rootNode);
		EnemyManager.setRootNode(rootNode);

		// add player
		createPlayer();

		// keyboard event handlers
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

	// set up the game loop
	private final void initGameLoop() {
		final KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						update();
					}
				});

		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		gameLoop.getKeyFrames().add(kf);
	}

	// start the game loop
	public void playGameLoop() {
		gameLoop.play();
	}

	// game loop
	private void update() {
		updateShips();
		updateBullets();
		checkCollisions();
		cleanSprites();
	}

	// update player and enemy sprites
	private void updateShips() {
		// handle player movement
		if (input.contains("LEFT"))
			p1.moveLeft();
		if (input.contains("RIGHT"))
			p1.moveRight();
		if (input.contains("UP"))
			p1.moveUp();
		if (input.contains("DOWN"))
			p1.moveDown();

		enemyList.update();
		enemyList.outOfBounds(WINDOWWIDTH, WINDOWHEIGHT);
	}

	// update bullets
	private void updateBullets() {
		// update and check for out of bounds bullets
		playerBullet.update();
		playerBullet.outOfBounds(WINDOWWIDTH, WINDOWHEIGHT);
		enemyBullet.update();
		enemyBullet.outOfBounds(WINDOWWIDTH, WINDOWHEIGHT);

		// handle bullet cooldown
		if (bulletCooldown > 0)
			bulletCooldown--;
		// create bullets
		if (input.contains("Z")) {
			// bullet cooldown (in frames)
			if (bulletCooldown == 0) {
				bulletCooldown += 10;
				playerBullet.createBullet(p1.hitbox.getCenterX(),
						p1.hitbox.getCenterY(), 0, -2, 2);
			}
		}
		if (timer++ % 50 == 0)
			createEnemy();

		// create enemy bullets
		/*
		 * for (Enemy e : enemies) { if (e.isFiring()) {
		 * enemyBullet.createBullet(e.hitbox.getCenterX(),
		 * e.hitbox.getCenterY(), 0, 2, 2); } }
		 */
	}

	// check for collisions
	private void checkCollisions() {
		// check if any enemies are hit
		enemyList.checkCollisions(playerBullet);
		// check if player is hit
		enemyBullet.checkCollisions(p1);
	}

	// clean up Sprites
	private void cleanSprites() {
		// clean bullets
		playerBullet.clean();
		enemyBullet.clean();
		// clean enemies
		enemyList.clean();
	}

	// create players
	private void createPlayer() {
		p1 = new Player(WINDOWWIDTH / 2, WINDOWHEIGHT / 2, 6);
		rootNode.getChildren().add(p1.sprite);
		if (SHOWHITBOX)
			rootNode.getChildren().add(p1.hitbox);
	}

	// create enemy
	private void createEnemy() {
		enemyList.createEnemy(0, 100, 1, 0, 10);
	}
}
