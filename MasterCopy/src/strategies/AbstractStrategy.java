package strategies;

import java.awt.Color;

import cells.Cell;
import cells.GenericCell;
import other.SwarmAgent;

public abstract class AbstractStrategy {
		
	public abstract Cell[][] Layer2 (Cell[][] layer1,Color polarity, int cellSize);
	public abstract void logic (SwarmAgent agent, Cell[][] layer1, Cell[][] layer2, GenericCell[] neighbors, Cell cell, int cellSize);
}
