package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainMenuPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private ImageIcon play = new ImageIcon("images/play-button.png");
	private ImageIcon exit = new ImageIcon("images/exit.png");
	private ImageIcon bckgrnd = new ImageIcon("images/MM31.jpg");
	private JButton playBtn = new JButton("Start", play);
	private JButton exitBtn = new JButton("Exit", exit);
	
	public MainMenuPanel() {
		setLayout(null);
		setSize(getMaximumSize());
		
		JLabel bckgrndImg = new JLabel("", bckgrnd, JLabel.CENTER);
		bckgrndImg.setBounds(0, 0, 1366, 500);
		add(bckgrndImg);
		
		playBtn.setBounds(580, 600, 100, 30);
		exitBtn.setBounds(680, 600, 100, 30);
		add(playBtn);
		add(exitBtn);
		playBtn.addActionListener(this);
		exitBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == playBtn) {
		
			Main game = new Main();
			
			game.remove(MainMenuPanel.this);
			JPanel panel= new GamePanel();
			game.add(panel);
			panel.requestFocus();
			game.validate();
			
//			Main exit = new Main();
//			
//			game.remove(panel);
//			JPanel panel2= new MainMenuPanel();
//			game.add(panel);
//			panel.requestFocus();
//			game.validate();
//			
		}
		else if(arg0.getSource() == exitBtn) {
			System.exit(0);
		}
	}

}
