package strategies;

import java.awt.Color;

import cells.Cell;
import cells.GenericCell;
import gui.Board;
import gui.GUI;
import other.SwarmAgent;

public class Lines extends AbstractStrategy{

	@Override
	public Cell Layer2(Cell[][] layer1, int cellSize, int row, int col, GenericCell[] neighbors) {
		Cell layer2 = new Cell(0,0,0,Color.RED);
		int[] listOfPolarities = new int[4];
		int max = listOfPolarities[0], chosenPolarity = 0, cornerCount = 0, edgeCount = 0, vertical = 0, horizontal = 0;
		for(int index = 0; index<neighbors.length; index++)
		{
			if(neighbors[index] != null)
			{
				if(index%2==0)
				{
					if (neighbors[index].getColor() == Color.BLACK){
						cornerCount++;
					}
				}
				else
				{
					if (neighbors[index].getColor() == Color.BLACK){
						edgeCount++;
						if(index==1||index==5)
						{
							vertical++;
						}
						if(index==3||index==7)
						{
							horizontal++;
						}
					}
				}
			}
			else
			{

			}
		}
		if(layer1[row][col].getColor()==Color.BLACK)
		{
			edgeCount+=2;
		}

		if (edgeCount>cornerCount)
		{
			if(vertical>horizontal)
			{
				chosenPolarity = 2;
			}
			else
			{
				chosenPolarity = 0;
			}
		}
		else
		{
			if(vertical>horizontal)
			{
				chosenPolarity = 1;
			}
			else
			{
				chosenPolarity = 3;
			}
		}
		if(layer1[row][col].getColor()==Color.BLACK)
		{
			if(chosenPolarity == 0)
			{
				if(row%2==0)
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity1());
				}
				else
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity2());
				}
			}
			else if(chosenPolarity == 1)
			{
				if(row%2==0)
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity1());
				}
				else
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity2());
				}
			}
			else if(chosenPolarity == 2)
			{
				if(col%2==0)
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity4());
				}
				else
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity3());
				}
			}
			else
			{
				if(col%2==0)
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity4());
				}
				else
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity3());
				}
			}
		}
		else
		{
			if(chosenPolarity == 0)
			{
				if(row%2==0)
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity2());
				}
				else
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity1());
				}
			}
			else if(chosenPolarity == 1)
			{
				if(row%2==0)
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity2());
				}
				else
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity1());
				}
			}
			else if(chosenPolarity == 2)
			{
				if(col%2==0)
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity3());
				}
				else
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity4());
				}
			}
			else
			{
				if(col%2==0)
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity3());
				}
				else
				{
					layer2 = new Cell(row*cellSize, col*cellSize, cellSize, GUI.getPolarity4());
				}
			}
		}

		return layer2;
	}

	@Override
	public void logic(SwarmAgent agent, Cell[][] layer1, Cell[][] layer2, GenericCell[] neighbors, Cell cell, int cellSize) {
		int cornerCount = 0;
		int edgeCount = 0;
		//if (Math.random() < 0.1) {
		for(int index = 0; index<neighbors.length; index++)
		{
			if(neighbors[index] != null)
			{
				if(index%2==0)
				{
					if (neighbors[index].getColor() == Color.BLACK){
						edgeCount++;
					}
				}
				else
				{
					if (neighbors[index].getColor() == Color.BLACK){
						cornerCount++;
					}
				}
			}
			else
			{

			}
		}
		if(cornerCount>edgeCount)
		{
			if(layer1[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].getColor() == Color.BLACK)
			{
				cornerCount = 0;
				edgeCount = 0;
			}
			else
			{
				layer1[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].flipColor();
				layer2[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize] = Layer2(layer1, cellSize, (int)agent.getCenterX()/cellSize, (int)agent.getCenterY()/cellSize, neighbors);
				cornerCount = 0;
				edgeCount = 0;
			}
		}
		else if(edgeCount>cornerCount)
		{
			if(layer1[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].getColor() == Color.BLACK)
			{
				layer1[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].flipColor();
				layer2[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize] = Layer2(layer1, cellSize, (int)agent.getCenterX()/cellSize, (int)agent.getCenterY()/cellSize, neighbors);

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
			double flipCoin = Math.random();
			if (flipCoin >.5)
			{
				layer1[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize].flipColor();
				layer2[(int)agent.getCenterX()/cellSize][(int)agent.getCenterY()/cellSize] = Layer2(layer1, cellSize, (int)agent.getCenterX()/cellSize, (int)agent.getCenterY()/cellSize, neighbors);

				cornerCount = 0;
				edgeCount = 0;
			}
		}


	}

}
