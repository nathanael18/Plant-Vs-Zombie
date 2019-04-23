package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Plant.Bullet;
import Plant.Peashooter;
import Plant.Plants;
import Plant.Sunflower;
import Plant.WalnutWall;
import Zombies.Zombies;

public class GamePanel extends JPanel {	
	private ImageIcon backGroundImage = new ImageIcon("images/field3.jpg");
	private ImageIcon squareFieldImage = new ImageIcon("images/fieldSquare.jpg");
	private ImageIcon sunImage = new ImageIcon("images/sun2.png");
	private ImageIcon zombieWalk = new ImageIcon("images/zombieWalk.jpg");
	private ImageIcon zombieImg = new ImageIcon("images/go_1.png");
	private ImageIcon[] cardImagelist = new ImageIcon[4];
	private ImageIcon loseMessage = new ImageIcon("images/LoseMessage.png");
	private ImageIcon winMessage = new ImageIcon("images/WinMessage.png");
	
	private Dimension maxFrame = getMaximumSize(); //maksimum ukuran frame
	private int currentId = 0; //id tanaman yang dipilih
	private static int sunMoney = 100; //uang kita
	private long timeCounter = 0; //untuk mengatur thread kapan dia hasilkan sun, nembak, dkk
	private int sumofZombies = 10; //jumlah zombie di wave ini 
	//private int wZombie = zombieWalk.getIconWidth()/10, hZombie = zombieWalk.getIconHeight(); //mencoba gambar gerak spt ko harvi
	private int isPlantAttack[] = new int[5];
	private boolean isWin=false;
	
	private CardImage [] cardImage = new CardImage [4]; //list kartu yg dipilih" 
	private Plants [][] plants = new Plants [10][5]; //lapangan kita
	private Rectangle boundField = new Rectangle(260, 130, 895, 580);  //pembatas untuk validasi bahwa posisi kursor di lapangan
	private Rectangle cardField = new Rectangle(345, 5, 405, 100); // pembatas untuk validasi bahwa posisi kursor di tempat milih tanaman

	private Point selectedCoor = null; //ini buat tahu posisi kotak yg terpilih di frame
	private Point hover = null; //buat tahu koordinat si kotak di frame
	private Point cardCoor = null; //dapetin koordinat kartu yg dipilih
	
	private Vector<SunMoneyIcon>vectorSunMoney = new Vector<SunMoneyIcon>(); //arraylist sun
	private Vector<Bullet> vectorAllBullet = new Vector<Bullet>(); // arraylist bullet
	private Vector<Zombies> vectorZombie = new Vector<Zombies>(); // zombie
	
	
//	private MouseMotionListener mouseMotionListener = new MouseMotionAdapter() { //ini mungkin pengembangannya pingin waktu kursor di atas field tertentu dia bisa lebih terang, biar tahu kursor di situ
//		public void mouseMoved(java.awt.event.MouseEvent e) {
//			Point above  = new Point((int)(e.getX()-260)/100, (int)(e.getY()-130)/120);//dapet koordinat si kotak
//			squareCoor = (Point)above.clone(); //copy supaya ngga berubah" pointnya
//			//System.out.println("X " + squareCoor.x + "Y " + squareCoor.y + e.getPoint());
//			if(boundField.contains(above)) { //validasi
//				if(squareCoor != null && plants[squareCoor.x][squareCoor.y].getPlantRec().contains(above)) {//kalo point mouse kita tepat di atas balok
//					hover.x = (int)(plants[squareCoor.x][squareCoor.y].getPlantRec().x);
//					hover.y = (int)( plants[squareCoor.x][squareCoor.y].getPlantRec().y-2);
//				//	repaint();
//				} else {
//					hover = null;
//				}
//			}
//		};
//	}; 
	
