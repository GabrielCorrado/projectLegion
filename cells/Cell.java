package cells;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import gui.GUI;



/**
 * @authors zak, Nick, Gabriel, Tim
 * description: Cell is a rectangle of one color that is generated in two 2X2 Arrays (cells, cells2) in board
 * parameters: Constructor takes in an X and Y position, a width and height(size X size), and a Color to fill
 */
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
		else if (c == Color.WHITE)
		{
			c = Color.BLACK;
		}
		//as cells2 can be different colors chosen by the user, the color is set in the GUI
		else if (c == GUI.polarity1)
		{
			c = GUI.polarity2;
		}
		else
		{
			c = GUI.polarity1;
		}
	}
	//getters
	public Color getColor() {
		return c;
	}
	//setters
	public void setColor(Color newColor) {
		c = newColor;
	}

}