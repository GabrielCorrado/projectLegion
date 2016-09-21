import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Agent extends Ellipse2D.Double {
	private int size;
	private Point2D velocity;
	
	public Agent(int x, int y, int size, Point2D v) {
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

	public Point2D getVelocity() {
		return velocity;
	}
	
	public void setVelocity(Point2D velocity) {
		this.velocity = new Point2D.Double(velocity.getX(), velocity.getY());
	}
	
	public void setVelocity(double x, double y) {
		this.velocity = new Point2D.Double(x, y);
	}
	
	public void setX(double x) {
		//i'm autocasting this to an int before i give it b/c i don't want to deal with fractional bullshit
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	//move the agent along according to its velocity vector, updating its x and y in the process
	public void step() {
		this.setX(x+velocity.getX());
		this.setY(y+velocity.getY());
		this.setVelocity(Math.random()*10-5, Math.random()*10-5);
	}
	
}
