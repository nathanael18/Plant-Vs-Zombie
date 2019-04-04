package gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CardButton extends JButton{
	private int id;
	private ImageIcon cardImage = new ImageIcon();
	
	public CardButton() {
		this.id = 0;
	}
	public CardButton(int id) {
		this.id = id;	
	}
	
	public CardButton(int id, ImageIcon image) {
		this.id = id;
		this.cardImage = new ImageIcon(image.getImage());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
