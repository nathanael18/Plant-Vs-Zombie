package Plant;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class WalnutWall extends Plants {
	
	private ImageIcon walnutWall = new ImageIcon("images/walnut.png");
	
	public WalnutWall(int health, int cost, int damage, String name, int id, double cooldown, ImageIcon image) {
		health = 750;
		cost = 50;
		damage = 0;
		name = "Walnut Wall";
		id = 3;
		cooldown = 0;
		image  = walnutWall;
	}
	public WalnutWall(int x, int y, int width, int height) {
		super.setPlantRec(new Rectangle(x, y, width, height));
		super.setHealth(1000);
		super.setCost(50);
		super.setSunPoints(0);
		super.setSpeed(0);
		super.setDamage(0);
		super.setName("wallnutWall");;
		super.setId(3);
		super.setImage(walnutWall);
	}
	
//	@Override
//	public Plants getPlantType(int id) {
//		Plants plant = null;
//		if (id == 3)
//			plant = (WalnutWall) new WalnutWall(health, cost, damage, name, id, cooldown, image);
//		return plant;
//	}
}
