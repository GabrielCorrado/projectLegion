import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Agent extends Ellipse2D.Double {
	private int size;
	private Vector velocity;
	
	public Agent(int x, int y, int size, Vector v) {
		super(x, y, size, size);
		this.velocity = v;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fill(this);
		g.fillOval((int)x, (int)y, size, size);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	
}
