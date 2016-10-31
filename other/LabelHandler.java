package other;
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
}
