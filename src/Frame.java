import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
    public static boolean debugging = true;

    private enum STATE { MENU, GAME, SELECT }
    private STATE state = STATE.MENU;

    SimpleAudioPlayer titleScreenMusic = new SimpleAudioPlayer("C:\\Users\\1917097\\git\\Rhythm-no-Rhythm\\src\\ATW.wav", true);
    
    private boolean up;
    private boolean down;
    
    private int dir;

    int score = 0;
    int combo = 0;

    Font myFont = new Font("Courier", Font.BOLD, 30);

    private final int Perfect = 20;
    private final int Good = 35;
    private final int Okay = 60;
    private final int Bad = 85;
    
    kkSliders kk = new kkSliders();
    PinkNote pinkNote = new PinkNote();
    BlueNote blueNote = new BlueNote();
    Hole hole = new Hole();
    Hole2 hole2 = new Hole2();
    TitleCard titleCard = new TitleCard();
    StartScreen start = new StartScreen();
    EnterToStart enter = new EnterToStart();
    SongSelect songSelect = new SongSelect();
    BrazilSelect brazil = new BrazilSelect();
    LordSelect lord = new LordSelect();
    WeezerSelect weeze = new WeezerSelect();
    upArrow upArrow = new upArrow();
    downArrow downArrow = new downArrow();
    defaultArrow defaultArrow = new defaultArrow();
    GameBackground game = new GameBackground();
    LeftBelt leftBelt = new LeftBelt();
    RightBelt rightBelt = new RightBelt();
    
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
            g.setColor(Color.WHITE);
            g.setFont(myFont);

        } else if (state == STATE.GAME) {
        	game.paint(g);
        	leftBelt.paint(g);
        	rightBelt.paint(g);
            pinkNote.paint(g);
            blueNote.paint(g);
            hole.paint(g);
            hole2.paint(g);
            kk.paint(g);
            g.setColor(Color.WHITE);
            g.setFont(myFont);
            
            g.drawString("Score: " + score, 15, 50);
            g.drawString("Combo: " + combo, 15, 85);

        } else if (state == STATE.SELECT) {
            songSelect.paint(g);

            if (dir == 0) {
                brazil.paint(g);
            } else if (dir == 1) {
                lord.paint(g);
            } else {
                weeze.paint(g);
            }

            if (up) {
                upArrow.paint(g);
            } else if (down) {
                downArrow.paint(g);
            } else {
                defaultArrow.paint(g);
            }
        }
    }

    public String accuracyCalculator(int note, int target) {
        int acc = Math.abs(note - target);

        if (acc <= Perfect) {
            return "Perfect";
        } else if (acc <= Good) {
            return "Good";
        } else if (acc <= Okay) {
            return "Okay";
        } else if (acc <= Bad) {
            return "Bad";
        } else {
            return "Miss";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pinkNote.missed) {
            combo = 0;
            pinkNote.missed = false;
            System.out.println("Score: " + score + " | Combo: 0");
        } else if (blueNote.missed) {
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
        if (e.getKeyCode() == 101) { // 'e' key, used for returning to menu
            state = STATE.MENU;
            titleScreenMusic.play();
            score = 0;
            dir = 0;
            combo = 0;
        }

        if (state == STATE.SELECT) {
            if (e.getKeyCode() == 40) { // Down arrow
                down = true;
                if (dir < 2) {
                    dir++;
                }
            } else if (e.getKeyCode() == 38) { // Up arrow
                up = true;
                if (dir > 0) {
                    dir--;
                }
            }
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
                } else {
                    combo = 0;
                }

                System.out.println("Accuracy: " + accuracy);
                System.out.println("Score: " + score + " | Combo: " + combo);
                blueNote.x = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (state == STATE.SELECT) {
            if (e.getKeyCode() == 40) { // Down arrow
                down = false;
            } else if (e.getKeyCode() == 38) { // Up arrow
                up = false;
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
    public void keyTyped(KeyEvent e) {}
}
