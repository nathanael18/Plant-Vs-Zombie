package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Plant.Plants;
import Plant.Sunflower;

public class Main extends JFrame{
	
	
	private Dimension maxFrame = getMaximumSize();
	public Main() {
		setSize(maxFrame);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(this);
		add(new MainMenuPanel());
	//	add(new GamePanel());
		//TODO : zombie serANG TANAMAN SEBARIS
		//KLIK BIAR KELIATAN
		//KALO MENANG KE HOME
		setVisible(true);
	}
	public static void main(String[] args) {
		new Main();
	}
}
