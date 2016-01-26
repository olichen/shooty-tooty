package shootytooty.engine.bullets;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import shootytooty.engine.DebugVars;
import shootytooty.engine.Sprite;

public class BulletManager {

	private static Group rootNode;
	private final List<Bullet> bulletList = new ArrayList<>();

	public BulletManager() {
	}

	public void setRootNode(Group rootNode) {
		BulletManager.rootNode = rootNode;
	}

	public void createBullet(double X, double Y, double Xv, double Yv,
			double rad) {
		Bullet newBullet = new Bullet(1, X, Y, Xv, Yv, rad);
		bulletList.add(newBullet);
		rootNode.getChildren().add(0, newBullet.sprite);
		if (DebugVars.SHOWHITBOX)
			rootNode.getChildren().add(1, newBullet.hitbox);
	}

	public void update() {
		for (Bullet b : bulletList)
			b.update();
	}

	public void outOfBounds(int width, int height) {
		for (Bullet b : bulletList)
			b.outOfBounds(width, height);
	}

	public int checkCollisions(Sprite s) {
		int damage = 0;
		for (Bullet b : bulletList) {
			if (b.collide(s)) {
				b.setDead();
				damage += b.getHitpoints();
			}
		}
		return damage;
	}

	public void clean() {
		int numBullets = bulletList.size();
		for (int i = 0; i < numBullets; i++) {
			Bullet b = bulletList.get(i);
			if (b.isAlive() == false) {
				rootNode.getChildren().remove(b.sprite);
				if (DebugVars.SHOWHITBOX)
					rootNode.getChildren().remove(b.hitbox);
				bulletList.remove(i);
				numBullets--;
				i--;
			}
		}
	}
}
