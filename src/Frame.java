import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
    public static boolean debugging = true;

    private enum STATE { MENU, GAME, SELECT }
    private STATE state = STATE.MENU;

    SimpleAudioPlayer titleScreenMusic = new SimpleAudioPlayer("H:\\git\\Rhythm-no-Rhythm\\src\\ATW.wav", true);

    int score = 0;
    int combo = 0;
    Font myFont = new Font("Courier", Font.BOLD, 40);
   
    private final int Perfect = 20;
    private final int Good = 35;
    private final int Okay = 60;
    private final int Bad = 85;
    
    PinkNote pinkNote = new PinkNote();
    BlueNote blueNote = new BlueNote();
    Hole hole = new Hole();
    Hole2 hole2 = new Hole2();
    TitleCard titleCard = new TitleCard();
    StartScreen start = new StartScreen();
    EnterToStart enter = new EnterToStart();

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
            titleCard.paint(g);
            enter.paint(g);
        } else if (state == STATE.GAME) {
            pinkNote.paint(g);
            blueNote.paint(g); 
            hole.paint(g);
            hole2.paint(g);

            g.setColor(Color.WHITE);
            g.setFont(myFont);
            g.drawString("Score: " + score, 50, 50);
            g.drawString("Combo: " + combo, 50, 100);
        }
    }

    public String accuracyCalculator(int note, int target) {
    	int acc = Math.abs(note - target);
    	
    	if(acc <= Perfect) {
    		return "PERFECT";
    	}else if(acc <= Good) {
    		return "GOOD";
    	}else if(acc <= Okay) {
    		return "OKAY";
    	}else if(acc <= Bad) {
    		return "BAD";
    	}else {
    		return "MISS";
    				
    	}
    	
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (pinkNote.missed) {
        	combo = 0;
            pinkNote.missed = false;
            System.out.println("Score: " + score + " | Combo: 0");
        }else if(blueNote.missed) {
        	combo = 0;
        	blueNote.missed = false;
        	System.out.println("Score: " + score + " | Combo: 0");
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (state == STATE.MENU && e.getKeyCode() == KeyEvent.VK_ENTER) {
            state = STATE.SELECT;
            return;
        }
        if (state == STATE.SELECT && e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                titleScreenMusic.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            state = STATE.GAME;
        }
        if (state == STATE.GAME) {
            if (e.getKeyCode() == 70) { // F key
                String accuracy = accuracyCalculator(pinkNote.x, hole.x);

                if (accuracy.equals("Perfect")) {
                    score += 300;
                    combo++;
                } else if (accuracy.equals("Good")) {
                    score += 200;
                    combo++;
                } else if (accuracy.equals("Okay")) {
                    score += 100;
                    combo++;
                } else if (accuracy.equals("Bad")) {
                    score += 50;
                    combo = 0;
                } else { 
                    combo = 0;
                }

                System.out.println("Accuracy: " + accuracy);
                System.out.println("Score: " + score + " | Combo: " + combo);
                pinkNote.x = 920; 
            }

            if (e.getKeyCode() == 74) { // J key
                String accuracy = accuracyCalculator(blueNote.x, hole2.x);

                if (accuracy.equals("Perfect")) {
                    score += 300;
                    combo++;
                } else if (accuracy.equals("Good")) {
                    score += 200;
                    combo++;
                } else if (accuracy.equals("Okay")) {
                    score += 100;
                    combo++;
                } else if (accuracy.equals("Bad")) {
                    score += 50;
                    combo = 0;
                } else { // Miss
                    combo = 0;
                }

                System.out.println("Accuracy: " + accuracy);
                System.out.println("Score: " + score + " | Combo: " + combo);
                blueNote.x = 0; // Reset note
            }

            if (e.getKeyCode() == 101) {
                state = STATE.MENU;
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
