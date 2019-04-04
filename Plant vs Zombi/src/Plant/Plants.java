package Plant;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class Plants {

	//private String stringtype;
	private int health = 100;
	private int cost = 100;
	private int sunPoints = 0;
	private int speed = 1;
	private int damage = 0;
	private String name;
	private int id;

	private int cooldown = 2;
	private JButton button;
	private ImageIcon image;

	public Plants() {
	//	this.stringtype = "";
		this.health = 100;
		this.cost = 100;
		this.sunPoints = 0;
		this.speed =1;
		this.damage = 0;
		this.name = "";
		this.id = 0;
		this.cooldown = 2;
		this.image = new ImageIcon("images/FieldSquare.jpg");
	}

	public Plants(int cost, int health) {
		this.cost = cost;
		this.health = 100;
	}

//	public String getStringtype() {
//		return stringtype;
//	}

//	public void setStringtype(String stringtype) {
//		this.stringtype = stringtype;
//	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getSunPoints() {
		return sunPoints;
	}

	public void setSunPoints(int sunPoints) {
		this.sunPoints = sunPoints;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = new ImageIcon(image.getImage());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void damageDealt(int hitpoints) {
		int reducedHealth = this.health - hitpoints;
		setHealth(reducedHealth);
	}

	public void attacked(int damage) {
		this.health = this.health - damage;

	}

	public boolean ismovable(int turns, int speed) {
		if (turns == 0) {
			return false;
		}
		if ((turns % speed) == 0)
			return true;
		else
			return false;
	}

	public void setButton(int x, int y, JButton[][] buttonField, Plants purchasedPlant) {
		buttonField[y][x] = purchasedPlant.button;
		buttonField[y][x].imageUpdate(purchasedPlant.image.getImage(), 1, buttonField[y][x].getX(),
				buttonField[y][x].getY(), buttonField[y][x].getWidth(), buttonField[y][x].getHeight());
	}

	public Plants getPlantType(int id) {
		Plants plant = null;
		if(id==1) plant = new Sunflower();
		return plant;
	}
}
