package Plant;

import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.ImageIcon;

import gui.SunMoneyIcon;

public class Sunflower extends Plants {
	private ImageIcon sunflower = new ImageIcon("images/sunFlower3.png");
	public Sunflower() {
		super.setHealth(100);
		super.setCost(50);
		super.setSunPoints(25);
		super.setSpeed(1);
		super.setDamage(0);;
		super.setName("Sunflower");;
		super.setId(1);
		super.setImage(sunflower);
	}

	public Sunflower(int cost, int health) {
		
		// TODO Auto-generated constructor stub
	}
	public Sunflower(int x, int y, int width, int height) {
		super.setHealth(100);
		super.setCost(50);
		super.setSunPoints(25);
		super.setSpeed(1);
		super.setDamage(0);;
		super.setName("Sunflower");;
		super.setId(1);
		super.setImage(sunflower);
		super.setPlantRec(new Rectangle(x, y, width, height));
	}

	@Override
	public boolean canPurchase(Plants a, int sunMoney) {
			boolean flag = false;
			if(sunMoney>=a.getCost()) flag =true;
			return flag;	
	}
	@Override
	public void sunflowerProduceSun(Plants [][] plants, Vector<SunMoneyIcon> vectorSunMoney, int i, int j) {
		if(plants[i][j].getStartLive()%175==0) vectorSunMoney.add(new SunMoneyIcon((int)(plants[i][j].getPlantRec().getX()), (int)plants[i][j].getPlantRec().getY(), 80, 80));
	}
	
}
