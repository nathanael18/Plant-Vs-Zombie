package Plant;

import javax.swing.ImageIcon;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Vector;

public class Peashooter extends Plants {
	private ImageIcon peashooterImage = new ImageIcon("images/peashooter.png");
	private int width = peashooterImage.getIconWidth();
	private int height = peashooterImage.getIconHeight();

	public Peashooter() {
		super();
		super.setHealth(100);
		super.setCost(100);
		super.setSunPoints(25);
		super.setSpeed(1);
		super.setDamage(20);
		super.setName("Peashooter");;
		super.setId(2);
		super.setImage(peashooterImage);
	}
	public Peashooter(int x, int y, int width, int height) {
		super.setPlantRec(new Rectangle(x, y, width, height));
		super.setHealth(100);
		super.setCost(100);
		super.setSunPoints(25);
		super.setSpeed(1);
		super.setDamage(10);;
		super.setName("Peashooter");;
		super.setId(2);
		super.setImage(peashooterImage);
	}
	public Peashooter(int health, int cost, int damage, String name, int id, double cooldown, ImageIcon image) {
		super.setHealth(health);
		super.setCost(cost);
		super.setSunPoints(0);
		super.setSpeed(1);
		super.setDamage(damage);
		super.setName("Peashooter");;
		super.setId(2);
		super.setImage(image);
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public ImageIcon getPeashooterImage() {
		return peashooterImage;
	}

	@Override
	public void peashooterShoot(Plants[][] plants, Vector<Bullet> vectorAllBullet, int i, int j) {
		if(plants[i][j].isShoot()==true&&plants[i][j].getStartLive()%50==0) vectorAllBullet.add(new Bullet((int)(plants[i][j].getPlantRec().getX()+90), (int)plants[i][j].getPlantRec().getY()+30));
	}

//	public void shoot(int interval, int x, int y) {
//		Bullet bullet = new Bullet(x, y);
//		bulletList.add(bullet);
//		//if(peashooter.interval)
//		//else if(!zombie)
//	}

//		@Override
//		public Plants getPlantType(int id) {
//			Plants plant = null;
//			if (id == 2)
//				plant = (Peashooter) new Peashooter(health, cost, damage, name, id, cooldown, image);
//			return plant;
//		}
//	}

}
