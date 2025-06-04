import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
    public static boolean debugging = true;

    private enum STATE { MENU, GAME, SELECT }
    private STATE state = STATE.MENU;

    SimpleAudioPlayer titleScreenMusic = new SimpleAudioPlayer("D:\\Eclipse IDE\\Code\\Rhythm-no-Rhythm\\src\\ATW.wav", true);
    SimpleAudioPlayer brazilMusic = new SimpleAudioPlayer("D:\\Eclipse IDE\\Code\\Rhythm-no-Rhythm\\src\\brazil.wav", false);
    SimpleAudioPlayer lordMusic = new SimpleAudioPlayer("D:\\Eclipse IDE\\Code\\Rhythm-no-Rhythm\\src\\lord.wav", false);
    SimpleAudioPlayer weezeMusic = new SimpleAudioPlayer("D:\\Eclipse IDE\\Code\\Rhythm-no-Rhythm\\src\\weeze.wav", false);

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

    private String currentAccuracy = "";
    private long accuracyTimestamp = 0;
    private final int accuracyDisplayDuration = 1000; // milliseconds
    private Color accuracyColor = Color.WHITE;

    kkSliders kk = new kkSliders();
  //  PinkNote pinkNote = new PinkNote(1000);
   // BlueNote blueNote = new BlueNote(-80);
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
    
    ArrayList<BlueNote> brazilBlues = new ArrayList<>();
    ArrayList<PinkNote> brazilPinks = new ArrayList<>();
    
    ArrayList<BlueNote> lordBlues = new ArrayList<>();
    ArrayList<PinkNote> lordPinks = new ArrayList<>();
    
    ArrayList<BlueNote> weezeBlues = new ArrayList<>();
    ArrayList<PinkNote> weezePinks = new ArrayList<>();

    
    
    
    
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


        //BRAZIL NOTES
            brazilBlues.add(new BlueNote(160));
            brazilBlues.add(new BlueNote(0));
            brazilPinks.add(new PinkNote(720));
            brazilPinks.add(new PinkNote(880));

            
        //LORD NOTES
            lordBlues.add(new BlueNote(720));
            lordPinks.add(new PinkNote(720));

            
        //WEEZE NOTES
            weezeBlues.add(new BlueNote(720));
            weezePinks.add(new PinkNote(720));


        
        Timer t = new Timer(16, this);
        t.start();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        
      
        
        
    }

    @Override
    protected void paintComponent(Graphics g) {
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
            //pinkNote.paint(g);
           // blueNote.paint(g);
            hole.paint(g);
            hole2.paint(g);
            kk.paint(g);

            g.setColor(Color.WHITE);
            g.setFont(myFont);
            g.drawString("Score: " + score, 15, 50);
            g.drawString("Combo: " + combo, 15, 85);

            if (!currentAccuracy.equals("") && System.currentTimeMillis() - accuracyTimestamp < accuracyDisplayDuration) {
                g.setColor(accuracyColor);
                g.setFont(new Font("Courier", Font.BOLD, 60));
                g.drawString(currentAccuracy, 750, 250);
            }
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
        
        if (state == STATE.GAME) {
            if (dir == 0) {
                for (BlueNote obj : brazilBlues) {
                	if (!obj.isHit) {
                		obj.paint(g);
                	}
                }
                for (PinkNote obj : brazilPinks) {
                	if (!obj.isHit) {
                		obj.paint(g);
                	}
                }
            } else if (dir == 1) {
                for (BlueNote obj : lordBlues) {
                	if (!obj.isHit) {
                		obj.paint(g);
                	}
                }
                for (PinkNote obj : lordPinks) {
                	if (!obj.isHit) {
                		obj.paint(g);
                	}
                }
            } else {
                for (BlueNote obj : weezeBlues) {
                	if (!obj.isHit) {
                		obj.paint(g);
                	}
                }
                for (PinkNote obj : weezePinks) {
                	if (!obj.isHit) {
                		obj.paint(g);
                	}
                }
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
    	
    	if(dir == 0) {
    	for(int i = 0; i < brazilPinks.size(); i++) {
        if (brazilPinks.get(i).missed) {
            combo = 0;
            brazilPinks.get(i).missed = false;
            System.out.println("Score: " + score + " | Combo: 0");
            
        } 
    	}
    	
    	for(int i = 0; i < brazilBlues.size(); i++) {
        if (brazilBlues.get(i).missed) {
            combo = 0;
            brazilBlues.get(i).missed = false;
            System.out.println("Score: " + score + " | Combo: 0");
        }

        
    }
    }
    	
    	if(dir == 1) {
    	for(int i = 0; i < lordPinks.size(); i++) {
            if (lordPinks.get(i).missed) {
                combo = 0;
                lordPinks.get(i).missed = false;
                System.out.println("Score: " + score + " | Combo: 0");
                
            } 
        	}
        	
        	for(int i = 0; i < lordBlues.size(); i++) {
            if (lordBlues.get(i).missed) {
                combo = 0;
                lordBlues.get(i).missed = false;
                System.out.println("Score: " + score + " | Combo: 0");
            }

            
        }
    	}
    	if(dir == 2) {
        	for(int i = 0; i < weezePinks.size(); i++) {
                if (weezePinks.get(i).missed) {
                    combo = 0;
                    weezePinks.get(i).missed = false;
                    System.out.println("Score: " + score + " | Combo: 0");
                    
                } 
            	}
            	
            	for(int i = 0; i < weezeBlues.size(); i++) {
                if (weezeBlues.get(i).missed) {
                    combo = 0;
                    weezeBlues.get(i).missed = false;
                    System.out.println("Score: " + score + " | Combo: 0");
                }

            	}
            }
    
    	if (dir == 0) {
    	    brazilPinks.removeIf(note -> note.missed || note.isHit);
    	    brazilBlues.removeIf(note -> note.missed || note.isHit);
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
            if (dir == 0) {
                brazilMusic.play();
            } else if (dir == 1) {
                lordMusic.play();
            } else if (dir == 2){
                weezeMusic.play();
            }
        }
       

        if (state == STATE.SELECT) {
            if (e.getKeyCode() == 40) {
                down = true;
                if (dir < 2) {
                	dir++;
                }
            } else if (e.getKeyCode() == 38) {
                up = true;
                if (dir > 0) {
                	dir--;
                }
            }

            
        }

        if (state == STATE.GAME) {
            if (e.getKeyCode() == 70) {
            	
            	 ArrayList<PinkNote> activeNotes = dir == 0 ? brazilPinks : dir == 1 ? lordPinks : weezePinks;
            	    int targetX = hole.x;

            	    PinkNote closestNote = null;
            	    int closestDistance = Integer.MAX_VALUE;

            	    for (PinkNote note : activeNotes) {
            	        if (!note.isHit) {
            	            int distance = Math.abs(note.x - targetX);
            	            if (distance < closestDistance) {
            	                closestDistance = distance;
            	                closestNote = note;
            	            }
            	        }
            	    }

            	    if (closestNote != null) {
            	        String accuracy = accuracyCalculator(closestNote.x, targetX);
            	        currentAccuracy = accuracy;
            	        accuracyTimestamp = System.currentTimeMillis();

            	        switch (accuracy) {
            	            case "Perfect":
            	                score += 300; combo++; accuracyColor = Color.GREEN; break;
            	            case "Good":
            	                score += 200; combo++; accuracyColor = Color.CYAN; break;
            	            case "Okay":
            	                score += 100; combo++; accuracyColor = Color.YELLOW; break;
            	            case "Bad":
            	                score += 50; combo = 0; accuracyColor = Color.RED; break;
            	            case "Miss":
            	                combo = 0; accuracyColor = Color.GRAY; break;
            	        }

            	        closestNote.isHit = true;
            	        System.out.println("Accuracy: " + accuracy);
            	        System.out.println("Score: " + score + " | Combo: " + combo);
            	    }
            }

            if (e.getKeyCode() == 74) {
            	
            	
            	    ArrayList<BlueNote> activeNotes = dir == 0 ? brazilBlues : dir == 1 ? lordBlues : weezeBlues;
            	    int targetX = hole2.x;

            	    BlueNote closestNote = null;
            	    int closestDistance = Integer.MAX_VALUE;

            	    for (BlueNote note : activeNotes) {
            	        if (!note.isHit) {
            	            int distance = Math.abs(note.x - targetX);
            	            if (distance < closestDistance) {
            	                closestDistance = distance;
            	                closestNote = note;
            	            }
            	        }
            	    }

            	    if (closestNote != null) {
            	        String accuracy = accuracyCalculator(closestNote.x, targetX);
            	        currentAccuracy = accuracy;
            	        accuracyTimestamp = System.currentTimeMillis();

            	        switch (accuracy) {
            	            case "Perfect":
            	                score += 300; combo++; accuracyColor = Color.GREEN; break;
            	            case "Good":
            	                score += 200; combo++; accuracyColor = Color.CYAN; break;
            	            case "Okay":
            	                score += 100; combo++; accuracyColor = Color.YELLOW; break;
            	            case "Bad":
            	                score += 50; combo = 0; accuracyColor = Color.RED; break;
            	            case "Miss":
            	                combo = 0; accuracyColor = Color.GRAY; break;
            	        }

            	        closestNote.isHit = true;
            	        System.out.println("Accuracy: " + accuracy);
            	        System.out.println("Score: " + score + " | Combo: " + combo);
            	    }
            	}
            }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (state == STATE.SELECT) {
            if (e.getKeyCode() == 40) down = false;
            else if (e.getKeyCode() == 38) up = false;
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