	private MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			planting(e);
			selectingCard(e);//untuk mendapatkan id card
			collectSun(e);	
		}
	};
	
	Thread thread = new Thread(()-> {
		while(true) {
			if(isZombieGetIntoHouse()==true||isWin==true) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("gameoverr");
				Main endgame = new Main();
				
				JPanel panel= new MainMenuPanel();
				endgame.remove(panel);
				endgame.add(panel);
				panel.requestFocus();
				endgame.validate();			
				break;
			}
			if(sumofZombies<=0 && vectorZombie.size()<=0 && isZombieGetIntoHouse()==false) {
				System.out.println("You Win!");
			}
			deleteUnused();
			try {
				timeCounter++; //waktu di gamee
				Thread.sleep(60);
				rainningSun();
				summonZombie(timeCounter);
				for(int i=0; i<9; i++) {
					for(int j=0; j<5; j++) {
						if(plants[i][j].getId()!=0) plants[i][j].addLifeTime(1);
						if(plants[i][j].getId()==1) plants[i][j].sunflowerProduceSun(plants, vectorSunMoney, i, j);//kalo dia sunflower produksi matahari
						else if(plants[i][j].getId()==2 && !vectorZombie.isEmpty()) plants[i][j].peashooterShoot(plants, vectorAllBullet, i, j);
						for (Zombies zombie : vectorZombie) { //zombie attack
							if(plants[i][j].getId()==2 && j==zombie.getRandom() && isPlantAttack[j]>0) {
								plants[i][j].setShoot(true);
							} else if(isPlantAttack[j]==0) { 
								plants[i][j].setShoot(false);
							}
							if(plants[i][j].getId()!=0 && zombie.getPostX()<=plants[i][j].getPlantRec().x+20 && j==zombie.getRandom()) {
								zombie.setIsAttack(true);
								plants[i][j].reduceHealth(10);
								Thread.sleep(100);
								if(plants[i][j].getHealth()<=0) {
									plants[i][j].setId(0);
									plants[i][j].setImage(squareFieldImage);
									zombie.setIsAttack(false);
								}
							} 
						}
					}
				}
				for (int k=0; k<vectorAllBullet.size(); k++) {
					vectorAllBullet.get(k).moveBulletCoorX(15);
					vectorAllBullet.get(k).moveBulletRec(vectorAllBullet.get(k).getBulletRectangle(), vectorAllBullet.get(k).getBulletCoorX(), vectorAllBullet.get(k).getBulletCoorY());	
				}
				
				for (Zombies zombie : vectorZombie) {
					if(zombie.getIsAttack()==false && zombie.getLifeTime()%5==0) {
						zombie.move(5);
					}
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}		
	});
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, (int)maxFrame.getWidth(), (int)maxFrame.getHeight());
		g.drawImage(backGroundImage.getImage(), 0, 0, null); //gambar background
		//gambar field
		for(int i=0; i<9; i++) {
			for(int j=0; j<5; j++) {
				if(plants[i][j].getId()==3)g.drawImage(plants[i][j].getImage().getImage(), (i+1)*260+i*(-160), (j+1)*130+j*(-15)-55, 95, 170, null);
				else g.drawImage(plants[i][j].getImage().getImage(), (i+1)*260+i*(-160), (j+1)*130+j*(-15), 95, 120, null);
			//	g.drawRect((i+1)*260+i*(-160), (j+1)*130+j*(-15), 95, 120);
			}
		}
		//gambar card
		g.drawImage(cardImagelist[0].getImage(), 345, 10, 85, 95, null);
		g.drawImage(cardImagelist[1].getImage(), 450, 10, 85, 95, null);
		g.drawImage(cardImagelist[2].getImage(), 555, 10, 85, 95, null);
		g.setColor(Color.YELLOW);
		if(currentId==1) g.drawRect(345, 10, 85, 95);
		else if(currentId==2) g.drawRect(450, 10, 85, 95);
		else if(currentId==3) g.drawRect(555, 10, 85, 95);
		// gambar kotak uang
		g.fillRect(230, 100, 50, 25);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString(String.valueOf(sunMoney), 235, 120);
		
		//ini pinginnya buat gambar zombie tp bug
		if(!vectorZombie.isEmpty()) {
			for (Zombies zombie : vectorZombie) {
				zombie.addLifeTime(1);
					g.drawImage(zombieImg.getImage(), zombie.getPostX(), zombie.getPostY()-50, 120, 175, null);
			}
		}
		//gambar matahari
		if(!vectorSunMoney.isEmpty()) {
			for (SunMoneyIcon sun : vectorSunMoney) {
				g.drawImage(sunImage.getImage(), sun.getCoorX(), sun.getCoorY(), null);
			}
		}
		g.setColor(new Color(0, 128, 0));//buat gambar bullet
		for (Bullet bullet : vectorAllBullet) {
			g.fillOval(bullet.getBulletCoorX(), bullet.getBulletCoorY(), 20, 20);
		}	
		if(isZombieGetIntoHouse())g.drawImage(loseMessage.getImage(), 500, 150, 500, 80, null);
		else if(sumofZombies<=0&&vectorZombie.size()==0&&isZombieGetIntoHouse()==false) {
			g.drawImage(winMessage.getImage(), 500, 150, 500, 80, null);
			isWin=true;
		}
	}
	
	public void init() {
		cardImagelist[0] = new ImageIcon("images/sun_card.png");
		cardImagelist[1] = new ImageIcon("images/shooter_card.png");
		cardImagelist[2] = new ImageIcon("images/walnut_card.png");
		for(int i=0; i<5; i++) isPlantAttack[i]=0;
		for(int i=0; i<9; i++) { //bikin field
			for(int j=0; j<5; j++) {
				plants[i][j]=new Plants() {	};
				plants[i][j].setId(0);
				plants[i][j].setPlantRec(new Rectangle((i+1)*260+i*(-160), (j+1)*130+j*(-15), 95, 120));
			}
		}
		 for(int i=0; i<3;i++) {
			 cardImage[i] = new CardImage (i+1, cardImagelist[i], (i+1)*345+i*(-235), 10, 85, 95);
	        	if(i==3) {
	        	//	cardImage[i].setBounds(672, 8, 65, 60);
	        	}
		 }
	//	addMouseMotionListener(mouseMotionListener);
		addMouseListener(mouseListener);
		setSize(maxFrame);
		setVisible(true);
	}
	
	public void deleteUnused() {
		try { //hapus peluru
			Iterator<Bullet> removeBullet = vectorAllBullet.iterator();
			while (removeBullet.hasNext()) {
				Bullet bullet = (Bullet) removeBullet.next();
				if(bullet.getBulletCoorX()>1380) {
					vectorAllBullet.remove(bullet);	
				} 		
				for (Zombies zombie : vectorZombie) {
					if(zombie.isAlive()==true && ((bullet.getBulletCoorY()-130)/120)==zombie.getRandom() && (bullet.getBulletCoorX()>=zombie.getPostX()) ){
						vectorAllBullet.remove(bullet);	
						if(zombie.getHealth()-10>=0)zombie.reduceHealth(20);
						if(zombie.getHealth()<=0) zombie.setAlive(false);
					}
				}
			}
		} catch(Exception e) {  }
		try { //hapus zombie
			Iterator<Zombies> removeZombie = vectorZombie.iterator();
			while (removeZombie.hasNext()) {
				Zombies zombie = (Zombies) removeZombie.next();
				if(zombie.isAlive()==false) {		
					isPlantAttack[zombie.getRandom()]--;
					System.out.println("Zombie mati");
					for(int i=0; i<5; i++) System.out.println(isPlantAttack[i]);
					vectorZombie.remove(zombie);	
				}
			}
		} catch(Exception e) {	}
		try { //hapus sun
			Iterator<SunMoneyIcon> removeSun = vectorSunMoney.iterator();
			while (removeSun.hasNext()) {
				SunMoneyIcon sunss = (SunMoneyIcon) removeSun.next();
				if(sunss.getWastedTime()<=0||sunss.isCollected()==true) {
					vectorSunMoney.remove(sunss);
				}
			}
		} catch(Exception e) {	}
	}
	
	public void planting(MouseEvent e) {
		if(boundField.contains(e.getPoint())) { //kalo kursor kita di field
			selectedCoor= new Point((int)(e.getX()-260)/100, (int)(e.getY()-130)/120);
			if(selectedCoor != null && plants[selectedCoor.x][selectedCoor.y].getPlantRec().contains(e.getPoint())&&currentId!=0) {//kalo point mouse kita tepat di atas balok
				hover = new Point((selectedCoor.x+1)*260+selectedCoor.x*(-160), (selectedCoor.y+1)*130+selectedCoor.y*(-15)); //dapetin titik gambar yg mau diupdate, titik paling kiri atas
				if(plants[selectedCoor.x][selectedCoor.y].getId()==0&&plants[selectedCoor.x][selectedCoor.y].isPlanted()==false) {
					System.out.println("ID Update! " + plants[selectedCoor.x][selectedCoor.y].getId());
					setPlantId(selectedCoor);
				}
				repaint();
				currentId=0;
			} else {
				hover = null;
			}
		}
	}
	
	public void selectingCard(MouseEvent e) {
		cardCoor = new Point((int)((e.getX()-345)/100), (int)((e.getY()-5)/100));
		if(cardField.contains(e.getPoint())) {
			if(cardCoor!=null && cardImage[cardCoor.x].getCardRec().contains(e.getPoint()) && currentId==cardImage[cardCoor.x].getId()) {
				currentId = 0;
				System.out.println("ID jadi 0");
			} else	if(cardCoor!=null && cardImage[cardCoor.x].getCardRec().contains(e.getPoint())) {
				currentId = cardImage[cardCoor.x].getId();
				System.out.println("ID : " + currentId);
			}
		}
	}
	
	public void collectSun(MouseEvent e) {
		Point sunPoint = new Point(e.getPoint());
		for (SunMoneyIcon sunsss : vectorSunMoney) {
			if(sunsss.getSunRec().contains(sunPoint)) {
				sunsss.setCollected(true);
				sunMoney+=25;
			}
		}
	}
	
	public void summonZombie(long timeCounter) {
		if(timeCounter%125==0 && sumofZombies>=0) {// summon zombie
			Zombies zombie = new Zombies();
			vectorZombie.add(zombie);
			sumofZombies--;
			isPlantAttack[zombie.getRandom()]++;
		}
		
	}
	 
	public void rainningSun() { //hasilkan sun yg jatuh" 
		if(timeCounter%200==0) {
			vectorSunMoney.add(new SunMoneyIcon());
		} 
		for (int i=0; i<vectorSunMoney.size(); i++) {// Sunflower hasilkan menghasilkan sunMoney
			vectorSunMoney.get(i).setWastedTime(1);
			for(int k=0; k<vectorSunMoney.size(); k++) {
				if(vectorSunMoney.get(k).getCoorY()+5<vectorSunMoney.get(k).getMaxCoor()&&timeCounter%2==0) {
					vectorSunMoney.get(k).setCoorY(4);
					vectorSunMoney.get(k).moveDown(4);
				}
			}
		}
	}
	
	public boolean isZombieGetIntoHouse() {
		boolean flag = false;
		for (Zombies zombie : vectorZombie) {
			if(zombie.getPostX()<=100) {
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	public void setPlantId(Point selectedCoor) {
		if(currentId==1&&sunMoney>=50) {
			plants[selectedCoor.x][selectedCoor.y].setPlanted(true);
			plants[selectedCoor.x][selectedCoor.y].setId(1);
			plants[selectedCoor.x][selectedCoor.y] = (Sunflower) new Sunflower(hover.x, hover.y, 95, 120);
			sunMoney-=50;
		}
		else if(currentId==2&&sunMoney>=100) {
			plants[selectedCoor.x][selectedCoor.y].setPlanted(true);
			plants[selectedCoor.x][selectedCoor.y].setId(2);
			plants[selectedCoor.x][selectedCoor.y] = (Peashooter) new Peashooter(hover.x, hover.y, 95, 120);
			sunMoney-=100;
		}
		else if(currentId==3&&sunMoney>=50) {
			plants[selectedCoor.x][selectedCoor.y].setPlanted(true);
			plants[selectedCoor.x][selectedCoor.y].setId(3);
			plants[selectedCoor.x][selectedCoor.y] = (WalnutWall) new WalnutWall(hover.x, hover.y, 95, 120);
			sunMoney-=50;
		}
	}
	
	public GamePanel() {
		init();
		thread.start();
	}

}
