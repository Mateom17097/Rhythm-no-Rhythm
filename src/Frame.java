import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
    public static boolean debugging = true;

    private enum STATE { MENU, GAME, SELECT }
    private STATE state = STATE.MENU;

    SimpleAudioPlayer titleScreenMusic = new SimpleAudioPlayer("H:\\git\\Rhythm-no-Rhythm\\src\\ATW.wav", true);
    SimpleAudioPlayer brazilMusic = new SimpleAudioPlayer("H:\\git\\Rhythm-no-Rhythm\\src\\brazil.wav", false);
    SimpleAudioPlayer lordMusic = new SimpleAudioPlayer("H:\\git\\Rhythm-no-Rhythm\\src\\lord.wav", false);
    SimpleAudioPlayer weezeMusic = new SimpleAudioPlayer("H:\\git\\Rhythm-no-Rhythm\\src\\weeze.wav", false);

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

    chibiRoboPink pinkRobo = new chibiRoboPink();
    chibiRoboBlue blueRobo = new chibiRoboBlue();
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
        chibiRoboPink pink = new chibiRoboPink(100, 100);
        chibiRoboBlue blue = new chibiRoboBlue(300, 100);

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    
         //BRAZIL NOTES
            brazilBlues.add(new BlueNote(-1040));  //Base 0
            brazilPinks.add(new PinkNote(2220));//Base 1000
            brazilPinks.add(new PinkNote(2380));
            brazilPinks.add(new PinkNote(2540));
            brazilPinks.add(new PinkNote(2880));
            brazilBlues.add(new BlueNote(-2320));
            brazilBlues.add(new BlueNote(-2480));
            brazilPinks.add(new PinkNote(3760));
            brazilPinks.add(new PinkNote(4060));
            
            brazilPinks.add(new PinkNote(4540));
            brazilBlues.add(new BlueNote(-4000));
            brazilBlues.add(new BlueNote(-4160));
            brazilBlues.add(new BlueNote(-4320));
            brazilBlues.add(new BlueNote(-4660));
            brazilPinks.add(new PinkNote(5860));
            brazilPinks.add(new PinkNote(6020));
            brazilBlues.add(new BlueNote(-5500));
            brazilBlues.add(new BlueNote(-5800));
            
            brazilBlues.add(new BlueNote(-6340));
            brazilPinks.add(new PinkNote(7580));
            brazilBlues.add(new BlueNote(-6820));
            brazilBlues.add(new BlueNote(-6980));
            brazilPinks.add(new PinkNote(8220));
            brazilBlues.add(new BlueNote(-7480));
            brazilPinks.add(new PinkNote(8540));
            brazilBlues.add(new BlueNote(-7660));
            brazilPinks.add(new PinkNote(8680));
            brazilBlues.add(new BlueNote(-7800));
            brazilPinks.add(new PinkNote(8860));
            brazilPinks.add(new PinkNote(9040));
            brazilBlues.add(new BlueNote(-8140));
            brazilPinks.add(new PinkNote(9200));
            brazilBlues.add(new BlueNote(-9300));
            brazilPinks.add(new PinkNote(9360));
            brazilBlues.add(new BlueNote(-8640));
            brazilPinks.add(new PinkNote(9680));

            brazilBlues.add(new BlueNote(-8960));
            brazilPinks.add(new PinkNote(10160));
            brazilBlues.add(new BlueNote(-9460));
            brazilBlues.add(new BlueNote(-9640));
            brazilPinks.add(new PinkNote(10820));
            brazilBlues.add(new BlueNote(-10060));
            brazilPinks.add(new PinkNote(11160));
            brazilBlues.add(new BlueNote(-10280));
            brazilPinks.add(new PinkNote(11300));
            brazilBlues.add(new BlueNote(-10420));
            brazilPinks.add(new PinkNote(11500));
            brazilPinks.add(new PinkNote(11640));
            brazilBlues.add(new BlueNote(-10760));
            brazilPinks.add(new PinkNote(11820));
            brazilBlues.add(new BlueNote(-10920));
            brazilPinks.add(new PinkNote(12000));
            brazilBlues.add(new BlueNote(-11260));
            brazilPinks.add(new PinkNote(12300));
            
            brazilBlues.add(new BlueNote(-11580));
            brazilPinks.add(new PinkNote(12840));
            brazilBlues.add(new BlueNote(-12100));
            brazilBlues.add(new BlueNote(-12280));
            brazilPinks.add(new PinkNote(13480));
            brazilBlues.add(new BlueNote(-12720));
            brazilPinks.add(new PinkNote(13780));
            brazilBlues.add(new BlueNote(-12900));
            brazilPinks.add(new PinkNote(13940));
            brazilBlues.add(new BlueNote(-13040));
            brazilPinks.add(new PinkNote(14100));
            brazilPinks.add(new PinkNote(14280));
            brazilBlues.add(new BlueNote(-13380));
            brazilPinks.add(new PinkNote(14440));
            brazilBlues.add(new BlueNote(-133540));
            brazilPinks.add(new PinkNote(14620));
            brazilBlues.add(new BlueNote(-13880));
            brazilPinks.add(new PinkNote(14920));
            
            brazilBlues.add(new BlueNote(-14160));
            brazilPinks.add(new PinkNote(15160));
            brazilBlues.add(new BlueNote(-14420));
            brazilPinks.add(new PinkNote(15340));
            brazilBlues.add(new BlueNote(-14600));
            brazilPinks.add(new PinkNote(15500));
            brazilPinks.add(new PinkNote(15720));
            brazilBlues.add(new BlueNote(-14940));
            brazilPinks.add(new PinkNote(15880));
            brazilBlues.add(new BlueNote(-15100));
            brazilPinks.add(new PinkNote(16040));
            brazilBlues.add(new BlueNote(-15320));
            brazilPinks.add(new PinkNote(16280));
            brazilBlues.add(new BlueNote(-15500));
            brazilPinks.add(new PinkNote(16460));
            brazilBlues.add(new BlueNote(-15740));
            brazilPinks.add(new PinkNote(16700));

            brazilBlues.add(new BlueNote(-16020));
            brazilPinks.add(new PinkNote(16920));
            brazilBlues.add(new BlueNote(-16200));
            brazilBlues.add(new BlueNote(-16360));
            brazilPinks.add(new PinkNote(17240));
            brazilBlues.add(new BlueNote(-16720));
            brazilPinks.add(new PinkNote(17500));
            brazilBlues.add(new BlueNote(-16880));
            brazilPinks.add(new PinkNote(17640));
            brazilBlues.add(new BlueNote(-17040));
            brazilPinks.add(new PinkNote(17820));
            brazilPinks.add(new PinkNote(17960));
            brazilBlues.add(new BlueNote(-17380));
            brazilPinks.add(new PinkNote(18120));
            brazilBlues.add(new BlueNote(-17520));
            brazilPinks.add(new PinkNote(18280));
            brazilBlues.add(new BlueNote(-17780));
            brazilPinks.add(new PinkNote(18520));

            brazilBlues.add(new BlueNote(-18000));
            brazilPinks.add(new PinkNote(18740));
            brazilBlues.add(new BlueNote(-18220));
            brazilPinks.add(new PinkNote(18920));
            brazilBlues.add(new BlueNote(-18400));
            brazilPinks.add(new PinkNote(19100));
            brazilPinks.add(new PinkNote(19340));
            brazilBlues.add(new BlueNote(-18740));
            brazilPinks.add(new PinkNote(19500));
            brazilBlues.add(new BlueNote(-18900));
            brazilPinks.add(new PinkNote(19680));
            brazilBlues.add(new BlueNote(-19120));
            brazilPinks.add(new PinkNote(19920));
            brazilBlues.add(new BlueNote(-19300));
            brazilPinks.add(new PinkNote(20100));
            brazilBlues.add(new BlueNote(-19540));
            brazilPinks.add(new PinkNote(20340));

            brazilBlues.add(new BlueNote(-19820));
            brazilPinks.add(new PinkNote(20560));
            brazilBlues.add(new BlueNote(-20000));
            brazilBlues.add(new BlueNote(-20160));
            brazilPinks.add(new PinkNote(20880));
            brazilBlues.add(new BlueNote(-20520));
            brazilPinks.add(new PinkNote(21140));
            brazilBlues.add(new BlueNote(-20680));
            brazilPinks.add(new PinkNote(21280));
            brazilBlues.add(new BlueNote(-20840));
            brazilPinks.add(new PinkNote(21460));
            brazilPinks.add(new PinkNote(21600));
            brazilBlues.add(new BlueNote(-21180));
            brazilPinks.add(new PinkNote(21760));
            brazilBlues.add(new BlueNote(-21320));
            brazilPinks.add(new PinkNote(21920));
            brazilBlues.add(new BlueNote(-21580));
            brazilPinks.add(new PinkNote(22160));

            brazilBlues.add(new BlueNote(-21800));
            brazilPinks.add(new PinkNote(22380));
            brazilBlues.add(new BlueNote(-22020));
            brazilPinks.add(new PinkNote(22560));
            brazilBlues.add(new BlueNote(-22200));
            brazilPinks.add(new PinkNote(22740));
            brazilPinks.add(new PinkNote(22980));
            brazilBlues.add(new BlueNote(-22540));
            brazilPinks.add(new PinkNote(23140));
            brazilBlues.add(new BlueNote(-22700));
            brazilPinks.add(new PinkNote(23320));
            brazilBlues.add(new BlueNote(-22920));
            brazilPinks.add(new PinkNote(23560));
            brazilBlues.add(new BlueNote(-23100));
            brazilPinks.add(new PinkNote(23740));
            brazilBlues.add(new BlueNote(-23340));
            brazilPinks.add(new PinkNote(23980));

            brazilBlues.add(new BlueNote(-23620));
            brazilPinks.add(new PinkNote(24200));
            brazilBlues.add(new BlueNote(-23800));
            brazilBlues.add(new BlueNote(-23960));
            brazilPinks.add(new PinkNote(24520));
            brazilBlues.add(new BlueNote(-24320));
            brazilPinks.add(new PinkNote(24780));
            brazilBlues.add(new BlueNote(-24480));
            brazilPinks.add(new PinkNote(24920));
            brazilBlues.add(new BlueNote(-24640));
            brazilPinks.add(new PinkNote(25100));
            brazilPinks.add(new PinkNote(25240));
            brazilBlues.add(new BlueNote(-24980));
            brazilPinks.add(new PinkNote(25400));
            brazilBlues.add(new BlueNote(-25120));
            brazilPinks.add(new PinkNote(25560));
            brazilBlues.add(new BlueNote(-25380));
            brazilPinks.add(new PinkNote(25800));

            brazilBlues.add(new BlueNote(-25600));
            brazilPinks.add(new PinkNote(26020));
            brazilBlues.add(new BlueNote(-25820));
            brazilPinks.add(new PinkNote(26200));
            brazilBlues.add(new BlueNote(-26000));
            brazilPinks.add(new PinkNote(26380));
            brazilPinks.add(new PinkNote(26620));
            brazilBlues.add(new BlueNote(-26340));
            brazilPinks.add(new PinkNote(26780));
            brazilBlues.add(new BlueNote(-26500));
            brazilPinks.add(new PinkNote(26960));
            brazilBlues.add(new BlueNote(-26720));
            brazilPinks.add(new PinkNote(27200));
            brazilBlues.add(new BlueNote(-26900));
            brazilPinks.add(new PinkNote(27380));
            brazilBlues.add(new BlueNote(-27140));
            brazilPinks.add(new PinkNote(27620));

            brazilBlues.add(new BlueNote(-27420));
            brazilPinks.add(new PinkNote(27840));
            brazilBlues.add(new BlueNote(-27600));
            brazilBlues.add(new BlueNote(-27760));
            brazilPinks.add(new PinkNote(28160));
            brazilBlues.add(new BlueNote(-28120));
            brazilPinks.add(new PinkNote(28420));
            brazilBlues.add(new BlueNote(-28280));
            brazilPinks.add(new PinkNote(28580));
            brazilBlues.add(new BlueNote(-28440));
            brazilPinks.add(new PinkNote(28740));
            brazilPinks.add(new PinkNote(28880));
            brazilBlues.add(new BlueNote(-28780));
            brazilPinks.add(new PinkNote(29040));
            brazilBlues.add(new BlueNote(-28920));
            brazilPinks.add(new PinkNote(29200));
            brazilBlues.add(new BlueNote(-29180));
            brazilPinks.add(new PinkNote(29440));

            brazilBlues.add(new BlueNote(-29460));
            brazilPinks.add(new PinkNote(29660));
            brazilBlues.add(new BlueNote(-29680));
            brazilPinks.add(new PinkNote(29840));
            brazilBlues.add(new BlueNote(-29860));
            brazilPinks.add(new PinkNote(30020));
            brazilPinks.add(new PinkNote(30260));
            brazilBlues.add(new BlueNote(-30200));
            brazilPinks.add(new PinkNote(30380));
            brazilBlues.add(new BlueNote(-30360));
            brazilPinks.add(new PinkNote(30560));
            brazilBlues.add(new BlueNote(-30540));
            brazilPinks.add(new PinkNote(30800));
            brazilBlues.add(new BlueNote(-30780));
            brazilPinks.add(new PinkNote(30980));
            brazilBlues.add(new BlueNote(-30960));
            brazilPinks.add(new PinkNote(31140));

            brazilBlues.add(new BlueNote(-31280));
            brazilPinks.add(new PinkNote(31380));
            brazilBlues.add(new BlueNote(-31420));
            brazilBlues.add(new BlueNote(-31580));
            brazilPinks.add(new PinkNote(31760));
            brazilBlues.add(new BlueNote(-31840));
            brazilPinks.add(new PinkNote(32020));
            brazilBlues.add(new BlueNote(-32000));
            brazilPinks.add(new PinkNote(32180));
            brazilBlues.add(new BlueNote(-32260));
            brazilPinks.add(new PinkNote(32380));
            brazilPinks.add(new PinkNote(32540));
            brazilBlues.add(new BlueNote(-32680));
            brazilPinks.add(new PinkNote(32760));
            brazilBlues.add(new BlueNote(-32920));
            brazilPinks.add(new PinkNote(32980));
            brazilBlues.add(new BlueNote(-33140));
            brazilPinks.add(new PinkNote(33260));

            brazilBlues.add(new BlueNote(-33400));
            brazilPinks.add(new PinkNote(33540));
            brazilBlues.add(new BlueNote(-33620));
            brazilPinks.add(new PinkNote(33720));
            brazilBlues.add(new BlueNote(-33800));
            brazilPinks.add(new PinkNote(33960));
            brazilPinks.add(new PinkNote(34120));
            brazilBlues.add(new BlueNote(-34260));
            brazilPinks.add(new PinkNote(34300));
            brazilBlues.add(new BlueNote(-34460));
            brazilPinks.add(new PinkNote(34540));
            brazilBlues.add(new BlueNote(-34620));
            brazilPinks.add(new PinkNote(34780));
            brazilBlues.add(new BlueNote(-34860));
            brazilPinks.add(new PinkNote(34940));
            brazilBlues.add(new BlueNote(-35080));
            brazilPinks.add(new PinkNote(35160));

            brazilBlues.add(new BlueNote(-35300));
            brazilPinks.add(new PinkNote(35420));
            brazilBlues.add(new BlueNote(-35500));
            brazilBlues.add(new BlueNote(-35660));
            brazilPinks.add(new PinkNote(35800));
            brazilBlues.add(new BlueNote(-35940));
            brazilPinks.add(new PinkNote(36080));
            brazilBlues.add(new BlueNote(-36160));
            brazilPinks.add(new PinkNote(36300));
            brazilBlues.add(new BlueNote(-36440));
            brazilPinks.add(new PinkNote(36480));
            brazilPinks.add(new PinkNote(36620));
            brazilBlues.add(new BlueNote(-36760));
            brazilPinks.add(new PinkNote(36840));
            brazilBlues.add(new BlueNote(-37000));
            brazilPinks.add(new PinkNote(37060));
            brazilBlues.add(new BlueNote(-37220));
            brazilPinks.add(new PinkNote(37380));
        /*
            brazilBlues.add(new BlueNote(-1040));  //Base 0
            brazilPinks.add(new PinkNote(2220));//Base 1000
            brazilPinks.add(new PinkNote(2380));
            brazilPinks.add(new PinkNote(2540));
            brazilPinks.add(new PinkNote(2880));
            brazilBlues.add(new BlueNote(-2320));
            brazilBlues.add(new BlueNote(-2480));
            brazilPinks.add(new PinkNote(3760));
            brazilPinks.add(new PinkNote(4060));
            
            brazilPinks.add(new PinkNote(4540));
            brazilBlues.add(new BlueNote(-4000));
            brazilBlues.add(new BlueNote(-4160));
            brazilBlues.add(new BlueNote(-4320));
            brazilBlues.add(new BlueNote(-4660));
            brazilPinks.add(new PinkNote(5860));
            brazilPinks.add(new PinkNote(6020));
            brazilBlues.add(new BlueNote(-5500));
            brazilBlues.add(new BlueNote(-5800));
            
            brazilBlues.add(new BlueNote(-6280));
            brazilPinks.add(new PinkNote(7480));
            brazilBlues.add(new BlueNote(-6740));
            brazilBlues.add(new BlueNote(-6900));
            brazilPinks.add(new PinkNote(8200));
            brazilBlues.add(new BlueNote(-7460));
            brazilPinks.add(new PinkNote(8600));
            brazilBlues.add(new BlueNote(-7700));
            brazilPinks.add(new PinkNote(8760));
            brazilBlues.add(new BlueNote(-7860));
            brazilPinks.add(new PinkNote(8960));
            brazilPinks.add(new PinkNote(9140));
            brazilBlues.add(new BlueNote(-8260));
            brazilPinks.add(new PinkNote(9280));
            brazilBlues.add(new BlueNote(-8400));
            brazilPinks.add(new PinkNote(9380));
            brazilBlues.add(new BlueNote(-8620));
            brazilPinks.add(new PinkNote(9700));
            
            
            
            brazilBlues.add(new BlueNote(-1040));
            brazilBlues.add(new BlueNote(-1040));
           
            */
           
        //LORD NOTES
            lordBlues.add(new BlueNote(0));
            lordPinks.add(new PinkNote(920));


        //WEEZE NOTES
            weezePinks.add(new PinkNote(840));
            weezeBlues.add(new BlueNote(-200));
            weezePinks.add(new PinkNote(1640));
            weezeBlues.add(new BlueNote(-880));
            weezeBlues.add(new BlueNote(-1060));
            weezePinks.add(new PinkNote(2400));
            weezeBlues.add(new BlueNote(-1680));
            weezeBlues.add(new BlueNote(-2480));
            weezePinks.add(new PinkNote(3680));
            weezeBlues.add(new BlueNote(-3340));
            weezePinks.add(new PinkNote(4400));
            weezePinks.add(new PinkNote(4580));
            weezeBlues.add(new BlueNote(-4120));
            weezePinks.add(new PinkNote(5180));
            weezePinks.add(new PinkNote(6060));
            weezeBlues.add(new BlueNote(-5340));
            weezePinks.add(new PinkNote(6380));
            weezeBlues.add(new BlueNote(-5640));
            weezePinks.add(new PinkNote(6680));
            weezeBlues.add(new BlueNote(-5940));
            weezePinks.add(new PinkNote(7000));
            weezeBlues.add(new BlueNote(-6280));
            weezePinks.add(new PinkNote(7340));
            weezeBlues.add(new BlueNote(-6580));
            weezePinks.add(new PinkNote(7640));
            weezeBlues.add(new BlueNote(-6900));
            weezePinks.add(new PinkNote(7920));
            weezeBlues.add(new BlueNote(-7180));
            weezePinks.add(new PinkNote(8220));
            weezeBlues.add(new BlueNote(-7500));
            weezePinks.add(new PinkNote(8560));
            weezePinks.add(new PinkNote(8780));
            weezeBlues.add(new BlueNote(-8100));
            weezePinks.add(new PinkNote(9280));
            weezeBlues.add(new BlueNote(-8720));
            weezeBlues.add(new BlueNote(-8880));
            weezePinks.add(new PinkNote(9980));
            weezePinks.add(new PinkNote(10140));
            weezeBlues.add(new BlueNote(-9380));
            weezeBlues.add(new BlueNote(-9580));
            weezePinks.add(new PinkNote(10640));
            weezeBlues.add(new BlueNote(-10040));
            weezeBlues.add(new BlueNote(-10720));
            weezePinks.add(new PinkNote(11960));
            weezePinks.add(new PinkNote(12300));
            weezeBlues.add(new BlueNote(-11540));
            weezePinks.add(new PinkNote(12600));
            weezeBlues.add(new BlueNote(-11880));
            weezePinks.add(new PinkNote(12940));
            weezeBlues.add(new BlueNote(-12180));
            weezeBlues.add(new BlueNote(-12380));
            weezePinks.add(new PinkNote(13820));
            weezeBlues.add(new BlueNote(-13260));
            weezePinks.add(new PinkNote(14500));
            weezeBlues.add(new BlueNote(-14000));
            weezePinks.add(new PinkNote(15060));
            weezeBlues.add(new BlueNote(-14340));
            weezeBlues.add(new BlueNote(-14500));
            weezePinks.add(new PinkNote(15560));
            weezeBlues.add(new BlueNote(-14800));
            weezePinks.add(new PinkNote(15860));
            weezeBlues.add(new BlueNote(-15060));
            weezePinks.add(new PinkNote(16020));
            weezeBlues.add(new BlueNote(-15200));
            weezePinks.add(new PinkNote(16600));
            weezeBlues.add(new BlueNote(-16000));
            weezePinks.add(new PinkNote(17200));
            weezeBlues.add(new BlueNote(-16480));
            weezeBlues.add(new BlueNote(-16800));
            weezePinks.add(new PinkNote(17880));
            weezeBlues.add(new BlueNote(-17120));
            weezePinks.add(new PinkNote(18180));
            weezeBlues.add(new BlueNote(-17400));
            weezePinks.add(new PinkNote(18420));
            weezeBlues.add(new BlueNote(-17600));
            weezePinks.add(new PinkNote(18600));
            weezeBlues.add(new BlueNote(-17780));
            weezePinks.add(new PinkNote(18820));
            weezeBlues.add(new BlueNote(-18280));
            weezePinks.add(new PinkNote(19500));
            weezeBlues.add(new BlueNote(-18900));
            weezePinks.add(new PinkNote(19980));
            weezeBlues.add(new BlueNote(-19400));
            weezePinks.add(new PinkNote(20520));
            weezeBlues.add(new BlueNote(-19780));
            weezePinks.add(new PinkNote(20820));
            weezeBlues.add(new BlueNote(-20100));
            weezePinks.add(new PinkNote(21120));
            weezeBlues.add(new BlueNote(-20280));
            weezePinks.add(new PinkNote(21280));
            weezeBlues.add(new BlueNote(-20560));
            weezePinks.add(new PinkNote(21800));
            weezeBlues.add(new BlueNote(-21220));
            weezePinks.add(new PinkNote(22440));
            weezeBlues.add(new BlueNote(-21620));
            weezePinks.add(new PinkNote(22620));
            weezeBlues.add(new BlueNote(-21840));
            weezePinks.add(new PinkNote(23080));
            weezeBlues.add(new BlueNote(-22380));
            weezePinks.add(new PinkNote(23460));
            weezeBlues.add(new BlueNote(-22740));
            weezePinks.add(new PinkNote(23700));
            weezeBlues.add(new BlueNote(-23000));
            weezePinks.add(new PinkNote(24080));
            weezeBlues.add(new BlueNote(-23240));
            weezeBlues.add(new BlueNote(-23560));
            weezeBlues.add(new BlueNote(-23880));
            weezePinks.add(new PinkNote(25100));
            weezeBlues.add(new BlueNote(-24280));
            weezePinks.add(new PinkNote(25280));
            weezeBlues.add(new BlueNote(-24460));
            weezeBlues.add(new BlueNote(-24840));
            weezePinks.add(new PinkNote(25920));
            weezeBlues.add(new BlueNote(-25180));
            weezeBlues.add(new BlueNote(-25360));
            weezeBlues.add(new BlueNote(-25500));
            weezePinks.add(new PinkNote(26560));
            weezeBlues.add(new BlueNote(-25840));
            weezePinks.add(new PinkNote(27040));
            weezeBlues.add(new BlueNote(-26260));
            weezePinks.add(new PinkNote(27360));
            weezeBlues.add(new BlueNote(-26640));
            weezePinks.add(new PinkNote(27640));
            weezeBlues.add(new BlueNote(-26900));
            weezePinks.add(new PinkNote(28040));
            weezePinks.add(new PinkNote(28360));
            weezeBlues.add(new BlueNote(-27640));
            weezeBlues.add(new BlueNote(-27820));
            weezePinks.add(new PinkNote(28900));
            weezeBlues.add(new BlueNote(-28160));
            weezePinks.add(new PinkNote(29240));
            weezeBlues.add(new BlueNote(-28500));
            weezePinks.add(new PinkNote(29720));
            weezeBlues.add(new BlueNote(-28880));
            weezePinks.add(new PinkNote(29900));
            weezeBlues.add(new BlueNote(-29100));
            weezePinks.add(new PinkNote(30120));
            weezeBlues.add(new BlueNote(-29300));
            weezePinks.add(new PinkNote(30320));
            weezeBlues.add(new BlueNote(-29520));
            weezePinks.add(new PinkNote(30500));
            weezeBlues.add(new BlueNote(-29720));
            weezePinks.add(new PinkNote(30720));
            weezeBlues.add(new BlueNote(-30120));
            weezePinks.add(new PinkNote(31140));
            weezeBlues.add(new BlueNote(-30320));
            weezePinks.add(new PinkNote(31360));
            weezeBlues.add(new BlueNote(-30620));
            weezeBlues.add(new BlueNote(-30800));
            weezeBlues.add(new BlueNote(-30960));
            weezePinks.add(new PinkNote(32000));
            weezePinks.add(new PinkNote(32200));
            weezeBlues.add(new BlueNote(-31460));
            weezePinks.add(new PinkNote(32840));
            weezePinks.add(new PinkNote(33200));
            weezePinks.add(new PinkNote(33500));
            weezeBlues.add(new BlueNote(-32740));
            weezeBlues.add(new BlueNote(-33100));
            weezePinks.add(new PinkNote(34180));
            weezeBlues.add(new BlueNote(-33980));
            weezePinks.add(new PinkNote(35180));
            weezeBlues.add(new BlueNote(-34620));
            weezeBlues.add(new BlueNote(-34960));
            weezePinks.add(new PinkNote(36160));
            weezePinks.add(new PinkNote(36560));
            weezeBlues.add(new BlueNote(-35780));
            weezeBlues.add(new BlueNote(-36120));
            weezePinks.add(new PinkNote(37200));
            weezeBlues.add(new BlueNote(-36460));
            weezePinks.add(new PinkNote(37540));
            weezeBlues.add(new BlueNote(-36780));
            weezePinks.add(new PinkNote(37840));
            weezeBlues.add(new BlueNote(-37100));
            weezePinks.add(new PinkNote(38160));
            weezeBlues.add(new BlueNote(-37420));
            weezePinks.add(new PinkNote(38440));
            weezeBlues.add(new BlueNote(-37700));
            weezePinks.add(new PinkNote(38880));
            weezeBlues.add(new BlueNote(-38300));
            weezePinks.add(new PinkNote(39520));
            weezeBlues.add(new BlueNote(-39020));
            weezePinks.add(new PinkNote(40220));
            weezeBlues.add(new BlueNote(-39640));
            weezePinks.add(new PinkNote(40880));
            weezeBlues.add(new BlueNote(-40300));
            weezePinks.add(new PinkNote(41440));
            weezeBlues.add(new BlueNote(-40700));
            weezePinks.add(new PinkNote(41780));
            weezeBlues.add(new BlueNote(-41040));
            weezeBlues.add(new BlueNote(-41200));
            weezePinks.add(new PinkNote(42240));
            weezeBlues.add(new BlueNote(-41500));
            weezePinks.add(new PinkNote(42560));
            weezeBlues.add(new BlueNote(-41820));
            weezeBlues.add(new BlueNote(-42160));
            weezePinks.add(new PinkNote(43380));
            weezeBlues.add(new BlueNote(-42780));
            weezeBlues.add(new BlueNote(-42940));
            weezePinks.add(new PinkNote(44000));
            weezePinks.add(new PinkNote(44140));
            weezeBlues.add(new BlueNote(-43400));
            weezePinks.add(new PinkNote(44480));
            weezeBlues.add(new BlueNote(-43760));
            weezeBlues.add(new BlueNote(-44060));
            weezeBlues.add(new BlueNote(-44720));
            weezePinks.add(new PinkNote(45960));
            weezePinks.add(new PinkNote(46260));
            weezeBlues.add(new BlueNote(-45540));
            weezePinks.add(new PinkNote(46640));
            weezeBlues.add(new BlueNote(-45900));
            weezeBlues.add(new BlueNote(-46080));
            weezePinks.add(new PinkNote(47120));
            weezeBlues.add(new BlueNote(-46360));
            weezeBlues.add(new BlueNote(-47020));
            weezePinks.add(new PinkNote(48240));
            weezePinks.add(new PinkNote(48580));
            weezeBlues.add(new BlueNote(-47980));
            weezePinks.add(new PinkNote(49040));
            weezeBlues.add(new BlueNote(-48320));
            weezePinks.add(new PinkNote(49360));
            weezeBlues.add(new BlueNote(-48640));
            weezePinks.add(new PinkNote(49720));
            weezeBlues.add(new BlueNote(-48980));
            weezePinks.add(new PinkNote(49960));
            weezeBlues.add(new BlueNote(-49120));
            weezeBlues.add(new BlueNote(-49620));
            weezePinks.add(new PinkNote(50840));
            weezeBlues.add(new BlueNote(-50300));
            weezePinks.add(new PinkNote(51380));
            weezePinks.add(new PinkNote(51680));
            weezeBlues.add(new BlueNote(-50960));
            weezePinks.add(new PinkNote(52040));
            weezeBlues.add(new BlueNote(-51300));
            weezePinks.add(new PinkNote(52400));
            weezeBlues.add(new BlueNote(-51640));
            weezePinks.add(new PinkNote(52740));
            weezeBlues.add(new BlueNote(-52000));
            weezeBlues.add(new BlueNote(-52280));
            weezePinks.add(new PinkNote(53480));
            weezeBlues.add(new BlueNote(-52880));
            weezePinks.add(new PinkNote(53960));
            weezeBlues.add(new BlueNote(-53380));
            weezePinks.add(new PinkNote(54500));
            weezeBlues.add(new BlueNote(-53760));
            weezePinks.add(new PinkNote(54840));
            weezeBlues.add(new BlueNote(-54100));
            weezePinks.add(new PinkNote(55080));
            weezeBlues.add(new BlueNote(-54280));
            weezePinks.add(new PinkNote(55280));
            weezeBlues.add(new BlueNote(-54520));
            weezeBlues.add(new BlueNote(-54860));
            weezePinks.add(new PinkNote(56080));
            weezeBlues.add(new BlueNote(-55500));
            weezePinks.add(new PinkNote(56560));
            weezeBlues.add(new BlueNote(-55940));
            weezePinks.add(new PinkNote(57040));
            weezeBlues.add(new BlueNote(-56280));
            weezePinks.add(new PinkNote(57340));
            weezeBlues.add(new BlueNote(-56540));
            weezePinks.add(new PinkNote(57560));
            weezeBlues.add(new BlueNote(-56720));
            weezePinks.add(new PinkNote(57720));
            weezeBlues.add(new BlueNote(-56900));
            weezePinks.add(new PinkNote(57920));
            weezeBlues.add(new BlueNote(-57140));
            weezePinks.add(new PinkNote(58340));
            weezeBlues.add(new BlueNote(-57740));
            weezePinks.add(new PinkNote(58940));
            weezeBlues.add(new BlueNote(-58160));
            weezePinks.add(new PinkNote(59200));
            weezeBlues.add(new BlueNote(-58560));
            weezePinks.add(new PinkNote(59620));
            weezeBlues.add(new BlueNote(-58880));
            weezePinks.add(new PinkNote(59920));
            weezeBlues.add(new BlueNote(-59120));
            weezePinks.add(new PinkNote(60100));
            weezeBlues.add(new BlueNote(-59320));
            weezePinks.add(new PinkNote(60300));
            weezeBlues.add(new BlueNote(-59500));
            weezePinks.add(new PinkNote(60480));
            weezeBlues.add(new BlueNote(-59700));

            
            
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
            // Menu stuff here

        } else if (state == STATE.GAME) {
            game.paint(g);
            leftBelt.paint(g);
            rightBelt.paint(g);
            hole.paint(g);
            hole2.paint(g);
            kk.paint(g);
            pinkRobo.paint(g);
            blueRobo.paint(g);

            g.setColor(Color.WHITE);
            g.setFont(myFont);
            g.drawString("Score: " + score, 15, 50);
            g.drawString("Combo: " + combo, 15, 85);

            if (!currentAccuracy.equals("") && System.currentTimeMillis() - accuracyTimestamp < accuracyDisplayDuration) {
                g.setColor(accuracyColor);
                g.setFont(new Font("Courier", Font.BOLD, 60));
                g.drawString(currentAccuracy, 750, 250);
            }

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

           
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier", Font.PLAIN, 20));
            
            String controlsText = "CONTROLS: F = Pink Notes | J = Blue Notes | ENTER = Confirm";
            int stringWidth = g.getFontMetrics().stringWidth(controlsText);
            int x = ((width - stringWidth) / 2) + 25;   
            int y = height - 40;  
            
            g.drawString(controlsText, x, y);
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
        ArrayList<PinkNote> pinks = dir == 0 ? brazilPinks : dir == 1 ? lordPinks : weezePinks;
        ArrayList<BlueNote> blues = dir == 0 ? brazilBlues : dir == 1 ? lordBlues : weezeBlues;
       
        for (PinkNote pink : pinks) {
            if (pink.missed) {
                combo = 0;
                pink.missed = false;
              //  System.out.println("Score: " + score + " | Combo: 0");
            }
        }

        for (BlueNote blue : blues) {
            if (blue.missed) {
                combo = 0;
                blue.missed = false;
              //  System.out.println("Score: " + score + " | Combo: 0");
            }
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
                if (dir < 2) dir++;
            } else if (e.getKeyCode() == 38) {
                up = true;
                if (dir > 0) dir--;
            }
        }

        if (state == STATE.GAME) {
            //F key (Pink Notes)
            if (e.getKeyCode() == 70) {
            	pinkRobo.onKeyPressed(e);
                if (debugging) System.out.println(hole.getVx());

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

                if (closestNote != null && Math.abs(closestNote.x - targetX) <= Bad) {
                    String accuracy = accuracyCalculator(closestNote.x, targetX);
                    currentAccuracy = accuracy;
                    accuracyTimestamp = System.currentTimeMillis();

                    if (accuracy.equals("Perfect")) {
                        score += 300;
                        combo++;
                        accuracyColor = Color.GREEN;
                    } else if (accuracy.equals("Good")) {
                        score += 200;
                        combo++;
                        accuracyColor = Color.CYAN;
                    } else if (accuracy.equals("Okay")) {
                        score += 100;
                        combo++;
                        accuracyColor = Color.YELLOW;
                    } else if (accuracy.equals("Bad")) {
                        score += 50;
                        combo++;
                        accuracyColor = Color.RED;
                    }

                    closestNote.isHit = true;
                } else {
                    currentAccuracy = "Miss";
                    accuracyTimestamp = System.currentTimeMillis();
                    accuracyColor = Color.GRAY;
                    combo = 0;
                }
                }

                // J key (Blue Notes)
                if (e.getKeyCode() == 74) {
                    
                    if (debugging) {
                    	System.out.println(hole2.getVx());
                    }
                    ArrayList<BlueNote> activeNotes = dir == 0 ? brazilBlues : dir == 1 ? lordBlues : weezeBlues;
                    int targetX = hole2.x;
                    blueRobo.onKeyPressed(e);
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

                    if (e.getKeyCode() == KeyEvent.VK_R) {
                        state = STATE.MENU;
                    }
                    
                    if (closestNote != null && Math.abs(closestNote.x - targetX) <= Bad) {
                        String accuracy = accuracyCalculator(closestNote.x, targetX);
                        currentAccuracy = accuracy;
                        accuracyTimestamp = System.currentTimeMillis();

                        if (accuracy.equals("Perfect")) {
                            score += 300;
                            combo++;
                            accuracyColor = Color.GREEN;
                        } else if (accuracy.equals("Good")) {
                            score += 200;
                            combo++;
                            accuracyColor = Color.CYAN;
                        } else if (accuracy.equals("Okay")) {
                            score += 100;
                            combo++;
                            accuracyColor = Color.YELLOW;
                        } else if (accuracy.equals("Bad")) {
                            score += 50;
                            combo++;
                            accuracyColor = Color.RED;
                        }

                        closestNote.isHit = true;
                    } else {
                        currentAccuracy = "Miss";
                        accuracyTimestamp = System.currentTimeMillis();
                        accuracyColor = Color.GRAY;
                        combo = 0;
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
