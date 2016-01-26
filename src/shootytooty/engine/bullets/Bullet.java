package shootytooty.engine.bullets;

import shootytooty.engine.Sprite;
import javafx.scene.image.Image;

public class Bullet extends Sprite {

	static Image bullet = new Image("bullet.png");

	public Bullet(int hitpoints, double X, double Y, double Xv, double Yv, double rad) {
		super(bullet, hitpoints, X, Y, Xv, Yv, rad);
	}
}
