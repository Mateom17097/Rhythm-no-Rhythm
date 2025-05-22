import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;
public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	public static boolean debugging = true;

	
	int score = 0;
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	PinkNote pinkNote = new PinkNote();
	BlueNote blueNote = new BlueNote();
	Hole hole = new Hole();
	Hole2 hole2 = new Hole2();
	public void paint(Graphics g) {
		super.paintComponent(g);
		pinkNote.paint(g);
		blueNote.paint(g);
		hole.paint(g);
		hole2.paint(g);
	}
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
		
	}
	
	public int width = 1000;
	public int height = 1000;
	
	public Frame() {
		JFrame f = new JFrame("Rhythm no Rhythm");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.gray);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
		//backgroundMusic.play();
		
	
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode() == 70) {
			if(pinkNote.collided(hole)) {
				pinkNote.x = 920;
				System.out.println("Hit!");
			}else if(!pinkNote.collided(hole)) {
				System.out.println("Slow Down!");
			}
		if(arg0.getKeyCode() == 74) {
			if(blueNote.collided(hole2)) {
				blueNote.x = 0;
				System.out.println("Hit!");
			}else if(!blueNote.collided(hole2)) {
				System.out.println("Slow Down!");
			}
			
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
