package shootytooty.engine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public abstract class Sprite {

	public ImageView sprite;
	public Circle hitbox;

	public double Xv;
	public double Yv;
	public double Xa;
	public double Ya;
	public boolean alive = true;

	public Sprite(Image img, double Xin, double Yin, double Xvin, double Yvin,
			double Xain, double Yain, double radin) {
		sprite = new ImageView(img);
		sprite.setX(Xin-sprite.getImage().getWidth()/2);
		sprite.setY(Yin-sprite.getImage().getHeight()/2);
		Xv = Xvin;
		Yv = Yvin;
		Xa = Xain;
		Ya = Yain;
		hitbox = new Circle(Xin, Yin, radin);
	}

	public Sprite(Image img, double Xin, double Yin, double Xvin, double Yvin, double radin) {
		sprite = new ImageView(img);
		sprite.setX(Xin-sprite.getImage().getWidth()/2);
		sprite.setY(Yin-sprite.getImage().getHeight()/2);
		Xv = Xvin;
		Yv = Yvin;
		Xa = 0;
		Ya = 0;
		hitbox = new Circle(Xin, Yin, radin);
	}

	public void update() {
		moveX(hitbox.getCenterX() + Xv);
		moveY(hitbox.getCenterY() + Yv);
		Xv += Xa;
		Yv += Ya;
	}
	
	public void moveX(double newX) {
		hitbox.setCenterX(newX);
		sprite.setX(newX-sprite.getImage().getWidth()/2);
	}
	public void moveY(double newY) {
		hitbox.setCenterY(newY);
		sprite.setY(newY-sprite.getImage().getHeight()/2);
	}

	public boolean collide(Sprite other) {
		double dx = other.hitbox.getCenterX() - hitbox.getCenterX();
		double dy = other.hitbox.getCenterY() - hitbox.getCenterY();
		double dist = Math.sqrt(dx * dx + dy * dy);
		double raddist = other.hitbox.getRadius() + hitbox.getRadius();
		return (dist < raddist);
	}
}
