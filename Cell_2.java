import java.awt.Color;
import java.awt.Point;


public class Cell_2 extends GenCell 
{
	
	
	public Cell_2(int x, int y, double size, Color c)
	{
		super(x,y,size,c);
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
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
