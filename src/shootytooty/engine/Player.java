package shootytooty.engine;

import javafx.scene.image.Image;

public class Player extends Sprite {

	static final Image player = new Image("player.png");
	static final double moveSpeed = 2;

	public Player(double X, double Y, double rad) {
		super(player, X, Y, 0, 0, rad);
	}

	public void moveLeft() {
		double newX = hitbox.getCenterX() - moveSpeed;
		moveX(newX);
	}

	public void moveRight() {
		double newX = hitbox.getCenterX() + moveSpeed;
		moveX(newX);
	}

	public void moveUp() {
		double newY = hitbox.getCenterY() - moveSpeed;
		moveY(newY);
	}

	public void moveDown() {
		double newY = hitbox.getCenterY() + moveSpeed;
		moveY(newY);
	}
}
