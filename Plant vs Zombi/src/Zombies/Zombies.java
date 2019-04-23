package Zombies;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Plant.Plants;
import Control.Helper;
import Control.War;

public class Zombies implements Control.War{
	private String name;
	private int postX = 1366;
	private int postY;
	private int random;
	private Integer health;
	private Integer damage;
	private Integer speed;
	private boolean isAlive;
	private boolean canMove;
	
	private long lifeTime = 0;
	private boolean isShot;
	private boolean isAttack;
	private ImageIcon image = new ImageIcon("");
	private Rectangle zombieRec = new Rectangle(0, 0, 0, 0); //kotak buat validasi apa dia ketembak
	

	private Rectangle zombieFrontRec = new Rectangle(0,0,30,20); // kotak buat validasi apakah dia akan menyerang, letaknya di depan zombie
		
	public void reduceHealth(int damage) {
		this.health-=damage;
	}
	
	public void doDamage(Plants p) {
		p.setHealth(p.getHealth()-10);
	}

	public String getName() {
		return name;
	}

	public int getPostX() {
		return postX;
	}

	public int getPostY() {
		return postY;
	}

	public Integer getHealth() {
		return health;
	}

	public Integer getDamage() {
		return damage;
	}

	public Integer getSpeed() {
		return speed;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isCanMove() {
		return canMove;
	}

	public boolean isShot() {
		return isShot;
	}

	public boolean getIsAttack() {
		return isAttack;
	}

	public void move(int move) {
		this.postX -= move;
	}

	public void setPostY(int postY) {
		this.postY = postY;
	}


	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public void setShot(boolean isShot) {
		this.isShot = isShot;
	}

	public void setIsAttack(boolean canAttack) {
		this.isAttack = canAttack;
	}
	public Rectangle getZombieRec() {
		return zombieRec;
	}

	public void setZombieRec(Rectangle zombieRec) {
		this.zombieRec = zombieRec;
	}

	public Rectangle getZombieFrontRec() {
		return zombieFrontRec;
	}

	public int getRandom() {
		return random;
	}
	public long getLifeTime() {
		return lifeTime;
	}

	public void addLifeTime(int lifeTime) {
		this.lifeTime += lifeTime;
	}
	public void resetLifeTime() {
		this.lifeTime=0;
	}

	public void setZombieFrontRec(Rectangle zombieFrontRec) {
		this.zombieFrontRec = zombieFrontRec;
	}
	public Zombies() {
		this.random = (int)Control.Helper.rand(4,0);
		this.name = "ZOMBIE";
		this.health = 100;
		this.damage = 10;
		this.speed = 50;
		this.postX = 1366;
		this.postY = (random+1)*130+random*(-15);
		this.isAlive = true;
		this.canMove = true;
		this.isShot = false;
		this.isAttack = false;
		this.zombieRec = new Rectangle(postX, postY, 95, 120);
	}
	
	public void attackPlant(Plants plant) {
		if(this.lifeTime%50==0)plant.setHealth(10);
	}
	
}
