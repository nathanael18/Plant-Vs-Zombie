package gui;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class CardImage extends ImageIcon{
	private int id = 0;
	private ImageIcon cardImage = new ImageIcon();
	private Rectangle cardRec =  new Rectangle(0, 0, 0, 0);
	
	public ImageIcon getCardImage() {
		return cardImage;
	}
	public void setCardImage(ImageIcon cardImage) {
		this.cardImage = cardImage;
	}
	public Rectangle getCardRec() {
		return cardRec;
	}
	public void setCardRec(Rectangle cardRec) {
		this.cardRec = cardRec;
	}
	public CardImage() {
		// TODO Auto-generated constructor stub
	}
	public CardImage(int id, ImageIcon image, int x, int y, int width, int height) {
		this.id = id;
		this.cardImage = image;
		this.cardRec = new Rectangle(x,y,width, height);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
