package v5;

import java.awt.Color;
import java.awt.Point;


public class Cell_2 
{
	private int x, y;
	private double size;
	private Color c;
	
	public Cell_2(int x, int y, double size, Color c)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.c = c;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public double getSize()
	{
		return size;
	}
	public boolean contains(Point p)
	{
		return (p.getX()>this.x && p.getX()<=this.x+this.size && p.getY()>this.y && p.getY()<=this.y+this.size);
	}
	public void flipColor()
	{
		if(c == Color.RED)
		{
			c = Color.BLUE;
		}
		else
		{
			c = Color.RED;
		}
	}
	
}
