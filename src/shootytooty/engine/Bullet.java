package shootytooty.engine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet {

	public Circle bullet;
	
	public double Xv;
	public double Yv;
	public double Xa;
	public double Ya;
	public boolean alive = true;

	public Bullet(double Xin, double Yin, double Xvin, double Yvin, double Xain, double Yain, double radin) {
		Xv = Xvin;
		Yv = Yvin;
		Xa = Xain;
		Ya = Yain;
		bullet = new Circle(Xin, Yin, radin);
		bullet.setFill(Color.RED);
	}
	public Bullet(double Xin, double Yin, double Xvin, double Yvin, double radin) {
		Xv = Xvin;
		Yv = Yvin;
		Xa = 0;
		Ya = 0;
		bullet = new Circle(Xin, Yin, radin);
		bullet.setFill(Color.RED);
	}
	
	public void update() {
		bullet.setCenterX(bullet.getCenterX()+Xv);
		bullet.setCenterY(bullet.getCenterY()+Yv);
		Xv+=Xa;
		Yv+=Ya;
	}
}
