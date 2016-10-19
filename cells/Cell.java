package cells;
/*		Author: Zak Gray and Tim Dobeck
 * 		Description: This is the constuctor for determining the first board's cells. The cells in layer 1 can be only black or white. This class mainly just
 * 					creates the instance of the cells in layer 1. FlipColor allows the color to be flipped if any cell is clicked. This class extends GenCell 
 * 					which is an abstract class that creates the cells as rectangles
 * 		Parameters: Cell is made up of an x coordinate, a y coordinate, a fixed size, and a color and for this class (layer 1) the cells can only be either 
 * 					black or white.
 * 					setColor is made up of an object color which determines what color each cell can be.
 */

import java.awt.Color;

@SuppressWarnings("serial")
public class Cell extends GenCell {
	
	//constructor
	public Cell(double x, double y, double size, Color c) {
		//a lot of these parameters actually belong to Rectangle2D.Double, so we call in the super class GenCell
		super(x,y,size,c);
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
}
