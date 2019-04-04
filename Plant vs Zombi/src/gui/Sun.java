package gui;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Control.Helper;

public class Sun extends JButton{
	int value = 25;
	int idx=0;
	int coorX = 0;
	int coorY = 0;
	ImageIcon sunImage = new ImageIcon("images/sun.png");
	
	public Sun() {
		// TODO Auto-generated constructor stub
	}
	public int collectSun(Vector<Sun> suns, int moneySun) {
		suns.remove(idx);
		moneySun+=this.value;
		return this.value;
	}
	public Sun generateSun(Vector<Sun> suns, int max) {
		Sun sun = new Sun();
		coorX = (int)Helper.rand(max, 50);
		sun.setLocation(coorX, coorY);
		return sun;
	}
}
