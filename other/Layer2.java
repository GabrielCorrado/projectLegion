package other;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import cells.Cell;
import cells.Cell_2;
import cells.GenCell;

public class Layer2 extends JPanel {
	private Cell_2[][] layer2;
	private Color polarity;
	private int numCellsOnSide;
	private GenCell[][] display;
	private Cell[][] layer1;
	private int cellSize;
	
	public Layer2(int width, int height, int numCellsOnSide, int cellSize, Color polarity, Cell[][] layer1) {
		setPreferredSize(new Dimension(width, height));
		this.numCellsOnSide = numCellsOnSide;
		display = new GenCell[numCellsOnSide][numCellsOnSide];
		this.cellSize = cellSize;
		this.layer1 = layer1;
		
		this.layer2();
	}
	
	protected void layer2()
	{

		layer2 = new Cell_2[numCellsOnSide][numCellsOnSide];


		for (int row = 0; row < layer1.length; row++) {
			for (int col = 0; col < layer1[row].length; col++) {

				if(polarity == Color.RED)
					//if the top left is black
				{
					if(layer1[row][col].getColor() == Color.BLACK)
						//if the layer 1 cell is black
					{
						if(col%2 == row%2)
							//if its in a spot that should be black
						{
							layer2[row][col] = new Cell_2(800+row*cellSize, col*cellSize, cellSize, Color.RED);
							//then you are the same polarity as cell[0][0]
						}
						else
							//if its in a spot that SHOULDN'T be black
						{
							layer2[row][col] = new Cell_2(row*cellSize, col*cellSize, cellSize, Color.BLUE);
							//then you are in the opposite polarity than cells[0][0]
						}
					}
					else
						//if the layer 1 cell is white
					{
						if(col%2 == row%2)
							//if its in a spot that SHOULDN'T be 
						{
							layer2[row][col] = new Cell_2(row*cellSize, col*cellSize, cellSize, Color.BLUE);
							// then its in the opposite polarity than cells[0]
						}
						else
							//if its in a spot that should be white
						{
							layer2[row][col] = new Cell_2(row*cellSize, col*cellSize, cellSize, Color.RED);
							//then its in the same polarity as cells[0][0]
						}
					}
				}
				else
				{
					if(layer1[row][col].getColor() == Color.WHITE)
					{
						if(col%2 == row%2)
						{
							layer2[row][col] = new Cell_2(row*cellSize, col*cellSize, cellSize, Color.RED);
						}
						else
						{
							layer2[row][col] = new Cell_2(row*cellSize, col*cellSize, cellSize, Color.BLUE);
						}
					}
					else
					{
						if(col%2 == row%2)
						{
							layer2[row][col] = new Cell_2(row*cellSize, col*cellSize, cellSize, Color.BLUE);
						}
						else
						{
							layer2[row][col] = new Cell_2(row*cellSize, col*cellSize, cellSize, Color.RED);
						}
					}
				}
			}
		}
	}
	
	public void draw(Graphics2D g) {
		for (int row = 0; row < layer2.length; row++) {
			for (int col = 0; col < layer2.length; col++) {
				display[row][col].draw(g);
			}
		}
	}
	
}
