package Plant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Bullet extends JPanel {
	
	private int bulletCoorX = super.getWidth();
	private int bulletCoorY = super.getHeight() / 2;
	private int id = 0;
	private Rectangle bulletRectangle = new Rectangle(0, 0, 0, 0);
	private final int speed = 1;
	
	public Bullet() {
		
	}
	
	public Bullet(int x, int y) {
		this.bulletCoorX = x;
		this.bulletCoorY = y;
		bulletRectangle = new Rectangle(bulletCoorX, bulletCoorY, 20, 20);
	}
	
	public int getBulletCoorX() {
		return bulletCoorX;
	}

	public void moveBulletCoorX(int bulletCoorX) {
		this.bulletCoorX += bulletCoorX;
	}

	public int getBulletCoorY() {
		return bulletCoorY;
	}

	public void BulletCoorY(int bulletCoorY) {
		this.bulletCoorY = bulletCoorY;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rectangle getBulletRectangle() {
		return bulletRectangle;
	}

	public void moveBulletRec (Rectangle a, int x, int y) {
		a.move(x, y);
	}

	public void drawBullet(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(bulletCoorX, bulletCoorY, 10, 10);
	}
}
