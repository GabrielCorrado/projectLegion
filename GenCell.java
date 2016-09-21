package v5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GenCell extends Rectangle2D.Double{
	protected double x, y;
	protected double size;
	protected Color c;
	
	public GenCell(double x, double y, double size, Color c)
	{
		super(x,y,size,size);
		this.x = x;
		this.y = y;
		this.size = size;
		this.c = c;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(c);
		g.fill(this);
		g.fillRect((int)x, (int)y, (int)size, (int)size);
	}
}