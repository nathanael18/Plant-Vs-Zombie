package Plant;

import javax.swing.ImageIcon;

public class Sunflower extends Plants {
	ImageIcon sunflower = new ImageIcon("images/sunFlower3.png");
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
		super(cost, health);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Plants getPlantType(int id) {
		Plants plant = new Plants() {};
		if(id==1) plant = (Sunflower) new Sunflower();
		return plant;
	}
}
