package shootytooty.engine;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class World {

	private Scene gameScene;
	private Group rootNode;
	private final int FPS;
	private final String TITLE;
	private final static int WINDOWHEIGHT = 600;
	private final static int WINDOWWIDTH = 800;
	private final static boolean SHOWHITBOX = true;

	private int bulletCooldown = 0;

	private final static List<Bullet> playerBullets = new ArrayList<>();
	private final static List<Bullet> enemyBullets = new ArrayList<>();
	private final static List<Enemy> enemies = new ArrayList<>();

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

		// add player
		createPlayer();

		// add enemy
		createEnemy();

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

		// update enemies and check for out of bounds enemies
		for (Enemy e : enemies) {
			e.update();
			e.outOfBounds(WINDOWWIDTH, WINDOWHEIGHT);
		}
	}

	// update bullets
	private void updateBullets() {
		// update and check for out of bounds bullets
		for (Bullet b : playerBullets) {
			b.update();
			b.outOfBounds(WINDOWWIDTH, WINDOWHEIGHT);
		}
		for (Bullet b : enemyBullets) {
			b.update();
			b.outOfBounds(WINDOWWIDTH, WINDOWHEIGHT);
		}

		// handle bullet cooldown
		if (bulletCooldown > 0)
			bulletCooldown--;
		// create bullets
		if (input.contains("Z")) {
			// bullet cooldown (in frames)
			if (bulletCooldown == 0) {
				bulletCooldown += 10;
				Bullet newBullet = new Bullet(p1.hitbox.getCenterX(),
						p1.hitbox.getCenterY() - 16, 0, -2, 2);
				playerBullets.add(newBullet);
				rootNode.getChildren().add(0, newBullet.sprite);
				if (SHOWHITBOX)
					rootNode.getChildren().add(1, newBullet.hitbox);
			}
		}

		// create enemy bullets
		for (Enemy e : enemies) {
			if (e.isFiring()) {
				Bullet newBullet = new Bullet(e.hitbox.getCenterX(),
						e.hitbox.getCenterY(), 0, 2, 2);
				enemyBullets.add(newBullet);
				rootNode.getChildren().add(0, newBullet.sprite);
				if (SHOWHITBOX)
					rootNode.getChildren().add(1, newBullet.hitbox);
			}
		}
	}

	// check for collisions
	private void checkCollisions() {
		// check if any enemies are hit
		for (Enemy e : enemies) {
			for (Bullet b : playerBullets) {
				if (e.collide(b)) {
					b.alive = false;
					e.subHitPoints(1);
				}
			}
		}
		// check if player is hit
		for (Bullet b : enemyBullets) {
			if (b.collide(p1)) {
				System.out.println("hit!");
				b.alive = false;
			}
		}
	}

	// clean up Sprites
	private void cleanSprites() {
		// clean player bullets
		int numClean = playerBullets.size();
		for (int i = 0; i < numClean; i++) {
			Bullet b = playerBullets.get(i);
			if (b.alive == false) {
				rootNode.getChildren().remove(b.sprite);
				if (SHOWHITBOX)
					rootNode.getChildren().remove(b.hitbox);
				playerBullets.remove(i);
				numClean--;
				i--;
			}
		}
		// clean enemy bullets
		numClean = enemyBullets.size();
		for (int i = 0; i < numClean; i++) {
			Bullet b = enemyBullets.get(i);
			if (b.alive == false) {
				rootNode.getChildren().remove(b.sprite);
				if (SHOWHITBOX)
					rootNode.getChildren().remove(b.hitbox);
				enemyBullets.remove(i);
				numClean--;
				i--;
			}
		}
		// clean enemies
		numClean = enemies.size();
		for (int i = 0; i < numClean; i++) {
			Enemy e = enemies.get(i);
			if (e.alive == false) {
				rootNode.getChildren().remove(e.sprite);
				if (SHOWHITBOX)
					rootNode.getChildren().remove(e.hitbox);
				enemies.remove(i);
				numClean--;
				i--;
			}
		}
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
		Image enemy = new Image("enemy.png");
		Enemy newEnemy = new Enemy(enemy, 0, 100, 1, 0, 20, 10);
		enemies.add(newEnemy);
		rootNode.getChildren().add(newEnemy.sprite);
		if (SHOWHITBOX)
			rootNode.getChildren().add(newEnemy.hitbox);
	}
}
