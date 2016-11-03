package code;
/*
 * CURRENTLY NOT USED
 * Author: Zak Gray
 * Description: This is an object to be passed from board to GUI so that labels in GUI can be updated
 * Parameters: The initial board size(nXn), the initial number of Agents, the initial White cells in board.cells and 
 * the initial black cells in board.cells
 */
public class LabelHandler {
	private int initBoardSize, initSwarmCount, initWhiteCellCount, initBlackCellCount;
	
	public LabelHandler(int initBoardSize, int initSwarmCount, int initWhiteCellCount, int initBlackCellCount)
	{
		this.initBoardSize = initBoardSize;
		this.initSwarmCount = initSwarmCount;
		this.initWhiteCellCount = initWhiteCellCount;
		this.initBlackCellCount = initBlackCellCount;
	}
	//getters
	public int getInitBoardSize()
	{
		return initBoardSize;
	}
	public int getInitSwarmCount()
	{
		return initSwarmCount;
	}
	public int getInitWhiteCellCount()
	{
		return initWhiteCellCount;
	}
	public int getInitBlackCellCount()
	{
		return initBlackCellCount;
	}
	//setters
	public void setInitBoardSize(int size)
	{
		initBoardSize = size;
	}
	public void setInitSwarmCount(int swarmCount)
	{
		initSwarmCount = swarmCount;
	}
	public void setInitWhiteCellCount(int whiteCount)
	{
		initWhiteCellCount = whiteCount;
	}
	public void setInitBlackCellCount(int blackCount)
	{
		initBlackCellCount = blackCount;
	}
}
