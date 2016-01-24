package shootytooty.engine;

import javafx.scene.image.Image;

public class Bullet extends Sprite {

	static Image bullet = new Image("bullet.png");

	public Bullet(double X, double Y, double Xv, double Yv, double Xa,
			double Ya, double rad) {
		super(bullet, X, Y, Xv, Yv, Xa, Ya, rad);
	}

	public Bullet(double X, double Y, double Xv, double Yv, double rad) {
		this(X, Y, Xv, Yv, 0, 0, rad);
	}
}
