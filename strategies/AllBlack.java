package strategies;

import java.awt.Color;

import cells.Cell;
import cells.GenericCell;
import gui.GUI;
import other.SwarmAgent;

public class AllBlack extends AbstractStrategy{

	@Override
	public Cell[][] Layer2(Cell[][] layer1, Color polarity, int cellSize) {
		Cell[][] layer2 = new Cell[layer1.length][layer1.length];
		for (int row = 0; row < layer1.length; row++) {
			for (int col = 0; col < layer1[row].length; col++) {

					if(layer1[row][col].getColor() == Color.BLACK)
						//if the layer 1 cell is black
					{
						layer2[row][col] = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity1());
					}
					else
						//if the layer 1 cell is white
					{
						layer2[row][col] = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity2());
					}
				}
			
		}
		return layer2;
	}

	@Override
	public void logic(SwarmAgent agent, Cell[][] layer1, Cell[][] layer2, GenericCell[] neighbors, Cell cell, int cellSize) {
	
				if(layer1[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].getColor() == Color.BLACK)
				{
				}
				else
				{
					layer1[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].flipColor();
					layer2[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].flipColor();
				}
		
	}

}
