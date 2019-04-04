package Plant;

import javax.swing.ImageIcon;

public class Peashooter extends Plants {
	ImageIcon peashooterImage = new ImageIcon("images/peashooter.png");
	public Peashooter() {
		super.setHealth(100);
		super.setCost(50);
		super.setSunPoints(25);
		super.setSpeed(1);
		super.setDamage(0);;
		super.setName("Peashooter");;
		super.setId(1);
		super.setImage(peashooterImage);
	}

}
