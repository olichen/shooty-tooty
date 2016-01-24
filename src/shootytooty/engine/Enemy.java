package shootytooty.engine;

import javafx.scene.image.Image;

public class Enemy extends Sprite {

	private int hitpoints;
	private boolean firing = false;
	private int timer = 10;
	private final static int RESETTIMER = 10;

	public Enemy(Image enemy, double X, double Y, double Xv, double Yv,
			double Xa, double Ya, double rad, int hitpoints) {
		super(enemy, X, Y, Xv, Yv, Xa, Ya, rad);
		this.hitpoints = hitpoints;
	}

	public Enemy(Image enemy, double X, double Y, double Xv, double Yv,
			double rad, int hitpoints) {
		this(enemy, X, Y, Xv, Yv, 0, 0, rad, hitpoints);
	}

	public Enemy(Image enemy, double X, double Y, double rad, int hitpoints) {
		this(enemy, X, Y, 0, 0, rad, hitpoints);
	}

	public void update() {
		super.update();
		if (timer == RESETTIMER)
			firing = false;
		if (timer-- == 0) {
			firing = true;
			timer = RESETTIMER;
		}
	}

	public void subHitPoints(int i) {
		hitpoints -= i;
		if (hitpoints <= 0)
			alive = false;
	}

	public boolean isFiring() {
		return firing;
	}
}