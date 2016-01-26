package shootytooty.engine.enemy;

import shootytooty.engine.Sprite;
import javafx.scene.image.Image;

public class Enemy extends Sprite {

	private boolean firing = false;

	public Enemy(Image enemy, int hitpoints, double X, double Y, double Xv, double Yv,
			double rad) {
		super(enemy, hitpoints, X, Y, Xv, Yv, rad);
	}

	public Enemy(Image enemy, int hitpoints, double X, double Y, double rad) {
		this(enemy, hitpoints, X, Y, 0, 0, rad);
	}

	public void update() {
		super.update();
		if (timer % 10 == 0)
			firing = true;
		if (timer % 10 == 1)
			firing = false;
	}

	public boolean isFiring() {
		return firing;
	}
}