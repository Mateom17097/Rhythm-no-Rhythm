import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class PinkNote {
	private Image forward;
	private AffineTransform tx;

	int dir = 0;
	int width, height;
	public int x, y;
	int vx, vy;
	double scaleWidth = 3.2;
	double scaleHeight = 5.4;
	public boolean isHit = false;
	public boolean missed = false;

	public PinkNote( int xx) {
		forward = getImage("/imgs/" + "pinkNote.png");

		width = 80;
		height = 80;

		x = xx;
		y = 720;

		vx = -20;
		vy = 0;

		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y);
	}

	public boolean collided(Hole character) {
		Rectangle main = new Rectangle(
			character.getX(),
			character.getY(),
			character.getWidth(),
			character.getWidth()
		);

		Rectangle thisObject = new Rectangle(x, y, width, height);
		return main.intersects(thisObject);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		x += vx;
		y += vy;

		init(x, y);

		
		if (x <= 105 && !missed && !isHit && x > -100) { 
		    missed = true;
		    System.out.println("MISS!");
		    x = -500;
		    
		}
		

		g2.drawImage(forward, tx, null);

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
			URL imageURL = PinkNote.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
