import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Cell extends Rectangle2D.Double {
	private double size;
	private Color c;
	
	public Cell(double x, double y, double size, Color c) {
		//a lot of these parameters actually belong to Rectangle2D.Double, so we call the super class
		super(x,y,size,size);
		this.c = c;
	}
	
	public void flipColor() {
		if (c == Color.BLACK) {
			c = Color.WHITE;
		}
		else {
			c = Color.BLACK;
		}
	}
	
	public Color getColor() {
		return c;
	}
	
	public void setColor(Color newColor) {
		c = newColor;
	}

	public void draw(Graphics2D g) {
		g.setColor(c);
		g.fill(this);
		g.fillRect((int)x, (int)y, (int)size, (int)size);
	}
	
}