package cells;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import gui.GUI;

public class Cell extends GenCell {
	
	public Cell(double x, double y, double size, Color color) {
		//a lot of these parameters actually belong to Rectangle2D.Double, so we call in the super class GenCell
		super(x,y,size,color);
	}
	
	public void flipColor()
	{
		if (cellColor == Color.BLACK) 
		{
			cellColor = Color.WHITE;
		}
		else if (cellColor == Color.WHITE)
		{
			cellColor = Color.BLACK;
		}
		else if (cellColor == Color.RED)
		{
			cellColor = Color.BLUE;
		}
		else
		{
			cellColor = Color.RED;
		}
	}
	
	public Color getColor() {
		return cellColor;
	}
	
	public void setColor(Color newColor) {
		cellColor = newColor;
	}

}
