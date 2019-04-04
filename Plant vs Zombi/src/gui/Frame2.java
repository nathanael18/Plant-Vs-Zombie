package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Plant.Plants;
import Plant.Sunflower;

public class Frame2 extends JFrame {
	
	int currentId = 0;
	int sunMoney = 100;
	
	int buttonX=0; //button yg mana
	int buttonY=0;
	
	//Point pointButton = new Point(0, 0); 
	
	boolean isWin = false;
	
	Dimension maxFrame = getMaximumSize();
	JPanel panel = new JPanel();
	JButton[][] buttonsField  = new JButton[9][5];
//	JTextField moneyField = new JTextField(sunMoney);
//	JButton[] cardList = new JButton[5];
	CardButton[] cardList = new CardButton[4];
	Vector<Sun> suns = new Vector<Sun>();
	Rectangle rec = new Rectangle();
	
	ImageIcon field = new ImageIcon("images/Field3.jpg");
	ImageIcon squareField = new ImageIcon("images/FieldSquare.jpg");
	ImageIcon sunImage = new ImageIcon("images/sun.png");
	
	Plants plant;
	Sun sun = new Sun();

	public void paint(Graphics g) {
		g.drawImage(field.getImage(), 0, 25, null);
		g.setColor(Color.WHITE);
		g.fillRect(230, 125, 50, 25);
		g.setColor(Color.BLACK);
//		g.setFont(new Font(name, style, size));
		g.drawString(String.valueOf(sunMoney), 230, 125);
		for (gui.Sun sun : suns) {
			//g.fillOval(sun.coorX, 50, 30, 30);
			g.drawImage(sunImage.getImage(), sun.coorX, sun.coorY, null);
			System.out.println(sun.coorX);
		}
		//g.drawOval(10, 10, 30, 30);
		
		
	}
	
	public Frame2() {
		setSize(maxFrame);
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                repaint();
//              setIconImage(field.getImage());   
                
              //  thread.start();
            }
        });
		createButton();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
   //     panel.add(moneyField);
      //  panel.add(sun);
        add(panel);
    //    add(sun);
       // add(sun.generateSun(suns, 1370));
        thread.start();
        setVisible(true);
	} 
	
	Thread thread = new Thread(()-> {
		while (true) {
			try {
				Thread.sleep(10000);
				System.out.println("hehe");
				suns.add(sun.generateSun(suns, 1370));
				repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	public void createButton() {
        for(int i=0; i<9; i++) {
        	for(int j=0; j<5; j++) {
        		buttonsField[i][j]= new JButton(String.valueOf(i+1), squareField);
        		buttonsField[i][j].setBounds((i+1)*260+i*(-162), (j+1)*130+j*(-15), 95, 120);
        		buttonsField[i][j].addMouseListener(new MouseAdapter() {
    				@Override
    				public void mouseClicked(MouseEvent e) {
    					//plant.setButton(0, 0, buttonsField, plant);
    					JButton source = (JButton)e.getSource();
    				//	buttonX = source.get
//    					buttonsField[y][x].imageUpdate(plant.getImage().getImage(), 1, buttonsField[y][x].getX(),
//    							buttonsField[y][x].getY(), buttonsField[y][x].getWidth(), buttonsField[y][x].getHeight());
    					buttonsField[0][0].setIcon(plant.getImage());
    				}
    			});
        		add(buttonsField[i][j]); 
        		
        	}
        }
       
        for(int i=0; i<4;i++) {
        	cardList[i] = new CardButton(i+1);// set id
        	cardList[i].setBounds((i+1)*340+i*(-235), 5, 80, 95);
        	if(i==3) {
        		cardList[i].setBounds(672, 8, 65, 60);
        	}
        	cardList[i].addMouseListener(new MouseAdapter() {
        		@Override
        		public void mouseClicked(MouseEvent e) {
        			CardButton source = (CardButton)e.getSource();
        		//	JButton source = (JButton)e.getSource();
        			currentId = source.getId();
        			System.out.println("current id " + currentId);
        			if(currentId == 1) {
        			//if(plant.getPlantType(currentId) instanceof Sunflower) {
        				plant = new Sunflower();
        				System.out.println("Sunflower");
        			}
        		}
        	});
        //	cardList[i].putClientProperty(key, value);
//         	cardList.getClass();
        	add(cardList[i]);
        }
	}
	
	public static void main(String[] args) {
		new Frame2();
	}
}
