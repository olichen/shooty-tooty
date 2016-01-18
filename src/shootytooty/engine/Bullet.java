package shootytooty.engine;

import javafx.scene.image.Image;

public class Bullet extends Sprite {

	static Image bullet = new Image("bullet.png");

	public Bullet(double X, double Y, double Xv, double Yv, double Xa,
			double Ya, double rad) {
		super(bullet, X, Y, Xv, Yv, Xa, Ya, rad);
	}

	public Bullet(double X, double Y, double Xv, double Yv, double rad) {
		super(bullet, X, Y, Xv, Yv, rad);
	}

	public boolean outOfBounds(int WIDTH, int HEIGHT) {
		if (hitbox.getCenterX() + hitbox.getRadius() < 0)
			return true;
		if (hitbox.getCenterX() - hitbox.getRadius() > WIDTH)
			return true;
		if (hitbox.getCenterY() + hitbox.getRadius() < 0)
			return true;
		if (hitbox.getCenterY() - hitbox.getRadius() > HEIGHT)
			return true;
		return false;
	}
}
