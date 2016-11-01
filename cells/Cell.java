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

import gui.GUI;

/**
 * @authors zak, Nick, Gabriel, Tim
 * description: Cell is a rectangle of one color that is generated in two 2X2 Arrays (cells, cells2) in board
 * parameters: Constructor takes in an X and Y position, a width and height(size X size), and a Color to fill
 */
@SuppressWarnings("serial")
public class Cell extends GenCell {
	public Cell(double x, double y, double size, Color c) {
		//a lot of these parameters actually belong to Rectangle2D.Double, so we call in the super class GenCell
		super(x,y,size,c);
	}
	
	//flip color of Cell
	public void flipColor()
	{
		//these first two cases apply to cells as they only are ever black or white
		if (c == Color.BLACK) 
		{
			c = Color.WHITE;
		}
		else if (c == Color.WHITE) {
			c = Color.BLACK;
		}
		//as cells2 can be different colors chosen by the user, the color is set in the GUI
		else if (c == GUI.getPolarity1())
		{
			c = GUI.getPolarity2();
		}
		else
		{
			c = GUI.getPolarity1();
		}

	}
	
	public Color getColor() {
		return c;
	}
	
	public void setColor(Color newColor) {
		c = newColor;
	}
}
