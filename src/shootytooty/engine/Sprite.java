package shootytooty.engine;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

public abstract class Sprite {

	public Node node;
	public Circle hitbox;
	
	public double Xv;
	public double Yv;
	public double Xa;
	public double Ya;
	public boolean alive = true;

	public boolean collide(Sprite other) {
		double dx = other.hitbox.getCenterX() - hitbox.getCenterX();
		double dy = other.hitbox.getCenterY() - hitbox.getCenterY();
		double dist = Math.sqrt(dx * dx + dy * dy);
		double raddist = other.hitbox.getRadius() + hitbox.getRadius();
		return (dist < raddist);
	}
}
