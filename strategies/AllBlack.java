package strategies;

import java.awt.Color;

import cells.Cell;
import cells.GenericCell;
import gui.GUI;
import other.SwarmAgent;

public class AllBlack extends AbstractStrategy{

	@Override
	public Cell Layer2(Cell[][] layer1, int cellSize, int row, int col, GenericCell[] neighbor) {
		Cell layer2 = new Cell(0,0,0,Color.RED);
		if(layer1[row][col].getColor() == Color.BLACK)
			//if the layer 1 cell is black
		{
			layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity1());
		}
		else
			//if the layer 1 cell is white
		{
			layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity2());
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
			layer2[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize] = Layer2(layer1, cellSize, (int)agent.getCenterX()/cellSize, (int)agent.getCenterY()/cellSize, neighbors);
		}

	}

}
