import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
    public static boolean debugging = true;

    private enum STATE { MENU, GAME }
    private STATE state = STATE.MENU;

    int score = 0;
    Font myFont = new Font("Courier", Font.BOLD, 40);
    PinkNote pinkNote = new PinkNote();
    BlueNote blueNote = new BlueNote();
    Hole hole = new Hole();
    Hole2 hole2 = new Hole2();
    StartScreen start = new StartScreen();

    public int width = 1000;
    public int height = 1000;

    public static void main(String[] arg) {
        Frame f = new Frame();
    }

    public Frame() {
        JFrame f = new JFrame("Rhythm no Rhythm");
        f.setSize(new Dimension(width, height));
        f.setBackground(Color.gray);
        f.add(this);
        f.setResizable(false);
        f.addMouseListener(this);
        f.addKeyListener(this);
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon("torch.png").getImage(),
                new Point(0, 0), "custom cursor"));

        Timer t = new Timer(16, this);
        t.start();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (state == STATE.MENU) {
            start.paint(g);
            g.setColor(Color.WHITE);
            g.setFont(myFont);
           
        } else if (state == STATE.GAME) {
            pinkNote.paint(g);
            blueNote.paint(g);
            hole.paint(g);
            hole2.paint(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {}

    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}

    @Override
    public void mousePressed(MouseEvent m) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}

    @Override
    public void actionPerformed(ActionEvent arg0) {
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (state == STATE.MENU && e.getKeyCode() == KeyEvent.VK_ENTER) {
            state = STATE.GAME;
            return;
        }

        if (state == STATE.GAME) {
            if (e.getKeyCode() == 70) { // F key
                if (pinkNote.collided(hole)) {
                    pinkNote.x = 920;
                    System.out.println("Hit!");
                } else {
                    System.out.println("Slow Down!");
                }
            }

            if (e.getKeyCode() == 74) { // J key
                if (blueNote.collided(hole2)) {
                    blueNote.x = 0;
                    System.out.println("Hit!");
                } else {
                    System.out.println("Slow Down!");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {}

    @Override
    public void keyTyped(KeyEvent arg0) {}
}
	
