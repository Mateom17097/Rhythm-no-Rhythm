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
	double scaleWidth = 0.07;
	double scaleHeight = 0.03;

	public boolean missed = false;

	public PinkNote() {
		forward = getImage("/imgs/" + "Baabu.png");

		width = 80;
		height = 80;

		x = 1000;
		y = 500;

		vx = -30;
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

		if (x <= 0) {
			x = 920;
			if (!missed) {
				missed = true;
				System.out.println("MISS!");
			}
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
