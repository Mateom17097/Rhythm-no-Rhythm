import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.event.KeyEvent;

public class chibiRoboPink {
    private Image forward, forwardAlt;
    private AffineTransform tx;

    private boolean usingAltImage = false;

    int dir = 0; // 0-forward, 1-backward, 2-left, 3-right
    int width, height;
    int x, y;
    double vx, vy;
    double scaleWidth = 3;
    double scaleHeight = 3;

    public chibiRoboPink() {
        forward     = getImage("/imgs/chibiRoboPink.gif");
        forwardAlt  = getImage("/imgs/chibiRoboSmashPink.png");

        width = 0;
        height = 0;

        x = -300;
        y = 395;

        vx = 0;
        vy = 0;

        tx = AffineTransform.getTranslateInstance(0, 0);
        init(x, y);
    }

    public chibiRoboPink(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        x += vx;
        y += vy;

        init(x, y);

        if (y >= -700) {
            vy *= -1;
        }
        if (y <= 0) {
            vy *= -1;
        }

        // Draw correct image based on flag
        g2.drawImage(usingAltImage ? forwardAlt : forward, tx, null);

        // Draw hitbox if debugging
        if (Frame.debugging) {
            g.setColor(Color.green);
            g.drawRect(x, y, width, height);
        }
    }

    private void init(double a, double b) {
        tx.setToTranslation(a, b);
        tx.scale(scaleWidth, scaleHeight);
    }

    private Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = chibiRoboPink.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }

    // Call this from keyPressed in your main Frame
    public void onKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F) {
            usingAltImage = true;

            // Revert back to original image after a short delay
            new Thread(() -> {
                try {
                    Thread.sleep(200); // 200 milliseconds
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                usingAltImage = false;
            }).start();
        }
    }
}
