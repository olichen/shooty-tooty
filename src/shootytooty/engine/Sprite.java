package shootytooty.engine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Sprite {

	public ImageView sprite;
	public Circle hitbox;
	public int timer = 0;
	private int hitpoints;

	private double Xv;
	private double Yv;
	private boolean alive = true;

	public Sprite(Image img, int hitpoints, double X, double Y, double Xv,
			double Yv, double rad) {
		sprite = new ImageView(img);
		sprite.setX(X - sprite.getImage().getWidth() / 2);
		sprite.setY(Y - sprite.getImage().getHeight() / 2);
		this.hitpoints = hitpoints;
		this.Xv = Xv;
		this.Yv = Yv;
		hitbox = new Circle(X, Y, rad, Color.color(1, 0, 0, 0.5));
	}

	public void update() {
		moveX(hitbox.getCenterX() + Xv);
		moveY(hitbox.getCenterY() + Yv);
		timer++;
	}

	public void moveX(double newX) {
		hitbox.setCenterX(newX);
		sprite.setX(newX - sprite.getImage().getWidth() / 2);
	}

	public void moveY(double newY) {
		hitbox.setCenterY(newY);
		sprite.setY(newY - sprite.getImage().getHeight() / 2);
	}

	public boolean collide(Sprite other) {
		double dx = other.hitbox.getCenterX() - hitbox.getCenterX();
		double dy = other.hitbox.getCenterY() - hitbox.getCenterY();
		double dist = Math.sqrt(dx * dx + dy * dy);
		double raddist = other.hitbox.getRadius() + hitbox.getRadius();
		return (dist < raddist);
	}

	public void outOfBounds(int width, int height) {
		if (hitbox.getCenterX() + sprite.getImage().getWidth() / 2 < 0)
			alive = false;
		if (hitbox.getCenterX() - sprite.getImage().getWidth() / 2 > width)
			alive = false;
		if (hitbox.getCenterY() + sprite.getImage().getHeight() / 2 < 0)
			alive = false;
		if (hitbox.getCenterY() - sprite.getImage().getHeight() / 2 > height)
			alive = false;
	}

	public void setDead() {
		alive = false;
	}

	public boolean isAlive() {
		return alive;
	}

	public void subHitpoints(int i) {
		hitpoints -= i;
		if (hitpoints <= 0)
			setDead();
	}

	public int getHitpoints() {
		return hitpoints;
	}
}
