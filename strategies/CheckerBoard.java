package strategies;

import java.awt.Color;

import cells.Cell;
import cells.GenericCell;
import gui.GUI;
import other.SwarmAgent;

public class CheckerBoard extends AbstractStrategy{

	@Override
	public Cell Layer2(Cell[][] layer1,  int cellSize, int row, int col, GenericCell[] neighbor) {
		Cell layer2 = new Cell(0,0,0,Color.RED);
		{
			if(layer1[row][col].getColor() == Color.BLACK)
				//if the layer 1 cell is black
			{
				if(col%2 == row%2)
					//if its in a spot that should be black
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity1());
					//then you are the same polarity as cell[0][0]
				}
				else
					//if its in a spot that SHOULDN'T be black
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity2());
					//then you are in the opposite polarity than cells[0][0]
				}
			}
			else
				//if the layer 1 cell is white
			{
				if(col%2 == row%2)
					//if its in a spot that SHOULDN'T be 
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity2());
					// then its in the opposite polarity than cells[0]
				}
				else
					//if its in a spot that should be white
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity1());
					//then its in the same polarity as cells[0][0]
				}
			}
		}
		if(layer1[row][col].getColor() == Color.WHITE)
		{
			if(col%2 == row%2)
			{
				layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity1());
			}
			else
			{
				layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity2());
			}
		}
		else
		{
			if(col%2 == row%2)
			{
				layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity2());
			}
			else
			{
				layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity1());
			}
		}

		return layer2;
	}

	public void logic(SwarmAgent agent, Cell[][] layer1, Cell[][] layer2, GenericCell[] neighbors, Cell cell, int cellSize) {
		int cornerCount = 0;
		int edgeCount = 0;
		for(int index = 0; index<neighbors.length; index++)
		{
			if(neighbors[index] != null)
			{
				if(index%2==0)
				{
					if (neighbors[index].getColor() == Color.BLACK){//if on the corner...
						cornerCount++;// cornerCount increases
					}
				}
				else
				{
					if (neighbors[index].getColor() == Color.BLACK){//if on the edge...
						edgeCount++;//edgeCount in creases
					}
				}
			}
			else
			{

			}
		}
		if(cornerCount>edgeCount)//if more corners are black than edges, you should be black in the center
		{
			if(layer1[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].getColor() == Color.BLACK)
			{
				cornerCount = 0;
				edgeCount = 0;
			}
			else
			{
				layer1[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].flipColor();
				layer2[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].flipColor();
				cornerCount = 0;
				edgeCount = 0;
			}
		}
		else if(edgeCount>cornerCount)//and visa versa
		{
			if(layer1[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].getColor() == Color.BLACK)
			{
				layer1[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].flipColor();
				layer2[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].flipColor();
				cornerCount = 0;
				edgeCount = 0;
			}
			else
			{
				cornerCount = 0;
				edgeCount = 0;
			}
		}
		else
		{
			double flipCoin = Math.random();// and if you are tied, like if all are black
			if (flipCoin >.5)// flip a coin
			{
				layer1[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].flipColor();
				layer2[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].flipColor();
				cornerCount = 0;
				edgeCount = 0;
			}
		}
	}


}
