package strategies;

import java.awt.Color;

import cells.Cell;
import cells.GenCell;
import other.Agent;

public abstract class AbstractStrategy {
		
	public abstract Cell[][] Layer2 (Cell[][] layer1,Color polarity, int cellSize);
	public abstract void logic (Agent agent, Cell[][] layer1, Cell[][] layer2, GenCell[] neighbors, Cell cell, int cellSize);
}
