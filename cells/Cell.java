package cells;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Cell extends GenCell {
	
	public Cell(double x, double y, double size, Color c) {
		//a lot of these parameters actually belong to Rectangle2D.Double, so we call in the super class GenCell
		super(x,y,size,c);
	}
	
	public void flipColor()
	{
		if (c == Color.BLACK) 
		{
			c = Color.WHITE;
		}
		else if (c == Color.WHITE)
		{
			c = Color.BLACK;
		}
		else if (c == Color.RED)
		{
			c = Color.BLUE;
		}
		else
		{
			c = Color.RED;
		}
	}
	
	public Color getColor() {
		return c;
	}
	
	public void setColor(Color newColor) {
		c = newColor;
	}

}