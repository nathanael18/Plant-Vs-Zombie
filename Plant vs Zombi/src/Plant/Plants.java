package Plant;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Control.War;
import gui.SunMoneyIcon;

public abstract class Plants implements War{

	// private String stringtype;
	private int health = 100;
	private int cost = 100;
	private int sunPoints = 0;
	private int speed = 1;
	private int damage = 0;

	private String name;
	private int id = 0;
	private long startLive = 0;

	private ImageIcon image;
	private Rectangle plantRec = new Rectangle(0, 0, 0, 0);
	private boolean isShoot = false;
	private boolean isPlanted = false;
	private Vector<SunMoneyIcon> vectorThisSun = new Vector<SunMoneyIcon>();
	//private ArrayList<Bullet> bulletList = new ArrayList<>();

	public Plants() {
		// this.stringtype = "";
		this.health = 100;
		this.cost = 100;
		this.sunPoints = 0;
		this.speed = 1;
		this.damage = 0;
		this.name = "";
		this.id = 0;
		this.image = new ImageIcon("images/FieldSquare.jpg");
		this.plantRec = new Rectangle(0, 0, 0, 0);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int damage) {
		this.health = damage;
	}
	
	public void reduceHealth(int damage) {
		this.health -= damage;
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

	public long getStartLive() {
		return startLive;
	}

	public void addLifeTime(int lifeTime) {
		this.startLive += lifeTime;
	}

	public Rectangle getPlantRec() {
		return plantRec;
	}

	public void setPlantRec(Rectangle plantRec) {
		this.plantRec = plantRec;
	}

	public Vector<SunMoneyIcon> getVectorThisSun() {
		return vectorThisSun;
	}

	public boolean isPlanted() {
		return isPlanted;
	}

	public void setPlanted(boolean isPlanted) {
		this.isPlanted = isPlanted;
	}

	public void damageDealt(int hitpoints) {
		int reducedHealth = this.health - hitpoints;
		setHealth(reducedHealth);
	}

	public void attacked(int damage) {
		this.health = this.health - damage;
	}
	
	public boolean isShoot() {
		return isShoot;
	}

	public void setShoot(boolean isShoot) {
		this.isShoot = isShoot;
	}
	
	public boolean canPurchase(Plants a, int sunMoney) {
		boolean flag = false;
		if(sunMoney>=a.cost) flag =true;
		return flag;
	}

	public void sunflowerProduceSun(Plants [][] plants, Vector<SunMoneyIcon> vectorSunMoney, int i, int j) { }
	
	public void peashooterShoot(Plants [][] plants, Vector<Bullet> vectorAllBullet, int i, int j) {	}
}
