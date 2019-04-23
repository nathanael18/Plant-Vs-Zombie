package gui;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Control.Helper;

public class SunMoneyIcon {
	private int value = 25;
	private int idx=0;
	private int coorX = 0;
	private int coorY = 0;
	private int maxCoor = 0;
	private int wastedTime = 250;
	private boolean isCollected = false;

	private ImageIcon sunImage = new ImageIcon("images/sun2.png");
	private Rectangle sunRec = new Rectangle(0, 0, 0, 0); 

	public SunMoneyIcon() {
		int random = (int)Helper.rand(1300, 50);
		this.coorX = random;
		random = (int)Helper.rand(600, 100);
		this.maxCoor = random;
		this.value = 25;
		this.sunRec = new Rectangle(this.coorX, this.coorY, 80, 80);
	}
	
	public SunMoneyIcon(int x, int y, int width, int height) {
		this.value=25;
		this.coorX= x;
		this.coorY = y;
		this.maxCoor = y;
		this.sunRec=new Rectangle(x, y, width, height);
	}
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getCoorX() {
		return coorX;
	}

	public void setCoorX(int coorX) {
		this.coorX = coorX;
	}

	public int getCoorY() {
		return coorY;
	}

	public void setCoorY(int coorY) {
		this.coorY += coorY;
	}

	public int getMaxCoor() {
		return maxCoor;
	}

	public void setMaxCoor(int maxCoor) {
		this.maxCoor = maxCoor;
	}

	public Rectangle getSunRec() {
		return sunRec;
	}

	public void setSunRec(Rectangle sunRec) {
		this.sunRec = sunRec;
	}
	
	public int getWastedTime() {
		return wastedTime;
	}

	public void setWastedTime(int time) {
		this.wastedTime -= time;
	}
	
	public int getValue() {
		return value;
	}

	public ImageIcon getSunImage() {
		return sunImage;
	}

	public boolean isCollected() {
		return isCollected;
	}

	public void setCollected(boolean isCollected) {
		this.isCollected = isCollected;
	}

	public void moveDown(int move) {
		this.sunRec.y+=move;
		this.sunRec.x=coorX;
	}
	
//	public void wasted(Vector<SunMoneyIcon>suns) {
//		suns.remove(this.idx);
//	}
	
}
