package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
//import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Plant.Plants;

public class Framee extends JFrame {
	Plants[] plants = new Plants[55];
	ImageIcon field = new ImageIcon("images/fieldDay3.jpg");
	ImageIcon squareField = new ImageIcon("images/FieldSquare.jpg");
	ImageIcon sunFlower = new ImageIcon("images/SunFlowerCard.jpg");
	//MouseListener mouseListener = new MouseEvent(source, id, when, modifiers, x, y, xAbs, yAbs, clickCount, popupTrigger, button)
//	ActionListener actionListener = new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			e.
//		}
//	};
	void createPanelButton() {
		JPanel[][] panelHolder = new JPanel[6][10];    
		this.getContentPane().setLocation(0, 30);
	//	this.addMouseMotionListener(l);
		setLayout(new GridLayout(6, 6));
		
			for(int m = 0; m < 6; m++) {
			   for(int n = 0; n < 10; n++) {
			      panelHolder[m][n] = new JPanel();
			      add(panelHolder[m][n]);
			   }
			}
			
			JButton buttons[] = new JButton[55];
			for (int i = 0; i < buttons.length; i++) {
				buttons[i] = new JButton(String.valueOf(i+1), squareField);
				buttons[i].setPreferredSize(new Dimension(135, 110));
				buttons[i].setTransferHandler(new ValueExportTransferHandler(Integer.toString(i + 1)));
			//	buttons[i].
			//	buttons[i].setBounds(50, 50, 135, 110);
				//buttons[i].getClass(plants);
			}
			buttons[0] = new JButton(String.valueOf("Sunflower"), sunFlower);
			buttons[1] = new JButton(String.valueOf("Peashooter"));
			buttons[2] = new JButton(String.valueOf("Chomper"));
			buttons[3] = new JButton(String.valueOf("Wallnut"));
			buttons[4] = new JButton(String.valueOf("Ice"));
			
			int btnCnt=0;
			for (int m = 0; m <1 ; m++) {
				for(int j=1; j<6; j++) {
					buttons[btnCnt].setPreferredSize(new Dimension(100, 95));
					
					 buttons[btnCnt].addMouseMotionListener(new MouseAdapter() {
		                 @Override
		                 public void mouseDragged(MouseEvent e) {
		                     JButton button = (JButton) e.getSource();
		                     TransferHandler handle = button.getTransferHandler();
		                     handle.exportAsDrag(button, e, TransferHandler.COPY);
		                 }
		             });
					 panelHolder[m][j].add(buttons[btnCnt]);
					btnCnt++;
				}
			}
			for (int m = 1; m < 6 ; m++) {
				for(int j=1; j<10; j++) {
					buttons[btnCnt].setTransferHandler(new ValueImportTransferHandler());
					panelHolder[m][j].add(buttons[btnCnt]);
					btnCnt++;
				}
			}
//		buttons[0].addActionListener((e)->{
//			JOptionPane.showMessageDialog(null, "hahaha");
//		});
		}
	
	public Framee() {
		Dimension framex = getMaximumSize();
		setSize(framex);
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

               createPanelButton();
        		setDefaultCloseOperation(EXIT_ON_CLOSE);
        		setLocationRelativeTo(null);
        		//setBackground(Color.BLACK);
        		 //setIconImage(field.getImage());
        		repaint();
        		setVisible(true);
        		
            }
        });
		
		
		
		
//		MouseListener mouseListener = new MouseListener() {
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				mouseListener.
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		};
		
	}
	
	@Override
	public void paint(Graphics g) {
//		super.paint(g);
		g.drawImage(field.getImage(), 50, 50, null);
	}
	
	public static void main(String[] args) {
		new Framee();
	}
	public static class ValueExportTransferHandler extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
        private String value;

        public ValueExportTransferHandler(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_COPY_OR_MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            Transferable t = new StringSelection(getValue());
            return t;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            super.exportDone(source, data, action);
            // Decide what to do after the drop has been accepted
        }

    }

    public static class ValueImportTransferHandler extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;

        public ValueImportTransferHandler() {
        }

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
                    if (value instanceof String) {
                        Component component = support.getComponent();
                        if (component instanceof JLabel) {
                            ((JLabel) component).setText(value.toString());
                            accept = true;
                        }
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            return accept;
        }
    }
}
