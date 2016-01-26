package shootytooty.engine.enemy;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import shootytooty.engine.DebugVars;
import shootytooty.engine.bullets.BulletManager;

public class EnemyManager {

	private static Group rootNode;
	private final List<Enemy> enemyList = new ArrayList<>();

	public EnemyManager() {
	}

	public static void setRootNode(Group rootNode) {
		EnemyManager.rootNode = rootNode;
	}

	public void createEnemy(double X, double Y, double Xv, double Yv, double rad) {
		Image enemy = new Image("enemy.png");
		Enemy newEnemy = new Enemy(enemy, 5, X, Y, Xv, Yv, rad);
		enemyList.add(newEnemy);
		rootNode.getChildren().add(0, newEnemy.sprite);
		if (DebugVars.SHOWHITBOX)
			rootNode.getChildren().add(1, newEnemy.hitbox);
	}

	public void update() {
		for (Enemy e : enemyList)
			e.update();
	}

	public void outOfBounds(int width, int height) {
		for (Enemy e : enemyList)
			e.outOfBounds(width, height);
	}

	public void checkCollisions(BulletManager bm) {
		for (Enemy e : enemyList) {
			bm.checkCollisions(e);
		}
	}

	public void clean() {
		int numEnemies = enemyList.size();
		for (int i = 0; i < numEnemies; i++) {
			Enemy e = enemyList.get(i);
			if (e.isAlive() == false) {
				rootNode.getChildren().remove(e.sprite);
				if (DebugVars.SHOWHITBOX)
					rootNode.getChildren().remove(e.hitbox);
				enemyList.remove(i);
				numEnemies--;
				i--;
			}
		}
	}

}
