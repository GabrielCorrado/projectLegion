package other;

public class LabelHandler {
	private int initBoardSize, initSwarmCount, initWhiteCellCount, initBlackCellCount;
	
	public LabelHandler(int initBoardSize, int initSwarmCount, int initWhiteCellCount, int initBlackCellCount)
	{
		this.initBoardSize = initBoardSize;
		this.initSwarmCount = initSwarmCount;
		this.initWhiteCellCount = initWhiteCellCount;
		this.initBlackCellCount = initBlackCellCount;
	}
	
	public int getInitBoardSize()
	{
		return initBoardSize;
	}
}
