/*		Author: Zak Gray and Tim Dobeck
 * 		Description: This is the constuctor for determining the SECOND board's cells. The cells in layer 2 can be only red or blue. This class mainly just
 * 					creates the instance of the cells in layer 2. FlipColor allows the color to be flipped if any cell is clicked. This class extends GenCell 
 * 					which is an abstract class that creates the cells as rectangles
 * 		Parameters: Cell_2 is made up of an x coordinate, a y coordinate, a fixed size, and a color and for this class (layer 2) the cells can only be either 
 * 					red or blue.
 * 					contains is made up of Point p which is a specific x and y coordinate. It is used in dtermining the location of the cells in layer 1 for 
 * 					determining what the cells will be for layer 2.
 */

import java.awt.Color;
import java.awt.Point;


@SuppressWarnings("serial")
public class Cell_2 extends GenCell  //GenCell holds the Rectangle2D
{
	//constructor
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
