package shootytooty.engine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Sprite {

	public ImageView sprite;
	public Circle hitbox;

	public double Xv;
	public double Yv;
	public double Xa;
	public double Ya;
	public boolean alive = true;

	public Sprite(Image img, double X, double Y, double Xv, double Yv,
			double Xa, double Ya, double rad) {
		sprite = new ImageView(img);
		sprite.setX(X - sprite.getImage().getWidth() / 2);
		sprite.setY(Y - sprite.getImage().getHeight() / 2);
		this.Xv = Xv;
		this.Yv = Yv;
		this.Xa = Xa;
		this.Ya = Ya;
		hitbox = new Circle(X, Y, rad, Color.color(1, 0, 0, 0.5));
	}

	public Sprite(Image img, double X, double Y, double Xv, double Yv,
			double rad) {
		this(img, X, Y, Xv, Yv, 0, 0, rad);
	}

	public void update() {
		moveX(hitbox.getCenterX() + Xv);
		moveY(hitbox.getCenterY() + Yv);
		Xv += Xa;
		Yv += Ya;
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

	public void outOfBounds(int WIDTH, int HEIGHT) {
		if (hitbox.getCenterX() + sprite.getImage().getWidth() / 2 < 0)
			alive = false;
		if (hitbox.getCenterX() - sprite.getImage().getWidth() / 2 > WIDTH)
			alive = false;
		if (hitbox.getCenterY() + sprite.getImage().getHeight() / 2 < 0)
			alive = false;
		if (hitbox.getCenterY() - sprite.getImage().getHeight() / 2 > HEIGHT)
			alive = false;
	}
}
