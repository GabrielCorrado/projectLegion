package code;
/*		Author: Zak Gray and Tim Dobeck
 * 		Description: GenCell allows for the objects of Cell and Cell_2 to be created and the rectangles (cells) to be drawn. This extends Rectangle2D and uses the
 * 					 Rectangle2D superclass for creating the cells.
 * 		Parameters: GenCell is the constructor. Each cell has an X coordinate, Y coordinate, fixed size, and Cell is either black or white while Cell_2 is either red or blue
 * 					Draw is allowing the graphics to appear on the JPanel
 * 
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class GenCell extends Rectangle2D.Double {
	protected double x, y;
	protected double size;
	protected Color c;
	
	//constructor
	public GenCell(double x, double y, double size, Color c)
	{
		super(x,y,size,size);
		this.x = x;
		this.y = y;
		this.size = size;
		this.c = c;
	}
	
	public GenCell() {
		//does nothing; just exists for NullCell to work
	}
	
	public void draw(Graphics2D g) {
		g.setColor(c);
		g.fill(this);
		g.fillRect((int)x, (int)y, (int)size, (int)size);
	}
}
